package com.mohamed.rami.firebase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class connect extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect);
    }

    public void send (View view){
        Toast.makeText(connect.this,"تم إرسال رسالتك ",Toast.LENGTH_SHORT).show();

        Intent x=new Intent(connect.this,choose.class);
        startActivity(x);
    }


}
