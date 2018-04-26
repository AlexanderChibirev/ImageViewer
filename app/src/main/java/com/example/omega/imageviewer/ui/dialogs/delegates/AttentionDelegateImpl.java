package com.example.omega.imageviewer.ui.dialogs.delegates;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;

import com.example.omega.imageviewer.ui.dialogs.AttentionDialog;

/**
 * Created by Alexander Chibirev on 4/25/2018.
 */

public class AttentionDelegateImpl implements AttentionDelegate {

    private Context mContext;
    private AttentionDialog mAttentionDialog;

    public AttentionDelegateImpl(Context context) {
        mContext = context;
    }

    @Override
    public void showConfirmDialog(@StringRes int message, @StringRes int cancelLabel, @StringRes int okLabel,
                                  AttentionDialog.OnCancelButtonListener onCancelListener,
                                  @Nullable AttentionDialog.OnOkButtonListener onOkListener,
                                  boolean cancelable) {
        if (isShowing()) {
            return;
        }

        mAttentionDialog = new AttentionDialog(mContext);
        mAttentionDialog.setCancelable(cancelable);
        mAttentionDialog.setOnOkButtonListener(onOkListener);
        mAttentionDialog.setOnCancelButtonListener(onCancelListener);
        mAttentionDialog.show(message, okLabel, cancelLabel);
    }

    private boolean isShowing() {
        return mAttentionDialog != null && mAttentionDialog.isShowing();
    }

    @Override
    public void hideConfirmDialog() {
        if (isShowing()) {
            mAttentionDialog.dismiss();
        }
    }

    @Override
    public void onDestroy() {
        if (isShowing()) {
            mAttentionDialog.setOnCancelButtonListener(null);
            mAttentionDialog.setOnOkButtonListener(null);
            mAttentionDialog.dismiss();
        }
    }
}

