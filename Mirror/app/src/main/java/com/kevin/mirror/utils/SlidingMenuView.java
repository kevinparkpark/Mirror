package com.kevin.mirror.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.HorizontalScrollView;
import android.widget.TextView;

import com.kevin.mirror.R;

/**
 * Created by dllo on 16/7/1.
 */
public class SlidingMenuView extends HorizontalScrollView {

    private SlidingListener slidingListener;
    private int mScrollWidth;
    boolean isOpen = false;//记录菜单的打开或关闭

    public void setSlidingListener(SlidingListener slidingListener) {
        this.slidingListener = slidingListener;
    }

    public SlidingMenuView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //让菜单的每一个横向滚动的跟随线都消失
        setHorizontalScrollBarEnabled(false);
        isOpen = false;//默认这个菜单是关闭状态
    }

    //该方法是用于放置子视图的位置调用的
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        //拿到菜单这个textView的宽度
        TextView textView = (TextView) findViewById(R.id.delete_item);
        mScrollWidth = textView.getWidth();
    }

    public void closeMenu() {
        //把滚动条滚动到0,0的位置 就相当于把它关上了
        smoothScrollTo(0, 0);
        isOpen = false;
    }

    //当用户点击或滑动
    //作用在这个View上的时候 就会回调这个方法
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_MOVE:
                slidingListener.onMove(this);
                break;
            case MotionEvent.ACTION_UP:
                changeScroll();
                return true;
        }
        return super.onTouchEvent(ev);
    }

    //判断菜单打开或关闭
    private void changeScroll() {
        //如果用户滑动超过menuTv的一半 打开菜单
        //如果距离不足一半就关闭菜单
        if (getScrollX() > mScrollWidth / 2) {
            isOpen = true;
            smoothScrollTo(mScrollWidth, 0);
            slidingListener.onMenuIsOpen(this);
        } else {
            closeMenu();
        }
    }

    public interface SlidingListener {
        void onMenuIsOpen(SlidingMenuView slidingMenuView);

        //当滑动的时候 把自己传过去 方便Adapter比较 是否是用一个slidingMenuView
        void onMove(SlidingMenuView slidingMenuView);
    }
}
