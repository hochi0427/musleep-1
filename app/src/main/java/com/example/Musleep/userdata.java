package com.example.Musleep;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class userdata extends AppCompatActivity {

    TextView tvDate;
    EditText et_name,etDate;
    RadioGroup gender;
    RadioButton male,female;
    DatePickerDialog.OnDateSetListener setListener;
    MaterialButton Registerbtn;
    FirebaseFirestore db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userdata);
        db = FirebaseFirestore.getInstance();
        FirebaseUser mAuth = FirebaseAuth.getInstance().getCurrentUser();

        //設定隱藏標題
        //getSupportActionBar().hide();
        et_name = findViewById(R.id.et_name);
        male = (RadioButton) findViewById(R.id.A1);
        female = (RadioButton) findViewById(R.id.A0);


        //上傳radiogroup的ID
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.mRadioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radiogroup,@IdRes int selectId) {
                int selectedId = radioGroup.getCheckedRadioButtonId();
                switch(selectedId){
                    case R.id.A0:
                        Map<String,Object> male = new HashMap<>();
                        male.put("Gender",0);
                        db.collection("User")
                                .document(mAuth.getUid())
                                .set(male);
                        Toast.makeText(userdata.this,"我是男生",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.A1:
                        Map<String,Object> female = new HashMap<>();
                        female.put("Gender",1);
                        db.collection("User")
                                .document(mAuth.getUid())
                                .set(female);
                        Toast.makeText(userdata.this,"我是女生",Toast.LENGTH_SHORT).show();
                        break;

                }
            }
        });

        Registerbtn = findViewById(R.id.Registerbtn);
        Registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Toast.makeText(userdata.this,"Click",Toast.LENGTH_SHORT).show();
//                Map<String,Object> gender = new HashMap<>();
//                String Gender = String.valueOf(mOnCheckedChangeListener);
//                gender.put("Gender",Gender);
//                db.collection("User")
//                        .document(mAuth.getUid())
//                        .set(gender);

//                String Name = et_name.getText().toString();
//                Map<String,Object> user = new HashMap<>();
//                user.put("Name",Name);
//                db.collection("User")
//                        .document(mAuth.getUid())
////                        .document("k53CGABt5HZKkYf77DQ5k3qOHOc2")
//                        .set(user)
//                        .addOnSuccessListener(new OnSuccessListener<Void>() {
//                            @Override
//                            public void onSuccess(Void aVoid) {
//                                Toast.makeText(userdata.this,"Successfull",Toast.LENGTH_SHORT).show();
//                                startActivity(new Intent(getApplicationContext(), homescreen.class));
//                            }
//                        }).addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Toast.makeText(userdata.this,"Failed",Toast.LENGTH_SHORT).show();
//
//                    }
//                });
            }
        });

        //性別radio
//        RadioButton male = (RadioButton) findViewById(R.id.male);
//        RadioButton female = (RadioButton) findViewById(R.id.female);
//        male.setOnCheckedChangeListener(mOnCheckedChangeListener);
//        female.setOnCheckedChangeListener(mOnCheckedChangeListener);


        //出生年月日
        tvDate = findViewById(R.id.tv_date);
        //etDate = findViewById(R.id.et_date);

        Calendar calendar = Calendar.getInstance();
        final  int year = calendar.get(Calendar.YEAR);
        final  int month = calendar.get(Calendar.MONTH);
        final  int day = calendar.get(Calendar.DAY_OF_MONTH);

        tvDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        userdata.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth
                        ,setListener,year,month,day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();

            }
        });

        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month+1;
                String date = year+"/"+month+"/"+day;
                tvDate.setText(date);
            }
        };



//        etDate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                DatePickerDialog datePickerDialog = new DatePickerDialog(
//                        userdata.this, new DatePickerDialog.OnDateSetListener() {
//                    @Override
//                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                        month = month+1;
//                        String date = day+"/"+month+"/"+year;
//                        etDate.setText(date);
//                    }
//                },year,month,day);
//                datePickerDialog.show();
//            }
//        });

    }
    //性別radio
    private CompoundButton.OnCheckedChangeListener mOnCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            switch (buttonView.getId()) {
                case R.id.A1:
                    Toast.makeText(userdata.this, "我是男生!", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.A0:
                    Toast.makeText(userdata.this, "我是女生!", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
    public void RadioClick(View view) {
    }

//    public void buttonOnClick(View view) {
//        Button button = (Button) view;
//        Toast toast = Toast.makeText(this, "按鈕已經被點擊", Toast.LENGTH_SHORT);
//        toast.show();
//    }


}