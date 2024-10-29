package com.example.rescuedanimals.data.repository.remote

import com.example.rescuedanimals.data.datasource.remote.RescuedAnimalsDataSource
import com.example.rescuedanimals.data.mapper.AnimalMapper
import com.example.rescuedanimals.data.mapper.ListBodyMapper
import com.example.rescuedanimals.data.model.remote.ListBody
import com.example.rescuedanimals.domain.entity.Animal
import javax.inject.Inject
import com.example.rescuedanimals.domain.entity.Result
import com.example.rescuedanimals.domain.repository.remote.RescuedAnimalsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext


class RescuedAnimalsRepositoryImpl @Inject constructor(
    private val dataSource: RescuedAnimalsDataSource
) : RescuedAnimalsRepository {

    override suspend fun getRescuedAnimal(
        pageNo: Int,
        numOfRows: Int
    ): Flow<Result<ListBody<Animal>>> = dataSource.getRescuedAnimal(
        pageNo = pageNo,
        numOfRows = numOfRows
    ).map { response ->
        val body = response.body()
        if (response.isSuccessful && (body != null)) {
            if (body.response.body != null) {
                val data = body.response.body
                Result.success(
                    data =
                    ListBodyMapper(
                        originEntity = data,
                        newEntity =
                        AnimalMapper.mapperToAnimalList(
                            data.items.item
                        )
                    )
                )
            } else {
                Result.error(message = body.response.header.resultMsg)
            }
        } else {
            Result.error(message = response.errorBody().toString())
        }
    }.catch { e ->
        emit(Result.fail(message = e.toString()))
    }


//        flow<Result<ListBody<Animal>>> {
//        dataSource.getRescuedAnimal(
//            pageNo = pageNo,
//            numOfRows = numOfRows
//        ).collect { response ->
//            val body = response.body()
//            if (response.isSuccessful && (body != null)) {
//                if (body.response.body != null) {
//                    val data = body.response.body
//                    emit(
//                        Result.success(
//                            data =
//                            ListBodyMapper(
//                                originEntity = data,
//                                newEntity =
//                                AnimalMapper.mapperToAnimalList(
//                                    data.items.item
//                                )
//                            )
//                        )
//                    )
//                } else {
//                    emit(Result.error(message = body.response.header.resultMsg))
//                }
//            } else {
//                emit(Result.error(message = response.errorBody().toString()))
//            }
//        }
//
////        val response = withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
////            dataSource.getRescuedAnimal(
////                pageNo = pageNo,
////                numOfRows = numOfRows
////            )
////        }
////
////        val body = response.body()
////        if (response.isSuccessful && (body != null)) {
////            if (body.response.body != null) {
////                val data = body.response.body
////                Result.success(
////                    data =
////                    ListBodyMapper(
////                        originEntity = data,
////                        newEntity =
////                        AnimalMapper.mapperToAnimalList(
////                            data.items.item
////                        )
////                    )
////                )
////            } else {
////                Result.error(message = body.response.header.resultMsg)
////            }
////        } else {
////            Result.error(message = response.errorBody().toString())
////        }
//    }.catch { e ->
//        emit(Result.fail(message = e.toString()))
//    }

}
