package com.app.lostandfound;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class IndividualPost extends AppCompatActivity {
    private TextView category,categoryValue,name,nameValue,locationValue,location,description,descValue;
    private ImageView img;
    private ImageButton b;
    private Button contact;
    private AlertDialog.Builder builder;
    private String email;
    private FirebaseFirestore db=FirebaseFirestore.getInstance();
    private static final String TAG="individualActivity";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_post);
       // SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
       // email = preferences.getString("email", "");
        category=findViewById(R.id.textViewcategory);
        name=findViewById(R.id.textViewname);
        location=findViewById(R.id.textViewlocation);
        description=findViewById(R.id.textViewdescription);
        locationValue=findViewById(R.id.locationValue);
        categoryValue=findViewById(R.id.categoryValue);
        nameValue=findViewById(R.id.nameValue);
        descValue=findViewById(R.id.descriptionValue);
        img=findViewById(R.id.imagePic);
        b=findViewById(R.id.back);
        builder = new AlertDialog.Builder(this);
        contact=findViewById(R.id.contact);
        Intent i=getIntent();
        String aux=i.getStringExtra("category");
        categoryValue.setText(aux);
        aux=i.getStringExtra("name");
        nameValue.setText(aux);
        aux=i.getStringExtra("description");
        descValue.setText(aux);
        aux=i.getStringExtra("location");
        locationValue.setText(aux);
        aux=i.getStringExtra("url");
        email=i.getStringExtra("email");
        if(!aux.equals(""))
        Picasso.get().load(aux).into(img);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(IndividualPost.this,AllPostsActivity.class);
                startActivity(i);
            }
        });

        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message="Flavia Zaharia flvzah@yahoo.com 0722222222";
                builder.setMessage(message)
                        .setCancelable(false)
                        .setNegativeButton("Go back", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //  Action for 'NO' Button
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                //Setting the title manually
                alert.setTitle("Contact");
                alert.show();
            }
        });
    }
private String findAuthor(String email)
    {
        ArrayList<String> user=new ArrayList<String>();
        db.collection("Users")
                .whereEqualTo("email", email)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        String fname, lname, phone;
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                fname = document.getString("firstname");
                                lname = document.getString("lastname");
                                phone = document.getString("phone");
                                user.add(fname);
                                user.add(lname);
                                user.add(phone);
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
        String message=user.get(0)+""+user.get(1)+""+email+""+user.get(2);
        return message;
    }
}
