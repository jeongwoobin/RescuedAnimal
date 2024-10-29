package com.example.rescuedanimals.domain.usecase

import com.example.rescuedanimals.domain.entity.Animal
import com.example.rescuedanimals.domain.entity.Result
import com.example.rescuedanimals.domain.repository.local.FavoriteAnimalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DeleteFavoriteAnimalUseCase @Inject constructor(
    private val repo: FavoriteAnimalRepository
) {

    suspend operator fun invoke(favoriteAnimal: Animal): Flow<Result<Boolean>> =
        repo.deleteFavoriteAnimal(
            desertionNo = favoriteAnimal.desertionNo ?: throw NullPointerException()
        )

    suspend fun deleteAll(): Flow<Result<Boolean>> = repo.deleteAll()
}