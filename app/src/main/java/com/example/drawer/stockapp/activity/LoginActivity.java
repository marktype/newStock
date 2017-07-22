package com.example.drawer.stockapp.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.drawer.stockapp.R;
import com.example.drawer.stockapp.customview.MyDialog;
import com.example.drawer.stockapp.htttputil.HttpManager;
import com.example.drawer.stockapp.model.UserInfo;
import com.example.drawer.stockapp.utils.DensityUtils;
import com.example.drawer.stockapp.utils.ManagerUtil;
import com.example.drawer.stockapp.utils.ShapePreferenceManager;
import com.google.gson.Gson;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.umeng.analytics.MobclickAgent;

import java.util.HashMap;

import cn.jpush.android.api.JPushInterface;

public class LoginActivity extends BascActivity implements View.OnClickListener{
    private EditText mUserName,mPassWord;
    private TextView mLogin;
    public static final String isFlishType = "com.stock.flishtype";   //是否是动态发送
    private Boolean isFinish = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        tintManager.setStatusBarTintResource(android.R.color.transparent);
        initWight();
    }

    public void initWight(){
        TextView mRegister = (TextView) findViewById(R.id.register_txt);
        mLogin = (TextView) findViewById(R.id.login_txt);
        TextView mForgetPassword = (TextView) findViewById(R.id.forget_password_txt);
        ImageView mBackimg = (ImageView) findViewById(R.id.back_img);
        mUserName = (EditText) findViewById(R.id.user_name_txt);
        mPassWord = (EditText) findViewById(R.id.password_txt);
        ImageView mEyeImg = (ImageView) findViewById(R.id.eye_img);

        RelativeLayout mTitleRelat = (RelativeLayout) findViewById(R.id.login_relat);    //title布局
        //设置距离顶部状态栏高度
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                DensityUtils.dp2px(this,50));
        params.setMargins(0, ManagerUtil.getStatusBarHeight(this),0,0);
        mTitleRelat.setLayoutParams(params);



        mForgetPassword.setOnClickListener(this);
        mLogin.setOnClickListener(this);
        mRegister.setOnClickListener(this);
        mBackimg.setOnClickListener(this);
        mEyeImg.setOnClickListener(this);

        /**
         * type广播（动态）
         */
        IntentFilter filter = new IntentFilter();
        // 向过滤器中添加action
        filter.addAction(isFlishType);
        // 注册广播
        registerReceiver(isFlashBroad, filter);

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
            unregisterReceiver(isFlashBroad);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isFinish){
            finish();
        }
        SystemBarTintManager tintManager = ManagerUtil.newInstance(this);
        ManagerUtil.setStataBarColorBlack(this,tintManager);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.register_txt:
                Intent intent = new Intent(this,RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.login_txt:
                String name = mUserName.getText().toString();
                String password = mPassWord.getText().toString();
                if (name.length() != 11){
                    Toast.makeText(getApplicationContext(),"电话号码写错了",Toast.LENGTH_SHORT).show();
                }else if (!TextUtils.isEmpty(name)&&!TextUtils.isEmpty(password)){
                    dialog = ManagerUtil.getDiaLog(this);
                    LoginAsyn loginAsyn = new LoginAsyn();
                    loginAsyn.execute(name,password);
                }else {
                    Toast.makeText(getApplicationContext(),"还没写密码哦",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.forget_password_txt:
                intent = new Intent(this,ForgetPasswordActivity.class);
                startActivity(intent);
                break;
            case R.id.back_img:
                finish();
                break;
            case R.id.eye_img:
                if (mPassWord.getInputType() == InputType.TYPE_TEXT_VARIATION_PASSWORD){
                    mPassWord.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }else {
                    mPassWord.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
                break;
        }
    }

    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        super.onBackPressed();
//        in.setAction("com.ymhd.select");
//        sendBroadcast(in);
//        finish();
//        Intent intent = new Intent(this, MainActivity.class);
//        MainActivity.isFirst = true;
//        startActivity(intent);
        finish();
    }

    private MyDialog dialog;
    /**
     * 异步登录接口
     */
        private class LoginAsyn extends AsyncTask<String,Void,String>{

            @Override
            protected String doInBackground(String... strings) {

                HashMap<String,String> map = new HashMap<>();
                map.put("UserName", strings[0]);
                map.put("Password", strings[1]);
                String message = HttpManager.newInstance().getHttpIndexData("",map,HttpManager.Login_URL);
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
                    Log.i("LoginActivity",userInfo.toString());
                    if (userInfo.getHead().getStatus()==0){
                        MobclickAgent.onEvent(getApplicationContext(),"Login");
                        SharedPreferences sharedPreferences = ShapePreferenceManager.getMySharedPreferences(LoginActivity.this);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(ShapePreferenceManager.TOKEN,userInfo.getResult().getToken());
                        editor.putString(ShapePreferenceManager.USER_UID,userInfo.getResult().getId());
                        editor.putString(ShapePreferenceManager.USER_NMAE,userInfo.getResult().getNickName());
                        editor.putString(ShapePreferenceManager.PHONE,userInfo.getResult().getPhoneNum());
                        editor.commit();
                        //推送标签(别名)
//                        Set<String> set = new HashSet<>();
//                        set.add(userInfo.getResult().getPhoneNum());
                        JPushInterface.setAlias(LoginActivity.this, userInfo.getResult().getPhoneNum(), null);
                        Toast.makeText(getApplicationContext(),"登录成功",Toast.LENGTH_SHORT).show();
                        finish();
                    }else {
                        Toast.makeText(getApplicationContext(),userInfo.getHead().getMsg(),Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }

}
