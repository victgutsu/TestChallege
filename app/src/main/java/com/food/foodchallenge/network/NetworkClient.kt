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
import java.util.regex.Matcher
import java.util.regex.Pattern


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
}