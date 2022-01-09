package com.app.lostandfound;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private EditText emailTextView,passwordTextView;
    private Button b;
    private TextView message,errorTextView;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth=FirebaseAuth.getInstance();
        emailTextView=findViewById(R.id.email);
        passwordTextView=findViewById(R.id.password);
        errorTextView=findViewById(R.id.error);
        b=findViewById(R.id.login);
        message=findViewById(R.id.loginToRegister);
        SpannableString content = new SpannableString(message.getText().toString());
        content.setSpan(new UnderlineSpan(), 0, message.length(), 0);
        message.setText(content);
        b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                loginUser();
            }
        }
        );
        message.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent i=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(i);
            }
        });
    }

    private void loginUser(){
        String email,password;
        email=emailTextView.getText().toString();
        password=passwordTextView.getText().toString();
        if(TextUtils.isEmpty(email)||TextUtils.isEmpty(password)){
            errorTextView.setText("Please fill in all the fields");
        return;
        }
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(
                new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Intent i=new Intent(LoginActivity.this,MenuActivity.class);
                            startActivity(i);
                        }
                        else{
                            errorTextView.setText("Incorrect email or password");
                        }

                    }
                }
        );

    }
}
