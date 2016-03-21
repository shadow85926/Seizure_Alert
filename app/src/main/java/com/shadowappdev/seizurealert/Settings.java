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

public class Settings extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_screen);
       final Vibrator mVibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        TextView textView = (TextView) findViewById(R.id.settings_header);
        SpannableString content = new SpannableString("Settings");
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        textView.setText(content);

        View settings_button = findViewById(R.id.settings_button_info);
        settings_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mVibrator.vibrate(125);
                Intent intent = new Intent(Settings.this, Settings_Info.class);
                startActivity(intent);
                finish();
            }
        });

    }



}
