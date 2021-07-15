package com.example.myactitivydatabase;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import java.util.List;

public class SinhVienViewModel extends ViewModel {
    public UserPaging2Dao userPaging2Dao;

 //   public SinhVienViewModel(UserPaging2Dao userPaging2Dao, LiveData<PagedList<User>> usertList) {
  //      this.userPaging2Dao = userPaging2Dao;
  //      this.usertList = usertList;
 //   }

    public  LiveData<PagedList<User>> usertList;
  //  MyDataBase dataBase = MyDataBase.instanceDatabase(MyApplication.context);
  //  public LiveData<List<User>> getUsers() {
     //   return dataBase.userDao().getAll();
    //}
    // Creates a PagedList object with 50 items per page.
    public SinhVienViewModel(UserPaging2Dao userPaging2Dao) {
        this.userPaging2Dao = userPaging2Dao;
        usertList = new LivePagedListBuilder<>(userPaging2Dao.concertsByID(), 50).build();
    }

}
