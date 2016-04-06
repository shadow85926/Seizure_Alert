package com.shadowappdev.seizurealert;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class Check extends AppCompatActivity {
boolean Splash_Shown = false;
boolean Info_Shown = false;
    boolean BLE_on = false;

    protected  void check_BLE_on(){
        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
            Toast.makeText(this, "Phone Doesn't Have BLE, App will not work", Toast.LENGTH_SHORT).show();
            BLE_on = false;
            finish();
        }
        else{
            BLE_on = true;
        }
    }
    protected void setPrefsSplash(){
        SharedPreferences prfs = getSharedPreferences("SPLASH_SCREEN", Context.MODE_PRIVATE);
        Splash_Shown = prfs.getBoolean("Splash_Screen_Displayed", false);
    }
    protected void setPrefsMain(){
        SharedPreferences prfs = getSharedPreferences("MAIN_SCREEN", Context.MODE_PRIVATE);
        Info_Shown = prfs.getBoolean("Main_Shown", false);
    }
    protected void intentCheck(){
        if(Splash_Shown && Info_Shown && BLE_on ) {
            Intent intent = new Intent(Check.this, TurnOnBT.class);
            startActivity(intent);
        }

        if(Splash_Shown && !Info_Shown && BLE_on ){
            Intent intent2 = new Intent(Check.this, MainActivity.class);
            startActivity(intent2);
        }

        //noinspection StatementWithEmptyBody
        if (!Splash_Shown && !Info_Shown && BLE_on){
            Intent intent3 = new Intent(Check.this, SplashScreen.class);
            startActivity(intent3);
        }
    }
    protected void permissionCheck(){
        // Assume thisActivity is the current activity
        int permissionCheck = ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.SEND_SMS);
        if(permissionCheck == PackageManager.PERMISSION_GRANTED){
            Toast.makeText(Check.this, "Can Send SMS!", Toast.LENGTH_SHORT).show();

            if(permissionCheck == PackageManager.PERMISSION_DENIED){
                Toast.makeText(Check.this, "DOESN'T HAVE PERMISSION TO SEND TEXTS!", Toast.LENGTH_SHORT).show();
              finish();
                System.exit(0);
            }
        }

    }
    protected void fixPermissions(){
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(Check.this,
                Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(Check.this,
                    Manifest.permission.SEND_SMS)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        check_BLE_on();
        setContentView(R.layout.check);
        setPrefsSplash();
        setPrefsMain();
        permissionCheck();
        fixPermissions();
        intentCheck();// must happen last
        finish();


    }



}
