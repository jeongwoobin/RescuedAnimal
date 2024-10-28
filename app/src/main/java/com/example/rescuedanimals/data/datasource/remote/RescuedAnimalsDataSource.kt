package com.example.rescuedanimals.data.datasource.remote

import com.example.rescuedanimals.data.model.remote.AnimalEntity
import com.example.rescuedanimals.data.model.remote.BaseResponse
import com.example.rescuedanimals.data.model.remote.ListBody
import com.example.rescuedanimals.data.model.remote.SidoEntity
import retrofit2.Response

interface RescuedAnimalsDataSource {
    suspend fun getRescuedAnimal(
        pageNo: Int,
        numOfRows: Int
    ): Response<BaseResponse<ListBody<AnimalEntity>>>
}