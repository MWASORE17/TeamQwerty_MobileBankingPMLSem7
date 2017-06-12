package qwerty.mobilebanking.Fragment;

import android.app.Dialog;
import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

import qwerty.mobilebanking.Adapter.HistoriTransaksiAdapter;
import qwerty.mobilebanking.Model.HistoriTransaksi;
import qwerty.mobilebanking.Model.SessionManager;
import qwerty.mobilebanking.Model.User;
import qwerty.mobilebanking.R;

/**
 * Created by 10 on 5/28/2017.
 */

public class Fragment_Transfer extends Fragment implements View.OnClickListener {
    public Fragment_Transfer() {

    }//Konstruktor Kosong

    private TextView tv_saldo;
    private TextInputLayout til_noRekTujuan, til_nominalTransfer;
    private EditText et_noRekTujuan,et_nominalTransfer;
    private Button btn_transfer;
    private RecyclerView rView;
    private HistoriTransaksiAdapter adapter;
    private ArrayList<HistoriTransaksi> historiTransaksiUserAktif = new ArrayList<>();
    private Button bt_pin0,bt_pin1,bt_pin2,bt_pin3,bt_pin4,bt_pin5,bt_pin6,bt_pin7,bt_pin8,bt_pin9,bt_pinCancel;
    private ImageView iv_pin1,iv_pin2,iv_pin3,iv_pin4,iv_pin5,iv_pin6;
    private ImageButton bt_pinBackSpace;
    private LinearLayout pinKeyboardLayout;
    private String pin;
    private ArrayList<ImageView> listLingkar;
    private Dialog dialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View _view = inflater.inflate(R.layout.fragment_transfer,container,false);
        init(_view);
        event();
        return _view;
    }


    private void init(View view){
        til_noRekTujuan = (TextInputLayout)view.findViewById(R.id.fragment_transfer_textInputLayout_noRekTjuan);
        til_nominalTransfer = (TextInputLayout)view.findViewById(R.id.fragment_transfer_textInputLayout_nominalTransfer);
        et_noRekTujuan = (EditText)view.findViewById(R.id.fragment_transfer_editText_noRekTujuan);
        et_nominalTransfer = (EditText)view.findViewById(R.id.fragment_transfer_editText_nominalTransfer);
        btn_transfer = (Button)view.findViewById(R.id.fragment_transfer_button_transfer);
        historiTransaksiUserAktif = User.loggedInUser.getListTransaksi();

       adapter = new HistoriTransaksiAdapter();
        rView = (RecyclerView)view.findViewById(R.id.fragment_transfer_recyclerView_historiTransaksi);
        rView.setHasFixedSize(true);
        rView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter.sethistoriTransaksi(historiTransaksiUserAktif);
        rView.setAdapter(adapter);
    }


    private void event(){
        btn_transfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean _isvalid = true;
                til_noRekTujuan.setErrorEnabled(false);
                til_nominalTransfer.setErrorEnabled(false);
                if(et_noRekTujuan.getText().length()!=11){
                    _isvalid=false;
                    til_noRekTujuan.setErrorEnabled(true);
                    til_noRekTujuan.setError("Nomor Rekening harus 11 digit");
                }
                else if(Integer.parseInt(et_nominalTransfer.getText().toString())<10000){
                    _isvalid=false;
                    til_nominalTransfer.setErrorEnabled(true);
                    til_nominalTransfer.setError("Nominal Transfer Minimal Rp. 10000");
                }
                else if(Integer.parseInt(et_nominalTransfer.getText().toString())>User.loggedInUser.getSaldo()){
                    _isvalid=false;
                    til_nominalTransfer.setErrorEnabled(true);
                    til_nominalTransfer.setError("Saldo Anda Tidak Mencukupi");
                }
                if(_isvalid){
                    dialogPin();
                    /*transfer(User.loggedInUser,Integer.parseInt(et_nominalTransfer.getText().toString()));
                    User.updateUser(User.loggedInUser);
                    User.loggedInUser.tambahListTransaksi(new HistoriTransaksi(User.loggedInUser.getNoRek(),et_noRekTujuan.getText().toString(),"Transfer ke "+et_noRekTujuan.getText(),Integer.parseInt(et_nominalTransfer.getText().toString()),new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date())));
                    adapter.swap(User.loggedInUser.getListTransaksi());
                    adapter.notifyDataSetChanged();*/
                }
            }
        });
    }
    private void dialogPin(){
        pin = "";
        dialog = new Dialog(getActivity(),android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        dialog.setContentView(R.layout.fragment_pin_view);
        dialog.show();
        pinKeyboardLayout = (LinearLayout)dialog.findViewById(R.id.dialog_pin_layoutKeyboard);
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

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.dialog_pin_button0:
                if (pin.length()<6){
                    pin+="0";
                    refreshLingkar(pin.length());
                    cekPinFull();
                }
                break;
            case R.id.dialog_pin_button1:
                if (pin.length()<6){
                    pin+="1";
                    refreshLingkar(pin.length());
                    cekPinFull();
                }
                break;
            case R.id.dialog_pin_button2:
                if (pin.length()<6){
                    pin+="2";
                    refreshLingkar(pin.length());
                    cekPinFull();
                }
                break;
            case R.id.dialog_pin_button3:
                if (pin.length()<6){
                    pin+="3";
                    refreshLingkar(pin.length());
                    cekPinFull();
                }
                break;
            case R.id.dialog_pin_button4:
                if (pin.length()<6){
                    pin+="4";
                    refreshLingkar(pin.length());
                    cekPinFull();
                }
                break;
            case R.id.dialog_pin_button5:
                if (pin.length()<6){
                    pin+="5";
                    refreshLingkar(pin.length());
                    cekPinFull();
                }
                break;
            case R.id.dialog_pin_button6:
                if (pin.length()<6){
                    pin+="6";
                    refreshLingkar(pin.length());
                    cekPinFull();
                }
                break;
            case R.id.dialog_pin_button7:
                if (pin.length()<6){
                    pin+="7";
                    refreshLingkar(pin.length());
                    cekPinFull();
                }
                break;
            case R.id.dialog_pin_button8:
                if (pin.length()<6){
                    pin+="8";
                    refreshLingkar(pin.length());
                    cekPinFull();
                }
                break;
            case R.id.dialog_pin_button9:
                if (pin.length()<6){
                    pin+="9";
                    refreshLingkar(pin.length());
                    cekPinFull();
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
    private void cekPinFull(){
        if(pin.length()==6){
            if(Objects.equals(pin, User.loggedInUser.getPin())){
                transfer(User.loggedInUser,Integer.parseInt(et_nominalTransfer.getText().toString()));
                User.loggedInUser.tambahListTransaksi(new HistoriTransaksi(User.loggedInUser.getNoRek(),et_noRekTujuan.getText().toString(),"Transfer ke "+et_noRekTujuan.getText(),Integer.parseInt(et_nominalTransfer.getText().toString()),new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date())));
                User.updateUser(User.loggedInUser);
                SessionManager.with(getActivity()).updateUser(User.loggedInUser);
                adapter.swap(User.loggedInUser.getListTransaksi());
                adapter.notifyDataSetChanged();
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
    private void transfer(User pengirim,int nominal){
        pengirim.kurangSaldo(nominal);
    }
}
