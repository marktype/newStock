package com.example.drawer.stockapp.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.drawer.stockapp.R;
import com.example.drawer.stockapp.customview.CanvasView;
import com.example.drawer.stockapp.customview.CanvasViewThree;
import com.example.drawer.stockapp.model.CeLueInfo;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by 欢大哥 on 2016/6/15.
 */
public class CeLueAdapter extends BaseAdapter {
    DecimalFormat df =new DecimalFormat("#0.00");   //保留两位小数
    private Context context;
    private ArrayList<CeLueInfo> list;
    public CeLueAdapter(Context context){
        this.context = context;
    }
    public void setData(ArrayList<CeLueInfo> list){
//        list.clear();
        this.list = list;
        Log.d("dsdsdsds", "setData: "+list);
        notifyDataSetChanged();
}
    public void addData(ArrayList<CeLueInfo> list){
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
        Log.d("dsdsdsdsds", "setData: "+list);

        ViewHolder viewHolder;
        if (view == null){
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.lianghuacelue_item_layout,null);
//            viewHolder.headImage = (ImageView) view.findViewById(R.id.celue_image);
            viewHolder.tvName = (TextView) view.findViewById(R.id.tv_name);
            viewHolder.tvUse = (TextView) view.findViewById(R.id.tvNumber);
           viewHolder.tvtitle_one_name = (TextView) view.findViewById(R.id.title_one_name);
            viewHolder.tvtitle_two_name = (TextView) view.findViewById(R.id.title_two_name);
            viewHolder.tvtitle_three_name = (TextView) view.findViewById(R.id.title_three_name);
//            viewHolder.rateNum = (TextView) view.findViewById(R.id.rate_num);
////            viewHolder.name = (TextView) view.findViewById(R.id.celue_people_name);
//            viewHolder.gengtouTxt = (TextView) view.findViewById(R.id.gengtou_num);
//            viewHolder.canvasViewThree = (CanvasViewThree) view.findViewById(R.id.chart1);
//            viewHolder.nowGen = (TextView) view.findViewById(R.id.celue_name_other);
//            viewHolder.status = (TextView) view.findViewById(R.id.status_celue);
//            viewHolder.zhaomuName = (TextView) view.findViewById(R.id.zhaomu_name);
//            viewHolder.titleLayout = (RelativeLayout) view.findViewById(R.id.celue_relat_item);
//            viewHolder.runTime = (TextView) view.findViewById(R.id.run_time_txt);
//            viewHolder.layout = (RelativeLayout) view.findViewById(R.id.lianghuacelue_item_relat);
//            viewHolder.zuheName = (TextView) view.findViewById(R.id.lianghuacelue_title);
//            viewHolder.levelImage = (ImageView) view.findViewById(R.id.line_celue_three);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        //CeLueInfo info = (CeLueInfo) getItem(i);
//        if (info.getType() == 1){
//            viewHolder.status.setText("运行中");
//            viewHolder.nowGen.setVisibility(View.GONE);
//            viewHolder.zhaomuName.setText("当前收益");
//            viewHolder.runTime.setVisibility(View.VISIBLE);
//            viewHolder.runTime.setText(info.getRunTime().substring(0,10)+" 开始运行");
//        }else if (info.getType() == 2||info.getType() == 5){
//            viewHolder.status.setText("招募中");
//            viewHolder.runTime.setVisibility(View.INVISIBLE);
//            viewHolder.nowGen.setVisibility(View.GONE);
//            viewHolder.zhaomuName.setText("招募进度");
//        }else if (info.getType() == 3){
//            viewHolder.status.setText("已结束");
//            viewHolder.runTime.setVisibility(View.INVISIBLE);
//            viewHolder.nowGen.setVisibility(View.GONE);
//            viewHolder.zhaomuName.setText("实现收益");
//        }else {
//            viewHolder.status.setText("");
//            viewHolder.runTime.setVisibility(View.INVISIBLE);
//            viewHolder.nowGen.setVisibility(View.GONE);
//        }
//        viewHolder.titleLayout.setBackgroundColor(context.getResources().getColor(R.color.write_color));
//        double persent = Double.parseDouble(info.getCeluePersent());
//        if (persent>0){
//            viewHolder.persent.setTextColor(context.getResources().getColor(R.color.red));
//            viewHolder.persent.setText(df.format(persent)+"%");
//        }else if (persent<0){
//            viewHolder.persent.setText(df.format(persent)+"%");
//            viewHolder.persent.setTextColor(context.getResources().getColor(R.color.green_color));
//        }else {
//            viewHolder.persent.setText("0.00%");
//            viewHolder.persent.setTextColor(context.getResources().getColor(android.R.color.black));
//        }

        CeLueInfo ceLueInfo = list.get(i);

        viewHolder.tvName.setText(list.get(i).getUserName());
        viewHolder.tvUse.setText((int)list.get(i).getAlongCount()+"");
        viewHolder.tvtitle_three_name.setText(list.get(i).getProfitLossProportion()+"%");
        //累计收益率
        if(list.get(i).getCumulativeReturn()>0){
            viewHolder.tvtitle_one_name.setTextColor(context.getResources().getColor(R.color.red));
            viewHolder.tvtitle_one_name.setText(list.get(i).getCumulativeReturn()+"%");
        }else if(list.get(i).getCumulativeReturn()<0){
            viewHolder.tvtitle_one_name.setTextColor(context.getResources().getColor(R.color.green_color));
            viewHolder.tvtitle_one_name.setText(list.get(i).getCumulativeReturn()+"%");
        }else{
            viewHolder.tvtitle_one_name.setTextColor(context.getResources().getColor(android.R.color.black));
            viewHolder.tvtitle_one_name.setText(list.get(i).getCumulativeReturn()+"%");
        }
        //近一月收益率
        if(list.get(i).getMonthProfitRate()>0){
            viewHolder.tvtitle_two_name.setTextColor(context.getResources().getColor(R.color.red));
            viewHolder.tvtitle_two_name.setText(list.get(i).getMonthProfitRate()+"%");
        }else if(list.get(i).getMonthProfitRate()<0){
            viewHolder.tvtitle_two_name.setTextColor(context.getResources().getColor(R.color.green_color));
            viewHolder.tvtitle_two_name.setText(list.get(i).getMonthProfitRate()+"%");
        }else{
            viewHolder.tvtitle_two_name.setTextColor(context.getResources().getColor(android.R.color.black));
            viewHolder.tvtitle_two_name.setText(list.get(i).getMonthProfitRate()+"%");
        }

//        viewHolder.tvName.setText(info.getOtherInfo());
//        viewHolder.tvtitle_one_name.setText(info.getJingZhiNum()+"");
//        viewHolder.tvtitle_two_name.setText(info.getMaxNum());
//        viewHolder.tvtitle_three_name.setText(info.getRateNum());

 //       viewHolder.gengtouTxt.setText(info.getMinGengTou());

 //       viewHolder.canvasViewThree.setRadius(DensityUtils.dp2px(context,40));
//        if (info.getType() == 2||info.getType() == 5){
//            setCanvasDataYellow(viewHolder.canvasViewThree, persent);
//        }else if (persent>100){
//            setCanvasDataRed(viewHolder.canvasViewThree, 100);
//        }else if (persent>0){
//            setCanvasDataRed(viewHolder.canvasViewThree, persent);
//        }else {
//            setCanvasDataGreen(viewHolder.canvasViewThree, persent);
//        }
        return view;
    }

    private class ViewHolder{
        TextView tvName;
        TextView tvUse;
        TextView tvtitle_one_name;
        TextView tvtitle_two_name;
        TextView tvtitle_three_name;
//        TextView rateNum;
//        TextView nowGen;
//        ImageView headImage,levelImage;
//        TextView zhaomuName;
//        TextView gengtouTxt;
//        CanvasViewThree canvasViewThree;
//        TextView status;
////        RelativeLayout layout;
//        RelativeLayout titleLayout;
//        TextView runTime;
    }

    private ArrayList<HashMap<String, Object>> data;
    private HashMap<String, Object> map;
    //设置历史业绩中的比例和颜色
    public void setCanvasDataRed(CanvasViewThree canvasView, double num) {
        data = new ArrayList<>();
        setDataToView(num + "%", "#E53739", (float) (num / 100));
        canvasView.setData(data);
    }

    public void setCanvasDataGreen(CanvasViewThree canvasView, double num) {
        data = new ArrayList<>();
        setDataToView(num + "%", "#49A854", (float) (num / 100));
        canvasView.setData(data);
    }

    public void setCanvasDataYellow(CanvasViewThree canvasView, double num) {
        data = new ArrayList<>();
        setDataToView(num + "%", "#FFE400", (float) (num / 100));
        canvasView.setData(data);
    }

    private void setDataToView(String title, String color, float weight) {
        map = new HashMap<>();
        map.put(CanvasView.TITLE, title);
        map.put(CanvasView.COLOR, Color.parseColor(color));
        map.put(CanvasView.WEIGHT, weight);
        data.add(map);
    }
}
