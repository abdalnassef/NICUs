package com.mohamed.rami.firebase;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class UserProfile extends AppCompatActivity {

    TextView phone,NAME,Address,Phone,Price,Empty ;
    ImageView im;
    TextView Desc;
    private  String[] name=new String[100];

    private  String[] address=new String[100];

    private  String[] phon=new String[100];

    private  String[] des=new String[100];

    private  String[] price =new String[100];
    private  String[] empty =new String[100];

    int pos;
    String tempid;
    private  String[] AIRPORT1=new String[100];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        im = (ImageView) findViewById(R.id.nicuImageee);
        Intent in = getIntent();
        name = in.getStringArrayExtra("name");
        address = in.getStringArrayExtra("address");
        phon = in.getStringArrayExtra("phone");
        des = in.getStringArrayExtra("des");
        pos = in.getIntExtra("pos",0);
        price = in.getStringArrayExtra("price");
       // tempid=in.getStringExtra("a");
        for(int x = 0 ; x < 10 ; x++){
            name[x] = name[x];
            address[x] = address[x];
            phon[x] = phon[x];
            des[x] = des[x];
            price[x]=price[x];
        }


        if(pos == 1){
            im.setBackgroundResource(R.drawable.main);
        }else if(pos == 2){
            im.setBackgroundResource(R.drawable.im);
        }else if(pos == 3){
            im.setBackgroundResource(R.drawable.imm);
        }else if(pos == 4){
            im.setBackgroundResource(R.drawable.immm);
        }else if(pos == 5){
            im.setBackgroundResource(R.drawable.immmm);
        }else if(pos == 6){
            im.setBackgroundResource(R.drawable.immmmm);
        }else if(pos == 7){
            im.setBackgroundResource(R.drawable.immmmmm);
        }else{
            im.setBackgroundResource(R.drawable.immmm);
        }
        NAME = (TextView) findViewById(R.id.nnnnnn);
        Address = (TextView) findViewById(R.id.address);
        Desc = (TextView) findViewById(R.id.desc);
        Price = (TextView) findViewById(R.id.price);
        phone = (TextView) findViewById(R.id.phone);
        Empty = (TextView) findViewById(R.id.empty);
        NAME.setText("حضانة : "+name[pos]);
        Address.setText("العنوان : "+address[pos]);
        Empty.setText("عدد الحضانات  : "+des[pos]);
        Price.setText("السعر : "+price[pos]);
        phone.setText(phon[pos]);
        NAME.setText(name[pos]);
        final String number = "tel:"+phone.getText().toString();

        phone.setTextColor(getResources().getColor(R.color.colorPrimary));
        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse(number));
                startActivity(callIntent);
            }
        });

    }
    public void guideme (View view){
        Intent x=new Intent(UserProfile.this,guidemaps.class);
        startActivity(x);
    }

}
