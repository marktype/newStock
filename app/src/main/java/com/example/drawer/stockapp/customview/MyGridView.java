package com.example.drawer.stockapp.customview;

import android.widget.GridView;

/**
 * Created by 欢大哥 on 2016/6/17.
 */
public class MyGridView extends GridView {
    public MyGridView(android.content.Context context, android.util.AttributeSet attrs)
    {
        super(context, attrs);
    }

    /**
     * 设置不滚动
     */
    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);

    }
}
