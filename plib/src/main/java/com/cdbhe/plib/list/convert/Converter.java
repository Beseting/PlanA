package com.cdbhe.plib.list.convert;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.IdRes;
import androidx.annotation.StringRes;
import android.util.SparseArray;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Checkable;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.kevin.photo_browse.utils.GlideHelper;

public class Converter {
    private View mConvertView;
    private SparseArray<View> mViews;

    public Converter(View convertView) {
        mViews = new SparseArray<>();
        this.mConvertView = convertView;
    }

    public static Converter get(View convertView) {
        Converter converter = (Converter) convertView.getTag();
        if (converter == null) {
            converter = new Converter(convertView);
            convertView.setTag(converter);
        }
        return converter;
    }

    /**
     * 根据viewId获取View视图实例
     *
     * @param viewId
     * @return
     */
    private <T extends View> T getView(int viewId) {
        View childView = mViews.get(viewId);
        if (childView == null) {
            childView = mConvertView.findViewById(viewId);
            mViews.put(viewId, childView);
        }
        return (T) childView;
    }

    /**
     * 设置控件显示隐藏
     * @param viewId
     * @param visible
     * @return
     */
    public Converter setVisible(@IdRes int viewId, boolean visible) {
        View view = getView(viewId);
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
        return this;
    }

    /**
     * 设置文本
     * @param viewId
     * @param value
     * @return
     */
    public Converter setText(@IdRes int viewId, CharSequence value) {
        TextView view = getView(viewId);
        view.setText(value);
        return this;
    }

    /**
     * 设置文本
     * @param viewId
     * @param strId
     * @return
     */
    public Converter setText(@IdRes int viewId, @StringRes int strId) {
        TextView view = getView(viewId);
        view.setText(strId);
        return this;
    }

    /**
     * 设置文字颜色
     * @param viewId
     * @param textColor
     * @return
     */
    public Converter setTextColor(@IdRes int viewId, @ColorInt int textColor) {
        TextView view = getView(viewId);
        view.setTextColor(textColor);
        return this;
    }

    /**
     * 给指定viewId的ImageView控件设置资源图片
     *
     * @param viewId
     * @param resId
     * @return
     */
    public Converter setImageResource(@IdRes int viewId, int resId) {
        ImageView iv = getView(viewId);
        iv.setImageResource(resId);
        return this;
    }

    /**
     * 给指定viewId的ImageView控件设置网络图片
     *
     * @param viewId
     * @param url
     * @return
     */
    public Converter setImageResource(@IdRes int viewId, String url) {
        ImageView iv = getView(viewId);
        GlideHelper.load(mConvertView.getContext(),url,iv);
        return this;
    }

    /**
     * 设置适配器
     * @param viewId
     * @param adapter
     * @return
     */
    public Converter setAdapter(@IdRes int viewId, Adapter adapter) {
        AdapterView view = getView(viewId);
        view.setAdapter(adapter);
        return this;
    }

    /**
     * 设置控件Alpha值 不允许重复调用
     * Alpha 范围 0-1.
     * @param viewId
     * @param value
     * @return
     */
    public Converter setAlpha(@IdRes int viewId, float value) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            getView(viewId).setAlpha(value);
        } else {
            AlphaAnimation alpha = new AlphaAnimation(value, value);
            alpha.setDuration(0);
            alpha.setFillAfter(true);
            getView(viewId).startAnimation(alpha);
        }
        return this;
    }

    /**
     * 设置控件的背景色
     * @param viewId
     * @param color
     * @return
     */
    public Converter setBackgroundColor(@IdRes int viewId, @ColorInt int color) {
        View view = getView(viewId);
        view.setBackgroundColor(color);
        return this;
    }

    /**
     * 设置控件背景资源
     * @param viewId
     * @param backgroundRes
     * @return
     */
    public Converter setBackgroundRes(@IdRes int viewId, @DrawableRes int backgroundRes) {
        View view = getView(viewId);
        view.setBackgroundResource(backgroundRes);
        return this;
    }

    /**
     * 设置控件选中
     * @param viewId
     * @param checked
     * @return
     */
    public Converter setChecked(@IdRes int viewId, boolean checked) {
        View view = getView(viewId);
        if (view instanceof Checkable) {
            ((Checkable) view).setChecked(checked);
        }
        return this;
    }

    /**
     * 设置图片控件图片显示
     * @param viewId
     * @param drawable
     * @return
     */
    public Converter setImageDrawable(@IdRes int viewId, Drawable drawable) {
        ImageView view = getView(viewId);
        view.setImageDrawable(drawable);
        return this;
    }

    /**
     * 设置图片控件图片显示
     * @param viewId
     * @param bitmap
     * @return
     */
    public Converter setImageBitmap(@IdRes int viewId, Bitmap bitmap) {
        ImageView view = getView(viewId);
        view.setImageBitmap(bitmap);
        return this;
    }

    /**
     * 设置进度
     * @param viewId
     * @param progress
     * @return
     */
    public Converter setProgress(@IdRes int viewId, int progress) {
        ProgressBar view = getView(viewId);
        view.setProgress(progress);
        return this;
    }

    /**
     * 设置进度
     * @param viewId
     * @param progress
     * @param max
     * @return
     */
    public Converter setProgress(@IdRes int viewId, int progress, int max) {
        ProgressBar view = getView(viewId);
        view.setMax(max);
        view.setProgress(progress);
        return this;
    }

    /**
     * 设置RatingBar的rating
     * @param viewId
     * @param rating
     * @return
     */
    public Converter setRating(@IdRes int viewId, float rating) {
        RatingBar view = getView(viewId);
        view.setRating(rating);
        return this;
    }

    /**
     * 设置RatingBar的rating
     * @param viewId
     * @param rating
     * @param max
     * @return
     */
    public Converter setRating(@IdRes int viewId, float rating, int max) {
        RatingBar view = getView(viewId);
        view.setMax(max);
        view.setRating(rating);
        return this;
    }

    /**
     * 设置checked事件
     * @param viewId
     * @param listener
     * @return
     */
    public Converter setOnCheckedChangeListener(@IdRes int viewId, CompoundButton.OnCheckedChangeListener listener) {
        CompoundButton view = getView(viewId);
        view.setOnCheckedChangeListener(listener);
        return this;
    }

    /**
     * 设置点击事件
     * @param viewId
     * @param listener
     * @return
     */
    public Converter setOnClickListener(@IdRes int viewId, View.OnClickListener listener) {
        View view = getView(viewId);
        view.setOnClickListener(listener);
        return this;
    }

    /**
     * 设置长按事件
     * @param viewId
     * @param listener
     * @return
     */
    public Converter setOnLongClickListener(@IdRes int viewId, View.OnLongClickListener listener) {
        View view = getView(viewId);
        view.setOnLongClickListener(listener);
        return this;
    }
}
