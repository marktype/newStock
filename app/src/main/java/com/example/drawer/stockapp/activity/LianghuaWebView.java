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
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.drawer.stockapp.R;
import com.example.drawer.stockapp.htttputil.HttpManager;
import com.example.drawer.stockapp.model.HeadIndex;
import com.example.drawer.stockapp.model.TiaoCangClass;
import com.example.drawer.stockapp.model.chiCangLieBiaoEntitySecond;
import com.example.drawer.stockapp.utils.PopWindow;
import com.example.drawer.stockapp.utils.ShapePreferenceManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Timer;

import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * Created by Administrator on 2017/3/15 0015.
 */

public class LianghuaWebView extends BascActivity implements View.OnClickListener {
    private WebView webView;
    private int zzuHeid;
    private RelativeLayout rlGentou;
    private RelativeLayout rlNiuRenBtnDismiss;
    private Button btn;
    private TextView tvName;
    private boolean zIsAlong;
    private String zname;
    private TextView tvOne, tvfive, tveight, tvTen;
    private EditText etMoney;
    private ImageView ivDismiss;
    private boolean zIsAlongTwo = false;
    private int zminM, zmaxM;
    private boolean zIsSmsNotic;
    private boolean IsSubscribe;
    private TextView tvShare;
    private String userNickName;
    private int typeNiur;
    private int adataerYype;
    private String MyZuHeType;
    private String wodezhuhename;
    private int wodezuhetype;
    private String wodezhuheId;
    private int paramId;
    private Boolean NIsSubscribe;
    private int Nid;
    private String NUserNickName;
    private int Ntype;
    private boolean adapterisIsMyPortfolio;
    private ImageView IvCancel;
    private double Preturn;
    private ImageView ivBack;
    private String lianghuacelue;
    private String niurenzuhe;
    private String token;
    private String wodezhuhedes;
    private ArrayList<chiCangLieBiaoEntitySecond.ResultBean> listdata;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        token = ShapePreferenceManager.getMySharedPreferences(this).getString(ShapePreferenceManager.TOKEN, null);
        setContentView(R.layout.lianghuawebview);
        initView();
        Intent intent = getIntent();
        //智能组合
        lianghuacelue = intent.getStringExtra("lianghuacelue");
        zname = intent.getStringExtra("zname");
        zzuHeid = intent.getIntExtra("zid", -1);
        zIsAlong = intent.getBooleanExtra("zIsAlong", false);
        zminM = intent.getIntExtra("zmin", -1);
        zmaxM = intent.getIntExtra("zmax", -1);
        zIsSmsNotic = intent.getBooleanExtra("zIsSmsNotic", true);
        Preturn = intent.getDoubleExtra("return", -1);
        //牛人组合
        niurenzuhe = intent.getStringExtra("niurenzuhe");
        NIsSubscribe = intent.getBooleanExtra("NIsSubscribe", false);
        Nid = intent.getIntExtra("Nid", -1);
        NUserNickName = intent.getStringExtra("NUserNickName");
        Ntype = intent.getIntExtra("Ntype", -1);
        //我的组合

        wodezhuhename = intent.getStringExtra("wodezhuhename");
        wodezuhetype = intent.getIntExtra("wodezuhetype", -1);
        wodezhuheId = intent.getStringExtra("wodezhuheId");
        wodezhuhedes = intent.getStringExtra("wodezhuheDes");
        adapterisIsMyPortfolio = intent.getBooleanExtra("adapterisIsMyPortfolio", true);
        listdata = (ArrayList<chiCangLieBiaoEntitySecond.ResultBean>) intent.getSerializableExtra("wodezuheList");
        adataerYype = intent.getIntExtra("adataerYype", -1);
        typeNiur = intent.getIntExtra("type", -1);
        MyZuHeType = intent.getStringExtra("MyZuHetype");
        userNickName = intent.getStringExtra("UserNickName");
        IsSubscribe = intent.getBooleanExtra("IsSubscribe", false);

        //智能组合跳转过来
        if (!TextUtils.isEmpty(lianghuacelue)) {
            IvCancel.setVisibility(View.GONE);
            etMoney.setHint("跟投范围" + zminM / 10000 + "万-" + zmaxM / 10000 + "万元(虚拟资金)");
            if (!TextUtils.isEmpty(zname)) {
                tvName.setText(zname);
                Log.i("是否跟投", zIsAlong + "");
                if (zIsAlong == true) {
                    btn.setText("查看我跟投的组合");
                }
            }
            if (zzuHeid != -1) {
                paramId = zzuHeid;
            }
        }


        ///牛人
        if (!TextUtils.isEmpty(niurenzuhe)) {
            if (Ntype == 3) {
                tvShare.setVisibility(View.VISIBLE);
                if (!TextUtils.isEmpty(String.valueOf(NIsSubscribe))) {
                    if (NIsSubscribe == true) {
                        btn.setText("已订阅");
                    } else {
                        btn.setText("点击订阅");
                    }
                }
            }
            if (NUserNickName != null) {
                tvName.setText(NUserNickName);
            }
            if (Nid != -1) {
                paramId = Nid;
            }
        }


        //我的组合 跳转过来的
        if (wodezuhetype != -1) {

            Log.i("订阅type", wodezuhetype + ".");
            btn.setText("立即调仓");
            tvName.setText(wodezhuhename);
            paramId = Integer.parseInt(wodezhuheId);
            Log.i("paramId", paramId + "");
            if (wodezuhetype == 3 && adapterisIsMyPortfolio == false) {
                btn.setVisibility(View.GONE);
            }
            tvShare.setVisibility(View.GONE);
            IvCancel.setVisibility(View.VISIBLE);

        }
        if (Ntype == 3) {
            Log.i("Ntype", Ntype + ".");
            tvShare.setVisibility(View.VISIBLE);
            IvCancel.setVisibility(View.GONE);
        }


//        //我的订阅
//        if(adataerYype!= -1) {
//            if (adataerYype == 3) {//订阅
//                Log.i("创建", adataerYype + "");
//                btn.setVisibility(View.GONE);
//            } else if (adataerYype == 2) {//创建
//                btn.setText("立即调仓");
//                Log.i("创建2", adataerYype + "");
//            }
//
//        }
        webView.clearCache(true);  //清除缓存

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadsImagesAutomatically(true);//auto load images
        webView.getSettings().setSupportZoom(false);
        webView.getSettings().setBuiltInZoomControls(false);//zoom
        webView.getSettings().setUseWideViewPort(false); //auto adjust screen
        webView.getSettings().setLoadWithOverviewMode(true);

        //设置Web视图
        webView.setWebViewClient(new webViewClient());
        webView.getSettings().setAllowFileAccess(true);//资源加载超时操作
        webView.setWebChromeClient(new WebChromeClientInfo());
        if (!TextUtils.isEmpty(token)) {
            webView.loadUrl("http://wechat.imaozhua.com/index.html#/public/groupDetails?Id=" + paramId + "&Token=" + token);
        } else {
            webView.loadUrl("http://wechat.imaozhua.com/index.html#/public/groupDetails?Id=" + paramId + "&Token=");
            Log.i("webView请求url", "http://wechat.imaozhua.com/index.html#/public/groupDetails?Id=" + paramId );

        }
    }


    private void initView() {
        tvName = (TextView) findViewById(R.id.tv_name);
        rlGentou = (RelativeLayout) findViewById(R.id.rl_gengtou);
        rlNiuRenBtnDismiss = (RelativeLayout) findViewById(R.id.relate_btntton);
        btn = (Button) findViewById(R.id.button);
        webView = (WebView) findViewById(R.id.web_view);
        tvOne = (TextView) findViewById(R.id.tv_1wan);
        tvfive = (TextView) findViewById(R.id.tv_5wan);
        tveight = (TextView) findViewById(R.id.tv_8wan);
        tvTen = (TextView) findViewById(R.id.tv_10wan);
        etMoney = (EditText) findViewById(R.id.et_money);
        ivDismiss = (ImageView) findViewById(R.id.iv_dismiss);
        tvShare = (TextView) findViewById(R.id.tv_share);
        IvCancel = (ImageView) findViewById(R.id.Iv_wenben);
        ivBack = (ImageView) findViewById(R.id.iv_back);


        ivBack.setOnClickListener(this);
        ivBack.setOnClickListener(this);
        IvCancel.setOnClickListener(this);
        tvShare.setOnClickListener(this);
        btn.setOnClickListener(this);
        tvOne.setOnClickListener(this);
        tvfive.setOnClickListener(this);
        tveight.setOnClickListener(this);
        tvTen.setOnClickListener(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!TextUtils.isEmpty(niurenzuhe)) {
            if (Ntype == 3) {
//            tvShare.setVisibility(View.VISIBLE);
                if (!TextUtils.isEmpty(String.valueOf(NIsSubscribe))) {
                    if (NIsSubscribe == true) {
                        btn.setText("已订阅");

                    } else {
                        btn.setText("点击订阅");

                    }
                }

            }

        }
    }

    private Timer timer;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_1wan:
                etMoney.setText("1");
                break;
            case R.id.tv_5wan:
                etMoney.setText("5");
                break;
            case R.id.tv_8wan:
                etMoney.setText("8");
                break;
            case R.id.tv_10wan:
                etMoney.setText("10");
                break;
            case R.id.iv_dismiss:
                rlGentou.setVisibility(View.GONE);
                break;
            case R.id.button:
                if (!TextUtils.isEmpty(niurenzuhe)) {
                    rlGentou.setBackgroundColor(getResources().getColor(R.color.write_color));
                    xiaDan dingyue = new xiaDan();
                    dingyue.execute();
                    return;
                }

                if (wodezuhetype == 2) {//创建
//                    Log.i("chuangjian", "onClick: "+"创建");
//                    List<StargDetial.ResultBean.HoldingDetailBean> hold =  starDetailInfo.getResult().getHoldingDetail();
                    ArrayList<HeadIndex> stockList = new ArrayList<>();
                    for (int i = 0; i < listdata.size(); i++) {
                        HeadIndex headIndex = new HeadIndex();
                        headIndex.setIndexName(listdata.get(i).getName());
                        headIndex.setIndexNum(listdata.get(i).getCode());
                        headIndex.setPrice(listdata.get(i).getPrice());
                        headIndex.setStockNum((int) listdata.get(i).getVolume());
                        headIndex.setType(1);    //标记为老仓位
                        stockList.add(headIndex);
                    }
                    TiaoCangClass tiaoCangClass = new TiaoCangClass();
                    tiaoCangClass.setStockID(wodezhuheId);
//                    tiaoCangClass.setTotalMoney(remainMoney);
                    tiaoCangClass.setTotalMoney(40);
                    tiaoCangClass.setName(wodezhuhename);
                    tiaoCangClass.setDesc(wodezhuhedes);
                    tiaoCangClass.setList(stockList);
                    Intent intent = new Intent(this, SetupZuHeActivity.class);
                    intent.putExtra(SetupZuHeActivity.TYPE, 1);     //调仓
                    intent.putExtra(SetupZuHeActivity.CHICANG_STOCK, tiaoCangClass);
                    intent.putExtra("wodezuheList", listdata);
                    startActivity(intent);
                    return;
                }

                if (zIsAlong == true) {//查看跟投详情
                    Intent intent = new Intent(LianghuaWebView.this, chaKanZuHe.class);
                    intent.putExtra("zuheId", zzuHeid);
                    intent.putExtra("zuheName", zname);
                    intent.putExtra("IsSmsNotic", zIsSmsNotic);
                    startActivity(intent);
                } else {
                    if (!TextUtils.isEmpty(token)) {

                        if (zname.contains("长征")){
                            Toast.makeText(this, "此组合暂未对外开放，请关注公众号imaozhua888，获取Vip权限", Toast.LENGTH_SHORT).show();
                        }else if (zIsAlongTwo == false) {

                            rlGentou.setVisibility(View.VISIBLE);
                            rlGentou.setBackgroundColor(getResources().getColor(R.color.write_color));
                            etMoney.getText().clear();
                            zIsAlongTwo = true;

                        } else {
                            String tvMoneyet = etMoney.getText().toString();
                            if (TextUtils.isEmpty(tvMoneyet)) {
                                Toast.makeText(this, "请输入跟投金额", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            liJiGenTou genTou = new liJiGenTou();
                            genTou.execute(tvMoneyet);
                            zIsAlongTwo = false;
                            rlGentou.setVisibility(View.GONE);
                        }
                    } else {
                        Toast.makeText(this, "请先登录", Toast.LENGTH_SHORT).show();
                    }
                    break;
                }
            case R.id.Iv_wenben:
                if (wodezuhetype == 3 && adapterisIsMyPortfolio == false) {
                    PopWindow popwindow = new PopWindow(this, "取消我的订阅", paramId, "1", "5");
                    popwindow.showPopupWindow(v);
                } else if (wodezuhetype != -1) {
                    //删除组合
                    PopWindow popwindow = new PopWindow(this, "删除组合", paramId, "1", "8");
                    popwindow.showPopupWindow(v);
                }

                break;
            case R.id.tv_share:
                if (!TextUtils.isEmpty(lianghuacelue)) {
                    String shareUrl = "http://wechat.imaozhua.com/index.html#/share/groupDetails?Id=" + paramId;
                    showShare(zname, shareUrl, "强烈推荐！我在爱猫爪发现一个收益" + Preturn + "%" + "的智能组合，炒股的朋友都打开看看");
                }
                if (!TextUtils.isEmpty(niurenzuhe)) {
                    String shareUrl = "http://wechat.imaozhua.com/index.html#/share/cattleDetails?Id=" + paramId;
                    showShare(zname, shareUrl, "强烈推荐！我在爱猫爪发现一个收益" + Preturn + "%" + "的智能组合，炒股的朋友都打开看看");
                }
                break;
            case R.id.iv_back:
                LianghuaWebView.this.finish();
                break;
        }

    }

    /**
     * 分享
     */
    private void showShare(String shareTxt, String shareUrl, String image) {
        Log.i("shareId", paramId + ".");
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        // 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle(shareTxt);
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl(shareUrl);

        // text是分享文本，所有平台都需要这个字段
        oks.setText("强烈推荐！我在爱猫爪发现一个收益" + Preturn + "%" + "的智能组合，炒股的朋友都打开看看");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        oks.setImagePath("/sdcard/icon.png");//确保SDcard下面存在此张图片
//        oks.setImageUrl(image);
//        oks.setImageUrl("http://p8.qhimg.com/dmfd/180_100_/t0131afbee1694751a9.jpg?size=400x222");
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl(shareUrl);
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl(shareUrl);

        // 启动分享GUI
        oks.show(this);
    }


    public class xiaDan extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            SharedPreferences share = ShapePreferenceManager.getMySharedPreferences(LianghuaWebView.this);
            String toeke = share.getString(ShapePreferenceManager.TOKEN, null);
            String reslut = HttpManager.newInstance().xiaDan(toeke, Nid + "", 5);
            return reslut;
        }

        @Override
        protected void onPostExecute(String s) {
            Log.i("一键下单返回数据", s.toString());
            super.onPostExecute(s);
            try {
                JSONObject object = new JSONObject(s);
                JSONObject head = object.getJSONObject("Head");
                int i = head.getInt("Status");
                if (i == 0) {
                    Toast.makeText(LianghuaWebView.this, "订阅成功", Toast.LENGTH_SHORT).show();
                    btn.setText("已订阅");
                } else {
                    Toast.makeText(LianghuaWebView.this, "" + head.getString("Msg"), Toast.LENGTH_SHORT).show();

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }


    public class liJiGenTou extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            String etMoneyP = params[0];
            double money = Double.parseDouble(etMoneyP);
            SharedPreferences shareP = ShapePreferenceManager.getMySharedPreferences(LianghuaWebView.this);
            String token = shareP.getString(ShapePreferenceManager.TOKEN, null);
            //String getTou =HttpManager().newInstance().liJiGenTou(token,HttpManager.LiJiGenTou,zuHeid,etMoney);
            String result = HttpManager.newInstance().liJiGenTou(token, HttpManager.LiJiGenTou, zzuHeid + "", money * 10000);
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            Log.i("立即跟投结果", s.toString());
            try {
                JSONObject object = new JSONObject(s);
                JSONObject head = object.getJSONObject("Head");
                int i = head.getInt("Status");
                if (i == 0) {
                    Toast.makeText(LianghuaWebView.this, "跟投成功", Toast.LENGTH_SHORT).show();
                    btn.setText("查看我跟投的组合");
                    zIsAlong = true;
                } else {
                    Toast.makeText(LianghuaWebView.this, "" + head.getString("Msg"), Toast.LENGTH_SHORT).show();

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            super.onPostExecute(s);
        }
    }


    /**
     * web加载进度
     */
    private class WebChromeClientInfo extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            if (newProgress >= 100) {

            }
        }

    }

    /**
     * /Web视图
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
//            TimerTask tt = new TimerTask() {
//                @Override
//                public void run() {
//                        /*
//                         * 超时后,首先判断页面加载进度,超时并且进度小于100,就执行超时后的动作
//                         */
//                    webView.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            if (LianghuaWebView.this.webView.getProgress() < 100) {
//                                Log.d("testTimeout", "timeout...........");
//                                Message msg = new Message();
//                                msg.what = 1;
//                                mHandler.sendMessage(msg);
//                                timer.cancel();
//                                timer.purge();
//                            }
//                        }
//                    });
//
//                }
//            };
//            timer.schedule(tt, timeout, 1);
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

    private long timeout = 1000;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    //这里对已经显示出页面且加载超时的情况不做处理
                    //dialog.dismiss();
                    break;

            }
        }
    };
}
