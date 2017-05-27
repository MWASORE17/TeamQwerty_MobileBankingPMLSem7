package qwerty.mobilebanking.Activity;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

import qwerty.mobilebanking.Fragment.Fragment_Home;
import qwerty.mobilebanking.Model.ItemObjek;
import qwerty.mobilebanking.Model.User;
import qwerty.mobilebanking.R;
import qwerty.mobilebanking.Model.SessionManager;

/**
 * Created by 10 on 4/6/2017.
 */

public class MainActivity extends Activity {
    private SessionManager session;
    private ImageButton logOutButton;
    private AppBarLayout _appBarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        session = new SessionManager(getApplicationContext());
        session.checkLogin();
        logOutButton = (ImageButton) findViewById(R.id.btnLogout);
        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                session.logOut();
            }
        });
        this.setTitle("Menu");
        inisialisasiMenu();
        inisialisasiUser();
        changeFragment(new Fragment_Home());
    }

    private void inisialisasiMenu(){
        ItemObjek transaksi = new ItemObjek("Transaksi" ,R.drawable.transaksi);
        ItemObjek cekSaldo = new ItemObjek("Cek Saldo",R.drawable.ceksaldo);
        ItemObjek historiTransaksi = new ItemObjek("Histori Transaksi",R.drawable.historitransaksi);
        ItemObjek pengaturanAkun = new ItemObjek("Pengaturan Akun",R.drawable.pengaturanakun);
        ItemObjek.itemMenu.clear();
        ItemObjek.itemMenu.add(transaksi);
        ItemObjek.itemMenu.add(cekSaldo);
        ItemObjek.itemMenu.add(historiTransaksi);
        ItemObjek.itemMenu.add(pengaturanAkun);
    }
    private void inisialisasiUser(){
        User a = new User("1111111111111111","password");
        User b = new User("1111111111111112","password");
        User c = new User("1111111111111113","password");
        User d = new User("1111111111111114","password");
        User e = new User("1111111111111115","password");
        User.users.clear();
        User.users.add(a);
        User.users.add(b);
        User.users.add(c);
        User.users.add(d);
        User.users.add(e);
    }
    private void changeFragment(Fragment fragment){
        getFragmentManager().beginTransaction().replace(R.id.activity_main,fragment).commit();
    }

}
