package com.shadowappdev.seizurealert;


import android.content.*;
import android.content.SharedPreferences;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Vibrator;
import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;


public class HomeScreen extends AppCompatActivity  implements OnClickListener {
    private Vibrator mVibrator;
    private String sp_Phone_Number;
    private String sp_Name;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);


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
    }

    protected void setSharedPreferencesInfo(){
        SharedPreferences prfs = getSharedPreferences("phone_and_name", Context.MODE_PRIVATE);
         sp_Phone_Number = prfs.getString("phone_Number", "");
         sp_Name = prfs.getString("Name", "");
         sp_Name = sp_Name + " may be having a Seizure, *this was sent by Seizure Alert*";
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
            e.printStackTrace();
        }
    }

    protected void Start_HR(){


    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.test_text_Button:
                Start_HR();
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
