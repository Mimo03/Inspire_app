package com.example.inspire_app.viewmodels;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.inspire_app.responsemodels.GetDetailsResponse;
import com.example.inspire_app.responsemodels.MostLikedResponse;
import com.example.inspire_app.restService.ApiInterface;
import com.example.inspire_app.restService.RetrofitBuilder;
import com.example.inspire_app.utils.LoginManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MostLikedViewModel extends ViewModel {
    private MutableLiveData<MostLikedResponse> createUserLiveData;

    public MostLikedViewModel(){
        createUserLiveData = new MutableLiveData<>();

    }
    public LiveData<MostLikedResponse> getCreateUserLiveData() {
        return createUserLiveData;
    }

    public void btnmostliked(Application application){
        LoginManager loginManager = new LoginManager(application);
        ApiInterface apiInterface = RetrofitBuilder.getInstance(application).getApi();
        Call<MostLikedResponse> call = apiInterface.getmostliked("Bearer "+loginManager.gettoken());
        call.enqueue(new Callback<MostLikedResponse>() {
            @Override
            public void onResponse(Call<MostLikedResponse> call, Response<MostLikedResponse> response) {
                if (response.isSuccessful() && response.body()!=null){
                    createUserLiveData.postValue(response.body());
                }
                else{
                    createUserLiveData.postValue(null);

                }
            }

            @Override
            public void onFailure(Call<MostLikedResponse> call, Throwable t) {
                Log.e(TAG,"onfailure :"+ t.getMessage());
                createUserLiveData.postValue(null);
            }
        });



    }
}
