package com.gzc.page;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.gzc.page.room.Account;
import com.gzc.page.room.AccountDao;
import com.gzc.page.room.AppDatabase;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private RecyclerView accountListView;
    private AccountListAdapter accountListAdapter;

    private AppDatabase appDatabase;
    private AccountDao accountDao;

    private LiveData<PagedList<Account>> allAccountList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        accountListView = findViewById(R.id.account_list_view);
        accountListAdapter = new AccountListAdapter();
        accountListView.setLayoutManager(new LinearLayoutManager(this));
        accountListView.setAdapter(accountListAdapter);


        appDatabase = Room.databaseBuilder(this, AppDatabase.class, "page_db").build();
        accountDao = appDatabase.getAccountDao();

        allAccountList = new LivePagedListBuilder<>(accountDao.getAll(),20).build();
        allAccountList.observe(this, new Observer<PagedList<Account>>() {
            @Override
            public void onChanged(PagedList<Account> accounts) {
                accountListAdapter.submitList(accounts);
                Log.e("guanzhenchuang","onChanged");
            }
        });

        findViewById(R.id.insert_data_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData();
            }
        });

        findViewById(R.id.insert_one_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Observable.create(new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                        accountDao.insert(new Account(0, "gzc新", "2020-11-1新", "承德市某个地方"));
                        emitter.onNext("");

                    }
                }).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<String>() {
                            @Override
                            public void accept(String s) throws Exception {
                                Log.e("guanzhenchuang","插入新数据");
                            }
                        });
            }
        });

    }

    public void insertData() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                for (int i = 0; i < 100; i++) {
                    accountDao.insert(new Account(0, "gzc" + i, "2020-11-1" + i, "承德市某个地方"));
                }
                emitter.onNext("");
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Toast.makeText(MainActivity.this, "导入成功", Toast.LENGTH_SHORT).show();
                    }
                });
    }
//        Observable.create(new ObservableOnSubscribe<String>() {
//            @Override
//            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Throwable {
////                for (int i = 0; i < 100; i++) {
////                    accountDao.insert(new Account(0,"gzc"+i,"2020-11-1"+i,"承德市某个地方"));
////                }
//                emitter.onNext("");
//            }
//        }).subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<String>() {
//                    @Override
//                    public void accept(String s) throws Throwable {
//                        Toast.makeText(MainActivity.this, "导入成功", Toast.LENGTH_SHORT).show();
//
//
//                    }
//                });
//    }
}