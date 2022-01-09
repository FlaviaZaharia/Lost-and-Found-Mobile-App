package com.app.lostandfound;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.firebase.firestore.auth.User;

public class UserProfileActivity extends AppCompatActivity {
    private Button b1,b2,b3,logout;
    private ImageButton back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        b1=findViewById(R.id.edit);
        back=findViewById(R.id.back2);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(UserProfileActivity.this,LoginActivity.class);
                startActivity(i);
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                Intent i=new Intent(UserProfileActivity.this,EditProfileActivity.class);
                startActivity(i);
            }
        }
        );
    }
}