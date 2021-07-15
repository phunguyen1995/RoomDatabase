package com.example.myactitivydatabase;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myactitivydatabase.databinding.ActivityAddBinding;
import com.example.myactitivydatabase.databinding.ActivityAddClassBinding;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ClassAddActivity extends AppCompatActivity {
    public MyDataBase dataBase1;
    private ActivityAddClassBinding binding;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddClassBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        dataBase1 =  MyDataBase.instanceDatabase(getApplicationContext());
        UserClass a = new UserClass(" ");
        binding.btnAddStudentBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        binding.btnAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataBase1.userClassDao().insertAll(editData(a));
                Intent intent = getIntent();
                intent.putExtra("dataadd1","ok");
                setResult(Activity.RESULT_OK,intent);
                finish();
            }
        });

    }
    public UserClass editData(UserClass a){
        a.name=binding.editAddNameClass.getText().toString().trim();
        return a;
    }
}
