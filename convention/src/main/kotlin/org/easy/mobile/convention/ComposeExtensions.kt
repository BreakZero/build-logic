package org.easy.mobile.convention


import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import java.io.File

/**
 * Configure Compose-specific options
 */
internal fun Project.configureAndroidCompose(
    commonExtension: CommonExtension<*, *, *, *, *>,
) {
    commonExtension.apply {
        configureKotlinAndroid(this)
        kotlinOptions {
            freeCompilerArgs = freeCompilerArgs + buildComposeMetricsParameters()
        }

        composeOptions {
            libs.findVersion("compose-compiler").ifPresentOrElse(
                {
                    kotlinCompilerExtensionVersion = it.toString()
                },
                {
                    kotlinCompilerExtensionVersion = "1.5.9"
                }
            )
        }

        buildFeatures {
            compose = true
        }

        dependencies {
            with(libs) {
                findLibrary("androidx-compose-bom").ifPresent {
                    add("implementation", platform(it))
                    add("androidTestImplementation", platform(it))
                }
                findLibrary("androidx.compose.ui.tooling").ifPresent { add("debugImplementation", it) }
                findLibrary("junit").ifPresent { add("testImplementation", it) }

                findBundle("compose.android.bundle").ifPresent { add("implementation", it) }
            }
        }
    }
}

private fun Project.buildComposeMetricsParameters(): List<String> {
    val metricParameters = mutableListOf<String>()
    val enableMetricsProvider = project.providers.gradleProperty("enableComposeCompilerMetrics")
    val enableMetrics = (enableMetricsProvider.orNull == "true")
    if (enableMetrics) {
        val metricsFolder = File(project.buildDir, "compose-metrics")
        metricParameters.add("-P")
        metricParameters.add(
            "plugin:androidx.compose.compiler.plugins.kotlin:metricsDestination=" + metricsFolder.absolutePath
        )
    }

    val enableReportsProvider = project.providers.gradleProperty("enableComposeCompilerReports")
    val enableReports = (enableReportsProvider.orNull == "true")
    if (enableReports) {
        val reportsFolder = File(project.buildDir, "compose-reports")
        metricParameters.add("-P")
        metricParameters.add(
            "plugin:androidx.compose.compiler.plugins.kotlin:reportsDestination=" + reportsFolder.absolutePath
        )
    }
    return metricParameters.toList()
}
