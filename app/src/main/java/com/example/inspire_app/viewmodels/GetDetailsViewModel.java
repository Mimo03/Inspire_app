package com.example.inspire_app.viewmodels;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.inspire_app.responsemodels.GetDetailsResponse;
import com.example.inspire_app.responsemodels.PostResponse;
import com.example.inspire_app.restService.ApiInterface;
import com.example.inspire_app.restService.RetrofitBuilder;
import com.example.inspire_app.utils.LoginManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetDetailsViewModel extends ViewModel {
    private MutableLiveData<GetDetailsResponse> createUserLiveData;

    public GetDetailsViewModel(){
        createUserLiveData = new MutableLiveData<>();

    }
    public LiveData<GetDetailsResponse> getCreateUserLiveData() {
        return createUserLiveData;
    }

    public void btndetails(Application application,String _id){
        LoginManager loginManager = new LoginManager(application);
        ApiInterface apiInterface = RetrofitBuilder.getInstance(application).getApi();
        Call<GetDetailsResponse> call = apiInterface.getdetails("Bearer "+loginManager.gettoken(),_id);
        call.enqueue(new Callback<GetDetailsResponse>() {
            @Override
            public void onResponse(Call<GetDetailsResponse> call, Response<GetDetailsResponse> response) {
                if (response.isSuccessful() && response.body()!=null){
                    createUserLiveData.postValue(response.body());
                }
                else{
                    createUserLiveData.postValue(null);

                }
            }

            @Override
            public void onFailure(Call<GetDetailsResponse> call, Throwable t) {
                Log.e(TAG,"onfailure :"+ t.getMessage());
                createUserLiveData.postValue(null);
            }
        });



    }
}
