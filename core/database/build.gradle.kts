plugins {
    alias(libs.plugins.editable.table.android.library)
    alias(libs.plugins.editable.table.hilt)
    alias(libs.plugins.editable.table.android.room)
}

android {
    namespace = "com.idt.core.database"
}
