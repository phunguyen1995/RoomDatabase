package com.example.myactitivydatabase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myactitivydatabase.databinding.ActivityDetailBinding;
import com.example.myactitivydatabase.databinding.ActivityDetailClassBinding;

import java.util.List;

public class DetailClass extends AppCompatActivity {
    private ActivityDetailClassBinding binding2;
    public MyDataBase dataBase ;
    public AdapterDetailClass adapterDetailClass;
    public List<User> dataDetailClass;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding2 = ActivityDetailClassBinding.inflate(getLayoutInflater());
        setContentView(binding2.getRoot());
        LinearLayoutManager linear = new LinearLayoutManager(this, RecyclerView.VERTICAL,false);
        binding2.recyclerview.setLayoutManager(linear);
        dataBase =  MyDataBase.instanceDatabase(getApplicationContext());
        Intent intent = this.getIntent();
        int position = intent.getIntExtra("positionDetail", 1);
        dataDetailClass = dataBase.userDao().selectUserByIdClass(position+1);
        adapterDetailClass = new AdapterDetailClass(dataDetailClass);
        binding2.recyclerview.setAdapter(adapterDetailClass);
        binding2.btnBackDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
