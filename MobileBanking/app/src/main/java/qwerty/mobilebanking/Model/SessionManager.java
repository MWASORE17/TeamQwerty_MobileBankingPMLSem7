package qwerty.mobilebanking.Model;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.google.gson.Gson;

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
    private static final String USER_DATA = "userdata";
    public SessionManager(Context context){
        this.context = context;
        this.sharePref = context.getSharedPreferences(FILE_PREF,0);
        this.editor = sharePref.edit();
    }

    public static SessionManager with(Context context){
        return new SessionManager(context);
    }

    public /*void*/ boolean checkLogin(){
        if(!sharePref.getBoolean(LOGGED_IN, false)){
            /*Intent intent = new Intent(context,LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            context.startActivity(intent);*/
            return false;
        }
        else {
            return true;
        }
    }
    public void loginUser(User user){
        editor.putBoolean(LOGGED_IN,true);
        editor.putString(USER_DATA,new Gson().toJson(user));
        editor.commit();
        Intent intent = new Intent(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }
    public User getUser(){
        return new Gson().fromJson(sharePref.getString(USER_DATA, ""),User.class);
    }
    public void updateUser(User user){
        editor.putString(USER_DATA,new Gson().toJson(user));
        editor.commit();
    }
    public void logOut(){
        editor.clear();
        editor.commit();
    }
}

