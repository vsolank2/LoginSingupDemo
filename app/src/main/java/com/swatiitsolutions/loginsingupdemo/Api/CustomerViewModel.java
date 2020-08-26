package com.swatiitsolutions.loginsingupdemo.Api;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.swatiitsolutions.loginsingupdemo.Login.ClsLoginResponse;
import com.swatiitsolutions.loginsingupdemo.SingIn.ClsSingInResponse;


public class CustomerViewModel extends AndroidViewModel {
    private Repository mRepository;

    public CustomerViewModel(@NonNull Application application) {
        super(application);
        this.mRepository=new Repository(application);
    }
    public LiveData<ClsSingInResponse> postCustomerInfo(String flag, String customer_name, String mobile, String email, String address, String password, String user_type){
        return mRepository.postCustomer(flag,  customer_name,  mobile,  email,  address,  password, user_type);
    }



    public LiveData<ClsLoginResponse> login(String mobile, String password, String fcmToken){
        return mRepository.login(mobile, password,fcmToken);
    }

    /* public LiveData<ClsCustomerResponse> updateCustomerInfo(String flag, int id, String customer_name, String mobile, String email, String address, String password){
        return mRepository.updateCustomer(flag,id,  customer_name,  mobile,  email,  address,  password);
    }*/

   /* public LiveData<ClsCustomerGetResponse> getCustomer(String flag){
        return mRepository.getCustomer(flag);
    }

    public LiveData<ClsCommonResponse> changePassword(String flag, String password, String id){
        return mRepository.changePassword(flag,password,id);
    }*/
}
