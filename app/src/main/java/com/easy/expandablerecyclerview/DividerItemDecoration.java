package com.easy.expandablerecyclerview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;

/**
 * @author fuyujie
 * @package: com.easy.expandablerecyclerview
 * @fileNmae DividerItemDecoration
 * @date 2018/10/22 16:44
 * @describe
 * @org easylinking
 * @email f279259625@gmail.com
 */
public class DividerItemDecoration extends RecyclerView.ItemDecoration {

    private static final String TAG = "DividerItemDecoration";

    private Context context;

    private int groupHeight;     // 分组分割线高度

    private Paint dividerPaint;     // 绘制分割线画笔

    private Paint textPaint;        // 绘制文字画笔

    private Paint topDividerPaint;


    public DividerItemDecoration(Context context, OnGroupListener listener) {
        this.context = context;
        this.listener = listener;
        groupHeight = dp2Px(24);
        initPaint();
    }

    private void initPaint() {
        dividerPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        dividerPaint.setColor(context.getResources().getColor(R.color.colorAccent));
        dividerPaint.setStyle(Paint.Style.FILL);


        topDividerPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        topDividerPaint.setColor(Color.parseColor("#9924f715"));
        topDividerPaint.setStyle(Paint.Style.FILL);


        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(sp2Px(14));

    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        //获取view在Adapter中的position
        int position = parent.getChildAdapterPosition(view);
        // 获取字母分类
        String groupName = getTagName(position);
        if (groupName == null) {
            return;
        }
        if (position == 0 || isFirstTag(position)) {
            outRect.top = groupHeight;
        }
    }


    @Override
    public void onDraw(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(canvas, parent, state);
        // 获取可见的item数
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = parent.getChildAt(i);
            int position = parent.getChildAdapterPosition(childView);
            int bottom = childView.getTop();
            int top = bottom - groupHeight;
            if (isFirstTag(position)) {
                // 矩形
                canvas.drawRect(0 , top, childView.getWidth(), bottom, dividerPaint);
                // 文字
                float baseLine = (top + bottom) / 2f - (textPaint.descent() + textPaint.ascent()) / 2f;
                canvas.drawText(getTagName(position), dp2Px(10),
                        baseLine, textPaint);
            }
        }
    }

    @Override
    public void onDrawOver(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(canvas, parent, state);
//        View firstVisibleView = parent.getChildAt(0);
//        int firstVisiblePosition = parent.getChildAdapterPosition(firstVisibleView);
//        String groupName = getTagName(firstVisiblePosition);
//        int left = parent.getPaddingLeft();
//        int right = firstVisibleView.getWidth() - parent.getPaddingRight();
//        // 第一个itemview(firstVisibleView) 的 bottom 值小于分割线高度，分割线随着 recyclerview 滚动，
//        // 分割线top固定不变，bottom=firstVisibleView.bottom
//        if (firstVisibleView.getBottom() <= groupHeight && isFirstTag(firstVisiblePosition + 1)) {
//            canvas.drawRect(left, 0, right, firstVisibleView.getBottom(), dividerPaint);
//            float baseLine = firstVisibleView.getBottom() / 2f - (textPaint.descent() + textPaint.ascent()) / 2f;
//            canvas.drawText(groupName, left + dp2Px(10),
//                    baseLine, textPaint);
//        } else {
//            canvas.drawRect(left, 0, right, groupHeight, dividerPaint);
//            float baseLine = groupHeight / 2f - (textPaint.descent() + textPaint.ascent()) / 2f;
//            canvas.drawText(groupName, left + dp2Px(10), baseLine, textPaint);
//        }


    }


    private OnGroupListener listener;

    static interface OnGroupListener {

        // 获取分组中第一个文字
        String getGroupName(int position);
    }


    public String getTagName(int position) {
        if (listener != null) {
            return listener.getGroupName(position);
        }
        return null;
    }


    /**
     * 是否是某组中第一个item
     *
     * @param position
     * @return
     */
    private boolean isFirstTag(int position) {
        // 第一个 itemView 肯定是新的一个分组
        if (position == 0) {
            return true;
        } else {
            String preGroupName = getTagName(position - 1);
            String groupName = getTagName(position);
            return !TextUtils.equals(preGroupName, groupName);
        }
    }


    private int dp2Px(int dpValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, context.getResources().getDisplayMetrics());
    }

    private int sp2Px(int spValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spValue, context.getResources().getDisplayMetrics());
    }

}
