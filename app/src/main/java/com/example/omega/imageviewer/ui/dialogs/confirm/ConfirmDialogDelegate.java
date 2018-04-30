package com.example.omega.imageviewer.ui.dialogs.confirm;

import android.support.annotation.Nullable;
import android.support.annotation.StringRes;

import com.example.omega.imageviewer.ui.dialogs.base.BaseDialogInterface;

/**
 * Created by Alexander Chibirev on 4/25/2018.
 */

public interface ConfirmDialogDelegate extends BaseDialogInterface {

    void showConfirmDialog(@StringRes int message, @StringRes int cancelLabel, @StringRes int okLabel,
                           ConfirmDialog.OnCancelButtonListener onCancelListener,
                           @Nullable ConfirmDialog.OnOkButtonListener onOkListener, boolean cancelable);

}
