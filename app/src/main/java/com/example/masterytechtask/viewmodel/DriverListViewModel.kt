package com.example.masterytechtask.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.masterytechtask.model.Driver
import com.example.masterytechtask.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DriverListViewModel : ViewModel() {

    private val mutableDriverList = MutableLiveData<List<Driver>>()
    private val mutableErrorMessage = MutableLiveData<String>()

    val driverList: LiveData<List<Driver>> = mutableDriverList
    val errorMessage: LiveData<String> = mutableErrorMessage

    fun loadDriverList() {
        val call = RetrofitClient.apiInterface.getDriverList()

        call.enqueue(object: Callback<List<Driver>> {
            override fun onFailure(call: Call<List<Driver>>, t: Throwable) {
                mutableErrorMessage.postValue("Network error")
            }

            override fun onResponse(
                call: Call<List<Driver>>,
                response: Response<List<Driver>>
            ) {
               if (response.body() != null) {
                   mutableDriverList.postValue(response.body())
               }
            }
        })
    }


}