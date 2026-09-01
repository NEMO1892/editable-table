package com.idt.core.database.di

import android.content.Context
import androidx.room.Room
import com.idt.core.common.coroutines.IoDispatcher
import com.idt.core.database.dao.TableDao
import com.idt.core.database.db.TableDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton
import kotlin.jvm.java

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context,
        @IoDispatcher ioDispatcher: CoroutineDispatcher,
    ) = Room.databaseBuilder(
        context,
        TableDatabase::class.java, DATABASE_NAME
    )
        .setQueryCoroutineContext(ioDispatcher)
        .build()

    @Provides
    @Singleton
    fun provideTableDao(tableDatabase: TableDatabase): TableDao = tableDatabase.tableDao()

    private companion object {

        const val DATABASE_NAME = "editable_table_db"
    }
}
