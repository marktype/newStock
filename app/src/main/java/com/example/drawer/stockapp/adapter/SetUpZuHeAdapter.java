package com.example.drawer.stockapp.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.drawer.stockapp.R;
import com.example.drawer.stockapp.customview.widget.BubbleSeekBar;
import com.example.drawer.stockapp.listener.StockCallBack;
import com.example.drawer.stockapp.model.HeadIndex;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by 欢大哥 on 2016/8/11.
 */
public class SetUpZuHeAdapter extends BaseAdapter{
    private Context context;
    private ArrayList<HeadIndex> list ;
    private HashMap<Integer,Integer> map = new HashMap<>();
    private int progressNum;    //进度数
    private StockCallBack stockCallBack;
    public SetUpZuHeAdapter(Context context){
        this.context = context;
    }
    public void setData(ArrayList<HeadIndex> list){
        this.list = list;
        notifyDataSetChanged();

    }

    public void setOnStockPersentListener(StockCallBack stockCallBack){
        this.stockCallBack = stockCallBack;
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
        if (view == null){
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.set_up_item_layout,null);
            viewHolder.name = (TextView) view.findViewById(R.id.culue_name_txt);
            viewHolder.number = (TextView) view.findViewById(R.id.number);
            viewHolder.num = (TextView) view.findViewById(R.id.edit_txt);
            viewHolder.delete = (ImageView) view.findViewById(R.id.image_delete);
//            viewHolder.decImg = (ImageView) view.findViewById(R.id.image_dec);
//            viewHolder.addImg = (ImageView) view.findViewById(R.id.image_add);
            viewHolder.seekBar = (BubbleSeekBar) view.findViewById(R.id.grient_img);    //进度条
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }

        HeadIndex index = (HeadIndex) getItem(i);
        if (index.getType() == 2){
            viewHolder.delete.setVisibility(View.VISIBLE);
        }else {
            viewHolder.delete.setVisibility(View.INVISIBLE);
        }
        if (index.getIndexPersent() != null&& !TextUtils.isEmpty(index.getIndexPersent())){
                viewHolder.seekBar.setProgress(Integer.parseInt(index.getIndexPersent()));
            viewHolder.num.setText(index.getIndexPersent()+"%");
        }
        viewHolder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.remove(i);
                notifyDataSetChanged();
            }
        });

        viewHolder.name.setText(index.getIndexName());
        viewHolder.number.setText(index.getIndexNum());
        viewHolder.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                progressNum = 0;
                for (int key : map.keySet()) {
                    if (i != key){
                        progressNum += map.get(key);
                    }
                }
                if (seekBar.getProgress()>(99-progressNum)){
                    viewHolder.num.setText((99-progressNum)+"%");
                    seekBar.setProgress(99-progressNum);
                    map.put(i,(99-progressNum));
                    stockCallBack.OnBackStockPersent(i,(99-progressNum));
                    Toast.makeText(context,"您的仓位已经超过了99%",Toast.LENGTH_SHORT).show();
                }else {
                    viewHolder.num.setText(seekBar.getProgress()+"%");
                    seekBar.setProgress(seekBar.getProgress());
                    map.put(i,seekBar.getProgress());
                    stockCallBack.OnBackStockPersent(i,seekBar.getProgress());
                }
            }
        });


        map.put(i,viewHolder.seekBar.getProgress());


        return view;
    }

    private class ViewHolder{
        TextView name;
        TextView number;
        ImageView delete;
        ImageView decImg;
        ImageView addImg;
        TextView num;
        BubbleSeekBar seekBar;
    }


}
