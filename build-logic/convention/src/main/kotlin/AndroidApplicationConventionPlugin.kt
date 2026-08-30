
import com.android.build.api.dsl.ApplicationExtension
import extensions.Constants
import extensions.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidApplicationConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.android.application")

            extensions.configure<ApplicationExtension> {
                with(defaultConfig) {
                    targetSdk = Constants.TARGET_SDK
                    versionCode = 1
                    versionName = "1.0"
                    applicationId = "com.idt.editable_table"
                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                }

                buildFeatures {
                    buildConfig = true
                }

                configureKotlinAndroid(this)
            }
        }
    }
}
