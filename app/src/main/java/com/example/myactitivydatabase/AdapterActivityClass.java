package com.example.myactitivydatabase;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myactitivydatabase.databinding.ActivityItemBinding;
import com.example.myactitivydatabase.databinding.ActivityItemClassBinding;

import java.util.ArrayList;
import java.util.List;

public class AdapterActivityClass extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public List<UserClass> data1 = new ArrayList<UserClass>();
    private EventClass listener1;
    public AdapterActivityClass(List<UserClass> data1) {
        this.data1=data1;
    }
    public void setListener(EventClass listener) {
        this.listener1 = listener;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        ActivityItemClassBinding binding = ActivityItemClassBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder1(binding);
    }

    @Override
    public void onBindViewHolder( RecyclerView.ViewHolder holder, int position) {
        ((AdapterActivityClass.ViewHolder1) holder).setData1(data1.get(position), position);
    }

    @Override
    public int getItemCount() {
        return data1.size();
    }
    public   class ViewHolder1 extends RecyclerView.ViewHolder {
        ActivityItemClassBinding binding;
        public ViewHolder1( ActivityItemClassBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }
        public void setData1(UserClass data1, int position){
            binding.tvDetailClass.setText(data1.name);
           // binding.tvClass.setText(data1.lop);
          //  binding.tvDate.setText(data1.ngaythem);

            binding.btnDetailClass.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener1.DetailClass(data1,getAdapterPosition());

                }
            });

        }
    }
    public void deleteAll(){
        notifyDataSetChanged();
    }
    public void AddClass() {
        notifyItemInserted(data1.size()-1);
    }
}