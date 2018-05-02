package com.example.omega.imageviewer.ui.dialogs.attention;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;

import com.example.omega.imageviewer.models.Text;

/**
 * Created by Alexander Chibirev on 4/30/2018.
 */

public class AttentionDialogDelegateImpl implements AttentionDialogDelegate {

    private Context mContext;
    private AttentionDialog mAttentionDialog;

    public AttentionDialogDelegateImpl(Context context) {
        mContext = context;
    }

    @Override
    public void showAttentionDialog(@StringRes int message, @Nullable AttentionDialog.OnOkClickListener listener) {
        if (mAttentionDialog != null && mAttentionDialog.isShowing()) return;

        mAttentionDialog = new AttentionDialog(mContext);
        mAttentionDialog.setOnOkClickListener(listener);
        mAttentionDialog.show(Text.from(message));
    }

    @Override
    public void hideDialog() {
        if (mAttentionDialog != null && mAttentionDialog.isShowing()) {
            mAttentionDialog.dismiss();
        }
    }

    @Override
    public void onDestroy() {
        if (mAttentionDialog != null && mAttentionDialog.isShowing()) {
            mAttentionDialog.setOnCancelListener(null);
            mAttentionDialog.setOnOkClickListener(null);
            mAttentionDialog.dismiss();
        }
    }

}
