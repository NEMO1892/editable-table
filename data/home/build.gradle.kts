plugins {
    alias(libs.plugins.editable.table.android.library)
    alias(libs.plugins.editable.table.hilt)
}

android {
    namespace = "com.idt.data.home"
}

dependencies {
    implementation(projects.domain.home)
}
