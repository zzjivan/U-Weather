package com.app.zzj.u_weather.NView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

/**
 * Created by sedwt on 2016/10/11.
 */
public class LetterView extends View {

    private Paint mPaint;
    private boolean isShowBg = false;// 用于区分是否显示view的背景
    private OnSlidingListener mOnSlidingListener;// 滑动此View的监听器
    private int choose = -1;// 用于标记当前所选中的位置
    private TextView tv_toast;//用于接受从activity中传过来的，中间用于展示字母的textView
    //需要展示的数据
    private String[] letter = { "定位", "最近", "热门", "全部", "A", "B", "C", "D",
            "E", "F", "G", "H","J", "K", "L", "M", "N","P", "Q",
            "R", "S", "T","W", "X", "Y", "Z" };

    public LetterView(Context context) {
        super(context);
    }

    public LetterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public LetterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public interface OnSlidingListener {
         void sliding(String str);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //当此View被按下时所显示的背景颜色
        if (isShowBg) {
            canvas.drawColor(Color.parseColor("#40000000"));
        }
        //计算每个字符所占的高度
        float singleHeight = getHeight() / letter.length;
        int width = getWidth();
        for (int i = 0; i < letter.length; i++) {
            String text = letter[i];

            float xPosition = width / 2 - mPaint.measureText(text) / 2;
            float yPosition = singleHeight * i + singleHeight;
            //通过不断的改变yPosition将数组中的数据一个一个绘制到自定义的View中
            canvas.drawText(text, xPosition, yPosition, mPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int position = (int) (event.getY()/getHeight()*letter.length);
        int old_choose = choose;
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                isShowBg = true;
                if(old_choose != position && mOnSlidingListener != null) {
                    if(position > 0 && position < letter.length) {
                        choose = position;
                        mOnSlidingListener.sliding(letter[choose]);
                        if(tv_toast != null) {
                            tv_toast.setText(letter[choose]);
                            tv_toast.setVisibility(VISIBLE);
                        }
                        invalidate();
                    }
                }
                break;
            case MotionEvent.ACTION_MOVE:
                isShowBg = true;
                if(old_choose != position && mOnSlidingListener != null) {
                    if(position > 0 && position < letter.length) {
                        choose = position;
                        mOnSlidingListener.sliding(letter[choose]);
                        if(tv_toast != null) {
                            tv_toast.setText(letter[choose]);
                            tv_toast.setVisibility(VISIBLE);
                        }
                        invalidate();
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                isShowBg = false;
                choose = -1;
                if(tv_toast != null)
                    tv_toast.setVisibility(INVISIBLE);
                invalidate();
                break;
            default:
                break;
        }
        return true;
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(32);
        mPaint.setColor(Color.parseColor("#8c8c8c"));
    }

    public void setTextView(TextView tvDialog) {
        tv_toast = tvDialog;
    }

    public void setOnSlidingListener(OnSlidingListener mOnSlidingListener) {
        this.mOnSlidingListener = mOnSlidingListener;
    }
}
