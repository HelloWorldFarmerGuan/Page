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
                .setEnablePlaceholders(false)//是否启用占位符，若为true，则视为固定数量的item
                .setPageSize(Constants.PAGE_SIZE)
                .setPrefetchDistance(3)
                .setInitialLoadSizeHint(Constants.PAGE_SIZE*3)
                .setMaxSize(65536* Constants.PAGE_SIZE)
                .build();
        userPagedList = new LivePagedListBuilder<>(new UserDataSourceFactory(),config).build();
    }
}
