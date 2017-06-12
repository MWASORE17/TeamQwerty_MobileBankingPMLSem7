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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

import qwerty.mobilebanking.Model.HistoriTransaksi;
import qwerty.mobilebanking.Model.SessionManager;
import qwerty.mobilebanking.Model.User;
import qwerty.mobilebanking.R;

/**
 * Created by 10 on 6/12/2017.
 */

public class fragment_Setting extends Fragment implements View.OnClickListener {
    private LinearLayout pinKeyboardLayout;
    private TextView judulDialog;
    private String pin, pinBaru, rePinBaru;
    private int state;
    private ArrayList<ImageView> listLingkar;
    private Button bt_GantiKodeAkses,bt_Dialog_SubmitKodeAkses,bt_gantiPin;
    private EditText et_Dialog_KodeAkses,et_Dialog_KodeAksesBaru,et_Dialog_reKodeAksesBaru;
    private TextInputLayout til_Dialog_KodeAkses,til_Dialog_KodeAksesBaru,til_Dialog_reKodeAksesBaru;
    private Dialog dialog;
    private Button bt_pin0,bt_pin1,bt_pin2,bt_pin3,bt_pin4,bt_pin5,bt_pin6,bt_pin7,bt_pin8,bt_pin9,bt_pinCancel;
    private ImageView iv_pin1,iv_pin2,iv_pin3,iv_pin4,iv_pin5,iv_pin6;
    private ImageButton bt_pinBackSpace;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View _view = inflater.inflate(R.layout.fragment_setting,container,false);
        init(_view);
        eventGantiKodeAkses();
        eventGantiPin();
        return _view;
    }
    public void init(View view){
        bt_GantiKodeAkses = (Button)view.findViewById(R.id.fragment_setting_button_gantiKodeAkses);
        bt_gantiPin = (Button)view.findViewById(R.id.fragment_setting_button_gantipin);
    }
    public void eventGantiKodeAkses(){
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
    public void eventGantiPin(){
        bt_gantiPin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pin="";
                state = 0;
                dialogPin();
            }
        });

    }
    private void dialogPin(){
        dialog = new Dialog(getActivity(),android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        dialog.setContentView(R.layout.fragment_pin_view);
        dialog.show();
        pinKeyboardLayout = (LinearLayout)dialog.findViewById(R.id.dialog_pin_layoutKeyboard);
        judulDialog = (TextView)dialog.findViewById(R.id.dialog_pin_textView_judul);
        bt_pin0 = (Button)dialog.findViewById(R.id.dialog_pin_button0);
        bt_pin0.setOnClickListener(this);
        bt_pin1 = (Button)dialog.findViewById(R.id.dialog_pin_button1);
        bt_pin1.setOnClickListener(this);
        bt_pin2 = (Button)dialog.findViewById(R.id.dialog_pin_button2);
        bt_pin2.setOnClickListener(this);
        bt_pin3 = (Button)dialog.findViewById(R.id.dialog_pin_button3);
        bt_pin3.setOnClickListener(this);
        bt_pin4 = (Button)dialog.findViewById(R.id.dialog_pin_button4);
        bt_pin4.setOnClickListener(this);
        bt_pin5 = (Button)dialog.findViewById(R.id.dialog_pin_button5);
        bt_pin5.setOnClickListener(this);
        bt_pin6 = (Button)dialog.findViewById(R.id.dialog_pin_button6);
        bt_pin6.setOnClickListener(this);
        bt_pin7 = (Button)dialog.findViewById(R.id.dialog_pin_button7);
        bt_pin7.setOnClickListener(this);
        bt_pin8 = (Button)dialog.findViewById(R.id.dialog_pin_button8);
        bt_pin8.setOnClickListener(this);
        bt_pin9 = (Button)dialog.findViewById(R.id.dialog_pin_button9);
        bt_pin9.setOnClickListener(this);
        bt_pinCancel = (Button)dialog.findViewById(R.id.dialog_pin_buttonCancel);
        bt_pinCancel.setOnClickListener(this);
        bt_pinBackSpace = (ImageButton)dialog.findViewById(R.id.dialog_pin_ImagebuttonBackSpace);
        bt_pinBackSpace.setOnClickListener(this);
        iv_pin1 = (ImageView)dialog.findViewById(R.id.dialog_pin_lingkar1);
        iv_pin2 = (ImageView)dialog.findViewById(R.id.dialog_pin_lingkar2);
        iv_pin3 = (ImageView)dialog.findViewById(R.id.dialog_pin_lingkar3);
        iv_pin4 = (ImageView)dialog.findViewById(R.id.dialog_pin_lingkar4);
        iv_pin5 = (ImageView)dialog.findViewById(R.id.dialog_pin_lingkar5);
        iv_pin6 = (ImageView)dialog.findViewById(R.id.dialog_pin_lingkar6);
        listLingkar = new ArrayList<>();
        listLingkar.add(iv_pin1);
        listLingkar.add(iv_pin2);
        listLingkar.add(iv_pin3);
        listLingkar.add(iv_pin4);
        listLingkar.add(iv_pin5);
        listLingkar.add(iv_pin6);
    }
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.dialog_pin_button0:
                if (pin.length()<6){
                    pin+="0";
                    refreshLingkar(pin.length());
                    if (state==0){
                        cekPinFull1();
                    }
                    else if(state==1){
                        cekPinFull2();
                    }
                    else if(state==2){
                        cekPinFull3();
                    }
                }
                break;
            case R.id.dialog_pin_button1:
                if (pin.length()<6){
                    pin+="1";
                    refreshLingkar(pin.length());
                    if (state==0){
                        cekPinFull1();
                    }
                    else if(state==1){
                        cekPinFull2();
                    }
                    else if(state==2){
                        cekPinFull3();
                    }
                }
                break;
            case R.id.dialog_pin_button2:
                if (pin.length()<6){
                    pin+="2";
                    refreshLingkar(pin.length());

                    if (state==0){
                        cekPinFull1();
                    }
                    else if(state==1){
                        cekPinFull2();
                    }
                    else if(state==2){
                        cekPinFull3();
                    }
                }
                break;
            case R.id.dialog_pin_button3:
                if (pin.length()<6){
                    pin+="3";
                    refreshLingkar(pin.length());

                    if (state==0){
                        cekPinFull1();
                    }
                    else if(state==1){
                        cekPinFull2();
                    }
                    else if(state==2){
                        cekPinFull3();
                    }
                }
                break;
            case R.id.dialog_pin_button4:
                if (pin.length()<6){
                    pin+="4";
                    refreshLingkar(pin.length());

                    if (state==0){
                        cekPinFull1();
                    }
                    else if(state==1){
                        cekPinFull2();
                    }
                    else if(state==2){
                        cekPinFull3();
                    }
                }
                break;
            case R.id.dialog_pin_button5:
                if (pin.length()<6){
                    pin+="5";
                    refreshLingkar(pin.length());

                    if (state==0){
                        cekPinFull1();
                    }
                    else if(state==1){
                        cekPinFull2();
                    }
                    else if(state==2){
                        cekPinFull3();
                    }
                }
                break;
            case R.id.dialog_pin_button6:
                if (pin.length()<6){
                    pin+="6";
                    refreshLingkar(pin.length());

                    if (state==0){
                        cekPinFull1();
                    }
                    else if(state==1){
                        cekPinFull2();
                    }
                    else if(state==2){
                        cekPinFull3();
                    }
                }
                break;
            case R.id.dialog_pin_button7:
                if (pin.length()<6){
                    pin+="7";
                    refreshLingkar(pin.length());

                    if (state==0){
                        cekPinFull1();
                    }
                    else if(state==1){
                        cekPinFull2();
                    }
                    else if(state==2){
                        cekPinFull3();
                    }
                }
                break;
            case R.id.dialog_pin_button8:
                if (pin.length()<6){
                    pin+="8";
                    refreshLingkar(pin.length());

                    if (state==0){
                        cekPinFull1();
                    }
                    else if(state==1){
                        cekPinFull2();
                    }
                    else if(state==2){
                        cekPinFull3();
                    }
                }
                break;
            case R.id.dialog_pin_button9:
                if (pin.length()<6){
                    pin+="9";
                    refreshLingkar(pin.length());

                    if (state==0){
                        cekPinFull1();
                    }
                    else if(state==1){
                        cekPinFull2();
                    }
                    else if(state==2){
                        cekPinFull3();
                    }
                }
                break;
            case R.id.dialog_pin_ImagebuttonBackSpace:
                if(pin.length()>0)
                {
                    pin = pin.substring(0,pin.length()-1);
                    refreshLingkar(pin.length());
                }
                break;
            case R.id.dialog_pin_buttonCancel:
                dialog.dismiss();
        }
    }
    private void cekPinFull1(){
        if(pin.length()==6){
            if(Objects.equals(pin, User.loggedInUser.getPin())){
                state = 1;
                pin="";
                refreshLingkar(pin.length());
                judulDialog.setText("Masukkan Pin Baru");
            }
            else {
                Animation shake;
                shake = AnimationUtils.loadAnimation(getActivity(),R.anim.shake);
                pinKeyboardLayout.startAnimation(shake);
                pin = "";
                refreshLingkar(pin.length());
            }
        }
    }
    private void cekPinFull2(){
        if(pin.length()==6){
            state = 2;
            pinBaru = pin;
            pin="";
            refreshLingkar(pin.length());
            judulDialog.setText("Masukkan Kembali Pin Baru");
        }
    }
    private void cekPinFull3(){
        if(pin.length()==6){
            if(Objects.equals(pin, pinBaru)){
                User.loggedInUser.setPin(pinBaru);
                SessionManager.with(getActivity()).updateUser(User.loggedInUser);
                dialog.dismiss();
            }
            else {
                Animation shake;
                shake = AnimationUtils.loadAnimation(getActivity(),R.anim.shake);
                pinKeyboardLayout.startAnimation(shake);
                pin = "";
                refreshLingkar(pin.length());
            }
        }
    }
    private void refreshLingkar(int panjangPin){
        for(ImageView imageView : listLingkar ){
            if(listLingkar.indexOf(imageView)<=panjangPin-1){
                imageView.setImageResource(R.drawable.lingkar_pin_full);
            }
            else {
                imageView.setImageResource(R.drawable.lingkar_pin);
            }
        }
    }
}
