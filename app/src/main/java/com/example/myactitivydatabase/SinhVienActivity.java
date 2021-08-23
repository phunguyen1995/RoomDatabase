package com.example.myactitivydatabase;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.myactitivydatabase.databinding.ActivityRecycleviewBinding;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;

public class SinhVienActivity extends AppCompatActivity  implements EventSV{
    private ActivityRecycleviewBinding binding;
    public AdapterActivity adapterActivity;
    public MyDataBase dataBase;
    private List<User> data = new ArrayList();
    int offset = 1;
  //  SinhVienViewModel viewModel;
    @SuppressLint("WrongThread")
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRecycleviewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        dataBase =  MyDataBase.instanceDatabase(getApplicationContext());
        LinearLayoutManager linear = new LinearLayoutManager(this, RecyclerView.VERTICAL,false);
        binding.recyclerview.setLayoutManager(linear);
  //      viewModel =new ViewModelProvider(this,new ViewModelProvider.NewInstanceFactory).get(SinhVienViewModel.class);
        if(dataBase.userDao().getAll1().size()==0){
         dataBase.userDao().insertAll (createDataForList());}
       // viewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication()).create(SinhVienViewModel.class);
        SinhVienViewModel viewModel = new ViewModelProvider(this).get(SinhVienViewModel.class);
        //   viewModel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(Application)).get(SinhVienViewModel.class)
    //  }
      //  data = dataBase.userDao().loadUser(0);
     //   data =  dataBase.userDao().getAll1();
        adapterActivity = new AdapterActivity(data);
        viewModel.usertList.observe(this, adapterActivity::submitList);
       // adapterActivity.setListener(this);
        binding.recyclerview.setAdapter(adapterActivity);
        adapterActivity.setListener(this);
        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SinhVienActivity.this, AddActivity.class);
                intent.putExtra("data1",1);
                someActivityResultLauncher.launch(intent);

            }
        });
        binding.btnDEleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               DeleteAll();

            }
        });
        binding.swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        binding.swipeContainer.setRefreshing(false);
                        data.clear();
                        adapterActivity.deleteAll();
                        data = dataBase.userDao().loadUser(0);
                        adapterActivity.addAll(data);
                    }
                },500);
            }

        });
  //      viewModel.getUsers().observe(this, users -> {
   //        adapterActivity.deleteAll();
      //      data.clear();
     //      adapterActivity.addAll(users);
      //      adapterActivity.notifyDataSetChanged();
     //   });
  //      viewModel.getUsers().observe(this, new Observer<List<User>>() {
   //         @Override
   //         public void onChanged(List<User> users) {

   //         }
   //     });
     //   viewModel.getUsers().observe(this,adapterActivity::addAll);
    //    viewModel.usertList.observe(this, adapterActivity::submitList);
        initScrollListener();

    }
    private boolean isLoading = false;
    private boolean isLoadmore = true;
    private void initScrollListener(){
        binding.recyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged( RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
            @Override
            public void onScrolled( RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                if(!isLoading ){
                    if(linearLayoutManager!=null
                            && linearLayoutManager.findLastCompletelyVisibleItemPosition()== data.size()-1 &&
                            adapterActivity.getItemCount()>5 && isLoadmore  ){
                       loadMore();
                        isLoading=true;
                    }
                }
            }
        });
    }
    private void loadMore(){
        data.add(null);
        adapterActivity.notifyItemInserted(data.size()-1);
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                List<User> data12 = dataBase.userDao().loadUser(offset);
                if(isLoadmore){
                    adapterActivity.deleteStudent(data.size()-1);
                    data.remove(data.size()-1);
                    int scrollPosition=data.size();
                    adapterActivity.notifyItemRemoved(scrollPosition);
                    adapterActivity.addAll(data12);
                    if(data12.size()==5) {
                        offset++;
                        isLoadmore=true;
                    } else isLoadmore=false;
                }

                isLoading=false;
            }
        },5000);
    }

    @Override
    public void onTopResumedActivityChanged(boolean isTopResumedActivity) {
        super.onTopResumedActivityChanged(isTopResumedActivity);

    }

   private User[] createDataForList(){
   for(int i = 0; i <200; i++) {
       data.add(new User(1,"nguyen van phu", "24", "nam o", "1A", "nguyen van A", "20/01/2021"));
        }
      User[] arr = new User[data.size()];
      data.toArray(arr);
      return (arr);
   }
    @Override
    public void EditStudent(User data2, int position) {
        Intent intent = new Intent(SinhVienActivity.this, InsertActivity1.class);
        intent.putExtra("data2", (Serializable) data2);
        intent.putExtra("position111",position);
        someActivityResultLauncher.launch(intent);
    }
//    @Override
    public void DeleteAll() {
        dataBase.userDao().deleteAllUser();
        data.clear();
        adapterActivity.deleteAll();
    }


    @Override
    public void DeleteStudent(User data,int position) {
        dataBase.userDao().delete(data);
        adapterActivity.deleteStudent(position);
    }

    @Override
    public void Detail(User data, int position) {
        Intent intent = new Intent(SinhVienActivity.this, DetailSV2.class);
        intent.putExtra("datadetail", (Serializable) data);
        startActivity(intent);
    }
   private ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data4 = result.getData();

//                        if(data4.getIntExtra("datainsert",0)== 1){
                            int i = (int) data4.getSerializableExtra("position111");
                            User user =  dataBase.userDao().findByIds(i+1);
                            adapterActivity.EditStudent( user,i);
//                        }else
//                        {
//                        //    int i = (int) data4.getSerializableExtra("dataadd");
//                            User user1= (User)dataBase.userDao().findLastIdUser();
  //                        User user1= (User)dataBase.userDao().findLastIdUser();
    //                      data.add(user1);
   //                         adapterActivity.AddStudent(user1);
//                            //AddStudent(user1);
//                        }

                        }
                    }

            });


}


