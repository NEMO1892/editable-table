package com.idt.data.table.di

import com.idt.data.table.datasource.LocalTableDataSource
import com.idt.data.table.datasource.TableDataSource
import com.idt.data.table.repository.TableRepositoryImpl
import com.idt.domain.table.repository.TableRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class TableDataModule {

    @Binds
    abstract fun bindTableRepository(tableRepositoryImpl: TableRepositoryImpl): TableRepository

    @Binds
    abstract fun bindTableDataSource(localTableDataSource: LocalTableDataSource): TableDataSource
}
