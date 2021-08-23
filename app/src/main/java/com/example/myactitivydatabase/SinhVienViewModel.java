package com.example.myactitivydatabase;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import java.util.List;

public class SinhVienViewModel extends AndroidViewModel {

  // public SinhVienViewModel(UserPaging2Dao userPaging2Dao, LiveData<PagedList<User>> usertList) {
  //      this.userPaging2Dao = userPaging2Dao;
  //      this.usertList = usertList;
  // }
  MyDataBase dataBase = MyDataBase.instanceDatabase(MyApplication.context);
    public  LiveData<PagedList<User>> usertList = new LivePagedListBuilder<>(dataBase
            .userDao().concertsByID(), 5).build();

    public SinhVienViewModel(Application application) {
        super(application);
    }
//    public LiveData<List<User>> getUsers() {
//        return dataBase.userDao().getAll();  }


}
