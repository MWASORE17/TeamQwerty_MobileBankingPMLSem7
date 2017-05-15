package qwerty.mobilebanking;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by 10 on 4/6/2017.
 */

public class MainActivity extends Activity {
    private SessionManager session;
    private ImageButton logOutButton;
    private AppBarLayout _appBarLayout;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
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




    }
}
