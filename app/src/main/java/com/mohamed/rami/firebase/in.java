package com.mohamed.rami.firebase;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;

/**

 */

public class in extends AppCompatActivity implements Serializable , GoogleApiClient.OnConnectionFailedListener,View.OnClickListener{


    private  String[] DEPARTURE=new String[100];

    private  String[] CITY=new String[100];

    private  String[] AIRPORT=new String[100];

    private  String[] DEPARTURE1=new String[100];

    private  String[] CITY1=new String[100];

    private  String[] AIRPORT1=new String[100];

    private  String[] TIME= new String[100];

    private  String[] PRICE=new String[100];
    private static final int RC_SIGN_IN = 9001;

    String userName , pass;

    //User Data must be retreaved Here
    //nicuName,nicuAddress,nicuPhone,nicuPrice,,nicuCount,lastUpdate,lat,lng,nicuDes
    ArrayList<String> nicuNames = new ArrayList<>();
    ArrayList<String> userNames = new ArrayList<>();
    ArrayList<String> Passwords = new ArrayList<>();
    ArrayList<String> nicuAddresses = new ArrayList<>();
    ArrayList<String> niucCount = new ArrayList<>();
    ArrayList<String> nicuPrices = new ArrayList<>();
    ArrayList<String> nicuPhones = new ArrayList<>();
    ArrayList<String> nicuUpdate = new ArrayList<>();
    ArrayList<String> nicuLat = new ArrayList<>();
    ArrayList<String> nicuLng = new ArrayList<>();
    ArrayList<String> nicuDes = new ArrayList<>();
    long counter ;


    DatabaseReference db;
    TextView t1,t2,signIn,gmail ;
    final FirebaseDatabase myref = FirebaseDatabase.getInstance();
    FirebaseHelper firebaseHelper;
    Intent intent;
    private ProgressDialog progressDialog;
    EditText email,password;
    TextView viewNicu;
    private FirebaseAuth fire,firebase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private GoogleApiClient mGoogleApiClient;
    private DatabaseReference mRef,cRef;
    private FirebaseUser mFirebaseUser;
    ImageView google;
    int a =0;
    String user,pa,userID;

    SettersGetters settersGetters;
    ArrayList<SettersGetters> data = new ArrayList<>();
    public boolean isOnline() {
        ConnectivityManager conMgr = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();

        if(netInfo == null || !netInfo.isConnected() || !netInfo.isAvailable()){
            Toast.makeText(in.this, "No Internet connection!", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
    @Override
    protected void onCreate (Bundle saveInstansBundle) {
        super.onCreate(saveInstansBundle);
        if(isOnline()) {
            setContentView(R.layout.signin);
            // Configure Google Sign In
            GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken(getString(R.string.default_web_client_id))
                    .requestEmail()
                    .build();
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .enableAutoManage(this,this)
                    .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                    .build();
            mAuth = FirebaseAuth.getInstance();
            mAuthListener = new FirebaseAuth.AuthStateListener(){
                @Override
                public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                    FirebaseUser user = firebaseAuth.getCurrentUser();
                    if(user !=null){
                        Log.d("Test","onAuthStateChanged:signed In: "+user.getUid());
                    }else
                        Log.d("Test","onAuthStateChanged:signed Out: ");
                }
            };
            viewNicu = (TextView) findViewById(R.id.findNicuaa);
            gmail = (TextView) findViewById(R.id.gmail);
            settersGetters = new SettersGetters();
            mRef = FirebaseDatabase.getInstance().getReference();
            cRef = FirebaseDatabase.getInstance().getReference();

            //firebaseHelper = new FirebaseHelper(db);

            //Toast.makeText(in.this,data.get(0).toString(),Toast.LENGTH_SHORT).show();
            //fire = FirebaseAuth.getInstance();
            //mFirebaseUser = fire.getCurrentUser();
            //userID = mFirebaseUser.getUid();

            //firebase = FirebaseAuth.getInstance();
            email = (EditText) findViewById(R.id.tt1);
            password = (EditText) findViewById(R.id.tt2);
            signIn = (TextView) findViewById(R.id.signin1);
            google = (ImageView) findViewById(R.id.google);

            userName = email.getText().toString();
            pass = password.getText().toString();
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

            cRef.child("Login").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    a = 0;
                    users user = new users();
                    counter = dataSnapshot.getChildrenCount();
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        //nicuName,nicuAddress,nicuPhone,nicuPrice,,nicuCount,lastUpdate,lat,lng,nicuDes
                        user = postSnapshot.getValue(users.class);

                        userNames.add(user.getUserName());
                        Passwords.add(user.getPassword());
                        Toast.makeText(in.this,user.getUserName(),Toast.LENGTH_SHORT).show();
                        a++;//System.out.println(nicuNames.get(0));
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


            signIn.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                   if(check()) {
                       Intent i = new Intent(in.this, MainForList.class);
                       i.putExtra("name", DEPARTURE);
                       i.putExtra("address", CITY);
                       i.putExtra("phone", AIRPORT);
                       i.putExtra("des", TIME);
                       i.putExtra("price", PRICE);
                       startActivity(i);
                   }
                    else{
                       Toast.makeText(in.this,"Invalid Email or password",Toast.LENGTH_SHORT).show();
                   }


                }
            });
        /*signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  Toast.makeText(in.this,nicuNames.get(0)+" "+nicuAddresses.get(0),Toast.LENGTH_SHORT).show();
                if(TextUtils.isEmpty(email.getText().toString())){
                    Toast.makeText(in.this,"Please Enter User Name",Toast.LENGTH_LONG).show();
                }if(TextUtils.isEmpty(password.getText().toString())){
                    Toast.makeText(in.this,"Please Enter Your Password",Toast.LENGTH_LONG).show();
                }else{
                        if (check()) {
                            Toast.makeText(in.this, nicuNames.get(0),Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(in.this, MapsActivity.class);
                            startActivity(i);
                        } else {
                            Toast.makeText(in.this, "User Name or Password is incorrect", Toast.LENGTH_LONG).show();
                        }
                }
            }
        });*/
            viewNicu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(in.this, nicuNames.get(0).toString()+" ", Toast.LENGTH_LONG).show();
                    //Toast.makeText(in.this,a+" ", Toast.LENGTH_LONG).show();

                    SettersGetters post = new SettersGetters();
                    //intent.putExtra("name",post.getNicuName());
                    //Toast.makeText(in.this,nicuNames.get(0)+" "+post.getNicuName()+" "+a,Toast.LENGTH_SHORT).show();
                    //startActivity(intent);

                }
            });
        }else{


            final AlertDialog.Builder dialog = new AlertDialog.Builder(this).setTitle("No Internet Connection").setMessage("Please check your internet connection then try again ");
            dialog.setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int whichButton) {
                    System.exit(0);
                }
            });
            final AlertDialog alert = dialog.create();
            alert.show();

// Hide after some seconds
            final Handler handler  = new Handler();
            final Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    if (alert.isShowing()) {
                        alert.dismiss();

                    }
                }
            };

            alert.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    handler.removeCallbacks(runnable);
                }
            });

            handler.postDelayed(runnable, 10000);


        }
        gmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });

    }



    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RC_SIGN_IN){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if(result.isSuccess()){
                GoogleSignInAccount account = result.getSignInAccount();
                firebaseAuthWithGoogle(account);
                //Toast.makeText(in.this,"google Play Service",Toast.LENGTH_SHORT).show();
                Intent ii = new Intent(in.this,MapsActivity.class);
                startActivity(ii);
                //Toast.makeText(in.this,"google Play Service Error",Toast.LENGTH_SHORT).show();
            }
            Intent ii = new Intent(in.this,MapsActivity.class);
            startActivity(ii);
        }
    }
    private void firebaseAuthWithGoogle(GoogleSignInAccount act){
        Toast.makeText(in.this,"dfdfdf"+act.getId(),Toast.LENGTH_SHORT).show();
        showProgressDialog();
        AuthCredential credential = GoogleAuthProvider.getCredential(act.getIdToken(),null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Toast.makeText(in.this,"Sign_in with Credintial Complete",Toast.LENGTH_SHORT).show();
                        if(!task.isSuccessful()){
                            Toast.makeText(in.this,"Sign_in with Credintial Complete"+task.getException(),Toast.LENGTH_SHORT).show();
                            Toast.makeText(in.this,"Authentication failed",Toast.LENGTH_SHORT).show();
                        }
                        hideProgressDialog();
                    }
                });
    }
    private void signIn(){
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent,RC_SIGN_IN);

    }
    private void signOut(){
        mAuth.signOut();
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {

            }
        });
    }
    public void showProgressDialog(){
        if(progressDialog == null){
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Loading...");
            progressDialog.setIndeterminate(true);
        }
        progressDialog.show();
    }
    public void hideProgressDialog(){
        if(progressDialog != null && progressDialog.isShowing()){
            progressDialog.hide();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        hideProgressDialog();
    }

    public boolean check(){
    boolean res = false;
    for(int x = 0 ; x < counter;x++){
        if (email.getText().toString().equals(userNames.get(x)) && password.getText().toString().equals(Passwords.get(x))){
            res = true;
            break;
        }
        else
            res = false;
    }
    return res;
}



    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
    }

    public void info (View view){
        Intent x=new Intent(in.this,MapsActivity.class);
        startActivity(x);
    }

    public void maps (View view){
        Intent xx=new Intent(in.this,MapsActivity.class);
        startActivity(xx);
    }

    public void goup (View view){
        Intent x=new Intent(in.this,NicuTab.class);
        startActivity(x);
    }


    @Override
    public void onClick(View v) {


    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

        Log.d("Test","onConnectionFailed: "+connectionResult);
        Toast.makeText(in.this,"google Play Service Error",Toast.LENGTH_SHORT).show();
    }
}
