package com.food.foodchallenge.network

import com.food.foodchallenge.network.response.DetailsMealsResponse
import com.food.foodchallenge.network.response.SearchMealsResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("1/search.php")
    fun searchMeals(@Query("s") query: String): Single<SearchMealsResponse>

    @GET("1/lookup.php")
    fun detailsMeal(@Query("i") index: Int): Single<DetailsMealsResponse>
}