package com.app.lostandfound;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

public class IndividualFoundActivity extends AppCompatActivity {
    private TextView category,categoryValue,name,nameValue,locationValue,location,description,descValue;
    private ImageView img;
    private ImageButton b;
    private Button contact;
    private AlertDialog.Builder builder;
    private String email;
    private FirebaseFirestore db=FirebaseFirestore.getInstance();
    private static final String TAG="individualActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
        builder = new AlertDialog.Builder(this);
        contact=findViewById(R.id.contact);
        Intent i=getIntent();
        String aux=i.getStringExtra("category");
        categoryValue.setText(aux);
        aux=i.getStringExtra("name");
        nameValue.setText(aux);
        aux=i.getStringExtra("location");
        locationValue.setText(aux);
        aux=i.getStringExtra("url");
        email=i.getStringExtra("email");
        if(!aux.equals(""))
            Picasso.get().load(aux).into(img);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(IndividualFoundActivity.this,AllPostsActivity.class);
                startActivity(i);
            }
        });
    }
}