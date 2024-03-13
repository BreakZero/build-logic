package org.easy.mobile.convention.plugins

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.variant.ApplicationAndroidComponentsExtension
import org.easy.mobile.convention.AndroidBuildConfig
import org.easy.mobile.convention.configureKotlinAndroid
import org.easy.mobile.convention.printTestTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class NormalAppConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<ApplicationExtension> {
                defaultConfig {
                    versionCode = AndroidBuildConfig.versionCode
                    versionName = AndroidBuildConfig.versionName
                    targetSdk = AndroidBuildConfig.targetSdkVersion
                }
                configureKotlinAndroid(this)
            }

            extensions.configure<ApplicationAndroidComponentsExtension> {
                printTestTask(this)
            }
        }
    }
}