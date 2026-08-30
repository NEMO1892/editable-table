import com.android.build.api.dsl.LibraryExtension
import extensions.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.android.library")

            with(extensions.getByType<LibraryExtension>()) {
                configureKotlinAndroid(this)
                buildFeatures {
                    buildConfig = true
                }
            }
        }
    }
}
