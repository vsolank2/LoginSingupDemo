package com.swatiitsolutions.loginsingupdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.swatiitsolutions.loginsingupdemo.Activity.SingUpActivity;
import com.swatiitsolutions.loginsingupdemo.Activity.WelcomActivity;
import com.swatiitsolutions.loginsingupdemo.Api.ClsGlobal;
import com.swatiitsolutions.loginsingupdemo.Api.CustomerViewModel;
import com.swatiitsolutions.loginsingupdemo.GetCustomer.Customer;
import com.swatiitsolutions.loginsingupdemo.Login.ClsLoginResponse;
import com.swatiitsolutions.loginsingupdemo.Login.Message;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<Customer> customers = new ArrayList<>();
    CustomerViewModel customerViewModel;
    EditText etMobile,etPassword;
    private SharedPreferences sharedPreferences;
    private CheckBox chkRemember;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            sharedPreferences = getSharedPreferences("Preferences", MODE_PRIVATE);

            progressDialog = new ProgressDialog(this);
            progressDialog.setTitle(getString(R.string.wait));
            progressDialog.setMessage(getString(R.string.loading));
            if (getSupportActionBar() != null) {
                getSupportActionBar().hide();
            }
            etMobile = findViewById(R.id.etMobile);
            etPassword = findViewById(R.id.etPassword);
            chkRemember = findViewById(R.id.chkRemember);


            if (sharedPreferences.getBoolean("isLoginCheck", false)) {
                etMobile.setText(sharedPreferences.getString("loginMobile",""));
                etPassword.setText(sharedPreferences.getString("loginPass",""));
                chkRemember.setChecked(true);
            } else {
                etMobile.setText("");
                etPassword.setText("");
                chkRemember.setChecked(false);
            }
            //isRemember();
            customerViewModel = ViewModelProviders.of(this)
                    .get(CustomerViewModel.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void goToSingUp(View view) {
        startActivity(new Intent(MainActivity.this, SingUpActivity.class));
    }

    public void goToLogin(View view) {
        try {
            if(ClsGlobal.CheckInternetConnection(this) ){
                if(etMobile.getText().toString().length() != 10){
                    Toast.makeText(this, R.string.tenDigitMobile, Toast.LENGTH_SHORT).show();
                }else if(etPassword.getText().toString().length() <6){
                    Toast.makeText(this, R.string.sixDigitsPass, Toast.LENGTH_SHORT).show();
                }else {
                    login("+91".concat(etMobile.getText().toString()), etPassword.getText().toString());

                }
            }else {
                Toast.makeText(this, R.string.noInternet, Toast.LENGTH_SHORT).show();
            }
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }
    }

    private void login(String mobile,String password){

        try {
            if(ClsGlobal.CheckInternetConnection(this) ){
                progressDialog.show();

                String fcmToken = sharedPreferences.getString("fcmToken","");
                customerViewModel.login(mobile,password,fcmToken).observe(this, new Observer<ClsLoginResponse>() {
                    @Override
                    public void onChanged(ClsLoginResponse clsLoginResponse) {

                        if (clsLoginResponse != null) {
                            if (clsLoginResponse.getStatus()) {
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("UserType", clsLoginResponse.getMessage().getUserType());
                                editor.commit();
                                // if(clsLoginResponse.getMessage().getUserType().equals("1")){ //1 for customer
                                MainActivity.this.storeCustomer(clsLoginResponse.getMessage());
                                MainActivity.this.startActivity(new Intent(MainActivity.this, WelcomActivity.class));
                                MainActivity.this.finish();
                                Toast.makeText(MainActivity.this, R.string.login, Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();

                          /*  }else { // 2 for admin
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                Toast.makeText(this, R.string.login, Toast.LENGTH_SHORT).show();
                                finish();
                                progressDialog.dismiss();

                            }*/
                            } else {
                                progressDialog.dismiss();

                            }
                        } else {
                            Toast.makeText(MainActivity.this, R.string.checkMobilePass, Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();

                        }
                    }
                });

            }else {
                Toast.makeText(this, R.string.noInternet, Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }
    }
    public void storeCustomer(Message loginData){
        try {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("cName",loginData.getCustomerName());
            editor.putString("cMobile",loginData.getMobile());
            editor.putString("cEmail",loginData.getEmail());
            editor.putString("cAddress",loginData.getAddress());
            editor.putString("cPassword",loginData.getPassword());
            editor.putString("cId",loginData.getId());
            editor.putBoolean("isLogin",true);
            editor.commit();
            isRemember();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void isRemember() {
        try {
            SharedPreferences.Editor editor = sharedPreferences.edit();

            if (chkRemember.isChecked()) {
                editor.putBoolean("isLoginCheck", true);
                String mobile = etMobile.getText().toString();
                editor.putString("loginMobile", mobile);
                String pass = etPassword.getText().toString();
                editor.putString("loginPass", pass);
                editor.commit();

            } else {
                editor.putBoolean("isLoginCheck", false);
                editor.putString("id", "");
                editor.putString("pass", "");
                editor.commit();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
