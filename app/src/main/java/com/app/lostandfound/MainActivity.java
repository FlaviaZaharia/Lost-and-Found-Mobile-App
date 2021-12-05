package com.app.lostandfound;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private static int TIME_OUT = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final View layout=findViewById(R.id.app_title);
       new Handler().postDelayed(new Runnable(){
           public void run(){
               Intent i=new Intent(MainActivity.this,LoginActivity.class);
               startActivity(i);
               finish();
           }
       },TIME_OUT);
    }
}