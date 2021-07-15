package com.example.myactitivydatabase;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myactitivydatabase.databinding.ActivityAddClassBinding;
import com.example.myactitivydatabase.databinding.ActivityDetailBinding;
import com.example.myactitivydatabase.databinding.ActivityDetailClassBinding;

public class DetailSV2 extends AppCompatActivity {

    private ActivityDetailBinding binding2;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding2 = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding2.getRoot());
        User a = (User) getIntent().getSerializableExtra("datadetail");
        binding2.tvlop.setText(a.lop);
        binding2.tvDateDetail.setText(a.ngaythem);
        binding2.tvTeacherDetail.setText(a.giaovien);
        binding2.tvAgeDetail.setText(a.age);
        binding2.tvNameDetail.setText(a.name);
        binding2.tvAdressDetail.setText(a.adress);
        binding2.btnBackDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }}
