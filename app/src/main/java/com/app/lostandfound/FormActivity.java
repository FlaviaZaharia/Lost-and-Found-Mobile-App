package com.app.lostandfound;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.UUID;

public class FormActivity extends AppCompatActivity {
    private Button b,open_camera;
    private EditText category,phone,description,name;
    private TextView error;
    private ImageView img;
    private static final int pic_id=123;
    private FirebaseStorage storage= FirebaseStorage.getInstance();;
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
                // Handle unsuccessful uploads
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
                // ...
            }
        });
    }

}
