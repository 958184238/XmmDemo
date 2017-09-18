package com.exam.admin.rongyundemo.interfa;

import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * 类名: OnRecyclerItemClickListener
 * 描述: 手势监听
 * 作者: 吴飞
 * 日期: 2017/9/14 13:55
 */

public abstract class OnRecyclerItemClickListener implements RecyclerView.OnItemTouchListener {

    private RecyclerView recyclerView;
    private final GestureDetectorCompat mGestureDetector;

    public OnRecyclerItemClickListener(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
        mGestureDetector = new GestureDetectorCompat(recyclerView.getContext(), new ItemTouchHelperGestureListener());
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        mGestureDetector.onTouchEvent(e);
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        mGestureDetector.onTouchEvent(e);
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }

    private class ItemTouchHelperGestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
            if (child != null) {
                RecyclerView.ViewHolder holder = recyclerView.getChildViewHolder(child);
                onItemClick(holder);
            }
            return true;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            super.onLongPress(e);
            View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
            if (child != null) {
                RecyclerView.ViewHolder holder = recyclerView.getChildViewHolder(child);
                onItemLongClick(holder);
            }
        }
    }

    public abstract void onItemClick(RecyclerView.ViewHolder holder);

    public abstract void onItemLongClick(RecyclerView.ViewHolder holder);
}
