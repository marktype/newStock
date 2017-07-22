package com.example.drawer.stockapp.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.drawer.stockapp.R;
import com.example.drawer.stockapp.customview.MyGridView;
import com.example.drawer.stockapp.listener.TypeCallBack;
import com.example.drawer.stockapp.model.TrendsInfo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by 欢大哥 on 2016/6/15.
 * 动态item
 */
public class TrendsAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<TrendsInfo> list;
    private TypeCallBack callBack;
    public TrendsAdapter(Context context,TypeCallBack callBack){
        this.context = context;
        this.callBack = callBack;
    }
    public void setData(ArrayList<TrendsInfo> list){
        this.list = list;
        notifyDataSetChanged();
    }
    public void addData(ArrayList<TrendsInfo> list){
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
    public View getView(final int i, View view, ViewGroup viewGroup) {
        final ViewHolder viewHolder;
        ImageAdapter adapter = null;
        if (view == null){
            viewHolder = new ViewHolder();

            view = LayoutInflater.from(context).inflate(R.layout.dongtai_item_layout,null);
            viewHolder.head = (ImageView) view.findViewById(R.id.dongtai_image);
            viewHolder.name = (TextView) view.findViewById(R.id.dongtai_name);
            viewHolder.content = (TextView) view.findViewById(R.id.dongtai_content);
            viewHolder.contentImage = (MyGridView) view.findViewById(R.id.dongtai_cntent_image);
            viewHolder.time = (TextView) view.findViewById(R.id.dongtai_time);
//            viewHolder.collect = (ImageView) view.findViewById(R.id.collect_info_img);
            viewHolder.zhuanFaNum = (TextView) view.findViewById(R.id.dongtai_zhuanfa);
            viewHolder.commentNum = (TextView) view.findViewById(R.id.dongtai_pinglun);
            viewHolder.goodNum = (TextView) view.findViewById(R.id.dongtai_zan);
            viewHolder.likes = (ImageView) view.findViewById(R.id.dianzan_img);    //点赞图
            viewHolder.mChick = (RelativeLayout) view.findViewById(R.id.zan_relate);   //点赞
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        final TrendsInfo info = (TrendsInfo) getItem(i);
            Picasso.with(context).load(info.getImage()).placeholder(R.mipmap.usericon).into(viewHolder.head);

            adapter = new ImageAdapter(context);
        adapter.setData(info.getContentImage());
        viewHolder.contentImage.setAdapter(adapter);
        if (info.getName()!= null&& !TextUtils.isEmpty(info.getName())){
            viewHolder.name.setText(info.getName());
        }else {
            viewHolder.name.setText(R.string.user_name)                                                                 ;
        }
        viewHolder.content.setText(info.getContent());
        viewHolder.time.setText(info.getTime());
        viewHolder.zhuanFaNum.setText(info.getZhuanFaNum()+"");
        viewHolder.commentNum.setText(info.getCommentNum()+"");
        viewHolder.goodNum.setText(info.getGoodNum()+"");

//        viewHolder.head.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.d("MyDynamicActivity", "你点击的头像");
//                Intent intent = new Intent(context,UserInfoDetialActivity.class);
//                intent.putExtra(UserInfoDetialActivity.USER_ID,info.getUserID());
//                context.startActivity(intent);
//            }
//        });

        if (info.getLikes()){
            viewHolder.likes.setImageResource(R.mipmap.dianzan_select);
        }else {
            viewHolder.likes.setImageResource(R.mipmap.dianzan_unselect);
        }


//        if (info.getCollect()){
//            viewHolder.collect.setImageResource(R.mipmap.collection_yes);
//        }else {
//            viewHolder.collect.setImageResource(R.mipmap.collection);
//        }
//
//        //收藏
//        viewHolder.collect.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (info.getCollect()){
//                    viewHolder.collect.setImageResource(R.mipmap.collection);
//                    info.setCollect(false);
//                    callBack.setCollectOrLikes(i,"1");
//                }else {
//                    viewHolder.collect.setImageResource(R.mipmap.collection_yes);
//                    info.setCollect(true);
//                    callBack.setCollectOrLikes(i,"0");
//                }
//            }
//        });
        //转发
        viewHolder.zhuanFaNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBack.setDongTaiType(i,2);
            }
        });
//        //评论
//        viewHolder.commentNum.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                callBack.setDongTaiType(i,1);
//            }
//        });
        //点赞
        viewHolder.mChick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (info.getLikes()){
                    callBack.setCollectOrLikes(i,"1");    //取消点赞
                    viewHolder.likes.setImageResource(R.mipmap.dianzan_unselect);
                    int num = (Integer.parseInt(viewHolder.goodNum.getText().toString())-1);
                    viewHolder.goodNum.setText(num+"");
                    info.setLikes(false);
                    info.setGoodNum(num);
                    list.set(i,info);
                }else {
                    callBack.setCollectOrLikes(i,"0");    //点赞
                    viewHolder.likes.setImageResource(R.mipmap.dianzan_select);
                    int num = (Integer.parseInt(viewHolder.goodNum.getText().toString())+1);
                    viewHolder.goodNum.setText(num+"");
                    info.setLikes(true);
                    info.setGoodNum(num);
                    list.set(i,info);
                }
            }
        });

        return view;
    }

    private class ViewHolder{
        TextView name;
        ImageView head;
        TextView content;
        MyGridView contentImage;
        TextView time;
        TextView zhuanFaNum;
        TextView commentNum;
        TextView goodNum;
        ImageView likes;
        RelativeLayout mChick;
//        ImageView collect;
    }

    }
