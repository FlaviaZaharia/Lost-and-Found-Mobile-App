package com.app.lostandfound;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class AddDescription extends AppCompatActivity {
    private static final String TAG="";
    ImageButton back;
    TextView t;
    private FirebaseFirestore db=FirebaseFirestore.getInstance();
    Button send;
    EditText desc;
    String uid;
    ArrayList<String> descs;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_description);
        back=findViewById(R.id.back);
        send=findViewById(R.id.sendDescription);
        desc=findViewById(R.id.addDesc);
        t=findViewById(R.id.error);
        Intent intent=getIntent();
        uid=intent.getStringExtra("uid");
        descs=intent.getStringArrayListExtra("d");
        String url=intent.getStringExtra("url");
        Log.d(TAG,"AICI"+descs);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(AddDescription.this,IndividualFound.class);
                intent.putExtra("url",url);
                startActivity(i);
            }
        });
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sendDescription(url);

            }
        });

    }

    private void sendDescription(String url) {
        String descriptionText;
        descriptionText = desc.getText().toString();
        if (TextUtils.isEmpty(descriptionText)) {
            t.setText("Please fill in the description");
            return;

        }
            descs.add(descriptionText);
            Map<String, Object> data = new HashMap<>();
            data.put("desc_requests", descs);


            db.collection("Posts").document(uid)
                    .set(data, SetOptions.merge());
            Intent intent=new Intent(AddDescription.this,IndividualFound.class);
            intent.putExtra("url",url);
            startActivity(intent);


    }
}
