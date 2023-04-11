package com.example.inspire_app.viewmodels;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.inspire_app.models.ChangePassModel;
import com.example.inspire_app.models.CommentData;
import com.example.inspire_app.responsemodels.ChangePasswordResponse;
import com.example.inspire_app.responsemodels.CommentResponse;
import com.example.inspire_app.restService.ApiInterface;
import com.example.inspire_app.restService.RetrofitBuilder;
import com.example.inspire_app.utils.LoginManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePasswordViewModel extends ViewModel {
    private MutableLiveData<ChangePasswordResponse> createUserLiveData;


    public ChangePasswordViewModel(){
        createUserLiveData = new MutableLiveData<>();

    }
    public LiveData<ChangePasswordResponse> getCreateUserLiveData() {
        return createUserLiveData;
    }


    public void btnChangePass(Application application, ChangePassModel changePassModel){
        LoginManager loginManager = new LoginManager(application);
        ApiInterface apiInterface = RetrofitBuilder.getInstance(application).getApi();
        Call<ChangePasswordResponse> call = apiInterface.changePass("Bearer "+loginManager.gettoken(),changePassModel);
        call.enqueue(new Callback<ChangePasswordResponse>() {
            @Override
            public void onResponse(Call<ChangePasswordResponse> call, Response<ChangePasswordResponse> response) {
                if (response.isSuccessful() && response.body()!=null){
                    createUserLiveData.postValue(response.body());
                }
                else{
                    createUserLiveData.postValue(null);

                }
            }

            @Override
            public void onFailure(Call<ChangePasswordResponse> call, Throwable t) {
                Log.e(TAG,"onfailure :"+ t.getMessage());
                createUserLiveData.postValue(null);
            }
        });



    }

}
