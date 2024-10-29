package com.example.rescuedanimals.data.datasource.remote

import com.example.rescuedanimals.data.model.remote.AnimalEntity
import com.example.rescuedanimals.data.service.RescuedAnimalsApi
import com.example.rescuedanimals.data.model.remote.BaseResponse
import com.example.rescuedanimals.data.model.remote.ListBody
import com.example.rescuedanimals.data.model.remote.SidoEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class RescuedAnimalsDataSourceImpl @Inject constructor(
    private val api: RescuedAnimalsApi
) : RescuedAnimalsDataSource {

    override suspend fun getRescuedAnimal(
        pageNo: Int,
        numOfRows: Int
    ): Flow<Response<BaseResponse<ListBody<AnimalEntity>>>> =
        flow { emit(api.fetchRescuedAnimal(pageNo = pageNo, numOfRows = numOfRows)) }

}