package com.swatiitsolutions.loginsingupdemo.Api;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Grocery App
 * https://github.com/quintuslabs/GroceryStore
 * Created on 18-Feb-2019.
 * Created by : Santosh Kumar Dash:- http://santoshdash.epizy.com
 */
public class LocalStorage {

    public static int cart_count = 0;

    public static final String KEY_HASH = "hash";
    public static final String RECIPE_SLIDER = "recipeSlider";
    public static final String KEY_USER = "User";
    public static final String KEY_USER_ADDRESS = "user_address";
    public static final String KEY_PREFERENCES = "preferences";
    public static final String USER_PREFERENCES = "user_preferences";
    public static final String USER_NAME = "user_name";
    public static final String USER_EMAIL = "user_email";
    public static final String SLIDER_IMAGE = "slider_image";
    public static final String ADVERTISE_IMAGE = "advertise_image";
    public static final String CATEGORY = "category";
    public static final String FAVORITE_CATEGORY = "fav_category";
    public static  Gson gson;
   // public static  List<WeightData> unitOrderList = new ArrayList<>();

    private static final String IS_USER_LOGIN = "IsUserLoggedIn";


    private static LocalStorage instance = null;
    SharedPreferences sharedPreferences;
    Editor editor;
    int PRIVATE_MODE = 0;
    Context _context;

    public LocalStorage(Context context) {
        sharedPreferences = context.getSharedPreferences("Preferences", 0);
    }


    public String getCartWeight() {
        if (sharedPreferences.contains("CartWeight"))
            return sharedPreferences.getString("CartWeight", null);
        else return null;
    }



//    public List<WeightData> getCartWeightList(String dataList) {
//        if (dataList != null) {
//            List<WeightData> weightDataList = new ArrayList<>();
//              Type type = new TypeToken<List<WeightData>>() {
//            }.getType();
//              Gson gson = new Gson();
//            weightDataList = gson.fromJson(dataList, type);
//
//            //weightDataArray = new WeightData[weightDataList.size()];
//            return weightDataList;
//        }
//        return null;
//    }




}
