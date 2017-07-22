package com.example.drawer.stockapp.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.drawer.stockapp.R;

import java.util.ArrayList;


/**
 * Created by Administrator on 2017/1/20 0020.
 */

public class GvPopupWindow extends BaseAdapter {
    private ArrayList<String> list;
    private Context context;
    private int selectPosition=-1;

    public GvPopupWindow(Context context, ArrayList<String > list){
        this.context=context;
        this.list=list;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    public void clearSelection(int position){
        selectPosition=position;
    }
    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        viewHolder holder;
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.templetepopubwindow,null);
            holder=new viewHolder();
            holder.btn=(TextView)convertView.findViewById(R.id.btnTempletePopwindow);
            convertView.setTag(holder);
        }
        holder=(viewHolder) convertView.getTag();
        if(selectPosition==position){
            holder.btn.setBackgroundResource(R.drawable.rect_circle_blue_shape);
            holder.btn.setTextColor(Color.parseColor("#ffffff"));
        }else{
            holder.btn.setBackgroundResource(R.drawable.rect_circle_gray_shape);
            holder.btn.setTextColor(Color.parseColor("#000000"));
        }
        holder.btn.setText(list.get(position));
        return convertView;
    }
    class viewHolder{
        TextView btn;
    }
}
