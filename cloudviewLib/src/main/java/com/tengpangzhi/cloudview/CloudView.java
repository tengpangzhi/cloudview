package com.tengpangzhi.cloudview;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * Created by tengpangzhi on 2016/9/8.
 * 自定义云加载的view
 */
public class CloudView extends LinearLayout {
    //系统变量
    private Context mCtx;
    private AttributeSet mAttr;
    private Resources mResources;

    //转轮view
    private RelativeLayout mRlCloudImageBag;//背景相对布局
    private ImageView mIvLoadBg;//背景云
    private ImageView mIvScroll1;// 大轮
    private ImageView mIvScroll2;// 中轮
    private ImageView mIvScroll3;// 左小轮
    private ImageView mIvScroll4;// 右小轮

    private float sizePersent;//图片放大缩小的百分比

    public CloudView(Context context) {
        super(context);
        uiInit(context, null);
    }

    public CloudView(Context context, AttributeSet attrs) {
        super(context, attrs);
        uiInit(context, attrs);
    }

    public CloudView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        uiInit(context, attrs);
    }

    private void uiInit(Context context, AttributeSet attrs) {
        inflate(getContext(), R.layout.layout_cloud, this);
        mCtx = context;
        mAttr = attrs;
        mResources = getResources();

        //获取组件属性
        TypedArray typedArray = mCtx.obtainStyledAttributes(mAttr, R.styleable.CloudView);

        sizePersent = typedArray.getFloat(R.styleable.CloudView_image_percent, 1f);
        uiInitView();
    }


    private void uiInitView() {
        mRlCloudImageBag = (RelativeLayout) findViewById(R.id.rlCloudImageBag);
        mIvLoadBg = (ImageView) findViewById(R.id.ivLoadBg);
        mIvScroll1 = (ImageView) findViewById(R.id.ivScroll1);
        mIvScroll2 = (ImageView) findViewById(R.id.ivScroll2);
        mIvScroll3 = (ImageView) findViewById(R.id.ivScroll3);
        mIvScroll4 = (ImageView) findViewById(R.id.ivScroll4);

        /*
         * 动态获取云背景图片在每个设备上的实际尺寸
		 */
        int w = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);//屏幕view宽
        int h = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);//屏幕viewh


        mRlCloudImageBag.measure(w, h);
        int cloudImagewidth = Math.round(mRlCloudImageBag.getMeasuredWidth() * sizePersent);//获取云的宽
        int cloudImageheight = Math.round(mRlCloudImageBag.getMeasuredHeight() * sizePersent);//获取云的高

        //动态设置云图片大小
        LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams) mRlCloudImageBag.getLayoutParams();
        linearParams.width = cloudImagewidth;
        linearParams.height = cloudImageheight;
        mRlCloudImageBag.setLayoutParams(linearParams);


//动态设置轮1大小
        mIvScroll1.measure(w, h);
        int scoll1width = Math.round(mIvScroll1.getMeasuredWidth() * sizePersent);//获取轮1的宽
        int scoll1height = Math.round(mIvScroll1.getMeasuredHeight() * sizePersent);//获取轮1的高

		/*
		 * 设置大轮相对位置
		 */
        int widthMarginLun1 = (int) (cloudImagewidth * 0.42);
        int heightMarginLun1 = (int) (cloudImageheight * 0.15);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                scoll1width,
                scoll1height);
        layoutParams.setMargins(widthMarginLun1, heightMarginLun1, 0, 0);
        mIvScroll1.setLayoutParams(layoutParams);


        //动态设置轮2大小
        mIvScroll2.measure(w, h);
        int scoll2width = Math.round(mIvScroll2.getMeasuredWidth() * sizePersent);//获取轮2的宽
        int scoll2height = Math.round(mIvScroll2.getMeasuredHeight() * sizePersent);//获取轮2的高
		/*
		 * 设置中轮相对位置
		 */
        int widthMarginLun2 = (int) (cloudImagewidth * 0.35);
        int heightMarginLun2 = (int) (cloudImageheight * 0.68);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(
                scoll2width,
                scoll2height);
        layoutParams2.setMargins(widthMarginLun2, heightMarginLun2, 0, 0);
        mIvScroll2.setLayoutParams(layoutParams2);


        //动态设置轮3大小
        mIvScroll3.measure(w, h);
        int scoll3width = Math.round(mIvScroll3.getMeasuredWidth() * sizePersent);//获取轮3的宽
        int scoll3height = Math.round(mIvScroll3.getMeasuredHeight() * sizePersent);//获取轮3的高
		/*
		 * 设置左小轮相对位置
		 */
        int widthMarginLun3 = (int) (cloudImagewidth * 0.6);
        int heightMarginLun3 = (int) (cloudImageheight * 0.72);
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(
                scoll3width,
                scoll3height);
        layoutParams3.setMargins(widthMarginLun3, heightMarginLun3, 0, 0);
        mIvScroll3.setLayoutParams(layoutParams3);


        //动态设置轮4大小
        mIvScroll4.measure(w, h);
        int scoll4width = Math.round(mIvScroll4.getMeasuredWidth() * sizePersent);//获取轮4的宽
        int scoll4height = Math.round(mIvScroll4.getMeasuredHeight() * sizePersent);//获取轮4的高
		/*
		 * 设置右小轮相对位置
		 */
        int widthMarginLun4 = (int) (cloudImagewidth * 0.32);
        int heightMarginLun4 = (int) (cloudImageheight * 0.48);
        RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(
                scoll4width,
                scoll4height);
        layoutParams4.setMargins(widthMarginLun4, heightMarginLun4, 0, 0);
        mIvScroll4.setLayoutParams(layoutParams4);

    }

    public void clearAnimation() {
        mIvScroll1.clearAnimation();
        mIvScroll2.clearAnimation();
        mIvScroll3.clearAnimation();
        mIvScroll4.clearAnimation();
    }

    /*
     * 播放动画
     */
    public void startAnimation() {
        LinearInterpolator lin = new LinearInterpolator();

		/*
		 * 设置大轮顺时针旋转动画
		 */
        Animation operatingAnim1 = AnimationUtils.loadAnimation(mCtx,
                R.anim.clockwisec_rotate);
        operatingAnim1.setInterpolator(lin);
        mIvScroll1.startAnimation(operatingAnim1);

		/*
		 * 设置中轮逆时针旋转动画
		 */
        Animation operatingAnim2 = AnimationUtils.loadAnimation(mCtx,
                R.anim.unclockwisec_rotate);
        operatingAnim2.setInterpolator(lin);
        mIvScroll2.startAnimation(operatingAnim2);

		/*
		 * 设置左小轮逆时针旋转动画
		 */
        Animation operatingAnim3 = AnimationUtils.loadAnimation(mCtx,
                R.anim.unclockwisec_rotate);
        operatingAnim3.setInterpolator(lin);
        mIvScroll3.startAnimation(operatingAnim3);

		/*
		 * 设置右小轮逆时针旋转动画
		 */
        Animation operatingAnim4 = AnimationUtils.loadAnimation(mCtx,
                R.anim.unclockwisec_rotate);
        operatingAnim4.setInterpolator(lin);
        mIvScroll4.startAnimation(operatingAnim4);
    }

}
