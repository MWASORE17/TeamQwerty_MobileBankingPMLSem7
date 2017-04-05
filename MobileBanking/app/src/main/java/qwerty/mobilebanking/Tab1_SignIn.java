package qwerty.mobilebanking;

/**
 * Created by Rico Wu on 19/03/2017.
 */
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

public class Tab1_SignIn  extends Fragment{
    private Button loginButton;
    private EditText etNoRekening;
    private EditText etKodeAkses;
    private SessionManager session;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab1_signin, container, false);
        session = new SessionManager(getActivity());
        loginButton = (Button)rootView.findViewById(R.id.buttonLogin);
        etNoRekening=(EditText)rootView.findViewById(R.id.editText);
        etKodeAkses=(EditText)rootView.findViewById(R.id.editText2);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Objects.equals(etNoRekening.getText().toString(), "admin")&& Objects.equals(etKodeAkses.getText().toString(), "admin")){
                    session.loginUser(etNoRekening.getText().toString(),etKodeAkses.getText().toString());
                    Intent intent = new Intent(getActivity(),menuAwal.class);
                    startActivity(intent);
                }
            }
        });

        return rootView;
    }
}

