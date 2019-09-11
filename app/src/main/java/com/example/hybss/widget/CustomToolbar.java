package com.example.hybss.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.hybss.mvpmodule.R;
import com.example.hybss.utils.DisplayUtil;


/**
 * @Author: yobin he
 * @Date:2019/8/28 11:48
 * @Email: heyibin@huawenpicture.com
 * @Des : 自定义Toolbar,目前还需要将高度测量给弄进去
 */
public class CustomToolbar extends FrameLayout {

    ImageView ivLeft;
    FrameLayout flLeft;
    TextView tvTitle;
    ImageView ivRight;
    TextView tvRight;
    FrameLayout flRight;
    RelativeLayout customBar;
    private View view;

    private int mLeftDrawable;
    private String mTextTitle;
    private int mTextTitleColor;
    private float mTextTitleSize;

    private String mTextRight;
    private int mTextRightColor;
    private float mTextRightSize;
    private int mRightDrawable;
    private int mTextRightBg;

    private boolean isHiddenRight;
    private boolean isHiddenRightImage;
    private OnClickListener listener;
    private Context context;

    public CustomToolbar(@NonNull Context context) {
        this(context, null);
        this.context = context;
    }

    public CustomToolbar(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
        this.context = context;
    }


    public CustomToolbar(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initView(context);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.customtoolbar);
        mLeftDrawable = array.getResourceId(R.styleable.customtoolbar_left_src, R.mipmap.icon_back_white);
        mTextTitle = array.getString(R.styleable.customtoolbar_title_text);
        mTextTitleColor = array.getColor(R.styleable.customtoolbar_title_color, getResources().getColor(R.color.title_bar_color));
        mTextTitleSize = array.getDimension(R.styleable.customtoolbar_title_size, DisplayUtil.dip2px(context, 10));

        mTextRight = array.getString(R.styleable.customtoolbar_right_text);
        mTextRightColor = array.getColor(R.styleable.customtoolbar_right_color, getResources().getColor(R.color.subtitle_bar_color));
        mTextRightSize = array.getDimension(R.styleable.customtoolbar_right_size, DisplayUtil.dip2px(context, 5));
        mTextRightBg = array.getResourceId(R.styleable.customtoolbar_right_bg, 0);
        mRightDrawable = array.getResourceId(R.styleable.customtoolbar_right_src, R.mipmap.icon_back_white);


        isHiddenRight = array.getBoolean(R.styleable.customtoolbar_is_hidden_right, true);
        isHiddenRightImage = array.getBoolean(R.styleable.customtoolbar_is_hidden_right_image, false);

        array.recycle();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private void initView(Context context) {
        view = LayoutInflater.from(context).inflate(R.layout.custom_toolbar, this, true);
    }

    @Override
    protected void onFinishInflate() { //在布局填充之后
        super.onFinishInflate();
        ivLeft = findViewById(R.id.iv_left);
        flLeft = findViewById(R.id.fl_left);
        tvTitle = findViewById(R.id.tv_title);
        ivRight = findViewById(R.id.iv_right);
        tvRight = findViewById(R.id.tv_right);
        flRight = findViewById(R.id.fl_right);


        ivLeft.setImageResource(mLeftDrawable);
        tvTitle.setText(mTextTitle);
        tvTitle.setTextSize(mTextTitleSize);
        tvTitle.setTextColor(mTextTitleColor);


        changeRight();

        flLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClickLeft(v);
                }
            }
        });
        flRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClickRight(v);
                }
            }
        });
    }

    private void changeRight() {
        if (isHiddenRight) {
            flRight.setVisibility(GONE);
        } else {
            flRight.setVisibility(VISIBLE);
            if (isHiddenRightImage) {
                tvRight.setText(mTextRight);

                tvRight.setTextSize(mTextRightSize);
                tvRight.setTextColor(mTextRightColor);
                if (mTextRightBg != 0) {
                    tvRight.setBackgroundResource(mTextRightBg);
                }
                ivRight.setVisibility(GONE);
                tvRight.setVisibility(VISIBLE);
            } else {
                ivRight.setImageResource(mRightDrawable);
                ivRight.setVisibility(VISIBLE);
                tvRight.setVisibility(GONE);

            }
        }
    }

    /*******************添加动态代码*******************************/

    public int getLeftDrawable() {
        return mLeftDrawable;
    }

    public void setLeftDrawable(int mLeftDrawable) {
        this.mLeftDrawable = mLeftDrawable;
        ivLeft.setImageResource(this.mLeftDrawable);
    }

    public String getTextTitle() {
        return mTextTitle;
    }

    public void setTextTitle(String mTextTitle) {
        this.mTextTitle = mTextTitle;
        tvTitle.setText(this.mTextTitle);
    }

    public int getTextTitleColor() {
        return mTextTitleColor;
    }

    public void setTextTitleColor(int mTextTitleColor) {
        this.mTextTitleColor = mTextTitleColor;
        tvTitle.setTextColor(this.mTextTitleColor);
    }

    public float getTextTitleSize() {
        return mTextTitleSize;
    }

    public void setTextTitleSize(float mTextTitleSize) {
        this.mTextTitleSize = mTextTitleSize;
        tvTitle.setTextSize(this.mTextTitleSize);
    }

    public String getTextRight() {
        return mTextRight;
    }

    public void setTextRight(String mTextRight) {
        this.mTextRight = mTextRight;
        tvRight.setText(this.mTextRight);
    }

    public int getTextRightColor() {
        return mTextRightColor;
    }

    public void setTextRightColor(int mTextRightColor) {
        this.mTextRightColor = mTextRightColor;
        tvRight.setTextColor(this.mTextRightColor);
    }

    public float getTextRightSize() {
        return mTextRightSize;
    }

    public void setTextRightSize(float mTextRightSize) {
        this.mTextRightSize = mTextRightSize;
        tvRight.setTextSize(this.mTextRightSize);
    }

    public int getmTextRightBg() {
        return mTextRightBg;
    }

    public void setmTextRightBg(int mTextRightBg) {
        this.mTextRightBg = mTextRightBg;
        tvRight.setBackgroundResource(mTextRightBg);
    }

    public int getRightDrawable() {
        return mRightDrawable;
    }

    public void setRightDrawable(int mRightDrawable) {
        this.mRightDrawable = mRightDrawable;
        ivRight.setImageResource(this.mRightDrawable);
    }

    public boolean isHiddenRight() {
        return isHiddenRight;
    }

    public void setHiddenRight(boolean hiddenRight) {
        isHiddenRight = hiddenRight;
        changeRight();

    }

    public boolean isHiddenRightImage() {
        return isHiddenRightImage;
    }

    public void setHiddenRightImage(boolean hiddenRightImage) {
        isHiddenRightImage = hiddenRightImage;
        changeRight();
    }

    public void setOnClickListener(OnClickListener listener) {
        this.listener = listener;
    }

    public interface OnClickListener {
        void onClickLeft(View view);

        void onClickRight(View view);
    }
}
