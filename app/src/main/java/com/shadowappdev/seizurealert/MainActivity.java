package com.shadowappdev.seizurealert;

import android.content.Intent;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements OnClickListener{
    private EditText phone_Number;
    private EditText KinderName;
    private Vibrator mVibrator;


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


    @Override
    public void onClick(View v) {
    mVibrator.vibrate(125);

        if(phone_Number.getText().length()==10 && KinderName.getText().length()>0){
            Intent intent = new Intent(MainActivity.this, HomeScreen.class);
            intent.putExtra("phone_Number", phone_Number.getText().toString());
            intent.putExtra("Name", KinderName.getText().toString());
            startActivity(intent);
        } else {
            Toast.makeText(this, "Please input Child's Name and a Parent Phone Number", Toast.LENGTH_SHORT).show();
        }


    }

}



