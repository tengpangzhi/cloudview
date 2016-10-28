package com.tengpangzhi.cloudview;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * @author liteng@sungrowpower.com
 * @Description:自定义云平台加载框
 */
public class CloudProgressDialog extends ProgressDialog {

//	public static void main(String[] args) {
//		/*
//		 *启动
//		 */
//		CloudProgressDialog cloudProgressDialog = new CloudProgressDialog(Context, "正在加载中。。");
//		cloudProgressDialog.setCancelable(false);
//		cloudProgressDialog.show();
//		
//		/*
//		 * 取消
//		 */
//		cloudProgressDialog.cancel();
//	}

    private Context mContext;
    private String mLoadingTip;
    private TextView mLoadingTv;
    private CloudView mCvDialogLoad;

    public CloudProgressDialog(Context context, String content) {
        // super(context);
        super(context, R.style.cloudDialog);
        this.mContext = context;
        this.mLoadingTip = content;
        setCanceledOnTouchOutside(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();// 初始化页面view
        initAction();// 初始化动作
    }

    /*
     * (non-Javadoc)
     *
     * @see android.app.ProgressDialog#onStop() dialog消失的时候，清空动画显示
     */
    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        clearAnimation();
        super.onStop();
    }

    /*
     * 清理动画
     */
    private void clearAnimation() {

        mCvDialogLoad.clearAnimation();
    }

    private void initAction() {
        mCvDialogLoad.startAnimation();
    }



    public void setContent(String str) {
        mLoadingTv.setText(str);
		
		/*
		 * 如果tip提示为空，则去除占位
		 */
        if (str == null || "".equals(str.trim()))
            mLoadingTv.setVisibility(View.GONE);
    }

    private void initView() {
        setContentView(R.layout.progress_dialog_cloud);
        mLoadingTv = (TextView) findViewById(R.id.loadingTv);
        //初始化云加载框
        mCvDialogLoad = (CloudView) findViewById(R.id.cvDialogLoad);
        setContent(mLoadingTip);

    }

	/*
	 * @Override public void onWindowFocusChanged(boolean hasFocus) { // TODO
	 * Auto-generated method stub mAnimation.start();
	 * super.onWindowFocusChanged(hasFocus); }
	 */
}
