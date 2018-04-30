package com.example.omega.imageviewer.ui.dialogs.attention;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.annotation.StyleRes;
import android.widget.TextView;

import com.example.omega.imageviewer.R;
import com.example.omega.imageviewer.ui.dialogs.base.BaseDialog;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Alexander Chibirev on 4/30/2018.
 */

public class AttentionDialog extends BaseDialog {

    private String mMessage;

    @BindView(R.id.textview_message)
    TextView mMessageTextView;

    @Nullable
    private OnOkClickListener mOnOkClickListener;

    public AttentionDialog(@NonNull Context context) {
        super(context);
    }

    public AttentionDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }

    public AttentionDialog(@NonNull Context context, boolean cancelable, @Nullable DialogInterface.OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected int getContentView() {
        return R.layout.dialog_attention;
    }

    @Override
    protected void onPostCreate() {
        super.onPostCreate();
        mMessageTextView.setText(mMessage);
    }

    public void show(String message) {
        mMessage = message;
        show();
    }

    public void setOnOkClickListener(@Nullable OnOkClickListener onOkClickListener) {
        mOnOkClickListener = onOkClickListener;
    }

    public void show(@StringRes int message) {
        show(getContext().getString(message));
    }

    @OnClick(R.id.button_ok)
    void onOkClick() {
        if (mOnOkClickListener != null) {
            mOnOkClickListener.onOkClick();
        }
        cancel();
    }

    public interface OnOkClickListener {
        void onOkClick();
    }

}
