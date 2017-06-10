package qwerty.mobilebanking.Fragment;

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import qwerty.mobilebanking.Adapter.HistoriTransaksiAdapter;
import qwerty.mobilebanking.Model.HistoriTransaksi;
import qwerty.mobilebanking.Model.User;
import qwerty.mobilebanking.R;

/**
 * Created by 10 on 5/28/2017.
 */

public class Fragment_Transfer extends Fragment {
    public Fragment_Transfer() {

    }//Konstruktor Kosong

    private TextView tv_saldo;
    private TextInputLayout til_noRekTujuan, til_nominalTransfer;
    private EditText et_noRekTujuan,et_nominalTransfer;
    private Button btn_transfer;
    private RecyclerView rView;
    private HistoriTransaksiAdapter adapter;
    private ArrayList<HistoriTransaksi> historiTransaksiUserAktif = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View _view = inflater.inflate(R.layout.fragment_transfer,container,false);
        init(_view);
        event();
        return _view;
    }


    private void init(View view){
       /* tv_saldo = (TextView)view.findViewById(R.id.fragment_transfer_textView_saldo);
        tv_saldo.setText("Saldo Anda : Rp. " + User.loggedInUser.getSaldo());*/
        til_noRekTujuan = (TextInputLayout)view.findViewById(R.id.fragment_transfer_textInputLayout_noRekTjuan);
        til_nominalTransfer = (TextInputLayout)view.findViewById(R.id.fragment_transfer_textInputLayout_nominalTransfer);
        et_noRekTujuan = (EditText)view.findViewById(R.id.fragment_transfer_editText_noRekTujuan);
        et_nominalTransfer = (EditText)view.findViewById(R.id.fragment_transfer_editText_nominalTransfer);
        btn_transfer = (Button)view.findViewById(R.id.fragment_transfer_button_transfer);

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
                if(et_noRekTujuan.getText().length()!=16){
                    _isvalid=false;
                    til_noRekTujuan.setErrorEnabled(true);
                    til_noRekTujuan.setError("Nomor Rekening harus 16 digit");
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
                    transfer(User.loggedInUser,Integer.parseInt(et_nominalTransfer.getText().toString()));User.updateUser(User.loggedInUser);
                    User.loggedInUser.tambahListTransaksi(new HistoriTransaksi(User.loggedInUser.getNoRek(),et_noRekTujuan.getText().toString(),"Transfer ke "+et_noRekTujuan.getText(),Integer.parseInt(et_nominalTransfer.getText().toString()),new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date())));
                    adapter.swap(User.loggedInUser.getListTransaksi());
                    adapter.notifyDataSetChanged();
                    //tv_saldo.setText("Saldo Anda : Rp. "+User.loggedInUser.getSaldo());
                }
            }
        });
    }


    private void transfer(User pengirim,int nominal){
        pengirim.kurangSaldo(nominal);
    }
}
