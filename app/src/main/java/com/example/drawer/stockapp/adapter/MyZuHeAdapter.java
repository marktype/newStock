package com.example.drawer.stockapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.drawer.stockapp.R;
import com.example.drawer.stockapp.activity.LianghuaWebView;
import com.example.drawer.stockapp.activity.chaKanZuHe;
import com.example.drawer.stockapp.customview.CanvasView;
import com.example.drawer.stockapp.customview.CanvasViewThree;
import com.example.drawer.stockapp.htttputil.HttpManager;
import com.example.drawer.stockapp.listener.DeleteCallBack;
import com.example.drawer.stockapp.model.MyzuheThree;
import com.example.drawer.stockapp.model.ZhanKaiDetail;
import com.example.drawer.stockapp.model.chiCangLieBiaoEntity;
import com.example.drawer.stockapp.model.chiCangLieBiaoEntitySecond;
import com.example.drawer.stockapp.utils.ShapePreferenceManager;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.example.drawer.stockapp.R.id.rl;
import static com.example.drawer.stockapp.R.id.rlHiden;
import static com.example.drawer.stockapp.htttputil.HttpManager.chiCang;

/**
 * Created by 欢大哥 on 2016/6/16.
 */
public class MyZuHeAdapter extends BaseAdapter {
    private Context context;
    DecimalFormat df =new DecimalFormat("#0.00");   //保留两位小数
    private List<MyzuheThree.ResultBean.DataBean> list;
    private DeleteCallBack deleteCallBack;
    public ZhanKaiDetail zhanKai;
    private ArrayList<chiCangLieBiaoEntitySecond.ResultBean> stockList;

    public MyZuHeAdapter(Context context){
        this.context = context;
    }
    public void setData(List<MyzuheThree.ResultBean.DataBean> list){
        this.list = list;
        notifyDataSetChanged();
    }
    public void addData(List<MyzuheThree.ResultBean.DataBean> blist){
        this.list.addAll(blist);
        notifyDataSetChanged();
    }

    public void setOnClickDeleteListener(DeleteCallBack deleteCallBack){
        this.deleteCallBack = deleteCallBack;
    }
    @Override
    public int getCount() {
        return list==null?0:list.size();
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
            view = LayoutInflater.from(context).inflate(R.layout.my_zuhe_item_layout,null);
            viewHolder.name=(TextView)view.findViewById(R.id.name);
            viewHolder.tv_shouyi_content=(TextView)view.findViewById(R.id.tv_shouyi_content);//收益
            viewHolder.chuangjian=(TextView)view.findViewById(R.id.chuangjian);//创建
            viewHolder.tv_shouyilv=(TextView)view.findViewById(R.id.tv_shouyilv);//收益率
            viewHolder.tv_zhankai=(TextView)view.findViewById(R.id.tv_zhankai);//可点击的textview
            viewHolder.rlHiden=(RelativeLayout)view.findViewById(rlHiden);
            viewHolder.rl=(RelativeLayout)view.findViewById(rl);
            viewHolder.tv_time=(TextView)view.findViewById(R.id.tv_time);//买入时间
            viewHolder.mairuType=(TextView)view.findViewById(R.id.mairuType);//买入类型
            viewHolder.pufa=(TextView)view.findViewById(R.id.pufa);//股票名称
            viewHolder.daima=(TextView)view.findViewById(R.id.daima);//股票代码
            viewHolder.chengjiao=(TextView)view.findViewById(R.id.chengjiao);//成交量
            viewHolder.jiaoyil=(TextView)view.findViewById(R.id.jiaoyil);//交易量

            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        //Log.d("adapter里面的数据",list.toString());
        final MyzuheThree.ResultBean.DataBean info = (MyzuheThree.ResultBean.DataBean) getItem(i);
        viewHolder.name.setText(list.get(i).getName());//名字
        viewHolder.tv_shouyi_content.setText(df.format(list.get(i).getPorfolioOther().getCumulativeProfit())+"");//收益
        viewHolder.tv_shouyilv.setText(df.format(list.get(i).getPorfolioOther().getCumulativeReturn())+"%");//收益率
        viewHolder.tv_zhankai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id=list.get(i).getId();
                getMyListDat(id,viewHolder);
                if(viewHolder.rlHiden.getVisibility()==View.VISIBLE){
                    viewHolder.rlHiden.setVisibility(View.GONE);
                    viewHolder.tv_zhankai.setText("展开查看最新调仓");

                }else{
                    viewHolder.rlHiden.setVisibility(View.VISIBLE);
                    viewHolder.tv_zhankai.setText("关闭查看最新调仓");

                }
            }
        });
        viewHolder.rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
////                if(list.get(i).getType()==2){//创建
//                    Activity activity = (Activity) context;
//                    Intent intent=new Intent(activity, LianghuaWebView.class);
//                    intent.putExtra("adapterType",list.get(i).getType());
//                    intent.putExtra("id",Integer.parseInt(list.get(i).getId()));
//                    Log.i("zuheId1",list.get(i).getId());
//
//                    activity.startActivity(intent);

                Activity activity = (Activity) context;
                Log.i("adapter里面类型",list.get(i).toString());
                if(list.get(i).getType()==4){//跟投
                    Intent intent=new Intent(context,chaKanZuHe.class);
                    intent.putExtra("wodezuhetype",list.get(i).getType());
                    intent.putExtra("wodezhuhename",list.get(i).getName());
                    intent.putExtra("wodezhuheId",list.get(i).getId());
                    intent.putExtra("MyZuHeIsSmsNotic",list.get(i).isIsSmsNotic());
                    activity.startActivity(intent);
                }else {//2 创建 订阅
                    SharedPreferences sp = ShapePreferenceManager.getMySharedPreferences(context);
                    String mToken = sp.getString(ShapePreferenceManager.TOKEN, null);
                    chiCangLieBiao liebiao = new chiCangLieBiao();
                    liebiao.execute(mToken,list.get(i).getId());
                    stockList = new ArrayList<>();

                    Log.i("创建和订阅",list.get(i).toString());
                    Intent intent=new Intent(context,LianghuaWebView.class);
                    intent.putExtra("wodezuhetype",list.get(i).getType());
                    intent.putExtra("wodezhuhename",list.get(i).getName());
                    intent.putExtra("wodezhuheId",list.get(i).getId());
                    intent.putExtra("wodezhuheDes",list.get(i).getDesc());
                    intent.putExtra("wodezuheList",stockList);
                    intent.putExtra("adapterisIsMyPortfolio",list.get(i).isIsMyPortfolio());
                    activity.startActivity(intent);
                }


//                }else if(list.get(i).getType()==4){//跟投
//                    Activity activity = (Activity) context;
//                    Intent intent=new Intent(activity,chaKanZuHe.class);
//                    intent.putExtra("adapterType",list.get(i).getType());
//                    intent.putExtra("zuheId",Integer.parseInt(list.get(i).getId()));
//                    intent.putExtra("zuheName", list.get(i).getName());
//                    Log.i("zuheId2",list.get(i).getId());
//                    activity.startActivity(intent);
//                }else if(list.get(i).getType()==3){
//                    if(list.get(i).isIsMyPortfolio()==false){//0 订阅
//
//                    }else{
//                        Activity activity = (Activity) context;
//                        Intent intent=new Intent(activity, LianghuaWebView.class);
//                        intent.putExtra("adapterType",list.get(i).getType());
//                        intent.putExtra("id",Integer.parseInt(list.get(i).getId()));
//                        Log.i("zuheId3",list.get(i).getId());
//                        activity.startActivity(intent);
//                    }
//
//                }

            }
        });

        if(list.get(i).getType()==2){//创建
            viewHolder.chuangjian.setText("创建");
        }else if(list.get(i).getType()==4){//跟投
            viewHolder.chuangjian.setText("跟投");
        }else if(list.get(i).getType()==3){
            if(list.get(i).isIsMyPortfolio()==false){//0 订阅
                viewHolder.chuangjian.setText("订阅");
            }else{
                viewHolder.chuangjian.setText("创建");
            }

        }

//        viewHolder.contentBg.setBackgroundColor(context.getResources().getColor(R.color.write_color));
//        viewHolder.name.setText(list.get(i).getName());
//        if (info.getShouyiRate() == 0){
//            viewHolder.rate.setText("0.00%");
//            viewHolder.rate.setTextColor(context.getResources().getColor(android.R.color.black));
//            setCanvasDataGreen(viewHolder.canvasViewThree,info.getShouyiRate());
//        }else if (info.getShouyiRate() > 0){
//            if (info.getShouyiRate()<100){
//                setCanvasDataRed(viewHolder.canvasViewThree,info.getShouyiRate());
//            }else {
//                setCanvasDataRed(viewHolder.canvasViewThree,100);
//            }
//            viewHolder.rate.setTextColor(context.getResources().getColor(R.color.red));
//            viewHolder.rate.setText("+"+info.getShouyiRate()+"%");
//        }else if (info.getShouyiRate()<0){
//            setCanvasDataGreen(viewHolder.canvasViewThree,info.getShouyiRate());
//            viewHolder.rate.setTextColor(context.getResources().getColor(R.color.green_color));
//            viewHolder.rate.setText(info.getShouyiRate()+"%");
//        }
//        viewHolder.name.setText(info.getNiurenName());
//        if (info.getZuheType() == 1){
//            viewHolder.guanzhuNum.setText("");
//            viewHolder.image.setImageResource(R.mipmap.gengtou);
//            viewHolder.status.setVisibility(View.VISIBLE);
//            if (info.getType() == 0){   //招募中
//                viewHolder.status.setText("招募中");
//            }else if (info.getType() == 1){   //运行中
//                viewHolder.status.setText("运行中");
//            }else {   //已结束
//                viewHolder.status.setText("已结束");
//            }
//        }else if (info.getZuheType() == 2){
//            viewHolder.guanzhuNum.setText("");
//            viewHolder.image.setImageResource(R.mipmap.chuangjian);
//            viewHolder.status.setVisibility(View.GONE);
//        }else {
//            viewHolder.guanzhuNum.setText("订阅人数:"+info.getTradeTime());
////            viewHolder.guanzhuNum.setText(info.getStockType());
//            viewHolder.image.setImageResource(R.mipmap.dingyue);
//            viewHolder.status.setVisibility(View.GONE);
//        }
       // viewHolder.canvasViewThree.setRadius(DensityUtils.dp2px(context,40));

//        viewHolder.delete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                final CustomDialog dialog = new CustomDialog(context);
//                dialog.setMessageText("确认要删除组合吗？");
//                dialog.show();
//                dialog.setOnPositiveListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        deleteCallBack.onDeleteId(info.getId());
//                        dialog.dismiss();
//                    }
//                });
//                dialog.setOnNegativeListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        dialog.dismiss();
//                    }
//                });
//
//
//            }
//        });
        return view;
    }

    private class ViewHolder{
        TextView name;
        TextView tv_shouyi_content;
        TextView chuangjian;
        TextView tv_shouyilv;
        TextView tv_zhankai;
        RelativeLayout rlHiden;
        RelativeLayout rl;
        TextView tv_time;//时间
        TextView mairuType;//类型
        TextView pufa;//股票名称
        TextView daima;//股票代码
        TextView chengjiao;//成交量
        TextView jiaoyil;//交易量

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

    private void setDataToView(String title, String color, float weight) {
        map = new HashMap<>();
        map.put(CanvasView.TITLE, title);
        map.put(CanvasView.COLOR, Color.parseColor(color));
        map.put(CanvasView.WEIGHT, weight);
        data.add(map);
    }

    public void getMyListDat(final String id, final ViewHolder viewHolder) {
        new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                HashMap<String, String> map = new HashMap<>();
                map.put("PageIndex", "0");
                map.put("PageCount", "0");
                map.put("PageSize", "10");
                String messageOne = HttpManager.newInstance().getHttpDataByAdapter("", HttpManager.LastTrades_URL,id);
                return messageOne;
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
                String message = (String) o;
                Log.d("我的组合onPostExecute",message);
                try {
                    JSONObject messageObject = new JSONObject(message);
                    JSONArray resultObject = messageObject.getJSONArray("Result");
                    JSONObject DataObject = resultObject.getJSONObject(0);
                    zhanKai = new ZhanKaiDetail();
                    zhanKai.setName(DataObject.getString("Name"));
                    zhanKai.setBuyType(DataObject.getInt("BuyType"));
                    zhanKai.setCode(DataObject.getString("Code"));
                    zhanKai.setVolumn(DataObject.getDouble("Volume"));
                    zhanKai.setTradePrice(DataObject.getInt("TradePrice"));
                    zhanKai.setTradeTime(DataObject.getString("TradeTime"));
                    //给展开控件赋值
                    viewHolder.tv_time.setText(zhanKai.getTradeTime());
                    viewHolder.pufa.setText(zhanKai.getName());
                    viewHolder.jiaoyil.setText(zhanKai.getVolumn()+"");//交易量
                    viewHolder.chengjiao.setText(zhanKai.getTradePrice()+"");//成交量
                    viewHolder.daima.setText(zhanKai.getCode());//代码
                    if(zhanKai.getBuyType()==0){
                        viewHolder.mairuType.setText("买入");
                    }else{
                        viewHolder.mairuType.setText("卖出");
                    }
                }catch (Exception e){
                    Log.i("异常","在getMyListDat");
                }
            }
        }.execute();
    }
    public class chiCangLieBiao extends AsyncTask<String,Void ,String >{
        @Override
        protected String doInBackground(String... params) {
            String token = params[0];
            String paramId = params[1];
            String chicang = chiCang(token,paramId);
            return chicang;
        }

        @Override
        protected void onPostExecute(String s) {
            Gson gson = new Gson();
            chiCangLieBiaoEntity chiCangEntity = gson.fromJson(s.toString(), chiCangLieBiaoEntity.class);
            Log.i("获取持仓列表", "onPostExecute: "+chiCangEntity.toString());
            int i=chiCangEntity.getHead().getStatus();
            if(i==0){
                chiCangLieBiaoEntitySecond chiCangEntitySecond = gson.fromJson(s.toString(), chiCangLieBiaoEntitySecond.class);
                List<chiCangLieBiaoEntitySecond.ResultBean> result = chiCangEntitySecond.getResult();
                for(int a=0;a<result.size();a++){
                    stockList.add(result.get(i));
                }
            }
        }
    }

}