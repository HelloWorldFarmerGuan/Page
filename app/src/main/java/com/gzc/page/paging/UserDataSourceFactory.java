package com.gzc.page.paging;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.gzc.page.MessageListBean;

/**
 * author：gzc
 * date：2020/11/16
 * describe：
 */
public class UserDataSourceFactory extends DataSource.Factory<Integer, MessageListBean.DataBean> {

    // 这里可以根据需求换成另外两种DataSource即可。
    private MutableLiveData<PositionalUserDataSource> liveDataSource = new MutableLiveData<>();

    @NonNull
    @Override
    public DataSource<Integer, MessageListBean.DataBean> create() {
        PositionalUserDataSource source = new PositionalUserDataSource();
        liveDataSource.postValue(source);
        return source;
    }

}


