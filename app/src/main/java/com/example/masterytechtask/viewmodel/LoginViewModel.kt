package com.example.masterytechtask.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.masterytechtask.model.Login
import com.example.masterytechtask.model.LoginError
import com.example.masterytechtask.model.User
import com.example.masterytechtask.network.RetrofitClient
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {

    private val mutableUserData = MutableLiveData<User>()
    private val mutableUserErrorData = MutableLiveData<LoginError>()

    val userData: LiveData<User> = mutableUserData
    val userErrorData: LiveData<LoginError> = mutableUserErrorData

    fun login(username: String, password: String) {
        val call = RetrofitClient.apiInterface.login(Login(username, password))

        call.enqueue(object: Callback<User> {
            override fun onFailure(call: Call<User>, t: Throwable) {
                mutableUserErrorData.postValue(LoginError("Network error"))
            }

            override fun onResponse(
                call: Call<User>,
                response: Response<User>
            ) {
                if (response.code() == 404 or 500 && response.errorBody() != null) {
                    val errorObject: JSONObject = JSONObject(response.errorBody()!!.string())
                    mutableUserErrorData.postValue(LoginError(errorObject.getString("error")))
                } else if (response.body() != null) {
                    mutableUserData.postValue(response.body())
                } else {
                    mutableUserErrorData.postValue(LoginError("Network error"))
                }
            }
        })
    }
}