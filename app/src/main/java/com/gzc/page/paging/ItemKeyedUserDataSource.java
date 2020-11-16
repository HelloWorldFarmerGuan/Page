package com.gzc.page.paging;

import androidx.annotation.NonNull;
import androidx.paging.ItemKeyedDataSource;

import com.gzc.page.MessageListBean;
import com.gzc.page.api.RetrofitClient;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * author：gzc
 * date：2020/11/16
 * describe：
 */
public class ItemKeyedUserDataSource extends ItemKeyedDataSource<String, MessageListBean.DataBean> {

    public static final int PAGE_SIZE = 20;

    private final Map<String,Object>paramsMap;

    public ItemKeyedUserDataSource() {
        paramsMap = new HashMap<>();
        paramsMap.put("bid","1002");
        paramsMap.put("version","v1.0.0");
        paramsMap.put("timestamp","20201116165146");
        paramsMap.put("system","android");
        paramsMap.put("source","a.6.1.5");
        paramsMap.put("weiqtoken","axntbubwbtmuhvqv16055166985fb23d9aa64db");
        paramsMap.put("count",PAGE_SIZE);
        paramsMap.put("page",1);
        paramsMap.put("type","system");
        paramsMap.put("sign","d67e7a3d810d016feea4935f1906d7de");

    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<String> params,
                            @NonNull final LoadInitialCallback<MessageListBean.DataBean> callback) {

        RetrofitClient.
                getInstance().
                getApi().
                getMessageList(paramsMap).
                enqueue(new Callback<MessageListBean>() {
                    @Override
                    public void onResponse(@NonNull Call<MessageListBean> call, @NonNull Response<MessageListBean> response) {
                        if (response.isSuccessful() && null != response.body()) {
                            callback.onResult(response.body().getData());
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<MessageListBean> call, @NonNull Throwable t) {

                    }
                });
    }

    @Override
    public void loadAfter(@NonNull LoadParams<String> params, @NonNull final LoadCallback<MessageListBean.DataBean> callback) {
        RetrofitClient.getInstance().
                getApi().
                getMessageList(paramsMap).
                enqueue(new Callback<MessageListBean>() {
                    @Override
                    public void onResponse(@NonNull Call<MessageListBean> call, @NonNull Response<MessageListBean> response) {
                        if (response.isSuccessful() && null != response.body()) {
                            callback.onResult(response.body().getData());
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<MessageListBean> call, @NonNull Throwable t) {

                    }
                });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<String> params, @NonNull LoadCallback<MessageListBean.DataBean> callback) {
    }

    @NonNull
    @Override
    public String getKey(@NonNull MessageListBean.DataBean item) {
        return item.getId();
    }
}


