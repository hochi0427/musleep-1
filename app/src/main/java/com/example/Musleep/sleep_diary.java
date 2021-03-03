package com.example.Musleep;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.firestore.auth.User;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class sleep_diary extends AppCompatActivity {
    TextView sleeptime;
    FirebaseFirestore db;
    FirebaseAuth firebaseAuth;

    Calendar today = new GregorianCalendar();
    int year = today.get(today.YEAR);
    int month = today.get(today.MONTH)+1;
    int day = today.get(today.DAY_OF_MONTH);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep_diary);
        sleeptime = findViewById(R.id.sleeptime);
        db = FirebaseFirestore.getInstance();
        firebaseAuth = firebaseAuth.getInstance();
        findViewById(R.id.data_pick).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                FirebaseFirestore rootRef = FirebaseFirestore.getInstance();
                DocumentReference docIdRef = rootRef.collection("User")
                        .document(firebaseAuth.getUid())
                        .collection(year+"-"+month+"-"+day)
                        .document("SleepData");
                docIdRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                startActivity(new Intent(getApplicationContext(), dairy_survey.class));
                                Log.d("INFO", "Document exists!");
                            } else {
                                startActivity(new Intent(getApplicationContext(), dairy_survey.class));
                                db = FirebaseFirestore.getInstance();
                                Map<String,Object> dairy = new HashMap<>();
                                dairy.put("ID",firebaseAuth.getUid());
                                db.collection("User")
                                    .document(firebaseAuth.getUid())
                                    .collection(year+"-"+month+"-"+day)
                                    .document("SleepData")
                                    .set(dairy);
                                Log.d("INFO", "Document does not exist!");
                            }
                        } else {
                            Log.d("INFO", "Failed with: ", task.getException());
                        }
                    }
                });
//                startActivity(new Intent(getApplicationContext(), dairy_survey.class));
//                db = FirebaseFirestore.getInstance();
////                Map<String,Object> dairy = new HashMap<>();
////                dairy.put("ID",firebaseAuth.getUid());
//                db.collection("User")
//                        .document(firebaseAuth.getUid())
//                        .collection(year+"-"+month+"-"+day)
//                        .document("SleepData");
            }
        });

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