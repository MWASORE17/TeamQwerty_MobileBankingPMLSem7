package qwerty.mobilebanking.Fragment;

/**
 * Created by Rico Wu on 19/03/2017.
 */
import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.Objects;

import qwerty.mobilebanking.R;
import qwerty.mobilebanking.Model.SessionManager;

public class Tab1_SignIn  extends Fragment{
    private Button loginButton;
    private EditText etNoRekening;
    private EditText etKodeAkses;
    private SessionManager session;
    private Typeface _typeFaceRL;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home_tab1_signin, container, false);
        session = new SessionManager(getActivity());

        loginButton = (Button)rootView.findViewById(R.id.buttonLogin);
        _typeFaceRL = Typeface.createFromAsset(getActivity().getAssets(), "fonts/robotolight.ttf");

        loginButton.setTypeface(_typeFaceRL);
        loginButton.setText("AUTHENTICATE");

        etNoRekening=(EditText)rootView.findViewById(R.id.editText);
        etKodeAkses=(EditText)rootView.findViewById(R.id.editText2);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Objects.equals(etNoRekening.getText().toString(), "123456789")&& Objects.equals(etKodeAkses.getText().toString(), "admin")){
                    session.loginUser(etNoRekening.getText().toString(),etKodeAkses.getText().toString());
                }
            }
        });

        return rootView;
    }

}

