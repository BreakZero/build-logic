package org.easy.mobile.convention

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.ApplicationProductFlavor
import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.ProductFlavor
import org.gradle.api.Project
import java.io.FileInputStream
import java.io.InputStreamReader
import java.util.Properties

enum class FlavorDimension {
    Environment
}

enum class Flavor(val dimension: FlavorDimension, val applicationIdSuffix: String? = null) {
    staging(FlavorDimension.Environment, ".staging"),
    prod(FlavorDimension.Environment)
}

fun configureFlavors(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
    flavorConfigurationBlock: ProductFlavor.(flavor: Flavor) -> Unit = {}
) {
    commonExtension.apply {
        flavorDimensions += FlavorDimension.Environment.name
        productFlavors {
            Flavor.values().forEach {
                create(it.name) {
                    dimension = it.dimension.name
                    flavorConfigurationBlock(this, it)
                    if (this@apply is ApplicationExtension && this is ApplicationProductFlavor) {
                        if (it.applicationIdSuffix != null) {
                            applicationIdSuffix = it.applicationIdSuffix
                        }
                    }
                }
            }
        }
    }
}

/**
 * @param path 文件路径，相对于rootProject的路径
 */
fun Project.getPropertiesByFile(path: String): Properties {
    val properties = Properties()
    val keyPropertiesFile = rootProject.file(path)

    if (keyPropertiesFile.isFile) {
        InputStreamReader(FileInputStream(keyPropertiesFile), Charsets.UTF_8).use { reader ->
            properties.load(reader)
        }
    }
    return properties
}
