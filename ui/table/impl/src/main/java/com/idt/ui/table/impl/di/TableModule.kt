package com.idt.ui.table.impl.di

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.idt.ui.table.impl.navigation.tableEntryBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(ActivityRetainedComponent::class)
internal object TableModule {

    @IntoSet
    @Provides
    fun provideTableEntryBuilder(): EntryProviderScope<NavKey>.(NavBackStack<NavKey>) -> Unit = { tableEntryBuilder() }
}
