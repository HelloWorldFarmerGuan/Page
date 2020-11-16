package com.gzc.page.api;

import com.gzc.page.MessageListBean;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * author：gzc
 * date：2020/11/16
 * describe：
 */
public interface Api {
    //bid=1002&version=v1.0.0&timestamp=20201116165146&system=android&source=a.6.1.5&weiqtoken=axntbubwbtmuhvqv16055166985fb23d9aa64db&count=20&page=1&type=system&sign=d67e7a3d810d016feea4935f1906d7de

    /**
     * bid=1002&
     * version=v1.0.0&
     * timestamp=20201116165146&
     * system=android&
     * source=a.6.1.5&
     * weiqtoken=axntbubwbtmuhvqv16055166985fb23d9aa64db&
     * count=20&
     * page=1&
     * type=system&
     * sign=d67e7a3d810d016feea4935f1906d7de
     */
    @POST("MessageMedia/messagesList")
    @FormUrlEncoded
    Call<MessageListBean>getMessageList(@FieldMap Map<String,Object>field);

//
//    @GET("/userList/")
//    Call<UserBean> getUserListByPositional(@Query("start") int start,
//                                            @Query("count") int count);
//
//    @GET("/userList2/")
//    Call<UserBean> getUserListByPageSize(@Query("pageIndex") int pageIndex,
//                                          @Query("pageSize") int pageSize);
//
//
//    @GET("/userList3/")
//    Call<UserBean> getUserListByItemPaged(@Query("nextId") int nextId,
//                                           @Query("pageCount") int pageSize);
}
