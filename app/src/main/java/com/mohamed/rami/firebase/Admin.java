package com.mohamed.rami.firebase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

public class Admin extends AppCompatActivity {

    EditText nicName , Address , price , latitude , longitude , phone , count , desc ,dat;
    String name , address , cost , lat , lng , ph , cou , des , date;
    DatabaseReference db;
    FirebaseHelper helper;
    TextView signIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        db = FirebaseDatabase.getInstance().getReference();
        helper = new FirebaseHelper(db);
        signIn = (TextView) findViewById(R.id.Edit);
        nicName = (EditText) findViewById(R.id.nicName);
        Address = (EditText) findViewById(R.id.address);
        price = (EditText) findViewById(R.id.price);
        latitude = (EditText) findViewById(R.id.latitude);
        longitude = (EditText) findViewById(R.id.longitude);
        phone = (EditText) findViewById(R.id.phone);
        count = (EditText) findViewById(R.id.count);
        desc = (EditText) findViewById(R.id.additional);
        dat = (EditText) findViewById(R.id.date);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit();
            }
        });

    }
    private void edit(){
        final String name = nicName.getText().toString();
        final String address = Address.getText().toString();
        final String cost = price.getText().toString();
        final String lat = latitude.getText().toString();
        final String lng = longitude.getText().toString();
        final String ph = phone.getText().toString();
        final String cou = count.getText().toString();
        final String des = desc.getText().toString();
        final String date = dat.getText().toString();
        //Date d = new Date();
        //long day = d.getTime();
        //final String  conv = day+"";
        NICUPosition nicuPosition = new NICUPosition();
        nicuPosition.setNicuName("sdsd");
        nicuPosition.setLongitude("sds");
        nicuPosition.setAddress("sdsds");
        nicuPosition.setPhone("sdsds");
        nicuPosition.setPrice("sdsds");
        nicuPosition.setSituations("sdsds");
        nicuPosition.setNicuCount("sdsds");
        nicuPosition.setLatitude("sdsdsd");
        nicuPosition.setDate("sdsdsd");

        if(helper.updateNICU(nicuPosition)){
            Toast.makeText(Admin.this, "Edited Successfully", Toast.LENGTH_SHORT).show();
        }
    }
}
