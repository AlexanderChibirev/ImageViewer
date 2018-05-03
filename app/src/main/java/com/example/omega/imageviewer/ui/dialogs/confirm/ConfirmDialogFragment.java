package com.example.omega.imageviewer.ui.dialogs.confirm;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.omega.imageviewer.R;
import com.example.omega.imageviewer.ui.dialogs.base.BaseDialogFragment;
import com.omega_r.libs.omegafragmentbuilder.AppOmegaFragmentBuilder;

import butterknife.BindView;
import butterknife.OnClick;
import omega.com.annotations.OmegaExtra;
import omega.com.annotations.OmegaFragment;

/**
 * Created by Alexander Chibirev on 4/25/2018.
 */

@OmegaFragment
public class ConfirmDialogFragment extends BaseDialogFragment {

    @BindView(R.id.textview_message)
    TextView mMessageTextView;

    @BindView(R.id.button_cancel)
    Button mCancelButton;

    @BindView(R.id.button_ok)
    Button mOkButton;

    @OmegaExtra(Field.SET_MESSAGE)
    String mMessage;

    @OmegaExtra(Field.SET_NEGATIVE_LABEL)
    String mOkButtonLabel;

    @OmegaExtra(Field.SET_POSITIVE_LABEL)
    String mCancelButtonLabel;

    private ConfirmDialogListener mListener;

    @Override
    protected int getContentView() {
        return R.layout.dialog_confirm;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppOmegaFragmentBuilder.inject(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mMessageTextView.setText(mMessage);
        mOkButton.setText(mOkButtonLabel);
        mCancelButton.setText(mCancelButtonLabel);
    }

    @Override
    public void onAttach(Context context) { //TODO refactoring
        super.onAttach(context);
        try {
            mListener = (ConfirmDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement ConfirmDialogListener");
        }
        if (mListener == null) {
            try {
                mListener = (ConfirmDialogListener) getTargetFragment();
            } catch (ClassCastException e) {
                throw new ClassCastException("Caller fragment must implement ConfirmDialogListener");
            }
        }
    }

    @OnClick({R.id.button_ok, R.id.button_cancel})
    protected void onOkButtonClick(View view) {
        switch (view.getId()) {
            case R.id.button_ok:
                mListener.onOkButtonPressed();
                break;
            case R.id.button_cancel:
                mListener.onCancelButtonPressed();
                break;
        }
        dismiss();
    }

    public interface ConfirmDialogListener {
        void onOkButtonPressed();

        void onCancelButtonPressed();
    }

    private interface Field {
        String SET_MESSAGE = "setMessage";
        String SET_NEGATIVE_LABEL = "setNegativeLabel";
        String SET_POSITIVE_LABEL = "setPositiveLabel";
    }

}
