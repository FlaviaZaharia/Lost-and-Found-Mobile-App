package com.app.lostandfound;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MyPostsActivity extends AppCompatActivity {
    private ImageButton back;
    private TextView filter1,filter2;
    private RecyclerView container;
    LinearLayoutManager layoutManager;
    List<PostModel> posts;
    com.app.lostandfound.Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_posts);
        back=findViewById(R.id.back3);
        filter1=findViewById(R.id.filter1);
        filter2=findViewById(R.id.filter2);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MyPostsActivity.this,UserProfileActivity.class);
                startActivity(i);
            }
        });
        initData();
        initRecyclerView();
    }
    private void initRecyclerView(){
        container=findViewById(R.id.container);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        container.setLayoutManager(layoutManager);
        adapter.setPosts(posts);
        container.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
    private void initData() {
        posts=new ArrayList<>();

    }
}