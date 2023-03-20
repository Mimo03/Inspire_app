package com.example.inspire_app.viewmodels;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.inspire_app.responsemodels.LoginResponse;
import com.example.inspire_app.restService.ApiInterface;
import com.example.inspire_app.restService.RetrofitBuilder;
import com.example.inspire_app.utils.LoginManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends ViewModel {
    private MutableLiveData<LoginResponse> createUserLiveData;

    public LoginViewModel(){
        createUserLiveData = new MutableLiveData<>();

    }
    public LiveData<LoginResponse> getCreateUserLiveData() {
        return createUserLiveData;
    }

    public void btnvalidate(Application application,String moodleid,String token){
        LoginManager loginManager = new LoginManager(application);
        ApiInterface apiInterface = RetrofitBuilder.getInstance(application).getApi();
        Call<LoginResponse> call = apiInterface.getlogin(moodleid);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful() && response.body()!=null){
                    createUserLiveData.postValue(response.body());
                }
                else{
                    createUserLiveData.postValue(null);

                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.e(TAG,"onfailure :"+ t.getMessage());
                createUserLiveData.postValue(null);
            }
        });



    }
}
