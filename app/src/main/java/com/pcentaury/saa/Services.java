package com.pcentaury.saa;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import static android.support.v4.content.ContextCompat.getSystemService;

public class Services {

    static boolean NetworkConnection(Context context) {
        boolean isConnected;
        ConnectivityManager cm =(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null && activeNetwork.isConnectedOrConnecting()){
            isConnected = true;
        }else {
            isConnected = false;
        }
        return isConnected;
    }
}