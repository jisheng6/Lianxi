package com.example.adminjs.lianxi;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.adminjs.lianxi.bean.NewsBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by dream on 2017/12/5.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder>{

    Context context;
    List<NewsBean.NewslistBean> list;

    public Adapter(Context context, List<NewsBean.NewslistBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_home,parent,false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
      holder.title.setText(list.get(position).getTitle());
      holder.tv2.setText(list.get(position).getCtime());
      holder.sdv.setImageURI(list.get(position).getPicUrl());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
         TextView title,tv2;
         SimpleDraweeView sdv;
        public MyViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            tv2 = itemView.findViewById(R.id.tv2);
            sdv = itemView.findViewById(R.id.sdv);
        }
    }
}
