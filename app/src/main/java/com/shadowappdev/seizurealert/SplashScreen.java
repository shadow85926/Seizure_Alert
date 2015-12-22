package com.shadowappdev.seizurealert;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class SplashScreen extends AppCompatActivity implements View.OnClickListener {
    private Vibrator mVibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.splash_screen);

        mVibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        View enterVib1 = this.findViewById(R.id.button_on_Splash_Screen);
        enterVib1.setOnClickListener(SplashScreen.this);
    }


    public void onClick(View v) {
        mVibrator.vibrate(125);
        Intent intent = new Intent(SplashScreen.this, MainActivity.class);
        startActivity(intent);
    }
}


