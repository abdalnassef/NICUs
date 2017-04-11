package com.mohamed.rami.firebase;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.HashMap;

/**
 * Created by mana3 on 02/03/2017.
 */

public class Del extends FragmentActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener,LocationListener {
    TextView tname,tplace,tnum,ttel,tadd ;

        private GoogleMap mMap;
    HashMap<String, String> markerMap = new HashMap<String, String>();
    String id = null;
    private  static GoogleApiClient googleApiClient;
    GoogleApiClient mGoogleApiClient;
    Location mLastLocation;
    Marker mCurrLocationMarker;
    LocationRequest mLocationRequest;
    String x1,y1;
    double xx,yy;
String test="0";

    @Override
    protected void onCreate (Bundle saveInstansBundle) {
        super.onCreate(saveInstansBundle);

        try {

            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            // Toast.makeText(Del.this, "2222", Toast.LENGTH_LONG).show();

               // mapFragment.getMap();
            // Toast.makeText(Del.this, "3333"+yy, Toast.LENGTH_LONG).show();


            setContentView(R.layout.nicu);
            tname = (TextView) findViewById(R.id.name);
            tplace = (TextView) findViewById(R.id.place);
            tnum = (TextView) findViewById(R.id.num);
            ttel = (TextView) findViewById(R.id.tel);
            tadd = (TextView) findViewById(R.id.add);


            Intent i = getIntent();
            if (i != null) {
                test = i.getStringExtra("a");
                //The key argument here must match that used in the other activity
            }
            if (test.equals("a1")) {
                tname.setText("مركز النيل");
                tplace.setText("المنوفية");
                tnum.setText("500 EL");
                ttel.setText("048/2227709");
                tadd.setText(" شبين الكوم-ميدان شرف- برج الكوثر ");
            } else if (test.equals("a2")) {
                tname.setText("مركز الفجر");
                tplace.setText("المنوفية");
                tnum.setText("500 EL");
                ttel.setText("048-2315924");
                tadd.setText("شبين الكوم عمر أفندى بجوار كوبرى مبارك");
            } else if (test.equals("a3")) {
                tname.setText("مستشفي المواساة");
                tplace.setText("المنوفية");
                tnum.setText("700 EL");
                ttel.setText("048-2194005");
                tadd.setText("شبين الكوم بجوار الكوبرى العلوى");
            } else if (test.equals("a4")) {
                tname.setText("مستشفى الامومه");
                tplace.setText("المنوفية");
                tnum.setText("1000 El");
                ttel.setText("048-2330970");
                tadd.setText("شبين الكوم بجوار مستشفى شبين الكوم التعليمى");
            } else if (test.equals("a5")) {
                tname.setText("الجمعيه الشرعيه");
                tplace.setText("المنوفية");
                tnum.setText("1000 EL");
                ttel.setText("01091178045");
                tadd.setText("شبين الكوم بجوار بشاير الخير");
            }
        }catch (Exception e){
            Toast.makeText(Del.this, " eee" + e, Toast.LENGTH_LONG).show();


        }

    }


    @Override
    public void onConnected(Bundle bundle) {
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);
        if (mLastLocation != null) {
            xx= Double.valueOf(mLastLocation.getLatitude());
            yy= Double.valueOf(mLastLocation.getLongitude());
            // Toast.makeText(MapsActivity.this, "xx = "+xx+"  yy = "+yy, Toast.LENGTH_LONG).show();
            LatLng s0 = new LatLng(xx, yy);
            mMap.addMarker(new MarkerOptions().position(s0).title("أهلا بك"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(s0));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(s0, 8));



        }    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Toast.makeText(Del.this, "Ready Map", Toast.LENGTH_LONG).show();

        LatLng s1 = new LatLng(30.5541156, 31.012626);
        mMap.addMarker(new MarkerOptions().position(s1).title("حضانة النور1")
                .snippet("شبين الكوم عمر برج الراية أضغط هنا للمزيد")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon)));


    }
    protected synchronized void buildGoogleApiClient() {mGoogleApiClient = new GoogleApiClient.Builder(this).addConnectionCallbacks(this).addOnConnectionFailedListener(this).addApi(LocationServices.API).build();mGoogleApiClient.connect();}

}