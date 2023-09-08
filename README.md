## About
By now, in android project the commendation is using `Composing builds` and managing the libs version in a version catalog file.
so this repository is for that.
Using it as a submodule, will reduce almost 80% gradle config in your project.

## How To Use
### Step 1
  Using as submodule in your project with git command.
```shell
git submodule add https://github.com/BreakZero/build-logic
```

### Step 2
Creating version catalog
- #### Option1 
Using the default provided `default.versions.toml` in root folder.
1. Open project `settings.gradle.kts`, 
2. add below code into `dependencyResolutionManagement` block
```kotlin
versionCatalogs {
    create("libs") {
        from(files("./build-logic/default.versions.toml"))
    }
}
```

- #### Option2
Managed by local file
1. In project `gradle` folder, create a catalog file named `libs.versions.toml`
2. Refer `default.versions.toml` to full you local version catalog
> Note: You have to add some default(default.versions.toml) that used in plugin, cause plugins need them.

### Step 3
Sync the project, and you can use it like below.<br>
For example plugin:
```kotlin
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin-android) apply false
}   
```
```kotlin
plugins {
    id("easy.android.application")
    id("easy.android.application.compose")
    id("easy.android.application.jacoco")
    id("jacoco")
}
```
  
For example dependencies:
```kotlin
dependencies {
    implementation(libs.core.ktx)
    implementation(libs.androidx.compose.activity)
}   
```

## Samples
Here is a [Sample](https://github.com/BreakZero/Build-Logic-UsingExample)

## Plan 
In fact, when we are in different project, we will use different libraries.<br>
For example, in native Android project, Hilt is better than Koin for DI, but in
KMP project, Koin will be better.<br>
So plan to support multi type of `version catalog file` as several files.<br>
Clean plugins

## Refer
[nowinandroid](https://github.com/android/nowinandroid)
