package com.example.rescuedanimals.domain.repository.remote

import com.example.rescuedanimals.domain.entity.Animal
import com.example.rescuedanimals.domain.entity.Result
import com.example.rescuedanimals.domain.entity.Sido

interface RescuedAnimalsRepository {
    suspend fun getRescuedAnimal(
        pageNo: Int,
        numOfRows: Int): Result<List<Animal>>
}