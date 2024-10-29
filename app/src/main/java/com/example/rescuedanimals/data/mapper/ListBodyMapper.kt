package com.example.rescuedanimals.data.mapper

import com.example.rescuedanimals.data.model.remote.Item
import com.example.rescuedanimals.data.model.remote.ListBody

object ListBodyMapper {
    operator fun <T> invoke(originEntity: ListBody<*>, newEntity: List<T>): ListBody<T> {
        return ListBody(
            items = Item(newEntity),
            numOfRows = originEntity.numOfRows,
            pageNo = originEntity.pageNo,
            totalCount = originEntity.totalCount
        )
    }
}