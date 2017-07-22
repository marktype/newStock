package com.example.drawer.stockapp.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.drawer.stockapp.R;
import com.example.drawer.stockapp.adapter.UserAttentionAdapter;
import com.example.drawer.stockapp.customview.view.XListView;
import com.example.drawer.stockapp.htttputil.HttpManager;
import com.example.drawer.stockapp.model.UserAttention;
import com.example.drawer.stockapp.model.UserAttentionBean;
import com.example.drawer.stockapp.utils.DensityUtils;
import com.example.drawer.stockapp.utils.ManagerUtil;
import com.example.drawer.stockapp.utils.ShapePreferenceManager;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AttentionActivity extends BascActivity {


    public static final String USER_ID = "userId";
    private XListView xListView;
    private List<UserAttention.ResultBean.DataBean> userAttention;
    private UserAttentionAdapter adapter;
    private int page=0;
    private String mToken;
    private TextView tvTitle;
    private String id;
    private int type;//0是关注列表,1粉丝列表
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attention);
        tintManager.setStatusBarTintResource(R.color.write_color);
        mToken = ShapePreferenceManager.getMySharedPreferences(this).getString(ShapePreferenceManager.TOKEN,"");
//        id = ShapePreferenceManager.getMySharedPreferences(this).getString(ShapePreferenceManager.ID,"");
        id = getIntent().getStringExtra(USER_ID);
        type = getIntent().getIntExtra("type",0);
        initWight();
        if (type==0){
            tvTitle.setText("关注列表");
        }else {
            tvTitle.setText("粉丝列表");

        }
//        UserAttentionAsyn userAttentionAsyn = new UserAttentionAsyn();
//        userAttentionAsyn.execute();
    }

    public void initWight(){
        RelativeLayout mTitleRelat = (RelativeLayout) findViewById(R.id.attention_title);    //title布局
        //设置距离顶部状态栏高度
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                DensityUtils.dp2px(this,50));
        params.setMargins(0, ManagerUtil.getStatusBarHeight(this),0,0);
        mTitleRelat.setLayoutParams(params);

        ImageView mBackImg = (ImageView) findViewById(R.id.back_img);
        mBackImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        tvTitle = (TextView) findViewById(R.id.back_txt);
        xListView = (XListView) findViewById(R.id.attention_list);

        adapter = new UserAttentionAdapter(this);
        adapter.setData(setData());
        xListView.setAdapter(adapter);

        UserAttentionAsyn asyn = new UserAttentionAsyn();
        asyn.execute("0");

    }

    public ArrayList<UserAttentionBean> setData(){
        ArrayList<UserAttentionBean> list = new ArrayList<>();

        return list;
    }


    private class UserAttentionAsyn extends AsyncTask<String,Void,String> {
        @Override
        protected String doInBackground(String... strings) {
            HashMap<String,String> map = new HashMap<>();
            map.put("PageIndex", strings[0]);
            map.put("PageCount", "0");
            map.put("PageSize", "0");
            String message;
            if (type ==0){

                 message = HttpManager.newInstance().getHttpDataByFiveLayer(mToken,map,HttpManager.USER_CONCERNS_LIST_URL,id);
            }else{
                 message = HttpManager.newInstance().getHttpDataByFiveLayer(mToken,map,HttpManager.USER_FANS_LIST_URL,id);

            }

            return message;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (!TextUtils.isEmpty(s)&&s.length()>10){
//                Gson gson = new Gson();
//                userAttention = gson.fromJson(s,UserAttention.class);

                try {
                    JSONObject jsonObject = new JSONObject(s);
                    JSONObject headObj = jsonObject.getJSONObject("Head");

                    UserAttention.HeadBean headBean = new UserAttention.HeadBean();
                    headBean.setStatus(headObj.getInt("Status"));
                    if (headBean.getStatus()!=0)return;

                    JSONObject resultObj = jsonObject.getJSONObject("Result");
                    JSONArray dataObj = resultObj.getJSONArray("Data");
                    userAttention = new ArrayList<>();


                    for (int i = 0; i <dataObj.length(); i++) {

                        JSONObject data = dataObj.getJSONObject(i);
                        UserAttention.ResultBean.DataBean dataBean = new UserAttention.ResultBean.DataBean();

                        dataBean.setId(data.getString("Id"));
                        dataBean.setNickName(data.getString("NickName"));
                        dataBean.setAvatar(data.getString("Avatar"));
                        dataBean.setCreateTime(data.getString("CreateTime"));
                        dataBean.setConcerns(data.getBoolean("IsConcerns"));
                        dataBean.setAverageCumulativeReturn(data.getDouble("AverageCumulativeReturn"));
                        dataBean.setWinRate(data.getDouble("WinRate"));
                        dataBean.setRcmdStock(data.getInt("RcmdStock"));
                        userAttention.add(dataBean);

                    }


                    parseData(userAttention);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }else {
            }
        }
    }

    private void parseData(List<UserAttention.ResultBean.DataBean> userAttention){

            List<UserAttention.ResultBean.DataBean> attention = userAttention;
//            List<XueTangInfo.ResultBean.CoursesBean> Courses = findInfo.getResult().getCourses();//以前的
            ArrayList<UserAttentionBean> list = new ArrayList<>();

            for (int i = 0;i<attention.size();i++){
                UserAttentionBean userAttentionBean = new UserAttentionBean();
                UserAttention.ResultBean.DataBean dataBean = attention.get(i);
                userAttentionBean.setAvatar(dataBean.getAvatar());
                userAttentionBean.setNickName(dataBean.getNickName());
                userAttentionBean.setConcerns(dataBean.isConcerns());
                list.add(userAttentionBean);
            }
            if (page == 0){
                adapter.setData(list);
                xListView.setAdapter(adapter);
            }else if (page >0&&list.size()>0){
//                listSave.addAll(list);
                adapter.addData(list);
            }else {
                Toast.makeText(this,"没有更多了哦",Toast.LENGTH_SHORT).show();
            }

    }
    @Override
    protected void onResume() {
        super.onResume();
        SystemBarTintManager tintManager = ManagerUtil.newInstance(this);
        ManagerUtil.setStataBarColor(this,tintManager);
    }
}
