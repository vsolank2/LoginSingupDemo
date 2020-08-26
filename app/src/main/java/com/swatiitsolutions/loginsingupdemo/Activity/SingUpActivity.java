package com.swatiitsolutions.loginsingupdemo.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.swatiitsolutions.loginsingupdemo.Api.ClsGlobal;
import com.swatiitsolutions.loginsingupdemo.Api.CustomerViewModel;
import com.swatiitsolutions.loginsingupdemo.MainActivity;
import com.swatiitsolutions.loginsingupdemo.R;
import com.swatiitsolutions.loginsingupdemo.SingIn.ClsSingInResponse;
import com.swatiitsolutions.loginsingupdemo.SingIn.ClsSingin.ClsCustomerResponse;

import java.util.Locale;

public class SingUpActivity extends AppCompatActivity {
    Button btnSingUp;
    CustomerViewModel customerViewModel;
    EditText etName,etPhone,etEmail,etAddress,etPassword;
    private SharedPreferences sharedPreferences;
    CheckBox cbEng,cbHindi,cbGuj;
    // String currentLanguage = "en", currentLang = "";
    Locale myLocale;
    // private LinearLayout llProgress;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);

        try {
            progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Wait");
            progressDialog.setMessage("Loading..");
            etPhone=findViewById(R.id.etPhone);
            etEmail=findViewById(R.id.etEmail);
            etName=findViewById(R.id.etName);
            etAddress=findViewById(R.id.etAddress);
            etPassword=findViewById(R.id.etPassword);
            btnSingUp=findViewById(R.id.btnSingUp);
            cbGuj=findViewById(R.id.cbGuj);
            cbHindi=findViewById(R.id.cbHindi);
            cbEng=findViewById(R.id.cbEng);
            if (getSupportActionBar() != null) {
                getSupportActionBar().hide();
            }

            sharedPreferences = getSharedPreferences("Preferences", Context.MODE_PRIVATE);

            customerViewModel = ViewModelProviders.of(this)
                    .get(CustomerViewModel.class);

            //  setLocale("hi");
            //  setLocale("hi");
            btnSingUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getCustomerInfo();

                    postCustomer();
                }
            });
            cbCheck();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void cbCheck(){
        try {
            cbEng.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if(cbEng.isChecked()){
                        cbHindi.setChecked(false);
                        cbGuj.setChecked(false);
                        setLocale("en");

                    }
                }
            });

            cbHindi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if(cbHindi.isChecked()){
                        cbEng.setChecked(false);
                        cbGuj.setChecked(false);
                        setLocale("hi");
                    }
                }
            });

            cbGuj.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if(cbGuj.isChecked()){
                        cbHindi.setChecked(false);
                        cbEng.setChecked(false);
                        setLocale("gu");
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        //  getLocale();
    }
//    public void storeCustomer(ClsCustomerResponse clsCustomerResponse, int id){
//        try {
//            SharedPreferences.Editor editor = sharedPreferences.edit();
//            editor.putString("cName",clsCustomerResponse.getCustomerName());
//            editor.putString("cMobile",clsCustomerResponse.getMobile());
//            editor.putString("cEmail",clsCustomerResponse.getEmail());
//            editor.putString("cAddress",clsCustomerResponse.getAddress());
//            editor.putString("cPassword",clsCustomerResponse.getPassword());
//            editor.putString("cId", String.valueOf(id));
//            editor.commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    public void skipRegistration(View view) {
    }

    public void help(View view) {
    }

    public void getCustomerInfo(){

    }

    public void setLocale(String lang) {
        try {
            myLocale = new Locale(lang);
            Resources res = getResources();
            DisplayMetrics dm = res.getDisplayMetrics();
            Configuration conf = res.getConfiguration();
            conf.locale = myLocale;
            res.updateConfiguration(conf, dm);
            Intent refresh = new Intent(SingUpActivity.this, SingUpActivity.class);

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("MyLang",lang);
            editor.commit();
            // refresh.putExtra("MyLang", lang);
            startActivity(refresh);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }






    public void postCustomer(){
        try {
            if(!checkValidations()) {

                ClsCustomerResponse clsCustomerResponse = new ClsCustomerResponse();

                clsCustomerResponse.setFlag("1");
                clsCustomerResponse.setId("0");
                clsCustomerResponse.setCustomerName("" + etName.getText().toString());
                clsCustomerResponse.setMobile("+91" + etPhone.getText().toString());
                clsCustomerResponse.setAddress("" + etAddress.getText().toString());
                clsCustomerResponse.setEmail("" + etEmail.getText().toString());
                clsCustomerResponse.setPassword("" + etPassword.getText().toString());

                if (ClsGlobal.CheckInternetConnection(this)) {

                    progressDialog.show();
                    customerViewModel.postCustomerInfo("1", "" + etName.getText().toString(), "+91" + etPhone.getText().toString(), "" + etAddress.getText().toString(), "" + etAddress.getText().toString(), "" + etPassword.getText().toString(), "1").observe(this, new Observer<ClsSingInResponse>() {
                        @Override
                        public void onChanged(ClsSingInResponse clsSingInResponse) {

                            if (clsSingInResponse != null) {

                                // Toast.makeText(this, "Status is " + clsSingInResponse.getStatus() + " Massage is " + clsSingInResponse.getMessage(), Toast.LENGTH_SHORT).show();
                                if (clsSingInResponse.getStatus()) {
                                   // SingUpActivity.this.storeCustomer(clsCustomerResponse, clsSingInResponse.getMessage().getId());
                                    SingUpActivity.this.startActivity(new Intent(SingUpActivity.this, MainActivity.class));
                                    Toast.makeText(SingUpActivity.this, R.string.mobilePassForLogin, Toast.LENGTH_LONG).show();

                                    progressDialog.dismiss();
                                } else {
                                    progressDialog.dismiss();

                                }

                            } else {
                                Toast.makeText(SingUpActivity.this, R.string.somethingWrong, Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                            }
                        }
                    });

                } else {
                    Toast.makeText(this, R.string.noInternet, Toast.LENGTH_SHORT).show();
                }
            }
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }
    }

    private boolean checkValidations() {
        boolean invalid = false;
        if (etPhone.getText().toString() == null || etPhone.getText().toString().equals("")) {
            etPhone.setError(getString(R.string.required));
            invalid = true;
        } else if (etPhone.getText().toString().length() != 10) {
            etPhone.setError(getString(R.string.tenDigitMobile));
            Toast.makeText(this, R.string.tenDigitMobile, Toast.LENGTH_SHORT).show();
            invalid = true;

        }

        if (!etEmail.getText().toString().equals("")) {
            if (!ClsGlobal.isValidEmail(etEmail.getText().toString())) {
                etEmail.setError(getString(R.string.enterEmail));
                Toast.makeText(this, R.string.enterEmail, Toast.LENGTH_SHORT).show();
                invalid = true;
            }

        }

        if (etPassword.getText().toString() == null || etPassword.getText().toString().equals("")) {
            // etPassword.setError("");
            Toast.makeText(this, R.string.passRequired, Toast.LENGTH_SHORT).show();

            invalid = true;

        } else if (etPassword.getText().toString().length() < 6) {
            Toast.makeText(this, R.string.sixDigitPassReqed, Toast.LENGTH_SHORT).show();
            invalid = true;

        }


        if (etName.getText().toString() == null || etName.getText().toString().equals("")) {
            etName.setError(getString(R.string.required));
            invalid = true;

        }
        if (etAddress.getText().toString() == null ||etAddress.getText().toString().equals("")) {
            etAddress.setError(getString(R.string.required));
            invalid = true;
        }
        return invalid;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        try {
            Intent intent = new Intent(SingUpActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
