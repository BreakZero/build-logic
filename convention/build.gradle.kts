/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

plugins {
    `kotlin-dsl`
}

group = "com.easy.plugin.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    compileOnly(easy.android.tools.build)
    compileOnly(easy.kotlin.gradle.plugin)
}

gradlePlugin {
    version = easy.versions.version
    plugins {
        register("androidApplicationCompose") {
            id = easy.plugins.android.application.compose.get().pluginId
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }
        register("androidApplication") {
            id = easy.plugins.android.application.asProvider().get().pluginId
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidApplicationJacoco") {
            id = easy.plugins.android.application.jacoco.get().pluginId
            implementationClass = "AndroidApplicationJacocoConventionPlugin"
        }
        register("androidLibraryCompose") {
            id = easy.plugins.android.library.compose.get().pluginId
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }
        register("androidLibrary") {
            id = easy.plugins.android.library.asProvider().get().pluginId
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("multiplatformLibrary") {
            id = easy.plugins.multiplatform.library.get().pluginId
            implementationClass = "MultiplatformLibraryConventionPlugin"
        }
        register("androidFeatureKoin") {
            id = easy.plugins.android.feature.koin.get().pluginId
            implementationClass = "AndroidFeatureWithKoinConventionPlugin"
        }
        register("androidFeatureHilt") {
            id = easy.plugins.android.feature.hilt.get().pluginId
            implementationClass = "AndroidFeatureWithHiltConventionPlugin"
        }
        register("androidLibraryJacoco") {
            id = easy.plugins.android.library.jacoco.get().pluginId
            implementationClass = "AndroidLibraryJacocoConventionPlugin"
        }
        register("androidTest") {
            id = easy.plugins.android.test.get().pluginId
            implementationClass = "AndroidTestConventionPlugin"
        }
        register("androidHilt") {
            id = easy.plugins.android.hilt.get().pluginId
            implementationClass = "AndroidHiltConventionPlugin"
        }
        register("firebase-perf") {
            id = easy.plugins.firebase.perf.get().pluginId
            implementationClass = "FirebasePerfConventionPlugin"
        }
    }
}
