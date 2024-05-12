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
    plugins.register("androidApplication") {
        id = "easy.android.application"
        implementationClass = "org.easy.mobile.convention.plugins.AndroidApplicationConventionPlugin"
    }
    plugins.register("androidApplicationCompose") {
        id = "easy.android.application.compose"
        implementationClass = "org.easy.mobile.convention.plugins.AndroidApplicationComposeConventionPlugin"
    }
    plugins.register("androidLibrary") {
        id = "easy.android.library"
        implementationClass = "org.easy.mobile.convention.plugins.AndroidLibraryConventionPlugin"
    }
    plugins.register("androidLibraryCompose") {
        id = "easy.android.library.compose"
        implementationClass = "org.easy.mobile.convention.plugins.AndroidLibraryComposeConventionPlugin"
    }
    plugins.register("androidLint") {
        id = "easy.android.lint"
        implementationClass = "org.easy.mobile.convention.plugins.AndroidLintConventionPlugin"
    }
    plugins.register("androidTest") {
        id = "easy.android.test"
        implementationClass = "org.easy.mobile.convention.plugins.AndroidTestConventionPlugin"
    }
    plugins.register("androidHilt") {
        id = "easy.android.hilt"
        implementationClass = "org.easy.mobile.convention.plugins.HiltConventionPlugin"
    }
    plugins.register("androidJacoco") {
        id = "easy.android.jacoco"
        implementationClass = "org.easy.mobile.convention.plugins.JacocoConventionPlugin"
    }
    plugins.register("jvmLibrary") {
        id = "easy.library.jvm"
        implementationClass = "org.easy.mobile.convention.plugins.JvmLibraryConventionPlugin"
    }
    plugins.register("androidKoin") {
        id = "easy.android.koin"
        implementationClass = "org.easy.mobile.convention.plugins.KoinConventionPlugin"
    }
    plugins.register("multiplatformLibrary") {
        id = "easy.library.multiplatform"
        implementationClass = "org.easy.mobile.convention.plugins.MultiplatformCommonConventionPlugin"
    }
}
