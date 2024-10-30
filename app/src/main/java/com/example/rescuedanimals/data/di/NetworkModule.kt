package com.example.rescuedanimals.data.di

import android.content.Context
import com.example.rescuedanimals.BuildConfig
import com.example.rescuedanimals.data.util.AuthInterceptor
import com.example.rescuedanimals.data.util.LiveNetworkMonitor
import com.example.rescuedanimals.data.util.NetworkMonitor
import com.example.rescuedanimals.data.util.NetworkMonitorInterceptor
import com.example.rescuedanimals.data.util.PublicSrvcParamInterceptor
import com.orhanobut.logger.Logger
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideMoshiConverterFactory(): MoshiConverterFactory {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        return MoshiConverterFactory.create(moshi)
    }


    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor { message ->
            Logger.t("Okhttp").i(message)
        }.setLevel(level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE)
    }

    @Provides
    @Singleton
    fun provideAuthInterceptor(): AuthInterceptor {
        return AuthInterceptor("")
    }

    @Provides
    @Singleton
    fun provideNetworkMonitor(
        @ApplicationContext appContext: Context
    ): NetworkMonitor{
        return LiveNetworkMonitor(appContext)
    }

    @Provides
    @Singleton
    fun provideNetworkMonitorInterceptor(
        liveNetworkMonitor: NetworkMonitor
    ): NetworkMonitorInterceptor {
        return NetworkMonitorInterceptor(liveNetworkMonitor)
    }

    @Provides
    @Singleton
    fun providePublicSrvcParamInterceptor(): PublicSrvcParamInterceptor {
        return PublicSrvcParamInterceptor
    }

    // Retrofit
    @Singleton
    @Provides
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        networkMonitorInterceptor: NetworkMonitorInterceptor,
        authInterceptor: AuthInterceptor,
        publicSrvcParamInterceptor: PublicSrvcParamInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .apply {
                addInterceptor(httpLoggingInterceptor)
                addInterceptor(networkMonitorInterceptor)
                addInterceptor(authInterceptor)
                addInterceptor(publicSrvcParamInterceptor)
            }
            .build()
    }

    @Singleton
    @Provides
    @Named("RescuedAnimals")
    fun provideRescuedAnimalsRetrofit(
        okHttpClient: OkHttpClient,
        moshiConverterFactory: MoshiConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.RESCUED_ANIMALS_BASE_URL)
            .addConverterFactory(moshiConverterFactory)
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    @Named("AnimalInfo")
    fun provideAnimalInfoRetrofit(
        okHttpClient: OkHttpClient,
        moshiConverterFactory: MoshiConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.ANIMAL_INFO_BASE_URL)
            .addConverterFactory(moshiConverterFactory)
            .client(okHttpClient)
            .build()
    }
}