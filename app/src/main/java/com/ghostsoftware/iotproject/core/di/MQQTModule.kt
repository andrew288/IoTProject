package com.ghostsoftware.iotproject.core.di

import android.content.Context
import com.ghostsoftware.iotproject.data.network.MqttClientProvideImpl
import com.ghostsoftware.iotproject.data.network.MqttClientProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object MQQTModule {
    @Provides
    @Singleton
    fun provideMqttClientProvider(@ApplicationContext appContext: Context): MqttClientProvider {
        return MqttClientProvideImpl(appContext)
    }
}