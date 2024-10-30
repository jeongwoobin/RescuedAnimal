package com.example.rescuedanimals.data.repository.remote

import com.example.rescuedanimals.data.datasource.remote.RescuedAnimalsDataSource
import com.example.rescuedanimals.data.mapper.AnimalMapper
import com.example.rescuedanimals.data.mapper.ListBodyMapper
import com.example.rescuedanimals.data.model.remote.ListBody
import com.example.rescuedanimals.data.util.NoNetworkException
import com.example.rescuedanimals.data.util.retryWhen
import com.example.rescuedanimals.domain.entity.Animal
import javax.inject.Inject
import com.example.rescuedanimals.domain.entity.Result
import com.example.rescuedanimals.domain.repository.remote.RescuedAnimalsRepository
import com.orhanobut.logger.Logger
import com.squareup.moshi.JsonDataException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.withContext
import java.net.UnknownServiceException


class RescuedAnimalsRepositoryImpl @Inject constructor(
    private val dataSource: RescuedAnimalsDataSource
) : RescuedAnimalsRepository {

    override suspend fun getRescuedAnimal(
        pageNo: Int, numOfRows: Int
    ): Flow<Result<ListBody<Animal>>> = dataSource.getRescuedAnimal(
        pageNo = pageNo, numOfRows = numOfRows
    ).retryWhen { cause, retryCount, delayTime ->
        if (retryCount < 5) {
            Logger.e("retry cause: $cause, retryCount: $retryCount, delayTime: $delayTime")
            delay(delayTime)
            true
        } else false
    }.map { response ->
        val body = response.body()
        if (response.isSuccessful && (body != null)) {
            if (body.response.body != null) {
                val data = body.response.body
                Result.success(
                    data = ListBodyMapper(
                        originEntity = data, newEntity = AnimalMapper.mapperToAnimalList(
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
        Logger.t(e.javaClass.typeName).e(e.message.toString())
        when (e) {
            is JsonDataException -> {
                emit(Result.fail(message = "데이터 에러가 발생했습니다."))
            }

            is NoNetworkException -> {
                emit(Result.fail(message = e.message))
            }

            is UnknownServiceException -> {
                emit(Result.fail(message = "안전하지 않은 네트워크 연결 입니다.(HTTP)"))
            }

            else -> emit(Result.fail(message = "잠시 후 다시 시도해주세요."))
        }
    }
}
