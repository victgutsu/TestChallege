package com.food.foodchallenge.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Meal::class, IngredientMeasure::class], version = MealDatabase.VERSION)
@TypeConverters(
    IngredientMeasureConverters::class
)
abstract class MealDatabase : RoomDatabase() {
    abstract val mealDao: MealDao

    companion object {
        const val VERSION = 1
    }
}