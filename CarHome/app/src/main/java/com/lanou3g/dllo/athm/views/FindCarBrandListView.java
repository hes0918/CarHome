package com.lanou3g.dllo.athm.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;

/**
 * Created by dllo on 16/9/18.
 * 自定义listView 解决滑动冲突
 */
public class FindCarBrandListView extends ListView {

    public FindCarBrandListView(Context context) {
        super(context);
    }

    public FindCarBrandListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FindCarBrandListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_MOVE) {
            return true;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

}
