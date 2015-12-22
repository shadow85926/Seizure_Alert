package com.shadowappdev.seizurealert;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Vibrator;
import android.content.Context;
import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;

import android.widget.Toast;

public class HomeScreen extends AppCompatActivity implements OnClickListener {
    private Vibrator mVibrator;
    String phone_No;
    String message;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);



        mVibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        View enterVib1 = this.findViewById(R.id.test_text_Button);
        enterVib1.setOnClickListener(this);
    }

    protected void setVariables() {
            Intent intent = getIntent();
            phone_No = intent.getStringExtra("phone_Number");
            message = intent.getStringExtra("Name");
            message = message + " may be having a Seizure, *this was sent by Seizure Alert*";



    }
    protected void sendSMSMessage(String phone_No, String message) {



        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phone_No, null, message, null, null);
            Toast.makeText(getApplicationContext(), "SMS sent.",
                    Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(),
                    "SMS failed, please try again.",
                    Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {

        mVibrator.vibrate(125);
        setVariables();
        sendSMSMessage(phone_No,message);
    }


}
