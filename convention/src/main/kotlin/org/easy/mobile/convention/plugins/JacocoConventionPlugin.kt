package org.easy.mobile.convention.plugins

import com.android.build.api.variant.ApplicationAndroidComponentsExtension
import com.android.build.api.variant.LibraryAndroidComponentsExtension
import org.easy.mobile.convention.configureJacoco
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

class JacocoConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("org.gradle.jacoco")
            }
            plugins.withId("com.android.application") {
                val extension = target.extensions.getByType<ApplicationAndroidComponentsExtension>()
                target.configureJacoco(extension)
            }
            plugins.withId("com.android.library") {
                val extension = target.extensions.getByType<LibraryAndroidComponentsExtension>()
                target.configureJacoco(extension)
            }
        }
    }
}
