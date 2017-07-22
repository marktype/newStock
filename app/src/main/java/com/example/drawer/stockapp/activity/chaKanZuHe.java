package com.example.drawer.stockapp.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.drawer.stockapp.R;
import com.example.drawer.stockapp.htttputil.HttpManager;
import com.example.drawer.stockapp.utils.PopWindow;
import com.example.drawer.stockapp.utils.ShapePreferenceManager;

import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by Administrator on 2017/3/16 0016.
 */

public class chaKanZuHe extends BascActivity implements View.OnClickListener{
    private WebView webView;
    private TextView tvName;
    private int zuheId;
    private Button btnSheZhi;
    private String zuheName;
    private boolean IsSmsNotic;
    private Button btnXiaDan;
    private String wodezhuheId;
    private String wodezhuhename;
    private boolean MyZuHeIsSmsNotic;
    private ImageView BtnAnNiu;

    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_chakanzuhe);
        token = ShapePreferenceManager.getMySharedPreferences(this).getString(ShapePreferenceManager.TOKEN,null);
        initView();
        Intent intent = getIntent();
        zuheId = intent.getIntExtra("zuheId", -1);
        zuheName = intent.getStringExtra("zuheName");
        IsSmsNotic=intent.getBooleanExtra("IsSmsNotic",false);
        //我的组合条转过来的
        wodezhuheId=intent.getStringExtra("wodezhuheId");
        wodezhuhename=intent.getStringExtra("wodezhuhename");
        MyZuHeIsSmsNotic=intent.getBooleanExtra("MyZuHeIsSmsNotic",false);
        if(!TextUtils.isEmpty(wodezhuheId)){
            zuheId=Integer.parseInt(wodezhuheId);
            zuheName=wodezhuhename;
            IsSmsNotic=MyZuHeIsSmsNotic;
        }
        tvName.setText("跟投"+zuheName);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadsImagesAutomatically(true);//auto load images
        webView.getSettings().setSupportZoom(false);
        webView.getSettings().setBuiltInZoomControls(false);//zoom
        webView.getSettings().setUseWideViewPort(false); //auto adjust screen
        webView.getSettings().setLoadWithOverviewMode(true);

        //设置Web视图
        webView.setWebViewClient(new webViewClient());
        webView.getSettings().setAllowFileAccess(true);//资源加载超时操作

        SharedPreferences prefarence = ShapePreferenceManager.getMySharedPreferences(this);
        String toke = prefarence.getString(ShapePreferenceManager.TOKEN, null);

        if(!TextUtils.isEmpty(toke)){
            webView.loadUrl("http://wechat.imaozhua.com/index.html#/public/profitDetails?Id="+zuheId+"&Token="+toke);
            Log.i("url","http://wechat.imaozhua.com/index.html#/public/profitDetails?Id="+zuheId+"&Token="+toke);
        }else{
            webView.loadUrl("http://wechat.imaozhua.com/index.html#/public/profitDetails?Id="+zuheId+"&Token=");
            Log.i("url","http://wechat.imaozhua.com/index.html#/public/profitDetails?Id="+zuheId+"&Token="+toke);

        }
    }

    private void initView() {
        webView=(WebView)findViewById(R.id.web_view_chakanzuhe);
        tvName=(TextView)findViewById(R.id.tv_chakan_zuhe_name);
        ImageView iv_back_two = (ImageView) findViewById(R.id.iv_back_two);
        btnSheZhi=(Button)findViewById(R.id.btn_shezhi);
        btnXiaDan=(Button)findViewById(R.id.button2);
        BtnAnNiu=(ImageView)findViewById(R.id.btn_anniu);
        iv_back_two.setOnClickListener(this);
        BtnAnNiu.setOnClickListener(this);
        btnSheZhi.setOnClickListener(this);
        btnXiaDan.setOnClickListener(this);
    }

    private Timer timer;

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_shezhi:
                Intent intent=new Intent(chaKanZuHe.this,zuHeSheZhi.class);
                intent.putExtra("zuheName",zuheName);
                intent.putExtra("zuheId",zuheId);
                intent.putExtra("IsSmsNotic",IsSmsNotic);
                startActivity(intent);
                break;

            case R.id.button2:
                Toast.makeText(this, "我们的程序猿哥哥正在夜以继日的开发此功能，稍后才能开放此功能！建议您先使用其他软件完成交易\n", Toast.LENGTH_SHORT).show();
                xiaDan xiadan = new xiaDan();
                xiadan.execute();
                break;
            case R.id.btn_anniu:
                    PopWindow popwindow = new PopWindow(this,"取消我的跟投",zuheId,"1","0");
                    popwindow.showPopupWindow(v);
                break;
            case R.id.iv_back_two:
                chaKanZuHe.this.finish();
                break;
        }

    }

    /**
     *  /Web视图
     */
    private class webViewClient extends WebViewClient {
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            timer = new Timer();
            TimerTask tt = new TimerTask() {
                @Override
                public void run() {
                        /*
                         * 超时后,首先判断页面加载进度,超时并且进度小于100,就执行超时后的动作
                         */
                    webView.post(new Runnable() {
                        @Override
                        public void run() {
                            if (chaKanZuHe.this.webView.getProgress() < 100) {
                                Log.d("testTimeout", "timeout...........");
                                Message msg = new Message();
                                msg.what = 1;
                                mHandler.sendMessage(msg);
                                timer.cancel();
                                timer.purge();
                            }
                        }
                    });

                }
            };
            timer.schedule(tt, timeout, 1);
        }

        /**
         * onPageFinished指页面加载完成,完成后取消计时器
         */
        @Override
        public void onPageFinished(WebView view, String url) {
            // TODO Auto-generated method stub
            super.onPageFinished(view, url);
            timer.cancel();
            timer.purge();
        }

    }

    private long timeout = 10000;
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch(msg.what){
                case 1:
                    //这里对已经显示出页面且加载超时的情况不做处理
                    //dialog.dismiss();
                    break ;

            }
        }
    };
    public class xiaDan extends AsyncTask<String ,Void,String >{

        @Override
        protected String doInBackground(String... params) {
            SharedPreferences share = ShapePreferenceManager.getMySharedPreferences(chaKanZuHe.this);
            String toeke = share.getString(ShapePreferenceManager.TOKEN, null);
            String reslut = HttpManager.newInstance().xiaDan(toeke, zuheId + "",4);
            return reslut;
        }

        @Override
        protected void onPostExecute(String s) {
            Log.i("一键下单返回数据",s.toString());
            super.onPostExecute(s);
        }
    }
}
