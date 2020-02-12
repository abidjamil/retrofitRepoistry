package com.example.retrofitmvvmdaggerrxjava

import android.content.Context
import androidx.databinding.BaseObservable
import androidx.lifecycle.LiveData
import com.example.retrofitmvvmdaggerrxjava.DataModels.FakeApiModel
import com.example.retrofitmvvmdaggerrxjava.DataRepoistry.EmployeeRepoistry


class MainViewModel() : BaseObservable() {
 private var ApiResponse: LiveData<FakeApiModel>? = null

 fun loadData(context: Context) {
  ApiResponse = EmployeeRepoistry.getInstance().GetEmployeeData(context,3)

 }
}