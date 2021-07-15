package com.example.myactitivydatabase;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myactitivydatabase.databinding.ActivityItemClassBinding;
import com.example.myactitivydatabase.databinding.ItemDetailClassBinding;

import java.util.ArrayList;
import java.util.List;

public class AdapterDetailClass extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private List<User> data1 = new ArrayList<User>();
    //private EventClass listener1;
    //public MyDataBase dataBase =MyDataBase.instanceDatabase(getApplicationContext());
   // dataBase =  MyDataBase.instanceDatabase(getApplicationContext());
    public AdapterDetailClass(List<User> data1) {
        this.data1=data1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        ItemDetailClassBinding  binding= ItemDetailClassBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder2(binding);
    }

    @Override
    public void onBindViewHolder( RecyclerView.ViewHolder holder, int position) {
        ((AdapterDetailClass.ViewHolder2) holder).setData1(data1.get(position), position);
    }

    @Override
    public int getItemCount() {
        return data1.size();
    }
    public   class ViewHolder2 extends RecyclerView.ViewHolder {
        ItemDetailClassBinding binding;
        public ViewHolder2( ItemDetailClassBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }
        public void setData1(User data1, int position){
            binding.tvName.setText(data1.name);
            binding.tvDate.setText(data1.ngaythem);
            binding.tvTeacher.setText(data1.giaovien);
            binding.tvAgeDetail.setText(data1.age);
            // binding.tvClass.setText(data1.lop);
            //  binding.tvDate.setText(data1.ngaythem);



        }
    }
}
