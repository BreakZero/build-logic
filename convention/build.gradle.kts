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

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    compileOnly(easy.android.tools.build)
    compileOnly(easy.kotlin.gradle.plugin)
}

gradlePlugin {
    plugins.register("org-easy-android-test") {
        id = easy.plugins.android.test.get().pluginId
        implementationClass = "org.easy.android.convention.plugins.AndroidTestConventionPlugin"
    }

    plugins.register("org-easy-application-compose") {
        id = easy.plugins.compose.application.get().pluginId
        implementationClass = "org.easy.android.convention.plugins.ComposeAppConventionPlugin"
    }

    plugins.register("org-easy-library-compose") {
        id = easy.plugins.compose.library.get().pluginId
        implementationClass = "org.easy.android.convention.plugins.ComposeLibraryConventionPlugin"
    }
    plugins.register("org-easy-hilt") {
        id = easy.plugins.hilt.get().pluginId
        implementationClass = "org.easy.android.convention.plugins.HiltConventionPlugin"
    }
    plugins.register("org-easy-jacoco") {
        id = easy.plugins.jacoco.get().pluginId
        implementationClass = "org.easy.android.convention.plugins.JacocoConventionPlugin"
    }
    plugins.register("org-easy-koin") {
        id = easy.plugins.koin.get().pluginId
        implementationClass = "org.easy.android.convention.plugins.KoinConventionPlugin"
    }
    plugins.register("org-easy-multiplatform") {
        id = easy.plugins.multiplatform.get().pluginId
        implementationClass = "org.easy.android.convention.plugins.MultiplatformCommonConventionPlugin"
    }
    plugins.register("org-easy-application") {
        id = easy.plugins.application.get().pluginId
        implementationClass = "org.easy.android.convention.plugins.NormalAppConventionPlugin"
    }
    plugins.register("org-easy-library") {
        id = easy.plugins.library.get().pluginId
        implementationClass = "org.easy.android.convention.plugins.NormalLibraryConventionPlugin"
    }
}
