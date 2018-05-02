package com.example.omega.imageviewer.ui.dialogs.cloud_drive_options;

import android.content.Context;

/**
 * Created by Alexander Chibirev on 4/30/2018.
 */

public class OptionsDialogDelegateImpl implements OptionsDialogDelegate {

    private Context mContext;
    private OptionsDialog mOptionsDialog;

    public OptionsDialogDelegateImpl(Context context) {
        mContext = context;
    }

    @Override
    public void onDestroy() {
        if (mOptionsDialog != null && mOptionsDialog.isShowing()) {
            mOptionsDialog.setOnClickListener(null);
            mOptionsDialog.dismiss();
        }
    }

    @Override
    public void showOptionsDialog(OptionsDialog.OnClickListener onClickListener) {
        if (mOptionsDialog != null && mOptionsDialog.isShowing()) return;
        //TODO скрыть кнопку сохранение картинки, если это режим офлайн
        mOptionsDialog = new OptionsDialog(mContext/* добавить режим офлайн онлайн со скрытием кнопки сохранения*/);
        mOptionsDialog.setOnClickListener(onClickListener);
        mOptionsDialog.show();
    }

    @Override
    public void hideDialog() {
        if (mOptionsDialog != null && mOptionsDialog.isShowing()) {
            mOptionsDialog.dismiss();
        }
    }

}
