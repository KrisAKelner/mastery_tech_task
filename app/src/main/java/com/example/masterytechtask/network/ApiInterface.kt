package com.example.masterytechtask.network

import com.example.masterytechtask.model.Driver
import com.example.masterytechtask.model.Login
import com.example.masterytechtask.model.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiInterface {

    @POST("login")
    fun login(  @Body login: Login): Call<User>

    @GET("drivers")
    fun getDriverList(): Call<List<Driver>>

}