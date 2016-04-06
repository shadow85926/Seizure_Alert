package com.shadowappdev.seizurealert;


import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.content.*;
import android.content.SharedPreferences;

import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Vibrator;
import android.telephony.SmsManager;

import android.view.View;
import android.view.View.OnClickListener;

import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;


public class HomeScreen extends AppCompatActivity  implements OnClickListener {
    private Vibrator mVibrator;
    private String sp_Phone_Number;
    private String sp_Name;
    private BluetoothManager bm = (BluetoothManager)getSystemService(Context.BLUETOOTH_SERVICE);
    private BluetoothAdapter mBluetoothAdapter = bm.getAdapter();
    private Set<BluetoothDevice> pairedDevices;
    int apiVersion = android.os.Build.VERSION.SDK_INT;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);
        bluetoothOn();

        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
            Toast.makeText(this, "BLE Not Supported",
                    Toast.LENGTH_SHORT).show();
            finish();
            View settings = findViewById(R.id.settings_dots);
            settings.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                Intent intent = new Intent(HomeScreen.this, Settings.class);
                startActivity(intent);
               mVibrator.vibrate(125);
                }
        });

        mVibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        View enterVib1 = this.findViewById(R.id.test_text_Button);
        enterVib1.setOnClickListener(this);
        View reset_button = this.findViewById(R.id.reset_Check_Button);
        reset_button.setOnClickListener(this);
    }}



    protected void setSharedPreferencesInfo(){
        SharedPreferences prfs = getSharedPreferences("phone_and_name", Context.MODE_PRIVATE);
         sp_Phone_Number = prfs.getString("phone_Number", "");
         sp_Name = prfs.getString("Name", "");
         sp_Name = sp_Name + " may be having a Seizure, *this was sent by Seizure Alert*";
    }

    public void bluetoothOn(){

        if (!mBluetoothAdapter.isEnabled()) {
            mBluetoothAdapter.enable();
            Toast.makeText(getApplicationContext(), "Bluetooth turned on", Toast.LENGTH_LONG).show();
        }
    }




    protected void sendSMSMessage(String sp_Phone_Number,String sp_Name) {



        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(sp_Phone_Number, null, sp_Name, null, null);
            Toast.makeText(getApplicationContext(), "SMS sent.",
                    Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(),
                    "SMS failed, please try again.",
                    Toast.LENGTH_LONG).show();
            check_SMS();

            e.printStackTrace();
        }
    }

    protected void check_SMS() {
        int permissionCheck = ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.SEND_SMS);
        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(getApplicationContext(), "Can Send SMS!", Toast.LENGTH_SHORT).show();
        }
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(getApplicationContext(), "Please go into settings and enable SMS permission for Seizure Alert", Toast.LENGTH_SHORT).show();

        }
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.test_text_Button:
            mVibrator.vibrate(125);
            setSharedPreferencesInfo();
            sendSMSMessage(sp_Phone_Number, sp_Name);
            //Toast.makeText(HomeScreen.this, sp_Phone_Number + " " + sp_Name , Toast.LENGTH_SHORT).show();
            break;

          case R.id.reset_Check_Button:
                SharedPreferences preferences = getSharedPreferences("phone_and_name", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("phone_Number"," ");
                editor.putString("Name","");
                editor.apply();

                SharedPreferences prfs = getSharedPreferences("SPLASH_SCREEN", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor1 = prfs.edit();
                editor1.putBoolean("Splash_Screen_Displayed", false);
                editor1.apply();

                SharedPreferences prfs1 = getSharedPreferences("MAIN_SCREEN", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor2 = prfs1.edit();
                editor2.putBoolean("Main_Shown", false);
                editor2.apply();

                Toast.makeText(HomeScreen.this, "ALL SCREENS RESET!", Toast.LENGTH_SHORT).show();
                break;
        }
    }



}
