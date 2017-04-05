package qwerty.mobilebanking;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

/**
 * Created by 10 on 4/6/2017.
 */

public class menuAwal extends Activity {
    private SessionManager session;
    private Button logOutButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_awal);
        session = new SessionManager(getApplicationContext());
        session.checkLogin();
        logOutButton = (Button)findViewById(R.id.btnLogout);
        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                session.logOut();
            }
        });
    }
}
