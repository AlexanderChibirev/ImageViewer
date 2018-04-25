package com.example.omega.imageviewer.ui.dialogs.delegates;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.omega.imageviewer.ui.dialogs.OfflineModeDialog;

/**
 * Created by Alexander Chibirev on 4/25/2018.
 */

public class OfflineModeDialogDelegateImpl implements OfflineModeDialogDelegate {

    private Context mContext;
    private OfflineModeDialog mOfflineModeDialog;

    public OfflineModeDialogDelegateImpl(Context context) {
        mContext = context;
    }

    @Override
    public void showConfirmDialog(@NonNull String message, @NonNull String cancelLabel, @NonNull String okLabel,
                                  OfflineModeDialog.OnCancelButtonListener onCancelListener,
                                  @Nullable OfflineModeDialog.OnOkButtonListener onOkListener,
                                  boolean cancelable) {
        showDialog(cancelable, message, cancelLabel, okLabel, onCancelListener, onOkListener);
    }

    private void showDialog(boolean cancelable, String message, String cancelLabel, String okLabel,
                            OfflineModeDialog.OnCancelButtonListener onCancelListener,
                            @Nullable OfflineModeDialog.OnOkButtonListener onOkListener) {
        if (isShowing()) {
            return;
        }

        mOfflineModeDialog = new OfflineModeDialog(mContext);
        mOfflineModeDialog.setCancelable(cancelable);
        mOfflineModeDialog.setOnOkButtonListener(onOkListener);
        mOfflineModeDialog.setOnCancelButtonListener(onCancelListener);
        mOfflineModeDialog.show(message, okLabel, cancelLabel);
    }

    private boolean isShowing() {
        return mOfflineModeDialog != null && mOfflineModeDialog.isShowing();
    }

    @Override
    public void hideConfirmDialog() {
        if (isShowing()) {
            mOfflineModeDialog.dismiss();
        }
    }

    @Override
    public void onDestroy() {
        if (isShowing()) {
            mOfflineModeDialog.setOnCancelButtonListener(null);
            mOfflineModeDialog.setOnOkButtonListener(null);
            mOfflineModeDialog.dismiss();
        }
    }
}

