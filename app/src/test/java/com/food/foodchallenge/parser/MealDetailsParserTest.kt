package com.food.foodchallenge.parser

import com.food.foodchallenge.network.MealDetailsParser
import com.google.gson.JsonParser
import org.junit.Assert
import org.junit.Test

class MealDetailsParserTest {

    @Test
    fun `should parse json to details`() {
        val json = JsonParser().parse(giveJson())
        val mealDetails = MealDetailsParser.parseToMealDetails(json)
        Assert.assertTrue(mealDetails.idMeal == 52772)
    }

    @Test
    fun `should parse json and create list of ingredient measures`() {
        val parse = JsonParser().parse(giveJson())
        val mealDetails = MealDetailsParser.parseToMealDetails(parse)
        Assert.assertTrue(mealDetails.ingredientMeasureList.isNotEmpty())
    }

    @Test
    fun `should parse json and create list of ingredient measures size 8`() {
        val parse = JsonParser().parse(giveJson8Ingred())
        val mealDetails = MealDetailsParser.parseToMealDetails(parse)
        Assert.assertTrue(mealDetails.ingredientMeasureList.size == 8)
    }

    @Test
    fun `should parse json and create list of ingredient measures size 2`() {
        val parse = JsonParser().parse(giveJsonUnUsual())
        val mealDetails = MealDetailsParser.parseToMealDetails(parse)
        Assert.assertTrue(mealDetails.ingredientMeasureList.size == 2)
    }

    @Test
    fun `should parse json and list of ingredient is empty`() {
        val parse = JsonParser().parse(giveJsonNoIngredients())
        val mealDetails = MealDetailsParser.parseToMealDetails(parse)
        Assert.assertTrue(mealDetails.ingredientMeasureList.isEmpty())
    }

    private fun giveJson(): String {
        return "{\n" +
                "            \"idMeal\": \"52772\",\n" +
                "            \"strMeal\": \"Teriyaki Chicken Casserole\",\n" +
                "            \"strDrinkAlternate\": null,\n" +
                "            \"strCategory\": \"Chicken\",\n" +
                "            \"strArea\": \"Japanese\",\n" +
                "            \"strInstructions\": \"Preheat oven to 350° F. Spray a 9x13-inch baking pan with non-stick spray.\\r\\nCombine soy sauce, ½ cup water, brown sugar, ginger and garlic in a small saucepan and cover. Bring to a boil over medium heat. Remove lid and cook for one minute once boiling.\\r\\nMeanwhile, stir together the corn starch and 2 tablespoons of water in a separate dish until smooth. Once sauce is boiling, add mixture to the saucepan and stir to combine. Cook until the sauce starts to thicken then remove from heat.\\r\\nPlace the chicken breasts in the prepared pan. Pour one cup of the sauce over top of chicken. Place chicken in oven and bake 35 minutes or until cooked through. Remove from oven and shred chicken in the dish using two forks.\\r\\n*Meanwhile, steam or cook the vegetables according to package directions.\\r\\nAdd the cooked vegetables and rice to the casserole dish with the chicken. Add most of the remaining sauce, reserving a bit to drizzle over the top when serving. Gently toss everything together in the casserole dish until combined. Return to oven and cook 15 minutes. Remove from oven and let stand 5 minutes before serving. Drizzle each serving with remaining sauce. Enjoy!\",\n" +
                "            \"strMealThumb\": \"https://www.themealdb.com/images/media/meals/wvpsxx1468256321.jpg\",\n" +
                "            \"strTags\": \"Meat,Casserole\",\n" +
                "            \"strYoutube\": \"https://www.youtube.com/watch?v=4aZr5hZXP_s\",\n" +
                "            \"strIngredient1\": \"soy sauce\",\n" +
                "            \"strIngredient2\": \"water\",\n" +
                "            \"strIngredient3\": \"brown sugar\",\n" +
                "            \"strIngredient4\": \"ground ginger\",\n" +
                "            \"strIngredient5\": \"minced garlic\",\n" +
                "            \"strIngredient6\": \"cornstarch\",\n" +
                "            \"strIngredient7\": \"chicken breasts\",\n" +
                "            \"strIngredient8\": \"stir-fry vegetables\",\n" +
                "            \"strIngredient9\": \"brown rice\",\n" +
                "            \"strIngredient10\": \"\",\n" +
                "            \"strIngredient11\": \"\",\n" +
                "            \"strIngredient12\": \"\",\n" +
                "            \"strIngredient13\": \"\",\n" +
                "            \"strIngredient14\": \"\",\n" +
                "            \"strIngredient15\": \"\",\n" +
                "            \"strIngredient16\": null,\n" +
                "            \"strIngredient17\": null,\n" +
                "            \"strIngredient18\": null,\n" +
                "            \"strIngredient19\": null,\n" +
                "            \"strIngredient20\": null,\n" +
                "            \"strMeasure1\": \"3/4 cup\",\n" +
                "            \"strMeasure2\": \"1/2 cup\",\n" +
                "            \"strMeasure3\": \"1/4 cup\",\n" +
                "            \"strMeasure4\": \"1/2 teaspoon\",\n" +
                "            \"strMeasure5\": \"1/2 teaspoon\",\n" +
                "            \"strMeasure6\": \"4 Tablespoons\",\n" +
                "            \"strMeasure7\": \"2\",\n" +
                "            \"strMeasure8\": \"1 (12 oz.)\",\n" +
                "            \"strMeasure9\": \"3 cups\",\n" +
                "            \"strMeasure10\": \"\",\n" +
                "            \"strMeasure11\": \"\",\n" +
                "            \"strMeasure12\": \"\",\n" +
                "            \"strMeasure13\": \"\",\n" +
                "            \"strMeasure14\": \"\",\n" +
                "            \"strMeasure15\": \"\",\n" +
                "            \"strMeasure16\": null,\n" +
                "            \"strMeasure17\": null,\n" +
                "            \"strMeasure18\": null,\n" +
                "            \"strMeasure19\": null,\n" +
                "            \"strMeasure20\": null,\n" +
                "            \"strSource\": null,\n" +
                "            \"strImageSource\": null,\n" +
                "            \"strCreativeCommonsConfirmed\": null,\n" +
                "            \"dateModified\": null\n" +
                "        }"
    }

    private fun giveJson8Ingred(): String {
        return "{\n" +
                "            \"idMeal\": \"52769\",\n" +
                "            \"strMeal\": \"Kapsalon\",\n" +
                "            \"strDrinkAlternate\": null,\n" +
                "            \"strCategory\": \"Lamb\",\n" +
                "            \"strArea\": \"Dutch\",\n" +
                "            \"strInstructions\": \"Cut the meat into strips. Heat oil in a pan and fry the strips for 6 minutes until it's ready.\\r\\nBake the fries until golden brown in a deep fryrer. When ready transfer to a backing dish. Make sure the fries are spread over the whole dish.\\r\\nCover the fries with a new layer of meat and spread evenly.\\r\\nAdd a layer of cheese over the meat. You can also use grated cheese. When done put in the oven for a few minutes until the cheese is melted.\\r\\nChop the lettuce, tomato and cucumber in small pieces and mix together. for a basic salad. As extra you can add olives jalapenos and a red union.\\r\\nDived the salad over the dish and Serve with garlicsauce and hot sauce\",\n" +
                "            \"strMealThumb\": \"https://www.themealdb.com/images/media/meals/sxysrt1468240488.jpg\",\n" +
                "            \"strTags\": \"Snack\",\n" +
                "            \"strYoutube\": \"https://www.youtube.com/watch?v=UIcuiU1kV8I\",\n" +
                "            \"strIngredient1\": \"Fries\",\n" +
                "            \"strIngredient2\": \"Doner Meat\",\n" +
                "            \"strIngredient3\": \"Garlic sauce\",\n" +
                "            \"strIngredient4\": \"Hotsauce\",\n" +
                "            \"strIngredient5\": \"Lettuce\",\n" +
                "            \"strIngredient6\": \"Tomato\",\n" +
                "            \"strIngredient7\": \"Cucumber\",\n" +
                "            \"strIngredient8\": \"Gouda cheese\",\n" +
                "            \"strIngredient9\": \"\",\n" +
                "            \"strIngredient10\": \"\",\n" +
                "            \"strIngredient11\": \"\",\n" +
                "            \"strIngredient12\": \"\",\n" +
                "            \"strIngredient13\": \"\",\n" +
                "            \"strIngredient14\": \"\",\n" +
                "            \"strIngredient15\": \"\",\n" +
                "            \"strIngredient16\": null,\n" +
                "            \"strIngredient17\": null,\n" +
                "            \"strIngredient18\": null,\n" +
                "            \"strIngredient19\": null,\n" +
                "            \"strIngredient20\": null,\n" +
                "            \"strMeasure1\": \"250 Grams\",\n" +
                "            \"strMeasure2\": \"500 Grams\",\n" +
                "            \"strMeasure3\": \"Topping\",\n" +
                "            \"strMeasure4\": \"Topping\",\n" +
                "            \"strMeasure5\": \"1 Bulb\",\n" +
                "            \"strMeasure6\": \"1\",\n" +
                "            \"strMeasure7\": \"3rd\",\n" +
                "            \"strMeasure8\": \"100 Grams\",\n" +
                "            \"strMeasure9\": \"\",\n" +
                "            \"strMeasure10\": \"\",\n" +
                "            \"strMeasure11\": \"\",\n" +
                "            \"strMeasure12\": \"\",\n" +
                "            \"strMeasure13\": \"\",\n" +
                "            \"strMeasure14\": \"\",\n" +
                "            \"strMeasure15\": \"\",\n" +
                "            \"strMeasure16\": null,\n" +
                "            \"strMeasure17\": null,\n" +
                "            \"strMeasure18\": null,\n" +
                "            \"strMeasure19\": null,\n" +
                "            \"strMeasure20\": null,\n" +
                "            \"strSource\": null,\n" +
                "            \"strImageSource\": null,\n" +
                "            \"strCreativeCommonsConfirmed\": null,\n" +
                "            \"dateModified\": null\n" +
                "        }"
    }

    private fun giveJsonUnUsual(): String {
        return " {\n" +
                "            \"idMeal\": \"52772\",\n" +
                "            \"strMeal\": \"Teriyaki Chicken Casserole\",\n" +
                "            \"strDrinkAlternate\": null,\n" +
                "            \"strCategory\": \"Chicken\",\n" +
                "            \"strArea\": \"Japanese\",\n" +
                "            \"strInstructions\": \"Preheat oven to 350° F. Spray a 9x13-inch baking pan with non-stick spray.\\r\\nCombine soy sauce, ½ cup water, brown sugar, ginger and garlic in a small saucepan and cover. Bring to a boil over medium heat. Remove lid and cook for one minute once boiling.\\r\\nMeanwhile, stir together the corn starch and 2 tablespoons of water in a separate dish until smooth. Once sauce is boiling, add mixture to the saucepan and stir to combine. Cook until the sauce starts to thicken then remove from heat.\\r\\nPlace the chicken breasts in the prepared pan. Pour one cup of the sauce over top of chicken. Place chicken in oven and bake 35 minutes or until cooked through. Remove from oven and shred chicken in the dish using two forks.\\r\\n*Meanwhile, steam or cook the vegetables according to package directions.\\r\\nAdd the cooked vegetables and rice to the casserole dish with the chicken. Add most of the remaining sauce, reserving a bit to drizzle over the top when serving. Gently toss everything together in the casserole dish until combined. Return to oven and cook 15 minutes. Remove from oven and let stand 5 minutes before serving. Drizzle each serving with remaining sauce. Enjoy!\",\n" +
                "            \"strMealThumb\": \"https://www.themealdb.com/images/media/meals/wvpsxx1468256321.jpg\",\n" +
                "            \"strTags\": \"Meat,Casserole\",\n" +
                "            \"strYoutube\": \"https://www.youtube.com/watch?v=4aZr5hZXP_s\",\n" +
                "            \"strIngredient1\": \"soy sauce\",\n" +
                "            \"strIngredient4\": \"water\",\n" +
                "            \"strIngredient10\": \"\",\n" +
                "            \"strIngredient11\": \"\",\n" +
                "            \"strIngredient12\": \"\",\n" +
                "            \"strIngredient13\": \"\",\n" +
                "            \"strIngredient14\": \"\",\n" +
                "            \"strIngredient15\": \"\",\n" +
                "            \"strIngredient16\": null,\n" +
                "            \"strIngredient17\": null,\n" +
                "            \"strIngredient18\": null,\n" +
                "            \"strIngredient19\": null,\n" +
                "            \"strIngredient20\": null,\n" +
                "            \"strMeasure1\": \"3/4 cup\",\n" +
                "            \"strMeasure2\": \"1/2 cup\",\n" +
                "            \"strMeasure3\": \"1/4 cup\",\n" +
                "            \"strMeasure4\": \"1/2 teaspoon\",\n" +
                "            \"strMeasure5\": \"1/2 teaspoon\",\n" +
                "            \"strMeasure6\": \"4 Tablespoons\",\n" +
                "            \"strMeasure7\": \"2\",\n" +
                "            \"strMeasure8\": \"1 (12 oz.)\",\n" +
                "            \"strMeasure9\": \"3 cups\",\n" +
                "            \"strMeasure10\": \"\",\n" +
                "            \"strMeasure11\": \"\",\n" +
                "            \"strMeasure12\": \"\",\n" +
                "            \"strMeasure13\": \"\",\n" +
                "            \"strMeasure14\": \"\",\n" +
                "            \"strMeasure15\": \"\",\n" +
                "            \"strMeasure16\": null,\n" +
                "            \"strMeasure17\": null,\n" +
                "            \"strMeasure18\": null,\n" +
                "            \"strMeasure19\": null,\n" +
                "            \"strMeasure20\": null,\n" +
                "            \"strSource\": null,\n" +
                "            \"strImageSource\": null,\n" +
                "            \"strCreativeCommonsConfirmed\": null,\n" +
                "            \"dateModified\": null\n" +
                "        }"
    }

    private fun giveJsonNoIngredients(): String {
        return "   {\n" +
                "            \"idMeal\": \"52772\",\n" +
                "            \"strMeal\": \"Teriyaki Chicken Casserole\",\n" +
                "            \"strDrinkAlternate\": null,\n" +
                "            \"strCategory\": \"Chicken\",\n" +
                "            \"strArea\": \"Japanese\",\n" +
                "            \"strInstructions\": \"Preheat oven to 350° F. Spray a 9x13-inch baking pan with non-stick spray.\\r\\nCombine soy sauce, ½ cup water, brown sugar, ginger and garlic in a small saucepan and cover. Bring to a boil over medium heat. Remove lid and cook for one minute once boiling.\\r\\nMeanwhile, stir together the corn starch and 2 tablespoons of water in a separate dish until smooth. Once sauce is boiling, add mixture to the saucepan and stir to combine. Cook until the sauce starts to thicken then remove from heat.\\r\\nPlace the chicken breasts in the prepared pan. Pour one cup of the sauce over top of chicken. Place chicken in oven and bake 35 minutes or until cooked through. Remove from oven and shred chicken in the dish using two forks.\\r\\n*Meanwhile, steam or cook the vegetables according to package directions.\\r\\nAdd the cooked vegetables and rice to the casserole dish with the chicken. Add most of the remaining sauce, reserving a bit to drizzle over the top when serving. Gently toss everything together in the casserole dish until combined. Return to oven and cook 15 minutes. Remove from oven and let stand 5 minutes before serving. Drizzle each serving with remaining sauce. Enjoy!\",\n" +
                "            \"strMealThumb\": \"https://www.themealdb.com/images/media/meals/wvpsxx1468256321.jpg\",\n" +
                "            \"strTags\": \"Meat,Casserole\",\n" +
                "            \"strYoutube\": \"https://www.youtube.com/watch?v=4aZr5hZXP_s\",\n" +
                "            \"strSource\": null,\n" +
                "            \"strImageSource\": null,\n" +
                "            \"strCreativeCommonsConfirmed\": null,\n" +
                "            \"dateModified\": null\n" +
                "        }"
    }
}

