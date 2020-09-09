package com.example.gads2

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

import okhttp3.ResponseBody
import retrofit2.Response

class SubmissionViewModel: ViewModel() {
    fun submitForm(email:String, name:String, lastName:String, projectUrl:String): LiveData<Response<String>>{
        val mutableLiveData = MutableLiveData<Response<String>>()
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = CommonNetwork.googleFormService.submitProject(email,name,lastName,projectUrl)
                Log.d("XXXXXX","FORM_SUBMIT: $response")
                mutableLiveData.postValue(response)
            }
            catch (ex: Exception){
                ex.printStackTrace()
                mutableLiveData.postValue(null)
            }

        }
        return mutableLiveData
    }
}