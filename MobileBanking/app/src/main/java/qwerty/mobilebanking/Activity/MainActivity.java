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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        init();
        event();
        inisialisasiMenu();
        //inisialisasiUser();
        //session.checkLogin();
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

    @Override
    protected void onStop() {
        super.onStop();
        session.logOut();
    }
}
