package com.example.inspire_app.restService;

import com.example.inspire_app.models.PostLikedData;
import com.example.inspire_app.responsemodels.GetLikedResponse;
import com.example.inspire_app.responsemodels.LoginResponse;
import com.example.inspire_app.responsemodels.PostLikedResponse;
import com.example.inspire_app.responsemodels.PostResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("/get/{moodleID}")
    Call<LoginResponse> getlogin(@Path("moodleID")String moodleID);

    @GET("/postget")
    Call<PostResponse> getnewpost(@Header("Authorization") String Token);

    @GET("/getliked")
    Call<GetLikedResponse> getliked(@Header("Authorization") String Token);

    @POST("/likedpost")
    Call<PostLikedResponse> postliked(@Header("Authorization") String Token, @Body PostLikedData postLikedData);
}
