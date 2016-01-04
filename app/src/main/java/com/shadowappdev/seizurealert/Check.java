package com.shadowappdev.seizurealert;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class Check extends AppCompatActivity {
boolean Splash_Shown = false;
boolean Info_Shown = false;

    protected void setPrefsSplash(){
        SharedPreferences prfs = getSharedPreferences("SPLASH_SCREEN", Context.MODE_PRIVATE);
        Splash_Shown = prfs.getBoolean("Splash_Screen_Displayed", false);
    }
    protected void setPrefsMain(){
        SharedPreferences prfs = getSharedPreferences("MAIN_SCREEN", Context.MODE_PRIVATE);
        Info_Shown = prfs.getBoolean("Main_Shown", false);
    }
    protected void intentCheck(){
        if(Splash_Shown && Info_Shown ) {
            Intent intent = new Intent(Check.this, HomeScreen.class);
            startActivity(intent);
        }

        if(Splash_Shown && !Info_Shown ){
            Intent intent2 = new Intent(Check.this, MainActivity.class);
            startActivity(intent2);
        }

        //noinspection StatementWithEmptyBody
        if (!Splash_Shown && !Info_Shown){
            Intent intent3 = new Intent(Check.this, SplashScreen.class);
            startActivity(intent3);
        }
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check);
        setPrefsSplash();
        setPrefsMain();
        intentCheck();
        finish();


    }



}
