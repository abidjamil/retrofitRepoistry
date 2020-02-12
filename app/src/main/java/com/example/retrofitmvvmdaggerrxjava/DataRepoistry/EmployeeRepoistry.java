package com.example.retrofitmvvmdaggerrxjava.DataRepoistry;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.retrofitmvvmdaggerrxjava.DataModels.EmployeeResponse;
import com.example.retrofitmvvmdaggerrxjava.DataModels.FakeApiModel;
import com.example.retrofitmvvmdaggerrxjava.Network.NetworkApiInterface;
import com.example.retrofitmvvmdaggerrxjava.Utilis.RetrofitClientInstance;
import com.google.gson.Gson;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableConverter;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class EmployeeRepoistry {

    @Inject
    public NetworkApiInterface networkApiInterface;
    private static EmployeeRepoistry repository;

    // Intialization
    @Inject
    EmployeeRepoistry() {
        Retrofit retrofit = RetrofitClientInstance.getRetrofitInstance();
        networkApiInterface = retrofit.create(NetworkApiInterface.class);
    }

    // Get Instance
    public synchronized static EmployeeRepoistry getInstance() {
        if (repository == null) {
            if (repository == null) {
                repository = new EmployeeRepoistry();
            }
        }
        return repository;
    }
    public LiveData<FakeApiModel> GetEmployeeData(Context context, int employeeId) {
        final MutableLiveData<FakeApiModel> data = new MutableLiveData<>();
        networkApiInterface.getEmployee(employeeId).enqueue(new Callback<FakeApiModel>() {
            @Override
            public void onResponse(Call<FakeApiModel> call, Response<FakeApiModel> response) {
                if (response != null) {
                    if (response.isSuccessful()) {
                        data.setValue(response.body());
                        Log.i("response", response.toString());
                    } else {
                        data.setValue(null);
                    }
                }
            }
            @Override
            public void onFailure(Call<FakeApiModel> call, Throwable t) {
                data.setValue(null);
            }
        });


        return data;
    }
}
