package br.com.appdog.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import br.com.appdog.service.ServiceConnection;

public class NetworkChangeReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(final Context context, final Intent intent) {


        if(checkInternet(context))
        {
            Toast.makeText(context, "Network Available Do operations",Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(context, "nao tem internet",Toast.LENGTH_LONG).show();
        }

    }

    boolean checkInternet(Context context) {
        ServiceConnection serviceManager = new ServiceConnection(context);
        if (serviceManager.isNetworkAvailable()) {
            return true;
        } else {
            return false;
        }
    }
}
