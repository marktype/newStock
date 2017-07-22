package com.example.drawer.stockapp.activity;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.drawer.stockapp.R;
import com.example.drawer.stockapp.htttputil.HttpManager;
import com.example.drawer.stockapp.model.UserInfo;
import com.example.drawer.stockapp.model.UserOutherInfoBean;
import com.example.drawer.stockapp.model.UserOuthreInfo;
import com.example.drawer.stockapp.utils.DensityUtils;
import com.example.drawer.stockapp.utils.ManagerUtil;
import com.example.drawer.stockapp.utils.ShapePreferenceManager;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.umeng.analytics.MobclickAgent;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import cn.jpush.android.api.JPushInterface;

public class UserInfoDetialActivity extends BascActivity implements View.OnClickListener {

    public static final String USER_ID = "user_id";
    private String userId;
    private UserOuthreInfo userOuthreInfo;
    private String token;

    private ImageView ivIcon,ivBack,ivSex;
    private TextView tvFansNum;//粉丝
    private TextView tvConcerns; //关注
    private TextView tvFavorites; //收藏
    private TextView tvUserNickName;
    private TextView tvTitleUserNickName;
    private TextView tvIsAttention;
    private UserOutherInfoBean resultBean;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tintManager.setStatusBarTintResource(R.color.write_color);
        setContentView(R.layout.activity_person_info_detial);
        userId = getIntent().getStringExtra(USER_ID);
        token = ShapePreferenceManager.getMySharedPreferences(this).getString(ShapePreferenceManager.TOKEN,"");

        initView();
        UserInfoAsyn asyn = new UserInfoAsyn();
        asyn.execute();

    }

    private void initView() {
        RelativeLayout mTitleRelat = (RelativeLayout) findViewById(R.id.user_info_title);    //title布局
        //设置距离顶部状态栏高度
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                DensityUtils.dp2px(this, 50));
        params.setMargins(0, ManagerUtil.getStatusBarHeight(this), 0, 0);
        mTitleRelat.setLayoutParams(params);

        ivIcon = (ImageView) findViewById(R.id.user_head);
        ivBack = (ImageView) findViewById(R.id.back_img);
        ivSex = (ImageView) findViewById(R.id.ImageView_sex);
        tvFansNum = (TextView) findViewById(R.id.fensi_num_txt);
        tvConcerns = (TextView) findViewById(R.id.foucs_txt);
        tvFavorites = (TextView) findViewById(R.id.collect_num_txt);
        tvUserNickName = (TextView) findViewById(R.id.user_name);
        tvTitleUserNickName = (TextView) findViewById(R.id.user_info_nickname);
        tvIsAttention = (TextView) findViewById(R.id.user_is_attention);

        tvIsAttention.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.user_is_attention:
                UserConcernAsyn asyn = new UserConcernAsyn();

                if (resultBean.isConcerns()) {
                    asyn.execute("1");
                } else {
                    asyn.execute("0");

                }

                break;

            case R.id.back_img:
                UserInfoDetialActivity.this.finish();
                break;
        }

    }


    private class UserConcernAsyn extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {

            HashMap<String, String> map = new HashMap<>();
            map.put("Id", userId);
            map.put("Type", strings[0]); //0关注,1取消关注
            String message = HttpManager.newInstance().getHttpIndexData(token, map, HttpManager.USER_CONCERNS_URL);
            return message;
        }

        @Override
        protected void onPostExecute(String s) {


            super.onPostExecute(s);

            String message = s;
            if (!TextUtils.isEmpty(message) && !s.equals(HttpManager.FAILED)) {
                JSONObject object = null;

                try {
                    object = new JSONObject(message);
                    JSONObject objecthead = (JSONObject) object.get("Head");
                    userOuthreInfo = new UserOuthreInfo();

                    UserOuthreInfo.HeadBean headBean = new UserOuthreInfo.HeadBean();
                    headBean.setMsg(objecthead.getString("Msg"));
                    headBean.setStatus(objecthead.getInt("Status"));

                    userOuthreInfo.setHead(headBean);
                    if (headBean.getStatus() == 1) return;


                    if ("取消关注".equals(tvIsAttention.getText().toString())) {
                        tvIsAttention.setText("点击关注");
                        tvIsAttention.setTextColor(getResources().getColor(R.color.content_bg));
                        Toast.makeText(UserInfoDetialActivity.this, "取消关注", Toast.LENGTH_SHORT).show();
                    } else {
                        tvIsAttention.setText("取消关注");
                        tvIsAttention.setTextColor(getResources().getColor(R.color.text_two));
                        Toast.makeText(UserInfoDetialActivity.this, "已关注", Toast.LENGTH_SHORT).show();
                    }



                } catch (JSONException e) {
                    e.printStackTrace();
                }



                UserInfoAsyn asyn = new UserInfoAsyn();
                asyn.execute();
//                Gson gson = new Gson();
//                userOuthreInfo = gson.fromJson(message, UserOuthreInfo.class);   //获取用户信息
//                if (userOuthreInfo.getHead().getStatus() == 0) {
//                    if (userOuthreInfo.getResult().isConcerns()) {
//                        tvIsAttention.setText("关注");
//                        tvIsAttention.setTextColor(getResources().getColor(R.color.content_bg));
//                    } else {
//                        tvIsAttention.setText("取消关注");
//                    }
//                }
            }
        }
    }

    /**
     * 获取用户信息
     */
    private class UserInfoAsyn extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            HashMap<String, String> map = new HashMap<>();
//            map.put("Id", "");
            map.put("Id", userId);
            Log.d("UserInfoAsyn", "userid" + userId);
            String message = HttpManager.newInstance().getHttpIndexData(token, map, HttpManager.USERINFO_URL);
            return message;
        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            String message = s;
            Gson gson=new Gson();
            UserInfo userInfo = gson.fromJson(message, UserInfo.class);
            Log.i("用户详情",userInfo.toString());
            if (userInfo.getHead().getStatus()==0){
                MobclickAgent.onEvent(UserInfoDetialActivity.this,"Login");
                SharedPreferences sharedPreferences = ShapePreferenceManager.getMySharedPreferences(UserInfoDetialActivity.this);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(ShapePreferenceManager.TOKEN,userInfo.getResult().getToken());
                editor.putString(ShapePreferenceManager.USER_UID,userInfo.getResult().getId());
                editor.putString(ShapePreferenceManager.USER_NMAE,userInfo.getResult().getNickName());
                editor.putString(ShapePreferenceManager.PHONE,userInfo.getResult().getPhoneNum());
                editor.putString(ShapePreferenceManager.ID, userInfo.getResult().getId());
                editor.commit();
                //推送标签(别名)
//                        Set<String> set = new HashSet<>();
//                        set.add(userInfo.getResult().getPhoneNum());
                JPushInterface.setAlias(UserInfoDetialActivity.this, userInfo.getResult().getPhoneNum(), null);
                Toast.makeText(UserInfoDetialActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                UserInfoDetialActivity.this.finish();
            }else {
                Toast.makeText(UserInfoDetialActivity.this,userInfo.getHead().getMsg(),Toast.LENGTH_SHORT).show();
            }


            if (!TextUtils.isEmpty(message) && !s.equals(HttpManager.FAILED)) {


                UserOuthreInfo userOuthreInfo;
                JSONObject object = null;
                try {


                    object = new JSONObject(message);
                    JSONObject objecthead = (JSONObject) object.get("Head");
                    userOuthreInfo = new UserOuthreInfo();

                    UserOuthreInfo.HeadBean headBean = new UserOuthreInfo.HeadBean();
                    headBean.setMsg(objecthead.getString("Msg"));
                    headBean.setStatus(objecthead.getInt("Status"));

                    userOuthreInfo.setHead(headBean);
                    if (headBean.getStatus() == 1) return;

                    JSONObject objectResult = (JSONObject) object.get("Result");


                    resultBean = new UserOutherInfoBean();

                    resultBean.setId(objectResult.getString("Id"));
                    resultBean.setNickName(objectResult.getString("NickName"));
                    resultBean.setAvatar(objectResult.getString("Avatar"));
                    resultBean.setDesc(objectResult.getString("Desc"));
                    resultBean.setSex(objectResult.getInt("Sex"));
                    resultBean.setRcmdStock(objectResult.getInt("RcmdStock"));
                    resultBean.setConcerns(objectResult.getInt("Concerns"));
                    resultBean.setFans(objectResult.getInt("Fans"));
                    resultBean.setFavorites(objectResult.getInt("Favorites"));
                    resultBean.setCredit(objectResult.getInt("Credit"));
                    resultBean.setTotalCredit(objectResult.getInt("TotalCredit"));
                    resultBean.setAverageCumulativeReturn(objectResult.getInt("AverageCumulativeReturn"));
                    resultBean.setWinRate(objectResult.getInt("WinRate"));
                    resultBean.setConcerns(objectResult.getBoolean("IsConcerns"));


                    parseUserInfo(resultBean);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

//                Gson gson = new Gson();
//                userOuthreInfo = gson.fromJson(message, UserOuthreInfo.class);   //获取用户信息

//                Log.d("UserInfoAsyn", "用户信息"+userOuthreInfo.toString());
//                parseUserInfo();


            } else {
                Toast.makeText(getApplicationContext(), "获取信息失败", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void parseUserInfo(UserOutherInfoBean userOuthreInfo) {

        Picasso.with(this).load((String) userOuthreInfo.getAvatar()).into(ivIcon);
        tvFansNum.setText(userOuthreInfo.getFans() + "");
        tvConcerns.setText(userOuthreInfo.getConcerns() + "");
        tvFavorites.setText(userOuthreInfo.getFavorites() + "");
        tvUserNickName.setText(userOuthreInfo.getNickName());
        tvTitleUserNickName.setText(userOuthreInfo.getNickName());

        if (userOuthreInfo.getSex()==0){
            ivSex.setImageResource(R.mipmap.man);
        }else {
            ivSex.setImageResource(R.mipmap.woman);
        }
        if (userOuthreInfo.isConcerns()) {
            tvIsAttention.setText("取消关注");
            tvIsAttention.setTextColor(getResources().getColor(R.color.text_two));
        } else {
            tvIsAttention.setText("点击关注");
            tvIsAttention.setTextColor(getResources().getColor(R.color.content_bg));
        }


    }
}
