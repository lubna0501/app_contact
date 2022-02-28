package com.example.app_contact;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class EmailActivity extends AppCompatActivity {
    EditText receveremail,entersubjct,entrmsg;
    Button sendemail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);

        receveremail =findViewById(R.id.recivermail);
        entersubjct =findViewById(R.id.entersubjct);
        entrmsg =findViewById(R.id.entrmsg);
        sendemail =findViewById(R.id.sendemail);

        sendemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailtext =receveremail.getText().toString().trim();
                String subjcttext =entersubjct.getText().toString().trim();
                String msgtext =entrmsg.getText().toString().trim();

                Intent email =new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL,new String[]{emailtext});
                email.putExtra(Intent.EXTRA_SUBJECT,subjcttext);
                email.putExtra(Intent.EXTRA_TEXT,msgtext);
                email.setType("message/rfc822");

                startActivity(Intent.createChooser(email,"Choose An Email Client"));
            }
        });
    }
}