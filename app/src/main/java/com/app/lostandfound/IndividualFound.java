package com.app.lostandfound;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class IndividualFound extends AppCompatActivity {
    TextView category,categoryValue,name,nameValue,locationValue,location;
    ImageView img;
    ImageButton b;
    Button toDescription;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_found);
        category=findViewById(R.id.textViewcategory);
        name=findViewById(R.id.textViewname);
        location=findViewById(R.id.textViewlocation);
        locationValue=findViewById(R.id.locationValue);
        categoryValue=findViewById(R.id.categoryValue);
        nameValue=findViewById(R.id.nameValue);
        img=findViewById(R.id.imagePic);
        b=findViewById(R.id.back);
        toDescription=findViewById(R.id.toDescription);
        Intent i=getIntent();
        String aux=i.getStringExtra("category");
        categoryValue.setText(aux);
        aux=i.getStringExtra("name");
        nameValue.setText(aux);
        aux=i.getStringExtra("location");
        locationValue.setText(aux);
        String aux1=i.getStringExtra("url");
        if(!aux1.equals(""))
            Picasso.get().load(aux1).into(img);

        ArrayList<String> descs=i.getStringArrayListExtra("d");
        String UID=i.getStringExtra("uid");
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(IndividualFound.this,AllPostsActivity.class);
                startActivity(i);
            }
        });
        toDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(IndividualFound.this,AddDescription.class);
                i.putExtra("d",descs);
                i.putExtra("uid",UID);
                i.putExtra("url",aux1);
                startActivity(i);
            }
        });
    }
}
