package com.example.Musleep;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.RadioGroup;
import android.widget.TextClock;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class dairy_survey extends AppCompatActivity {
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dairy_survey);
        db = FirebaseFirestore.getInstance();
        FirebaseUser mAuth = FirebaseAuth.getInstance().getCurrentUser();

        Calendar today = new GregorianCalendar();
        int year = today.get(today.YEAR);
        int month = today.get(today.MONTH)+1;
        int day = today.get(today.DAY_OF_MONTH);

        //上傳radiogroup的ID
        RadioGroup radioGroup1 = (RadioGroup) findViewById(R.id.drink_coffee);
        radioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup radiogroup,@IdRes int selectId) {
                int selectedId = radioGroup1.getCheckedRadioButtonId();

                Log.i("INFO", String.valueOf(year));
                Log.i("INFO", String.valueOf(month));
                Log.i("INFO", String.valueOf(day));
                switch(selectedId){
                    case R.id.A1:
                        Map<String,Object> yes = new HashMap<>();
                        yes.put("coffee",1);
                        db.collection("User")
                                .document(mAuth.getUid())
                                .collection(year+"-"+month+"-"+day)
                                .document("SleepData")
                                .update(yes);
                        Toast.makeText(dairy_survey.this,"是",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.A0:
                        Map<String,Object> no = new HashMap<>();
                        no.put("coffee",0);
                        db.collection("User")
                                .document(mAuth.getUid())
                                .collection(year+"-"+month+"-"+day)
                                .document("SleepData")
                                .update(no);
                        Toast.makeText(dairy_survey.this,"否",Toast.LENGTH_SHORT).show();
                        break;

                }
            }
        });
        //上傳radiogroup的ID
        RadioGroup radioGroup2 = (RadioGroup) findViewById(R.id.little_sleep);
        radioGroup2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radiogroup,@IdRes int selectId) {
                int selectedId = radioGroup2.getCheckedRadioButtonId();
                switch(selectedId){
                    case R.id.B1:
                        Map<String,Object> yes = new HashMap<>();
                        yes.put("little_sleep",1);
                        db.collection("User")
                                .document(mAuth.getUid())
                                .collection(year+"-"+month+"-"+day)
                                .document("SleepData")
                                .update(yes);
                        Toast.makeText(dairy_survey.this,"是",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.B0:
                        Map<String,Object> no = new HashMap<>();
                        no.put("little_sleep",0);
                        db.collection("User")
                                .document(mAuth.getUid())
                                .collection(year+"-"+month+"-"+day)
                                .document("SleepData")
                                .update(no);
                        Toast.makeText(dairy_survey.this,"否",Toast.LENGTH_SHORT).show();
                        break;

                }
            }
        });



    }
}