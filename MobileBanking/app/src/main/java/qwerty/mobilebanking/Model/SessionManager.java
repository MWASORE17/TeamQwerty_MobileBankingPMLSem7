package qwerty.mobilebanking.Model;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import qwerty.mobilebanking.Activity.LoginActivity;
import qwerty.mobilebanking.Activity.MainActivity;

/**
 * Created by 10 on 4/2/2017. by Andy Willianto
 */

public class SessionManager{
    SharedPreferences sharePref;
    SharedPreferences.Editor editor;

    Context context;

    private static final String FILE_PREF = "UserPref";
    private static final String LOGGED_IN = "IsLoggedIn";
    private static final String KODE_AKSES = "KodeAkses";
    private static final String NOMOR_REKENING = "NoRekening";
    public SessionManager(Context context){
        this.context = context;
        sharePref = context.getSharedPreferences(FILE_PREF,0);
        editor = sharePref.edit();
    }

    public void checkLogin(){
        if(!sharePref.getBoolean("IsLoggedIn", false)){
            Intent intent = new Intent(context,LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            context.startActivity(intent);
        }
    }
    public void loginUser(String noRekening,String kodeAkses){
        editor.putBoolean(LOGGED_IN,true);
        editor.putString(NOMOR_REKENING,noRekening);
        editor.putString(KODE_AKSES,kodeAkses);
        editor.commit();
        Intent intent = new Intent(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }
    public void logOut(){
        editor.clear();
        editor.commit();
        Intent intent = new Intent(context,LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }
    public boolean isLoggedIn(){
        return sharePref.getBoolean("IsLoggedIn", false);
    }
}

