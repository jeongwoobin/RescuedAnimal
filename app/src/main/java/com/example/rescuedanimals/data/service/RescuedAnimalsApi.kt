package com.example.rescuedanimals.data.service

import com.example.rescuedanimals.data.model.remote.AnimalEntity
import com.example.rescuedanimals.data.model.remote.BaseResponse
import com.example.rescuedanimals.data.model.remote.ListBody
import com.example.rescuedanimals.data.model.remote.SidoEntity
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RescuedAnimalsApi {

    @GET("abandonmentPublic")
    suspend fun fetchRescuedAnimal(
        @Query("pageNo") pageNo: Int,
        @Query("numOfRows") numOfRows: Int
    ): Response<BaseResponse<ListBody<AnimalEntity>>>
}