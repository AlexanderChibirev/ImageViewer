package com.example.omega.imageviewer.ui.dialogs.cloud_drive_options;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.omega.imageviewer.R;
import com.example.omega.imageviewer.ui.dialogs.base.BaseDialog;

import butterknife.OnClick;

/**
 * Created by Alexander Chibirev on 4/30/2018.
 */

public class OptionsDialog extends BaseDialog {

    private OnClickListener mOnClickListener;

    public OptionsDialog(@NonNull Context context) {
        super(context);
    }

    public OptionsDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected OptionsDialog(@NonNull Context context, boolean cancelable, @Nullable DialogInterface.OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected int getContentView() {
        return R.layout.dialog_cloud_drive_options;
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        mOnClickListener = onClickListener;
    }

    @OnClick({R.id.textview_delete_image, R.id.textview_save_image,
            R.id.textview_full_mode_image, R.id.cardview_cancel})
    void onOkClick(View view) {
        if (mOnClickListener != null) {
            switch (view.getId()) {
                case R.id.textview_full_mode_image:
                    mOnClickListener.onFullModeImageClicked();
                    break;
                case R.id.textview_save_image:
                    mOnClickListener.onSaveImageClicked();
                    break;
                case R.id.textview_delete_image:
                    mOnClickListener.onDeleteClicked();
                    break;
            }
        }
        cancel();
    }

    public interface OnClickListener {

        void onFullModeImageClicked();

        void onSaveImageClicked();

        void onDeleteClicked();

    }
}