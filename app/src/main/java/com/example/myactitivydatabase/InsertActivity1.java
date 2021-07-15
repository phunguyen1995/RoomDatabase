package com.example.myactitivydatabase;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myactitivydatabase.databinding.ActivityInsertBinding;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class InsertActivity1 extends AppCompatActivity {
    private ActivityInsertBinding binding1;
    private String formatted;
    public MyDataBase dataBase;
    List<UserClass> listdata = new ArrayList<>();
    List<String> lop = new ArrayList<>();
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding1 = ActivityInsertBinding.inflate(getLayoutInflater());
        setContentView(binding1.getRoot());
        dataBase =  MyDataBase.instanceDatabase(getApplicationContext());
        listdata= dataBase.userClassDao().getAll();
        for(int i=0;i<listdata.size();i++){
            lop.add( listdata.get(i).name);
        }
        LocalDateTime current = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        User a = (User) getIntent().getSerializableExtra("data2");
        //  int c= (int) getIntent().getSerializableExtra("position");

        ArrayAdapter adapterCountries  = new ArrayAdapter(this,android.R.layout.simple_list_item_1,lop);
        binding1.etEditlop.setAdapter(adapterCountries);
        binding1.etEditlop.setThreshold(1);
        binding1.etEditlop.setText(a.lop);
        binding1.etEditTeacher.setText(a.giaovien);
        binding1.etEditAge.setText(a.age);
        binding1.etEditName.setText(a.name);
        binding1.etEditAdress.setText(a.adress);

        binding1.btnetEditBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
        binding1.btnetEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                formatted = current.format(formatter);
                editData(a);
                dataBase.userDao().update(a);
                Intent intent_insert = getIntent();
              //int c = (int) getIntent().getSerializableExtra("position");
             //  intent_insert.putExtra("datainsert", 1);
             //  intent.putExtra("position1",intent.getSerializableExtra("position"));
               // intent_insert.pu
                setResult(Activity.RESULT_OK, intent_insert);
                finish();
            }
        });

    }
    public User editData(User a){
        a.name=binding1.etEditName.getText().toString().trim();
        a.age=binding1.etEditAge.getText().toString().trim();
        a.adress=binding1.etEditAdress.getText().toString().trim();
        a.lop=binding1.etEditlop.getText().toString().trim();
        a.giaovien=binding1.etEditTeacher.getText().toString().trim();
        a.ngaythem=formatted;
        return a;
    }
}
