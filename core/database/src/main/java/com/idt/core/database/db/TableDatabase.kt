package com.idt.core.database.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.idt.core.database.dao.TableDao
import com.idt.core.database.entity.CellEntity
import com.idt.core.database.entity.TableRowEntity

@Database(
    entities = [TableRowEntity::class, CellEntity::class],
    version = 1
)
abstract class TableDatabase : RoomDatabase() {

    abstract fun tableDao(): TableDao
}
