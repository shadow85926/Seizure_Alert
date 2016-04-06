package com.shadowappdev.seizurealert;

import android.app.IntentService;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.widget.Toast;

import java.util.Set;

public class BackgroundBluetoothCheck extends IntentService{
private BluetoothAdapter BA;
    private Set<BluetoothDevice>pairedDevices;


    public BackgroundBluetoothCheck(){
        super("BackgroundBluetoothCheck");
        BA = BluetoothAdapter.getDefaultAdapter();


    }

    @Override
    protected void onHandleIntent(Intent intent) {
        //This is what the service does



    }
}




