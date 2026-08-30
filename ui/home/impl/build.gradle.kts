plugins {
    alias(libs.plugins.editable.table.android.library)
    alias(libs.plugins.editable.table.android.library.compose)
    alias(libs.plugins.editable.table.hilt)
    alias(libs.plugins.editable.table.android.navigation)
}

android {
    namespace = "com.idt.ui.home.impl"
}

dependencies {
    implementation(projects.domain.home)

    implementation(projects.ui.home.api)
    implementation(projects.ui.table.api)

    implementation(projects.core.designSystem)
}
