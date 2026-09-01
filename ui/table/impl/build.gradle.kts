plugins {
    alias(libs.plugins.editable.table.android.library)
    alias(libs.plugins.editable.table.android.library.compose)
    alias(libs.plugins.editable.table.hilt)
    alias(libs.plugins.editable.table.android.navigation)
}

android {
    namespace = "com.idt.ui.table.impl"
}

dependencies {
    implementation(projects.ui.table.api)

    implementation(projects.domain.table)

    implementation(projects.core.designSystem)
}
