package qwerty.mobilebanking.Fragment;

import android.content.Context;
import android.graphics.Typeface;
import android.icu.text.DecimalFormat;
import android.icu.text.NumberFormat;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import qwerty.mobilebanking.Model.User;
import qwerty.mobilebanking.R;


public class fragment_CekSaldo extends Fragment {

    private TextView tv_saldo;
    private TextView tv_name;
    private TextView tv_norek;
    private Typeface _typeFace;



    public fragment_CekSaldo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View _view = inflater.inflate(R.layout.fragment_cek_saldo,container,false);
        tv_saldo = (TextView)_view.findViewById(R.id.fragment_transfer_textView_saldo);
        tv_name = (TextView)_view.findViewById(R.id.cek_saldo_nama);
        tv_norek = (TextView)_view.findViewById(R.id.cek_saldo_norek);

        //set font custom untuk saldo
        _typeFace = Typeface.createFromAsset(getActivity().getAssets(), "fonts/card_font.ttf");
        tv_saldo.setTypeface(_typeFace);
        tv_norek.setTypeface(_typeFace);

        //merah karena minimal API kami 21 sedangkan yang dibutuhkanitu 24
        //untuk formating
        NumberFormat nf= NumberFormat.getInstance();
        DecimalFormat df=(DecimalFormat)nf;
        df.applyPattern("#,###.00");

        tv_name.setText(User.loggedInUser.getNama());
        tv_norek.setText(User.loggedInUser.getNoRek());
        tv_saldo.setText("IDR  " + df.format(User.loggedInUser.getSaldo()) );
        return _view;
    }





}
