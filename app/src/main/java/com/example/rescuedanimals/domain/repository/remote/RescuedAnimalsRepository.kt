package com.example.rescuedanimals.domain.repository.remote

import com.example.rescuedanimals.data.model.remote.ListBody
import com.example.rescuedanimals.domain.entity.Animal
import com.example.rescuedanimals.domain.entity.Result
import com.example.rescuedanimals.domain.entity.Sido
import kotlinx.coroutines.flow.Flow

interface RescuedAnimalsRepository {
    suspend fun getRescuedAnimal(
        pageNo: Int,
        numOfRows: Int): Flow<Result<ListBody<Animal>>>
}