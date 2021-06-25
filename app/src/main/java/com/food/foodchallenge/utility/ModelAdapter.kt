package com.food.foodchallenge.utility

import com.food.foodchallenge.database.IngredientMeasure
import com.food.foodchallenge.database.Meal
import com.food.foodchallenge.network.response.model.DetailsMealServer
import com.food.foodchallenge.network.response.model.SearchMealServer
import com.food.foodchallenge.ui.detailsflow.DetailsItem
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

fun DetailsMealServer.toDetailsItemFromServer(): DetailsItem {
    return DetailsItem(
        id = idMeal,
        meal = strMeal,
        area = strArea,
        thumb = strMealThumb,
        category = strCategory,
        instructions = strInstructions,
        ingredientMeasureList = ingredientMeasureList
    )
}

fun List<Meal>.toSearchItemsFromStorage(): List<SearchItem> {
    return map { it.toSearchItemFromStorage() }
}

fun Meal.toSearchItemFromStorage(): SearchItem {
    return SearchItem(
        id = idMeal,
        meal = strMeal,
        area = strArea,
        thumb = strMealThumb,
        category = strCategory
    )
}

fun Meal.toDetailsItemFromStorage(): DetailsItem {
    return DetailsItem(
        id = idMeal,
        meal = strMeal,
        area = strArea,
        thumb = strMealThumb,
        category = strCategory,
        instructions = strInstructions,
        ingredientMeasureList = ingredientMeasureList.map { Pair(it.ingredient, it.measure) }
    )
}

fun DetailsItem.toMealStorage(): Meal {
    return Meal(
        idMeal = id,
        strMeal = meal,
        strArea = area,
        strMealThumb = thumb,
        strCategory = category,
        strInstructions = instructions,
        ingredientMeasureList = ingredientMeasureList.map {
            // creating own id, just to avoid duplicates
            val id = "$id _ ${ingredientMeasureList.indexOf(it)}"
            IngredientMeasure(id, it.first, it.second)
        }
    )
}
