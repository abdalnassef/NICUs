package com.mohamed.rami.firebase;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainForList extends AppCompatActivity {
    private DatabaseReference mRef;

    private  Integer[]IMAGE={R.drawable.ic_commercial_air_company,R.drawable.ic_commercial_air_company,R.drawable.ic_commercial_air_company,R.drawable.ic_commercial_air_company,R.drawable.ic_commercial_air_company,
            R.drawable.ic_commercial_air_company,R.drawable.ic_commercial_air_company,R.drawable.ic_commercial_air_company};

    private  String[] name=new String[100];

    private  String[] address=new String[100];

    private  String[] phone=new String[100];

    private  String[] des=new String[100];

    private  String[] price =new String[100];

    private  String[] AIRPORT1=new String[100];

    private  String[] TIME= new String[100];

    private  String[] PRICE=new String[100];

    private ArrayList<BeanClassForRecyclerView_Flightbooking> beanClassForListViewArrayRecyclerHistroy;

    private RecyclerView recyclerView;
    private MyRecycleAdapter_Flightbooking mAdapter;

    long counter;
    int a;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitylist_main);

        Intent intent =getIntent();
        name = intent.getStringArrayExtra("name");
        address = intent.getStringArrayExtra("address");
        phone = intent.getStringArrayExtra("phone");
        des = intent.getStringArrayExtra("des");
        price = intent.getStringArrayExtra("price");
        for (int co = 0 ; co <10 ; co++) {
            name[co] = name[co];
            address[co]=address[co];
            phone[co]=phone[co];
            des[co]=des[co];
            price[co]=price[co];
        }

        /*mRef = FirebaseDatabase.getInstance().getReference();
        mRef.child("User").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                SettersGetters post = new SettersGetters();
                counter = dataSnapshot.getChildrenCount();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //nicuName,nicuAddress,nicuPhone,nicuPrice,,nicuCount,lastUpdate,lat,lng,nicuDes
                    post = postSnapshot.getValue(SettersGetters.class);
                    name[a]=post.getNicuName();
                    address[a]=post.getNicuAddress();
                    phone[a]=post.getNicuPhone();
                    des[a]=post.getNicuCount();
                    price[a]=post.getNicuPrice();
                    //nicuLat.add(post.getNicuLat());
                    //nicuLng.add(post.getNicuLng());
                    //nicuDes.add(post.getNicuDesc());
                    //intent = new Intent(in.this,MapsActivity.class);
                    //intent.putExtra("name"+a,post.getNicuName());

                    //Toast.makeText(MainForList.this,post.getNicuName(),Toast.LENGTH_SHORT).show();

                    a++;//System.out.println(nicuNames.get(0));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/
        //Toast.makeText(MainForList.this,a+"",Toast.LENGTH_SHORT).show();

        recyclerView = (RecyclerView) findViewById(R.id.recycleview);
        beanClassForListViewArrayRecyclerHistroy = new ArrayList<>();


        for (int i = 0; i < IMAGE.length; i++) {
            BeanClassForRecyclerView_Flightbooking beanClassForRecyclerView_contacts = new BeanClassForRecyclerView_Flightbooking(IMAGE[i],name[i],address[i],phone[i],des[i],price[i],AIRPORT1[i],des[i],price[i]);

            beanClassForListViewArrayRecyclerHistroy.add(beanClassForRecyclerView_contacts);
        }


        mAdapter = new MyRecycleAdapter_Flightbooking(MainForList.this,beanClassForListViewArrayRecyclerHistroy);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(MainForList.this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        /*recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                Intent in = new Intent(MainForList.this,UserProfile.class);
                startActivity(in);
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });*/
    }
    }

