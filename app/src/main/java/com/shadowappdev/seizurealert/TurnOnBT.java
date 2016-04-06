package com.shadowappdev.seizurealert;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.Set;

public class TurnOnBT extends AppCompatActivity {
    private static BluetoothAdapter BA;
    private static Set<BluetoothDevice> pairedDevices;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        bluetoothOn();
        Intent goToNext = new Intent(TurnOnBT.this, HomeScreen.class);
        startActivity(goToNext);
    }

    public void bluetoothOn(){
        //Turn on BT
        BA = BluetoothAdapter.getDefaultAdapter();

        if (!BA.isEnabled()) {
             //Intent turnOn = new Intent(BA.enable());
            // startActivityForResult(turnOn, 0);
            BA.enable();
            Toast.makeText(getApplicationContext(), "Bluetooth turned on", Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(getApplicationContext()," Bluetooth already on", Toast.LENGTH_LONG).show();
        }

        //Find paired Devices
        pairedDevices = BA.getBondedDevices();

    }
}
