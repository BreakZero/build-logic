package org.easy.mobile.convention.plugins

import com.android.build.api.dsl.ApplicationExtension
import org.easy.mobile.convention.configureAndroidCompose
import org.easy.mobile.convention.configureFlavors
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

class AndroidApplicationComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
            }

            val extension = extensions.getByType<ApplicationExtension>()

            configureFlavors(extension)
            configureAndroidCompose(extension)
        }
    }
}
