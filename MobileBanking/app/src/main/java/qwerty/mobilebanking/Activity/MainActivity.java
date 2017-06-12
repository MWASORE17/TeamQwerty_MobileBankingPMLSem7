package qwerty.mobilebanking.Activity;

import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Objects;

import qwerty.mobilebanking.Fragment.Fragment_Home;
import qwerty.mobilebanking.Fragment.Fragment_Transfer;
import qwerty.mobilebanking.Model.ItemObjek;
import qwerty.mobilebanking.Model.User;
import qwerty.mobilebanking.R;
import qwerty.mobilebanking.Model.SessionManager;

/**
 * Created by 10 on 4/6/2017.
 */

public class MainActivity extends Activity {
    public SessionManager session;
    private ImageButton logOutButton;
    private AppBarLayout _appBarLayout;
    private TextInputLayout til_kodeAkses;
    private EditText et_kodeAkses;
    private Button bt_kodeAkses;
    private Button bt_cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        init();
        CheckLogin();
        event();
        inisialisasiMenu();
        inisialisasiUser();
        //changeFragment(new Fragment_Transfer());//<=============================GANTI FRAGMENT AWAL
        changeFragment(new Fragment_Home());
    }
    private void init(){
        logOutButton = (ImageButton) findViewById(R.id.btnLogout);
        session = new SessionManager(getApplicationContext());
    }
    private void event(){
        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                session.logOut();
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                getApplicationContext().startActivity(intent);
            }
        });
    }

    private void inisialisasiMenu(){
        ItemObjek transaksi = new ItemObjek("Transaksi" ,R.drawable.transaksi);
        ItemObjek cekSaldo = new ItemObjek("Cek Saldo",R.drawable.ceksaldo);
        ItemObjek historiTransaksi = new ItemObjek("Histori Transaksi",R.drawable.historitransaksi);
        ItemObjek pengaturanAkun = new ItemObjek("Pengaturan Akun",R.drawable.pengaturanakun);
        ItemObjek.itemMenu.clear();
        ItemObjek.itemMenu.add(cekSaldo);
        ItemObjek.itemMenu.add(transaksi);
        ItemObjek.itemMenu.add(historiTransaksi);
        ItemObjek.itemMenu.add(pengaturanAkun);
    }

    private void changeFragment(Fragment fragment){
        getFragmentManager().beginTransaction().replace(R.id.activity_main,fragment).commit();
    }

    private void CheckLogin(){
        User.loggedInUser = session.getUser();
        if(session.checkLogin()){
            final Dialog dialog = new Dialog(this);
            dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
            dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
            dialog.setContentView(R.layout.dialog_kodeakses);
            dialog.setCancelable(false);
            dialog.show();
            til_kodeAkses = (TextInputLayout) dialog.findViewById(R.id.dialog_textInputLayout_kodeAkses);
            et_kodeAkses = (EditText)dialog.findViewById(R.id.dialog_editText_kodeAkses);
            bt_kodeAkses = (Button)dialog.findViewById(R.id.dialog_button_submit);
            bt_cancel = (Button)dialog.findViewById(R.id.dialog_button_cancel);
            bt_kodeAkses.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(Objects.equals(et_kodeAkses.getText().toString(), session.getUser().getKodeAkses())){
                        dialog.dismiss();
                    }
                    else {
                        til_kodeAkses.setErrorEnabled(true);
                        til_kodeAkses.setError("Kode Akses Salah");
                    }
                }
            });
            bt_cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                    System.exit(0);
                }
            });
        }
        else {
            Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            getApplicationContext().startActivity(intent);
        }
    }
    private void inisialisasiUser(){
        User a = new User("11111111111","password",100001,"123456" ,"Shendy Lim");
        User Rico = new User("12345678901","password",10000000,"526253", "Rico Wu");
        User b = new User("11111111112","password",100002,"123456","Sasa Marisa");
        User c = new User("11111111113","password",100003,"123456","Mantan Terindah");
        User d = new User("11111111114","password",100004,"123456","Fera");
        User e = new User("11111111115","password",100005,"123456","Jessica Wijaya");
        User.users.clear();
        User.users.add(a);
        User.users.add(b);
        User.users.add(c);
        User.users.add(d);
        User.users.add(e);
        User.users.add(Rico);
    }

    /*@Override
    protected void onStop() {
        super.onStop();
        session.logOut();
    }*/
}
