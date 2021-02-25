package com.example.Musleep;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.firestore.auth.User;

public class sleep_diary extends AppCompatActivity {
    TextView sleeptime;
    FirebaseFirestore db;
    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep_diary);
        sleeptime = findViewById(R.id.sleeptime);
        db = FirebaseFirestore.getInstance();
        firebaseAuth = firebaseAuth.getInstance();

        Log.i("INFO", firebaseAuth.getUid());
        DocumentReference documentReference = db.collection("User").document(firebaseAuth.getUid()).collection("110.1.19").document("SleepData");
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                sleeptime.setText(documentSnapshot.getString("sleeptime"));
            }
        });
    }
}