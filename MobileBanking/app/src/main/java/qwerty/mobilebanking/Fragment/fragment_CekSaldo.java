package qwerty.mobilebanking.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import qwerty.mobilebanking.Model.User;
import qwerty.mobilebanking.R;


public class fragment_CekSaldo extends Fragment {

    private TextView tv_saldo;

    public fragment_CekSaldo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View _view = inflater.inflate(R.layout.fragment_cek_saldo,container,false);
        // Inflate the layout for this fragment
        tv_saldo = (TextView)_view.findViewById(R.id.fragment_transfer_textView_saldo);
        tv_saldo.setText("Saldo Anda : Rp. " + User.loggedInUser.getSaldo());
        return _view;
    }





}
