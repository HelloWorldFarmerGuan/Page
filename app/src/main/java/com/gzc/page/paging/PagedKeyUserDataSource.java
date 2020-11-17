package com.gzc.page.paging;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.gzc.page.MessageListBean;
import com.gzc.page.api.RetrofitClient;
import com.gzc.page.utils.Md5Tools;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * author：gzc
 * date：2020/11/16
 * describe：
 */
public class PagedKeyUserDataSource extends PageKeyedDataSource<Integer, MessageListBean.DataBean> {

    public static final int FIRST_PAGE = 1;

    private final Map<String, Object> paramsMap = new LinkedHashMap<>();

    protected String sign(Map<String, Object> paramsMap) {
        StringBuilder sb = new StringBuilder();
        for (String key : paramsMap.keySet()) {
            sb.append(key + "=" + paramsMap.get(key) + "&");
        }
        sb.append("key=37cx75atx45pi9biowo709o1a38c49m");
        return Md5Tools.hexdigest(sb.toString());
    }

    /**
     * 加载第一页数据
     *
     * @param params
     * @param callback
     */
    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params,
                            @NonNull final LoadInitialCallback<Integer, MessageListBean.DataBean> callback) {
        paramsMap.clear();
        paramsMap.put("bid","1002");
        paramsMap.put("version","v1.0.0");
        paramsMap.put("timestamp","20201116165146");
        paramsMap.put("system","android");
        paramsMap.put("source","a.6.1.5");
        paramsMap.put("weiqtoken","pudvkvgtntjtnxlw16055272235fb266b7028c7");
        paramsMap.put("count",Constants.PAGE_SIZE);
        paramsMap.put("page",FIRST_PAGE);
        paramsMap.put("type","system");
        paramsMap.put("sign",sign(paramsMap));

        RetrofitClient.getInstance().getApi().
                getMessageList(paramsMap).
                enqueue(new Callback<MessageListBean>() {
                    @Override
                    public void onResponse(@NonNull Call<MessageListBean> call,
                                           @NonNull Response<MessageListBean> response) {

                        if (response.isSuccessful() && null != response.body()) {
                            callback.onResult(response.body().getData(),
                                    null,
                                    FIRST_PAGE + 1);
                        }
                    }

                    @Override
                    public void onFailure(Call<MessageListBean> call, Throwable t) {

                    }
                });

    }

    /**
     * 加载下一页的数据
     *
     * @param params
     * @param callback
     */
    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params,
                          @NonNull final LoadCallback<Integer, MessageListBean.DataBean> callback) {
        paramsMap.clear();
        paramsMap.put("bid","1002");
        paramsMap.put("version","v1.0.0");
        paramsMap.put("timestamp","20201116165146");
        paramsMap.put("system","android");
        paramsMap.put("source","a.6.1.5");
        paramsMap.put("weiqtoken","pudvkvgtntjtnxlw16055272235fb266b7028c7");
        paramsMap.put("count",Constants.PAGE_SIZE);
        //params.key就是具体的页码
        paramsMap.put("page",params.key);
        paramsMap.put("type","system");
        paramsMap.put("sign",sign(paramsMap));

        RetrofitClient.getInstance().getApi().
                getMessageList(paramsMap).
                enqueue(new Callback<MessageListBean>() {
                    @Override
                    public void onResponse(@NonNull Call<MessageListBean> call, @NonNull Response<MessageListBean> response) {
                        if (response.isSuccessful() && null != response.body()) {
                            List<MessageListBean.DataBean> userList = response.body().getData();
                            boolean hasMoreData = userList != null && userList.size() >= Constants.PAGE_SIZE;

                            callback.onResult(userList, hasMoreData ? params.key + 1 : null);
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<MessageListBean> call, @NonNull Throwable t) {

                    }
                });
    }


    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params,
                           @NonNull LoadCallback<Integer, MessageListBean.DataBean> callback) {

    }

}

