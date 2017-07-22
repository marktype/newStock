package com.example.drawer.stockapp.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.drawer.stockapp.R;
import com.example.drawer.stockapp.adapter.GvPopupWindow;
import com.example.drawer.stockapp.htttputil.HttpManager;

import java.util.ArrayList;

public class PopWindow extends PopupWindow implements View.OnClickListener{
	private final GvPopupWindow adapter;
	private final SharedPreferences sp;
	private final String mToken;
	private ArrayList<String> list;
	private View conentView; //存放popupwindow的布局
	private TextView gv_popupWindow;
	private Context context;
	private String message;
	private int paramId;
	private String Option;
	private String Type;


	public PopWindow(final Activity context,String s,int paramId,String Option,String Type) {
		//TODO 通过构造方法 获取传递过来的开发商，小区数据集合，并给成员变量赋值
		this.context=context;
		this.message=s;
		this.paramId=paramId;
		this.Option = Option;
		this.Type = Type;

		conentView = LayoutInflater.from(context).inflate(R.layout.popwindow,null);
		gv_popupWindow=(TextView)conentView.findViewById(R.id.popwindow);
		sp = ShapePreferenceManager.getMySharedPreferences(context);
		mToken = sp.getString(ShapePreferenceManager.TOKEN,null);

		gv_popupWindow.setOnClickListener(this);
		gv_popupWindow.setText(s);
		//给girdview设置适配器
		adapter = new GvPopupWindow(context,list);
		//  获取context窗口的宽高；
		int h = context.getWindowManager().getDefaultDisplay().getHeight();
		int w = context.getWindowManager().getDefaultDisplay().getWidth();
		// 设置SelectPicPopupWindow的View
		this.setContentView(conentView);
		// 设置SelectPicPopupWindow弹出窗体的宽
		this.setWidth(LayoutParams.MATCH_PARENT);
		// 设置SelectPicPopupWindow弹出窗体的高
		this.setHeight(LayoutParams.WRAP_CONTENT);
		// 设置SelectPicPopupWindow弹出窗体可点击
		this.setFocusable(true);
		// 当前窗体外围可触摸，加这个貌似没什么效果，这里可以不加；
		this.setOutsideTouchable(true);
		// 刷新状态，加或者不加并没有什么影响。
 		this.update();
		// 实例化一个ColorDrawable颜色为半透明
		ColorDrawable dw = new ColorDrawable(0000000000);
		// 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
		this.setBackgroundDrawable(dw);
	}

	public void showPopupWindow(View parent) {
		if (!this.isShowing()) {
			this.showAsDropDown(parent, 0, 5);// 出现在父控件正左下方，x、y方向有偏移；
		} else {
			this.dismiss();
		}
	}

	@Override
	public void onClick(View v) {
		AlertDialog.Builder dialog=new AlertDialog.Builder(context);
		dialog.setMessage("你确定要"+message+"吗?");
		dialog.setTitle("注意");
		dialog.setNegativeButton("确定", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				//发请求
				queding quding = new queding();
				quding.execute();
				Activity activity = (Activity) context;
				activity.finish();
			}
		});
		dialog.setPositiveButton("取消", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				PopWindow.this.dismiss();
			}
		});
		dialog.create().show();
	}
	private class queding extends AsyncTask<String ,Void,String>{

		@Override
		protected String doInBackground(String... params) {
			String result = HttpManager.newInstance().PopwindowQueDing(mToken, paramId,Option,Type);
			return result;
		}

		@Override
		protected void onPostExecute(String s) {
			Log.i("rsutl",s);
		}
	}
}
