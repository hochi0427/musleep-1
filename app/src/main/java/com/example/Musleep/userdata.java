package com.example.Musleep;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class userdata extends AppCompatActivity {

    TextView tvDate, tvd;
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
//        male = (RadioButton) findViewById(R.id.A1);
//        female = (RadioButton) findViewById(R.id.A0);


        //上傳radiogroup的ID
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.mRadioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radiogroup,@IdRes int selectId) {
                int selectedId = radioGroup.getCheckedRadioButtonId();
                switch(selectedId){
                    case R.id.A0:
                        Map<String,Object> female = new HashMap<>();
                        female.put("Gender",0);
                        db.collection("User")
                                .document(mAuth.getUid())
                                .update(female);
                        Toast.makeText(userdata.this,"我是女生",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.A1:
                        Map<String,Object> male = new HashMap<>();
                        male.put("Gender",1);
                        db.collection("User")
                                .document(mAuth.getUid())
                                .update(male);
                        Toast.makeText(userdata.this,"我是男生",Toast.LENGTH_SHORT).show();
                        break;

                }
            }
        });

        Registerbtn = findViewById(R.id.Registerbtn);
        Registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


//                Toast.makeText(userdata.this,"Click",Toast.LENGTH_SHORT).show();
//                Map<String,Object> gender = new HashMap<>();
//                String Gender = String.valueOf(mOnCheckedChangeListener);
//                gender.put("Gender",Gender);
//                db.collection("User")
//                        .document(mAuth.getUid())
//                        .update(gender);

                String Name = et_name.getText().toString();
                Map<String,Object> user = new HashMap<>();
                user.put("Name",Name);
                db.collection("User")
                        .document(mAuth.getUid())
                        .update(user)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(userdata.this,"Successfull",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), homescreen.class));
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(userdata.this,"Failed",Toast.LENGTH_SHORT).show();

                    }
                });
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
                month = month + 1;
                String date = year + "/" + month + "/" + day;

                tvDate.setText(date);
                Log.i("TAG", date);
                Log.i("TAG", String.valueOf(year));
                Calendar cal = Calendar.getInstance();
                int yearNow = cal.get(Calendar.YEAR);
                int monthNow = cal.get(Calendar.MONTH);
                int dayNow = cal.get(Calendar.DAY_OF_MONTH);

                int age = yearNow - year;
                if (monthNow < month || (monthNow == month && dayNow < day)) {
                    age--;
                    Log.i("TAG", String.valueOf(age));
                    Map<String,Object> gg = new HashMap<>();
                    gg.put("AGE",age);
                    db.collection("User")
                            .document(mAuth.getUid())
                            .update(gg);
                }else{
                    Log.i("TAG", String.valueOf(age));
                    Map<String,Object> ggg = new HashMap<>();
                    ggg.put("AGE",age);
                    db.collection("User")
                            .document(mAuth.getUid())
                            .update(ggg);
                };
            }

        };

//第二版
        tvd = findViewById(R.id.tvd);
        tvd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(userdata.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthofYear, int dayOfMonth) {
                        //顯示生日（月份要+1，因為這個方法是從0開始算的）
                        tvd.setText(String.format("%d-%d-%d", year, monthofYear + 1, dayOfMonth));

                        Calendar cal = Calendar.getInstance();
                        String strDate = year + "-" + monthofYear + "-" + dayOfMonth;
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        Date birthDay = null;
                        try {
                            birthDay = sdf.parse(strDate);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        int birth = countAge(birthDay);
                        if (birth < 0) {
                            Toast.makeText(getApplicationContext(), "生日輸入有誤", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(getApplicationContext(), birth + "歲", Toast.LENGTH_SHORT).show();
                        }

                    }

                    private int countAge(Date birthDay) {
                        Calendar cal = Calendar.getInstance();

                        if (cal.before(birthDay)) {
                            throw new IllegalArgumentException(
                                    "The birthDay is before Now.It's unbelievable!");
                        }
                        //獲得當前日期
                        int yearNow = cal.get(Calendar.YEAR);
                        int monthNow = cal.get(Calendar.MONTH);
                        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
                        //獲得出生日期
                        cal.setTime(birthDay);
                        int yearBirth = cal.get(Calendar.YEAR);
                        int monthBirth = cal.get(Calendar.MONTH) + 1;
                        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

                        int age = yearNow - yearBirth;
                        if (monthNow <= monthBirth) {
                            if (monthNow == monthBirth) {
                                if (dayOfMonthNow < dayOfMonthBirth) age--;
                            } else {
                                age--;
                                Map<String,Object> ggg = new HashMap<>();
                                ggg.put("AGE",age);
                                db.collection("User")
                                        .document(mAuth.getUid())
                                        .update(ggg);
                            }
                        }
                        return age;
                    }
                    //設定初始的顯示日期
                }, 2000, 0, 1).show();

            }
        });




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

    //第二版
//日期選擇

//    private void dialogDate() {
//        tv_birthday_set.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                new DatePickerDialog(userdata.this, new DatePickerDialog.OnDateSetListener() {
//                    @Override
//                    public void onDateSet(DatePicker view, int year, int monthofYear, int dayOfMonth) {
//                        //顯示生日（月份要+1，因為這個方法是從0開始算的）
//                        tv_birthday_set.setText(String.format("%d-%d-%d", year, monthofYear + 1, dayOfMonth));
//
//                        Calendar cal = Calendar.getInstance();
//                        String strDate = year + "-" + monthofYear + "-" + dayOfMonth;
//                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//                        Date birthDay = null;
//                        try {
//                            birthDay = sdf.parse(strDate);
//                        } catch (ParseException e) {
//                            e.printStackTrace();
//                        }
//                        int birth = countAge(birthDay);
//                        if (birth < 0) {
//                            Toast.makeText(getApplicationContext(), "生日輸入有誤", Toast.LENGTH_SHORT).show();
//
//                        } else {
//                            Toast.makeText(getApplicationContext(), birth + "歲", Toast.LENGTH_SHORT).show();
//                        }
//
//                    }
//
//                    private int countAge(Date birthDay) {
//                        Calendar cal = Calendar.getInstance();
//
//                        if (cal.before(birthDay)) {
//                            throw new IllegalArgumentException(
//                                    "The birthDay is before Now.It's unbelievable!");
//                        }
//                        //獲得當前日期
//                        int yearNow = cal.get(Calendar.YEAR);
//                        int monthNow = cal.get(Calendar.MONTH);
//                        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
//                        //獲得出生日期
//                        cal.setTime(birthDay);
//                        int yearBirth = cal.get(Calendar.YEAR);
//                        int monthBirth = cal.get(Calendar.MONTH) + 1;
//                        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
//
//                        int age = yearNow - yearBirth;
//                        if (monthNow <= monthBirth) {
//                            if (monthNow == monthBirth) {
//                                if (dayOfMonthNow < dayOfMonthBirth) age--;
//                            } else {
//                                age--;
//                            }
//                        }
//                        return age;
//                    }
//                    //設定初始的顯示日期
//                }, 2000, 0, 1).show();
//            }
//        });
//
//    }

}