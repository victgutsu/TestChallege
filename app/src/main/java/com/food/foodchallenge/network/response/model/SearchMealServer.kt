package com.food.foodchallenge.network.response.model

data class SearchMealServer(
    val idMeal: Int,
    val strMeal: String,
    val strCategory: String,
    val strArea: String,
    val strInstructions: String,
    val strMealThumb: String
)