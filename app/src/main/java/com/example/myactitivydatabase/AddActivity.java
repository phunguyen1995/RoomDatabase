package com.example.myactitivydatabase;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myactitivydatabase.databinding.ActivityAddBinding;
import com.example.myactitivydatabase.databinding.ActivityDetailBinding;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class AddActivity extends AppCompatActivity {
    public MyDataBase dataBase;
    private ActivityAddBinding binding;
    AutoCompleteTextView etAddClass;
    List<UserClass> listdata = new ArrayList<>();
   // Array array = new Ar;
     List<String> lop = new ArrayList<>();
    int positiondata;
   // String []lop = {" lop 1"};
    long val = 1346524199000l;
    private String formatted;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        dataBase =  MyDataBase.instanceDatabase(getApplicationContext());
//        listdata= dataBase.userClassDao().getAll();
//        for(int i=0;i<listdata.size();i++){
//           lop.add( listdata.get(i).name);
//        }

      //  listdata.ge
      //  listdata.toArray(lop);
        LocalDateTime current = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        binding.btnAddStudentBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ArrayAdapter adapterlop = new ArrayAdapter(this,android.R.layout.simple_list_item_1,lop);
        binding.editAddClass.setAdapter(adapterlop);
        binding.editAddClass.setThreshold(1);
        binding.editAddClass.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               positiondata = listdata.get(position).id;
            }
        });
        //Ý nghĩa của nó là thiết lập số ký tự bắt đầu lọc trong AutoComplete
        binding.btnAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User a = new User(1," "," "," "," "," "," ");
                formatted = current.format(formatter);
                editData(a);
                dataBase.userDao().insertAll(editData(a));
             //   Intent intent = getIntent();
               // intent.putExtra("positiondata", positiondata);
               // intent.putExtra("dataadd",2);
              //  setResult(Activity.RESULT_OK,intent);
                finish();
            }
        });

    }
    public User editData(User a){
        a.idClass=positiondata;
        a.name=binding.editAddName.getText().toString().trim();
        a.age=binding.editAddAge.getText().toString().trim();
        a.adress=binding.editAddAdress.getText().toString().trim();
        a.lop=binding.editAddClass.getText().toString().trim();
        a.giaovien=binding.editAddTeacher.getText().toString().trim();
        a.ngaythem=formatted;
        return a;
    }
}
