package com.example.drawer.stockapp.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.drawer.stockapp.R;
import com.example.drawer.stockapp.adapter.HeJiAdapter;
import com.example.drawer.stockapp.customview.view.XListView;
import com.example.drawer.stockapp.htttputil.HttpManager;
import com.example.drawer.stockapp.model.HeadIndex;
import com.example.drawer.stockapp.model.Information;
import com.example.drawer.stockapp.utils.ShapePreferenceManager;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * 学堂二级列表页面
 *
 */
public class XueTangclassifyListActivity extends AppCompatActivity implements View.OnClickListener{
    private XListView mList;
    private HeJiAdapter adapter;
    private Information findInfo;
    private  ArrayList<HeadIndex> listSave;
    private int page;
    private String mToken;
    private ImageView mNoDataImage;
    private String id;
    private ImageView ivBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xue_tangclassify_list);

        mToken = ShapePreferenceManager.getMySharedPreferences(this).getString(ShapePreferenceManager.TOKEN,null);
        id = getIntent().getStringExtra("Id"); //学堂列表的ID
        initView();
        if (findInfo == null){
            listSave = new ArrayList<>();
            SchoolFindAsyn asyn = new SchoolFindAsyn();
            asyn.execute(page+"");
        }else {
            adapter.setData(listSave);
            mList.setAdapter(adapter);
        }


    }



    private void initView(){
        mNoDataImage  = (ImageView)findViewById(R.id.loading_failed); //未获取到数据时显示该页面
        mList = (XListView) findViewById(R.id.xuetang_classify_list);   //学堂列表
        ivBack = (ImageView) findViewById(R.id.back_img);

        adapter = new HeJiAdapter(XueTangclassifyListActivity.this);

        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                HeadIndex headIndex = (HeadIndex) adapterView.getAdapter().getItem(i);

                Intent intent = new Intent(getApplicationContext(), WebViewActivity.class);


                intent.putExtra(WebViewActivity.URLID,headIndex.getXuetangId());
                intent.putExtra("TargetUrl",headIndex.getTargetUrl());
                intent.putExtra(WebViewActivity.INFORMATION_TYPE,8);

                startActivity(intent);
            }
        });

        mList.setPullLoadEnable(true);
        mList.setXListViewListener(new XListView.IXListViewListener() {
            @Override
            public void onRefresh() {
                page = 0;
                SchoolFindAsyn asyn = new SchoolFindAsyn();
                asyn.execute(page+"");

            }

            @Override
            public void onLoadMore() {
                page++;
                SchoolFindAsyn asyn = new SchoolFindAsyn();
                asyn.execute(page+"");
            }
        });

        ivBack.setOnClickListener(this);
    }




    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.loading_failed:
                mNoDataImage.setVisibility(View.GONE);
                SchoolFindAsyn schoolFindAsyn = new SchoolFindAsyn();
                schoolFindAsyn.execute(page+"");
                break;

            case R.id.back_img:
                XueTangclassifyListActivity.this.finish();
                break;

        }
    }


    /**
     * 策略组合停止加载和刷新
     */
    private void onLoad() {
        mList.stopRefresh();
        mList.stopLoadMore();
    }
    /**
     * 学堂
     */
    private class SchoolFindAsyn extends AsyncTask<String,Void,String> {
        @Override
        protected String doInBackground(String... strings) {
            HashMap<String,String> map = new HashMap<>();
            map.put("PageIndex", strings[0]);
            map.put("PageCount", "0");
            map.put("PageSize", "0");
            String message = HttpManager.newInstance().getHttpDataByFiveLayer(mToken,map,HttpManager.ExceCollSIGN_INFORMATION_URL,id);

            return message;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            onLoad();
            if (!TextUtils.isEmpty(s)&&s.length()>10){
                Gson gson = new Gson();
                findInfo = gson.fromJson(s,Information.class);
                parseData(findInfo);
            }else {
                mNoDataImage.setVisibility(View.VISIBLE);
            }
        }
    }

    private void parseData(Information findInfo){
        if (findInfo.getHead().getStatus() == 0){
            List<Information.ResultBean.DataBean> Courses = findInfo.getResult().getData();
//            List<XueTangInfo.ResultBean.CoursesBean> Courses = findInfo.getResult().getCourses();//以前的
            ArrayList<HeadIndex> list = new ArrayList<>();
            for (int i = 0;i<Courses.size();i++){
                HeadIndex headIndex = new HeadIndex();
                Information.ResultBean.DataBean coursesBean = Courses.get(i);
//                XueTangInfo.ResultBean.CoursesBean coursesBean = Courses.get(i);//以前的
                headIndex.setIndexImage(coursesBean.getImgs().get(0));
                headIndex.setXuetangId(coursesBean.getId());
                headIndex.setTargetUrl(coursesBean.getTargetUrl());
                list.add(headIndex);
            }
            if (page == 0){
                listSave = list;
                adapter.setData(list);
                mList.setAdapter(adapter);
            }else if (page >0&&list.size()>0){
//                listSave.addAll(list);
                adapter.addData(list);
            }else {
                Toast.makeText(this,"没有更多了哦",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
