package com.example.inspire_app.restService;

import com.example.inspire_app.responsemodels.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("{moodleID}")
    Call<LoginResponse> getlogin(@Path("moodleID")String moodleID);
}
