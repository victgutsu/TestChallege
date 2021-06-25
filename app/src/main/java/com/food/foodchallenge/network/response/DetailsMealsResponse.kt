package com.food.foodchallenge.network.response

import com.google.gson.JsonElement

data class DetailsMealsResponse(
    // we need return as JsonElement cause some data should be parsed by hands
    val meals: List<JsonElement>?
)