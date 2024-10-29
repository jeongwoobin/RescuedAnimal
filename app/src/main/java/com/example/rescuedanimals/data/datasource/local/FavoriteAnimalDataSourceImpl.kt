package com.example.rescuedanimals.data.datasource.local

import com.example.rescuedanimals.data.database.FavoriteAnimalDao
import com.example.rescuedanimals.data.model.remote.AnimalEntity
import com.example.rescuedanimals.data.service.RescuedAnimalsApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FavoriteAnimalDataSourceImpl @Inject constructor(private val favoriteAnimalDao: FavoriteAnimalDao): FavoriteAnimalDataSource {
    override suspend fun getAll(): Flow<List<AnimalEntity>> = favoriteAnimalDao.getAll()

    override suspend fun selectFavoriteAnimal(desertionNo: String): Flow<AnimalEntity> = favoriteAnimalDao.selectFavoriteAnimal(desertionNo = desertionNo)

    override suspend fun insertFavoriteAnimal(favoriteAnimal: AnimalEntity): Flow<Long> = flow { emit(favoriteAnimalDao.insertFavoriteAnimal(favoriteAnimal = favoriteAnimal)) }

    override suspend fun deleteFavoriteAnimal(desertionNo: String): Flow<Int> = flow { emit(favoriteAnimalDao.deleteFavoriteAnimal(desertionNo = desertionNo)) }

    override suspend fun deleteAll(): Flow<Int> = flow { emit(favoriteAnimalDao.deleteAll()) }
}