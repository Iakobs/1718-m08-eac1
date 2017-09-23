package ibanez.jacob.cat.xtec.ioc.eac1_2017s1.util;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * Utility class with some utility methods
 *
 * @author <a href="mailto:jacobibanez@jacobibanez.com">Jacob Ibáñez Sánchez</a>.
 */
public class Utils {

    /**
     * A method for hiding the Android virtual keyboard
     *
     * @param activity The activity which owns the keyboard to hide
     */
    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        View currentFocus = activity.getCurrentFocus();
        if (inputManager != null && currentFocus != null) {
            inputManager.hideSoftInputFromWindow(currentFocus.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /**
     * Method that checks if the text from an {@link EditText} widget is not empty.
     * <p>
     * If the text is empty, returns {@code null}, otherwise returns the text as is.
     *
     * @param text The text to be checked.
     * @return See method description
     */
    public static String getTextFromEditText(String text) {
        if ("".equals(text.trim()) || text.isEmpty()) {
            return null;
        } else {
            return text;
        }
    }
}
