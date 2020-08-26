package com.swatiitsolutions.loginsingupdemo.Api;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.swatiitsolutions.loginsingupdemo.R;


public class NetworkChangeReceiver extends BroadcastReceiver {


    NetworkChangers networkChangersListener;

    @Override
    public void onReceive(Context context, Intent intent) {

        try {
            if (!ClsGlobal.CheckInternetConnection(context)) {
                networkChangersListener.OnNetworkChange(context.getString(R.string.noInternet));
            }

        } catch (NullPointerException e) {
            e.printStackTrace();
        }

    }


    public void SetOnNetworkChange(NetworkChangers networkChangersListener) {
        this.networkChangersListener = networkChangersListener;
    }


    public interface NetworkChangers {
        void OnNetworkChange(String status);
    }


}
