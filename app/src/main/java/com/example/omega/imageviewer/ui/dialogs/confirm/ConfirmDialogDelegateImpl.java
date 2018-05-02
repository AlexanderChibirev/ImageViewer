package com.example.omega.imageviewer.ui.dialogs.confirm;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;

/**
 * Created by Alexander Chibirev on 4/25/2018.
 */

public class ConfirmDialogDelegateImpl implements ConfirmDialogDelegate {

    private Context mContext;
    private ConfirmDialog mConfirmDialog;

    public ConfirmDialogDelegateImpl(Context context) {
        mContext = context;
    }

    @Override
    public void showConfirmDialog(@StringRes int message, @StringRes int cancelLabel, @StringRes int okLabel,
                                  ConfirmDialog.OnCancelButtonListener onCancelListener,
                                  @Nullable ConfirmDialog.OnOkButtonListener onOkListener,
                                  boolean cancelable) {
        if (isShowing()) {
            return;
        }

        mConfirmDialog = new ConfirmDialog(mContext);
        mConfirmDialog.setCancelable(cancelable);
        mConfirmDialog.setOnOkButtonListener(onOkListener);
        mConfirmDialog.setOnCancelButtonListener(onCancelListener);
        mConfirmDialog.show(message, okLabel, cancelLabel);
    }

    private boolean isShowing() {
        return mConfirmDialog != null && mConfirmDialog.isShowing();
    }

    @Override
    public void hideDialog() {
        if (isShowing()) {
            mConfirmDialog.dismiss();
        }
    }

    @Override
    public void onDestroy() {
        if (isShowing()) {
            mConfirmDialog.setOnCancelButtonListener(null);
            mConfirmDialog.setOnOkButtonListener(null);
            mConfirmDialog.dismiss();
        }
    }

}

