/*
 * Copyright 2022 The Android Open Source Project
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       https://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

import com.android.build.gradle.LibraryExtension
import com.easy.configs.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("easy.android.library")
                // ignore hilt for this project
                // apply("easy.android.hilt")
            }
            extensions.configure<LibraryExtension> {
                defaultConfig {
                    // testInstrumentationRunner = "com.easy.defi.app.core.testing.EasyTestRunner"
                }
            }
            dependencies {
                add("implementation", libs.findLibrary("coil.kt").get())
                add("implementation", libs.findLibrary("coil.kt.compose").get())

                add("implementation", libs.findLibrary("androidx.compose.viewmodel").get())
                add("implementation", libs.findLibrary("kotlinx.coroutines.android").get())

                val bom = libs.findLibrary("androidx-compose-bom").get()
                val composeBundle = libs.findBundle("compose.android.bundle")
                val koinBundle = libs.findBundle("koin.android.bundle")
                val testBundle = libs.findBundle("junit.test.bundle")

                add("implementation", platform(bom))
                add("implementation", composeBundle)

                add("implementation", koinBundle)

                add("debugImplementation", libs.findLibrary("androidx.compose.ui.tooling").get())
                add("testImplementation", libs.findLibrary("junit").get())
                add("testImplementation", testBundle)
                add("androidTestImplementation", platform(bom))
            }
        }
    }
}
