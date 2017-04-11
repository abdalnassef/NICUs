package com.mohamed.rami.firebase;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Mana3 on 03/04/2017.
 */

public class choose extends AppCompatActivity {

    private  String[] DEPARTURE=new String[100];

    private  String[] CITY=new String[100];

    private  String[] AIRPORT=new String[100];

    private  String[] DEPARTURE1=new String[100];

    private  String[] CITY1=new String[100];

    private  String[] AIRPORT1=new String[100];

    private  String[] TIME= new String[100];

    private  String[] PRICE=new String[100];
    private static final int RC_SIGN_IN = 9001;


    private DatabaseReference mRef,cRef;
    SettersGetters settersGetters;
    long counter;
    int a;

    protected void onCreate(Bundle saveInstansBundle) {
        super.onCreate(saveInstansBundle);
        setContentView(R.layout.choose);
        settersGetters = new SettersGetters();
        mRef = FirebaseDatabase.getInstance().getReference();

        mRef.child("User").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                SettersGetters post = new SettersGetters();
                counter = dataSnapshot.getChildrenCount();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //nicuName,nicuAddress,nicuPhone,nicuPrice,,nicuCount,lastUpdate,lat,lng,nicuDes
                    post = postSnapshot.getValue(SettersGetters.class);
                    DEPARTURE[a] = post.getNicuName();
                    CITY[a] = post.getNicuAddress();
                    AIRPORT[a] = post.getNicuPhone();
                    TIME[a] = post.getNicuCount();
                    PRICE[a] = post.getNicuPrice();
                    //nicuAddresses.add(post.getNicuAddress());
                    //niucCount.add(post.getNicuCount());
                    //nicuPrices.add(post.getNicuCount());
                    //nicuPhones.add(post.getNicuPhone());
                    //nicuUpdate.add(post.getNicuUpdate());
                    //nicuLat.add(post.getNicuLat());
                    //nicuLng.add(post.getNicuLng());
                    //nicuDes.add(post.getNicuDesc());
                    //intent = new Intent(in.this,MapsActivity.class);
                    //intent.putExtra("name"+a,post.getNicuName());

                    // Toast.makeText(in.this,a+"",Toast.LENGTH_SHORT).show();
                    a++;//System.out.println(nicuNames.get(0));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }



    public void inmap (View view){
        Intent ii = new Intent(choose.this,MapsActivity.class);
        startActivity(ii);
    }

    public void signup (View view){
        Intent ii = new Intent(choose.this,SignUp.class);
        startActivity(ii);
    }
    public void incon (View view){
        Intent ii = new Intent(choose.this,connect.class);
        startActivity(ii);
    }

    public void inlist (View view){
        Intent i = new Intent(choose.this, MainForList.class);
        i.putExtra("name", DEPARTURE);
        i.putExtra("address", CITY);
        i.putExtra("phone", AIRPORT);
        i.putExtra("des", TIME);
        i.putExtra("price", PRICE);
        startActivity(i);
    }

    public void inhelp (View view){
        PrefManager prefManager = new PrefManager(getApplicationContext());

        // make first time launch TRUE
        prefManager.setFirstTimeLaunch(true);

        startActivity(new Intent(choose.this, WelcomeActivity.class));
        finish();

    }




}
