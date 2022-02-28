package com.example.app_contact;


import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SmsActivity extends AppCompatActivity {
    EditText entrnumbr,entrmsg;
    Button sendmsg;
    int requestcode =115058;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);

        entrnumbr =findViewById(R.id.entrnumber);
        entrmsg =findViewById(R.id.entrmsg);
        sendmsg =findViewById(R.id.sendmsg);

        sendmsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nmbrtext =entrnumbr.getText().toString().trim();
                String msgtext =entrmsg.getText().toString().trim();


                Intent smsintent =new Intent(getApplicationContext(),MainActivity.class);

                SmsManager sms =SmsManager.getDefault();
                PendingIntent pendingIntent =PendingIntent.getActivity(getApplicationContext(),requestcode,smsintent,PendingIntent.FLAG_UPDATE_CURRENT);
                sms.sendTextMessage(nmbrtext,null,msgtext,pendingIntent,null);

                Toast.makeText(getApplicationContext(), "Message sent successfully", Toast.LENGTH_SHORT).show();
            }
        });
    }
}