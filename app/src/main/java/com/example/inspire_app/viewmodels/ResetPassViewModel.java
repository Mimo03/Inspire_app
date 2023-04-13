package com.example.inspire_app.viewmodels;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.inspire_app.models.ChangePassModel;
import com.example.inspire_app.models.ResetPassModel;
import com.example.inspire_app.responsemodels.ChangePasswordResponse;
import com.example.inspire_app.responsemodels.ResetPassResponse;
import com.example.inspire_app.restService.ApiInterface;
import com.example.inspire_app.restService.RetrofitBuilder;
import com.example.inspire_app.utils.LoginManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResetPassViewModel extends ViewModel {
    private MutableLiveData<ResetPassResponse> createUserLiveData;


    public ResetPassViewModel(){
        createUserLiveData = new MutableLiveData<>();

    }
    public LiveData<ResetPassResponse> getCreateUserLiveData() {
        return createUserLiveData;
    }


    public void btnChangePass(Application application, ResetPassModel resetPassModel){
        LoginManager loginManager = new LoginManager(application);
        ApiInterface apiInterface = RetrofitBuilder.getInstance(application).getApi();
        Call<ResetPassResponse> call = apiInterface.resetpass("Bearer "+loginManager.gettoken(),resetPassModel);
        call.enqueue(new Callback<ResetPassResponse>() {
            @Override
            public void onResponse(Call<ResetPassResponse> call, Response<ResetPassResponse> response) {
                if (response.isSuccessful() && response.body()!=null){
                    createUserLiveData.postValue(response.body());
                }
                else{
                    createUserLiveData.postValue(null);

                }
            }

            @Override
            public void onFailure(Call<ResetPassResponse> call, Throwable t) {
                Log.e(TAG,"onfailure :"+ t.getMessage());
                createUserLiveData.postValue(null);
            }
        });



    }

}
