package com.swatiitsolutions.loginsingupdemo.Api;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClsGlobal {
    public static boolean isScrollingProduct = true;
    public static boolean isTotalOrderChangeEditable = false;
    public static final String SPLoginDetails = "Preferences";
//    public static List<ItemInfo> itemList = new ArrayList<>();
//    public static List<WeightData> unitOrderDetails = new ArrayList<>();
    public static  int TableExit = 0;
    public static  int setChangeTable = 0;
    public static  int searchCustomer = 0;
    public static HashMap<String, Float> hashMap=new HashMap<>();
    public static int newOrder = 0;
   // public static Message productGlobal = null;
    public static int orderEditSatus = 0; //0 = nrmal start, get list from intant , 1 = editActvyty finss get list from sqlite Database

    public static boolean isValidEmail(String email) {

        if (email == null) {
            return false;
        }

        final String emailPattern = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Matcher matcher;
        Pattern pattern = Pattern.compile(emailPattern);

        matcher = pattern.matcher(email);

        if (matcher != null) {
            return matcher.matches();
        } else {
            return false;
        }
    }

    public static boolean CheckInternetConnection(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }
}
