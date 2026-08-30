plugins {
    alias(libs.plugins.editable.table.android.application)
    alias(libs.plugins.editable.table.android.application.compose)
    alias(libs.plugins.editable.table.hilt)
}

android {
    namespace = "com.idt.editable_table"

    buildTypes {
        release {
            optimization {
                enable = false
            }
        }
    }
}

dependencies {
    implementation(projects.data.home)

    implementation(projects.domain.home)

    implementation(projects.ui.home)

    implementation(projects.core.dataStore)
    implementation(projects.core.database)
}