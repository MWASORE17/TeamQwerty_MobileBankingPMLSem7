package qwerty.mobilebanking.Fragment;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

import qwerty.mobilebanking.Activity.LoginActivity;
import qwerty.mobilebanking.Model.User;
import qwerty.mobilebanking.R;

/**
 * Created by Rico Wu on 19/03/2017.
 */

public class Tab2__SignUp  extends Fragment {

    private TextInputLayout til_noRek,til_kodeAkses,til_reKodeAkses;
    private EditText et_noRek,et_kodeAkses,et_reKodeAkses;
    private Button btn_register;
    private Typeface _typeFaceRL;
    private ViewPager vPager;
    private Snackbar snackbar;
    //private DatabaseHandler db;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View _view = inflater.inflate(R.layout.fragment_home_tab2_signup, container, false);
        init(_view);
        event();
        return _view;
    }
    
    private void init(View view){
        //db = new DatabaseHandler(getActivity());
        vPager = (ViewPager)getActivity().findViewById(R.id.container);
        _typeFaceRL = Typeface.createFromAsset(getActivity().getAssets(),"fonts/robotolight.ttf");
        btn_register = (Button) view.findViewById(R.id.register_button);
        btn_register.setTypeface(_typeFaceRL);
        btn_register.setText("REGISTER");
        til_noRek = (TextInputLayout)view.findViewById(R.id.fragment_signup_textInputLayout_noRek);
        til_kodeAkses = (TextInputLayout)view.findViewById(R.id.fragment_signup_textInputLayout_kodeAkses);
        til_reKodeAkses = (TextInputLayout)view.findViewById(R.id.fragment_signup_textInputLayout_reKodeAkses);
        et_noRek = (EditText)view.findViewById(R.id.fragment_signup_editText_noRek);
        et_kodeAkses = (EditText)view.findViewById(R.id.fragment_signup_editText_kodeAkses);
        et_reKodeAkses = (EditText)view.findViewById(R.id.fragment_signup_editText_reKodeAkses);
        snackbar = Snackbar.make(getActivity().findViewById(R.id.activity_login),"Registrasi Berhasil",Snackbar.LENGTH_LONG);
    }

    private void event(){
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean _isvalid = true;
                til_noRek.setErrorEnabled(false);
                til_kodeAkses.setErrorEnabled(false);
                til_reKodeAkses.setErrorEnabled(false);

                if(TextUtils.isEmpty(et_noRek.getText())){
                    _isvalid=false;
                    til_noRek.setErrorEnabled(true);
                    til_noRek.setError("Nomor Rekening Tidak Boleh Kosong");
                }
                else if(et_noRek.getText().toString().length()!=11){
                    _isvalid=false;
                    til_noRek.setErrorEnabled(true);
                    til_noRek.setError("Nomor Rekening Harus Berisi 11 Digit angka");
                }
                else if(isRegisteredAlready(et_noRek.getText().toString())){
                    _isvalid=false;
                    til_noRek.setErrorEnabled(true);
                    til_noRek.setError("Nomor Rekening Telah Terdaftar");
                }
                else if(TextUtils.isEmpty(et_kodeAkses.getText())){
                    _isvalid=false;
                    til_kodeAkses.setErrorEnabled(true);
                    til_kodeAkses.setError("Kode Akses Tidak Boleh Kosong");
                }
                else if(et_kodeAkses.getText().toString().length()<8){
                    _isvalid=false;
                    til_kodeAkses.setErrorEnabled(true);
                    til_kodeAkses.setError("Kode Akses Minimal 8 Karakter");
                }
                else if(LoginActivity.isNoRekValid(et_kodeAkses.getText().toString())){
                    _isvalid=false;
                    til_kodeAkses.setErrorEnabled(true);
                    til_kodeAkses.setError("KodeAkses Harus Berisi Minimal 1 Angka, 1 Huruf Besar, 1 Huruf Kecil");
                }

                else if(TextUtils.isEmpty(et_reKodeAkses.getText())){
                    _isvalid=false;
                    til_reKodeAkses.setErrorEnabled(true);
                    til_reKodeAkses.setError("Input Ulang Kode Akses");
                }

                else if(!Objects.equals(et_kodeAkses.getText().toString(), et_reKodeAkses.getText().toString())){
                    _isvalid=false;
                    til_reKodeAkses.setErrorEnabled(true);
                    til_reKodeAkses.setError("Kode Akses Tidak Cocok");
                }

                if(_isvalid){
                    User.users.add(new User(et_noRek.getText().toString(),et_kodeAkses.getText().toString(),0,"123456"));
                    //db.addUser(new User(et_noRek.getText().toString(),et_kodeAkses.getText().toString(),0));
                    et_noRek.setText("");
                    et_kodeAkses.setText("");
                    et_reKodeAkses.setText("");
                    snackbar.show();
                    vPager.setCurrentItem(0,true);
                }
            }
        });
    }

    private boolean isRegisteredAlready(String nomorRekening){
        for(User user : User.users){
            if (Objects.equals(user.getNoRek(), et_noRek.getText().toString())){
                return true;
            }
        }
        return false;
    }

}
