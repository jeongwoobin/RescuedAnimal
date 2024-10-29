package com.example.rescuedanimals.data.di

import com.example.rescuedanimals.data.database.FavoriteAnimalDao
import com.example.rescuedanimals.data.datasource.local.FavoriteAnimalDataSource
import com.example.rescuedanimals.data.datasource.local.FavoriteAnimalDataSourceImpl
import com.example.rescuedanimals.data.service.RescuedAnimalsApi
import com.example.rescuedanimals.data.datasource.remote.RescuedAnimalsDataSource
import com.example.rescuedanimals.data.datasource.remote.RescuedAnimalsDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DataSourceModule {

    @Provides
    @Singleton
    fun provideRescuedAnimalRemoteDataSource(rescuedAnimalsApi: RescuedAnimalsApi): RescuedAnimalsDataSource {
        return RescuedAnimalsDataSourceImpl(rescuedAnimalsApi)
    }

    @Provides
    @Singleton
    fun provideFavoriteAnimalDataSource(favoriteAnimalDao: FavoriteAnimalDao): FavoriteAnimalDataSource {
        return FavoriteAnimalDataSourceImpl(favoriteAnimalDao)
    }
}