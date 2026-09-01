plugins {
    alias(libs.plugins.editable.table.android.library)
    alias(libs.plugins.editable.table.hilt)
    alias(libs.plugins.editable.table.android.room)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.idt.core.database"
}

dependencies {
    implementation(projects.core.common)
}
