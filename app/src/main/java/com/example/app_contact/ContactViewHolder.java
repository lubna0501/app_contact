package com.example.app_contact;

import android.Manifest;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
public class ContactViewHolder extends RecyclerView.ViewHolder {
    TextView tvName, tvPhoneNum;
    RelativeLayout relativeLayout;
    ImageView deleteContact ,call;
    ImageView editContact;
    String[] prem = {Manifest.permission.CALL_PHONE,Manifest.permission.READ_SMS,Manifest.permission.INTERNET,Manifest.permission.SEND_SMS};
    int CAAL_REQUEST = 1;

    public ContactViewHolder(View itemView) {
        super(itemView);
        tvName = itemView.findViewById(R.id.contactName);
        tvPhoneNum = itemView.findViewById(R.id.phoneNum);
        deleteContact = itemView.findViewById(R.id.deleteContact);
        editContact = itemView.findViewById(R.id.editContact);
        call = itemView.findViewById(R.id.callContact);
        relativeLayout =itemView.findViewById(R.id.relative);

    }
}
