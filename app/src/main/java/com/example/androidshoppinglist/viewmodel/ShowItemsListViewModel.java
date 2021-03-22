package com.example.androidshoppinglist.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.androidshoppinglist.db.AppDatabase;
import com.example.androidshoppinglist.db.Items;

import java.util.List;


public class ShowItemsListViewModel extends AndroidViewModel {

    private MutableLiveData<List<Items>> listOfItems;
    private AppDatabase appDatabase;

    public ShowItemsListViewModel(@NonNull Application application) {
        super(application);
        listOfItems = new MutableLiveData<>();

        appDatabase = AppDatabase.getDBinstance(getApplication().getApplicationContext());

    }

    public MutableLiveData<List<Items>> getCategoryListObserver() {
        return listOfItems;
    }

    public void getAllItemsList(int categoyID) {
        List<Items> itemsList = appDatabase.shoppingListDao().getAllItemsList(categoyID);
        if (itemsList.size() > 0)
        {
            listOfItems.postValue(itemsList);
        } else {
            listOfItems.postValue(null);
        }
    }

    public void insertItems(Items item) {
        appDatabase.shoppingListDao().insertItems(item);
        getAllItemsList(item.categoryId);
    }

    public void updateItems(Items item) {
        appDatabase.shoppingListDao().updateItems(item);
        getAllItemsList(item.categoryId);
    }

    public void deleteItems(Items item) {
        appDatabase.shoppingListDao().deleteItem(item);
        getAllItemsList(item.categoryId);
    }


}
