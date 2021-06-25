package com.food.foodchallenge.network

import androidx.annotation.VisibleForTesting
import com.food.foodchallenge.network.response.model.DetailsMealServer
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import java.util.regex.Matcher
import java.util.regex.Pattern

object MealDetailsParser {
    fun parseToMealDetails(json: JsonElement): DetailsMealServer {
        val data: JsonObject = json.asJsonObject
        val id = data.get("idMeal").asInt
        val meal = data.get("strMeal").asString
        val area = data.get("strArea").asString
        val category = data.get("strCategory").asString
        val mealThumb = data.get("strMealThumb").asString
        val instructions = data.get("strInstructions").asString
        // we getting ingredients and measures as fields
        // by regex we can understand how many fields there and than parse
        val ingredientMeasureList = mutableListOf<Pair<String, String>>().apply {
            val size = getFieldListFromJsonStr(data.toString(), "strIngredient").size
            for (k in 1 until size) {
                val fieldIngredientName = "strIngredient$k"
                val fieldMeasureName = "strMeasure$k"
                // make sure if it persist
                if (data.has(fieldIngredientName) && data.has(fieldMeasureName)) {
                    val ingredient = data.get(fieldIngredientName)
                    val measure = data.get(fieldMeasureName)
                    // just add if it's not blank and not null
                    if (!ingredient.isJsonNull
                        && !measure.isJsonNull
                        && ingredient.asString.isNotBlank()
                        && measure.asString.isNotBlank()
                    ) {
                        add(Pair(ingredient.asString, measure.asString))
                    }
                }
            }
        }

        return DetailsMealServer(
            idMeal = id,
            strMeal = meal,
            strArea = area,
            strCategory = category,
            strInstructions = instructions,
            strMealThumb = mealThumb,
            ingredientMeasureList = ingredientMeasureList,
        )
    }

    // сan be improved by regex and return all list
    @VisibleForTesting
    fun getFieldListFromJsonStr(jsonStr: String, fieldName: String): List<String> {
        val fieldValues = mutableListOf<String>()
        val matcher: Matcher = Pattern.compile("$fieldName\\d*").matcher(jsonStr)
        while (matcher.find()) {
            if (matcher.group().trim().isNotEmpty()) {
                fieldValues.add(matcher.group().trim())
            }
        }
        return fieldValues
    }
}