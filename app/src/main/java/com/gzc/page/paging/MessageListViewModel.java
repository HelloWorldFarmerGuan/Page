package com.gzc.page.paging;

import android.icu.text.Transliterator;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.gzc.page.MessageListBean;

/**
 * author：gzc
 * date：2020/11/16
 * describe：
 */
public class MessageListViewModel extends ViewModel {
    public LiveData<PagedList<MessageListBean.DataBean>>userPagedList;

    public MessageListViewModel(){
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(true)
                .setPageSize(PositionalUserDataSource.PAGE_SIZE)
                .setPrefetchDistance(3)
                .setInitialLoadSizeHint(PositionalUserDataSource.PAGE_SIZE*3)
                .setMaxSize(65536* PositionalUserDataSource.PAGE_SIZE)
                .build();
        userPagedList = new LivePagedListBuilder<>(new UserDataSourceFactory(),config).build();
    }
}
