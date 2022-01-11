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

public class IndividualPost extends AppCompatActivity {
    TextView category,categoryValue,name,nameValue;
    ImageView img;
    ImageButton b;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_post);
        category=findViewById(R.id.textViewcategory);
        name=findViewById(R.id.textViewname);
        categoryValue=findViewById(R.id.categoryValue);
        nameValue=findViewById(R.id.nameValue);
        img=findViewById(R.id.imagePic);
        b=findViewById(R.id.back);
        Intent i=getIntent();
        String aux=i.getStringExtra("category");
        categoryValue.setText(aux);
        aux=i.getStringExtra("name");
        nameValue.setText(aux);
        aux=i.getStringExtra("url");
        Picasso.get().load(aux).into(img);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(IndividualPost.this,AllPostsActivity.class);
                startActivity(i);
            }
        });
    }
}
