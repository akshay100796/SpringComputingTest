package com.example.springtak.utils.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

enum class STATUS { ERROR, DONE, LOADING}


private const val BASE_URL = "https://reqres.in/api/"


val retrofit: Retrofit = Retrofit.Builder()
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface RetrofitAPI {


    @POST("login")
    fun doLoginAsync(@Body request: LoginReq): Deferred<LoginRes>
}

object RetrofitRef{
    val retrofitRef = retrofit.create(RetrofitAPI::class.java)
}