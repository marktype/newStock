package com.example.drawer.stockapp.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.drawer.stockapp.R;
import com.example.drawer.stockapp.utils.DensityUtils;
import com.example.drawer.stockapp.utils.ManagerUtil;
import com.readystatesoftware.systembartint.SystemBarTintManager;

/**
 *
 */
public class PayActivity extends BascActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        tintManager.setStatusBarTintResource(R.color.write_color);
        initWight();
    }

    public void initWight(){
        RelativeLayout mTitleRelat = (RelativeLayout) findViewById(R.id.group_title);    //title布局
        //设置距离顶部状态栏高度
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                DensityUtils.dp2px(this,50));
        params.setMargins(0, ManagerUtil.getStatusBarHeight(this),0,0);
        mTitleRelat.setLayoutParams(params);

        ImageView mBackimg = (ImageView) findViewById(R.id.back_img);
        mBackimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        TextView mPay = (TextView) findViewById(R.id.order_pay);
        View mPayWay = findViewById(R.id.pay_way);
        RelativeLayout mPriceRelat = (RelativeLayout) findViewById(R.id.price_relat);
        TextView mPrice = (TextView) findViewById(R.id.price_txt);
        if (Double.valueOf(mPrice.getText().toString()) == 0){
            mPriceRelat.setVisibility(View.GONE);
            mPay.setText("免费订阅");
            mPayWay.setVisibility(View.GONE);
            mPay.setWidth(ManagerUtil.getPetilWight(this));
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        SystemBarTintManager tintManager = ManagerUtil.newInstance(this);
        ManagerUtil.setStataBarColor(this,tintManager);
    }
}
