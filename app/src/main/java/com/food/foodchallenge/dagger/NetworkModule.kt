package com.food.foodchallenge.dagger

import com.food.foodchallenge.network.NetworkClient
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NetworkModule {
    @Singleton
    @Provides
    fun providesNetworkClient(): NetworkClient {
        return NetworkClient()
    }
}
