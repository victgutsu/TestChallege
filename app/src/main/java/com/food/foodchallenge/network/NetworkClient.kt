package com.food.foodchallenge.network

import com.food.foodchallenge.BuildConfig
import com.food.foodchallenge.network.response.model.DetailsMealServer
import com.food.foodchallenge.network.response.model.SearchMealServer
import com.google.gson.GsonBuilder
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import io.reactivex.rxjava3.core.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory


open class NetworkClient {
    private val retrofit: Retrofit
    private val apiService: ApiService

    init {
        val httpclient = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            httpclient.addInterceptor(logging)
        }
        val client = httpclient.build()
        retrofit = Retrofit.Builder()
            .baseUrl("https://themealdb.com/api/json/v1/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(client)
            .build()
        apiService = retrofit.create(ApiService::class.java)
    }

    fun searchMeals(query: String): Single<List<SearchMealServer>> {
        return apiService.searchMeals(query)
            .map { it.meals ?: throw IllegalStateException("Can't find meals for <<$query>>") }
    }

    fun detailsMeal(id: Int): Single<DetailsMealServer> {
        return apiService.detailsMeal(id)
            .map {
                // Fixme current implementation can be redone with GsonAdapter (but that moment should be discussed)
                val meals = it.meals ?: throw IllegalStateException("Can't find detail meal for <<$id>>")

                //seems we need only one item (details should be one i suppose)
                MealDetailsParser.parseToMealDetails(meals.first())
            }
    }

}