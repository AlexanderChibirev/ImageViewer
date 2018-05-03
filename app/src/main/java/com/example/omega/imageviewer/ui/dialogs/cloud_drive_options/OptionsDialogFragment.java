package com.example.omega.imageviewer.ui.dialogs.cloud_drive_options;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.omega.imageviewer.R;
import com.example.omega.imageviewer.ui.dialogs.base.BaseDialogFragment;
import com.omega_r.libs.omegafragmentbuilder.AppOmegaFragmentBuilder;

import butterknife.OnClick;
import omega.com.annotations.OmegaFragment;

/**
 * Created by Alexander Chibirev on 4/30/2018.
 */

@OmegaFragment
public class OptionsDialogFragment extends BaseDialogFragment {

    private OnClickListener mOnClickListener;

    @Override
    protected int getContentView() {
        return R.layout.dialog_options;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppOmegaFragmentBuilder.inject(this);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mOnClickListener = (OnClickListener) getTargetFragment();
        } catch (ClassCastException e) {
            throw new ClassCastException("Caller fragment must implement OnClickListener");
        }
    }

    @OnClick({R.id.textview_delete_image, R.id.textview_save_image,
            R.id.textview_full_mode_image, R.id.cardview_cancel})
    protected void onOkClick(View view) {
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
        dismiss();
    }

    public interface OnClickListener {

        void onFullModeImageClicked();

        void onSaveImageClicked();

        void onDeleteClicked();

    }

}