package com.example.omega.imageviewer.mvp.models;

import android.content.res.Resources;
import android.support.annotation.StringRes;

import java.io.Serializable;

/**
 * Created by Alexander Chibirev on 4/22/2018.
 */

public interface Text extends Serializable {

    static Text from(String string) {
        return new StringText(string);
    }

    static Text from(@StringRes int stringRes) {
        return new ResourceText(stringRes);
    }

    static Text from(@StringRes int stringRes, Object... formatArgs) {
        return new FormatResourceText(stringRes, formatArgs);
    }

    String getString(Resources resources);

    boolean isEmpty();



    class StringText implements Text {

        private final String string;

        StringText(String string) {
            this.string = string;
        }

        @Override
        public String getString(Resources resources) {
            return string;
        }

        @Override
        public boolean isEmpty() {
            return string == null || string.isEmpty();
        }
    }

    class ResourceText implements Text {

        private final int stringRes;

        ResourceText(@StringRes int stringRes) {
            this.stringRes = stringRes;
        }

        @Override
        public String getString(Resources resources) {
            return resources.getString(stringRes);
        }

        @Override
        public boolean isEmpty() {
            return stringRes == 0;
        }

    }

    class FormatResourceText implements Text {

        private final int stringRes;
        private final Object[] args;

        FormatResourceText(@StringRes int stringRes, Object... formatArgs) {
            this.stringRes = stringRes;
            this.args = formatArgs;
        }

        @Override
        public String getString(Resources resources) {
            return resources.getString(stringRes, args);
        }

        @Override
        public boolean isEmpty() {
            return stringRes == 0;
        }
    }

}