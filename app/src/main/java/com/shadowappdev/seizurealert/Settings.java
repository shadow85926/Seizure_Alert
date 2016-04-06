package com.shadowappdev.seizurealert;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.TextView;

public class Settings extends AppCompatActivity implements View.OnClickListener{
    public View settings_button;
    public View settings_arrow;
    public Vibrator mVibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_screen);
       mVibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        TextView textView = (TextView) findViewById(R.id.settings_header);
        SpannableString content = new SpannableString("Settings");
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        textView.setText(content);
        settings_button = findViewById(R.id.settings_button_info);
        settings_button.setOnClickListener(this);
        settings_arrow = findViewById(R.id.back_button_settings_header);
        settings_arrow.setOnClickListener(this);
              //  mVibrator.vibrate(125);
                //Intent intent = new Intent(Settings.this, Settings_Info.class);
                //startActivity(intent);
                //finish();
            }
    public void onClick(View v) {

        switch(v.getId()) {
            case R.id.settings_button_info:
                mVibrator.vibrate(125);

                Intent intent = new Intent(getApplicationContext(), Settings_Info.class);
                startActivity(intent);
                finish();

                break;

            case R.id.back_button_settings_header:
                mVibrator.vibrate(125);
                Intent intent2 = new Intent(getApplicationContext(), HomeScreen.class);
                startActivity(intent2);
                finish();
                break;
        }
        }

    }



