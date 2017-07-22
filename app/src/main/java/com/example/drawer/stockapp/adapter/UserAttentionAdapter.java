package com.example.drawer.stockapp.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.drawer.stockapp.R;
import com.example.drawer.stockapp.model.UserAttentionBean;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by 欢大哥 on 2016/8/9.
 * 用户关注
 */
public class UserAttentionAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<UserAttentionBean> list;
    public UserAttentionAdapter(Context context){
        this.context = context;
    }
    public void setData(ArrayList<UserAttentionBean> list){
        this.list = list;
        notifyDataSetChanged();
    }
    public void addData(ArrayList<UserAttentionBean> list){
        this.list.addAll(list);
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
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null){
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.item_user_attention,null);
            viewHolder.userIcon = (CircleImageView) view.findViewById(R.id.item_attention_user_icon);
            viewHolder.nickName = (TextView) view.findViewById(R.id.item_attention_user_nickname);
            viewHolder.attention = (TextView) view.findViewById(R.id.item_attention);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        UserAttentionBean info = (UserAttentionBean) getItem(i);
        Picasso.with(context).load(info.getAvatar()).placeholder(R.mipmap.usericon).into(viewHolder.userIcon);
        if (info.getNickName() != null&& !TextUtils.isEmpty(info.getNickName())){
            viewHolder.nickName.setText(info.getNickName());
        }else {
            viewHolder.nickName.setText(R.string.user_name);
        }
        if (info.isConcerns()){
            viewHolder.attention.setText("取消关注");
            viewHolder.attention.setTextColor(context.getResources().getColor(R.color.text_two));
        }else{
            viewHolder.attention.setText("点击关注");
            viewHolder.attention.setTextColor(context.getResources().getColor(R.color.content_bg));
        }

        return view;
    }

    private class ViewHolder{
        CircleImageView userIcon;
        TextView nickName;
        TextView attention;

    }
}
