package com.food.foodchallenge.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ingredientmeasure_table")
data class IngredientMeasure(
    @PrimaryKey val id: String,
    val ingredient: String,
    val measure: String,
)
