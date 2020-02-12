package com.example.retrofitmvvmdaggerrxjava

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.retrofitmvvmdaggerrxjava.DataRepoistry.EmployeeRepoistry
import com.example.retrofitmvvmdaggerrxjava.Network.NetworkApiInterface
import com.example.retrofitmvvmdaggerrxjava.databinding.ActivityMainBinding
import com.google.gson.Gson
import io.realm.Realm
import io.realm.Realm.Transaction
import io.realm.RealmConfiguration
import com.example.retrofitmvvmdaggerrxjava.DataModels.FakeApiModel


class MainActivity : AppCompatActivity() {

    private val networkApiInterface: NetworkApiInterface? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityMainBinding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        activityMainBinding.viewModel = MainViewModel()
        val data= EmployeeRepoistry.getInstance().GetEmployeeData(applicationContext,1)
        var mydata : FakeApiModel?=null
        val nameObserver = Observer<FakeApiModel>{
            mydata = it
        }
        data.observe(this,nameObserver)
        val config = RealmConfiguration.Builder()
            .name("test.db")
            .schemaVersion(1)
            .deleteRealmIfMigrationNeeded()
            .build()

        val realm = Realm.getDefaultInstance();
        realm.executeTransactionAsync(object : Transaction{
            override fun execute(realm: Realm?) {

            }

        })


        }

}
