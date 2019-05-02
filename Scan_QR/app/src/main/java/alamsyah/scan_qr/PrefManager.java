package alamsyah.scan_qr;

import android.content.Context;
import android.content.SharedPreferences;


public class PrefManager {

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;

    int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "CODE";
    private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";

    private static final String MESSAGE = "Message";
    private static final String STATUS = "Status";
    private static final String CHOSE = "Chose";

    public PrefManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }
    public String getStatus() {
        return pref.getString(STATUS, "empty");
    }
    public void setStatus(String status) {
        editor.putString(STATUS, status);
        editor.commit();
    }
    public String getMESSAGE() {
        return pref.getString(MESSAGE, "empty");
    }
    public void setMessage(String message) {
        editor.putString(MESSAGE, message);
        editor.commit();
    }

    public String getCHOSE() {
        return pref.getString(CHOSE, "empty");
    }
    public void setChose(String chose) {
        editor.putString(CHOSE, chose);
        editor.commit();
    }
    public void setFirstTimeLaunch(boolean isFirstTime) {
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.commit();
    }

    public boolean isFirstTimeLaunch() {
        return pref.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }



}
