package com.example.rescuedanimals.domain.usecase

import com.example.rescuedanimals.domain.entity.Animal
import com.example.rescuedanimals.domain.entity.Result
import com.example.rescuedanimals.domain.entity.Sido
import com.example.rescuedanimals.domain.repository.remote.RescuedAnimalsRepository
import javax.inject.Inject

class GetRescuedAnimalUseCase @Inject constructor(
    private val repo: RescuedAnimalsRepository
) {

    suspend operator fun invoke(
        pageNo: Int,
        numOfRows: Int
    ): Result<List<Animal>> {
        return repo.getRescuedAnimal(
            pageNo = pageNo,
            numOfRows = numOfRows
        )
    }
}