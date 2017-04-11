package com.mohamed.rami.firebase;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

/**
 * Created by Rp on 6/14/2016.
 */
public class MyRecycleAdapter_Flightbooking extends RecyclerView.Adapter<MyRecycleAdapter_Flightbooking.MyViewHolder> {
Context context;

    boolean showingFirst = true;

    private  String[] name=new String[100];

    private  String[] address=new String[100];

    private  String[] phone=new String[100];

    private  String[] des=new String[100];

    private  String[] pric =new String[100];

    private  String[] AIRPORT1=new String[100];
    private DatabaseReference mRef;
    long counter;
    int a;

    private List<BeanClassForRecyclerView_Flightbooking> moviesList;


    ImageView NormalImageView;
    Bitmap ImageBit;
    float ImageRadius = 40.0f;




    public class MyViewHolder extends RecyclerView.ViewHolder {



        ImageView image;
        TextView departure;
        TextView city;
        TextView airport;
        TextView departure1;
        TextView city1;
        TextView airport1;
        TextView time;
        TextView price;

        private String mItem;

        public MyViewHolder(View view) {
            super(view);

            image = (ImageView) view.findViewById(R.id.image);
            departure = (TextView) view.findViewById(R.id.departure);
            city = (TextView) view.findViewById(R.id.city);
            airport= (TextView) view.findViewById(R.id.airport);
            //departure1 = (TextView) view.findViewById(R.id.departure1);
            //city1 = (TextView) view.findViewById(R.id.city1);
            ///airport1= (TextView) view.findViewById(R.id.airport1);
            time= (TextView) view.findViewById(R.id.time);
            price= (TextView) view.findViewById(R.id.price);
            mRef = FirebaseDatabase.getInstance().getReference();
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
                        pric[a]=post.getNicuPrice();
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
            });



        }
        public void setItem(String item) {
            mItem = item;
        }



    }




    public MyRecycleAdapter_Flightbooking(MainForList mainActivityContacts, List<BeanClassForRecyclerView_Flightbooking> moviesList) {
        this.moviesList = moviesList;
       this.context = mainActivityContacts;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.flight_recycler_view, parent, false);



        return new MyViewHolder(itemView);


    }




    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        BeanClassForRecyclerView_Flightbooking movie = moviesList.get(position);
        holder.departure.setText(movie.getDeparture());
        holder.city.setText(movie.getCity());
        holder.airport.setText((movie.getAirport()));
        //holder.departure1.setText(movie.getDeparture1());
        //holder.city1.setText(movie.getCity1());
        //holder.airport1.setText((movie.getAirport1()));
        holder.time.setText(movie.getTime());
        holder.price.setText(movie.getPrice());
        holder.image.setImageResource(movie.getImage());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i = new Intent(v.getContext(),UserProfile.class);
                i.putExtra("pos",holder.getAdapterPosition());
                i.putExtra("name", name);
                i.putExtra("address", address);
                i.putExtra("phone", phone);
                i.putExtra("des", des);
                i.putExtra("price", pric);

                context.startActivity(i);

            }
        });


    }


    @Override
    public int getItemCount() {
        return moviesList.size();
    }






}


