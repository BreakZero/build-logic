package org.easy.mobile.convention.plugins

import com.android.build.gradle.LibraryExtension
import org.easy.mobile.convention.AndroidBuildConfig
import org.easy.mobile.convention.configureAndroidCompose
import org.easy.mobile.convention.configureKotlinAndroid
import org.easy.mobile.convention.libs
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.cocoapods.CocoapodsExtension
import org.jetbrains.kotlin.gradle.plugin.mpp.BitcodeEmbeddingMode

class MultiplatformCommonConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.multiplatform")
                apply("org.jetbrains.kotlin.native.cocoapods")
            }

            extensions.configure<LibraryExtension> {
                configureAndroidCompose(this)
                defaultConfig.targetSdk = AndroidBuildConfig.targetSdkVersion
                testOptions.animationsDisabled = true
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
                    summary = "replace to your feature summary"
                    homepage = "https://mock.com"
                    authors = ""
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
