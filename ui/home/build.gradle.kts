plugins {
    alias(libs.plugins.editable.table.android.library)
    alias(libs.plugins.editable.table.android.library.compose)
    alias(libs.plugins.editable.table.hilt)
}

android {
    namespace = "com.idt.ui.home"
}

dependencies {
    implementation(projects.domain.home)
}
