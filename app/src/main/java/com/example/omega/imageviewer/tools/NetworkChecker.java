package com.example.omega.imageviewer.tools;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.inject.Inject;

/**
 * Created by Alexander Chibirev on 4/25/2018.
 */

public class NetworkChecker {

    private final ConnectivityManager mConnectivityManager;
    private final NetworkStateBroadcastReceiver mNetworkStateBroadcastReceiver;
    private final IntentFilter mIntentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
    private final Context mContext;
    private final Set<OnConnectivityChangedListener> listeners = new CopyOnWriteArraySet<>();

    @Inject
    public NetworkChecker(@NonNull final Context context) {
        mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        mNetworkStateBroadcastReceiver = new NetworkStateBroadcastReceiver();
        mContext = context;
    }

    public void registerListener(OnConnectivityChangedListener listener) {
        listeners.add(listener);
        listener.onConnectivityChanged(isConnected());
        mContext.registerReceiver(mNetworkStateBroadcastReceiver, mIntentFilter);
    }

    public void unregisterListener(OnConnectivityChangedListener listener) {
        listeners.remove(listener);
        mContext.unregisterReceiver(mNetworkStateBroadcastReceiver);
    }

    private boolean isConnected() {
        NetworkInfo activeNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public interface OnConnectivityChangedListener {

        void onConnectivityChanged(boolean availableNow);

    }

    private class NetworkStateBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            boolean isConnectedNow = isConnected();

            for (OnConnectivityChangedListener listener : listeners) {
                listener.onConnectivityChanged(isConnectedNow);
            }
        }
    }
}
