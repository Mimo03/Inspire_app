package com.example.inspire_app.viewmodels;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.inspire_app.models.PostLikedData;
import com.example.inspire_app.responsemodels.GetLikedResponse;
import com.example.inspire_app.responsemodels.PostLikedResponse;
import com.example.inspire_app.responsemodels.PostResponse;
import com.example.inspire_app.restService.ApiInterface;
import com.example.inspire_app.restService.RetrofitBuilder;
import com.example.inspire_app.utils.LoginManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LikedPostViewModel extends ViewModel {
    private MutableLiveData<GetLikedResponse> createUserLiveData;
    private MutableLiveData<PostLikedResponse> createUserLiveData2;


    public LikedPostViewModel(){
        createUserLiveData = new MutableLiveData<>();
        createUserLiveData2 = new MutableLiveData<>();

    }
    public LiveData<GetLikedResponse> getCreateUserLiveData() {
        return createUserLiveData;
    }

    public LiveData<PostLikedResponse> getCreateUserLiveData2() {
        return createUserLiveData2;
    }

    public void btnnewpost(Application application){
        LoginManager loginManager = new LoginManager(application);
        ApiInterface apiInterface = RetrofitBuilder.getInstance(application).getApi();
        Call<GetLikedResponse> call = apiInterface.getliked("Bearer "+loginManager.gettoken(),loginManager.getid());
        call.enqueue(new Callback<GetLikedResponse>() {
            @Override
            public void onResponse(Call<GetLikedResponse> call, Response<GetLikedResponse> response) {
                if (response.isSuccessful() && response.body()!=null){
                    createUserLiveData.postValue(response.body());
                }
                else{
                    createUserLiveData.postValue(null);

                }
            }

            @Override
            public void onFailure(Call<GetLikedResponse> call, Throwable t) {
                Log.e(TAG,"onfailure :"+ t.getMessage());
                createUserLiveData.postValue(null);
            }
        });



    }

    public void btnpost(Application application, PostLikedData data){
        LoginManager loginManager = new LoginManager(application);
        ApiInterface apiInterface = RetrofitBuilder.getInstance(application).getApi();
        Call<PostLikedResponse> call = apiInterface.postliked("Bearer "+loginManager.gettoken(),data);
        call.enqueue(new Callback<PostLikedResponse>() {
            @Override
            public void onResponse(Call<PostLikedResponse> call, Response<PostLikedResponse> response) {
                if (response.isSuccessful() && response.body()!=null){
                    createUserLiveData2.postValue(response.body());
                }
                else{
                    createUserLiveData2.postValue(null);

                }
            }

            @Override
            public void onFailure(Call<PostLikedResponse> call, Throwable t) {
                Log.e(TAG,"onfailure :"+ t.getMessage());
                createUserLiveData2.postValue(null);
            }
        });



    }
}
