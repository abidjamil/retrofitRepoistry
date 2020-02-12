package com.example.retrofitmvvmdaggerrxjava.Network;



import android.database.Observable;

import com.example.retrofitmvvmdaggerrxjava.DataModels.EmployeeResponse;
import com.example.retrofitmvvmdaggerrxjava.DataModels.FakeApiModel;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface NetworkApiInterface {
    @GET("users/{id}")
    Call<FakeApiModel> getEmployee(@Path("id") int id);
}
