package com.example.adminjs.lianxi;



import com.example.adminjs.lianxi.bean.NewsBean;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;


/**
 * Created by Adminjs on 2017/12/5.
 */

public interface Service {
    @GET("nba/?key=71e58b5b2f930eaf1f937407acde08fe&num=10")
    Observable<NewsBean> getData(@Query("num") Integer num);
}
