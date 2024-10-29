package com.example.rescuedanimals.data.datasource.remote

import com.example.rescuedanimals.data.model.remote.AnimalEntity
import com.example.rescuedanimals.data.model.remote.BaseResponse
import com.example.rescuedanimals.data.model.remote.ListBody
import com.example.rescuedanimals.data.model.remote.SidoEntity
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface RescuedAnimalsDataSource {
    suspend fun getRescuedAnimal(
        pageNo: Int,
        numOfRows: Int
    ): Flow<Response<BaseResponse<ListBody<AnimalEntity>>>>
}