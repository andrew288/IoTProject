package com.ghostsoftware.iotproject.core.di

import android.content.Context
import androidx.room.Room
import com.ghostsoftware.iotproject.data.network.local.AppDatabase
import com.ghostsoftware.iotproject.data.network.local.SensorDataEntityDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Provides
    @Singleton
    fun providesAppDatabase(@ApplicationContext app: Context)= Room.databaseBuilder(
            app,
            AppDatabase::class.java, "app-database"
        ).build()

    @Provides
    @Singleton
    fun providesSensorDao(appDatabase: AppDatabase): SensorDataEntityDao = appDatabase.sensorDataEntityDao()

}