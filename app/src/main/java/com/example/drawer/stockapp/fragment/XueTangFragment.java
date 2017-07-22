package com.example.drawer.stockapp.fragment;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.drawer.stockapp.R;
import com.example.drawer.stockapp.activity.MessageActivity;
import com.example.drawer.stockapp.activity.SerchActivity;
import com.example.drawer.stockapp.activity.XueTangclassifyListActivity;
import com.example.drawer.stockapp.adapter.HeJiAdapter;
import com.example.drawer.stockapp.customview.view.XListView;
import com.example.drawer.stockapp.htttputil.HttpManager;
import com.example.drawer.stockapp.htttputil.RequestTypeConstant;
import com.example.drawer.stockapp.model.HeadIndex;
import com.example.drawer.stockapp.model.Information;
import com.example.drawer.stockapp.model.UserSignInfo;
import com.example.drawer.stockapp.utils.DensityUtils;
import com.example.drawer.stockapp.utils.ManagerUtil;
import com.example.drawer.stockapp.utils.ShapePreferenceManager;
import com.google.gson.Gson;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class XueTangFragment extends Fragment implements View.OnClickListener{

    private View mView;
    private XListView mList;
    private HeJiAdapter adapter;
    private Information findInfo;
    private  ArrayList<HeadIndex> listSave;
    private int page;
    private ImageView mNoDataImage;
    private Button btnSign;
    private String mToken;
    private UserSignInfo userSignInfo; //用户签到实体类
    private TextView tvCheckTotal;//总的签到天数
    private TextView tvUserTotalCreit;//用户的总积分数
    private TextView tvSubjectComplte;//完成课程数
    private TextView tvCheckingMonth;//本月签到天数
    private SchoolSignUserInfoAsyn schoolSignUserInfoAsyn;
    private View viewSignLayout;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (mView == null){
            mView = inflater.inflate(R.layout.fragent_xuetang_layout, null);
            mToken = ShapePreferenceManager.getMySharedPreferences(getActivity()).getString(ShapePreferenceManager.TOKEN,null);
            initWight();
            if (findInfo == null){
                listSave = new ArrayList<>();
                SchoolFindAsyn asyn = new SchoolFindAsyn();
                asyn.execute(page+"");
                schoolSignUserInfoAsyn = new SchoolSignUserInfoAsyn();
                schoolSignUserInfoAsyn.execute();
            }else {
                adapter.setData(listSave);
                mList.setAdapter(adapter);
            }
        }

        return mView;
    }

    @Override
    public void onResume() {
        super.onResume();

        SystemBarTintManager tintManager = ManagerUtil.newInstance(getActivity());
        ManagerUtil.setStataBarColor(getActivity(),tintManager);

    }


    /**
     * 初始化控件
     */
    public void initWight(){
        RelativeLayout  mTitle = (RelativeLayout) mView.findViewById(R.id.xuetang_title);
        //设置距离顶部状态栏高度
        final RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                DensityUtils.dp2px(getActivity(),50));
        params.setMargins(0, ManagerUtil.getStatusBarHeight(getActivity()),0,0);
        mTitle.setLayoutParams(params);


        ImageView mMessage = (ImageView) mView.findViewById(R.id.my_class_img);
        ImageView mSearch = (ImageView) mView.findViewById(R.id.search_img);

        mMessage.setOnClickListener(this);
        mSearch.setOnClickListener(this);


        mList = (XListView) mView.findViewById(R.id.xuetang_list);   //学堂列表
        mNoDataImage = (ImageView) mView.findViewById(R.id.loading_failed);

        viewSignLayout = LayoutInflater.from(getActivity()).inflate(R.layout.xuetang_sign_relativelayout, null,true);
        tvCheckingMonth = (TextView) viewSignLayout.findViewById(R.id.tv_xuetang_sign_days); //用户本月签到天数
        tvSubjectComplte = (TextView) viewSignLayout.findViewById(R.id.tv_xuetang_subject);//完成课程数
        tvCheckTotal = (TextView) viewSignLayout.findViewById(R.id.tv_xuetang_sign_days_total);//累计签到天数
        tvUserTotalCreit = (TextView) viewSignLayout.findViewById(R.id.tv_xuetang_integral);//用户总积分
        btnSign = (Button) viewSignLayout.findViewById(R.id.btn_xuetang_sign);//签到按钮
        mList.addHeaderView(viewSignLayout);
//        mList.addHeaderView(viewSignLayout);
        mNoDataImage.setOnClickListener(this);
        btnSign.setOnClickListener(this);

        adapter = new HeJiAdapter(getActivity());

        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                HeadIndex headIndex = (HeadIndex) adapterView.getAdapter().getItem(i);

                Intent intent = new Intent(getContext(), XueTangclassifyListActivity.class);

                intent.putExtra("Id",headIndex.getXuetangId());

                startActivity(intent);

                //跳转至二级页面
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
    }

    /**
     * 策略组合停止加载和刷新
     */
    private void onLoad() {
        mList.stopRefresh();
        mList.stopLoadMore();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.my_class_img:
                Intent intent = new Intent(getActivity(), MessageActivity.class);
                startActivity(intent);
                break;
            case R.id.search_img:
                intent = new Intent(getActivity(), SerchActivity.class);
                startActivity(intent);
                break;
            case R.id.loading_failed:
                mNoDataImage.setVisibility(View.GONE);
                SchoolFindAsyn schoolFindAsyn = new SchoolFindAsyn();
                schoolFindAsyn.execute(page+"");
                break;
            case R.id.btn_xuetang_sign:
                SchoolSignAsyn schoolSignAsyn = new SchoolSignAsyn(); //异步任务发送签到请求
                schoolSignAsyn.execute();
                break;
        }
    }

    /**
     *获取用户签到信息
     *
     */

    private class SchoolSignUserInfoAsyn extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {
            HashMap<String,String> map = new HashMap<>();

            String message = HttpManager.newInstance().getHttpIndexData(mToken,map,HttpManager.ExceCollSIGN_USER_INFO_URL);

            return message;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (!TextUtils.isEmpty(s)&&!s.equals(HttpManager.FAILED)){
               // Gson gson = new Gson();



                UserSignInfo userSignInfo = new UserSignInfo();
                UserSignInfo.HeadBean headBean = new UserSignInfo.HeadBean();
                try {
                    JSONObject object = new JSONObject(s);


                    JSONObject objecthead = (JSONObject) object.get("Head");


                    headBean.setMsg(objecthead.getString("Msg"));

                    headBean.setStatus(objecthead.getInt("Status"));
                    if (headBean.getStatus() == 1) return;

                    JSONObject jsonObject = object.getJSONObject("Result");


                    UserSignInfo.ResultBean resultBean = new UserSignInfo.ResultBean();

                    resultBean.setChcekingVolumn(jsonObject.getDouble("ChcekingVolumn"));
                    resultBean.setMonthVolumn(jsonObject.getDouble("MonthVolumn"));
                    resultBean.setCredit(jsonObject.getDouble("Credit"));
                    resultBean.setChecking(jsonObject.getBoolean("IsChecking"));
                    resultBean.setCloseInfoVolumn(jsonObject.getDouble("CloseInfoVolumn"));
                    resultBean.setTotalCredit(jsonObject.getDouble("TotalCredit"));
                    Log.d("XueTangFragment11", resultBean.toString());
                    userSignInfo.setResultBean(resultBean);


                    userSignInfo.setHead(headBean);
                    parseUserSignInfoData(userSignInfo);

                } catch (JSONException e) {
                    e.printStackTrace();
                }





                // userSignInfo = gson.fromJson(s,UserSignInfo.class);
            }
        }
    }

    private void parseUserSignInfoData(UserSignInfo userSignInfo) {


        if (userSignInfo.getHead().getStatus()==0){

            UserSignInfo.ResultBean result = userSignInfo.getResultBean();

            Log.d("XueTangFragment22", result.toString());
            tvCheckingMonth.setText((int)result.getMonthVolumn()+"");
            tvSubjectComplte.setText((int)result.getCloseInfoVolumn()+0+"");
            tvCheckTotal.setText((int)result.getChcekingVolumn()+"");
            tvUserTotalCreit.setText((int)result.getTotalCredit()+"");
        }
    }


    /**
     *
     * 用户签到请求
     *
     */
    private class SchoolSignAsyn extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {
            HashMap<String,String> map = new HashMap<>();

            String message = HttpManager.newInstance().getHttpIndexData(mToken,map,HttpManager.ExceCollSIGN_URL);

            return message;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                JSONObject object = new JSONObject(s);
                if (object.has("Head")){
                    JSONObject head = object.getJSONObject("Head");
                    if (head.getString("Status").equals("1")){
                        Toast.makeText(getActivity(),head.getString("Msg"),Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getActivity(),"签到成功",Toast.LENGTH_SHORT).show();
//                        schoolSignUserInfoAsyn.execute();
//                        SchoolFindAsyn asyn = new SchoolFindAsyn();
//                        asyn.execute("0");
                        schoolSignUserInfoAsyn = new SchoolSignUserInfoAsyn();
                        schoolSignUserInfoAsyn.execute();
                        adapter.notifyDataSetChanged();
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
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
            String message = HttpManager.newInstance().getHttpDataByFourLayer("",map,HttpManager.CourseInfoList_URL, RequestTypeConstant.REQUEST_TYPE_INFORMAITON_COLECTION);

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
                Toast.makeText(getActivity(),"没有更多了哦",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
