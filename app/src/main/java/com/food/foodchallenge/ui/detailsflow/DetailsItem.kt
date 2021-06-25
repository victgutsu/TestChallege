package com.food.foodchallenge.ui.detailsflow

data class DetailsItem(
    val id: Int,
    val meal: String,
    val category: String,
    val area: String,
    val instructions: String,
    val thumb: String,
    val ingredientMeasureList: List<Pair<String, String>>
)