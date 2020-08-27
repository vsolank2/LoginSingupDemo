package com.swatiitsolutions.loginsingupdemo.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.swatiitsolutions.loginsingupdemo.MainActivity;
import com.swatiitsolutions.loginsingupdemo.R;

public class WelcomActivity extends AppCompatActivity {
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcom);
        sharedPreferences = getSharedPreferences("Preferences", Context.MODE_PRIVATE);

    }

    public void logout(View view) {
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putBoolean("isLogin", false);
        editor.commit();

        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
