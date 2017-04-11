 package com.mohamed.rami.firebase;


 import android.Manifest;
 import android.app.AlertDialog;
 import android.content.DialogInterface;
 import android.content.Intent;
 import android.content.pm.PackageManager;
 import android.graphics.Color;
 import android.location.Location;
 import android.location.LocationManager;
 import android.os.Build;
 import android.os.Bundle;
 import android.support.annotation.Nullable;
 import android.support.v4.app.FragmentActivity;
 import android.support.v4.content.ContextCompat;
 import android.util.Log;
 import android.widget.Toast;

 import com.akexorcist.googledirection.DirectionCallback;
 import com.akexorcist.googledirection.GoogleDirection;
 import com.akexorcist.googledirection.model.Direction;
 import com.akexorcist.googledirection.model.Leg;
 import com.akexorcist.googledirection.util.DirectionConverter;
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
 import com.google.android.gms.maps.model.PolylineOptions;
 import com.google.firebase.auth.FirebaseAuth;
 import com.google.firebase.database.DataSnapshot;
 import com.google.firebase.database.DatabaseError;
 import com.google.firebase.database.DatabaseReference;
 import com.google.firebase.database.FirebaseDatabase;
 import com.google.firebase.database.ValueEventListener;

 import java.util.ArrayList;
 import java.util.HashMap;


 public class guidemaps extends FragmentActivity implements OnMapReadyCallback,
         GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener,LocationListener{


     private GoogleMap mMap;
      String id = null;
     private  static GoogleApiClient googleApiClient;

     Location mLastLocation;

     double xx,yy,xnear,ynear;
     GoogleMap googleMap;


     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.guidemaps);
          SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                 .findFragmentById(R.id.map);
         mapFragment.getMapAsync(this);
         LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
         if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            // Toast.makeText(this, "GPS is Enabled in your device", Toast.LENGTH_SHORT).show();
         }else{
             showGPSDisabledAlertToUser();
         }
         if (googleApiClient == null) {
             googleApiClient = new GoogleApiClient.Builder(this)
                     .addConnectionCallbacks(this)
                     .addOnConnectionFailedListener(this)
                     .addApi(LocationServices.API)
                     .build();
         }

     }
//////////////////////////////////////////////
     public double getx(){return xx;}
     public void setx(double x){
         this.xx=x;
     }
     public double gety(){return yy;}
     public void sety(double y){
         this.yy=y;
     }
/////////////////////////////////////////////

/////////////////////////////////////////////////

     @Override
     public void onConnected(@Nullable Bundle bundle) {


         try {
              //googleMap.setMyLocationEnabled(true);
             LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);


             mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                     googleApiClient);


             if (mLastLocation != null) {

                 setx(mLastLocation.getLatitude());

                 sety(mLastLocation.getLongitude());

                 LatLng s = new LatLng( getx(), gety());
//                 Toast.makeText(this, "x="+getx() +"  y="+gety(), Toast.LENGTH_SHORT).show();

                mMap.addMarker(new MarkerOptions().position(s).title("أهلا بك"));
            ///     Toast.makeText(this, "7", Toast.LENGTH_SHORT).show();

                 mMap.moveCamera(CameraUpdateFactory.newLatLng(s));
                 mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(s, 12));

                 /////////////////////////////////

                 /////////////////////////////////////////
                 GoogleDirection.withServerKey(getResources().getString(R.string.google_maps_key))
                         .from(new LatLng(getx(),gety()))
                         .to(new LatLng(30.5499017,31.0138393))
                         .execute(new DirectionCallback() {
                             @Override
                             public void onDirectionSuccess(Direction direction, String rawBody) {
                                 if (direction.isOK()) {
                                     Leg leg = direction.getRouteList().get(0).getLegList().get(0);
                                     ArrayList<LatLng> directionpositionlist = leg.getDirectionPoint();
                                     PolylineOptions polylineOptions = DirectionConverter.createPolyline(guidemaps.this, directionpositionlist, 10, Color.red(552299));
                                     mMap.addPolyline(polylineOptions
                                             .width(5)
                                             .color(Color.BLACK)
                                     );
                                 //    Toast.makeText(guidemaps.this, "dooonnneee", Toast.LENGTH_SHORT).show();
                                 } else {
                                //     Toast.makeText(guidemaps.this, "eeelseee ", Toast.LENGTH_SHORT).show();

                                     GoogleDirection.withServerKey(getResources().getString(R.string.google_maps_key))
                                             .from(new LatLng(getx(), gety()))
                                             .to(new LatLng(30.5499017,31.0138393))
                                             .execute(new DirectionCallback() {
                                                 @Override
                                                 public void onDirectionSuccess(Direction direction, String rawBody) {
                                                     if (direction.isOK()) {
                                                         Leg leg = direction.getRouteList().get(0).getLegList().get(0);
                                                         ArrayList<LatLng> directionpositionlist = leg.getDirectionPoint();
                                                         PolylineOptions polylineOptions = DirectionConverter.createPolyline(guidemaps.this, directionpositionlist, 10, Color.red(552299));
                                                         mMap.addPolyline(polylineOptions
                                                                 .width(5)
                                                                 .color(Color.BLACK)
                                                         );
                                                       //  Toast.makeText(guidemaps.this, "dooonnneee", Toast.LENGTH_SHORT).show();
                                                     } else {


                                                     }
                                                 }

                                                 @Override
                                                 public void onDirectionFailure(Throwable t) {
                                                     Toast.makeText(guidemaps.this, "يرجي التاكد من وجود شبكة الانترنت ", Toast.LENGTH_SHORT).show();

                                                 }
                                             });

                                 }
                             }

                             @Override
                             public void onDirectionFailure(Throwable t) {
                             }
                         });










             }
         }catch (Exception e){
             Toast.makeText(guidemaps.this, "ee"+e, Toast.LENGTH_SHORT).show();
             Log.d("tag","wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww yad "+e);
         }

     }


     @Override
     public void onMapReady(GoogleMap googleMap) {

try {

   }catch (Exception e){

    Toast.makeText(guidemaps.this, "qq"+e, Toast.LENGTH_SHORT).show();
    Log.d("tag","qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqyad "+e);

}

     }

     protected synchronized void buildGoogleApiClient() {googleApiClient = new GoogleApiClient.Builder(this).addConnectionCallbacks(this).addOnConnectionFailedListener(this).addApi(LocationServices.API).build();googleApiClient.connect();}




     @Override
     public void onLocationChanged(Location location) {
              }

     @Override
     public void onConnectionSuspended(int i) {}
     @Override
     public void onConnectionFailed(ConnectionResult connectionResult) {}
     @Override
     public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {}
     @Override
     public void onStart() {googleApiClient.connect();super.onStart();}
     @Override
     public void onStop() {googleApiClient.disconnect();super.onStop();}

    //   ALART FOR NO GPS
     private void showGPSDisabledAlertToUser(){
         AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
         alertDialogBuilder.setMessage("GPS is disabled in your device. Would you like to enable it?")
                 .setCancelable(false)
                 .setPositiveButton("Goto Settings Page To Enable GPS",
                         new  DialogInterface.OnClickListener(){
                             public void onClick(DialogInterface dialo, int id){
                                 Intent callGPSSettingIntent = new Intent(
                                         android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                 startActivity(callGPSSettingIntent);
                             }
                         });
         alertDialogBuilder.setNegativeButton("Cancel",
                 new DialogInterface.OnClickListener(){
                     public void onClick(DialogInterface dialog, int id){
                         dialog.cancel();
                     }
                 });
         AlertDialog alert = alertDialogBuilder.create();
         alert.show();
     }


 }