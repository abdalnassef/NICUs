package com.mohamed.rami.firebase;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.vision.text.Text;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by mana3 on 14/02/2017.
 */

public class SignUp extends AppCompatActivity {
    EditText nicuName, nicuAddress, nicuPhone, nicuPrice , nicuCount , nicuUpdate,nicuLat,nicuLng,nicuDesc ;

    DatabaseReference db;
    FirebaseHelper helper;



    TextView reg ;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle saveInstansBundle) {
        super.onCreate(saveInstansBundle);
        setContentView(R.layout.signup);
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        nicuName = (EditText) findViewById(R.id.regName);
        nicuAddress = (EditText) findViewById(R.id.regAddress);
        nicuPhone = (EditText) findViewById(R.id.regPhone);
        nicuPrice = (EditText) findViewById(R.id.regPrice);
        nicuCount = (EditText) findViewById(R.id.regCount);
        nicuUpdate = (EditText) findViewById(R.id.regDate);
        nicuLat = (EditText) findViewById(R.id.regLat);
        nicuLng = (EditText) findViewById(R.id.regLng);
        nicuDesc = (EditText) findViewById(R.id.regDes);
        reg = (TextView) findViewById(R.id.register);

        db = FirebaseDatabase.getInstance().getReference();
        helper = new FirebaseHelper(db);

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
    }

    private void register(){
        //nicuName,nicuAddress,nicuPhone,nicuPrice,,nicuCount,lastUpdate,lat,lng,nicuDes
        String nicuname = nicuName.getText().toString();
        final String nicuaddress = nicuAddress.getText().toString();
        final String nicuphone = nicuPhone.getText().toString();
        final String nicuprice = nicuPrice.getText().toString();
        final String nicucount = nicuCount.getText().toString();
        final String lastupdate = nicuUpdate.getText().toString();
        final String niculat = nicuLat.getText().toString();
        final String niculng = nicuLng.getText().toString();
        String nicudesc = nicuDesc.getText().toString();
        /*if(TextUtils.isEmpty(name)){
            Toast.makeText(this, "Enter Your Name ", Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(userName)){
            Toast.makeText(this, "Enter User Name ", Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(pass)){
            Toast.makeText(this, "Enter Password ", Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(confPass)){
            Toast.makeText(this, "Enter Password ", Toast.LENGTH_LONG).show();
            return;
        }
        if(!pass.equals(confPass)){
            Toast.makeText(this, "Password didn't match ", Toast.LENGTH_LONG).show();
            return;
        }*/
        //nicuName,nicuAddress,nicuPhone,nicuPrice,,nicuCount,lastUpdate,lat,lng,nicuDes
        SettersGetters s = new SettersGetters();
        s.setNicuName(nicuname);
        s.setNicuAddress(nicuaddress);
        s.setNicuPhone(nicuphone);
        s.setNicuPrice(nicuprice);
        s.setNicuCount(nicucount);
        s.setNicuUpdate(lastupdate);
        s.setNicuLat(niculat);
        s.setNicuLng(niculng);
        s.setNicuDesc(nicudesc);
        if(helper.save(s)){
            nicuName.setText("");
            nicuAddress.setText("");
            nicuPhone.setText("");
            nicuPrice.setText("");
            nicuCount.setText("");
            nicuUpdate.setText("");
            nicuLat.setText("");
            nicuLng.setText("");
            nicuDesc.setText("");
            Toast.makeText(this, "Well done", Toast.LENGTH_LONG).show();
            //Intent i = new Intent(SignUp.this,MapsActivity.class);
            //startActivity(i);
        }

    }
}