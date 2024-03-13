package org.easy.mobile.convention.plugins

import com.android.build.api.variant.LibraryAndroidComponentsExtension
import com.android.build.gradle.LibraryExtension
import org.easy.mobile.convention.AndroidBuildConfig
import org.easy.mobile.convention.configureKotlinAndroid
import org.easy.mobile.convention.printTestTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class NormalLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)
                defaultConfig.targetSdk = AndroidBuildConfig.targetSdkVersion
            }
            extensions.configure<LibraryAndroidComponentsExtension>(::printTestTask)
        }
    }
}
