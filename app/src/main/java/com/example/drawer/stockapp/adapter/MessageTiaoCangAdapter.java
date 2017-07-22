package com.example.drawer.stockapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.drawer.stockapp.R;
import com.example.drawer.stockapp.activity.HistoryRecordActivity;
import com.example.drawer.stockapp.activity.LiangHuaCelueDetialActivity;
import com.example.drawer.stockapp.customview.MyListView;
import com.example.drawer.stockapp.model.HistoryTiaoCangInfo;

import java.util.ArrayList;

/**
 * Created by 欢大哥 on 2016/9/26.
 */
public class MessageTiaoCangAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<HistoryTiaoCangInfo> list;
    public MessageTiaoCangAdapter(Context context){
        this.context = context;
    }
    public void setData(ArrayList<HistoryTiaoCangInfo> list){
        this.list = list;
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.tiaocang_message_item_layout,null);
            viewHolder = new ViewHolder();
            viewHolder.title = (TextView) view.findViewById(R.id.zuhe_name);
            viewHolder.time = (TextView) view.findViewById(R.id.time_txt);
            viewHolder.listView = (MyListView) view.findViewById(R.id.tiaocang_listview);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        view.setBackgroundColor(context.getResources().getColor(R.color.write_color));
        final HistoryTiaoCangInfo historyTiaoCangInfo = (HistoryTiaoCangInfo) getItem(i);
        viewHolder.title.setText(historyTiaoCangInfo.getTitle());
        viewHolder.time.setText(historyTiaoCangInfo.getTime());
        TiaoCangAdapter adapter = new TiaoCangAdapter(context);
        adapter.setData(historyTiaoCangInfo.getList());
        viewHolder.listView.setAdapter(adapter);
        viewHolder.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(context,HistoryRecordActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra(LiangHuaCelueDetialActivity.LIANGHUA_ID,historyTiaoCangInfo.getId());
                context.startActivity(intent);
            }
        });
        return view;
    }


    private class ViewHolder{
        TextView title;
        TextView time;
        MyListView listView;
    }
}
