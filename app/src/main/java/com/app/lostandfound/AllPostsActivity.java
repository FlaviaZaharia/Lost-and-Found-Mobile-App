package com.app.lostandfound;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.GridView;

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
    private FirebaseFirestore db=FirebaseFirestore.getInstance();
    private static final String TAG="";
    private ArrayList<PostClass> list= new ArrayList<PostClass>();
   protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_all_posts);
       postsGV = findViewById(R.id.idGVpost);
       SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
       String email = preferences.getString("email", "");
       getPostsFromDB(email);
   }
   private void  getPostsFromDB(String currentEmail){
       db.collection("Posts")
               .whereEqualTo("email",currentEmail)
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
                               list.add(new PostClass(categ,url,name,desc,phone));
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
