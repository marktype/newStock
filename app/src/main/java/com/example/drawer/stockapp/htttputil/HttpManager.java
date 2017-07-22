package com.example.drawer.stockapp.htttputil;

import android.text.TextUtils;
import android.util.Log;

import com.example.drawer.stockapp.utils.MD5Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by 欢大哥 on 2016/7/8.
 * http管理类，调用接口处理回调信息
 */
public class HttpManager {
    private static HttpManager instance;
    public static final String FAILED = "failed";

    private HttpManager() {

    }

    public static HttpManager newInstance() {
        if (instance == null) {
            instance = new HttpManager();
        }
        return instance;
    }

    public static final String BASE_URL = "https://newapi.imaozhua.com/";      //基地址 /*----------------已修改-----------------*/
    //    public static final String BASE_URL = "http://test.api.matrix.lab.supwin.com:9500/";
    public static final String UID = "edd5c089ed57fa9f";
    public static final String LANG = "zh-cn";
    public static final Long TimeStamp = System.currentTimeMillis();

    //用户id fabb3adf20ee9fcf

    public static final String MD5_UID = "ClientId=edd5c089ed57fa9fClientKey=SupwinTimeStamp=" + String.valueOf(TimeStamp);
    public static final String Sign = MD5Utils.getMd5Value(MD5_UID);

    public static final MediaType JSON = MediaType.parse("application/json;charset=utf-8");   //数据传输类型
    public static final String TEST_URL = BASE_URL + "quant/ProductList";      //测试地址
    public static final String SHARE_LIST_URL = BASE_URL + "Dynamics/StartShareList";      //牛人分享列表
    public static final String COMMENT_LIST_URL = BASE_URL + "Information/CommentsList ";      //评论列表 /*----------------已修改-----------------*/
    public static final String DeleteDynamic_URL = BASE_URL + "Dynamics/DeleteDynamic";      //删除自己的动态
    public static final String DynamicDetail_URL = BASE_URL + "Dynamics/DynamicDetail";      //获取动态详情   /*----------------已修改-----------------*/
    public static final String StatementDetail_URL = BASE_URL + "Financial/StatementDetail";      //


    public static final String NewsDetail_URL = BASE_URL + "Information/InformationDetail";      //  资讯详情 /*----------------已修改-----------------*/
    public static final String NewsList_URL = BASE_URL + "Information/InformationList";      //  资讯列表  /*----------------已修改-----------------*/
    public static final String FAVORITES_List_URL = BASE_URL + "Information/FavoritesList";      //  资讯列表  /*----------------已修改-----------------*/
    public static final String Information_URL = BASE_URL + "HeadLine/Information";      //  一级界面资讯
    //    public static final String NewsDetail_URL = BASE_URL + "HeadLine/NewsDetail";      //  新闻详情
    public static final String News_Comment_URL = BASE_URL + "HeadLine/Comment";      //  新闻评论或转发
    public static final String CommentsList_URL = BASE_URL + "HeadLine/CommentsList";      //  评论列表
    public static final String MarketData_URL = BASE_URL + "Intelligent/IndexData";      //  指数信息 /*----------------已修改-----------------*/
    public static final String BannerList_URL = BASE_URL + "Information/InformationList";      //  获取banner图片  /*----------------已修改-----------------*/
    public static final String StrategyList_URL = BASE_URL + "Intelligent/StrategyList";      //  获取策略列表
    public static final String StarPorfolio_URL = BASE_URL + "Intelligent/StarPorfolio";      //  获取牛人组合列表
    public static final String MyPorfolio_URL = BASE_URL + "Intelligent/MyPorfolio";      //  获取我的组合列表
    public static final String AMZPortfolioList_URl = BASE_URL + "Intelligent/AMZPortfolioList";      //  获取组合列表/*---新---获取组合列表--------*/
    public static final String MyCollectPorfolio_URL = BASE_URL + "Intelligent/MyCollectPorfolio";      //  获取我的订阅、收藏列表
    public static final String StrategyDetail_URL = BASE_URL + "Intelligent/StrategyDetail";      // 策略详情
    public static final String AlongRecords_URL = BASE_URL + "Intelligent/AlongRecords";      // 跟投记录
    public static final String StrategyBefor_URL = BASE_URL + "Intelligent/StrategyBefor";      // 量化策略支付前信息
    public static final String CollectStrategy_URL = BASE_URL + "Intelligent/CollectStrategy";      // 收藏、订阅策略组合
    public static final String PayStrategy_URL = BASE_URL + "Intelligent/PayStrategy";      // 跟投策略
    public static final String CancelAlong_URL = BASE_URL + "Intelligent/CancelAlong";      // 取消跟头
    public static final String StarPorfolioDetail_URL = BASE_URL + "Intelligent/StarPorfolioDetail";      // 牛人组合详情
    public static final String PayPorfolio_URL = BASE_URL + "Intelligent/PayPorfolio";      // 支付组合
    public static final String CodeList_URL = BASE_URL + "Intelligent/CodeList";      // 股票名称列表
    public static final String ChangePosition_URL = BASE_URL + "Intelligent/ChangePosition";      // 调仓
    public static final String ShortStrategiesList_URL = BASE_URL + "Intelligent/ShortStrategiesList";      // 策略名称列表
    public static final String CreatePorfolio_URL = BASE_URL + "Intelligent/CreatePorfolio";      // 创建组合
    public static final String DeletePorfolio_URL = BASE_URL + "Intelligent/DeletePorfolio";      // 删除组合
    public static final String CodeHotKeys_URL = BASE_URL + "Intelligent/HotKeys";      // 热门搜索股票关键字
    public static final String FindCode_URL = BASE_URL + "Intelligent/FindCode";      // 搜索股票
    public static final String LastTrades_URL = BASE_URL + "Intelligent/LastTransferPosition";      // 最后一次调仓 /*------xiugai-----*/
    public static final String Signals_URL = BASE_URL + "Intelligent/Signals";      // 获取调仓历史记录
    public static final String send_dynamic_URL = BASE_URL + "Information/AddInformation";      // 发布动态 /*---------------已修改-----------------*/
    public static final String Comment_URL = BASE_URL + "Dynamics/Comment";      // 评论或转发     /*----------------/Information/AddComment---------*/
    public static final String Favorites_URL = BASE_URL + "Information/AddComment";      // 收藏4,点赞3,评论0,转发1, /*----------------已修改---------*/
    public static final String Login_URL = BASE_URL + "Membership/Login";      // 登录 /*----------------已修改-----------------*/
    public static final String RegistorLogin_URL = BASE_URL +"Membership/RegisterOrLogin";      // 通过验证码，注册或登录 /*----------------已修改-----------------*/
    public static final String USERINFOMY_URL = BASE_URL + "Membership/MyUserDetail";      // 获取我的用户信息 /*----------------已修改-----------------*/
    public static final String USERINFO_URL = BASE_URL + "Membership/UserDetail";      // 获取用户信息 /*----------------已修改-----------------*/
    public static final String USER_CONCERNS_URL = BASE_URL + "Membership/UserConcernsOption";      // 用户关注动作 /*----------------已修改-----------------*/
    public static final String USER_FANS_LIST_URL = BASE_URL + "Membership/FansList";      // 粉丝列表 /*----------------已修改-----------------*/
    public static final String USER_CONCERNS_LIST_URL = BASE_URL + "/Membership/ConcernsList";      // 关注列表 /*----------------已修改-----------------*/
    public static final String AddFeedBack_URL = BASE_URL + "FeedBack/AddFeedBack";      // 用户反馈  /*----------------没变-----------------*/
    public static final String Version_URL = BASE_URL + "Membership/Version";      // 版本
    public static final String UpdateCustomerPassword_URL = BASE_URL + "Membership/UpdatePassword";      // 修改密码  /*----------------已修改-----------------*/
    public static final String RegisterCode_URL = BASE_URL + "Membership/RegisterCode";      // 注册验证码    /*----------------已修改-----------------*/
    public static final String UpdateAvatar_URL = BASE_URL + "Membership/UpdateAvatar";      // 修改用户头像
    public static final String UpdataUser_URL = BASE_URL + "Membership/UpdateUserInfo";      // 修改用户信息   /*----------------正在修改-----------------*/
    public static final String ReSetPasswordCode_URL = BASE_URL + "Membership/ReSetPasswordCode";      // 重置密码验证码 /*----------------已修改--------------
    public static final String LoginByPhoneCode_URL = BASE_URL + "Membership/RegisterOrLoginCode ";      // 获取验证码（短信登录时） /*----------------已修改--------------
    public static final String Register_URL = BASE_URL + "Membership/Register";      // 注册
    public static final String ReSetPassword_URL = BASE_URL + "Membership/ReSetPassword";      // 重置密码(忘记密码) /*----------------已修改--------------
    public static final String Information_other_URL = BASE_URL + "Other/Information";      // 一级页面
    public static final String HotKeys_URL = BASE_URL + "Other/HotKeys";      // 热门搜索关键字
    public static final String Find_URL = BASE_URL + "Other/Find";      // 搜索股票
    public static final String ProductList_URL = BASE_URL + "Quant/ProductList";      // 产品列表
    public static final String ProductStart_URL = BASE_URL + "Quant/ProductStart";      // 开启产品
    public static final String ProductStop_URL = BASE_URL + "Quant/ProductStop";      // 关闭产品
    public static final String ProductDeposit_URL = BASE_URL + "Quant/ProductDeposit";      // 转入资金到产品
    public static final String ProductWithdraw_URL = BASE_URL + "Quant/ProductWithdraw";      // 从产品转出资金
    public static final String PorfoliosList_URL = BASE_URL + "Quant/PorfoliosList";      // 组合列表
    public static final String PorfoliosView_URL = BASE_URL + "Quant/PorfoliosView";      //
    public static final String SystemsView_URL = BASE_URL + "Quant/SystemsView";      //
    public static final String PorfolioCharts_URL = BASE_URL + "Quant/PorfolioCharts";      //报表图表
    public static final String PorfolioDetails_URL = BASE_URL + "Quant/PorfolioDetails";      //产品详情
    public static final String HoldingList_URL = BASE_URL + "Quant/HoldingList";      //持仓列表
    public static final String StrategyList_Quant_URL = BASE_URL + "Quant/StrategyList";      //策略列表
    public static final String ArticleList_URL = BASE_URL + "QuantCollege/ArticleList";      //文章列表
    public static final String ArticleDetails_URL = BASE_URL + "QuantCollege/ArticleDetails";      //获取文章详细
    public static final String Information_School_URL = BASE_URL + "School/Information";      //一级界面资讯
    public static final String CourseInfoList_URL = BASE_URL + "Information/InformationList";      //学堂列表 /*----------------已修改-----------------*/
//    public static final String CourseInfoList_URL = BASE_URL + "School/CourseInfoList";      //学堂列表
    public static final String My_School_URL = BASE_URL + "School/MyInformation";      //我的课堂列表
    public static final String CourseCommentsInfo_URL = BASE_URL + "School/CourseCommentsInfo";      //评论转发列表
    public static final String CourseComment_URL = BASE_URL + "School/CourseComment";      //评论/转发
    public static final String CourseFavorites_URL = BASE_URL + "School/CourseFavorites";      //课堂收藏、点赞
    public static final String ExceCollList_URL = BASE_URL + "School/ExceCollList";      //课堂合集列表
    public static final String ExceCollDetail_URL = BASE_URL + "School/ExceCollDetail";      //课堂合集列表详情
    public static final String ExceCollCommInfo_URL = BASE_URL + "School/ExceCollCommInfo";      //评论转发列表
    public static final String ExceCollComment_URL = BASE_URL + "School/ExceCollComment";      //合集进行评论转发
    public static final String ExceCollFavorites_URL = BASE_URL + "School/ExceCollFavorites";      //课堂收藏
    public static final String CourseDetail_URL = BASE_URL + "Information/InformationDetail";      //课堂详情 /*----------------已修改-----------------*/
    public static final String LiJiGenTou = BASE_URL + "Intelligent/AMZPortfolioOption";      // 2017.3.16 立即跟投
    public static final String UpDateZuHeMe = BASE_URL + "Intelligent/UpdateAlongPortfolio";      // 2017.3.16 更新跟投
    public static final String xiaDan = BASE_URL + "Intelligent/AMZPortfolioOption";      // 2017.3.16 下单
    public static final String search = BASE_URL + "Intelligent/MarketData";      // 2017.3.16 下单
    public static final String queRenChuangJian=BASE_URL+"Intelligent/AddAMZStarPorfolio";


    public static final String ExceCollSIGN_URL = BASE_URL + "Credit/AddChecking";      //课堂签到 /*----------------新加-----------------*/
    public static final String ExceCollSIGN_USER_INFO_URL = BASE_URL + "Credit/UserCheckingInfo";      //用户签到的信息 /*----------------新加-----------------*/
    public static final String ExceCollSIGN_INFORMATION_URL = BASE_URL + "Information/ExcellentDetailList";      //学堂列表二级页面 /*----------------新加-----------------*/

    public static final String chiCang= BASE_URL+"Intelligent/GetHolding"; //2017.3.28




    public String search(String token,String key) {
        Boolean flag = true;
        if (TextUtils.isEmpty(token)) {   //token为null请求时会报异常
            token = "";
        }
        JSONObject kk = new JSONObject();
        try {


            JSONObject object = new JSONObject();
            object.put("Uid", UID);
            object.put("Lang", LANG);
            object.put("Token", token);
            object.put("Sign", Sign);
            object.put("TimeStamp", TimeStamp);

            kk.put("Head", object);

            JSONObject object2 = new JSONObject();
            object2.put("Key", key);
            object2.put("Type",0);
            object2.put("Codes",(new ArrayList<String>()).add("q"));
            kk.put("Param",object2);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        String str = kk.toString();
        Log.d("tag", "str-----" + str);
        RequestBody formBody = RequestBody.create(JSON, str);
        OkHttpClient mOkHttpClient = new OkHttpClient();
//        mOkHttpClient.newBuilder().connectTimeout(10000, TimeUnit.MILLISECONDS);      //设置链接超时
        Request request = new Request.Builder()
                .url(search)
                .post(formBody)
                .build();

        //发送请求获取响应
        Response response = null;
        try {
            //请求加入调度
            response = mOkHttpClient.newCall(request).execute();
            //判断请求是否成功
            if (response.isSuccessful()) {
                //打印服务端返回结果
                String info = response.body().string();
//                Log.d("tag", "getHttpData: 1111111--" + url);
                Log.d("tag", "getHttpData: 2222222--" + info);
                return info;
            } else {
                Log.d("tag", "body-code--" + response.code() + "--string ---" + response.message());
                return FAILED;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }




    /**
     * 只用传url
     *
     * @param url
     * @return
     */
    public String getHttpData(String url) {
        RequestBody formBody = RequestBody.create(JSON, "");
        OkHttpClient mOkHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();
//        Call call = mOkHttpClient.newCall(request);
        //发送请求获取响应
        Response response = null;
        try {
            //请求加入调度
            response = mOkHttpClient.newCall(request).execute();
            //判断请求是否成功
            if (response.isSuccessful()) {
                //打印服务端返回结果
                String info = response.body().string();
                Log.d("tag", "getHttpData: 1111111--" + url);
                Log.d("tag", "getHttpData: 2222222--" + info);
                return info;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";

    }


    public String getHttpAddDynamic(String token, HashMap<String, Object> map, String url,String content) {
        Boolean flag = true;
        if (TextUtils.isEmpty(token)) {   //token为null请求时会报异常
            token = "";
        }
        JSONObject kk = new JSONObject();
        try {


            JSONObject object = new JSONObject();
            object.put("Uid", UID);
            object.put("Lang", LANG);
            object.put("Token", token);
            object.put("Sign", Sign);
            object.put("TimeStamp", TimeStamp);

            kk.put("Head", object);

            JSONObject object2 = new JSONObject();
            if (map != null) {
                Iterator iter = map.entrySet().iterator();
                while (iter.hasNext()) {
                    Map.Entry entry = (Map.Entry) iter.next();
                    if (entry.getKey().equals("Imgs")) {
                        JSONArray object3 = new JSONArray();
                        ArrayList<String> mapinfo = (ArrayList<String>) map.get("Imgs");
                        for (int i = 0; i < mapinfo.size(); i++) {
                            object3.put(i, mapinfo.get(i));
                        }
                        object2.put("Imgs", object3);
                    } else {
                        String key = (String) entry.getKey();
                        String val = (String) entry.getValue();
                        object2.put(key, val);
                    }
                }
            }


            object2.put("Content", content);
            kk.put("Param",object2);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        String str = kk.toString();
        Log.d("tag", "str-----" + str);
        RequestBody formBody = RequestBody.create(JSON, str);
        OkHttpClient mOkHttpClient = new OkHttpClient();
//        mOkHttpClient.newBuilder().connectTimeout(10000, TimeUnit.MILLISECONDS);      //设置链接超时
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();

        //发送请求获取响应
        Response response = null;
        try {
            //请求加入调度
            response = mOkHttpClient.newCall(request).execute();
            //判断请求是否成功
            if (response.isSuccessful()) {
                //打印服务端返回结果
                String info = response.body().string();
                Log.d("tag", "getHttpData: 1111111--" + url);
                Log.d("tag", "getHttpData: 2222222--" + info);
                return info;
            } else {
                Log.d("tag", "body-code--" + response.code() + "--string ---" + response.message());
                return FAILED;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }


    /**
     * 获取指数
     * @param url
     * @return
     */
    public String getHttpIndexData(String token, HashMap<String, String> map, String url) {
        Boolean flag = true;
        if (TextUtils.isEmpty(token)) {   //token为null请求时会报异常
            token = "";
        }
        JSONObject kk = new JSONObject();
        try {


            JSONObject object = new JSONObject();
            object.put("Uid", UID);
            object.put("Lang", LANG);
            object.put("Token", token);
            object.put("Sign", Sign);
            object.put("TimeStamp", TimeStamp);

            kk.put("Head", object);

            JSONObject object2 = new JSONObject();
            if (map != null) {
                Iterator iter = map.entrySet().iterator();
                while (iter.hasNext()) {
                    Map.Entry entry = (Map.Entry) iter.next();
                    String key = (String) entry.getKey();
                    String val = (String) entry.getValue();
                    object2.put(key, val);
                    if (key.equals("Param")){
                        kk.put("Param", val);
                        flag = false;
                    }
                }
            }else {
                kk.put("Param", "");

            }
            if (flag){
                kk.put("Param", object2);
            }




        } catch (JSONException e) {
            e.printStackTrace();
        }
        String str = kk.toString();
        Log.d("tag", "str-----" + str);
        RequestBody formBody = RequestBody.create(JSON, str);
        OkHttpClient mOkHttpClient = new OkHttpClient();
//        mOkHttpClient.newBuilder().connectTimeout(10000, TimeUnit.MILLISECONDS);      //设置链接超时
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();

        //发送请求获取响应
        Response response = null;
        try {
            //请求加入调度
            response = mOkHttpClient.newCall(request).execute();
            //判断请求是否成功
            if (response.isSuccessful()) {
                //打印服务端返回结果
                String info = response.body().string();
                Log.d("tag", "getHttpData: 1111111--" + url);
                Log.d("tag", "getHttpData: 2222222--" + info);
                Log.d("登陆返回数据", "." + info);

                return info;
            } else {
                Log.d("tag", "body-code--" + response.code() + "--string ---" + response.message());
                return FAILED;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }

    /**
     * 处理接口信息post请求(数据格式json传输
     * {"Head": {
     * "Uid": "string",
     * "Lang": "string",
     * "Token": "string"
     * },
     * "Param": {
     * "PageIndex": 0,
     * "PageCount": 0,
     * "PageSize": 0
     * }
     * })
     * 调用
     * HashMap<String,String> map = new HashMap<>();
     * map.put("PageIndex", "0");
     * map.put("PageCount", "0");
     * map.put("PageSize", "0");
     * HttpManager.getHttpDataByTwoLayer("",map,HttpManager.COMMENT_LIST_URL);
     *
     * @return token    用户token（区别用户）
     * map     传参所用的键值对
     * url     请求地址
     */

    /**
     * @param token
     * @param map
     * @param url
     * @return
     */
    public String getHttpDataByTwoLayer(String token, HashMap<String, String> map, String url) {
        Boolean flag = true;      //判断是否只有param一个参数
        if (TextUtils.isEmpty(token)) {   //token为null请求时会报异常
            token = "";
        }
        JSONObject kk = new JSONObject();
        try {
            JSONObject object = new JSONObject();
            object.put("Uid", UID);
            object.put("Lang", LANG);
            object.put("Token", token);
            kk.put("Head", object);
            JSONObject object2 = new JSONObject();
            if (map != null) {
                Iterator iter = map.entrySet().iterator();
                while (iter.hasNext()) {
                    Map.Entry entry = (Map.Entry) iter.next();
                    String key = (String) entry.getKey();
                    String val = (String) entry.getValue();
                    object2.put(key, val);
                    if (key.equals("Param")){
                        kk.put("Param", val);
                        flag = false;
                    }
                }
            }
            if (flag){
                kk.put("Param", object2);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        String str = kk.toString();
        Log.d("tag","str-----"+str);
        RequestBody formBody = RequestBody.create(JSON, str);
        OkHttpClient mOkHttpClient = new OkHttpClient();
//        mOkHttpClient.newBuilder().connectTimeout(10000, TimeUnit.MILLISECONDS);      //设置链接超时
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();

        //发送请求获取响应
        Response response = null;
        try {
            //请求加入调度
            response = mOkHttpClient.newCall(request).execute();
            //判断请求是否成功
            if (response.isSuccessful()) {
                //打印服务端返回结果
                String info = response.body().string();
                Log.d("tag", "getHttpData: 1111111--" + url);
                Log.d("tag", "getHttpData: 2222222--" + info);
                return info;
            } else {
                Log.d("tag", "body-code--" + response.code() + "--string ---" + response.message());
                return FAILED;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }

    /**
     * {
     * "Head": {
     * "Uid": "string",
     * "Lang": "string",
     * "Token": "string"
     * },
     * "Param": {
     * "PageInfo": {
     * "PageIndex": 0,
     * "PageCount": 0,
     * "PageSize": 0
     * },
     * "Id": 0,
     * "Type": 0
     * }
     * }
     * 调用 HashMap<String,Object> hashMap = new HashMap<>();
     * HashMap<String,String> map = new HashMap<>();
     * map.put("PageIndex", "0");
     * map.put("PageCount", "0");
     * map.put("PageSize", "0");
     * hashMap.put("PageInfo",map);
     * hashMap.put("Id","0");
     * hashMap.put("Type","0");
     * HttpManager.getHttpDataByThreeLayer("",hashMap,HttpManager.COMMENT_LIST_URL);
     *
     * @param token
     * @param map
     * @param url
     * @return
     */
    public String getHttpDataByThreeLayer(String token, HashMap<String, Object> map, String url) {
        if (TextUtils.isEmpty(token)) {   //token为null请求时会报异常
            token = "";
        }
        JSONObject kk = new JSONObject();
        try {
            JSONObject object = new JSONObject();


            object.put("Uid", UID);
            object.put("Lang", LANG);
            object.put("Token", token);
            object.put("Sign", Sign);
            object.put("TimeStamp", TimeStamp);


            kk.put("Head", object);
            JSONObject object2 = new JSONObject();
            if (map != null) {
                Iterator iter = map.entrySet().iterator();
                while (iter.hasNext()) {
                    Map.Entry entry = (Map.Entry) iter.next();
                    if (entry.getKey().equals("PageInfo")) {
                        JSONObject object3 = new JSONObject();
                        HashMap<String, String> mapinfo = (HashMap<String, String>) map.get("PageInfo");
                        Iterator iterItem = mapinfo.entrySet().iterator();
                        while (iterItem.hasNext()) {
                            Map.Entry entryThree = (Map.Entry) iterItem.next();
                            String key = (String) entryThree.getKey();
                            String val = (String) entryThree.getValue();
                            object3.put(key, val);
                        }
                        object2.put("PageInfo", object3);
                    } else {
                        String key = (String) entry.getKey();
                        String val = (String) entry.getValue();
                        object2.put(key, val);
                    }
                }
            }
            kk.put("Param", object2);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        String str = kk.toString();

        Log.d("tag", "str---------" + str);
        RequestBody formBody = RequestBody.create(JSON, str);
        OkHttpClient mOkHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();

        //发送请求获取响应(同步请求)
        Response response = null;
        try {
            //请求加入调度
            response = mOkHttpClient.newCall(request).execute();
            //判断请求是否成功
            if (response.isSuccessful()) {
                //打印服务端返回结果
                String info = response.body().string();
                Log.d("tag", "getHttpData: 1111111--" + url);
                Log.d("tag", "getHttpData: 2222222--" + info);
                return info;
            } else {
                Log.d("tag", "body-code--" + response.code() + "--string ---" + response.message());
                return FAILED;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        /**
         * 异步请求
         */
//        Call call = mOkHttpClient.newCall(request);
//        //请求加入调度
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                Log.d("tag", "onFailure: ---" + e.toString());
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                String info = response.body().string();
//                Log.d("tag", "getHttpData: 2222222--"+info);
//                setHttpInfo(info);
//            }
//        });
        return "";
    }

    /**
     * {
     * "Head": {
     * "Uid": "string",
     * "Lang": "string",
     * "Token": "string"
     * },
     * "Param": {
     * "Id": 0,
     * "Name": "string",
     * "MarketData": [
     * "string"
     * ],
     * "Amount": 0,
     * "TargetReturn": 0
     * }
     * }
     * 调用 HashMap<String,Object> hashMap = new HashMap<>();
     * ArrayList<String> map = new Arraylist<>();
     * map.put("MarketData");
     * hashMap.put("MarketData",map);
     * hashMap.put("Id","0");
     * hashMap.put("Type","0");
     * HttpManager.getHttpDataByThreeLayer("",hashMap,HttpManager.COMMENT_LIST_URL);
     *
     * @param token
     * @param map
     * @param url
     * @return
     */
    public String getHttpDataByThreeLayerArray(String token, HashMap<String, Object> map, String url) {
        if (TextUtils.isEmpty(token)) {   //token为null请求时会报异常
            token = "";
        }
        JSONObject kk = new JSONObject();
        try {
            JSONObject object = new JSONObject();
            object.put("Uid", UID);
            object.put("Lang", LANG);
            object.put("Token", token);
            kk.put("Head", object);
            JSONObject object2 = new JSONObject();
            if (map != null) {
                Iterator iter = map.entrySet().iterator();
                while (iter.hasNext()) {
                    Map.Entry entry = (Map.Entry) iter.next();
                    if (entry.getKey().equals("Imgs")) {
                        JSONArray object3 = new JSONArray();
                        ArrayList<String> mapinfo = (ArrayList<String>) map.get("Imgs");
                        for (int i = 0; i < mapinfo.size(); i++) {
                            object3.put(i, mapinfo.get(i));
                        }
                        object2.put("Imgs", object3);
                    } else {
                        String key = (String) entry.getKey();
                        String val = (String) entry.getValue();
                        object2.put(key, val);
                    }
                }
            }
            kk.put("Param", object2);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        String str = kk.toString();
        RequestBody formBody = RequestBody.create(JSON, str);
        OkHttpClient mOkHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();

        //发送请求获取响应(同步请求)
        Response response = null;
        try {
            //请求加入调度
            response = mOkHttpClient.newCall(request).execute();
            //判断请求是否成功
            if (response.isSuccessful()) {
                //打印服务端返回结果
                String info = response.body().string();
                Log.d("tag", "getHttpData: 1111111--" + url);
                Log.d("tag", "getHttpData: 2222222--" + info);
                return info;
            } else {
                Log.d("tag", "body-code--" + response.code() + "--string ---" + response.message());
                return FAILED;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }

    /**
     * {
     * "Head": {
     * "Uid": "string",
     * "Lang": "string",
     * "Token": "string"
     * },
     * "Param": {
     * "Name": "string",
     * "CodeList": [
     * {
     * "Code": "string",
     * "Price": 0,
     * "Name": "string",
     * "Volume": 0,
     * "TradeTime": "2016-08-29T03:10:22.016Z",
     * "TradeType": "Open"
     * }
     * ],
     * "Amount": 0,
     * "TargetReturn": 0,
     * "Desc": "string"
     * }
     * }
     *
     * @param token
     * @param map
     * @param url
     * @return
     */
    public String getHttpDataByThreeLayerArrayObject(String token, HashMap<String, Object> map, String url) {
        if (TextUtils.isEmpty(token)) {   //token为null请求时会报异常
            token = "";
        }
        JSONObject kk = new JSONObject();
        try {
            JSONObject object = new JSONObject();
            object.put("Uid", UID);
            object.put("Lang", LANG);
            object.put("Token", token);
            kk.put("Head", object);
            JSONObject object2 = new JSONObject();
            if (map != null) {
                Iterator iter = map.entrySet().iterator();
                while (iter.hasNext()) {
                    Map.Entry entry = (Map.Entry) iter.next();
                    if (entry.getKey().equals("CodeList")) {
                        JSONArray object3 = new JSONArray();

                        ArrayList<HashMap<String, String>> mapinfo = (ArrayList<HashMap<String, String>>) map.get("CodeList");
                        for (int i = 0; i < mapinfo.size(); i++) {
                            Iterator itermap = mapinfo.get(i).entrySet().iterator();
                            JSONObject json = new JSONObject();
                            while (itermap.hasNext()) {
                                Map.Entry entryThree = (Map.Entry) itermap.next();
                                String key = (String) entryThree.getKey();
                                String val = (String) entryThree.getValue();
                                json.put(key, val);
                            }
                            object3.put(i, json);
                        }
                        object2.put("CodeList", object3);
                    } else if (entry.getKey().equals("Codes")) {
                        JSONArray object3 = new JSONArray();

                        ArrayList<HashMap<String, String>> mapinfo = (ArrayList<HashMap<String, String>>) map.get("Codes");
                        for (int i = 0; i < mapinfo.size(); i++) {
                            Iterator itermap = mapinfo.get(i).entrySet().iterator();
                            JSONObject json = new JSONObject();
                            while (itermap.hasNext()) {
                                Map.Entry entryThree = (Map.Entry) itermap.next();
                                String key = (String) entryThree.getKey();
                                String val = (String) entryThree.getValue();
                                json.put(key, val);
                            }
                            object3.put(i, json);
                        }
                        object2.put("Codes", object3);
                    } else {
                        String key = (String) entry.getKey();
                        String val = (String) entry.getValue();
                        object2.put(key, val);
                    }
                }
            }
            kk.put("Param", object2);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        String str = kk.toString();

        Log.d("tag", "str---调仓--" + str);
        RequestBody formBody = RequestBody.create(JSON, str);
        OkHttpClient mOkHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();

        //发送请求获取响应(同步请求)
        Response response = null;
        try {
            //请求加入调度
            response = mOkHttpClient.newCall(request).execute();
            //判断请求是否成功
            if (response.isSuccessful()) {
                //打印服务端返回结果
                String info = response.body().string();
                Log.d("tag", "getHttpData: 1111111--" + url);
                Log.d("tag", "getHttpData: 2222222--" + info);
                return info;
            } else {
                Log.d("tag", "body-code--Three" + response.code() + "--string ---" + response.message());
                return FAILED;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }


    /**
     * {
     * "Head": {
     * "Uid": "string",
     * "Lang": "string",
     * "Token": "string"
     * },
     * "Param": {
     * "PageInfo": {
     * "PageIndex": 0,
     * "PageCount": 0,
     * "PageSize": 0
     * },
     * "Id": "string",
     * "Type": "string"
     * }
     * }
     *
     * @param token
     * @param map
     * @param url
     * @param
     * @return
     */

    public String getHttpDataByFourLayer(String token, HashMap<String, String> map, String url, int RequestType) {
        Boolean flag = true;      //判断是否只有param一个参数
        if (TextUtils.isEmpty(token)) {   //token为null请求时会报异常
            token = "";
        }
        JSONObject kk = new JSONObject();
        try {


            JSONObject object = new JSONObject();
            object.put("Uid", UID);
            object.put("Lang", LANG);
            object.put("Token", token);
            object.put("Sign", Sign);
            object.put("TimeStamp", TimeStamp);


            kk.put("Head", object);
            JSONObject object2 = new JSONObject();
            if (map != null) {
                Iterator iter = map.entrySet().iterator();
                while (iter.hasNext()) {
                    Map.Entry entry = (Map.Entry) iter.next();
                    String key = (String) entry.getKey();
                    String val = (String) entry.getValue();
                    object2.put(key, val);
                    if (key.equals("PageInfo")) {
                        kk.put("PageInfo", val);
                        flag = false;
                    }
                }
            }
            JSONObject jsonObjectParam = new JSONObject();
            jsonObjectParam.put("PageInfo", object2);
            jsonObjectParam.put("Id", "");
            switch (RequestType) {
                case RequestTypeConstant.REQUEST_TYPE_INFORMAION_NEWS_LIST://获取新闻列表
                    if (flag) {
                        jsonObjectParam.put("Type", 0);
                    }
                    break;
                case RequestTypeConstant.REQUEST_TYPE_INFORMATION_DYNAMIC_LIST://获取动态列表
                    if (flag) {
                        jsonObjectParam.put("Type", 1);
                    }
                    break;
                case 2://获取banner图
                    if (flag) {
                        jsonObjectParam.put("Type", 2);

                    }
                case RequestTypeConstant.REQUEST_TYPE_INFORMAITON_COLECTION://学堂列表
                    if (flag) {
                        jsonObjectParam.put("Type", 8);

                    }

                    break;


            }

            kk.put("Param", jsonObjectParam);
            Log.i("banner...请求体",jsonObjectParam.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        String str = kk.toString();
        Log.d("tag", "str-----" + str);
        RequestBody formBody = RequestBody.create(JSON, str);
        OkHttpClient mOkHttpClient = new OkHttpClient();
//        mOkHttpClient.newBuilder().connectTimeout(10000, TimeUnit.MILLISECONDS);      //设置链接超时
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();

        //发送请求获取响应
        Response response = null;
        try {
            //请求加入调度
            response = mOkHttpClient.newCall(request).execute();
            //判断请求是否成功
            if (response.isSuccessful()) {
                //打印服务端返回结果
                String info = response.body().string();
                Log.d("tag", "getHttpData: 1111111--" + url);
                Log.d("tag", "getHttpData: 2222222--" + info);
                return info;
            } else {
                Log.d("tag", "body-code--four" + response.code() + "--string ---" + response.message());
                return FAILED;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }


    public String getHttpDataByFiveLayer(String token, HashMap<String, String> map, String url, String id) {
        Boolean flag = true;      //判断是否只有param一个参数
        if (TextUtils.isEmpty(token)) {   //token为null请求时会报异常
            token = "";
        }
        JSONObject kk = new JSONObject();
        try {


            JSONObject object = new JSONObject();
            object.put("Uid", UID);
            object.put("Lang", LANG);
            object.put("Token", token);
            object.put("Sign", Sign);
            object.put("TimeStamp", TimeStamp);


            kk.put("Head", object);
            JSONObject object2 = new JSONObject();
            if (map != null) {
                Iterator iter = map.entrySet().iterator();
                while (iter.hasNext()) {
                    Map.Entry entry = (Map.Entry) iter.next();
                    String key = (String) entry.getKey();
                    String val = (String) entry.getValue();
                    object2.put(key, val);
                    if (key.equals("PageInfo")) {
                        kk.put("PageInfo", val);
                        flag = false;
                    }
                }
            }
            JSONObject jsonObjectParam = new JSONObject();
            jsonObjectParam.put("PageInfo", object2);
            jsonObjectParam.put("Id", id);

            Log.i("fsdafsfasfs",kk.toString());
            kk.put("Param", jsonObjectParam);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String str = kk.toString();
        Log.d("tag", "str-----" + str);
        RequestBody formBody = RequestBody.create(JSON, str);
        OkHttpClient mOkHttpClient = new OkHttpClient();
//        mOkHttpClient.newBuilder().connectTimeout(10000, TimeUnit.MILLISECONDS);      //设置链接超时
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();

        //发送请求获取响应
        Response response = null;
        try {
            //请求加入调度
            response = mOkHttpClient.newCall(request).execute();
            //判断请求是否成功
            if (response.isSuccessful()) {
                //打印服务端返回结果
                String info = response.body().string();
                Log.d("tag", "getHttpData: 1111111--" + url);
                Log.d("tag", "getHttpData: 2222222--" + info);
                return info;
            } else {
                Log.d("tag", "body-code--five" + response.code() + "--string ---" + response.message());
                return FAILED;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }









    public String getHttpDataByBanner(String token, HashMap<String, String> map, String url, int RequestType) {
        Boolean flag = true;      //判断是否只有param一个参数
        if (TextUtils.isEmpty(token)) {   //token为null请求时会报异常
            token = "";
        }
        JSONObject kk = new JSONObject();
        try {
            JSONObject object = new JSONObject();
            object.put("Uid", UID);
            object.put("Lang", LANG);
            object.put("Token", token);
            object.put("Sign", Sign);
            object.put("TimeStamp", TimeStamp);


            kk.put("Head", object);
            JSONObject object2 = new JSONObject();
            if (map != null) {
                Iterator iter = map.entrySet().iterator();
                while (iter.hasNext()) {
                    Map.Entry entry = (Map.Entry) iter.next();
                    String key = (String) entry.getKey();
                    String val = (String) entry.getValue();
                    object2.put(key, val);
                    if (key.equals("PageInfo")) {
                        kk.put("PageInfo", val);
                        flag = false;
                    }
                }
            }
            JSONObject jsonObjectParam = new JSONObject();
            jsonObjectParam.put("PageInfo", object2);
            jsonObjectParam.put("Id", "");
            switch (RequestType) {
                case RequestTypeConstant.REQUEST_TYPE_INFORMAION_NEWS_LIST://获取新闻列表
                    if (flag) {

                        jsonObjectParam.put("Type", 0);
                    }
                    break;

                case RequestTypeConstant.REQUEST_TYPE_INFORMATION_DYNAMIC_LIST://获取动态列表
                    if (flag) {

                        jsonObjectParam.put("Type", 1);
                    }

                    break;

                case 2://获取 banner
                    if (flag) {

                        jsonObjectParam.put("Type", 2);
                    }

                    break;


                case 9://获取banner图
                    if (flag) {
                        jsonObjectParam.put("Type", 9);
                    }

                    break;


            }

            kk.put("Param", jsonObjectParam);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        String str = kk.toString();
        Log.d("banner请求体", str);
        RequestBody formBody = RequestBody.create(JSON, str);
        OkHttpClient mOkHttpClient = new OkHttpClient();
//        mOkHttpClient.newBuilder().connectTimeout(10000, TimeUnit.MILLISECONDS);      //设置链接超时
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();

        //发送请求获取响应
        Response response = null;
        try {
            //请求加入调度
            response = mOkHttpClient.newCall(request).execute();
            //判断请求是否成功
            if (response.isSuccessful()) {
                //打印服务端返回结果
                String info = response.body().string();
                Log.d("tag", "getHttpData: 1111111--" + url);
                Log.d("返回数据", "getHttpData: 2222222--" + info);
                return info;
            } else {
                Log.d("tag", "body-code--baner" + response.code() + "--string ---" + response.message());
                return FAILED;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }


    public String getHttpDataByZuHe(String token, HashMap<String, String> map, String url, Integer RequestType) {
        Boolean flag = true;      //判断是否只有param一个参数
        if (TextUtils.isEmpty(token)) {   //token为null请求时会报异常
            token = "";
        }
        JSONObject kk = new JSONObject();
        try {


            JSONObject object = new JSONObject();
            object.put("Uid", UID);
            object.put("Lang", LANG);
            object.put("Token", token);
            object.put("Sign", Sign);
            object.put("TimeStamp", TimeStamp);


            kk.put("Head", object);
            JSONObject object2 = new JSONObject();
            if (map != null) {
                Iterator iter = map.entrySet().iterator();
                while (iter.hasNext()) {
                    Map.Entry entry = (Map.Entry) iter.next();
                    String key = (String) entry.getKey();
                    String val = (String) entry.getValue();
                    object2.put(key, val);
                    if (key.equals("PageInfo")) {
                        kk.put("PageInfo", val);
                        flag = false;
                    }
                }
            }
            JSONObject jsonObjectParam = new JSONObject();
            jsonObjectParam.put("PageInfo", object2);
            jsonObjectParam.put("Id", "");
            jsonObjectParam.put("SortBy",1);
            jsonObjectParam.put("SortType",0);
            switch (RequestType) {
                case RequestTypeConstant.REQUEST_TYPE_INFORMAION_NEWS_LIST://获取量化组合
                    if (flag) {

                        jsonObjectParam.put("Type", 0);
                        Log.d("ds","0");
                    }
                    break;

                case RequestTypeConstant.REQUEST_TYPE_INFORMATION_DYNAMIC_LIST://获取牛人组合
                    if (flag) {
                        jsonObjectParam.put("Type", 1);
                    }

                    break;


                case RequestTypeConstant.REQUEST_TYPE_INFORMAION_BANNER://我的组合
                    if (flag) {
                        jsonObjectParam.put("Type", 2);
                    }

                    break;


            }

            kk.put("Param", jsonObjectParam);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String str = kk.toString();
        Log.d("组合", "组合" + str);
        RequestBody formBody = RequestBody.create(JSON, str);
        OkHttpClient mOkHttpClient = new OkHttpClient();
//        mOkHttpClient.newBuilder().connectTimeout(10000, TimeUnit.MILLISECONDS);      //设置链接超时
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();

        //发送请求获取响应
        Response response = null;
        try {
            //请求加入调度
            response = mOkHttpClient.newCall(request).execute();
            //判断请求是否成功
            if (response.isSuccessful()) {
                //打印服务端返回结果
                String info = response.body().string();
                Log.d("tag", "getHttpData: 1111111--" + url);
                Log.d("返回数据-组合", "getHttpData: 2222222--" + info);
                return info;
            } else {
                Log.d("tag", "body-code--zuhe" + response.code() + "--string ---" + response.message());
                return FAILED;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }

    public String  getHttpDataByZuHeNiuRen(String token, HashMap<String, String> map, String url, Integer RequestType) {
        Boolean flag = true;      //判断是否只有param一个参数
        if (TextUtils.isEmpty(token)) {   //token为null请求时会报异常
            token = "";
        }
        JSONObject kk = new JSONObject();
        try {
            JSONObject object = new JSONObject();
            object.put("Uid", UID);
            object.put("Lang", LANG);
            object.put("Token", token);
            object.put("Sign", Sign);
            object.put("TimeStamp", TimeStamp);
            kk.put("Head", object);

            JSONObject object2 = new JSONObject();
            if (map != null) {
                Iterator iter = map.entrySet().iterator();
                while (iter.hasNext()) {
                    Map.Entry entry = (Map.Entry) iter.next();
                    String key = (String) entry.getKey();
                    String val = (String) entry.getValue();
                    object2.put(key, val);
                    if (key.equals("PageInfo")) {
                        kk.put("PageInfo", val);
                        flag = false;
                    }

                }
            }
            JSONObject jsonObjectParam = new JSONObject();
            jsonObjectParam.put("PageInfo", object2);
            jsonObjectParam.put("Id", "");
            switch (RequestType) {
                case RequestTypeConstant.REQUEST_TYPE_INFORMAION_NEWS_LIST://获取量化组合
                    if (flag) {
                        jsonObjectParam.put("Type", 0);
                        Log.d("ds","0");
                    }
                    break;

                case 1://获取牛人组合
                    if (flag) {

                        jsonObjectParam.put("Type", 1);
                        Log.d("牛人组合","1");

                    }
                    break;

                case RequestTypeConstant.REQUEST_TYPE_INFORMAION_BANNER://我的组合
                    if (flag) {
                        jsonObjectParam.put("Type", 2);
                        Log.d("牛人组合","2");

                    }
                    break;
            }

            kk.put("Param", jsonObjectParam);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String str = kk.toString();
        Log.d("牛人组合", "数据" + str);
        RequestBody formBody = RequestBody.create(JSON, str);
        OkHttpClient mOkHttpClient = new OkHttpClient();
//        mOkHttpClient.newBuilder().connectTimeout(10000, TimeUnit.MILLISECONDS);      //设置链接超时
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();

        //发送请求获取响应
        Response response = null;
        try {
            //请求加入调度
            response = mOkHttpClient.newCall(request).execute();
            //判断请求是否成功
            if (response.isSuccessful()) {
                //打印服务端返回结果
                String info = response.body().string();
                Log.d("牛人组合结果", "getHttpData: 1111111--" + url);
                Log.d("牛人组合结果info", "getHttpData: 2222222--" + info);
                return info;
            } else {
                Log.d("tag", "body-code--niuren" + response.code() + "--string ---" + response.message());
                return FAILED;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }

    public String  getHttpDataByZuHeMyOne(String token, HashMap<String, String> map, String url, Integer RequestType,String Userid) {
        Boolean flag = true;      //判断是否只有param一个参数
        if (TextUtils.isEmpty(token)) {   //token为null请求时会报异常
            token = "";
        }
        JSONObject kk = new JSONObject();//最大object
        try {
            JSONObject object = new JSONObject();
            object.put("Uid", UID);
            object.put("Lang", LANG);
            object.put("Token", token);
            object.put("Sign", Sign);
            object.put("TimeStamp", TimeStamp);
            kk.put("Head", object);

            JSONObject object2 = new JSONObject(); //PageInfo
            if (map != null) {
                Iterator iter = map.entrySet().iterator();
                while (iter.hasNext()) {
                    Map.Entry entry = (Map.Entry) iter.next();
                    String key = (String) entry.getKey();
                    String val = (String) entry.getValue();
                    object2.put(key, val);
                    if (key.equals("PageInfo")) {
                        kk.put("PageInfo", val);
                        flag = false;
                    }

                }
            }
            JSONObject jsonObjectParam = new JSONObject();//param
            jsonObjectParam.put("PageInfo", object2);
            jsonObjectParam.put("Id",Userid);
            jsonObjectParam.put("Relationship",0);
            switch (RequestType) {
                case 0://获取量化组合
                    if (flag) {
                        jsonObjectParam.put("Type", 1);
                    }
                    break;

                case 1://获取牛人组合
                    if (flag) {
                        jsonObjectParam.put("Type", 1);

                    }
                    break;

                case 2://我的组合
                    if (flag) {
                        jsonObjectParam.put("Type", 2);
                        Log.d("我的组合","2");
                    }
                    break;
            }

            kk.put("Param", jsonObjectParam);
            Log.d("我的组合请求体", "我的组合" + kk.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String str = kk.toString();
        RequestBody formBody = RequestBody.create(JSON, str);
        OkHttpClient mOkHttpClient = new OkHttpClient();
//        mOkHttpClient.newBuilder().connectTimeout(10000, TimeUnit.MILLISECONDS);      //设置链接超时
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();

        //发送请求获取响应
        Response response = null;
        try {
            //请求加入调度
            response = mOkHttpClient.newCall(request).execute();
            //判断请求是否成功
            if (response.isSuccessful()) {
                //打印服务端返回结果
                String info = response.body().string();
                Log.d("我的组合结果", "" + info);
                return info;
            } else {
                Log.d("我的组合结果else", "" +response.code()+ response.message());
                return FAILED;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }

    public String  getHttpDataByZuHeMyTwo(String token, HashMap<String, String> map, String url, Integer RequestType,String Userid) {
        Boolean flag = true;      //判断是否只有param一个参数
        if (TextUtils.isEmpty(token)) {   //token为null请求时会报异常
            token = "";
        }
        JSONObject kk = new JSONObject();//最大object
        try {
            JSONObject object = new JSONObject();
            object.put("Uid", UID);
            object.put("Lang", LANG);
            object.put("Token", token);
            object.put("Sign", Sign);
            object.put("TimeStamp", TimeStamp);
            kk.put("Head", object);

            JSONObject object2 = new JSONObject(); //PageInfo
            if (map != null) {
                Iterator iter = map.entrySet().iterator();
                while (iter.hasNext()) {
                    Map.Entry entry = (Map.Entry) iter.next();
                    String key = (String) entry.getKey();
                    String val = (String) entry.getValue();
                    object2.put(key, val);
                    if (key.equals("PageInfo")) {
                        kk.put("PageInfo", val);
                        flag = false;
                    }

                }
            }
            JSONObject jsonObjectParam = new JSONObject();//param
            jsonObjectParam.put("PageInfo", object2);
            jsonObjectParam.put("Id",Userid);
            jsonObjectParam.put("Relationship",1);
            switch (RequestType) {
                case 0://获取量化组合
                    if (flag) {
                        jsonObjectParam.put("Type", 1);
                    }
                    break;

                case 1://获取牛人组合
                    if (flag) {
                        jsonObjectParam.put("Type", 1);

                    }
                    break;

                case 2://我的组合
                    if (flag) {
                        jsonObjectParam.put("Type", 2);
                        Log.d("我的组合","2");
                    }
                    break;
            }

            kk.put("Param", jsonObjectParam);
            //Log.d("我的组合", "我的组合" + kk.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String str = kk.toString();
        RequestBody formBody = RequestBody.create(JSON, str);
        OkHttpClient mOkHttpClient = new OkHttpClient();
//        mOkHttpClient.newBuilder().connectTimeout(10000, TimeUnit.MILLISECONDS);      //设置链接超时
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();

        //发送请求获取响应
        Response response = null;
        try {
            //请求加入调度
            response = mOkHttpClient.newCall(request).execute();
            //判断请求是否成功
            if (response.isSuccessful()) {
                //打印服务端返回结果
                String info = response.body().string();
                Log.d("我的组合结果", "" + info);
                return info;
            } else {
                Log.d("我的组合结果else", "" +response.code()+ response.message());
                return FAILED;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }
    public String  getHttpDataByZuHeMyThree(String token, HashMap<String, String> map, String url, Integer RequestType,String Userid) {
        Boolean flag = true;      //判断是否只有param一个参数
        if (TextUtils.isEmpty(token)) {   //token为null请求时会报异常
            token = "";
        }
        JSONObject kk = new JSONObject();//最大object
        try {
            JSONObject object = new JSONObject();
            object.put("Uid", UID);
            object.put("Lang", LANG);
            object.put("Token", token);
            object.put("Sign", Sign);
            object.put("TimeStamp", TimeStamp);
            kk.put("Head", object);

            JSONObject object2 = new JSONObject(); //PageInfo
            if (map != null) {
                Iterator iter = map.entrySet().iterator();
                while (iter.hasNext()) {
                    Map.Entry entry = (Map.Entry) iter.next();
                    String key = (String) entry.getKey();
                    String val = (String) entry.getValue();
                    object2.put(key, val);
                    if (key.equals("PageInfo")) {
                        kk.put("PageInfo", val);
                        flag = false;
                    }

                }
            }
            JSONObject jsonObjectParam = new JSONObject();//param
            jsonObjectParam.put("PageInfo", object2);
            jsonObjectParam.put("Id",Userid);
            jsonObjectParam.put("Relationship",2);
            switch (RequestType) {
                case 0://获取量化组合
                    if (flag) {
                        jsonObjectParam.put("Type", 1);
                    }
                    break;

                case 1://获取牛人组合
                    if (flag) {
                        jsonObjectParam.put("Type", 1);

                    }
                    break;

                case 2://我的组合
                    if (flag) {
                        jsonObjectParam.put("Type", 2);
                        Log.d("我的组合","2");
                    }
                    break;
            }

            kk.put("Param", jsonObjectParam);
            //Log.d("我的组合", "我的组合" + kk.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String str = kk.toString();
        RequestBody formBody = RequestBody.create(JSON, str);
        OkHttpClient mOkHttpClient = new OkHttpClient();
//        mOkHttpClient.newBuilder().connectTimeout(10000, TimeUnit.MILLISECONDS);      //设置链接超时
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();

        //发送请求获取响应
        Response response = null;
        try {
            //请求加入调度
            response = mOkHttpClient.newCall(request).execute();
            //判断请求是否成功
            if (response.isSuccessful()) {
                //打印服务端返回结果
                String info = response.body().string();
                Log.d("我的组合结果", "" + info);
                return info;
            } else {
                Log.d("我的组合结果else", "" +response.code()+ response.message());
                return FAILED;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }

    public String  getHttpDataByAdapter(String token, String url, String id) {
        Boolean flag = true;      //判断是否只有param一个参数
        if (TextUtils.isEmpty(token)) {   //token为null请求时会报异常
            token = "";
        }
        JSONObject kk = new JSONObject();//最大object
        try {
            JSONObject object = new JSONObject();
            object.put("Uid",UID);
//            object.put("Uid","41e88946ba3f2459");
            object.put("Lang", LANG);
//            object.put("Token", "9d26a30e6473c946");
            object.put("Token", token);
            object.put("Sign", Sign);
            object.put("TimeStamp", TimeStamp);
            kk.put("Head", object);

            JSONObject object2 = new JSONObject(); //PageInfo
            JSONObject jsonObjectParam = new JSONObject();//param
            jsonObjectParam.put("Id",id);

            kk.put("Param", jsonObjectParam);
            Log.d("请求提", "adapter里面" + kk.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String str = kk.toString();
        RequestBody formBody = RequestBody.create(JSON, str);
        OkHttpClient mOkHttpClient = new OkHttpClient();
//        mOkHttpClient.newBuilder().connectTimeout(10000, TimeUnit.MILLISECONDS);      //设置链接超时
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();

        //发送请求获取响应
        Response response = null;
        try {
            //请求加入调度
            response = mOkHttpClient.newCall(request).execute();
            //判断请求是否成功
            if (response.isSuccessful()) {
                //打印服务端返回结果
                String info = response.body().string();
                Log.d("展开产看数据", "" + info);
                return info;
            } else {
                Log.d("我的组合结果else", "" +response.code()+ response.message());
                return FAILED;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }
    public String  upteMessage(String token,  String Upid,String name,boolean IsSmsNotic) {

        Boolean flag = true;      //判断是否只有param一个参数
        if (TextUtils.isEmpty(token)) {   //token为null请求时会报异常
            token = "";
        }
        JSONObject kk = new JSONObject();//最大object
        try {
            JSONObject object = new JSONObject();
            object.put("Uid",UID);
            object.put("Lang", LANG);
            object.put("Token", token);
            object.put("Sign", Sign);
            object.put("TimeStamp", TimeStamp);
            kk.put("Head", object);

            JSONObject jsonObjectParam = new JSONObject();//param
            jsonObjectParam.put("Id",Upid);
            jsonObjectParam.put("Name",name);
            jsonObjectParam.put("IsSmsNotic ",IsSmsNotic);


            kk.put("Param", jsonObjectParam);
            Log.d("请求提", "保存 跟新" + kk.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String str = kk.toString();
        RequestBody formBody = RequestBody.create(JSON, str);
        OkHttpClient mOkHttpClient = new OkHttpClient();
//        mOkHttpClient.newBuilder().connectTimeout(10000, TimeUnit.MILLISECONDS);      //设置链接超时
        Request request = new Request.Builder()
                .url(UpDateZuHeMe)
                .post(formBody)
                .build();

        //发送请求获取响应
        Response response = null;
        try {
            //请求加入调度
            response = mOkHttpClient.newCall(request).execute();
            //判断请求是否成功
            if (response.isSuccessful()) {
                //打印服务端返回结果
                String info = response.body().string();
                Log.d("保存 组合设置 返回数据", "" + info);
                return info;
            } else {
                Log.d("我的组合结果else", "" +response.code()+ response.message());
                return FAILED;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }

    public String  liJiGenTou(String token, String url, String id,double money) {

        Boolean flag = true;      //判断是否只有param一个参数
        if (TextUtils.isEmpty(token)) {   //token为null请求时会报异常
            token = "";
        }
        JSONObject kk = new JSONObject();//最大object
        try {
            JSONObject object = new JSONObject();
            object.put("Uid",UID);
            object.put("Lang", LANG);
            object.put("Token", token);
            object.put("Sign", Sign);
            object.put("TimeStamp", TimeStamp);
            kk.put("Head", object);

            JSONObject jsonObjectParam = new JSONObject();//param
            jsonObjectParam.put("Id",id);
            jsonObjectParam.put("Option",0);
            jsonObjectParam.put("Type",0);
            jsonObjectParam.put("Money",money);
            jsonObjectParam.put("InfoId","");
            jsonObjectParam.put("Content","");

            kk.put("Param", jsonObjectParam);
            Log.d("请求提", "立即跟投" + kk.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String str = kk.toString();
        RequestBody formBody = RequestBody.create(JSON, str);
        OkHttpClient mOkHttpClient = new OkHttpClient();
//        mOkHttpClient.newBuilder().connectTimeout(10000, TimeUnit.MILLISECONDS);      //设置链接超时
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();

        //发送请求获取响应
        Response response = null;
        try {
            //请求加入调度
            response = mOkHttpClient.newCall(request).execute();
            //判断请求是否成功
            if (response.isSuccessful()) {
                //打印服务端返回结果
                String info = response.body().string();
                Log.d("展开产看数据", "" + info);
                return info;
            } else {
                Log.d("我的组合结果else", "" +response.code()+ response.message());
                return FAILED;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }


    public String  xiaDan(String token, String id,int type) {

        Boolean flag = true;      //判断是否只有param一个参数
        if (TextUtils.isEmpty(token)) {   //token为null请求时会报异常
            token = "";
        }
        JSONObject kk = new JSONObject();//最大object
        try {
            JSONObject object = new JSONObject();
            object.put("Uid",UID);
            object.put("Lang", LANG);
            object.put("Token", token);
            object.put("Sign", Sign);
            object.put("TimeStamp", TimeStamp);
            kk.put("Head", object);

            JSONObject jsonObjectParam = new JSONObject();//param
            jsonObjectParam.put("Id",id);
            jsonObjectParam.put("Option",0);
            jsonObjectParam.put("Type",type);
            jsonObjectParam.put("Money","");
            jsonObjectParam.put("InfoId","");
            jsonObjectParam.put("Content","");

            kk.put("Param", jsonObjectParam);
            Log.d("请求提", "立即跟投" + kk.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String str = kk.toString();
        RequestBody formBody = RequestBody.create(JSON, str);
        OkHttpClient mOkHttpClient = new OkHttpClient();
//        mOkHttpClient.newBuilder().connectTimeout(10000, TimeUnit.MILLISECONDS);      //设置链接超时
        Request request = new Request.Builder()
                .url(xiaDan)
                .post(formBody)
                .build();

        //发送请求获取响应
        Response response = null;
        try {
            //请求加入调度
            response = mOkHttpClient.newCall(request).execute();
            //判断请求是否成功
            if (response.isSuccessful()) {
                //打印服务端返回结果
                String info = response.body().string();
                Log.d("展开产看数据", "" + info);
                return info;
            } else {
                Log.d("我的组合结果else", "" +response.code()+ response.message());
                return FAILED;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }

    public String  PopwindowQueDing(String token, int paramId,String Option,String Type) {

        Boolean flag = true;      //判断是否只有param一个参数
        if (TextUtils.isEmpty(token)) {   //token为null请求时会报异常
            token = "";
        }
        JSONObject kk = new JSONObject();//最大object
        try {
            JSONObject object = new JSONObject();
            object.put("Uid",UID);
            object.put("Lang", LANG);
            object.put("Token", token);
            object.put("Sign", Sign);
            object.put("TimeStamp", TimeStamp);
            kk.put("Head", object);

            JSONObject jsonObjectParam = new JSONObject();//param
            jsonObjectParam.put("Id",paramId);
            jsonObjectParam.put("Option",Option);
            jsonObjectParam.put("Type",Type);
            jsonObjectParam.put("Money","");
            jsonObjectParam.put("InfoId","");
            jsonObjectParam.put("Content","");

            kk.put("Param", jsonObjectParam);
            Log.d("请求提", "取消" + kk.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String str = kk.toString();
        RequestBody formBody = RequestBody.create(JSON, str);
        OkHttpClient mOkHttpClient = new OkHttpClient();
//        mOkHttpClient.newBuilder().connectTimeout(10000, TimeUnit.MILLISECONDS);      //设置链接超时
        Request request = new Request.Builder()
                .url(xiaDan)
                .post(formBody)
                .build();

        //发送请求获取响应
        Response response = null;
        try {
            //请求加入调度
            response = mOkHttpClient.newCall(request).execute();
            //判断请求是否成功
            if (response.isSuccessful()) {
                //打印服务端返回结果
                String info = response.body().string();
                Log.d("展开产看数据", "" + info);
                return info;
            } else {
                Log.d("我的组合结果else", "" +response.code()+ response.message());
                return FAILED;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }

    public String queRenChuangJian(String token, HashMap<String, Object> map) {
        Log.d("SetupZuHeActivity", "mar组合:" + map.toString());
        if (TextUtils.isEmpty(token)) {   //token为null请求时会报异常
            token = "";
        }
        JSONObject kk = new JSONObject();
        try {
            JSONObject object = new JSONObject();
            object.put("Uid", UID);
            object.put("Lang", LANG);
            object.put("TimeStamp",TimeStamp);
            object.put("Token", token);
            object.put("Sign",Sign);
            kk.put("Head", object);
            JSONObject object2 = new JSONObject();
            if (map != null) {
                Iterator iter = map.entrySet().iterator();
                while (iter.hasNext()) {
                    Map.Entry entry = (Map.Entry) iter.next();
                    if (entry.getKey().equals("CodeList")) {
                        JSONArray object3 = new JSONArray();

                        ArrayList<HashMap<String, String>> mapinfo = (ArrayList<HashMap<String, String>>) map.get("CodeList");
                        for (int i = 0; i < mapinfo.size(); i++) {
                            Iterator itermap = mapinfo.get(i).entrySet().iterator();
                            JSONObject json = new JSONObject();
                            while (itermap.hasNext()) {
                                Map.Entry entryThree = (Map.Entry) itermap.next();
                                String key = (String) entryThree.getKey();
                                String val = (String) entryThree.getValue();
                                json.put(key, val);
                            }
                            object3.put(i, json);
                        }
                        object2.put("CodeList", object3);
                    } else if (entry.getKey().equals("Codes")) {
                        JSONArray object3 = new JSONArray();

                        ArrayList<HashMap<String, String>> mapinfo = (ArrayList<HashMap<String, String>>) map.get("Codes");
                        for (int i = 0; i < mapinfo.size(); i++) {
                            Iterator itermap = mapinfo.get(i).entrySet().iterator();
                            JSONObject json = new JSONObject();
                            while (itermap.hasNext()) {
                                Map.Entry entryThree = (Map.Entry) itermap.next();
                                String key = (String) entryThree.getKey();
                                String val = (String) entryThree.getValue();

                                if (key.equals("TradeItem")){
                                    ArrayList<HashMap<String, String>> list = (ArrayList<HashMap<String, String>>) entry.getValue();
                                    json.put(key, list);
                                }

                                json.put(key, val);
                            }
                            object3.put(i, json);
                        }
                        object2.put("Codes", object3);
                    } else {

                        String key = (String) entry.getKey();

                        String val="";
                        if (key.equals("TradeItem")){

                            ArrayList<HashMap<String, String>> list = (ArrayList<HashMap<String, String>>) entry.getValue();
                            Log.d("HttpManager", "11111111"+list.toString());

                            JSONArray arr = new JSONArray();
                            for (int i = 0; i < list.size(); i++) {
                                JSONObject obj = new JSONObject();
                                HashMap<String, String> mapList = list.get(i);
                                obj.put("Name", mapList.get("Name"));
                                obj.put("TradeType", mapList.get("TradeType"));
                                obj.put("TradeTime", mapList.get("TradeTime"));
                                obj.put("Code", mapList.get("Code"));
                                obj.put("Volume", mapList.get("Volume"));
                                obj.put("Price", mapList.get("Price"));
                                Log.d("HttpManager", "11111111"+mapList.toString());

                                arr.put(obj);
                            }

                            object2.put(key, arr);

                            Log.d("HttpManager", "object2"+object2.toString());
                        }

                        else {
                             val = (String) entry.getValue();
                            object2.put(key, val);
                        }

                    }
                }
            }
            kk.put("Param", object2);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        String str = kk.toString();

        Log.d("tag", "str---调仓--" + str);
        RequestBody formBody = RequestBody.create(JSON, str);
        OkHttpClient mOkHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(queRenChuangJian)
                .post(formBody)
                .build();

        //发送请求获取响应(同步请求)
        Response response = null;
        try {
            //请求加入调度
            response = mOkHttpClient.newCall(request).execute();
            //判断请求是否成功
            if (response.isSuccessful()) {
                //打印服务端返回结果
                String info = response.body().string();
//                Log.d("tag", "getHttpData: 1111111--" + url);
                Log.d("tag", "getHttpData: 2222222--" + info);
                return info;
            } else {
                Log.d("tag", "body-code--Three" + response.code() + "--string ---" + response.message());
                return FAILED;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }

    public static String chiCang(String token,String id){
        MediaType JSON = MediaType.parse("application/json;charset=utf-8");   //数据传输类型
        OkHttpClient client = new OkHttpClient();
        JSONObject object = new JSONObject();
        JSONObject objectHead = new JSONObject();
        JSONObject objectBody = new JSONObject();
        try {
            objectHead.put("Uid",UID);
            objectHead.put("Lang",LANG);
            objectHead.put("Token",token);
            objectHead.put("Sign",Sign);
            objectHead.put("TimeStamp",TimeStamp);
            object.put("Head",objectHead);
            objectBody.put("Id",id);
            object.put("Param",objectBody);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body=RequestBody.create(JSON,object.toString());
        Request request = new Request.Builder().post(body).url(HttpManager.chiCang).build();
        Call call = client.newCall(request);
        try {
            Response respose = call.execute();
            return respose.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

}
