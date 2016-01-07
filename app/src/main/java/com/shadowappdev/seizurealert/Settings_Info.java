package com.shadowappdev.seizurealert;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;


public class Settings_Info extends AppCompatActivity implements OnClickListener {
    public String sp_Phone_Number;
    public String sp_Name;
    public TextView ph_Number;
    public TextView Name;
    public View Save_Button;
    public Vibrator mVibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_info);
        getSharedPreferencesInfo();
        setEditTexts();
        Save_Button = findViewById(R.id.save_changes_info_button);
        Save_Button.setOnClickListener(this);
        mVibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
    }





    protected void getSharedPreferencesInfo() {
        SharedPreferences prfs = getSharedPreferences("phone_and_name", Context.MODE_PRIVATE);
        sp_Phone_Number = prfs.getString("phone_Number", "");
        sp_Name = prfs.getString("Name", "");
    }

    protected void setSharedPreferencesInfo() {
        SharedPreferences preferences = getSharedPreferences("phone_and_name", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("phone_Number",sp_Phone_Number);
        editor.putString("Name", sp_Name);
        editor.apply();
        Toast.makeText(Settings_Info.this, "Saved!", Toast.LENGTH_SHORT).show();
    }

    protected void setEditTexts() {
        ph_Number = (TextView) findViewById(R.id.phone_number_editText_settings_info);
        Name = (TextView) findViewById(R.id.editText_childName_settings_info);

        ph_Number.setText(sp_Phone_Number);
        Name.setText(sp_Name);
    }

    protected void setValuesFromEditTexts() {
        sp_Phone_Number = ph_Number.getText().toString();
        sp_Name = Name.getText().toString();
    }


        public void onClick(View v) {
            mVibrator.vibrate(125);
            setValuesFromEditTexts();
            setSharedPreferencesInfo();
            Intent intent = new Intent(Settings_Info.this, Settings.class);
            startActivity(intent);
            finish();
            Toast.makeText(Settings_Info.this, "Saved!", Toast.LENGTH_SHORT).show();

    }

}
