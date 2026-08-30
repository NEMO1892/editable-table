package com.idt.ui.home.di

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.idt.ui.home.navigation.homeEntryBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(ActivityRetainedComponent::class)
internal object HomeModule {

    @IntoSet
    @Provides
    fun provideHomeEntryBuilder(): EntryProviderScope<NavKey>.(NavBackStack<NavKey>) -> Unit =
        { backStack ->
            homeEntryBuilder(onNavigateToTable = { tableKey -> backStack.add(tableKey) })
        }
}
