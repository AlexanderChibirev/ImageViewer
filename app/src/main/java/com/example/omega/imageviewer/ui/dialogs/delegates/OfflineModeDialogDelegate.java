package com.example.omega.imageviewer.ui.dialogs.delegates;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.omega.imageviewer.ui.dialogs.OfflineModeDialog;

/**
 * Created by Alexander Chibirev on 4/25/2018.
 */

public interface OfflineModeDialogDelegate {

    void showConfirmDialog(@NonNull String message, @NonNull String cancelLabel, @NonNull String okLabel,
                           OfflineModeDialog.OnCancelButtonListener onCancelListener,
                           @Nullable OfflineModeDialog.OnOkButtonListener onOkListener, boolean cancelable);

    void hideConfirmDialog();

    void onDestroy();
}
