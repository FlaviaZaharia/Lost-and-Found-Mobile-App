package com.app.lostandfound;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class LostFormActivity extends AppCompatActivity {
    private Button b;
    private static final String TAG="";
    private EditText category,phone,description,name,location;
    private TextView error;
    private FirebaseFirestore db=FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_lost);
        category = findViewById(R.id.category);
        name = findViewById(R.id.itemName);
        description = findViewById(R.id.description);
        phone = findViewById(R.id.phoneNr);
        location=findViewById(R.id.location);
        error = findViewById(R.id.error);
        b = findViewById(R.id.post);

        b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                submitPost();
            }
        });
    }

    private void submitPost(){
        String categoryInput,descriptionInput,phoneInput,nameInput,locationInput;
        categoryInput=category.getText().toString();
        nameInput=name.getText().toString();
        descriptionInput=description.getText().toString();
        phoneInput= phone.getText().toString();
        locationInput=location.getText().toString();
        if(TextUtils.isEmpty(categoryInput)|| TextUtils.isEmpty(nameInput)||TextUtils.isEmpty(descriptionInput)||TextUtils.isEmpty(phoneInput)||TextUtils.isEmpty(locationInput)) {
            error.setText("Please fill in all the fields");
            return;
        }
        else
        {
            addPostToDatabase();
            Intent i=new Intent(LostFormActivity.this,MenuActivity.class);
            startActivity(i);
        }
    }


    private void addPostToDatabase(){
        String categoryText,nameText,descriptionText,phoneText,locationText;
        categoryText=category.getText().toString();
        //get user email
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String email = preferences.getString("email", "");
        nameText=name.getText().toString();
        descriptionText=description.getText().toString();
        phoneText=phone.getText().toString();
        locationText=location.getText().toString();
        Map<String,Object> post=new HashMap<>();
        post.put("email",email);
        post.put("category",categoryText);
        post.put("name",nameText);
        post.put("description",descriptionText);
        post.put("phone",phoneText);
        post.put("status","lost");
        post.put("image","");
        post.put("location",locationText);
        db.collection("Posts").add(post)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });
    }



}
