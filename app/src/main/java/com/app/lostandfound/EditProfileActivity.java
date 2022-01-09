package com.app.lostandfound;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Transaction;

public class EditProfileActivity extends AppCompatActivity {
    private Button c,s;
    private EditText fname,lname,p;
    private TextView error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        c=findViewById(R.id.cancel);
        s=findViewById(R.id.save);
        fname=findViewById(R.id.fname);
        lname=findViewById(R.id.lname);
        c.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent i=new Intent(EditProfileActivity.this,UserProfileActivity.class);
                startActivity(i);
            }
        });

    }

    private void editUser() {
        String lastname,firstname,phone;
        lastname=lname.getText().toString();
        firstname=fname.getText().toString();
        phone=p.getText().toString();
        if (TextUtils.isEmpty(lastname)||TextUtils.isEmpty(firstname)||TextUtils.isEmpty(phone)) {
            error.setText("Please fill in all the fields");
            return;
        }
    }
}