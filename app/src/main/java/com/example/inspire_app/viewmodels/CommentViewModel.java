package com.example.inspire_app.viewmodels;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.inspire_app.models.CommentData;
import com.example.inspire_app.models.PostLikedData;
import com.example.inspire_app.responsemodels.CommentResponse;
import com.example.inspire_app.responsemodels.GetLikedResponse;
import com.example.inspire_app.responsemodels.PostLikedResponse;
import com.example.inspire_app.restService.ApiInterface;
import com.example.inspire_app.restService.RetrofitBuilder;
import com.example.inspire_app.utils.LoginManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentViewModel extends ViewModel {
    private MutableLiveData<CommentResponse> createUserLiveData;


    public CommentViewModel(){
        createUserLiveData = new MutableLiveData<>();

    }
    public LiveData<CommentResponse> getCreateUserLiveData() {
        return createUserLiveData;
    }


    public void btncomment(Application application, CommentData commentData){
        LoginManager loginManager = new LoginManager(application);
        ApiInterface apiInterface = RetrofitBuilder.getInstance(application).getApi();
        Call<CommentResponse> call = apiInterface.postcomment("Bearer "+loginManager.gettoken(),commentData);
        call.enqueue(new Callback<CommentResponse>() {
            @Override
            public void onResponse(Call<CommentResponse> call, Response<CommentResponse> response) {
                if (response.isSuccessful() && response.body()!=null){
                    createUserLiveData.postValue(response.body());
                }
                else{
                    createUserLiveData.postValue(null);

                }
            }

            @Override
            public void onFailure(Call<CommentResponse> call, Throwable t) {
                Log.e(TAG,"onfailure :"+ t.getMessage());
                createUserLiveData.postValue(null);
            }
        });



    }

}

