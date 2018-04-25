package com.example.omega.imageviewer.ui.dialogs;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.annotation.StyleRes;
import android.widget.Button;
import android.widget.TextView;

import com.example.omega.imageviewer.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Alexander Chibirev on 4/25/2018.
 */
public class OfflineModeDialog extends BaseDialog {

    @BindView(R.id.textview_message)
    TextView mMessageTextView;

    @BindView(R.id.button_cancel)
    Button mCancelButton;

    @BindView(R.id.button_ok)
    Button mOkButton;

    private String mMessage;
    private String mOkButtonLabel;
    private String mCancelButtonLabel;

    private OnOkButtonListener mOnOkButtonListener;
    private OnCancelButtonListener mOnCancelButtonListener;

    public OfflineModeDialog(@NonNull Context context) {
        super(context);
    }

    public OfflineModeDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected OfflineModeDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected int getContentView() {
        return R.layout.dialog_offline_mode;
    }

    @Override
    protected void onPostCreate() {
        super.onPostCreate();
        mMessageTextView.setText(mMessage);
        mOkButton.setText(mOkButtonLabel);
        mCancelButton.setText(mCancelButtonLabel);
    }

    public void show(String message, String okButtonLabel, String cancelButtonLabel) {
        mMessage = message;
        mOkButtonLabel = okButtonLabel;
        mCancelButtonLabel = cancelButtonLabel;
        show();
    }

    public void show(@StringRes int message, @StringRes int okButtonLabel, @StringRes int cancelButtonLabel) {
        Context context = getContext();
        show(context.getString(message), context.getString(okButtonLabel), context.getString(cancelButtonLabel));
    }

    public void setOnOkButtonListener(OnOkButtonListener listener) {
        mOnOkButtonListener = listener;
    }

    public void setOnCancelButtonListener(OnCancelButtonListener listener) {
        mOnCancelButtonListener = listener;
    }

    @OnClick(R.id.button_ok)
    public void onOkButtonClick() {
        if (mOnOkButtonListener != null) {
            mOnOkButtonListener.onOkButtonPressed();
        }
    }

    @OnClick(R.id.button_cancel)
    public void onCancelButtonClick() {
        if (mOnCancelButtonListener != null) {
            mOnCancelButtonListener.onCancelButtonPressed();
        }
    }

    public interface OnOkButtonListener {
        void onOkButtonPressed();
    }

    public interface OnCancelButtonListener {
        void onCancelButtonPressed();
    }
}
