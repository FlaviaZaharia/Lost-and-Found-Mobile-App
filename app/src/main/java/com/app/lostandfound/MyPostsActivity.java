package com.app.lostandfound;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MyPostsActivity extends AppCompatActivity {
    private ImageButton back;
    private TextView filter1,filter2;
    private GridView container;
    private String email;
    private FirebaseFirestore db= FirebaseFirestore.getInstance(); ;
    private ArrayList<PostModel> posts=new ArrayList<PostModel>();
    private static final String TAG = "MyPosts";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_posts);
        back=findViewById(R.id.back3);
        filter1=findViewById(R.id.filter1);
        filter2=findViewById(R.id.filter2);
        container=findViewById(R.id.container);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        email = preferences.getString("email", "");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MyPostsActivity.this,UserProfileActivity.class);
                startActivity(i);
            }
        });
//        filter1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                lost(email);
//
//            }
//        });
//        filter2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                found(email);
//            }
//        });
        initData(email);

    }

    private void initData(String email) {
        db.collection("Posts")
                .whereEqualTo("email", email)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                String title=document.get("name").toString();
                                String desc=document.get("description").toString();
                                String categ=document.get("category").toString();
                                String img=document.get("image").toString();
                                posts.add(new PostModel(desc,title,categ,img));

                            }
                            Adapter adapter=new Adapter(MyPostsActivity.this,posts);
                            container.setAdapter(adapter);

                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        } } }
                    );
    }

    private void lost(String email) {
        db.collection("Posts")
                .whereEqualTo("email", email)
                .whereEqualTo("status","lost")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                String title=document.get("name").toString();
                                String desc=document.get("description").toString();
                                String categ=document.get("category").toString();
                                String img=document.get("image").toString();
                                posts.add(new PostModel(desc,title,categ,img));

                            }
                            Adapter adapter=new Adapter(MyPostsActivity.this,posts);
                            container.setAdapter(adapter);

                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        } } }
                );
    }

    private void found(String email) {
        db.collection("Posts")
                .whereEqualTo("email", email)
                .whereEqualTo("status","found")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                String title=document.get("name").toString();
                                String desc=document.get("description").toString();
                                String categ=document.get("category").toString();
                                String img=document.get("image").toString();
                                posts.add(new PostModel(desc,title,categ,img));

                            }
                            Adapter adapter=new Adapter(MyPostsActivity.this,posts);
                            container.setAdapter(adapter);

                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        } } }
                );
    }

}