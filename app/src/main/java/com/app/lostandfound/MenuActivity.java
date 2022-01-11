package com.app.lostandfound;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MenuActivity extends AppCompatActivity {
    private ImageButton button,back;
    private Button b1,b2,b3,logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        button=findViewById(R.id.profilePic);
        b1=findViewById(R.id.lost);
        b2=findViewById(R.id.found);
        b3=findViewById(R.id.allposts);
        back=findViewById(R.id.back);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                Intent i=new Intent(MenuActivity.this,UserProfileActivity.class);
                startActivity(i);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                Intent i=new Intent(MenuActivity.this,LoginActivity.class);
                startActivity(i);
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MenuActivity.this,LostFormActivity.class);
                startActivity(i);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MenuActivity.this, FoundFormActivity.class);
                startActivity(i);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MenuActivity.this,AllPostsActivity.class);
                startActivity(i);
            }
        });


    }
}