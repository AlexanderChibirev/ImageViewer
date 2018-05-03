package com.example.omega.imageviewer.ui.dialogs.attention;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.example.omega.imageviewer.R;
import com.example.omega.imageviewer.ui.dialogs.base.BaseDialogFragment;
import com.omega_r.libs.omegafragmentbuilder.AppOmegaFragmentBuilder;

import butterknife.BindView;
import butterknife.OnClick;
import omega.com.annotations.OmegaExtra;
import omega.com.annotations.OmegaFragment;

/**
 * Created by Alexander Chibirev on 4/30/2018.
 */

@OmegaFragment
public class AttentionDialogFragment extends BaseDialogFragment {

    @OmegaExtra(Field.SET_MESSAGE)
    String mMessage;

    @BindView(R.id.textview_message)
    TextView mMessageTextView;

    private OnOkClickListener mOnOkClickListener;

    @Override
    protected int getContentView() {
        return R.layout.dialog_attention;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppOmegaFragmentBuilder.inject(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mMessageTextView.setText(mMessage);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mOnOkClickListener = (OnOkClickListener) getTargetFragment();
        } catch (ClassCastException e) {
            throw new ClassCastException("Caller fragment must implement OnOkClickListener");
        }
    }

    @OnClick(R.id.button_ok)
    protected void onOkClick() {
        mOnOkClickListener.onOkClick();
        dismiss();
    }

    public interface OnOkClickListener {
        void onOkClick();
    }

    private interface Field {
        String SET_MESSAGE = "setMessage";
    }

}
