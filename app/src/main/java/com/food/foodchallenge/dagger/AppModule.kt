package com.food.foodchallenge.dagger

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {
    @Singleton
    @Provides
    fun bindContext(application: Application): Context {
        return application
    }
}
