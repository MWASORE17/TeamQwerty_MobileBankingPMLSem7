package qwerty.mobilebanking.Activity;


import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

import qwerty.mobilebanking.Animation.BottomNavigationHelper;
import qwerty.mobilebanking.Fragment.Fragment_Home;
import qwerty.mobilebanking.Fragment.Fragment_Transfer;
import qwerty.mobilebanking.Fragment.fragment_CekSaldo;
import qwerty.mobilebanking.Fragment.fragment_Setting;
import qwerty.mobilebanking.Fragment.fragment_history;
import qwerty.mobilebanking.R;

public class SubMenu extends AppCompatActivity{
    private BottomNavigationView bottomNavigation;
    private Fragment fragment;
    private FragmentManager fragmentManager;
    private ImageButton _imageButton;
    private TextView test;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_menu);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        bottomNavigation = (BottomNavigationView) findViewById(R.id.navigation);
        BottomNavigationHelper.disableShiftMode(bottomNavigation);
        int posisi = getIntent().getExtras().getInt("posisi");
        bottomNavigation.getMenu().getItem(0).setChecked(false);

        //inisialisasi fragment pertama

        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener()  {

            public String title;

            private void setTitle(String title) {
                this.title = title;
            }

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                bottomNavigation.getMenu().getItem(0).setChecked(false);
                bottomNavigation.getMenu().getItem(1).setChecked(false);
                bottomNavigation.getMenu().getItem(2).setChecked(false);
                bottomNavigation.getMenu().getItem(3).setChecked(false);

                switch (id) {
                    case R.id.navigation_cek_saldo:
                        getFragmentManager().beginTransaction().replace(R.id.content ,new fragment_CekSaldo() ).commit();
                        bottomNavigation.getMenu().getItem(0).setChecked(true);

                        return true;
                    case R.id.navigation_transfer:
                        getFragmentManager().beginTransaction().replace(R.id.content ,new Fragment_Transfer() ).commit();
                        bottomNavigation.getMenu().getItem(1).setChecked(true);
                        return true;
                    case R.id.navigation_history:
                        getFragmentManager().beginTransaction().replace(R.id.content ,new fragment_history()).commit();
                        bottomNavigation.getMenu().getItem(2).setChecked(true);
                        return true;
                    case R.id.navigation_setting:
                        getFragmentManager().beginTransaction().replace(R.id.content ,new fragment_Setting()).commit();
                        bottomNavigation.getMenu().getItem(3).setChecked(true);
                        return true;
                }
                return false;
            }
        });
        if(posisi == 0) {
            getFragmentManager().beginTransaction().replace(R.id.content, new fragment_CekSaldo()).commit();
            bottomNavigation.getMenu().getItem(posisi).setChecked(true);
        }
        else if(posisi ==1) {
            getFragmentManager().beginTransaction().replace(R.id.content, new Fragment_Transfer()).commit();
            bottomNavigation.getMenu().getItem(posisi).setChecked(true);
        }
        else if(posisi ==2) {
            getFragmentManager().beginTransaction().replace(R.id.content, new fragment_history()).commit();
            bottomNavigation.getMenu().getItem(posisi).setChecked(true);
        }
        else if(posisi ==3) {
            getFragmentManager().beginTransaction().replace(R.id.content, new fragment_Setting()).commit();
            bottomNavigation.getMenu().getItem(posisi).setChecked(true);
        }
    }
}