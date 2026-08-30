plugins {
    alias(libs.plugins.editable.table.android.application)
    alias(libs.plugins.editable.table.android.application.compose)
    alias(libs.plugins.editable.table.hilt)
    alias(libs.plugins.editable.table.android.navigation)
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

    implementation(projects.ui.home.impl)
    implementation(projects.ui.home.api)

    implementation(projects.ui.table.impl)
    implementation(projects.ui.table.api)

    implementation(projects.core.dataStore)
    implementation(projects.core.database)
    implementation(projects.core.designSystem)
}
