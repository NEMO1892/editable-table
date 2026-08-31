plugins {
    alias(libs.plugins.editable.table.android.library)
    alias(libs.plugins.editable.table.hilt)
}

android {
    namespace = "com.idt.data.table"
}

dependencies {
    implementation(projects.domain.table)

    implementation(projects.core.dataStore)
}
