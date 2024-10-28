package com.example.rescuedanimals.data.repository.remote

import com.example.rescuedanimals.data.datasource.remote.RescuedAnimalsDataSource
import com.example.rescuedanimals.data.mapper.AnimalMapper
import com.example.rescuedanimals.domain.entity.Animal
import javax.inject.Inject
import com.example.rescuedanimals.domain.entity.Result
import com.example.rescuedanimals.domain.repository.remote.RescuedAnimalsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class RescuedAnimalsRepositoryImpl @Inject constructor(
    private val dataSource: RescuedAnimalsDataSource
) : RescuedAnimalsRepository {

    //        val response = withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
//            dataSource.getSido()
//        }
//        Logger.d(response.body())
//        return Result.success(SidoEntity(item = listOf(SidoItemEntity(orgCd = "1", orgdownNm = "2"))))
//    }
    override suspend fun getRescuedAnimal(
        pageNo: Int,
        numOfRows: Int
    ): Result<List<Animal>> =
        try {
            val response = withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
                dataSource.getRescuedAnimal(
                    pageNo = pageNo,
                    numOfRows = numOfRows
                )
            }

            val body = response.body()
            if (response.isSuccessful && (body != null)) {
                if (body.response.body != null) {
                    Result.success(AnimalMapper.mapperToAnimalList(body.response.body.items.item))
                } else {
                    Result.error(body.response.header.resultMsg, null)
                }
            } else {
                Result.error(response.errorBody().toString(), null)
            }
        } catch (e: Exception) {
            Result.fail()
        }

}
