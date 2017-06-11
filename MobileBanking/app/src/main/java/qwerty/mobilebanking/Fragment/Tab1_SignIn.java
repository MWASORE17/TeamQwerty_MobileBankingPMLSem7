package qwerty.mobilebanking.Fragment;

/**
 * Created by Rico Wu on 19/03/2017.
 */
import android.graphics.Typeface;
import android.provider.ContactsContract;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Objects;

import qwerty.mobilebanking.Activity.MainActivity;
import qwerty.mobilebanking.Model.User;
import qwerty.mobilebanking.R;
import qwerty.mobilebanking.Model.SessionManager;

public class Tab1_SignIn  extends Fragment{
    private Button loginButton;
    private TextInputLayout til_noRek, til_kodeAkses;
    private EditText etNoRekening;
    private EditText etKodeAkses;
    private SessionManager session;
    private Typeface _typeFaceRL;
    private ArrayList<User> listUser;
    private User userLogin;

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
        til_noRek=(TextInputLayout)rootView.findViewById(R.id.fragment_signin_textInputLayout_noRek);
        til_kodeAkses=(TextInputLayout)rootView.findViewById(R.id.fragment_signin_textInputLayout_kodeAkses);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*if(Objects.equals(etNoRekening.getText().toString(), "123456789")&& Objects.equals(etKodeAkses.getText().toString(), "admin")){
                    session.loginUser(etNoRekening.getText().toString(),etKodeAkses.getText().toString());
                }*/
                boolean _isvalid = true;
                til_noRek.setErrorEnabled(false);
                til_kodeAkses.setErrorEnabled(false);
                if(TextUtils.isEmpty(etNoRekening.getText().toString())){
                    _isvalid=false;
                    til_noRek.setErrorEnabled(true);
                    til_noRek.setError("Nomor Rekening Tidak Boleh Kosong");
                }
                else if(etNoRekening.getText().toString().length()!=11){
                    _isvalid=false;
                    til_noRek.setErrorEnabled(true);
                    til_noRek.setError("Nomor Rekening Terdiri Dari 11 Digit Angka");
                }
                else if(TextUtils.isEmpty(etKodeAkses.getText().toString())){
                    _isvalid=false;
                    til_kodeAkses.setErrorEnabled(true);
                    til_kodeAkses.setError("Kode Akses Tidak Boleh Kosong");
                }
                else if (etKodeAkses.getText().toString().length()<8){
                    _isvalid=false;
                    til_kodeAkses.setErrorEnabled(true);
                    til_kodeAkses.setError("Password Terlalu Pendek");
                }
                if(_isvalid){
                    boolean _isregistered = false,_ismatch = false;
                    for(User user : User.users){
                        Log.d("array",user.getNoRek());
                        if(Objects.equals(user.getNoRek(), etNoRekening.getText().toString())){
                            if(Objects.equals(user.getKodeAkses(), etKodeAkses.getText().toString())){
                                _ismatch=true;
                                userLogin = user;
                            }
                            _isregistered = true;
                            break;
                        }
                    }

                    if(!_isregistered){
                        til_noRek.setErrorEnabled(true);
                        til_noRek.setError("Nomor Rekening Belum Terdaftar");
                    }
                    else if(!_ismatch){
                        til_kodeAkses.setErrorEnabled(true);
                        til_kodeAkses.setError("Kode Akses Salah");
                    }
                    if(_isregistered && _ismatch){
                        session.loginUser(userLogin);
                    }
                }
            }
        });

        return rootView;
    }

}

