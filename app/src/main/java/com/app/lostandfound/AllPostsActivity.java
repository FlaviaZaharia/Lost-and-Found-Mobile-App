package com.app.lostandfound;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class AllPostsActivity extends AppCompatActivity {
    private Button b;
    private FirebaseFirestore db=FirebaseFirestore.getInstance();
    private static final String TAG="";
   protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_all_posts);
       b=findViewById(R.id.getData);
       SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
       String email = preferences.getString("email", "");
       getPostsFromDB(email);

   }
   private void getPostsFromDB(String currentEmail){
       db.collection("Posts")
               .whereEqualTo("email",currentEmail)
               .get()
               .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                   @Override
                   public void onComplete(@NonNull Task<QuerySnapshot> task) {
                       //int i=0;
                       if (task.isSuccessful()) {
                           for (QueryDocumentSnapshot document : task.getResult()) {
                               Log.d(TAG, document.getId() + " => " + document.getString("category")+" "+document.getString("email"));
                           }
                       } else {
                           Log.d(TAG, "Error getting documents: ", task.getException());
                       }
                   }
               });
   }
}
