package com.food.foodchallenge.dagger

import android.app.Application
import androidx.room.Room
import com.food.foodchallenge.database.MealDao
import com.food.foodchallenge.database.MealDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule() {
    @Singleton
    @Provides
    fun providesRoomDatabase(application: Application): MealDatabase {
        return Room.databaseBuilder(application, MealDatabase::class.java, "meal-db").build()
    }

    @Singleton
    @Provides
    fun providesProductDao(mealDatabase: MealDatabase): MealDao {
        return mealDatabase.mealDao
    }
}