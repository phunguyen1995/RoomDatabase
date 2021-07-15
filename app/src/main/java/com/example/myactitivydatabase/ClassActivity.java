package com.example.myactitivydatabase;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myactitivydatabase.databinding.ActivityClassBinding;
import com.example.myactitivydatabase.databinding.ActivityRecycleviewBinding;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ClassActivity extends AppCompatActivity implements  EventClass{
    private ActivityClassBinding binding;
    public AdapterActivityClass adapterActivityClass;
    public MyDataBase dataBase1;
    public List<UserClass> dataClass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityClassBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        dataBase1 =  MyDataBase.instanceDatabase(getApplicationContext());
        LinearLayoutManager linear = new LinearLayoutManager(this, RecyclerView.VERTICAL,false);
        binding.recyclerview.setLayoutManager(linear);
        dataBase1.userClassDao().insertAll (createDataForList());
        dataClass = dataBase1.userClassDao().getAll();
        adapterActivityClass = new AdapterActivityClass(dataClass);
        binding.recyclerview.setAdapter(adapterActivityClass);
        adapterActivityClass.setListener(this);
        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ClassActivity.this, ClassAddActivity.class);
                intent.putExtra("data1",1);
                someActivityResultLauncher.launch(intent);
            }
        });
        binding.btnDEleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataBase1.userClassDao().deleteAllUserClass();
                dataClass.clear();
                adapterActivityClass.deleteAll();
            }
        });

    }
    private UserClass[] createDataForList(){
        List<UserClass>  data = new ArrayList<>();
       // for(int i = 0; i <200; i++) {
         //   data.add(new UserClass("Lớp 1"));
       // }
        UserClass[] arr = new UserClass[data.size()];
        data.toArray(arr);
        return (arr);
    }

    @Override
    public void AddClass() {

    }

    @Override
    public void DetailClass(UserClass data, int position) {
        Intent intent = new Intent(ClassActivity.this, DetailClass.class);
        intent.putExtra("positionDetail", position);
        startActivity(intent);
    }
    private ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK ){
                            dataClass.add(dataBase1.userClassDao().findLastIdUserClass());
                            adapterActivityClass.AddClass();
                        }

                    }


            });
        }

