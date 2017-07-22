package com.example.drawer.stockapp.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.drawer.stockapp.R;
import com.example.drawer.stockapp.customview.MyGridView;
import com.example.drawer.stockapp.model.NewsInfo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by 欢大哥 on 2016/8/11.
 */
public class CollectionAdapter extends BaseAdapter {
    private static final int TYPE_ONE_IMAGE = 1;
    private static final int TYPE_THREE_IMAGE = 2;
    private static final int FRIEND_DYN = 3;
    private Context context;
    private ArrayList<NewsInfo> list;
    private LayoutInflater mInflater;
    public CollectionAdapter(Context context){
        this.context = context;
        mInflater = LayoutInflater.from(context);
    }
    public void setData(ArrayList<NewsInfo> list){
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
    public int getItemViewType(int position) {
        return list.get(position).getType();
    }

    @Override
    public int getViewTypeCount() {
        return 4;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        ViewHolderTwo viewHolderTwo;
        ViewHolderDyn viewHolderDyn;
        int type = getItemViewType(i);
        if (type == TYPE_ONE_IMAGE){
            if (view == null){
                view = mInflater.inflate(R.layout.index_item,null);
                viewHolder = new ViewHolder();
                viewHolder.name = (TextView) view.findViewById(R.id.index_name);
                viewHolder.time = (TextView) view.findViewById(R.id.index_num);
                viewHolder.persent = (TextView) view.findViewById(R.id.index_persent);
                viewHolder.headImahe = (ImageView) view.findViewById(R.id.head_zixun_item);

                view.setTag(viewHolder);
            }else {
                viewHolder = (ViewHolder) view.getTag();
            }
            NewsInfo headIndex = (NewsInfo) getItem(i);
            viewHolder.name.setText(headIndex.getTitle());
            viewHolder.time.setText(headIndex.getTime());
            viewHolder.persent.setText(headIndex.getPeopleNum());
            Picasso.with(context).load(headIndex.getImage()).into(viewHolder.headImahe);
        }else if (type == TYPE_THREE_IMAGE){
            if (view == null){
                view = mInflater.inflate(R.layout.zixun_list_three_image_item,null);
                viewHolderTwo = new ViewHolderTwo();
                viewHolderTwo.title = (TextView) view.findViewById(R.id.title_txt);
                viewHolderTwo.xontent = (TextView) view.findViewById(R.id.content_txt);
                viewHolderTwo.oneImage = (ImageView) view.findViewById(R.id.news_image_one);
                viewHolderTwo.twoImage = (ImageView) view.findViewById(R.id.news_image_two);
                viewHolderTwo.threeImage = (ImageView) view.findViewById(R.id.news_image_three);
                viewHolderTwo.commentNum = (TextView) view.findViewById(R.id.index_persent_three);
                viewHolderTwo.timeThree = (TextView) view.findViewById(R.id.index_num_three);
                view.setTag(viewHolderTwo);
            }else {
                viewHolderTwo = (ViewHolderTwo) view.getTag();
            }



        }else if (type == FRIEND_DYN){
            if (view == null){
                viewHolderDyn = new ViewHolderDyn();

                view = LayoutInflater.from(context).inflate(R.layout.dongtai_item_layout,null);
                viewHolderDyn.head = (ImageView) view.findViewById(R.id.dongtai_image);
                viewHolderDyn.name = (TextView) view.findViewById(R.id.dongtai_name);
                viewHolderDyn.content = (TextView) view.findViewById(R.id.dongtai_content);
                viewHolderDyn.contentImage = (MyGridView) view.findViewById(R.id.dongtai_cntent_image);
                viewHolderDyn.time = (TextView) view.findViewById(R.id.dongtai_time);
                view.setTag(viewHolderDyn);
            }else {
                viewHolderDyn = (ViewHolderDyn) view.getTag();
            }
            NewsInfo headIndex = (NewsInfo) getItem(i);
            if (!TextUtils.isEmpty(headIndex.getDynImage())){
                Picasso.with(context).load(headIndex.getDynImage()).into(viewHolderDyn.head);
            }

            ImageAdapter adapter = new ImageAdapter(context);
            adapter.setData(headIndex.getDynContentImage());
            viewHolderDyn.contentImage.setAdapter(adapter);
            viewHolderDyn.name.setText(headIndex.getDynName());
            viewHolderDyn.content.setText(headIndex.getDynContent());
            viewHolderDyn.time.setText(headIndex.getDynTime());
        }



        return view;
    }

    private class ViewHolder{
        TextView name;
        TextView time;
        TextView persent;
        ImageView headImahe;
    }
    private class ViewHolderTwo{
        TextView title;
        TextView xontent;
        TextView timeThree;
        TextView commentNum;
        ImageView oneImage;
        ImageView twoImage;
        ImageView threeImage;
    }
    private class ViewHolderDyn{
        TextView name;
        ImageView head;
        TextView content;
        MyGridView contentImage;
        TextView time;
        TextView zhuanFaNum;
        TextView commentNum;
        TextView goodNum;
    }
}
