package com.example.drawer.stockapp.activity;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.drawer.stockapp.R;
import com.example.drawer.stockapp.customview.PagerSlidingTabStrip;
import com.example.drawer.stockapp.fragment.LoginByPasswordFragment;
import com.example.drawer.stockapp.fragment.LoginBySmsFragment;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.util.ArrayList;
import java.util.List;

public class NewLoginActivity extends  FragmentActivity implements View.OnClickListener {

    protected SystemBarTintManager tintManager;
    private ViewPager get_record_viewpager;
    private List<Fragment> fragmentList = new ArrayList<Fragment>();
    private GetRecordsPagerAdapter pagerAdapter;
    private PagerSlidingTabStrip get_record_tab;
    private LoginBySmsFragment loginBySmsFragment;
    private LoginByPasswordFragment loginByPasswordFragment;
    private DisplayMetrics dm; // 获取当前屏幕密度
    private ImageView ivBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }
//        tintManager = new SystemBarTintManager(this);
//        tintManager.setStatusBarTintEnabled(true);
        setContentView(R.layout.new_login_layout);
        initView();
    }

    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    private void initView() {

        ivBack = (ImageView) findViewById(R.id.back_img);
        get_record_viewpager = (ViewPager) this
                .findViewById(R.id.zixun_content_pager);
        get_record_tab = (PagerSlidingTabStrip) this
                .findViewById(R.id.first_group);
        dm = getResources().getDisplayMetrics();
        loginBySmsFragment = new LoginBySmsFragment();
        loginByPasswordFragment = new LoginByPasswordFragment();
        fragmentList.add(loginBySmsFragment);
        fragmentList.add(loginByPasswordFragment);
        pagerAdapter = new GetRecordsPagerAdapter(getSupportFragmentManager(),
                fragmentList);
        get_record_viewpager.setAdapter(pagerAdapter);
        get_record_tab.setViewPager(get_record_viewpager);
        setTabsValue();
        ivBack.setOnClickListener(this);
    }

    /**
     * 对PagerSlidingTabStrip的各项属性进行赋值。
     */
    private void setTabsValue() {
        // 设置Tab是自动填充满屏幕的
        get_record_tab.setShouldExpand(true);
        // 设置Tab的分割线是透明的
        get_record_tab.setDividerColor(Color.TRANSPARENT);
        // 设置Tab底部线的高度
        get_record_tab.setUnderlineHeight((int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 1, dm));
        // 设置Tab Indicator的高度
        get_record_tab.setIndicatorHeight((int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 1, dm));
        // 设置Tab标题文字的大小
        get_record_tab.setTextSize((int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_SP, 6, dm));
        // 设置Tab标题默认的颜色
        get_record_tab.setTextColor(getResources().getColor(
                R.color.write_color));
        // 设置选中Tab标题的颜色
        get_record_tab.setSelectedTextColor(getResources().getColor(
                R.color.write_color));
        // 设置Tab底部线的颜色
        get_record_tab.setUnderlineColor(getResources().getColor(
                R.color.write_color));
        // 设置Tab Indicator的颜色
        get_record_tab.setIndicatorColor(getResources().getColor(
                R.color.write_color));
        // 取消点击Tab时的背景色
        // get_record_tab.setTabBackground(getResources().getColor(R.color.tab_pressed_hover));
    }

    private PagerSlidingTabStrip tabs;
    private ViewPager mPager;

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back_img:
                NewLoginActivity.this.finish();
                break;
        }

    }


//    private void initWight(){
//        tabs = (PagerSlidingTabStrip) findViewById(R.id.first_group);
//        mPager = (ViewPager) findViewById(R.id.zixun_content_pager);   //viewpager
//
//    }




    public class GetRecordsPagerAdapter extends FragmentPagerAdapter {
        private final String[] titles = { "短信登录", "密码登录" };
        private List<Fragment> fragmentLists;

        @Override
        public CharSequence getPageTitle(int position) {
            // TODO Auto-generated method stub
            return titles[position];
        }

        public GetRecordsPagerAdapter(FragmentManager fm,
                                      List<Fragment> fragmentLists) {
            super(fm);
            this.fragmentLists = fragmentLists;
        }

        @Override
        public Fragment getItem(int position) {
            // TODO Auto-generated method stub
            return fragmentLists.get(position);
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return fragmentLists.size();
        }

    }
}
