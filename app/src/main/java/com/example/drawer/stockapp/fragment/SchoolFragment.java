package com.example.drawer.stockapp.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.drawer.stockapp.R;
import com.example.drawer.stockapp.activity.MessageActivity;
import com.example.drawer.stockapp.activity.SerchActivity;
import com.example.drawer.stockapp.activity.WebViewUpTitleActivity;
import com.example.drawer.stockapp.adapter.MyClassImageAdapter;
import com.example.drawer.stockapp.adapter.MyViewPagerAdapter;
import com.example.drawer.stockapp.adapter.SelectClassAdapter;
import com.example.drawer.stockapp.customview.MyGridView;
import com.example.drawer.stockapp.customview.MyListView;
import com.example.drawer.stockapp.customview.PagerSlidingTabStrip;
import com.example.drawer.stockapp.htttputil.HttpManager;
import com.example.drawer.stockapp.model.ClassInfo;
import com.example.drawer.stockapp.model.FindInfo;
import com.example.drawer.stockapp.model.HeadIndex;
import com.example.drawer.stockapp.utils.DensityUtils;
import com.example.drawer.stockapp.utils.ManagerUtil;
import com.example.drawer.stockapp.utils.ShapePreferenceManager;
import com.google.gson.Gson;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link SchoolFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SchoolFragment extends Fragment  implements AdapterView.OnItemClickListener,View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private View mView,myClassView,findView;
    private ViewPager mPager;
    private RadioButton mFind,mClass;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private PagerSlidingTabStrip tabs;
    private String token;
    private SharedPreferences sp;

    public SchoolFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SchoolFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SchoolFragment newInstance(String param1, String param2) {
        SchoolFragment fragment = new SchoolFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        sp = ShapePreferenceManager.getMySharedPreferences(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        token = sp.getString(ShapePreferenceManager.TOKEN,null);
        mView = inflater.inflate(R.layout.fragment_school, container, false);
        initWight();
        initData();
        SchoolFindAsyn findAsyn = new SchoolFindAsyn();
        findAsyn.execute();
        return mView;
    }

    @Override
    public void onResume() {
        super.onResume();
        SystemBarTintManager tintManager = ManagerUtil.newInstance(getActivity());
        ManagerUtil.setStataBarColor(getActivity(),tintManager);
        if (!TextUtils.isEmpty(token)){
            noLogin.setVisibility(View.GONE);
            MySchoolAsyn mySchoolAsyn = new MySchoolAsyn();
            mySchoolAsyn.execute(sp.getString(ShapePreferenceManager.USER_UID,""),token);
        }else {
            noLogin.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 初始化控件
     */
    public void initWight(){
        RelativeLayout mTitle = (RelativeLayout) mView.findViewById(R.id.all_title);
        //设置距离顶部状态栏高度
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                DensityUtils.dp2px(getActivity(),50));
        params.setMargins(0, ManagerUtil.getStatusBarHeight(getActivity()),0,0);
        mTitle.setLayoutParams(params);


//        RadioGroup mGroup = (RadioGroup) mView.findViewById(R.id.class_group);
        ImageView mMessage = (ImageView) mView.findViewById(R.id.my_class_img);
        ImageView mSearch = (ImageView) mView.findViewById(R.id.search_img);

//        mFind = (RadioButton) mView.findViewById(R.id.find_txt);
//        mClass = (RadioButton) mView.findViewById(R.id.class_txt);
        tabs = (PagerSlidingTabStrip) mView.findViewById(R.id.class_group);

        mPager = (ViewPager) mView.findViewById(R.id.class_content_pager);   //viewpager

        mPager.setOnPageChangeListener(new TabOnPageChangeListener());
//        mGroup.setOnCheckedChangeListener(new RadioGroupListener() );
        mMessage.setOnClickListener(this);
        mSearch.setOnClickListener(this);

    }
    /**
     * 初始化适配器数据
     */
    public void initData(){
        List<View> viewList = new ArrayList<View>();
        LayoutInflater mInflater=LayoutInflater.from(getActivity());
        findView = mInflater.inflate(R.layout.find_layout,null);     //发现布局
        myClassView = mInflater.inflate(R.layout.my_class_layout,null);   //我的课程布局
        viewList.add(findView);
        viewList.add(myClassView);
        ArrayList<String> titles = new ArrayList<>();
        titles.add("发现");
        titles.add("我的学堂");
        MyViewPagerAdapter adapter = new MyViewPagerAdapter(viewList,titles);
        mPager.setAdapter(adapter);
        tabs.setViewPager(mPager);
        tabs.setSelectedTextColor(getActivity().getResources().getColor(android.R.color.background_dark));

        setMyClassData();

        initFindWight();

        initMyClassWight();
    }

    private GridView gridView;
    private MyListView myListView,myClassList;
    private MyGridView myGridView;
    private MyClassImageAdapter listViewadapter,adapter;
    private SelectClassAdapter selectClassAdapter,ClassAdapter;
    private ImageView noLogin;
    /**
     * 初始化发现界面控件
     */
    public void initFindWight(){
//        ImageView mImgaeOne = (ImageView) findView.findViewById(R.id.heji_image_one);
//        mImgaeOne.setOnClickListener(this);
//        ImageView mImageTwo = (ImageView) findView.findViewById(R.id.heji_image_two);
//        mImageTwo.setOnClickListener(this);

        myListView = (MyListView) findView.findViewById(R.id.my_find_list);
        listViewadapter = new MyClassImageAdapter(getActivity());

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getActivity(),"list点击",Toast.LENGTH_SHORT).show();
            }
        });

        //精选课程
        gridView = (GridView) findView.findViewById(R.id.gridview_layout);
        gridView.setOnItemClickListener(this);
        selectClassAdapter = new SelectClassAdapter(getActivity());




        myGridView = (MyGridView) findView.findViewById(R.id.my_gridview);
        myGridView.setOnItemClickListener(this);
        ClassAdapter = new SelectClassAdapter(getActivity());


    }

    /**
     * 初始化我的课堂界面控件
     */
    public void initMyClassWight(){
//        ImageView mMyclass = (ImageView) myClassView.findViewById(R.id.my_class_img_txt);
//        ImageView mMyOpenClass = (ImageView) myClassView.findViewById(R.id.my_class_start_txt);
        myClassList = (MyListView) myClassView.findViewById(R.id.class_listview);     //课堂列表
        myClassList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getActivity(),"list点击",Toast.LENGTH_SHORT).show();
            }
        });

        noLogin = (ImageView) myClassView.findViewById(R.id.img_no_login);    //未登陆显示


        adapter = new MyClassImageAdapter(getActivity());



//        mMyclass.setOnClickListener(this);
//        mMyOpenClass.setOnClickListener(this);
    }

//    public ArrayList<String> setData(){
//        ArrayList<String> list = new ArrayList<>();
//        for (int i = 0;i<1;i++){
//            list.add("http://m2.quanjing.com/2m/ivary_photorf001/stp005_00051.jpg");
//        }
//        return list;
//    }


//    /**
//     * 推荐数据
//     */
//    public ArrayList<HeadIndex> recommendData(){
//        ArrayList<HeadIndex> indices = new ArrayList<>();
//        for (int i = 0;i<10;i++){
//            HeadIndex headIndex = new HeadIndex();
//            headIndex.setIndexImage("http://img.lanrentuku.com/img/allimg/1605/5-1605291106390-L.jpg");
//            headIndex.setIndexName("测试"+i);
//            headIndex.setIndexPersent("martix推荐");
//            indices.add(headIndex);
//        }
//        return indices;
//    }

    /**
     * 初始化推荐人信息
     */
    public void initScrollData(ArrayList<HeadIndex> recomend){
        LinearLayout layout = (LinearLayout) findView.findViewById(R.id.scroll_lin);

        for (int i = 0;i<recomend.size();i++){
            HeadIndex index = recomend.get(i);

            LinearLayout layout1 = new LinearLayout(getActivity());
            ListView.LayoutParams lay = new ListView.LayoutParams(ListView.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            layout1.setLayoutParams(lay);
            layout1.setGravity(Gravity.CENTER);
            layout1.setOrientation(LinearLayout.VERTICAL);

            CircleImageView img = new CircleImageView(getActivity());
            LinearLayout.LayoutParams aaaa = new LinearLayout.LayoutParams(100, 100);
            img.setBorderWidth(1);
            img.setBorderColor(getActivity().getResources().getColor(R.color.write_color));
            aaaa.setMargins(10,10,10,10);
            img.setLayoutParams(aaaa);
            Picasso.with(getActivity()).load(index.getIndexImage()).into(img);
            layout1.addView(img);

            TextView txt1 = new TextView(getActivity());
            LinearLayout.LayoutParams aaaa1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            aaaa1.setMargins(10,10,10,10);
            txt1.setLayoutParams(aaaa1);
            txt1.setText(index.getIndexName());
            txt1.setTextColor(getActivity().getResources().getColor(android.R.color.background_dark));
            txt1.setGravity(Gravity.CENTER);
            txt1.setTextSize(16);

            layout1.addView(txt1);

            TextView txt2 = new TextView(getActivity());
            LinearLayout.LayoutParams aaaa2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            aaaa2.setMargins(10,10,10,10);
            txt2.setLayoutParams(aaaa2);
            txt2.setText(index.getIndexPersent());
            txt2.setTextColor(getActivity().getResources().getColor(android.R.color.darker_gray));
            txt2.setGravity(Gravity.CENTER);
            layout1.addView(txt2);

            layout.addView(layout1);
        }
    }

//    /**
//     * 精选课程数据
//     */
//    public ArrayList<HeadIndex> setGridviewData(){
//        ArrayList<HeadIndex> headIndices = new ArrayList<>();
//        for (int i = 0;i<2;i++){
//            HeadIndex headIndex = new HeadIndex();
//            headIndex.setIndexImage("http://img.lanrentuku.com/img/allimg/1605/5-1605291106390-L.jpg");
//            headIndex.setIndexName("测试标题"+i);
//            headIndex.setIndexNum("56");
//            headIndex.setIndexPersent("22");
//            headIndices.add(headIndex);
//        }
//        return headIndices;
//    }
//    /**
//     * 课程数据
//     */
//    public ArrayList<HeadIndex> setMyGridviewData(){
//        ArrayList<HeadIndex> headIndices = new ArrayList<>();
//        for (int i = 0;i<4;i++){
//            HeadIndex headIndex = new HeadIndex();
//            headIndex.setIndexImage("http://img.lanrentuku.com/img/allimg/1605/5-1605291106390-L.jpg");
//            headIndex.setIndexName("测试标题"+i);
//            headIndex.setIndexNum("55");
//            headIndex.setIndexPersent("25");
//            headIndices.add(headIndex);
//        }
//        return headIndices;
//    }

    private TextView mDayTxt,mDayTxt2,mDayTxt3,mDayTxt4;
    /**
     * 设置签到天数字体大小
     */
    public void setMyClassData(){
        mDayTxt = (TextView) myClassView.findViewById(R.id.class_day);

        mDayTxt2 = (TextView) myClassView.findViewById(R.id.success_class);
//        SpannableString ss2 = new SpannableString(mDayTxt2.getText().toString());
//        ss2.setSpan(new AbsoluteSizeSpan(20,true), 0, mDayTxt2.length()-1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);   //设置字体大小
//        mDayTxt2.setText(ss2);    //重新设置回去（不设置则没有用）

        mDayTxt3 = (TextView) myClassView.findViewById(R.id.leiji_class);
//        SpannableString ss3 = new SpannableString(mDayTxt3.getText().toString());
//        ss3.setSpan(new AbsoluteSizeSpan(20,true), 0, mDayTxt3.length()-1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);   //设置字体大小
//        mDayTxt3.setText(ss3);    //重新设置回去（不设置则没有用）

        mDayTxt4 = (TextView) myClassView.findViewById(R.id.jifen_class);
//        SpannableString ss4 = new SpannableString(mDayTxt4.getText().toString());
//        ss4.setSpan(new AbsoluteSizeSpan(20,true), 0, mDayTxt4.length()-1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);   //设置字体大小
//        mDayTxt4.setText(ss4);    //重新设置回去（不设置则没有用）


    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        switch (adapterView.getId()){
            case R.id.gridview_layout:
                Intent intent = new Intent(getActivity(), WebViewUpTitleActivity.class);
                startActivity(intent);
                break;
            case R.id.my_gridview:
                intent = new Intent(getActivity(), WebViewUpTitleActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
//            case R.id.heji_image_one:
//                Intent intent = new Intent(getActivity(), HeJiDetailActivity.class);
//                startActivity(intent);
//                break;
//            case R.id.heji_image_two:
//                intent = new Intent(getActivity(), HeJiDetailActivity.class);
//                startActivity(intent);
//                break;
//            case R.id.my_class_img_txt:
//                intent = new Intent(getActivity(), HeJiDetailActivity.class);
//                startActivity(intent);
//                break;
//            case R.id.my_class_start_txt:
//                intent = new Intent(getActivity(), HeJiDetailActivity.class);
//                startActivity(intent);
//                break;
            case R.id.my_class_img:
                Intent intent = new Intent(getActivity(), MessageActivity.class);
                startActivity(intent);
                break;
            case R.id.search_img:
                intent = new Intent(getActivity(), SerchActivity.class);
                startActivity(intent);
                break;
        }
    }

    /**
     * 页卡改变事件
     */
    private class TabOnPageChangeListener implements ViewPager.OnPageChangeListener {

        //当滑动状态改变时调用
        public void onPageScrollStateChanged(int state) {

        }

        //当前页面被滑动时调用
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        //当新的页面被选中时调用
        public void onPageSelected(int position) {
            switch (position) {
                case 0:
//                    mFind.setChecked(true);
                    break;
                case 1:
//                    mClass.setChecked(true);
                    break;

            }
        }
    }


    /**
     * 学堂，发现
     */
    private class SchoolFindAsyn extends AsyncTask<String,Void,String>{
        @Override
        protected String doInBackground(String... strings) {

            String message = HttpManager.newInstance().getHttpData(HttpManager.Information_School_URL);
            return message;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (!TextUtils.isEmpty(s)&&!s.equals(HttpManager.FAILED)){
                Gson gson = new Gson();
                FindInfo findInfo = gson.fromJson(s,FindInfo.class);
                parseData(findInfo);
            }
        }
    }
    public void parseData(FindInfo findInfo){
        if (findInfo != null){
            int sta = findInfo.getHead().getStatus();
            if (sta == 0){
                //精选课程
                List<FindInfo.ResultBean.ExcellentCoursesBean> excell= findInfo.getResult().getExcellentCourses();
                ArrayList<HeadIndex> headIndices = new ArrayList<>();
                for (int i = 0;i<excell.size();i++){
                    HeadIndex headIndex = new HeadIndex();
                    headIndex.setIndexImage(excell.get(i).getImgUrl());
                    headIndex.setIndexName(excell.get(i).getName());
                    headIndex.setIndexNum(excell.get(i).getFavorites()+"");
                    headIndex.setIndexPersent(excell.get(i).getComments()+"");
                    headIndices.add(headIndex);
                }
                selectClassAdapter.setData(headIndices);
                gridView.setAdapter(selectClassAdapter);
                //课堂合集
                List<FindInfo.ResultBean.ExceCollBean> exceCollBeen = findInfo.getResult().getExceColl();
                ArrayList<String> list = new ArrayList<>();
                for (int j = 0;j<exceCollBeen.size();j++){
                    list.add(exceCollBeen.get(j).getImgUrl());
                    }
                listViewadapter.setData(list);
                myListView.setAdapter(listViewadapter);

                //推荐用户
                List<FindInfo.ResultBean.RecommendUsersBean> recomd = findInfo.getResult().getRecommendUsers();
                ArrayList<HeadIndex> head = new ArrayList<>();
                for (int k =0;k<recomd.size();k++){
                    HeadIndex headIndex = new HeadIndex();
                    headIndex.setIndexImage(recomd.get(k).getImgUrl());
                    headIndex.setIndexName(recomd.get(k).getName());
                    headIndex.setIndexPersent(recomd.get(k).getSource());
                    head.add(headIndex);
                }
                initScrollData(head);
            }
            //
            List<FindInfo.ResultBean.CoursesBean> course = findInfo.getResult().getCourses();
            ArrayList<HeadIndex> headIndices = new ArrayList<>();
            for (int n = 0;n<course.size();n++){
                    HeadIndex headIndex = new HeadIndex();
                    headIndex.setIndexImage(course.get(n).getImgUrl());
                    headIndex.setIndexName(course.get(n).getName());
                    headIndex.setIndexNum(course.get(n).getFavorites()+"");
                    headIndex.setIndexPersent(course.get(n).getComments()+"");
                    headIndices.add(headIndex);
            }
            ClassAdapter.setData(headIndices);
            myGridView.setAdapter(ClassAdapter);
        }
    }

    /**
     * 我的课堂
     */
    private class MySchoolAsyn extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {
            HashMap<String,Object> hashMap = new HashMap<>();
            HashMap<String,String> map = new HashMap<>();
            map.put("PageIndex", "0");
            map.put("PageCount", "0");
            map.put("PageSize", "0");
            hashMap.put("PageInfo",map);
            hashMap.put("Id",strings[0]);
            String message = HttpManager.newInstance().getHttpDataByThreeLayer(strings[1],hashMap,HttpManager.My_School_URL);
            return message;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (!TextUtils.isEmpty(s)){
                Gson gson = new Gson();
                ClassInfo classInfo = gson.fromJson(s,ClassInfo.class);
                parseClassAsyn(classInfo);
            }
        }
    }

    /**
     * 课堂解析
     * @param classInfo
     */
    public void parseClassAsyn(ClassInfo classInfo){
        if (classInfo.getHead().getStatus() == 0){
            ClassInfo.ResultBean.UserDynasticBean dynmaic = classInfo.getResult().getUserDynastic();
            mDayTxt.setText(dynmaic.getSignDays()+"");

            String day2 = dynmaic.getFinishCourses()+"课";
            SpannableString ss2 = new SpannableString(day2);
            ss2.setSpan(new AbsoluteSizeSpan(20,true), 0, day2.length()-1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);   //设置字体大小
            mDayTxt2.setText(ss2);    //重新设置回去（不设置则没有用

            String day3 = dynmaic.getAccumulativeTotal()+"天";
            SpannableString ss3 = new SpannableString(day3);
            ss3.setSpan(new AbsoluteSizeSpan(20,true), 0, day3.length()-1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);   //设置字体大小
            mDayTxt3.setText(ss3);    //重新设置回去（不设置则没有用）

            String day4 = dynmaic.getCredits()+"分";
            SpannableString ss4 = new SpannableString(day4);
            ss4.setSpan(new AbsoluteSizeSpan(20,true), 0, day4.length()-1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);   //设置字体大小
            mDayTxt4.setText(ss4);    //重新设置回去（不设置则没有用）

            List<ClassInfo.ResultBean.UserCoursesBean> cours = classInfo.getResult().getUserCourses();
            ArrayList<String> list = new ArrayList<>();
            for (int i= 0;i<cours.size();i++){
                list.add(cours.get(i).getImgUrl());
            }
            adapter.setData(list);
            myClassList.setAdapter(adapter);
        }
    }


}
