### About
By now, in android project the commendation is using `Composing builds` and managing the libs version in a version catalog file.
so this repository is for that(refer to [nowinandroid](https://github.com/android/nowinandroid)).
Using it as a submodule, will reduce almost 80% gradle config in you project.

### How To Use
add as submodule in you project
```shell
git submodule add https://github.com/BreakZero/build-logic
```
update root project `build.gradle.kts`
```kotlin
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlinAndroid) apply false
}
```
create `versionCatalogs` in root project `settings.gradle.kts` dependencyResolutionManagement block
```kotlin
versionCatalogs {
    create("libs") {
        from(files("./build-logic/libs.versions.toml"))
    }
}
```
sync the project, then you can use the plugins and libs as below:
```kotlin
plugins {
    id("easy.android.application")
    id("easy.android.application.compose")
    id("easy.android.application.jacoco")
    id("jacoco")
}

dependencies {
    implementation(libs.core.ktx)
    implementation(libs.androidx.activity.compose)
}
```
