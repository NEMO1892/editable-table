plugins {
    alias(libs.plugins.editable.table.android.library)
    alias(libs.plugins.editable.table.hilt)
}

android {
    namespace = "com.idt.core.data_store"
}

dependencies {
    implementation(libs.androidx.datastore.core)
    implementation(libs.androidx.datastore.preferences)
}
