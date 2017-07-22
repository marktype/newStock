package com.example.drawer.stockapp.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.drawer.stockapp.R;
import com.example.drawer.stockapp.htttputil.HttpManager;
import com.example.drawer.stockapp.utils.ShapePreferenceManager;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2017/3/16 0016.
 */

public class zuHeSheZhi extends BascActivity implements View.OnClickListener{
    private TextView tvGenTou;
    private String zuheName;
    private EditText etZuheShezhi;
    private String id;
    private boolean IsSmsNotic;
    private Button btnSheZhi;
    private String tvGen;
    private String scS;
    private SwitchCompat scSms;
    private String zuheId;
    private boolean IsSmsNoticMoren;
    private Boolean TextOnAndOff=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zuheshezhi);
        initView();
        Intent intent = getIntent();
        if(intent!=null){
            zuheName = intent.getStringExtra("zuheName");
            int zuId = intent.getIntExtra("zuheId",-1);
            IsSmsNoticMoren=intent.getBooleanExtra("IsSmsNotic",false);
            zuheId=zuId+"";
            tvGenTou.setText(zuheName);
        }
        etZuheShezhi.setHint("跟投组合_"+zuheName);

    }

    private void initView() {
        tvGenTou=(TextView)findViewById(R.id.tv_gentou_name);
        etZuheShezhi=(EditText)findViewById(R.id.et_zuheshezhi);
        scSms=(SwitchCompat)findViewById(R.id.sc_sms);
        btnSheZhi=(Button)findViewById(R.id.btn_baocun_shezhi);
        scSms.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                switch (buttonView.getId()){
                    case R.id.sc_sms:
                        if(isChecked){
                            TextOnAndOff=true;
                        }else{
                            TextOnAndOff=false;
                        }
                        break;
                }
            }
        });
        btnSheZhi.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        tvGen = etZuheShezhi.getText().toString();
        String tex = scSms.getText().toString();
        IsSmsNotic = Boolean.parseBoolean(scS);
        if(TextUtils.isEmpty(tvGen)&&TextOnAndOff==false){
            Log.i("没改动时参数",zuheName+IsSmsNoticMoren);
            UpdateMessage message = new UpdateMessage();
            message.execute(zuheName,IsSmsNoticMoren+"");
        }else{
            Log.i("改动了的参数",tvGen+scS);
            if(TextUtils.isEmpty(tvGen)){
                UpdateMessage message = new UpdateMessage();
                message.execute(zuheName,scS);
            }else{
                UpdateMessage message = new UpdateMessage();
                message.execute(tvGen,scS);
            }
        }
    }

    public class UpdateMessage extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... params) {
            SharedPreferences share = ShapePreferenceManager.getMySharedPreferences(zuHeSheZhi.this);
            String toke = share.getString(ShapePreferenceManager.TOKEN, null);
            String paramtvGen = params[0];
            String isSmsN = params[1];
            Log.i("参数",paramtvGen+isSmsN);
            String result = HttpManager.newInstance().upteMessage(toke, zuheId, paramtvGen, Boolean.parseBoolean(isSmsN));
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
//            Log.i("结果！！！！！",s.toString());
            try {
                JSONObject object = new JSONObject(s);
                JSONObject JsonObject = object.getJSONObject("Head");
                int i = JsonObject.getInt("Status");
                if(i==0){
                    Toast.makeText(zuHeSheZhi.this, ""+JsonObject.getString("Msg"), Toast.LENGTH_SHORT).show();
                    zuHeSheZhi.this.finish();
                }else{
                    Toast.makeText(zuHeSheZhi.this, ""+JsonObject.get("Msg"), Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            super.onPostExecute(s);
        }
    }
}
