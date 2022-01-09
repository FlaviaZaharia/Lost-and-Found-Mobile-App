package com.app.lostandfound;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    private static final String TAG="";
    private EditText emailTextView,passwordTextView,firstnameTextView,lastNameTextView;
    private Button b;
    private TextView message,errorTextView;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db=FirebaseFirestore.getInstance();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth=FirebaseAuth.getInstance();
        emailTextView=findViewById(R.id.email);
        passwordTextView=findViewById(R.id.password);
        firstnameTextView=findViewById(R.id.firstname);
        lastNameTextView=findViewById(R.id.lastname);
        errorTextView=findViewById(R.id.error);
        b=findViewById(R.id.login);
        message=findViewById(R.id.registerToLogin);
        SpannableString content = new SpannableString(message.getText().toString());
        content.setSpan(new UnderlineSpan(), 0, message.length(), 0);
        message.setText(content);
        b.setOnClickListener(new View.OnClickListener() {
                                 public void onClick(View v){
                                     registerUser();
                                 }
                             }
        );
        message.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent i=new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(i);
            }
        });
    }

private void registerUser(){
    String email, password,firstname,lastname;
    email = emailTextView.getText().toString();
    password = passwordTextView.getText().toString();
    firstname=firstnameTextView.getText().toString();
    lastname=lastNameTextView.getText().toString();
    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
    SharedPreferences.Editor editor = preferences.edit();
    editor.putString("email",email);
    editor.apply();
    if (TextUtils.isEmpty(email)||TextUtils.isEmpty(password)||TextUtils.isEmpty(firstname)||TextUtils.isEmpty(lastname)) {
        errorTextView.setText("Please fill in all the fields");
        return;
    }
    if(password.length()<6)
    {
        errorTextView.setText("Password must have minimum 6 letters");
        return;
    }

    mAuth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        addUserToDatabase(email,firstname,lastname);
                        Intent i=new Intent(RegisterActivity.this,FormActivity.class);
                        startActivity(i);
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Fail",Toast.LENGTH_LONG).show();
                    }
                }
            });
}

private void addUserToDatabase(String email,String firstname,String lastname){
    Map<String,Object> user=new HashMap<>();
    user.put("email",email);
    user.put("firstname",firstname);
    user.put("lastname",lastname);
    db.collection("Users").add(user)
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
