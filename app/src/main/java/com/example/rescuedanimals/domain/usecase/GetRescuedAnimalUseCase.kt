package com.example.rescuedanimals.domain.usecase

import com.example.rescuedanimals.data.model.remote.ListBody
import com.example.rescuedanimals.domain.entity.Animal
import com.example.rescuedanimals.domain.entity.Result
import com.example.rescuedanimals.domain.repository.remote.RescuedAnimalsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class GetRescuedAnimalUseCase @Inject constructor(
    private val repo: RescuedAnimalsRepository
) {

    suspend operator fun invoke(
        pageNo: Int,
        numOfRows: Int
    ): Flow<Result<ListBody<Animal>>> = repo.getRescuedAnimal(
        pageNo = pageNo,
        numOfRows = numOfRows
    )

//        flow {
//        emit(Result.loading(null))
//        emit(
//            repo.getRescuedAnimal(
//                pageNo = pageNo,
//                numOfRows = numOfRows
//            )
//        )
//    }.flowOn(Dispatchers.IO)

}