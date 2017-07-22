package com.example.drawer.stockapp.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.drawer.stockapp.R;
import com.example.drawer.stockapp.adapter.IndexAdapter;
import com.example.drawer.stockapp.htttputil.HttpManager;
import com.example.drawer.stockapp.model.Information;
import com.example.drawer.stockapp.model.NewsInfo;
import com.example.drawer.stockapp.utils.DensityUtils;
import com.example.drawer.stockapp.utils.ManagerUtil;
import com.example.drawer.stockapp.utils.ShapePreferenceManager;
import com.google.gson.Gson;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CollectionActivity extends BascActivity implements AdapterView.OnItemClickListener, AbsListView.OnScrollListener {


    private Information headMassageInfo;
    private IndexAdapter indexAdapter;
    private ListView mlist;
    private View loadingFailed, zixunView;
    private int page=0;
    private String userId;
    private String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);
        tintManager.setStatusBarTintResource(R.color.write_color);
        initWight();


        userId = getIntent().getStringExtra("userId");

        token = ShapePreferenceManager.getMySharedPreferences(this).getString(ShapePreferenceManager.TOKEN,"");
        LayoutInflater mInflater = LayoutInflater.from(this);
        indexAdapter = new IndexAdapter(this);
        //zixunView = mInflater.inflate(R.layout.zixun_layout, null);
        //loadingFailed = zixunView.findViewById(R.id.loading_failed);
        //mlist = (XListView) zixunView.findViewById(R.id.listview_zixun);
        mlist=(ListView)findViewById(R.id.lv);
        //mlist.setPullLoadEnable(true);
        mlist.setOnItemClickListener(this);
        mlist.setOnScrollListener(this);



        GetNewsListAsyn asyn = new GetNewsListAsyn();
        asyn.execute(page + "");

//        mlist.setXListViewListener(new XListView.IXListViewListener() {
//            @Override
//            public void onRefresh() {
//                page = 0;
//                GetNewsListAsyn getNewsListAsyn = new GetNewsListAsyn();
//                getNewsListAsyn.execute(page + "");
//
//
//            }

//            @Override
//            public void onLoadMore() {
//                page++;
//                GetNewsListAsyn getNewsListAsyn = new GetNewsListAsyn();
//                getNewsListAsyn.execute(page + "");
//
//            }
//        });
    }


    public void initWight(){
        RelativeLayout mTitleRelat = (RelativeLayout) findViewById(R.id.collect_title);    //title布局
        //设置距离顶部状态栏高度
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                DensityUtils.dp2px(this,50));
        params.setMargins(0, ManagerUtil.getStatusBarHeight(this),0,0);
        mTitleRelat.setLayoutParams(params);

        ImageView mBackimg = (ImageView) findViewById(R.id.back_img);

        mBackimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

//        ListView mListview = (ListView) findViewById(R.id.collect_list);
//        CollectionAdapter adapter = new CollectionAdapter(this);
//        adapter.setData(setNewsInfo());
//        mListview.setAdapter(adapter);

    }
    @Override
    protected void onResume() {
        super.onResume();
        SystemBarTintManager tintManager = ManagerUtil.newInstance(this);
        ManagerUtil.setStataBarColor(this,tintManager);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        switch (adapterView.getId()) {
            case R.id.listview_zixun:
                NewsInfo info = (NewsInfo) adapterView.getAdapter().getItem(i);
                Intent intent = new Intent(this, WebViewActivity.class);

                intent.putExtra(WebViewActivity.URLID, info.getLinkUrl());
                intent.putExtra(WebViewActivity.TargetUrl, info.getTargetUrl());
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onScrollStateChanged(AbsListView absListView, int i) {

    }

    @Override
    public void onScroll(AbsListView absListView, int i, int i1, int i2) {

    }


    /**
     * 获取收藏列表
     */
    private class GetNewsListAsyn extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            HashMap<String, String> map = new HashMap<>();
            map.put("PageIndex", strings[0]);
            map.put("PageCount", "0");
            map.put("PageSize", "0");
            String message = HttpManager.newInstance().getHttpDataByFiveLayer(token, map, HttpManager.FAVORITES_List_URL,userId);
            return message;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d("GetNewsListAsyn", s);
            //onLoadZx();

            if (!TextUtils.isEmpty(s) && !s.equals(HttpManager.FAILED)) {
                Gson gson = new Gson();
                headMassageInfo = gson.fromJson(s, Information.class);
                //Log.d("GetNewsListAsyn", "headMassageInfo:" + headMassageInfo);

                if (headMassageInfo.getHead().getStatus() == 0) {
                    //Log.i("gson解析后",headMassageInfo.toString());
                    getDataZixun();   //解析数据稍后放开
                } else {
                    Toast.makeText(CollectionActivity.this, "还没找到相关信息哦", Toast.LENGTH_SHORT).show();
                }
            } else {
                loadingFailed.setVisibility(View.VISIBLE);
            }
        }
    }



//    private void onLoadZx() {
//        mlist.stopRefresh();
//        mlist.stopLoadMore();
//    }



    /**
     * 设置首页数据
     */
    public void getDataZixun() {
        ArrayList<NewsInfo> listInfo = setNewsInfo();
        if (page == 0) {
//            listInfoSave.clear();
            indexAdapter.setData(listInfo);
            mlist.setAdapter(indexAdapter);
        } else if (page > 0 && listInfo.size() > 0) {
            indexAdapter.addData(listInfo);
        } else {
            Toast.makeText(CollectionActivity.this, "已经到底了哦", Toast.LENGTH_SHORT).show();
        }
//        listInfoSave.addAll(listInfo);

    }


    public ArrayList<NewsInfo> setNewsInfo() {
        ArrayList<NewsInfo> newsInfos = new ArrayList<>();
        List<Information.ResultBean.DataBean> newsBeen = headMassageInfo.getResult().getData();
        for (int i = 0; i < newsBeen.size(); i++) {
            NewsInfo info = new NewsInfo();
            Information.ResultBean.DataBean newsBean = newsBeen.get(i);
            info.setTitle(newsBean.getTitle());
            info.setContent(newsBean.getSummary());
            info.setTime(newsBean.getUpdateTime());
            info.setPeopleNum(newsBean.getComments() + "");
            info.setLinkUrl(newsBean.getId() + "");
            info.setTargetUrl(newsBean.getTargetUrl());
            if (newsBean.getImgs().size() > 1) {
                info.setType(2);
                ArrayList<String> list = new ArrayList<>();
                for (int j = 0; j < newsBean.getImgs().size(); j++) {
                    list.add(newsBean.getImgs().get(j) + "");
                }
                info.setImgaes(list);
            } else {
                info.setImage(newsBean.getImgs().get(0) + "");
                info.setType(1);
            }
            newsInfos.add(info);
        }
        return newsInfos;
    }

//    public ArrayList<NewsInfo> setData(){
//        ArrayList<NewsInfo> list = new ArrayList<>();
//        for (int i = 0;i<3;i++){
//            NewsInfo info = new NewsInfo();
//            if (i == 0){
//                info.setType(1);
//                info.setImage("http://m2.quanjing.com/2m/ivary_photorf001/stp005_00051.jpg");
//                info.setTitle("战略转移");
//                info.setContent("文本内容测试显示");
//                info.setPeopleNum("20");
//                info.setTime("2016.08.11");
//            }else if (i== 1){
//                info.setType(2);
//                info.setThreeTitle("3张图片的title");
//                info.setThreeContent("啊哈哈哈哈哈哈哈哈哈");
//                info.setThreeTime("2016.08.11");
//                info.setThreePeopleNum(30);
//            }else if (i == 2){
//                info.setType(3);
//                info.setDynImage("http://m2.quanjing.com/2m/ivary_photorf001/stp005_00051.jpg");
//                info.setDynName("牛人1号");
//                info.setDynTime("2016.08.11");
//                info.setDynContent("yeyeyeyeyeyyeyeyeyeyeyye");
//                ArrayList<String> listimg = new ArrayList<>();
//                listimg.add("http://m2.quanjing.com/2m/ivary_photorf001/stp005_00051.jpg");
//                listimg.add("http://m2.quanjing.com/2m/ivary_photorf001/stp005_00051.jpg");
//                listimg.add("http://m2.quanjing.com/2m/ivary_photorf001/stp005_00051.jpg");
//                info.setDynContentImage(listimg);
//            }
//            list.add(info);
//        }
//        return list;
//    }
}
