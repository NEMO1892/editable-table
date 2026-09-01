package com.idt.data.table.mapper

import com.idt.core.database.entity.TableRowEntity
import com.idt.domain.table.repository.TableRow
import javax.inject.Inject

internal class TableRowEntityMapper @Inject constructor() {

    operator fun invoke(unmapped: TableRow): TableRowEntity = with(unmapped) {
        TableRowEntity(id = id)
    }
}
