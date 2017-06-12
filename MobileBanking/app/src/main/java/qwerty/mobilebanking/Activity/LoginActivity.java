package qwerty.mobilebanking.Activity;

import android.content.pm.ActivityInfo;
import android.media.MediaCodec;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;

import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import qwerty.mobilebanking.Animation.SlideUpTransformer;
import qwerty.mobilebanking.Model.User;
import qwerty.mobilebanking.R;
import qwerty.mobilebanking.Model.SessionManager;
import qwerty.mobilebanking.Fragment.Tab1_SignIn;
import qwerty.mobilebanking.Fragment.Tab2__SignUp;

public class LoginActivity extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    public LoginActivity(){}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //buat dia full screen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button button = (Button)findViewById(R.id.button);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setVisibility(View.GONE);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setPageTransformer(false, new SlideUpTransformer());
        mViewPager.setAdapter(mSectionsPagerAdapter);

       TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
       tabLayout.setupWithViewPager(mViewPager);  //hubungkan viewpager dengan tab
        //endregion

    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }
        @Override
        public Fragment getItem(int position) {
           switch (position)
           {
               case 0:
                   Tab1_SignIn tab1 = new Tab1_SignIn();
                   return tab1;
               case 1:
                   Tab2__SignUp tab2 = new Tab2__SignUp();
                   return tab2;
               default:
                   return null;
           }

        }
        @Override
        public int getCount() {
            // Show 2 total pages.
            return 2;
        }

        /*@Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "S I G N   I N";
                case 1:
                    return "S I G N   U P";
                default:
                    return null;
            }
        }*/
    }

    public static boolean isNoRekValid(String noRek){
        String _expression = "\\b\\d{4}(| |-)\\d{4}\\1\\d{4}\\1\\d{4}\\b";
        CharSequence _noRek = noRek;
        Pattern _pattern = Pattern.compile(_expression);
        Matcher _matcher = _pattern.matcher(_noRek);
        if(_matcher.matches()){
            return true;
        }
        return false;
    }
    public static boolean isKodeAksesValid(String kodeAkses){
        String _expression = "\"^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$\"";
        CharSequence _kodeAkses = kodeAkses;
        Pattern _pattern = Pattern.compile(_expression);
        Matcher _matcher = _pattern.matcher(_kodeAkses);
        if(_matcher.matches()){
            return true;
        }
        return false;
    }
}
