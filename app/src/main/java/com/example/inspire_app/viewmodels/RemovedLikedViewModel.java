package com.example.inspire_app.viewmodels;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.inspire_app.responsemodels.DeleteLikedResponse;
import com.example.inspire_app.responsemodels.PostLikedResponse;
import com.example.inspire_app.responsemodels.PostResponse;
import com.example.inspire_app.restService.ApiInterface;
import com.example.inspire_app.restService.RetrofitBuilder;
import com.example.inspire_app.utils.LoginManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RemovedLikedViewModel extends ViewModel {
    private MutableLiveData<DeleteLikedResponse> createUserLiveData;

    public RemovedLikedViewModel(){
        createUserLiveData = new MutableLiveData<>();

    }
    public LiveData<DeleteLikedResponse> getCreateUserLiveData() {
        return createUserLiveData;
    }

    public void btnremove(Application application,String _id){
        LoginManager loginManager = new LoginManager(application);
        ApiInterface apiInterface = RetrofitBuilder.getInstance(application).getApi();
        Call<DeleteLikedResponse> call = apiInterface.deleteliked("Bearer "+loginManager.gettoken(),_id);
        call.enqueue(new Callback<DeleteLikedResponse>() {
            @Override
            public void onResponse(Call<DeleteLikedResponse> call, Response<DeleteLikedResponse> response) {
                if (response.isSuccessful() && response.body()!=null){
                    createUserLiveData.postValue(response.body());
                }
                else{
                    createUserLiveData.postValue(null);

                }
            }

            @Override
            public void onFailure(Call<DeleteLikedResponse> call, Throwable t) {
                Log.e(TAG,"onfailure :"+ t.getMessage());
                createUserLiveData.postValue(null);
            }
        });



    }
}
