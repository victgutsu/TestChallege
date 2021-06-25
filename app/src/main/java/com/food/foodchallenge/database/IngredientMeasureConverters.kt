package com.food.foodchallenge.database

import androidx.room.TypeConverter
import com.google.gson.Gson

object IngredientMeasureConverters {
    @TypeConverter
    fun listToJson(value: List<IngredientMeasure>): String = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<IngredientMeasure>::class.java).toList()
}