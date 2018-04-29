package com.example.omega.imageviewer.ui.dialogs.delegates;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;

import com.example.omega.imageviewer.ui.dialogs.AttentionDialog;

/**
 * Created by Alexander Chibirev on 4/25/2018.
 */

public interface AttentionDialogDelegate {

    void showConfirmDialog(@StringRes int message, @StringRes int cancelLabel, @StringRes int okLabel,
                           AttentionDialog.OnCancelButtonListener onCancelListener,
                           @Nullable AttentionDialog.OnOkButtonListener onOkListener, boolean cancelable);

    void hideConfirmDialog();

    void onDestroy();
}
