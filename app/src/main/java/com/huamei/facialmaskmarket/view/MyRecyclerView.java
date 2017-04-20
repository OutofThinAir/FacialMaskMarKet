package com.huamei.facialmaskmarket.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Use:
 * Author:陈懿鹏
 * Data:2017/4/17.
 */

public class MyRecyclerView extends RecyclerView {
    public MyRecyclerView(Context context) {
        super(context);
    }

    public MyRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {
        //设置为Integer.MAX_VALUE>>2 是listview全部展开
        int measureSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE>>2, MeasureSpec.AT_MOST);
//设置为400是设置listview的高度只能有400 不全部展开   实现可以滑动的效果
        int measureSpec1 = MeasureSpec.makeMeasureSpec(400, MeasureSpec.AT_MOST);
        super.onMeasure(widthSpec, measureSpec);
    }
}
