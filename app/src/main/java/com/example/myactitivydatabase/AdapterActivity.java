package com.example.myactitivydatabase;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.paging.PagedList;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myactitivydatabase.databinding.ActivityItemBinding;
import com.example.myactitivydatabase.databinding.ItemLoadMoreBinding;

import java.util.ArrayList;
import java.util.List;

public class AdapterActivity extends PagedListAdapter {
       // RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int View_TYPE_ITEM =0;
    private final int VIEW_TYPE_LOADING = 1;
    private List<User> data = new ArrayList<User>();
    private EventSV listener;

     public AdapterActivity(List<User> data) {
         super(DIFF_CALLBACK);
         this.data=data;
   }
    private static DiffUtil.ItemCallback<User> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<User>() {
                // Concert details may have changed if reloaded from the database,
                // but ID is fixed.
                @Override
                public boolean areItemsTheSame(User oldUser, User newUser) {
                    return oldUser.id == newUser.id;
                }

                @SuppressLint("DiffUtilEquals")
                @Override
                public boolean areContentsTheSame(User oldUser,
                                                  User newUser) {
                    return oldUser.equals(newUser);
                }
            };
    public void setListener(EventSV listener) {
        this.listener = listener;
    }

    @Override
    public void submitList(PagedList pagedList) {
        super.submitList(pagedList);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == View_TYPE_ITEM) {
            ActivityItemBinding binding = ActivityItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
            return new ViewHolder11(binding);
        } else {
            ItemLoadMoreBinding itemLoadMoreBinding = ItemLoadMoreBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
            //  View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item,parent,false);
            return new LoadingViewHolder (itemLoadMoreBinding);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder11) {
            ((ViewHolder11) holder).setData(data.get(position), position);
        } else if (holder instanceof LoadingViewHolder) {
            Log.e("", "");
        }
    }
    @Override
    public int getItemCount() {
        return data.size();
    }
    @Override
    public int getItemViewType(int position) {
        return data.get(position)== null? VIEW_TYPE_LOADING:View_TYPE_ITEM;
    }
    public void addAll(List<User> dataForList) {
        data.addAll(dataForList);
//        notify();
        notifyDataSetChanged();
    }
    public class ViewHolder11 extends RecyclerView.ViewHolder {
        ActivityItemBinding binding;
        public ViewHolder11( ActivityItemBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }
        public void setData(User data, int position){
            binding.tvName.setText(data.name);
            binding.tvClass.setText(data.lop);
            binding.tvDate.setText(data.ngaythem);
            binding.btnDelete.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     listener.DeleteStudent(data,getAdapterPosition());
                 }
             });

            binding.btnSee.setOnClickListener(new View.OnClickListener() {
             @Override
            public void onClick(View v) {
                  listener.Detail(data,getAdapterPosition());
                }
           });
            binding.btnEdit.setOnClickListener(new View.OnClickListener() {
               @Override
                public void onClick(View v) {
                    listener.EditStudent(data,getAdapterPosition());
               }
            });
        }
    }
    public class LoadingViewHolder extends  RecyclerView.ViewHolder{
        ItemLoadMoreBinding itemLoadMoreBinding;
        public LoadingViewHolder (ItemLoadMoreBinding itemLoadMoreBinding){
            super(itemLoadMoreBinding.getRoot());
            this.itemLoadMoreBinding =itemLoadMoreBinding;
        }
    }
    public void AddStudent(User user) {
     data.add(user);
     notifyItemInserted(data.size());
    }
    public void deleteStudent(int position){
        data.remove(position);
        notifyItemRemoved(position);
    }
    public void deleteAll(){
        data.clear();
        notifyDataSetChanged();
    }
    public void EditStudent(User data1, int position){
        User a = data.get(position);
        a.name =data1.name;
        a.adress=data1.adress;
        a.age=data1.age;
        a.giaovien=data1.giaovien;
        a.lop=data1.lop;
        a.ngaythem=data1.ngaythem;
        notifyItemChanged(position);
        //notifyDataSetChanged();
    }

}

