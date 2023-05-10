package com.example.smsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.smsapp.R.id;

public class MainActivity extends AppCompatActivity {
   Button send,clear;
    EditText txt_mobilenumber,txt_msg;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        send = (Button) findViewById(R.id.btn_send);
        txt_mobilenumber = (EditText) findViewById(R.id.mobile_number);
        txt_msg = (EditText) findViewById(R.id.msg);
        clear =(Button) findViewById(id.btn_clear);


        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.SEND_SMS) ==
                PackageManager.PERMISSION_GRANTED){
                     sendSMS();
                }else {
                    requestPermissions(new String[]{Manifest.permission.SEND_SMS},1);
                }

            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt_mobilenumber .setText("");
                txt_msg .setText("");

            }
        });

    }
    public void sendSMS(){
        String mobile_number = txt_mobilenumber.getText().toString();
        String msg = txt_msg.getText().toString();

        if (!mobile_number.isEmpty() && !msg.isEmpty()){
            try {
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(mobile_number,null,msg,null,null);
                Toast.makeText(this, "Message send Successfullly", Toast.LENGTH_SHORT).show();
            }catch (Exception e){
                e.printStackTrace();


                Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }

        }else {
            Toast.makeText(this, "Enter something", Toast.LENGTH_SHORT).show();
        }

    }
}