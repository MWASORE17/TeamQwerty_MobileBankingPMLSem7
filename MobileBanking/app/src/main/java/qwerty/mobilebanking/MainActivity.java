package qwerty.mobilebanking;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.view.View;
import android.widget.ImageButton;

import qwerty.mobilebanking.Model.ItemObjek;

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
        changeFragment(new fragment_menu());
    }

    private void inisialisasiMenu(){
        ItemObjek transaksi = new ItemObjek("Transaksi",R.drawable.transaksi);
        ItemObjek cekSaldo = new ItemObjek("Cek Saldo",R.drawable.ceksaldo);
        ItemObjek historiTransaksi = new ItemObjek("Histori Transaksi",R.drawable.historitransaksi);
        ItemObjek pengaturanAkun = new ItemObjek("Pengaturan Akun",R.drawable.pengaturanakun);
        ItemObjek.itemMenu.add(transaksi);
        ItemObjek.itemMenu.add(cekSaldo);
        ItemObjek.itemMenu.add(historiTransaksi);
        ItemObjek.itemMenu.add(pengaturanAkun);
    }
    private void changeFragment(Fragment fragment){
        getFragmentManager().beginTransaction().replace(R.id.activity_main,fragment).commit();
    }
}
