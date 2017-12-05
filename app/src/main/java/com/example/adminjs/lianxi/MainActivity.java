package com.example.adminjs.lianxi;

import android.database.Observable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.adminjs.lianxi.bean.NewsBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;


import retrofit2.Retrofit;

import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    XRecyclerView rv;
    int page=10;
    private List<NewsBean.NewslistBean> newslist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         rv = findViewById(R.id.rv);

         rv.setLayoutManager(new LinearLayoutManager(this));

        rv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(MainActivity.this,"已经是最新的啦！",Toast.LENGTH_SHORT).show();
                rv.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                page++;
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://api.tianapi.com/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .build();

                Service service = retrofit.create(Service.class);
                 rx.Observable<NewsBean> data = service.getData(page);

                data.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<NewsBean>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onNext(NewsBean newsBean) {
                                newslist = newsBean.getNewslist();
                                Adapter adapter = new Adapter(MainActivity.this, newslist);
                                rv.setAdapter(adapter);
                            }
                        });

                rv.loadMoreComplete();
            }
        });

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.tianapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        Service service = retrofit.create(Service.class);
        rx.Observable<NewsBean> data = service.getData(page);

        data.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NewsBean>() {
                    @Override
                    public void onCompleted() {


                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(NewsBean newsBean) {
                        newslist = newsBean.getNewslist();
                        Adapter adapter = new Adapter(MainActivity.this, newslist);
                        rv.setAdapter(adapter);
                    }
                });







    }
}
