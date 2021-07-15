package com.example.myactitivydatabase;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class SinhVienViewModel extends ViewModel {
    MyDataBase dataBase = MyDataBase.instanceDatabase(MyApplication.context);
    public LiveData<List<User>> getUsers() {
        return dataBase.userDao().getAll();
    }
}
