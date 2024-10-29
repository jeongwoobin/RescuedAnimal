package com.example.rescuedanimals.data.datasource.local

import com.example.rescuedanimals.data.model.remote.AnimalEntity
import kotlinx.coroutines.flow.Flow

interface FavoriteAnimalDataSource {
    suspend fun getAll(): Flow<List<AnimalEntity>>

    suspend fun getFavoriteAnimal(desertionNo: String): Flow<AnimalEntity>

    suspend fun insertFavoriteAnimal(favoriteAnimal: AnimalEntity): Flow<Long>

    suspend fun deleteFavoriteAnimal(desertionNo: String): Flow<Int>

    suspend fun deleteAll(): Flow<Int>
}