package com.food.foodchallenge.utility

import com.food.foodchallenge.network.response.model.SearchMealServer
import com.food.foodchallenge.ui.searchflow.SearchItem

fun SearchMealServer.toSearchItemFromServer(): SearchItem {
    return SearchItem(
        id = idMeal,
        meal = strMeal,
        area = strArea,
        thumb = strMealThumb,
        category = strCategory
    )
}

fun List<SearchMealServer>.toSearchItemsFromServer(): List<SearchItem> {
    return map { it.toSearchItemFromServer() }
}
