package com.example.omega.imageviewer.ui.dialogs.attention;

import android.support.annotation.Nullable;
import android.support.annotation.StringRes;

import com.example.omega.imageviewer.ui.dialogs.base.BaseDialogInterface;

/**
 * Created by Alexander Chibirev on 4/30/2018.
 */

public interface AttentionDialogDelegate extends BaseDialogInterface {
    void showAttentionDialog(@StringRes int message, @Nullable AttentionDialog.OnOkClickListener listener);
}
