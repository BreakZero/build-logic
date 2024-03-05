import com.android.build.api.variant.LibraryAndroidComponentsExtension
import com.android.build.gradle.LibraryExtension
import com.easy.configs.configurePrintApksTask
import com.easy.configs.libs
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.cocoapods.CocoapodsExtension
import org.jetbrains.kotlin.gradle.plugin.mpp.BitcodeEmbeddingMode

class MultiplatformLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.multiplatform")
                apply("org.jetbrains.kotlin.native.cocoapods")
            }

            extensions.configure<LibraryExtension> {
                compileOptions {
                    sourceCompatibility = JavaVersion.VERSION_17
                    targetCompatibility = JavaVersion.VERSION_17
                }
                compileSdk = AndroidBuildConfig.compileSdkVersion
                defaultConfig {
                    minSdk = AndroidBuildConfig.minSdkVersion
                }
                lint {
                    abortOnError = false
                }
            }
            extensions.configure<LibraryAndroidComponentsExtension> {
                configurePrintApksTask(this)
            }

            extensions.configure<KotlinMultiplatformExtension> {

                androidTarget {
                    compilations.all {
                        kotlinOptions {
                            jvmTarget = JavaVersion.VERSION_17.toString()
                        }
                    }
                }
                iosX64()
                iosArm64()
                iosSimulatorArm64()
                applyDefaultHierarchyTemplate()

                sourceSets.getByName("commonMain") {
                    dependencies {
                        implementation(libs.findLibrary("koin.core").get())
                    }
                }

                (this as ExtensionAware).extensions.configure<CocoapodsExtension> {
                    // TODO change to your information
                    summary = "cocoapod submodule"
                    homepage = "dejinlu.com"
                    authors = "Dougie"
                    version = "1.0"
                    ios.deploymentTarget = "16.0"
                    framework {
                        baseName = project.name
                        embedBitcode(BitcodeEmbeddingMode.BITCODE)
                    }
                }
            }
        }
    }
}
