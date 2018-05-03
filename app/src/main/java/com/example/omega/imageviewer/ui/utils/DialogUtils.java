package com.example.omega.imageviewer.ui.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.example.omega.imageviewer.ui.dialogs.attention.AttentionDialogFragment;
import com.example.omega.imageviewer.ui.dialogs.cloud_drive_options.OptionsDialogFragment;
import com.example.omega.imageviewer.ui.dialogs.confirm.ConfirmDialogFragment;
import com.omega_r.libs.omegafragmentbuilder.AppOmegaFragmentBuilder;

/**
 * Created by Alexander Chibirev on 5/3/2018.
 */

public class DialogUtils {

    private static final String DIALOG_TAG = "dialog";
    private static final int REQUEST_CODE_TARGET_FRAGMENT = 101;

    public static void showOptionsScreen(Fragment fragment, FragmentManager fragmentManager, boolean isOnlineMode) {
        OptionsDialogFragment attentionDialogFragment = createOptionsDialogFragment(isOnlineMode);
        attentionDialogFragment.setTargetFragment(fragment, REQUEST_CODE_TARGET_FRAGMENT);
        attentionDialogFragment.show(fragmentManager, DIALOG_TAG);
    }

    public static void showAttentionScreen(FragmentManager supportFragmentManager, String message) {
        createAttentionScreen(message).show(supportFragmentManager, DIALOG_TAG);
    }

    public static void showAttentionScreen(Fragment fragment, FragmentManager fragmentManager, String message) {
        AttentionDialogFragment attentionDialogFragment = createAttentionScreen(message);
        attentionDialogFragment.setTargetFragment(fragment, REQUEST_CODE_TARGET_FRAGMENT);
        attentionDialogFragment.show(fragmentManager, DIALOG_TAG);
    }

    public static void showConfirmScreen(FragmentManager supportFragmentManager, String message,
                                         String negativeLabel,
                                         String positiveLabel) {
        ConfirmDialogFragment fragment = createConfirmDialogFragment(message, negativeLabel, positiveLabel);
        fragment.show(supportFragmentManager, DIALOG_TAG);
    }

    private static OptionsDialogFragment createOptionsDialogFragment(boolean isOnlineMode) {
        return AppOmegaFragmentBuilder.optionsDialogFragment().setMode(isOnlineMode).createFragment();
    }

    private static ConfirmDialogFragment createConfirmDialogFragment(String message,
                                                                     String negativeLabel,
                                                                     String positiveLabel) {
        return AppOmegaFragmentBuilder.confirmDialogFragment()
                .setMessage(message)
                .setNegativeLabel(negativeLabel)
                .setPositiveLabel(positiveLabel)
                .createFragment();
    }

    private static AttentionDialogFragment createAttentionScreen(String message) {
        return AppOmegaFragmentBuilder
                .attentionDialogFragment()
                .setMessage(message)
                .createFragment();
    }

    public static void showConfirmScreen(Fragment fragment, FragmentManager fragmentManager,
                                         String message,
                                         String negativeLabel,
                                         String positiveLabel) {
        ConfirmDialogFragment confirmDialogFragment
                = createConfirmDialogFragment(message, negativeLabel, positiveLabel);
        confirmDialogFragment.setTargetFragment(fragment, REQUEST_CODE_TARGET_FRAGMENT);
        confirmDialogFragment.show(fragmentManager, DIALOG_TAG);
    }
}
