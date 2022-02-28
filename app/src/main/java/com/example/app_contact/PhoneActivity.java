package com.example.app_contact;


import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.HashMap;

public class PhoneActivity extends AppCompatActivity {
    ProgressBar progressBar;
    TextView text;
    String[] permission={Manifest.permission.READ_CONTACTS};
    int requestcode=123;
    boolean permissionprovider=false;
    HashMap<Integer,String> namearray;
    HashMap<Integer,String> numberarray;

    String[] mcol=new String[]
            {
                    ContactsContract.Contacts.DISPLAY_NAME,
                    ContactsContract.Contacts._ID,
                    ContactsContract.Contacts.HAS_PHONE_NUMBER
            };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);
        combinexml();
        namearray=new HashMap<>();
        numberarray=new HashMap<>();

        if(permissionprovider)
        {
            getname();
        }
        else
        {
            getpermission();
        }

    }

    private void getpermission() {
        if(Build.VERSION.SDK_INT>Build.VERSION_CODES.M)
        {
            if(ContextCompat.checkSelfPermission(this,Manifest.permission.READ_CONTACTS)== PackageManager.PERMISSION_GRANTED)
            {
                getname();
            }
            else
            {
                requestPermissions(permission,requestcode);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==requestcode && grantResults.length>0 && grantResults[0] ==PackageManager.PERMISSION_GRANTED)
        {
            permissionprovider=true;
            getname();
        }
        else
        {
            permissionprovider=false;
            getpermission();
        }
    }

    private void getname()
    {
        namearray=getAllContact();
        StringBuilder builder=new StringBuilder();
        for(int i =1;i<=namearray.size();i++)
        {
            builder.append("name=>").append(namearray.get(i)).append("\n");
            builder.append("number=>").append(numberarray.get(i)).append("\n\n");
        }
        progressBar.setVisibility(View.GONE);
        text.setText(builder.toString());
    }

    private HashMap<Integer, String> getAllContact()
    {
        HashMap<Integer,String> namelist =new HashMap<>();
        int count=0;
        ContentResolver cr=getContentResolver();
        Cursor cursor=cr.query(ContactsContract.Contacts.CONTENT_URI,mcol,null,null,null);
        if((cursor !=null ? cursor.getCount() :0)>0)
        {
            while(cursor.moveToNext())
            {
                count++;
                String name=cursor.getString(0);
                String id= cursor.getString(1);
                int status= Integer.parseInt(cursor.getString(2));
                namelist.put(count,name);

                if (status>0)

                {
                    Cursor cur= cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?" ,
                            new String[] {id},null);
                    while(cur.moveToNext())
                    {
                        @SuppressLint("Range") String phoneno=cur.getString(cur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        numberarray.put(count,phoneno);

                    }
                    cur.close();

                }
            }
        }
        cursor.close();
        return  namelist;
    }

    private void combinexml() {
        progressBar=findViewById(R.id.progress);
        text=findViewById(R.id.text);

    }
}
