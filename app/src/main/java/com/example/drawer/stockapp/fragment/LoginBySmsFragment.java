package com.example.drawer.stockapp.fragment;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.drawer.stockapp.R;
import com.example.drawer.stockapp.activity.ForgetPasswordActivity;
import com.example.drawer.stockapp.customview.MyDialog;
import com.example.drawer.stockapp.htttputil.HttpManager;
import com.example.drawer.stockapp.model.UserInfo;
import com.example.drawer.stockapp.utils.DensityUtils;
import com.example.drawer.stockapp.utils.ManagerUtil;
import com.example.drawer.stockapp.utils.ShapePreferenceManager;
import com.google.gson.Gson;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.umeng.analytics.MobclickAgent;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import cn.jpush.android.api.JPushInterface;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginBySmsFragment extends Fragment implements View.OnClickListener {

    private EditText mUserName,mPassWord;
    private TextView mLogin,getVerify;
    public static final String isFlishType = "com.stock.flishtype";   //是否是动态发送
    private Boolean isFinish = false;
    private ImageView ivWeiXinLogin;

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view  =      inflater.inflate(R.layout.fragment_login_by_sms, container, false);
        initWight();
        return view;
    }


    public void initWight(){
        mLogin = (TextView) view.findViewById(R.id.login_txt);
        mUserName = (EditText) view.findViewById(R.id.user_name_txt);
        mPassWord = (EditText) view.findViewById(R.id.password_txt);

        getVerify = (TextView) view.findViewById(R.id.get_verify);
        ivWeiXinLogin = (ImageView) view.findViewById(R.id.login_by_password_weixin);
        //设置距离顶部状态栏高度
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                DensityUtils.dp2px(getActivity(),50));
        params.setMargins(0, ManagerUtil.getStatusBarHeight(getActivity()),0,0);



        mLogin.setOnClickListener(this);
        ivWeiXinLogin.setOnClickListener(this);
        getVerify.setOnClickListener(this);

        /**
         * type广播（动态）
         */
        IntentFilter filter = new IntentFilter();
        // 向过滤器中添加action
        filter.addAction(isFlishType);
        // 注册广播
        getActivity().registerReceiver(isFlashBroad, filter);

    }

    /**
     * 广播接收者
     */
    private BroadcastReceiver isFlashBroad = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            isFinish = true;
        }

    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (isFlashBroad != null){
            getActivity().unregisterReceiver(isFlashBroad);
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        if (isFinish){
            getActivity().finish();
        }
        SystemBarTintManager tintManager = ManagerUtil.newInstance(getActivity());
        ManagerUtil.setStataBarColorBlack(getActivity(),tintManager);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login_txt:
                String name = mUserName.getText().toString();
                String password = mPassWord.getText().toString();
                if (name.length() != 11){
                    Toast.makeText(getActivity(),"电话号码写错了",Toast.LENGTH_SHORT).show();
                }else if (!TextUtils.isEmpty(name)&&!TextUtils.isEmpty(password)){
                    dialog = ManagerUtil.getDiaLog(getActivity());
                    LoginAsyn loginAsyn = new LoginAsyn();
                    loginAsyn.execute(name,password);
                }else {
                    Toast.makeText(getActivity(),"还没写密码哦",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.login_by_password_forget_password:
                Intent intent = new Intent(getActivity(),ForgetPasswordActivity.class);
                startActivity(intent);
                break;
            case R.id.get_verify:

                getVerify.setEnabled(false);
                String phoneV = mUserName.getText().toString();
                if (phoneV.length() == 11){
                    GetVerify getVerify = new GetVerify();
                    getVerify.execute(phoneV);
                }else {
                    getVerify.setEnabled(true);
                    Toast.makeText(getActivity(),"手机号码写错了",Toast.LENGTH_SHORT).show();
//                    TSnackbar.make(mGetVerify,"输入有误！",TSnackbar.LENGTH_SHORT).show();
                }

                break;
        }
    }


    private MyDialog dialog;
    /**
     * 异步登录接口
     */
    private class LoginAsyn extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... strings) {

            HashMap<String,String> map = new HashMap<>();
            map.put("UserName", strings[0]);
            map.put("Verification", strings[1]);
            String message = HttpManager.newInstance().getHttpIndexData("",map,HttpManager.RegistorLogin_URL);
            return message;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            dialog.dismiss();
            String message = s;
            if (!TextUtils.isEmpty(message)&&message.length()>10){
                Gson gson = new Gson();
                UserInfo userInfo = gson.fromJson(message,UserInfo.class);
                if (userInfo.getHead().getStatus()==0){
                    MobclickAgent.onEvent(getActivity(),"Login");
                    SharedPreferences sharedPreferences = ShapePreferenceManager.getMySharedPreferences(getActivity());
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
                    JPushInterface.setAlias(getActivity(), userInfo.getResult().getPhoneNum(), null);
                    Toast.makeText(getActivity(),"登录成功",Toast.LENGTH_SHORT).show();
                    getActivity().finish();
                }else {
                    Toast.makeText(getActivity(),userInfo.getHead().getMsg(),Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private class GetVerify extends AsyncTask<String,Integer,String> {

        @Override
        protected String doInBackground(String... strings) {
            HashMap<String,String> map = new HashMap<>();
            map.put("PhoneNum", strings[0]);
            String message = HttpManager.newInstance().getHttpIndexData("",map,HttpManager.LoginByPhoneCode_URL);
            return message;
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            String message = s;
            String stutas = null;
            String msg = null;
            try {
                JSONObject object = new JSONObject(message);
                JSONObject head = object.getJSONObject("Head");
                stutas = head.getString("Status");
                msg = head.getString("Msg");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (stutas.equals("1")){
                getVerify.setEnabled(true);
                Toast.makeText(getActivity(),msg,Toast.LENGTH_SHORT).show();
            }else {
                TimeCount time = new TimeCount(60000, 1000);
                time.start();// 开始计时
            }

        }
    }

    /**
     * 倒计时
     */
    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {// 计时完毕
            getVerify.setText("发送验证码");
            getVerify.setEnabled(true);
        }

        @Override
        public void onTick(long millisUntilFinished) {// 计时过程
            getVerify.setEnabled(false);
            getVerify.setText(millisUntilFinished / 1000+"秒后重新获取");
        }
    }

}
