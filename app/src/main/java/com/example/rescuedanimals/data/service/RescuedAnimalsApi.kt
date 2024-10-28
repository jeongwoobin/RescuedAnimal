package com.example.rescuedanimals.data.service

import com.example.rescuedanimals.data.model.remote.AnimalEntity
import com.example.rescuedanimals.data.model.remote.BaseResponse
import com.example.rescuedanimals.data.model.remote.ListBody
import com.example.rescuedanimals.data.model.remote.SidoEntity
import retrofit2.Response
import retrofit2.http.GET

interface RescuedAnimalsApi {

    @GET("abandonmentPublic")
    suspend fun fetchRescuedAnimal(
        pageNo: Int, numOfRows: Int
    ): Response<BaseResponse<ListBody<AnimalEntity>>>
}