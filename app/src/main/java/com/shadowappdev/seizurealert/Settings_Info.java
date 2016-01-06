package com.shadowappdev.seizurealert;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;


public class Settings_Info extends AppCompatActivity{
     String sp_Phone_Number;
     String sp_Name;
     TextView ph_Number;
     TextView Name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_info);
        getSharedPreferencesInfo();
        setEditTexts();
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


}
