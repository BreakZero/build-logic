import com.android.build.gradle.AbstractAppExtension
import com.android.build.gradle.internal.api.BaseVariantOutputImpl
import org.gradle.api.Project

fun Project.configOutputName(
    commonExtension: AbstractAppExtension
) {
    commonExtension.apply {
        applicationVariants.all {
            this.outputs.all {
                val outputFileName = "${applicationId}-${versionName}-${flavorName}.${outputFile.extension}"
                (this as BaseVariantOutputImpl).outputFileName = outputFileName
            }
        }
    }
}