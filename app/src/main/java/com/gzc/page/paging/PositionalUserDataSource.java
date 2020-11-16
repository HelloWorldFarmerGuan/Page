package com.gzc.page.paging;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.paging.PositionalDataSource;

import com.google.gson.Gson;
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
public class PositionalUserDataSource extends PositionalDataSource<MessageListBean.DataBean> {

    public static final int PAGE_SIZE = 20 ;

    private final Map<String,Object> paramsMap = new LinkedHashMap<>();


    protected String sign(Map<String,Object>paramsMap){
        StringBuilder sb = new StringBuilder();
        for(String key:paramsMap.keySet()){
            sb.append(key+"="+paramsMap.get(key)+"&");
        }
        sb.append("key=37cx75atx45pi9biowo709o1a38c49m");
        return Md5Tools.hexdigest(sb.toString());
    }
    /**
     * 首次加载数据
     */
    @Override
    public void loadInitial(@NonNull LoadInitialParams params,
                            @NonNull final LoadInitialCallback<MessageListBean.DataBean> callback) {
        final int startPosition = 0;
        paramsMap.clear();
        paramsMap.put("bid","1002");
        paramsMap.put("version","v1.0.0");
        paramsMap.put("timestamp","20201116165146");
        paramsMap.put("system","android");
        paramsMap.put("source","a.6.1.5");
        paramsMap.put("weiqtoken","pudvkvgtntjtnxlw16055272235fb266b7028c7");
        paramsMap.put("count",PAGE_SIZE);
        paramsMap.put("page",startPosition+1);
        paramsMap.put("type","system");
        paramsMap.put("sign",sign(paramsMap));
        RetrofitClient.
                getInstance().
                getApi().
                getMessageList(paramsMap).
                enqueue(new Callback<MessageListBean>() {
                    @Override
                    public void onResponse(@NonNull Call<MessageListBean> call,
                                           @NonNull Response<MessageListBean> response) {
                        if(response.isSuccessful()) {
                            MessageListBean body = response.body();
                            Log.e("guanzhenchuang",new Gson().toJson(body));
                            if(null != body) {
                                callback.onResult(body.getData(),startPosition, Integer.MAX_VALUE);
                            }
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<MessageListBean> call, @NonNull Throwable t) {
                        Log.e("guanzhenchuang","onFailure:"+t.getMessage());
                    }
                });
    }

    /**
     *
     * 第 N 页加载数据
     *
     * @param params
     * @param callback
     */
    @Override
    public void loadRange(@NonNull LoadRangeParams params,
                          @NonNull final LoadRangeCallback<MessageListBean.DataBean> callback) {
        Log.e("guanzhenchuang","startPosition:"+params.startPosition);
        paramsMap.clear();
        paramsMap.put("bid","1002");
        paramsMap.put("version","v1.0.0");
        paramsMap.put("timestamp","20201116165146");
        paramsMap.put("system","android");
        paramsMap.put("source","a.6.1.5");
        paramsMap.put("weiqtoken","pudvkvgtntjtnxlw16055272235fb266b7028c7");
        paramsMap.put("count",PAGE_SIZE);
        paramsMap.put("page",params.startPosition+1);
        paramsMap.put("type","system");
        paramsMap.put("sign",sign(paramsMap));
        RetrofitClient.
                getInstance().
                getApi().
                getMessageList(paramsMap).
                enqueue(new Callback<MessageListBean>() {
                    @Override
                    public void onResponse(@NonNull Call<MessageListBean> call, @NonNull Response<MessageListBean> response) {
                        if(response.isSuccessful() && null != response.body()){
                            MessageListBean body = response.body();
                            callback.onResult(body.getData());
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<MessageListBean> call, @NonNull Throwable t) {}
                });
    }
}


