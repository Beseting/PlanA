package com.cdbhe.plib.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by Kevin on 2017/10/17.
 */

public class GridViewWithBorder extends GridView {
    public GridViewWithBorder(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GridViewWithBorder(Context context) {
        super(context);
    }

    public GridViewWithBorder(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
