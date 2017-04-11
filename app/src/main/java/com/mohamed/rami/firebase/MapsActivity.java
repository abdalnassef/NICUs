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

 import java.io.Serializable;
 import java.text.DecimalFormat;
 import java.util.ArrayList;
 import java.util.HashMap;


 public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,
         GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener,LocationListener{
     private  String[] na=new String[100];

     private  String[] address=new String[100];

     private  String[] phone=new String[100];

     private  String[] count=new String[100];

     private  String[] price =new String[100];

     private  String[] AIRPORT1=new String[100];

     private  String[] TIME= new String[100];

     private  String[] PRICE=new String[100];

     int special;


     float neardistance  =999999999;
     int chek=0;
     Location myLocation ;
     Location destination;
     ArrayList<String> nicuNames = new ArrayList<>();
     ArrayList<String> nicuAddresses = new ArrayList<>();
     ArrayList<String> niucCount = new ArrayList<>();
     ArrayList<String> nicuPrices = new ArrayList<>();
     ArrayList<String> nicuPhones = new ArrayList<>();
     ArrayList<String> nicuUpdate = new ArrayList<>();
     ArrayList<String> nicuLat = new ArrayList<>();
     ArrayList<String> nicuLng = new ArrayList<>();
     ArrayList<String> nicuDes = new ArrayList<>();
     SettersGetters post;
     private GoogleMap mMap;
     HashMap<String, String> markerMap = new HashMap<String, String>();
     String id = null;
     private  static GoogleApiClient googleApiClient;
     GoogleApiClient mGoogleApiClient;
     Location mLastLocation;
     Marker mCurrLocationMarker;
     LocationRequest mLocationRequest;
     String x1,y1;
     double xx,yy,xnear,ynear;
     long counter;
     private FirebaseAuth fire,firebase;
     private FirebaseAuth mFirebaseAuth;
     private DatabaseReference mRef;
     int a = 0,b=0;

     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_maps);
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
     public double getxnear(){return xnear;}
     public void setxnear(double x) {this.xnear=x;}
     public double getynear(){return ynear;}
     public void setynear(double y){
         this.ynear=y;
     }
/////////////////////////////////////////////////

     @Override
     public void onConnected(@Nullable Bundle bundle) {


         try {
             mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                     mGoogleApiClient);
             if (mLastLocation != null) {
                 setx(mLastLocation.getLatitude());
                 sety(mLastLocation.getLongitude());
                 LatLng s0 = new LatLng(getx(), gety());
                 mMap.addMarker(new MarkerOptions().position(s0).title("أهلا بك"));
                 mMap.moveCamera(CameraUpdateFactory.newLatLng(s0));
                 mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(s0, 12));

                 /////////////////////////////////

                 /////////////////////////////////////////
                 GoogleDirection.withServerKey(getResources().getString(R.string.google_maps_key))
                         .from(new LatLng(getx(), gety()))
                         .to(new LatLng(getxnear(), getynear()))
                         .execute(new DirectionCallback() {
                             @Override
                             public void onDirectionSuccess(Direction direction, String rawBody) {
                                 if (direction.isOK()) {
                                     Leg leg = direction.getRouteList().get(0).getLegList().get(0);
                                     ArrayList<LatLng> directionpositionlist = leg.getDirectionPoint();
                                     PolylineOptions polylineOptions = DirectionConverter.createPolyline(MapsActivity.this, directionpositionlist, 10, Color.red(552299));
                                     mMap.addPolyline(polylineOptions
                                             .width(5)
                                             .color(Color.BLACK)
                                     );
                                  } else {
                                     GoogleDirection.withServerKey(getResources().getString(R.string.google_maps_key))
                                             .from(new LatLng(getx(), gety()))
                                             .to(new LatLng(getxnear(), getynear()))
                                             .execute(new DirectionCallback() {
                                                 @Override
                                                 public void onDirectionSuccess(Direction direction, String rawBody) {
                                                     if (direction.isOK()) {
                                                         Leg leg = direction.getRouteList().get(0).getLegList().get(0);
                                                         ArrayList<LatLng> directionpositionlist = leg.getDirectionPoint();
                                                         PolylineOptions polylineOptions = DirectionConverter.createPolyline(MapsActivity.this, directionpositionlist, 10, Color.red(552299));
                                                         mMap.addPolyline(polylineOptions
                                                                 .width(5)
                                                                 .color(Color.BLACK)
                                                         );
                                                      } else {


                                                     }
                                                 }
                                                 @Override
                                                 public void onDirectionFailure(Throwable t) {
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
             Toast.makeText(MapsActivity.this, "network falied "+e, Toast.LENGTH_SHORT).show();
         }

     }


     @Override
     public void onMapReady(GoogleMap googleMap) {
         try {
             mMap = googleMap;
             mRef = FirebaseDatabase.getInstance().getReference();
             mRef.child("User").addValueEventListener(new ValueEventListener() {

                 @Override
                 public void onDataChange(DataSnapshot dataSnapshot) {
//                     counter = dataSnapshot.getChildrenCount();
//                     myLocation = new Location("");
//                     destination = new Location("");
//                     myLocation.setLatitude(getx());
//                     destination.setLongitude(gety());
//                     Toast.makeText(MapsActivity.this, getx() + " " + gety(), Toast.LENGTH_LONG).show();
                     for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                         post = postSnapshot.getValue(SettersGetters.class);
                         nicuNames.add(post.getNicuName());
                         na[b] = post.getNicuName();
                         address[b] = post.getNicuAddress();
                         phone[b] = post.getNicuPhone();
                         price[b] = post.getNicuPrice();
                         count[b] = post.getNicuCount();
                         nicuAddresses.add(post.getNicuAddress());
                         niucCount.add(post.getNicuCount());
                         nicuPrices.add(post.getNicuCount());
                         nicuPhones.add(post.getNicuPhone());
                         nicuUpdate.add(post.getNicuUpdate());
                         nicuLat.add(post.getNicuLat());
                         nicuLng.add(post.getNicuLng());
                         nicuDes.add(post.getNicuDesc());
                         LatLng s1 = new LatLng(Double.parseDouble(nicuLat.get(a)), Double.parseDouble(nicuLng.get(a)));
                         mMap.addMarker(new MarkerOptions().position(s1).title(nicuNames.get(a))
                                 .snippet(nicuDes.get(a) + " للمزيد أضغط هنا ")
                                 .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon)));

                         Location l1 = new Location("One");
                         l1.setLatitude(getx());
                         l1.setLongitude(gety());

                         Location l2 = new Location("Two");
                         l2.setLatitude( Double.parseDouble(nicuLat.get(a)));
                         l2.setLongitude(Double.parseDouble(nicuLng.get(a)));

                         float distance = l1.distanceTo(l2);
                         if (distance > 1000.0f) {
                             distance = distance / 1000.0f;
                          }

                          if (neardistance>distance){


                              neardistance=distance;
                              setxnear(Double.parseDouble(nicuLat.get(a)));
                              setynear(Double.parseDouble(nicuLng.get(a)));
                          }


                         a++;
                         b++;

                      }


                  }
                 @Override
                 public void onCancelled(DatabaseError databaseError) {
                 }
             });
             if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                 if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                     buildGoogleApiClient();
                     mMap.setMyLocationEnabled(true);
                 }
             } else {
                 buildGoogleApiClient();
                 mMap.setMyLocationEnabled(true);
             }
///////////////////////////////////////////////////////////////////////////
          /*   mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                 @Override
                 public void onInfoWindowClick(Marker marker)
                     try {
                         String m = marker.getId();
                         if (m.equals("m0")) {
                             Intent i = new Intent(MapsActivity.this, UserProfile.class);
                             i.putExtra("a", "a1");
                             startActivity(i);
                         } else if (m.equals("m2")) {
                             Intent i = new Intent(MapsActivity.this, UserProfile.class);
                             i.putExtra("a", "a1");
                             startActivity(i);
                         } else if (m.equals("m4")) {
                             Intent i = new Intent(MapsActivity.this, UserProfile.class);
                             i.putExtra("a", "a3");
                             startActivity(i);
                         } else if (m.equals("m6")) {
                             Intent i = new Intent(MapsActivity.this, UserProfile.class);
                             i.putExtra("a", "a4")
                             startActivity(i);
                         } else if (m.equals("m8")) {
                             Intent i = new Intent(MapsActivity.this, UserProfile.class);
                             i.putExtra("a", "a5");
                             startActivity(i);
                         } else {
                             Toast.makeText(MapsActivity.this, "اختر المكان الصحيح ", Toast.LENGTH_LONG).show();
                         }
                     } catch (Exception e) {
                         Toast.makeText(MapsActivity.this, " " + e, Toast.LENGTH_LONG).show();
                     }
                 }
             });*/

             mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                 @Override
                 public void onInfoWindowClick(Marker marker)
                 {
                     int id=0;
                     String name = marker.getTitle();
                     for(int u=0;u<na.length;u++){
                         if (name.equals(na[u])){
                             id=u;

                         }

                     }
                     Intent n =new Intent(MapsActivity.this, UserProfile.class);
                     n.putExtra("name", na);
                     n.putExtra("price",price);
                     n.putExtra("phone",phone);
                     n.putExtra("address",address);
                     n.putExtra("des",count);
                     n.putExtra("pos",id);
                     startActivity(n);

                 }






             });






             }catch (Exception e){


         }

     }

     protected synchronized void buildGoogleApiClient() {mGoogleApiClient = new GoogleApiClient.Builder(this).addConnectionCallbacks(this).addOnConnectionFailedListener(this).addApi(LocationServices.API).build();mGoogleApiClient.connect();}




     @Override
     public void onLocationChanged(Location location) {
        /* mLastLocation = location;
         if (mCurrLocationMarker != null) {
             mCurrLocationMarker.remove();
         }

         //Place current location marker
         LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
         MarkerOptions markerOptions = new MarkerOptions();
         markerOptions.position(latLng);
         markerOptions.title("Current Position");
         markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.google));
      //   mCurrLocationMarker = mMap.addMarker(markerOptions);
        // mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
         //Toast.makeText(this, "animateCameraanimaraanimateCamera", Toast.LENGTH_SHORT).show();

//         mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));

         /*
        LatLng s2 = new LatLng(30.5269969, 31.0240747);

         mMap.addMarker(new MarkerOptions().position(s2).title(" حضانه 2 ")
                 .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon)));
         mMap.moveCamera(CameraUpdateFactory.newLatLng(s2));
*/
         //move map camera
       //  mMap.animateCamera(CameraUpdateFactory.zoomTo(5));

         //stop location updates
        /* if (mGoogleApiClient != null) {
             LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
         }*/
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

     public float getDistance(LatLng my_latlong, LatLng frnd_latlong) {
         Location l1 = new Location("One");
         l1.setLatitude(my_latlong.latitude);
         l1.setLongitude(my_latlong.longitude);

         Location l2 = new Location("Two");
         l2.setLatitude(frnd_latlong.latitude);
         l2.setLongitude(frnd_latlong.longitude);

         float distance = l1.distanceTo(l2);
         String dist = distance + " M";

         if (distance > 1000.0f) {
             distance = distance / 1000.0f;
             dist = distance + " KM";
         }
         return distance;
     }
 }