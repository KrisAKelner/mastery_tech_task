package com.example.masterytechtask.network

import com.example.masterytechtask.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    var url = "http://private-f699e3-masteryinterview.apiary-mock.com/"

    val retrofitClient: Retrofit.Builder by lazy {

        val okhttpClient = OkHttpClient.Builder()

        okhttpClient.addInterceptor(Interceptor { chain ->
            val original: Request = chain.request()

            val request: Request = original.newBuilder()
                .header("Content-Type", "application/json")
                .header("clientId", BuildConfig.clientId)
                .method(original.method, original.body)
                .build()

            chain.proceed(request)
        })


        Retrofit.Builder()
            .baseUrl(url)
            .client(okhttpClient.build())
            .addConverterFactory(GsonConverterFactory.create())
    }

    val apiInterface: ApiInterface by lazy {
        retrofitClient
            .build()
            .create(ApiInterface::class.java)
    }



}