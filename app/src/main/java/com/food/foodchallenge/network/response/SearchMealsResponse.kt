package com.food.foodchallenge.network.response

import com.food.foodchallenge.network.response.model.SearchMealServer

data class SearchMealsResponse(
    val meals: List<SearchMealServer>?
)