package com.example.drawer.stockapp.activity;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.drawer.stockapp.R;
import com.example.drawer.stockapp.adapter.xiaoFeiJiLu;
import com.example.drawer.stockapp.model.xiaoFeiJilu;
import com.example.drawer.stockapp.utils.ShapePreferenceManager;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.example.drawer.stockapp.htttputil.HttpManager.BASE_URL;
import static com.example.drawer.stockapp.htttputil.HttpManager.FAILED;
import static com.example.drawer.stockapp.htttputil.HttpManager.JSON;
import static com.example.drawer.stockapp.htttputil.HttpManager.LANG;
import static com.example.drawer.stockapp.htttputil.HttpManager.Sign;
import static com.example.drawer.stockapp.htttputil.HttpManager.TimeStamp;
import static com.example.drawer.stockapp.htttputil.HttpManager.UID;

public class IntegeralHistoryActivity extends AppCompatActivity implements View.OnClickListener {

    private RelativeLayout relativeLayoutTitle;
    private String mToken;
    private String UserId;
    private ListView lvt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_integeral_history);
        SharedPreferences sp = ShapePreferenceManager.getMySharedPreferences(IntegeralHistoryActivity.this);
        UserId=sp.getString(ShapePreferenceManager.ID,null);

        mToken = sp.getString(ShapePreferenceManager.TOKEN, null);
        initView();
        tiJiao tiJiao=new tiJiao();
        tiJiao.execute();
    }
    private void initView(){
        relativeLayoutTitle = (RelativeLayout) findViewById(R.id.attention_title);
        relativeLayoutTitle.setBackgroundColor(getResources().getColor(R.color.write_color));
        ImageView ivBack = (ImageView) findViewById(R.id.back_img);
        lvt=(ListView)findViewById(R.id.integral_list);
        ivBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        IntegeralHistoryActivity.this.finish();
    }

    //
    public class tiJiao extends AsyncTask<String,Void,String> {
        @Override
        protected String doInBackground(String... params) {
            String buWeiM = getHttpDataByZuHe();
            return buWeiM;
        }

        @Override
        protected void onPostExecute(String s) {
            Gson gson=new Gson();
            xiaoFeiJilu entity = gson.fromJson(s.toString(), xiaoFeiJilu.class);
            if(entity.getHead().getStatus()==0){
                List<xiaoFeiJilu.ResultBean.DataBean> dataList = entity.getResult().getData();
                if(dataList!=null&&dataList.size()>0) {
                    xiaoFeiJiLu adapter = new xiaoFeiJiLu(IntegeralHistoryActivity.this, dataList);
                    lvt.setAdapter(adapter);
                }
            }else{
                Toast.makeText(IntegeralHistoryActivity.this, "获取数据失败", Toast.LENGTH_SHORT).show();
            }

        }
    }
        public String getHttpDataByZuHe() {
            JSONObject kk = new JSONObject();
            try {
                JSONObject object = new JSONObject();
                object.put("Uid", UID);
                object.put("Lang", LANG);
                object.put("Token",mToken);
                object.put("Sign", Sign);
                object.put("TimeStamp", TimeStamp);
                kk.put("Head", object);

                JSONObject param = new JSONObject();
                JSONObject pageInfo = new JSONObject();
                pageInfo.put("PageIndex", 0);
                pageInfo.put("PageCount", 0);
                pageInfo.put("PageSize", 0);
                param.put("PageInfo",pageInfo);

                param.put("Id",UserId+"");

                kk.put("Param",param);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String str = kk.toString();
            Log.d("组合", "组合" + str);
            RequestBody formBody = RequestBody.create(JSON, str);
            OkHttpClient mOkHttplient = new OkHttpClient();
            String url=BASE_URL+"Credit/CreditList";
            Request request = new Request.Builder()
                    .url(url)
                    .post(formBody)
                    .build();

            Response response = null;
            try {
                response = mOkHttplient.newCall(request).execute();
                if (response.isSuccessful()) {
                    String info = response.body().string();
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
    }