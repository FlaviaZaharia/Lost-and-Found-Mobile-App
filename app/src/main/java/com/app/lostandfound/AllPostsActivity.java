package com.app.lostandfound;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class AllPostsActivity extends AppCompatActivity {
    GridView postsGV;
    ImageButton b;
    private FirebaseFirestore db=FirebaseFirestore.getInstance();
    private static final String TAG="";
    private ArrayList<PostClass> list= new ArrayList<PostClass>();
   protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_all_posts);
       postsGV = findViewById(R.id.idGVpost);
       b=findViewById(R.id.back);
       b.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent i=new Intent(AllPostsActivity.this,MenuActivity.class);
               startActivity(i);
           }
       });
       SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
       String email = preferences.getString("email", "");
       getPostsFromDB();
   }
   private void  getPostsFromDB(){
       db.collection("Posts")
               //.whereEqualTo("email",currentEmail)
               .get()
               .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                   @Override
                   public void onComplete(@NonNull Task<QuerySnapshot> task) {

                       if (task.isSuccessful()) {
                           for (QueryDocumentSnapshot document : task.getResult()) {
                               String categ=document.get("category").toString();
                               String url=document.get("image").toString();
                               String name= document.getString("name");
                               String desc= document.getString("name");
                               String phone= document.getString("phone");
                               String location=document.getString("location");
                               String status= document.getString("status");
                               list.add(new PostClass(categ,url,name,desc,phone,location,status));
                           }
                           PostsGVAdapter adapter = new PostsGVAdapter(   AllPostsActivity.this,list);
                           postsGV.setAdapter(adapter);
                       } else {
                           Log.d(TAG, "Error getting documents: ", task.getException());
                       }
                   }
               });

   }
}
