package com.example.omega.imageviewer;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.omega.imageviewer.models.Preferences;
import com.example.omega.imageviewer.models.Text;
import com.example.omega.imageviewer.models.UserManager;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Alexander Chibirev on 4/28/2018.
 */

@RunWith(AndroidJUnit4.class)
public class ModelsTests {

    private Context mContext = InstrumentationRegistry.getContext();
    private Context mTargetContext = InstrumentationRegistry.getTargetContext();
    private static final String TOKEN = "token";

    @Test
    public void PreferencesTest() {
        Preferences preferences = new Preferences(mContext);
        assertNull(preferences.getToken());
        preferences.setToken(TOKEN);
        assertNotNull(preferences.getToken());
        assertEquals(preferences.getToken(), TOKEN);

    }

    private Preferences getPreferencesWithToken() {
        Preferences preferences = new Preferences(mContext);  //create pref with token
        preferences.setToken(TOKEN);
        return preferences;
    }

    @Test
    public void ShouldCorrectWorkUserManagerClass() {
        UserManager userManager = new UserManager(getPreferencesWithToken());
        assertTrue(userManager.isAuthorized()); //correct authorized
        userManager.logout();
        assertFalse(userManager.isAuthorized()); //correct logout
        UserManager userManagerWithoutToken = new UserManager(new Preferences(mContext));
        assertFalse(userManagerWithoutToken.isAuthorized()); //correct behavior without token
    }

    @Test
    public void TextClassTest() {
        Text textRes;
        textRes = Text.from(R.string.app_name); // res to text, text to string
        Assert.assertFalse(textRes.isEmpty());
        Assert.assertEquals(textRes.getString(mTargetContext.getResources()), mTargetContext.getString(R.string.app_name));

        Text textString = Text.from("Hello"); //string to text, text to string
        textString.getString(mTargetContext.getResources());
        Assert.assertFalse(textString.isEmpty());
        Assert.assertEquals(textString.getString(mTargetContext.getResources()), "Hello");
    }
}
