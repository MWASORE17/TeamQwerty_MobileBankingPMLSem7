package qwerty.mobilebanking.Fragment;

import android.app.Dialog;
import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import java.util.Objects;

import qwerty.mobilebanking.Model.SessionManager;
import qwerty.mobilebanking.Model.User;
import qwerty.mobilebanking.R;

/**
 * Created by 10 on 6/12/2017.
 */

public class fragment_Setting extends Fragment {
    private Button bt_GantiKodeAkses,bt_Dialog_SubmitKodeAkses;
    private EditText et_Dialog_KodeAkses,et_Dialog_KodeAksesBaru,et_Dialog_reKodeAksesBaru;
    private TextInputLayout til_Dialog_KodeAkses,til_Dialog_KodeAksesBaru,til_Dialog_reKodeAksesBaru;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View _view = inflater.inflate(R.layout.fragment_setting,container,false);
        init(_view);
        event();
        return _view;
    }
    public void init(View view){
        bt_GantiKodeAkses = (Button)view.findViewById(R.id.fragment_setting_button_gantiKodeAkses);
    }
    public void event(){
        bt_GantiKodeAkses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(getActivity());
                dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
                dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
                dialog.setContentView(R.layout.dialog_ganti_kodeakses);
                dialog.show();
                et_Dialog_KodeAkses = (EditText)dialog.findViewById(R.id.dialog_gantiKodeAkses_editText_kodeAkses);
                et_Dialog_KodeAksesBaru = (EditText)dialog.findViewById(R.id.dialog_gantiKodeAkses_editText_kodeAksesBaru);
                et_Dialog_reKodeAksesBaru = (EditText)dialog.findViewById(R.id.dialog_gantiKodeAkses_editText_rekodeAksesBaru);
                bt_Dialog_SubmitKodeAkses = (Button)dialog.findViewById(R.id.dialog_gantiKodeAkses_button_submit);
                til_Dialog_KodeAkses = (TextInputLayout)dialog.findViewById(R.id.dialog_gantiKodeAkses_textInputLayout_kodeAkses);
                til_Dialog_KodeAksesBaru = (TextInputLayout)dialog.findViewById(R.id.dialog_gantiKodeAkses_textInputLayout_kodeAksesBaru);
                til_Dialog_reKodeAksesBaru = (TextInputLayout)dialog.findViewById(R.id.dialog_gantiKodeAkses_textInputLayout_rekodeAksesBaru);
                bt_Dialog_SubmitKodeAkses.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        boolean _isValid = true;
                        til_Dialog_KodeAkses.setErrorEnabled(false);
                        til_Dialog_KodeAksesBaru.setErrorEnabled(false);
                        til_Dialog_reKodeAksesBaru.setErrorEnabled(false);
                        if(TextUtils.isEmpty(et_Dialog_KodeAkses.getText())){
                            _isValid=false;
                            til_Dialog_KodeAkses.setErrorEnabled(true);;
                            til_Dialog_KodeAkses.setError("Kode Akses Tidak Boleh Kosong");
                        }
                        else if(et_Dialog_KodeAkses.getText().length()<8){
                            _isValid=false;
                            til_Dialog_KodeAkses.setErrorEnabled(true);;
                            til_Dialog_KodeAkses.setError("Kode Akses Minimal 8 Karakter");
                        }else if(!Objects.equals(et_Dialog_KodeAkses.getText().toString(), User.loggedInUser.getKodeAkses())){
                            _isValid=false;
                            til_Dialog_KodeAkses.setErrorEnabled(true);;
                            til_Dialog_KodeAkses.setError("Kode Akses Salah");
                        }
                        else if(TextUtils.isEmpty(et_Dialog_KodeAksesBaru.getText())){
                            _isValid=false;
                            til_Dialog_KodeAksesBaru.setErrorEnabled(true);;
                            til_Dialog_KodeAksesBaru.setError("Kode Akses Baru Tidak Boleh Kosong");
                        }
                        else if(et_Dialog_KodeAksesBaru.getText().length()<8){
                            _isValid=false;
                            til_Dialog_KodeAksesBaru.setErrorEnabled(true);;
                            til_Dialog_KodeAksesBaru.setError("Kode Akses Baru Minimal 8 Karakter");
                        }
                        else if(!Objects.equals(et_Dialog_KodeAksesBaru.getText().toString(), et_Dialog_reKodeAksesBaru.getText().toString())){
                            _isValid=false;
                            til_Dialog_reKodeAksesBaru.setErrorEnabled(true);;
                            til_Dialog_reKodeAksesBaru.setError("Kode Akses Tidak Sesuai");
                        }
                        if(_isValid){
                            User.loggedInUser.setKodeAkses(et_Dialog_KodeAksesBaru.getText().toString());
                            User.updateUser(User.loggedInUser);
                            SessionManager.with(getActivity()).updateUser(User.loggedInUser);
                            dialog.dismiss();
                        }
                    }
                });
            }
        });
    }
}
