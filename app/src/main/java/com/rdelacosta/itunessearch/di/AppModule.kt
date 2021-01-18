package com.rdelacosta.itunessearch.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.rdelacosta.itunessearch.data.local.AppDatabase
import com.rdelacosta.itunessearch.data.local.MusicDao
import com.rdelacosta.itunessearch.data.remote.MusicRemoteDataSource
import com.rdelacosta.itunessearch.data.remote.MusicService
import com.rdelacosta.itunessearch.data.repository.MusicRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    private const val BASE_URL = "https://itunes.apple.com/"

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson) : Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideMusicService(retrofit: Retrofit) : MusicService = retrofit.create(MusicService::class.java)

    @Singleton
    @Provides
    fun provideMusicRemoteDataSource(musicService: MusicService) = MusicRemoteDataSource(musicService)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) = AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideMusicDao(database: AppDatabase) = database.musicDao()

    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: MusicRemoteDataSource, localDataSource: MusicDao)
        = MusicRepository(remoteDataSource, localDataSource)

}