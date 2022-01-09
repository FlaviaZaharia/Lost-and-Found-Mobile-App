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

public class FormActivity extends AppCompatActivity {
    private Button b,open_camera,toMap;
    private static final String TAG="";
    private EditText category,phone,description,name;
    private TextView error;
    private ImageView img;
    private static final int pic_id=123;
    private FirebaseStorage storage= FirebaseStorage.getInstance();
    private FirebaseFirestore db=FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        category=findViewById(R.id.category);
        name=findViewById(R.id.itemName);
        description=findViewById(R.id.description);
        phone=findViewById(R.id.phoneNr);
        error=findViewById(R.id.error);
        b=findViewById(R.id.post);
        open_camera=findViewById(R.id.takePic);
        //toMap=findViewById(R.id.toMap);
        img=findViewById(R.id.imageView);

        open_camera.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent camera_intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(camera_intent,pic_id);
            }
        });
        b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                submitPost();
            }
        });
//        toMap.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i= new Intent(FormActivity.this,MapsActivity.class);
//                startActivity(i);
//            }
//        });
    }

    protected void onActivityResult(int requestCode,int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == pic_id) {

            // BitMap is data structure of image file
            // which stor the image in memory
            Bitmap photo = (Bitmap) data.getExtras()
                    .get("data");

            // Set the image in imageview for display
            img.setImageBitmap(photo);
        }
    }
    private boolean hasImage(@NonNull ImageView view) {
        Drawable drawable = view.getDrawable();
        boolean hasImage = (drawable != null);

        if (hasImage && (drawable instanceof BitmapDrawable)) {
            hasImage = ((BitmapDrawable)drawable).getBitmap() != null;
        }

        return hasImage;
    }
    private void submitPost(){
        String categoryInput,descriptionInput,phoneInput,nameInput;
        categoryInput=category.getText().toString();
        nameInput=name.getText().toString();
        descriptionInput=description.getText().toString();
        phoneInput= phone.getText().toString();
        if(TextUtils.isEmpty(categoryInput)|| TextUtils.isEmpty(nameInput)||TextUtils.isEmpty(descriptionInput)||TextUtils.isEmpty(phoneInput)) {
            error.setText("Please fill in all the fields");
            return;
        }
        else
        if(hasImage(img)==false){
            error.setText("Please provide an image");
        }
        else
        {
            uploadPhotoToFirebase();
            Intent i=new Intent(FormActivity.this,MainActivity.class);
            startActivity(i);
        }
    }
    private void uploadPhotoToFirebase() {
        StorageReference storageRef = storage.getReference();
        StorageReference photoRef = storageRef.child(UUID.randomUUID().toString() +name.getText().toString()+".jpg");

        img.setDrawingCacheEnabled(true);
        img.buildDrawingCache();
        Bitmap bitmap = ((BitmapDrawable) img.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();
        UploadTask uploadTask = photoRef.putBytes(data);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                Toast.makeText(getApplicationContext(),"Upload failed, please try again",Toast.LENGTH_LONG).show();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
               photoRef.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                   @Override
                   public void onComplete(@NonNull Task<Uri> task) {
                        String imgURL=task.getResult().toString();
                       addPostToDatabase(imgURL);
                   }
               });
            }
        });
    }

    private void addPostToDatabase(String img){
        String categoryText,nameText,descriptionText,phoneText;
        categoryText=category.getText().toString();
        //get user email
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String email = preferences.getString("email", "");
        nameText=name.getText().toString();
        descriptionText=description.getText().toString();
        phoneText=phone.getText().toString();
        Map<String,Object> post=new HashMap<>();
        post.put("email",email);
        post.put("category",categoryText);
        post.put("name",nameText);
        post.put("description",descriptionText);
        post.put("phone",phoneText);
        post.put("image",img);
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
