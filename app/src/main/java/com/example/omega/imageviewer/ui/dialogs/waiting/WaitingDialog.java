package com.example.omega.imageviewer.ui.dialogs.waiting;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;

import com.example.omega.imageviewer.R;
import com.example.omega.imageviewer.ui.dialogs.base.BaseDialog;

/**
 * Created by Alexander Chibirev on 4/25/2018.
 */

public class WaitingDialog extends BaseDialog {

    private final Handler mHandler = new Handler();
    private Runnable mShowRunnable = this::showRunnable;

    public WaitingDialog(@NonNull Context context) {
        super(context);
        setCancelable(false);
        setCanceledOnTouchOutside(false);
    }

    @Override
    protected int getContentView() {
        return R.layout.dialog_waiting;
    }

    public void postShow(long delayMillis) {
        mHandler.removeCallbacks(mShowRunnable);
        mHandler.postDelayed(mShowRunnable, delayMillis);
    }

    @Override
    public void show() {
        mHandler.removeCallbacks(mShowRunnable);
        super.show();
    }

    @Override
    public void dismiss() {
        mHandler.removeCallbacks(mShowRunnable);
        super.dismiss();
    }

    private void showRunnable() {
        try {
            show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}