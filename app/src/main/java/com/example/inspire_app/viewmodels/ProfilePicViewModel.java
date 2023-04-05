package com.example.inspire_app.viewmodels;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.inspire_app.models.CommentData;
import com.example.inspire_app.models.ProfilePicData;
import com.example.inspire_app.responsemodels.CommentResponse;
import com.example.inspire_app.responsemodels.ProfilePicResponse;
import com.example.inspire_app.restService.ApiInterface;
import com.example.inspire_app.restService.RetrofitBuilder;
import com.example.inspire_app.utils.LoginManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfilePicViewModel extends ViewModel {
    private MutableLiveData<ProfilePicResponse> createUserLiveData;


    public ProfilePicViewModel(){
        createUserLiveData = new MutableLiveData<>();

    }
    public LiveData<ProfilePicResponse> getCreateUserLiveData() {
        return createUserLiveData;
    }


    public void btnpostprofile(Application application, ProfilePicData profilePicData){
        LoginManager loginManager = new LoginManager(application);
        ApiInterface apiInterface = RetrofitBuilder.getInstance(application).getApi();
        Call<ProfilePicResponse> call = apiInterface.postprofile("Bearer "+loginManager.gettoken(),profilePicData);
        call.enqueue(new Callback<ProfilePicResponse>() {
            @Override
            public void onResponse(Call<ProfilePicResponse> call, Response<ProfilePicResponse> response) {
                if (response.isSuccessful() && response.body()!=null){
                    createUserLiveData.postValue(response.body());
                }
                else{
                    createUserLiveData.postValue(null);

                }
            }

            @Override
            public void onFailure(Call<ProfilePicResponse> call, Throwable t) {
                Log.e(TAG,"onfailure :"+ t.getMessage());
                createUserLiveData.postValue(null);
            }
        });



    }

}
