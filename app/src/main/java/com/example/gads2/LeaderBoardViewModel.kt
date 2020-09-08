package com.example.gads2

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gads2.CommonNetwork.gadsService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LeaderBoardViewModel : ViewModel() {

    fun loadLearnersLeaders(): LiveData<List<LearnerResponseItem>?> {
        val mutableLiveData = MutableLiveData<List<LearnerResponseItem>?>()
        viewModelScope.launch(Dispatchers.IO) {
            val topSkillsIQ = gadsService.topLearners()
            Log.d("XXXX", "TOP_L: $topSkillsIQ")
            mutableLiveData.postValue(topSkillsIQ)
        }
        return mutableLiveData
    }

    fun loadSkillIQLeaders(): LiveData<List<SkillIQResponseItem>?> {
        val mutableLiveData = MutableLiveData<List<SkillIQResponseItem>?>()
        viewModelScope.launch(Dispatchers.IO) {
            val topSkillIQ = gadsService.topSkillIQ()
            Log.d("XXXX", "TOP_S: $topSkillIQ")
            mutableLiveData.postValue(topSkillIQ)
        }
        return mutableLiveData
    }
}