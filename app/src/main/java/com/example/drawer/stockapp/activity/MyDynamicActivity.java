package com.example.drawer.stockapp.activity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.drawer.stockapp.R;
import com.example.drawer.stockapp.adapter.DynamicInfoAdapter;
import com.example.drawer.stockapp.adapter.ImageAdapter;
import com.example.drawer.stockapp.customview.MyDialog;
import com.example.drawer.stockapp.customview.MyGridView;
import com.example.drawer.stockapp.htttputil.HttpManager;
import com.example.drawer.stockapp.model.CommnetInfo;
import com.example.drawer.stockapp.model.DynamicDetialInfo;
import com.example.drawer.stockapp.model.DynamicsInfo;
import com.example.drawer.stockapp.model.TrendsInfo;
import com.example.drawer.stockapp.utils.DensityUtils;
import com.example.drawer.stockapp.utils.ManagerUtil;
import com.example.drawer.stockapp.utils.ShapePreferenceManager;
import com.google.gson.Gson;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.sharesdk.onekeyshare.OnekeyShare;

public class MyDynamicActivity extends BascActivity implements View.OnClickListener{
    public static final String DYNAMICINFO = "dynamicinfoid";//获取动态数据id
    public static final String TYPE = "type";//获取类型数据
//    private DynamicsInfo.ResultBean.DataBean shareBean;
//    private TrendsInfo trendsInfo;
    private DynamicInfoAdapter adapter;
    private CommnetInfo commnetInfo;
    private ListView mList;
    private int type;     //跳转类型
    private String id; //动态数据id
    private EditText mCommentEdit;
    private String mToken,mImage;
    private ImageView mZanImg;
    private MyDialog dialog;
    private TextView mComment,mZhuanFa,mLikes,name,content,time;
    private MyGridView contentImage;
    private ImageView head;
    private Boolean flag;
    private String userID;//当前动态的用户ID
    private Intent in = new Intent();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_dynamic);

        mToken = ShapePreferenceManager.getMySharedPreferences(this).getString(ShapePreferenceManager.TOKEN,null);
        tintManager.setStatusBarTintResource(R.color.write_color);
        id = getIntent().getStringExtra(DYNAMICINFO); //动态ID 来自FirstNewsFragment.class
        type = getIntent().getIntExtra(TYPE,0);
        initWight();
        initSoftWindow(type);
        DsyInfoAsyn dsyInfoAsyn = new DsyInfoAsyn();
        dsyInfoAsyn.execute(id);
        DynamicTask task = new DynamicTask();
        task.execute();
        dialog = ManagerUtil.getDiaLog(this);

    }

    public void initWight(){
        RelativeLayout mTitleRelat = (RelativeLayout) findViewById(R.id.dynamic_title);    //title布局
        //设置距离顶部状态栏高度
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                DensityUtils.dp2px(this,50));
        params.setMargins(0, ManagerUtil.getStatusBarHeight(this),0,0);
        mTitleRelat.setLayoutParams(params);

        ImageView mBackimg = (ImageView) findViewById(R.id.back_img);
        View headRelat = LayoutInflater.from(this).inflate(R.layout.dongtai_item_layout,null);

        mCommentEdit = (EditText) findViewById(R.id.dongtai_comment_edit);
        TextView mSend = (TextView) findViewById(R.id.send_comment);  //发送


//        RelativeLayout layout = (RelativeLayout) headRelat.findViewById(R.id.zan_relate);
        head = (ImageView) headRelat.findViewById(R.id.dongtai_image);
        name = (TextView) headRelat.findViewById(R.id.dongtai_name);
        content = (TextView) headRelat.findViewById(R.id.dongtai_content);
        contentImage = (MyGridView) headRelat.findViewById(R.id.dongtai_cntent_image);
        time = (TextView) headRelat.findViewById(R.id.dongtai_time);
        mZhuanFa = (TextView) headRelat.findViewById(R.id.dongtai_zhuanfa);
        mComment = (TextView) headRelat.findViewById(R.id.dongtai_pinglun);
        mLikes = (TextView) headRelat.findViewById(R.id.dongtai_zan);
        mZanImg = (ImageView) headRelat.findViewById(R.id.dianzan_img);

        content.setMaxLines(100);
        content.setTextSize(14);
//        ImageView mCollect = (ImageView) headRelat.findViewById(R.id.collect_info_img);


//        if (trendsInfo != null){
//            if (trendsInfo.getLikes()){
//                mZanImg.setImageResource(R.mipmap.y_dianzan);
//            }else {
//                mZanImg.setImageResource(R.mipmap.zan);
//            }
//            if (!TextUtils.isEmpty(trendsInfo.getImage())){
//                Picasso.with(this).load(trendsInfo.getImage()).into(head);
//            }
//
//
//            ImageAdapter adapter = new ImageAdapter(this);
//            adapter.setData(trendsInfo.getContentImage());
//            contentImage.setAdapter(adapter);
//            name.setText(trendsInfo.getName());
//            content.setText(trendsInfo.getContent());
//            time.setText(trendsInfo.getTime());
//            mZhuanFa.setText(trendsInfo.getZhuanFaNum()+"");
//            mComment.setText(trendsInfo.getCommentNum()+"");
//            mLikes.setText(trendsInfo.getGoodNum()+"");
//        }
//        ImageView mShare = (ImageView) findViewById(R.id.share_img);   //分享

        mList = (ListView) findViewById(R.id.dynamic_list);
        adapter = new DynamicInfoAdapter(this);

        mList.addHeaderView(headRelat);

//        mCommentEdit.setOnKeyListener(onKeyListener);
        mBackimg.setOnClickListener(this);
        mZhuanFa.setOnClickListener(this);
        mComment.setOnClickListener(this);
//        layout.setOnClickListener(this);
        mSend.setOnClickListener(this);
//        mShare.setOnClickListener(this);
//        mCollect.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        SystemBarTintManager tintManager = ManagerUtil.newInstance(this);
        ManagerUtil.setStataBarColor(this,tintManager);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.dongtai_image:
                break;
            case R.id.back_img:
                finish();
                break;
            case R.id.dongtai_zhuanfa:
                Toast.makeText(getApplicationContext(),"该功能还在完善",Toast.LENGTH_SHORT).show();
                type  = 2;
                initSoftWindow(type);
                break;
            case R.id.dongtai_pinglun:
                type = 1;
                initSoftWindow(type);
                break;
            case R.id.zan_relate:
                dialog = ManagerUtil.getDiaLog(this);
                if (flag){
                    LikeOrCollectAsyn likeOrCollectAsyn = new LikeOrCollectAsyn();
                    likeOrCollectAsyn.execute(id,"1","4","1",mToken);
//                    mLikes.setText((Integer.parseInt(mLikes.getText().toString())-1)+"");
//                    mZanImg.setImageResource(R.mipmap.zan);
//                    trendsInfo.setLikes(false);
                }else {
                    LikeOrCollectAsyn likeOrCollectAsyn = new LikeOrCollectAsyn();
                    likeOrCollectAsyn.execute(id,"1","4","0",mToken);
//                    mLikes.setText((Integer.parseInt(mLikes.getText().toString())+1)+"");
//                    trendsInfo.setLikes(true);
//                    mZanImg.setImageResource(R.mipmap.y_dianzan);
                }
                break;
//            case R.id.collect_info_img:   //收藏
//                likeOrCollectAsyn = new LikeOrCollectAsyn();
//                if (shareBean.isFavorite()){
//                    likeOrCollectAsyn.execute(shareBean.getId(),"CancelFavorites",mToken);
//                }else {
//                    likeOrCollectAsyn.execute(shareBean.getId(),"Favorites",mToken);
//                }
//                break;
//            case R.id.share_img:
//                showShare(name.getText().toString(),mImage);
//                break;
            case R.id.send_comment:
                //点击进行逻辑处理
                String key = mCommentEdit.getText().toString().trim();
                if (type == 2){
                    Toast.makeText(getApplicationContext(),"该功能还在完善",Toast.LENGTH_SHORT).show();
//                    mZhuanFa.setText((Integer.parseInt(mZhuanFa.getText().toString())+1)+"");
//                    likeOrForwordAsyn.execute(shareBean.getId(),"Forward",key,mToken,HttpManager.Comment_URL);
                }else if (!TextUtils.isEmpty(key)){
                    dialog = ManagerUtil.getDiaLog(MyDynamicActivity.this);
                    LikeOrForwordAsyn likeOrForwordAsyn = new LikeOrForwordAsyn();

                    mComment.setText((Integer.parseInt(mComment.getText().toString())+1)+"");

                    likeOrForwordAsyn.execute(id,"0","0",key,"0",mToken);
//                    likeOrForwordAsyn.execute(id,"Comment",key,mToken,HttpManager.Comment_URL);

                }else {
                    Toast.makeText(getApplicationContext(),"请输入要发表的内容",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }


    /**
     * 获取动态详情
     */
    private class DsyInfoAsyn extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... strings) {
            HashMap<String,String> map = new HashMap<>();
            map.put("Id", strings[0]);
            String message = HttpManager.newInstance().getHttpIndexData(mToken,map,HttpManager.NewsDetail_URL);
            return message;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d("tag","sss-------"+s);
            if (!TextUtils.isEmpty(s)){
                Gson gson = new Gson();
                DynamicDetialInfo newsDetial = gson.fromJson(s,DynamicDetialInfo.class);
                if (newsDetial.getHead().getStatus() == 0){
                   DynamicDetialInfo.ResultBean resultBean =  newsDetial.getResult();

                    userID = resultBean.getUserId();
                    if (resultBean.isLike()){
                        mZanImg.setImageResource(R.mipmap.dianzan_select);
                    }else {
                        mZanImg.setImageResource(R.mipmap.dianzan_unselect);
                    }
                    mImage = resultBean.getImgUrl();
                        Picasso.with(MyDynamicActivity.this).load(mImage).placeholder(R.mipmap.usericon).into(head);
                    if (resultBean.getNickName() != null&&!TextUtils.isEmpty(resultBean.getNickName())){
                        name.setText(resultBean.getNickName());
                    }else {
                        name.setText(R.string.user_name);
                    }
                    content.setText(resultBean.getContent());
                    time.setText(resultBean.getUpdateTime());
                    mZhuanFa.setText(resultBean.getForwards()+"");
                    mComment.setText(resultBean.getComments()+"");
                    mLikes.setText(resultBean.getLikes()+"");
                    if (resultBean.isLike()){
                        flag= true;
                        mZanImg.setImageResource(R.mipmap.dianzan_select);
                    }else {
                        flag = false;
                        mZanImg.setImageResource(R.mipmap.dianzan_unselect);
                    }
                    ArrayList<String> list = new ArrayList<>();
                    for (int j = 0;j<resultBean.getImgs().size();j++){
                        list.add(resultBean.getImgs().get(j));
                    }
                    ImageAdapter adapter = new ImageAdapter(MyDynamicActivity.this);
                    adapter.setData(list);
                    contentImage.setAdapter(adapter);


                }
            }
        }
    }

    /**
     * 分享
     */
    private void showShare(String shareTxt,String image) {

        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        // 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle(shareTxt);
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl("");

        // text是分享文本，所有平台都需要这个字段
        oks.setText(shareTxt);
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        oks.setImageUrl(image);
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("");

// 启动分享GUI
        oks.show(this);
    }

    /**
     * 获取动态评论
     */
    private class DynamicTask extends AsyncTask<Void,Void,String>{

        @Override
        protected String doInBackground(Void... voids) {

            HashMap<String,Object> hashMap = new HashMap<>();
            HashMap<String,String> map = new HashMap<>();
            map.put("PageIndex", "0");
            map.put("PageCount", "0");
            map.put("PageSize", "10000");
            hashMap.put("PageInfo",map);
            hashMap.put("Id",id);
            hashMap.put("Type","0");
            String message = HttpManager.newInstance().getHttpDataByThreeLayer(mToken,hashMap,HttpManager.COMMENT_LIST_URL);
            return message;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            dialog.dismiss();
            String message = s;
            if (!TextUtils.isEmpty(message)){
                Gson gson = new Gson();

                JSONObject object1 = null;
                DynamicsInfo.HeadBean headBean = new DynamicsInfo.HeadBean();;
                try {
                    object1 = new JSONObject(s);
                    JSONObject objecthead = (JSONObject) object1.get("Head");
                    headBean.setMsg(objecthead.getString("Msg"));
                    headBean.setStatus(objecthead.getInt("Status"));
                    if (headBean.getStatus()==1)return;
                } catch (JSONException e) {
                    e.printStackTrace();
                }



                if (headBean.getStatus()!=1){

                    commnetInfo = gson.fromJson(message,CommnetInfo.class);
                    parseData(commnetInfo);
                }




            }
        }
    }

    public void parseData(CommnetInfo commnetInfo){
        List<CommnetInfo.ResultBean.DataBean> data = commnetInfo.getResult().getData();
        ArrayList<TrendsInfo> list = new ArrayList<>();
        for (int i = 0;i<data.size();i++){
            CommnetInfo.ResultBean.DataBean dataBean = data.get(i);
            TrendsInfo info = new TrendsInfo();
            info.setFriendName(dataBean.getNickName());
            info.setFriendContent(dataBean.getContent());
            info.setFriendImage(dataBean.getImgUrl());
            list.add(info);
        }
        adapter.setData(list);
        mList.setAdapter(adapter);
    }


    /**
     * r软件盘弹出状况
     * @param type
     */
    public void initSoftWindow(int type){
        switch (type){
            case 0:
                mCommentEdit.setText("");
                mCommentEdit.setHint("写下您的评论");
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
                break;
            case 1:
                mCommentEdit.setText("");
                mCommentEdit.setHint("写下您的评论");
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN|WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
                break;
            case 2:
                mCommentEdit.setText("");
                mCommentEdit.setHint("写下您的转发内容");
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN|WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
                break;
        }
    }

    /**
     * 软键盘监听
     */
    private View.OnKeyListener onKeyListener = new View.OnKeyListener() {

        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            if(keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN){
                /*隐藏软键盘*/
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if(inputMethodManager.isActive()){
                    inputMethodManager.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
                }
                //点击进行逻辑处理
                String key = mCommentEdit.getText().toString().trim();
                if (type == 2){
                    Toast.makeText(getApplicationContext(),"该功能还在完善",Toast.LENGTH_SHORT).show();
//                    mZhuanFa.setText((Integer.parseInt(mZhuanFa.getText().toString())+1)+"");
//                    likeOrForwordAsyn.execute(shareBean.getId(),"Forward",key,mToken,HttpManager.Comment_URL);
                }else if (!TextUtils.isEmpty(key)){
                    dialog = ManagerUtil.getDiaLog(MyDynamicActivity.this);
                    LikeOrForwordAsyn likeOrForwordAsyn = new LikeOrForwordAsyn();
                    mComment.setText((Integer.parseInt(mComment.getText().toString())+1)+"");
                    likeOrForwordAsyn.execute(id,"0","0",key,"0",mToken);

//                    likeOrForwordAsyn.execute(id,"Comment",key,mToken,HttpManager.Comment_URL);//以前的
                }else {
                    Toast.makeText(getApplicationContext(),"请输入要发表的内容",Toast.LENGTH_SHORT).show();
                }

                return true;
            }
            return false;
        }
    };


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(null != this.getCurrentFocus()){
            /**
             * 点击空白位置 隐藏软键盘
             */
            InputMethodManager mInputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            return mInputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
        }
        return super .onTouchEvent(event);
    }

    // 获取点击事件
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        // TODO Auto-generated method stub
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View view = getCurrentFocus();
            if (isHideInput(view, ev)) {
                HideSoftInput(view.getWindowToken());
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    // 判定是否需要隐藏
    private boolean isHideInput(View v, MotionEvent ev) {
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0], top = l[1], bottom = top + v.getHeight(), right = left
                    + v.getWidth();
            if (ev.getX() > left && ev.getX() < right && ev.getY() > top
                    && ev.getY() < bottom) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    // 隐藏软键盘
    private void HideSoftInput(IBinder token) {
        if (token != null) {
            InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            manager.hideSoftInputFromWindow(token,
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /**
     * 评论、转发
     */
    private class LikeOrForwordAsyn extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... strings) {
            HashMap<String, String> map = new HashMap<>();
            map.put("Id", strings[0]);
            map.put("TargetType", strings[1]);
            map.put("Type",strings[2]);
            map.put("Content", strings[3]);
            map.put("OptionType",strings[4]);
            String message = HttpManager.newInstance().getHttpIndexData(strings[5], map, HttpManager.Favorites_URL);
            return message;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            dialog.dismiss();
            if (!TextUtils.isEmpty(s)){
                try {
                    JSONObject object = new JSONObject(s);
                    if (object.has("Head")){
                        JSONObject head = object.getJSONObject("Head");
                        if (head.getString("Status").equals("1")){
                            Toast.makeText(getApplicationContext(),"发布失败",Toast.LENGTH_SHORT).show();
//                            Snackbar.make(mCommentEdit,"发布失败！",Snackbar.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getApplicationContext(),"发布成功",Toast.LENGTH_SHORT).show();
                            mCommentEdit.setText("");
                            DynamicTask task = new DynamicTask();
                            task.execute();
//                            in.setAction("com.stock.sendtype");
//                            //发送广播,销毁此界面
//                            sendBroadcast(in);
//                            finish();
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    /**
     * 搜索收藏、点赞
     */
    private class LikeOrCollectAsyn extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... strings) {
            HashMap<String, String> map = new HashMap<>();
            map.put("Id", strings[0]);
            map.put("TargetType", strings[1]);
            map.put("Type",strings[2]);
            map.put("OptionType",strings[3]);


            String message = HttpManager.newInstance().getHttpIndexData(mToken, map, HttpManager.Favorites_URL);
            return message;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            dialog.dismiss();
            if (!TextUtils.isEmpty(s)){
                try {
                    JSONObject object = new JSONObject(s);
                    if (object.has("Head")){
                        JSONObject head = object.getJSONObject("Head");
                        if (head.getString("Status").equals("1")){
                            Toast.makeText(getApplicationContext(),head.getString("Msg"),Toast.LENGTH_SHORT).show();
//                            TSnackbar.make(mCommentEdit,head.getString("Msg"),TSnackbar.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getApplicationContext(),"操作成功",Toast.LENGTH_SHORT).show();
                            DsyInfoAsyn task = new DsyInfoAsyn();
                            task.execute(id);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
