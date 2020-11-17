package com.gzc.page.paging;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.paging.ItemKeyedDataSource;

import com.gzc.page.MessageListBean;
import com.gzc.page.api.RetrofitClient;
import com.gzc.page.utils.Md5Tools;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * author：gzc
 * date：2020/11/16
 * describe：
 */
public class ItemKeyedUserDataSource extends ItemKeyedDataSource<Integer, MessageListBean.DataBean> {

    private final Map<String,Object>paramsMap = new LinkedHashMap<>();

    private int startPosition = 1;


    protected String sign(Map<String, Object> paramsMap) {
        StringBuilder sb = new StringBuilder();
        for (String key : paramsMap.keySet()) {
            sb.append(key + "=" + paramsMap.get(key) + "&");
        }
        sb.append("key=37cx75atx45pi9biowo709o1a38c49m");
        return Md5Tools.hexdigest(sb.toString());
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params,
                            @NonNull final LoadInitialCallback<MessageListBean.DataBean> callback) {
        paramsMap.clear();
        paramsMap.put("bid","1002");
        paramsMap.put("version","v1.0.0");
        paramsMap.put("timestamp","20201116165146");
        paramsMap.put("system","android");
        paramsMap.put("source","a.6.1.5");
        paramsMap.put("weiqtoken","pudvkvgtntjtnxlw16055272235fb266b7028c7");
        paramsMap.put("count",Constants.PAGE_SIZE);
        paramsMap.put("page",startPosition);
        paramsMap.put("type","system");
        paramsMap.put("sign",sign(paramsMap));
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
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull final LoadCallback<MessageListBean.DataBean> callback) {
        //params.key是getKey的返回值
        Log.e("guanzhenchuang","params:"+params.key);
        paramsMap.clear();
        paramsMap.put("bid","1002");
        paramsMap.put("version","v1.0.0");
        paramsMap.put("timestamp","20201116165146");
        paramsMap.put("system","android");
        paramsMap.put("source","a.6.1.5");
        paramsMap.put("weiqtoken","pudvkvgtntjtnxlw16055272235fb266b7028c7");
        paramsMap.put("count",Constants.PAGE_SIZE);
        paramsMap.put("page",startPosition++);
        paramsMap.put("type","system");
        paramsMap.put("sign",sign(paramsMap));
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
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<MessageListBean.DataBean> callback) {
    }

    @NonNull
    @Override
    public Integer getKey(@NonNull MessageListBean.DataBean item) {
        return Integer.parseInt(item.getId());
    }
}


