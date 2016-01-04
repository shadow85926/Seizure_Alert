package com.shadowappdev.seizurealert;

import android.content.*;
import android.content.SharedPreferences;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements OnClickListener {
    private EditText phone_Number;
    private EditText KinderName;
    private Vibrator mVibrator;

    protected void setSharedPreferencesShown(){
        SharedPreferences shown = getSharedPreferences("MAIN_SCREEN", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = shown.edit();
        editor.putBoolean("Main_Shown", true);
        editor.apply();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mVibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        phone_Number = (EditText) findViewById(R.id.phone_number_editText);
        KinderName = (EditText) findViewById(R.id.editText_childName);

        View enterVib1 = this.findViewById(R.id.enter_firstscreen_button);
        enterVib1.setOnClickListener(this);
    }

    public void saveInfo(){
        SharedPreferences preferences = getSharedPreferences("phone_and_name", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("phone_Number",phone_Number.getText().toString());
        editor.putString("Name", KinderName.getText().toString());
        editor.apply();
        Toast.makeText(MainActivity.this, "Saved!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
    mVibrator.vibrate(125);

        if(phone_Number.getText().length()==10 && KinderName.getText().length()>0){
            setSharedPreferencesShown();
            saveInfo();
            Intent intent = new Intent(MainActivity.this, HomeScreen.class);
            intent.putExtra("phone_Number", phone_Number.getText().toString());
            intent.putExtra("Name", KinderName.getText().toString());
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Please input Child's Name and a Parent Phone Number", Toast.LENGTH_SHORT).show();
        }


    }


}



