package com.example.drawer.stockapp.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.drawer.stockapp.R;
import com.example.drawer.stockapp.activity.AgreementWebActivity;
import com.example.drawer.stockapp.activity.LianghuaWebView;
import com.example.drawer.stockapp.activity.MessageActivity;
import com.example.drawer.stockapp.activity.NewLoginActivity;
import com.example.drawer.stockapp.activity.SerchActivity;
import com.example.drawer.stockapp.activity.SetupZuHeActivity;
import com.example.drawer.stockapp.activity.chaKanZuHe;
import com.example.drawer.stockapp.adapter.CeLueAdapter;
import com.example.drawer.stockapp.adapter.MyViewPagerAdapter;
import com.example.drawer.stockapp.adapter.MyZuHeAdapter;
import com.example.drawer.stockapp.adapter.NiuRenAdapter;
import com.example.drawer.stockapp.customview.PagerSlidingTabStrip;
import com.example.drawer.stockapp.customview.view.XListView;
import com.example.drawer.stockapp.htttputil.HttpManager;
import com.example.drawer.stockapp.model.CeLueInfo;
import com.example.drawer.stockapp.model.CeLueListInfo;
import com.example.drawer.stockapp.model.MyZuHe;
import com.example.drawer.stockapp.model.MyzuheThree;
import com.example.drawer.stockapp.model.NiuRenInfo;
import com.example.drawer.stockapp.model.NiuRenInfoS;
import com.example.drawer.stockapp.model.NiuRenListInfo;
import com.example.drawer.stockapp.model.NiurenEntity;
import com.example.drawer.stockapp.model.UserInfo;
import com.example.drawer.stockapp.model.webClass;
import com.example.drawer.stockapp.utils.DensityUtils;
import com.example.drawer.stockapp.utils.ManagerUtil;
import com.example.drawer.stockapp.utils.ShapePreferenceManager;
import com.google.gson.Gson;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.scxh.slider.library.Indicators.PagerIndicator;
import com.scxh.slider.library.SliderLayout;
import com.scxh.slider.library.SliderTypes.BaseSliderView;
import com.scxh.slider.library.SliderTypes.TextSliderView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link AutoWisdomFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AutoWisdomFragment extends Fragment implements AdapterView.OnItemClickListener, View.OnClickListener, XListView.OnXScrollListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static final String CELUENAME = "celuename";
    public static final String ZUHETYPE = "type";
    public static final String BROAD_TYPE = "com.stock.flash";   //发送广播
    private Boolean isShowNoNet = false;   //是否显示无网络图（我的组合）
    private View mView, liangHuaZuHeView, niuRenZuHeView, myZuHeView, mSliderVIew, mSliderVIewTwo, mSliderVIewThree;
    private ViewPager mPager;
    private CeLueAdapter ceLueAdapter;
    private CeLueListInfo ceLueListInfo;
    private NiuRenListInfo niuRenListInfo, niuRenListInfoSave, niuRenListInfoMySave;
    private XListView listView, niurenList, myList;//三个listview
    private RelativeLayout mTitle;
    private ImageView mMessage, mSearch;
    private String mToken;
    private int MyZuhePage;
    private ImageView mLogin, mImageNoDataOne, mImageNoDataTwo, mImageNoDataThree;
    private SharedPreferences sp;
    private ArrayList<CeLueInfo> ceLueInfos;
    private ArrayList<NiuRenInfoS> NiuRenInfoS;//牛人详情集合
    private ArrayList<MyZuHe> MyZuHeSave;
    private NiuRenAdapter niuRenAdapter;
    private int page, niurenPage;
    private DecimalFormat df = new DecimalFormat("#0.00");   //保留两位小数
    private String tokenOld;   //刚进入时的token；与在次进入时比较，看是否刷新
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ArrayList<MyzuheThree> MyZuHelist = new ArrayList<>();
    private String UserId;
    private webClass webClassEntity;
    private List<NiurenEntity.ResultBean.DataBean> NiurenList;
    private MyzuheThree MyZuHeE;
    List<MyzuheThree.ResultBean.DataBean> Listdata = new ArrayList<>();
    private String messageOne;
    private String messageTwo;
    private String messageThree;

    private UserInfo userInfo;

    private String Id; //用户ID

    public AutoWisdomFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AutoWisdomFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AutoWisdomFragment newInstance(String param1, String param2) {
        AutoWisdomFragment fragment = new AutoWisdomFragment();
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

        /**
         * 跟投，订阅，创建等是否刷新
         */
        IntentFilter filter = new IntentFilter();
        // 向过滤器中添加action
        filter.addAction(BROAD_TYPE);
        // 注册广播
        getContext().registerReceiver(isFlashBroad, filter);
    }

    private Boolean isFlash = false;
    /**
     * 广播接收者
     */
    private BroadcastReceiver isFlashBroad = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            isFlash = true;
        }

    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(R.layout.fragment_auto_wisdom, container, false);
            sp = ShapePreferenceManager.getMySharedPreferences(getActivity());
            UserId = sp.getString(ShapePreferenceManager.ID, null);
            Log.i("onCreateView里UserId", UserId + "");
            initWight();
            initData();
            CeLueInfoSave = new ArrayList<>();
            getCelueInfo(page);
            niuRenInfosSave = new ArrayList<>();
            getNiuRenListData(niurenPage);
            //banner加载
            GetBannerInfo getBannerInfo = new GetBannerInfo();
            getBannerInfo.execute();
        }
        return mView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (CeLueInfoSave != null) {
            CeLueInfoSave.clear();
            ceLueListInfo = null;
        }
        if (myListSave != null) {
            myListSave.clear();
            niuRenListInfoMySave = null;
        }
        if (niuRenInfosSave != null) {
            niuRenInfosSave.clear();
            niuRenListInfo = null;
        }
        if (isFlashBroad != null) {
            getContext().unregisterReceiver(isFlashBroad);
        }
    }


    @Override
    public void onResume() {
        super.onResume();

        getNiuRenListData(page);





        SystemBarTintManager tintManager = ManagerUtil.newInstance(getActivity());
        ManagerUtil.setStataBarColorWhite(getActivity(), tintManager);
        mToken = sp.getString(ShapePreferenceManager.TOKEN, null);

        UserId = sp.getString(ShapePreferenceManager.ID, null);

        if (TextUtils.isEmpty(UserId)&&!TextUtils.isEmpty(mToken)) {
            UserInfoAsyn asyn = new UserInfoAsyn();
            asyn.execute(mToken);
        }
        UserId = sp.getString(ShapePreferenceManager.ID, null);

        UserId = sp.getString(ShapePreferenceManager.ID, null);

        if (tokenOld == null && TextUtils.isEmpty(tokenOld)) {
            tokenOld = mToken;
        }
        if (!TextUtils.isEmpty(mToken)) {
            if (tokenOld.equals(mToken)) {
                if (isFlash) {

                    myListSave = new ArrayList<>();
                    mLogin.setVisibility(View.GONE);
                    getMyListData(MyZuhePage);
                } else if (niuRenListInfoMySave == null) {
                    myListSave = new ArrayList<>();
                    mLogin.setVisibility(View.GONE);
                    getMyListData(MyZuhePage);
                } else {
                    mLogin.setVisibility(View.GONE);
                    //myZuHeAdapter.setData(myListSave);
                    myList.setAdapter(myZuHeAdapter);
                }
            } else {
                myListSave = new ArrayList<>();
                mLogin.setVisibility(View.GONE);
                getMyListData(MyZuhePage);
            }
        } else {
            mLogin.setVisibility(View.VISIBLE);
        }
    }

    private PagerSlidingTabStrip tabs;

    /**
     * 初始化控件
     */
    public void initWight() {


        mTitle = (RelativeLayout) mView.findViewById(R.id.all_title);
        //设置距离顶部状态栏高度
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                DensityUtils.dp2px(getActivity(), 50));
        params.setMargins(0, ManagerUtil.getStatusBarHeight(getActivity()), 0, 0);
        mTitle.setLayoutParams(params);


        tintManager = ManagerUtil.newInstance(getActivity());
        tintManager.setStatusBarTintEnabled(true);
        mTitle.setBackgroundColor(getResources().getColor(R.color.write_color));
//        tintManager.setStatusBarTintColor(getResources().getColor(R.color.write_color));

        mMessage = (ImageView) mView.findViewById(R.id.wisdom_info);
        mSearch = (ImageView) mView.findViewById(R.id.pop_item_img);

        tabs = (PagerSlidingTabStrip) mView.findViewById(R.id.wisdom_group);

        mPager = (ViewPager) mView.findViewById(R.id.wisdom_content_pager);   //viewpager
        mPager.setOffscreenPageLimit(1);
        mPager.setOnPageChangeListener(new TabOnPageChangeListener());

        mMessage.setOnClickListener(this);
        mSearch.setOnClickListener(this);


    }

    private int mCurrentfirstVisibleItem = 0;
    private SparseArray recordSp = new SparseArray(0);
    private SparseArray recordSp2 = new SparseArray(0);
    private SparseArray recordSp3 = new SparseArray(0);
    protected SystemBarTintManager tintManager;
    private MyZuHeAdapter myZuHeAdapter;

    /**
     * 初始化适配器数据
     */
    public void initData() {


        List<View> viewList = new ArrayList<View>();
        LayoutInflater mInflater = LayoutInflater.from(getActivity());
        liangHuaZuHeView = mInflater.inflate(R.layout.lianghuacelue_layout, null);      //量化策略
        niuRenZuHeView = mInflater.inflate(R.layout.niurenzuhe_layout, null);          //牛人组合
        myZuHeView = mInflater.inflate(R.layout.my_zuhe_layout, null);                //我的组合

        mImageNoDataOne = (ImageView) liangHuaZuHeView.findViewById(R.id.loading_failed);
        mImageNoDataTwo = (ImageView) niuRenZuHeView.findViewById(R.id.loading_failed_two);
        mImageNoDataThree = (ImageView) myZuHeView.findViewById(R.id.loading_failed_three);

        mImageNoDataOne.setOnClickListener(this);
        mImageNoDataTwo.setOnClickListener(this);
        mImageNoDataThree.setOnClickListener(this);

        viewList.add(liangHuaZuHeView);
        viewList.add(niuRenZuHeView);
        viewList.add(myZuHeView);
        ArrayList<String> titles = new ArrayList<>();
        titles.add("智能组合");
        titles.add("牛人组合");
        titles.add("我的组合");
        mSliderVIew = mInflater.inflate(R.layout.imageslider_layout_two, null);    //第一个head imageSlider
        mSliderVIewTwo = mInflater.inflate(R.layout.imageslider_layout_celue_two, null);
        mSliderVIewThree = mInflater.inflate(R.layout.imageslider_layout_celue_three, null);
        MyViewPagerAdapter adapter = new MyViewPagerAdapter(viewList, titles);
        mPager.setAdapter(adapter);
        tabs.setViewPager(mPager);


        //量化策略
        listView = (XListView) liangHuaZuHeView.findViewById(R.id.lianghuacelue_list);
        listView.setPullLoadEnable(true);    //设置上拉加载
        listView.setOnItemClickListener(this);
        ceLueAdapter = new CeLueAdapter(getActivity());
        listView.addHeaderView(mSliderVIew);

        listView.setXListViewListener(new XListView.IXListViewListener() {
            @Override
            public void onRefresh() {
                //Log.i("量化 page", "onRefresh: "+page);
                page = 0;
                getCelueInfo(page);

            }

            @Override
            public void onLoadMore() {
                //Log.i("量化 page2", "onRefresh: "+page++);
//                page++;
                page = 0;
                getCelueInfo(page);
            }
        });    //注册监听

        listView.setOnScrollListener(this);

        //牛人组合
        niurenList = (XListView) niuRenZuHeView.findViewById(R.id.niuren_listview);
        niurenList.setOnItemClickListener(this);
        niurenList.setPullLoadEnable(true);    //设置上拉加载
        niurenList.addHeaderView(mSliderVIewTwo);
        niuRenAdapter = new NiuRenAdapter(getActivity());

        niurenList.setXListViewListener(new XListView.IXListViewListener() {
            @Override
            public void onRefresh() {
                niurenPage = 0;
                getNiuRenListData(niurenPage);
            }

            @Override
            public void onLoadMore() {
                niurenPage++;
                getNiuRenListData(niurenPage);
            }
        });    //注册监听
        niurenList.setOnScrollListener(null);


        //我的组合
        myList = (XListView) myZuHeView.findViewById(R.id.my_zuhe_listview);
//        ImageView mAddImg = (ImageView) myZuHeView.findViewById(R.id.add_image);
        mLogin = (ImageView) myZuHeView.findViewById(R.id.my_zuhe_img);
        mLogin.setOnClickListener(this);
        myList.setPullLoadEnable(true);    //设置上拉加载
//        mAddImg.setOnClickListener(this);
        //TODO myList.setOnItemClickListener(this);
        myList.addHeaderView(mSliderVIewThree);
        myList.setXListViewListener(new XListView.IXListViewListener() {
            @Override
            public void onRefresh() {
                MyZuhePage = 0;
                Log.i("MYZuhePage", MyZuhePage + ".");
                getMyListData(MyZuhePage);
            }

            @Override
            public void onLoadMore() {
                MyZuhePage++;
                getMyListData(MyZuhePage);
//                onLoadMy();
            }
        });
        myList.setOnScrollListener(null);
        myZuHeAdapter = new MyZuHeAdapter(getActivity());

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.add_zuhe_layout, null);
        TextView mAddZuhe = (TextView) view.findViewById(R.id.add_image_txt);
        //myList.addFooterView(view);

        mAddZuhe.setOnClickListener(this);


    }


    private ArrayList<String> images;

    /**
     * imageSlider控件加入
     */
    public void getSliderLayoutView(ArrayList<String> mImage, final ArrayList<String> mString) {
        SliderLayout mSliderLayout = (SliderLayout) mSliderVIew.findViewById(R.id.image_slider_layout);
//        mSliderLayout.setMinimumHeight(180);

        PagerIndicator pagerIndicator = (PagerIndicator) mSliderVIew.findViewById(R.id.custom1_indicator);
        Log.d("路径fds", mImage.toString() + mString.toString());
        mSliderLayout.removeAllSliders();
        int length = mImage.size();
        for (int i = 0; i < length; i++) {
            TextSliderView sliderView = new TextSliderView(getContext());   //向SliderLayout中添加控件
            sliderView.image(mImage.get(i));
//            sliderView.description(mString[i]);
            final int finalI = i;
            sliderView.setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                @Override
                public void onSliderClick(BaseSliderView slider) {
                    Intent intent = new Intent(getContext(), AgreementWebActivity.class);
                    intent.putExtra(AgreementWebActivity.URLTYPE, 3);
                    intent.putExtra(AgreementWebActivity.URL, mString.get(finalI) + "&isApp=true");
                    startActivity(intent);
                }
            });
            mSliderLayout.addSlider(sliderView);
        }
//        mSliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);  //将小圆点设置到右下方
        mSliderLayout.setCustomIndicator(pagerIndicator);  //将小圆点设置到右下方(自定义控件指示器)

    }

    /**
     * imageSlider控件加入
     */
    public void getSliderLayoutViewTwo(ArrayList<String> mImage, final ArrayList<String> mString) {
        SliderLayout mSliderLayout = (SliderLayout) mSliderVIewTwo.findViewById(R.id.image_slider_layout);
//        mSliderLayout.setMinimumHeight(180);

        PagerIndicator pagerIndicator = (PagerIndicator) mSliderVIewTwo.findViewById(R.id.custom1_indicator);

        mSliderLayout.removeAllSliders();
        int length = mImage.size();
        for (int i = 0; i < length; i++) {
            TextSliderView sliderView = new TextSliderView(getContext());   //向SliderLayout中添加控件
            sliderView.image(mImage.get(i));
//            sliderView.description(mString[i]);
            final int finalI = i;
            sliderView.setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                @Override
                public void onSliderClick(BaseSliderView slider) {
                    Intent intent = new Intent(getContext(), AgreementWebActivity.class);
                    intent.putExtra(AgreementWebActivity.URLTYPE, 3);
                    intent.putExtra(AgreementWebActivity.URL, mString.get(finalI) + "&isApp=true");
                    startActivity(intent);
                }
            });
            mSliderLayout.addSlider(sliderView);
        }
//        mSliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);  //将小圆点设置到右下方
        mSliderLayout.setCustomIndicator(pagerIndicator);  //将小圆点设置到右下方(自定义控件指示器)

    }

    /**
     * imageSlider控件加入
     */
    public void getSliderLayoutViewThree(ArrayList<String> mImage, final ArrayList<String> mString) {
        SliderLayout mSliderLayout = (SliderLayout) mSliderVIewThree.findViewById(R.id.image_slider_layout);
//        mSliderLayout.setMinimumHeight(180);

        PagerIndicator pagerIndicator = (PagerIndicator) mSliderVIewThree.findViewById(R.id.custom1_indicator);

        mSliderLayout.removeAllSliders();
        int length = mImage.size();
        for (int i = 0; i < length; i++) {
            TextSliderView sliderView = new TextSliderView(getContext());   //向SliderLayout中添加控件
            sliderView.image(mImage.get(i));
//            sliderView.description(mString[i]);
            final int finalI = i;
            sliderView.setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                @Override
                public void onSliderClick(BaseSliderView slider) {
                    Intent intent = new Intent(getContext(), AgreementWebActivity.class);
                    intent.putExtra(AgreementWebActivity.URLTYPE, 3);
                    intent.putExtra(AgreementWebActivity.URL, mString.get(finalI) + "&isApp=true");
                    startActivity(intent);
                }
            });
            mSliderLayout.addSlider(sliderView);
        }
//        mSliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);  //将小圆点设置到右下方
        mSliderLayout.setCustomIndicator(pagerIndicator);  //将小圆点设置到右下方(自定义控件指示器)

    }

    private ArrayList<String> strings;

    /**
     * 获取banner图
     */
    private class GetBannerInfo extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            HashMap<String, String> map = new HashMap<>();
            map.put("PageIndex", "0");
            map.put("PageCount", "0");
            map.put("PageSize", "0");
            String message = HttpManager.newInstance().getHttpDataByBanner("", map, HttpManager.BannerList_URL, 2);
            //Log.i("这是message", " 开始"+message+"结束");
            return message;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            //Log.d("banner这是",s.toString());
            if (!TextUtils.isEmpty(s) && !s.equals(HttpManager.FAILED)) {
                try {
                    JSONObject object = new JSONObject(s);
                    JSONObject d = object.getJSONObject("Result");
                    JSONArray data = d.getJSONArray("Data");
                    images = new ArrayList<>();
                    strings = new ArrayList<>();
                    for (int i = 0; i < data.length(); i++) {
                        Object image = data.getJSONObject(i).getJSONArray("Imgs").get(0);
                        String imagePath = (String) image;
                        images.add(imagePath);
                        String TargetUrl = data.getJSONObject(i).getString("TargetUrl");
                        strings.add(TargetUrl);
                    }
                    getSliderLayoutView(images, strings);
                    getSliderLayoutViewTwo(images, strings);
                    getSliderLayoutViewThree(images, strings);
                    //Log.d("路径1",TargetUrl);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return;
//                Gson gson = new Gson();
//                BannerInfo bannerInfo = gson.fromJson(s, BannerInfo.class);
//                if (bannerInfo.getHead().getStatus() == 0) {
//                    List<BannerInfo.ResultBean.DataBean> size = bannerInfo.getResult().getBannerUrl();
//                    images = new ArrayList<>();
//                    strings = new ArrayList<>();
//
//                    for (int i = 0; i < size.size(); i++) {
//                        if (i>3){
//                            images.add(size.get(i).getImgs().get(0));
//                            strings.add(size.get(i).getTargetUrl());     //bar图
//                        }
//                    }
//                    Log.i("范文芳发放", images+"onPostExecute: ");
//                    Log.i("范文芳发", strings+"onPostExecute: ");
//                    //添加轮播图数据
//                    getSliderLayoutView(images, strings);
                //                   getSliderLayoutViewTwo(images, strings);
//                    getSliderLayoutViewThree(images, strings);
//
//                }
            }
        }

    }

    /**
     * 获取量化策略列表
     */
    public void getCelueInfo(final int page) {
        new AsyncTask() {

            @Override
            protected Object doInBackground(Object[] objects) {
                HashMap<String, String> map = new HashMap<>();
                map.put("PageIndex", page + "");
                map.put("PageCount", "0");
                map.put("PageSize", "0");
                Log.i("page", page + "");
                String message = HttpManager.newInstance().getHttpDataByZuHe(mToken, map, HttpManager.AMZPortfolioList_URl, 3);
                return message;
            }

            @Override
            protected void onPostExecute(Object o) {
                Log.i("量化原始数据", o.toString());
                super.onPostExecute(o);
                onLoad();
                String message = (String) o;
                try {
                    Gson gson = new Gson();
                    webClassEntity = gson.fromJson(message, webClass.class);

                    ceLueInfos = new ArrayList<CeLueInfo>();
                    JSONObject messageObject = new JSONObject(message);
                    JSONObject resultobjc = messageObject.getJSONObject("Result");
                    JSONArray DataArray = resultobjc.getJSONArray("Data");
                    for (int i = 0; i < DataArray.length(); i++) {
                        CeLueInfo CeLueInf = new CeLueInfo();
                        CeLueInf.setUserName(DataArray.getJSONObject(i).getString("Name") + "");
                        CeLueInf.setDesc(DataArray.getJSONObject(i).getString("Desc") + "");
                        CeLueInf.setAlongCount(DataArray.getJSONObject(i).getDouble("AlongCount"));//多少人跟投
                        CeLueInf.setProfitLossProportion(DataArray.getJSONObject(i).getJSONObject("PorfolioOther").getDouble("ProfitLossProportion"));//交易胜率
                        CeLueInf.setMonthProfitRate(DataArray.getJSONObject(i).getJSONObject("PorfolioOther").getDouble("MonthProfitRate"));//近一月收益率
                        CeLueInf.setCumulativeReturn(DataArray.getJSONObject(i).getJSONObject("PorfolioOther").getDouble("CumulativeReturn"));//；累计收益率
                        ceLueInfos.add(CeLueInf);
                    }
                    Log.d("量化组合数据", ceLueInfos.toString());
                } catch (Exception exception) {
                    Log.d("异常", CeLueInfoSave.toString());
                }

                if (!TextUtils.isEmpty(message) && !message.equals(HttpManager.FAILED)) {
//                    Gson gson = new Gson();
//                    ceLueListInfo = gson.fromJson(message, CeLueListInfo.class);
//                    Log.d("量化策略1", "onPostExecute: "+ceLueListInfo.toString());
//                    if (ceLueListInfo.getHead().getStatus() == 0) {
                    // ArrayList<CeLueInfo> celueList = setLianghuaCelueData();
                    //Log.d("量化策略", " "+ceLueInfosSave.toString());

                    if (page == 0) {
                        CeLueInfoSave = ceLueInfos;
                        Log.i("量化celueinfos1", ceLueInfos.toString());

                        ceLueAdapter.setData(ceLueInfos);

                        Log.i("量化celueinfos2", ceLueInfos.toString());
                        listView.setAdapter(ceLueAdapter);
                    } else if (page > 0 && ceLueInfos.size() > 0) {
                        ceLueAdapter.addData(ceLueInfos);
                    } else {
                        Toast.makeText(getActivity(), "没有更多了", Toast.LENGTH_SHORT).show();
                    }
                }
//                }else {
//                    Toast.makeText(getActivity(),"获取数据失败",Toast.LENGTH_SHORT).show();
//                    mImageNoDataOne.setVisibility(View.VISIBLE);
//                }

            }
        }.execute();
    }

    /**
     * 获取牛人组合列表数据
     */
    public void getNiuRenListData(final int page) {
        new AsyncTask() {

            @Override
            protected Object doInBackground(Object[] objects) {
                HashMap<String, String> map = new HashMap<>();
                map.put("PageIndex", "" + page);
                map.put("PageCount", "0");
                map.put("PageSize", "0");
                String message = HttpManager.newInstance().getHttpDataByZuHeNiuRen(mToken, map, HttpManager.AMZPortfolioList_URl, 1);
                Log.d("牛人组合" + System.currentTimeMillis(), message);
                return message;
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
                onLoadNiu();
                String message = (String) o;
                //Log.d("牛人fsf",message);

                NiuRenInfoS = new ArrayList<NiuRenInfoS>();
                try {

                    JSONObject messageObject = new JSONObject(message);
                    JSONObject ResultObject = messageObject.getJSONObject("Result");
                    JSONArray DataArray = ResultObject.getJSONArray("Data");
                    for (int i = 0; i < DataArray.length(); i++) {
                        NiuRenInfoS niuRen = new NiuRenInfoS();
                        niuRen.setName(DataArray.getJSONObject(i).getString("Name"));
                        niuRen.setUserAvatar(DataArray.getJSONObject(i).getString("UserAvatar"));
                        niuRen.setProfitLossProportion(DataArray.getJSONObject(i).getJSONObject("PorfolioOther").getDouble("ProfitLossProportion"));
                        niuRen.setHoldCount(DataArray.getJSONObject(i).getJSONObject("PorfolioOther").getInt("HoldCount"));
                        niuRen.setPosition(DataArray.getJSONObject(i).getJSONObject("PorfolioOther").getDouble("Position"));
                        niuRen.setMonthProfitRate(DataArray.getJSONObject(i).getJSONObject("PorfolioOther").getDouble("MonthProfitRate"));
                        niuRen.setCumulativeReturn(DataArray.getJSONObject(i).getJSONObject("PorfolioOther").getDouble("CumulativeReturn"));
                        NiuRenInfoS.add(niuRen);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //Log.d("牛人fsfasdf",NiuRenInfoS.toString());
                if (!TextUtils.isEmpty(message) && !message.equals(HttpManager.FAILED)) {
                    Gson gson = new Gson();
                    NiurenEntity NiurenGson = gson.fromJson(message, NiurenEntity.class);
                    Log.i("牛人gson解析", NiurenGson.toString());
                    if(NiurenGson!=null){
                        NiurenList = NiurenGson.getResult().getData();
                    }
                    NiuRenListInfo niuRenListInfo = gson.fromJson(message, NiuRenListInfo.class);
                    //Log.d("牛人", "onPostExecute: "+niuRenListInfo.toString());
                    if (niuRenListInfo.getHead().getStatus() == 0) {
                        //ArrayList<NiuRenInfo> niurenInfoList = setNiuRenData(niuRenListInfo);
                        niuRenListInfoSave = niuRenListInfo;
                        if (niurenPage == 0) {
                            niuRenInfosSave = NiuRenInfoS;
                            niuRenAdapter.setData(NiuRenInfoS);
                            niurenList.setAdapter(niuRenAdapter);
                        } else if (niurenPage > 0 && NiuRenInfoS.size() > 0) {
                            niuRenAdapter.addData(NiuRenInfoS);
                        } else {
                            Toast.makeText(getActivity(), "没有更多了", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    mImageNoDataTwo.setVisibility(View.VISIBLE);
                }
            }
        }.execute();
    }

    /**
     * 我的组合列表
     */
    public void getMyListData(final int MyZuPage) {
        final ArrayList<String> list = new ArrayList<>();

        new AsyncTask() {

            @Override
            protected Object doInBackground(Object[] objects) {
                HashMap<String, String> map = new HashMap<>();
                map.put("PageIndex", "" + MyZuhePage);
                map.put("PageCount", "0");
                map.put("PageSize", "10");
                Log.d("AutoWisdomFragment", "USERID:" + UserId);
                messageOne = HttpManager.newInstance().getHttpDataByZuHeMyOne(mToken, map, HttpManager.AMZPortfolioList_URl, 2, UserId);
                messageTwo = HttpManager.newInstance().getHttpDataByZuHeMyTwo(mToken, map, HttpManager.AMZPortfolioList_URl, 2, UserId);
                messageThree = HttpManager.newInstance().getHttpDataByZuHeMyThree(mToken, map, HttpManager.AMZPortfolioList_URl, 2, UserId);
                list.add(messageOne);
                list.add(messageTwo);
                list.add(messageThree);
                return list.toString();
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
                String message = (String) o;
                Gson gson = new Gson();
//                if(!TextUtils.isEmpty(messageOne)){
//                    MyZuHeE = gson.fromJson(messageOne, MyzuheThree.class);
//                    Log.i("one",MyZuHeE.toString());
//                    for(int t=0;t<MyZuHeE.getResult().getData().size();t++){
//                        MyzuheThree.ResultBean.DataBean databO=MyZuHeE.getResult().getData().get(t);
//                        Listdata.add(databO);
//                    }
//                }
//                if(!TextUtils.isEmpty(messageTwo)){
//                    MyzuheThree MyZuHeET = gson.fromJson(messageTwo, MyzuheThree.class);
//                    Log.i("one2",MyZuHeET.toString());
//
//                    for(int t=0;t<MyZuHeET.getResult().getData().size();t++){
//                        MyzuheThree.ResultBean.DataBean databTQ=MyZuHeET.getResult().getData().get(t);
//                        Listdata.add(databTQ);
//                    }
//                }
//                if(!TextUtils.isEmpty(messageThree)){
//                    MyzuheThree MyZuHeETR = gson.fromJson(messageThree, MyzuheThree.class);
//                    Log.i("one3",MyZuHeETR.toString());
//
//                    for(int t=0;t<MyZuHeETR.getResult().getData().size();t++){
//                        MyzuheThree.ResultBean.DataBean databT=MyZuHeETR.getResult().getData().get(t);
//                        Listdata.add(databT);
//                    }
//                }
                Log.d("我的组合onPostExecute", message);
                Log.i("wode zuhejieguo", list.toString());
                try {
                    if (MyZuPage == 0) {
                        Listdata.clear();
                    }
                        for (int a = 0; a < list.size(); a++) {
                            Log.i("3个实体类", list.get(a) + list.size());
                            String mes = list.get(a);
                            MyZuHeE = gson.fromJson(mes, MyzuheThree.class);
                            if (MyZuHeE.getHead().getMsg().equals("success")) {
                                Log.i("实体类", MyZuHeE.toString());
                                for (int k = 0; k < MyZuHeE.getResult().getData().size(); k++) {
                                    MyzuheThree.ResultBean.DataBean data = MyZuHeE.getResult().getData().get(k);
                                    Log.i("执行了几次", data.toString());
                                    Listdata.add(data);
                                }
                            }
                        }

//                    Log.d("我的组合3次总数据", ""+MyZuHelist.toString());
                } catch (Exception x) {
                    Log.d("这有异常", "a");
                }
                for (int i = 0; i < MyZuHelist.size(); i++) {
                    MyzuheThree.ResultBean result = MyZuHelist.get(i).getResult();
                    if (result != null) {
                        for (MyzuheThree.ResultBean.DataBean dataBean : result.getData()) {
                            Listdata.add(dataBean);
                        }
                    }
                }
                Log.i("data", Listdata.toString());


                Log.d("AutoWisdomFragment", "MyZuPage:" + MyZuPage);
                if (MyZuPage == 0) {

                    Log.d("AutoWisdomFragment", "MyZuPage00:" + MyZuPage);

                    myZuHeAdapter.setData(Listdata);
                    myList.setAdapter(myZuHeAdapter);
                    Log.i("myzupage", MyZuPage + "..");
                    onLoadMy();
                } else if (MyZuPage > 0 && Listdata.size() > 0) {

                    Log.d("AutoWisdomFragment", "MyZuPagedayu:" + MyZuPage);
                    myZuHeAdapter.addData(Listdata);
                    myList.setAdapter(myZuHeAdapter);
                    onLoadMy();
                    Log.i("myzupage", MyZuPage + ".1.");

                } else {
                    Toast.makeText(getActivity(), "没有更多了", Toast.LENGTH_SHORT).show();
                }


//                if (!TextUtils.isEmpty(message)&&!message.equals(HttpManager.FAILED)) {
//                    niuRenListInfo = gson.fromJson(message, NiuRenListInfo.class);
//                    if (niuRenListInfo.getHead().getStatus() == 0) {
//                        niuRenListInfoMySave = niuRenListInfo;
//                        getMyCollect(setMyZuHeData());
//                    }else {
//                        if (myInfoList == null){
//                            myInfoList = new ArrayList<>();   //我的组合信息
//                        }
//                        getMyCollect(myInfoList);
//                    }
//                }else {
//                    isShowNoNet = true;
//                    mImageNoDataThree.setVisibility(View.VISIBLE);
//                }

            }
        }.execute();
    }

    /**
     * 我的订阅
     */
    public void getMyCollect(final ArrayList<NiuRenInfo> niurenList) {
        new AsyncTask() {

            @Override
            protected Object doInBackground(Object[] objects) {
                HashMap<String, String> map = new HashMap<>();
                map.put("PageIndex", "0");
                map.put("PageCount", "0");
                map.put("PageSize", "100");
                String message = HttpManager.newInstance().getHttpDataByTwoLayer(mToken, map, HttpManager.MyCollectPorfolio_URL);
                return message;
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
                onLoadMy();
                String message = (String) o;
                if (!TextUtils.isEmpty(message) && !message.equals(HttpManager.FAILED)) {
                    Gson gson = new Gson();
                    niuRenListInfo = gson.fromJson(message, NiuRenListInfo.class);
                    if (niuRenListInfo.getHead().getStatus() == 0) {
                        setMyZuHeInfo(niurenList);
                    } else {
                        if (niurenList.size() == 0) {
                            mLogin.setVisibility(View.VISIBLE);
                        }
                    }
                } else {
                    if (isShowNoNet) {  //显示图片
                        mImageNoDataThree.setVisibility(View.VISIBLE);
                        isShowNoNet = false;
                    }
                }


            }
        }.execute();
    }

    /**
     * 设置我的组合、订阅等
     *
     * @param niurenList
     */
    public void setMyZuHeInfo(ArrayList<NiuRenInfo> niurenList) {
        List<NiuRenListInfo.ResultBean.StrategiesBean> starPorfolioBeen = niuRenListInfo.getResult().getStrategies();
        for (int i = 0; i < starPorfolioBeen.size(); i++) {
            NiuRenListInfo.ResultBean.StrategiesBean ben = starPorfolioBeen.get(i);
            NiuRenInfo info = new NiuRenInfo();
            info.setId(ben.getId());
            info.setNiurenName(ben.getTitle());
            info.setShouyiRate(Double.parseDouble(df.format(ben.getTotleReturns())));
            info.setStockType(ben.getDesc());
            info.setZuheType(3);   //全是订阅
            info.setTradeTime(ben.getFavorites() + "");
            niurenList.add(info);
        }
        if (niurenList.size() > 0) {
            myListSave = niurenList;
        }
//        myZuHeAdapter.setData(niurenList);
//        myList.setAdapter(myZuHeAdapter);

    }

    /**
     * 初始化量化策略数据
     */
    public ArrayList<CeLueInfo> setLianghuaCelueData() {

        ArrayList<CeLueInfo> ceLueInfos = new ArrayList<>();
        List<CeLueListInfo.ResultBean.b> strategiesBeen = ceLueListInfo.getResult().getData();
        //Log.d("ffdfdds",""+strategiesBeen.toString());


//        CeLueInfo.profolother profolother = new CeLueInfo.profolother();
//        CeLueListInfo.ResultBean.profolother other=new CeLueListInfo.ResultBean.profolother();
//        double p2 = other.getProfitLossProportion();

        for (int i = 0; i < strategiesBeen.size(); i++) {
            CeLueListInfo.ResultBean.b ben = strategiesBeen.get(i);
            CeLueListInfo.ResultBean.profolother ther = new CeLueListInfo.ResultBean.profolother();
            CeLueInfo info = new CeLueInfo();
            info.setDesc(ben.getDesc());
            ceLueInfos.add(info);

        }


//            info.setId(ben.getId());
//            info.setTitle(ben.getName());
//            info.setJingZhiNum(ben.getTargetReturns() + "%");
//            info.setMaxNum(ben.getMaxDay() + "天");
//            info.setRateNum(ben.getShareRatio() + "%");
//            info.setName((String) ben.getUserName());
//            info.setMinGengTou(ben.getMinFollow() + "");
//            info.setOtherInfo((String) ben.getDesc());
//            info.setHeadImage((String) ben.getUserImgUrl());
//            info.setEndInvestment(ben.isIsEndInvestment());      //运行是否结束
//            info.setEndRecruit(ben.isIsEndRecruit());      //招募是否结束
//            info.setStartInvestment(ben.isIsStartInvestment());   //运行是否结束
//            if (ben.isIsNotStartRecruit()){
//                info.setType(4);
//            }else if (ben.isIsStartRecruit()){   //招募中
//                info.setCeluePersent(df.format(ben.getRecruitment()));
//                info.setType(2);
//                ceLueInfos.add(info);
//            }else if (ben.isIsStartInvestment()&&!ben.isIsStartRun()){   //待运行
//                info.setCeluePersent(df.format(ben.getRecruitment()));
//                info.setType(5);
//                ceLueInfos.add(info);
//            }else if (ben.isIsStartInvestment()&&ben.isIsStartRun()&&ben.getRunEndDay() == null){//运行中
//                info.setCeluePersent(df.format(ben.getTotalReturn()));
//                info.setRunTime(ben.getRunStartDay());
//                info.setType(1);
//                ceLueInfos.add(info);
//            }else if (ben.isIsEndInvestment()||ben.getRunEndDay() != null){   //已结束
//                info.setCeluePersent(df.format(ben.getTotalReturn()));
//                info.setEndStatus(ben.getRunEndState());
//                info.setType(3);
//                ceLueInfos.add(info);
//            }
//        }
        CeLueInfoSave = ceLueInfos;
        return ceLueInfos;
    }

    /**
     * 初始化牛人数据
     *
     * @return
     */
    public ArrayList<NiuRenInfo> setNiuRenData(NiuRenListInfo niuRenListInfolist) {
        ArrayList<NiuRenInfo> niuRenInfos = new ArrayList<>();
        List<NiuRenListInfo.ResultBean.StrategiesBean> starPorfolioBeen = niuRenListInfolist.getResult().getStrategies();
        Log.i("niuren", starPorfolioBeen + "");
        for (int i = 0; i < starPorfolioBeen.size(); i++) {
            NiuRenListInfo.ResultBean.StrategiesBean ben = starPorfolioBeen.get(i);
            NiuRenInfo info = new NiuRenInfo();
            info.setId(ben.getId());
            info.setNiurenHead(ben.getUserImgUrl());
            info.setNiurenName(ben.getTitle() + "");
            info.setNiurenRoundImage("http://img2.imgtn.bdimg.com/it/u=1689964256,2679873424&fm=21&gp=0.jpg");
            info.setShouyiRate(Double.parseDouble(df.format(ben.getTotleReturns())));
            info.setVictorRate(ben.getWinRatio() + "%");
            info.setShouyiByMonth(ben.getMonthlyAverage());
            info.setStockNum(ben.getHolding());
            info.setCangweiRate(df.format(ben.getPosition()) + "%");
            info.setDayNum(ben.getAveragePosition());
            info.setTradeTime(ben.getAverageTrading() + "");
            info.setStockType(ben.getTitle());
            niuRenInfos.add(info);
        }
        return niuRenInfos;
    }


    private ArrayList<NiuRenInfo> myInfoList, myListSave;
    private ArrayList<NiuRenInfoS> niuRenInfosSave;
    private ArrayList<CeLueInfo> CeLueInfoSave;

    /**
     * 初始化我的组合数据
     *
     * @return
     */
    public ArrayList<NiuRenInfo> setMyZuHeData() {
        long nowTime = System.currentTimeMillis();
        //Log.d("tag","nowTime----"+nowTime);
        myInfoList = new ArrayList<>();   //我的组合信息
        DecimalFormat df = new DecimalFormat("#0.00");   //保留两位小数
        List<NiuRenListInfo.ResultBean.StrategiesBean> starPorfolioBeen = niuRenListInfo.getResult().getStrategies();
//        for (int i = 0; i < starPorfolioBeen.size(); i++) {
//            NiuRenListInfo.ResultBean.StrategiesBean ben = starPorfolioBeen.get(i);
//            NiuRenInfo info = new NiuRenInfo();
//            info.setNiurenName(ben.getTitle());
//            info.setId(ben.getId());
//            info.setShouyiRate(Double.parseDouble(df.format(ben.getTotleReturns())));
//            info.setStockType(ben.getDesc());
//            info.setTradeTime(ben.getFavorites() + "");
//            if (ben.getPorfolioChooseType() == 1){
//                info.setZuheType(ben.getPorfolioChooseType());
//                if (nowTime>ManagerUtil.getTime(ben.getRecuitmentStartTime())&&nowTime<ManagerUtil.getTime(ben.getRunStartDay())){
//                    info.setType(0);    //招募中
//                }else if (nowTime>ManagerUtil.getTime(ben.getRecuitmentStartTime())&&nowTime>ManagerUtil.getTime(ben.getRunStartDay())&&nowTime<ManagerUtil.getTime(ben.getRunEndDay())){
//                    info.setType(1);   //运行中
//                }else if (nowTime>ManagerUtil.getTime(ben.getRecuitmentStartTime())&&nowTime>ManagerUtil.getTime(ben.getRunStartDay())&&nowTime>ManagerUtil.getTime(ben.getRunEndDay())){
//                    info.setType(2);   //已结束
//                }
//            }else {
//                info.setZuheType(2);   //2和3全是创建
//            }
//            myInfoList.add(info);
//        }
        return myInfoList;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    /**
     * 策略组合点击事件
     *
     * @param adapterView
     * @param view
     * @param i
     * @param l
     */
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        switch (adapterView.getId()) {
            case R.id.lianghuacelue_list:
                i = i - 2;
                //CeLueInfo celueinfo = (CeLueInfo) adapterView.getAdapter().getItem(i);
                Intent inte = new Intent(getActivity(), LianghuaWebView.class);
                inte.putExtra("lianghuacelue", "lianghuacelue");
                inte.putExtra("zname", webClassEntity.getResult().getData().get(i).getName());
                inte.putExtra("zid", webClassEntity.getResult().getData().get(i).getId());
                Log.i("zid", webClassEntity.getResult().getData().get(i).getId() + "");
                inte.putExtra("zIsAlong", webClassEntity.getResult().getData().get(i).isIsAlong());
                inte.putExtra("zmax", webClassEntity.getResult().getData().get(i).getPorfolioAttribute().getLimtAmount());
                inte.putExtra("zmin", webClassEntity.getResult().getData().get(i).getPorfolioAttribute().getMinFollow());
                inte.putExtra("zIsSmsNotic", webClassEntity.getResult().getData().get(i).isIsSmsNotic());
                inte.putExtra("return", webClassEntity.getResult().getData().get(i).getPorfolioOther().getCumulativeReturn());
                Log.i("onItemClick", webClassEntity.getResult().getData().get(i).toString());
                startActivity(inte);

//                if (celueinfo.getType() == 1){   //运行中
//                    Intent intent = new Intent(getActivity(), LiangHuaCelueDetialActivity.class);
//                    intent.putExtra(LiangHuaCelueDetialActivity.LIANGHUA_ID,celueinfo.getId());
//                    //intent.putExtra(LiangHuaCelueDetialActivity.LIANGHUA_NAME,celueinfo.getTitle()+"（运行中）");
//                    startActivity(intent);
//                }else if (celueinfo.getType() == 2||celueinfo.getType() == 5){   //招募中
//                    Intent intent = new Intent(getActivity(), LianghuaCelueZhaoMuZhongActivity.class);
//                    intent.putExtra(LiangHuaCelueDetialActivity.LIANGHUA_ID,celueinfo.getId());
//                    startActivity(intent);
//                }else if (celueinfo.getType() == 3){    //已结束
//                    Intent intent = new Intent(getActivity(), LiangHuaCelueDetialActivity.class);
//                    intent.putExtra(LiangHuaCelueDetialActivity.LIANGHUA_ID,celueinfo.getId());
//                    //intent.putExtra(LiangHuaCelueDetialActivity.LIANGHUA_NAME,celueinfo.getTitle()+"（已结束）");
//                    //intent.putExtra(LiangHuaCelueDetialActivity.LIANGHUA_STATUS,celueinfo.getEndStatus());
//                    intent.putExtra(LiangHuaCelueDetialActivity.TYPE,4);
//                    startActivity(intent);
//                }else {
//                    Toast.makeText(getActivity(),"策略状态出错",Toast.LENGTH_SHORT).show();
//                }
                break;
            case R.id.niuren_listview:
                i = i - 2;
                Intent intentNir = new Intent(getActivity(), LianghuaWebView.class);
                intentNir.putExtra("niurenzuhe", "niurenzuhe");
                intentNir.putExtra("Nid", Integer.parseInt(NiurenList.get(i).getId())+"");
                intentNir.putExtra("NIsSubscribe", NiurenList.get(i).isIsSubscribe());
                intentNir.putExtra("NUserNickName", NiurenList.get(i).getName());
                intentNir.putExtra("Ntype", NiurenList.get(i).getType());



                startActivity(intentNir);
                break;
            case R.id.my_zuhe_listview:
                Log.i("我的组合类型", Listdata.toString() + "q");
                if (Listdata.get(i).getType() == 0) {//跟投
                    Intent intent = new Intent(getActivity(), chaKanZuHe.class);

                    intent.putExtra("wodezuhetype", Listdata.get(i).getType());
                    intent.putExtra("wodezhuhename", Listdata.get(i).getName());
                    intent.putExtra("wodezhuheId", Listdata.get(i).getId());
                    startActivity(intent);
                } else {//2 创建 订阅
                    Log.i("创建和订阅", Listdata.get(i).toString());
                    Intent intent = new Intent(getActivity(), LianghuaWebView.class);
                    intent.putExtra("wodezuhetype", Listdata.get(i).getType());
                    intent.putExtra("wodezhuhename", Listdata.get(i).getName());
                    intent.putExtra("wodezhuheId", Listdata.get(i).getId());
                    startActivity(intent);
                }

//                NiuRenInfo info = (NiuRenInfo) adapterView.getAdapter().getItem(i);
//                Intent intent = new Intent(getActivity(), CelueDatilActivity.class);
//                intent.putExtra(CelueDatilActivity.ZUHE_ID,info.getId());
//                intent.putExtra(CELUENAME, info.getNiurenName());
//                startActivity(intent);
//                info = (NiuRenInfo) adapterView.getAdapter().getItem(i);
//                if (info.getZuheType() == 1){    //跟投
//                    if (info.getType() == 0){
//                        intent = new Intent(getActivity(), LianghuaCelueZhaoMuZhongActivity.class);
//                        intent.putExtra(LiangHuaCelueDetialActivity.LIANGHUA_ID,info.getId());
//                        intent.putExtra(LiangHuaCelueDetialActivity.TYPE,1);
//                        startActivity(intent);
//                    }else if (info.getType() == 1){
//                        intent = new Intent(getActivity(), LiangHuaCelueDetialActivity.class);
//                        intent.putExtra(LiangHuaCelueDetialActivity.LIANGHUA_ID,info.getId());
//                        intent.putExtra(LiangHuaCelueDetialActivity.LIANGHUA_NAME,info.getNiurenName()+"（运行中）");
//                        intent.putExtra(LiangHuaCelueDetialActivity.TYPE,2);
//                        startActivity(intent);
//                    }else if (info.getType() == 2){
//                        intent = new Intent(getActivity(), LiangHuaCelueDetialActivity.class);
//                        intent.putExtra(LiangHuaCelueDetialActivity.LIANGHUA_ID,info.getId());
//                        intent.putExtra(LiangHuaCelueDetialActivity.LIANGHUA_NAME,info.getNiurenName()+"（已结束）");
//                        intent.putExtra(LiangHuaCelueDetialActivity.TYPE,3);
//                        startActivity(intent);
//                    }
////                    intent = new Intent(getActivity(), CelueDatilActivity.class);
////                    intent.putExtra(CelueDatilActivity.ZUHE_ID,info.getId());
////                    intent.putExtra(CELUENAME, info.getNiurenName());
////                    intent.putExtra(ZUHETYPE,1);
////                    startActivity(intent);
//                }else if (info.getZuheType() == 2){   //创建
//                    intent = new Intent(getActivity(), MyZuHeDatilActivity.class);
//                    intent.putExtra(CelueDatilActivity.ZUHE_ID,info.getId());
//                    intent.putExtra(CELUENAME, info.getNiurenName());
//                    startActivity(intent);
//                }else {    //订阅
//                    intent = new Intent(getActivity(), CelueDatilActivity.class);
//                    intent.putExtra(CelueDatilActivity.ZUHE_ID,info.getId());
//                    intent.putExtra(CELUENAME, info.getNiurenName());
//                    intent.putExtra(ZUHETYPE,3);
//                    startActivity(intent);
//                }
                break;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.wisdom_info:
                Intent intent = new Intent(getActivity(), MessageActivity.class);
                startActivity(intent);
                break;
            case R.id.pop_item_img:
                intent = new Intent(getActivity(), SerchActivity.class);
//                intent.putExtra(SerchActivity.URL_SEARCH,HttpManager.FindCode_URL);
                startActivity(intent);
                break;
            case R.id.add_image_txt:
                intent = new Intent(getActivity(), SetupZuHeActivity.class);
                intent.putExtra(SetupZuHeActivity.TYPE, 0);
                startActivity(intent);
                break;
            case R.id.my_zuhe_img:
                intent = new Intent(getActivity(), NewLoginActivity.class);
                startActivity(intent);
                break;
            case R.id.loading_failed:
                mImageNoDataOne.setVisibility(View.GONE);
                getCelueInfo(page);
                break;
            case R.id.loading_failed_two:
                mImageNoDataTwo.setVisibility(View.GONE);
                getNiuRenListData(niurenPage);
                break;
            case R.id.loading_failed_three:
                mImageNoDataThree.setVisibility(View.GONE);
                getMyListData(MyZuhePage);
                break;
        }
    }

    /**
     * 策略组合停止加载和刷新
     */
    private void onLoad() {
        listView.stopRefresh();
        listView.stopLoadMore();
//        listView.setRefreshTime("刚刚");
    }

    /**
     * 牛人组合停止加载和刷新
     */
    private void onLoadNiu() {
        niurenList.stopRefresh();
        niurenList.stopLoadMore();
//        niurenList.setRefreshTime("刚刚");
    }

    /**
     * 我的组合停止加载和刷新
     */
    private void onLoadMy() {
        myList.stopRefresh();
        myList.stopLoadMore();
//        myList.setRefreshTime("刚刚");
    }



    @Override
    public void onXScrolling(View view) {

    }

    @Override
    public void onScrollStateChanged(AbsListView absListView, int i) {

    }

    @Override
    public void onScroll(AbsListView absListView, int i, int i1, int i2) {
        if (absListView.getId() == R.id.lianghuacelue_list) {
            mCurrentfirstVisibleItem = i;
            tintManager.setStatusBarTintColor(getResources().getColor(R.color.write_color));
            View firstView = absListView.getChildAt(0);
            if (null != firstView) {
                ItemRecod itemRecord = (ItemRecod) recordSp.get(i);
                if (null == itemRecord) {
                    itemRecord = new ItemRecod();
                }
                itemRecord.height = firstView.getHeight();
                itemRecord.top = firstView.getTop();
                recordSp.append(i, itemRecord);
                //设置滑动颜色渐变（0-511）
                if (getScrollY(recordSp) <= 100) {
                    //设置渐变
//                        mTitleRelat.getBackground().setAlpha(getScrollY() / 2);
//                        tintManager.setTintAlpha((float) getScrollY() / 510);
                    //不设置渐变
                    mTitle.getBackground().setAlpha(25);
                    tintManager.setTintAlpha(0.1f);

                    ManagerUtil.FlymeSetStatusBarLightMode(getActivity().getWindow(), false);
                    ManagerUtil.MIUISetStatusBarLightMode(getActivity().getWindow(), false);
                    tabs.setSelectedTextColor(getActivity().getResources().getColor(R.color.write_color));
                    mMessage.setImageResource(R.mipmap.message_white);
                    mSearch.setImageResource(R.mipmap.search_white);
                } else {       //只执行一次就好
                    mTitle.getBackground().setAlpha(220);
                    tintManager.setTintAlpha(0.85f);

                    ManagerUtil.FlymeSetStatusBarLightMode(getActivity().getWindow(), true);
                    ManagerUtil.MIUISetStatusBarLightMode(getActivity().getWindow(), true);
                    tabs.setSelectedTextColor(getActivity().getResources().getColor(android.R.color.background_dark));
                    mMessage.setImageResource(R.mipmap.message_black);
                    mSearch.setImageResource(R.mipmap.searchblack);
                }
            }
        } else if (absListView.getId() == R.id.niuren_listview) {
            mCurrentfirstVisibleItem = i;
            tintManager.setStatusBarTintColor(getResources().getColor(R.color.write_color));
            View firstView = absListView.getChildAt(0);
            if (null != firstView) {
                ItemRecod itemRecord = (ItemRecod) recordSp2.get(i);
                if (null == itemRecord) {
                    itemRecord = new ItemRecod();
                }
                itemRecord.height = firstView.getHeight();
                itemRecord.top = firstView.getTop();
                recordSp2.append(i, itemRecord);
                //设置滑动颜色渐变（0-511）
                if (getScrollY(recordSp2) <= 100) {
                    //设置渐变
//                        mTitleRelat.getBackground().setAlpha(getScrollY() / 2);
//                        tintManager.setTintAlpha((float) getScrollY() / 510);
                    //不设置渐变
                    mTitle.getBackground().setAlpha(25);
                    tintManager.setTintAlpha(0.1f);

                    ManagerUtil.FlymeSetStatusBarLightMode(getActivity().getWindow(), false);
                    ManagerUtil.MIUISetStatusBarLightMode(getActivity().getWindow(), false);
                    tabs.setSelectedTextColor(getActivity().getResources().getColor(R.color.write_color));
                    mMessage.setImageResource(R.mipmap.message_white);
                    mSearch.setImageResource(R.mipmap.search_white);
                } else {       //只执行一次就好
                    mTitle.getBackground().setAlpha(220);
                    tintManager.setTintAlpha(0.85f);

                    ManagerUtil.FlymeSetStatusBarLightMode(getActivity().getWindow(), true);
                    ManagerUtil.MIUISetStatusBarLightMode(getActivity().getWindow(), true);
                    tabs.setSelectedTextColor(getActivity().getResources().getColor(android.R.color.background_dark));
                    mMessage.setImageResource(R.mipmap.message_black);
                    mSearch.setImageResource(R.mipmap.searchblack);
                }
            }
        } else if (absListView.getId() == R.id.my_zuhe_listview) {
            mCurrentfirstVisibleItem = i;
            tintManager.setStatusBarTintColor(getResources().getColor(R.color.write_color));
            View firstView = absListView.getChildAt(0);
            if (null != firstView) {
                ItemRecod itemRecord = (ItemRecod) recordSp3.get(i);
                if (null == itemRecord) {
                    itemRecord = new ItemRecod();
                }
                itemRecord.height = firstView.getHeight();
                itemRecord.top = firstView.getTop();
                recordSp3.append(i, itemRecord);
                //设置滑动颜色渐变（0-511）
                if (getScrollY(recordSp3) <= 100) {
                    //设置渐变
//                        mTitleRelat.getBackground().setAlpha(getScrollY() / 2);
//                        tintManager.setTintAlpha((float) getScrollY() / 510);
                    //不设置渐变
                    mTitle.getBackground().setAlpha(25);
                    tintManager.setTintAlpha(0.1f);

                    ManagerUtil.FlymeSetStatusBarLightMode(getActivity().getWindow(), false);
                    ManagerUtil.MIUISetStatusBarLightMode(getActivity().getWindow(), false);
                    tabs.setSelectedTextColor(getActivity().getResources().getColor(R.color.write_color));
                    mMessage.setImageResource(R.mipmap.message_white);
                    mSearch.setImageResource(R.mipmap.search_white);
                } else {       //只执行一次就好
                    mTitle.getBackground().setAlpha(220);
                    tintManager.setTintAlpha(0.85f);

                    ManagerUtil.FlymeSetStatusBarLightMode(getActivity().getWindow(), true);
                    ManagerUtil.MIUISetStatusBarLightMode(getActivity().getWindow(), true);
                    tabs.setSelectedTextColor(getActivity().getResources().getColor(android.R.color.background_dark));
                    mMessage.setImageResource(R.mipmap.message_black);
                    mSearch.setImageResource(R.mipmap.searchblack);
                }
            }
        }
    }

    //获取偏移距离
    private int getScrollY(SparseArray recordSp) {
        int height = 0;
        for (int i = 0; i < mCurrentfirstVisibleItem; i++) {
            ItemRecod itemRecod = (ItemRecod) recordSp.get(i);
            if (itemRecod != null)
                height += itemRecod.height;
        }
        ItemRecod itemRecod = (ItemRecod) recordSp.get(mCurrentfirstVisibleItem);
        if (null == itemRecod) {
            itemRecod = new ItemRecod();
        }
        return height - itemRecod.top;
    }

    class ItemRecod {
        int height = 0;
        int top = 0;

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

//            Log.d("tag", "position---" + position + "----pooffest---" + positionOffset + "----pixel---" + positionOffsetPixels);
        }

        //当新的页面被选中时调用
        public void onPageSelected(int position) {
            switch (position) {
                case 0:
                    listView.setOnScrollListener(AutoWisdomFragment.this);
                    niurenList.setOnScrollListener(null);
                    myList.setOnScrollListener(null);
                    break;
                case 1:
                    listView.setOnScrollListener(null);
                    niurenList.setOnScrollListener(AutoWisdomFragment.this);
                    myList.setOnScrollListener(null);
                    break;
                case 2:
//                    Log.d("tag","yoken------"+mToken);
//                    if (TextUtils.isEmpty(mToken)){
//                        mLogin.setVisibility(View.VISIBLE);
//                    }else {
//                        mLogin.setVisibility(View.GONE);
//
//                    }
                    listView.setOnScrollListener(null);
                    niurenList.setOnScrollListener(null);
                    myList.setOnScrollListener(AutoWisdomFragment.this);
                    break;

            }
        }
    }

    /**
     * 获取用户信息
     */
    private class UserInfoAsyn extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... strings) {
            HashMap<String,String> map = new HashMap<>();
//            map.put("Id", "fabb3adf20ee9fcf");
//            map.put("token", mToken);
            String message = HttpManager.newInstance().getHttpIndexData(strings[0],map,HttpManager.USERINFOMY_URL);
            return message;
        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            String message = s;
            if (!TextUtils.isEmpty(message)&&!s.equals(HttpManager.FAILED)){
                Gson gson = new Gson();
                userInfo = gson.fromJson(message,UserInfo.class);   //获取用户信息
                Log.d("UserInfoAsyn", "获取用户信息"+userInfo.getResult().getId());
                if (userInfo.getHead().getStatus() == 0) {

                    Id = userInfo.getResult().getId();
                    Log.d("UserInfoAsyn", "ID"+Id);
                }
//                parseUserInfo();
            }else {
//                Toast.makeText(getContext(),"获取信息失败",Toast.LENGTH_SHORT).show();
//                mNoDataImage.setVisibility(View.VISIBLE);
            }
        }
    }


    /**
     * 解析用户数据
     */
    public void parseUserInfo(){
        if (userInfo.getHead().getStatus() == 0){

            SharedPreferences sharedPreferences = ShapePreferenceManager.getMySharedPreferences(getActivity());
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(ShapePreferenceManager.ID, userInfo.getResult().getId());
            editor.commit();

        }
    }
}
