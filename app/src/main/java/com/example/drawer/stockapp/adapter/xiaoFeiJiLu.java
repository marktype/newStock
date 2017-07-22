package com.example.drawer.stockapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.drawer.stockapp.R;
import com.example.drawer.stockapp.model.xiaoFeiJilu;

import java.util.List;

/**
 * Created by Administrator on 2017/4/19 0019.
 */

public class xiaoFeiJiLu extends BaseAdapter {
    private Context context;
    private List<xiaoFeiJilu.ResultBean.DataBean> list;

    public xiaoFeiJiLu(Context context, List<xiaoFeiJilu.ResultBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        viewHolder holder;
        if(convertView==null){
            holder=new viewHolder();
            convertView= LayoutInflater.from(context).inflate(R.layout.item_xiaofeijlu,null);
            holder.tv1=(TextView)convertView.findViewById(R.id.tv1);
            holder.tv2=(TextView)convertView.findViewById(R.id.tv2);
            holder.tv3=(TextView)convertView.findViewById(R.id.tv3);
            convertView.setTag(holder);
        }
        holder=(viewHolder)convertView.getTag();
        holder.tv1.setText("积分"+list.get(position).getNum());
        holder.tv2.setText(list.get(position).getRemark());
        holder.tv3.setText(list.get(position).getCreateTime());
        return convertView;
    }

    class viewHolder{
        TextView tv1;
        TextView tv2;
        TextView tv3;
    }

}
