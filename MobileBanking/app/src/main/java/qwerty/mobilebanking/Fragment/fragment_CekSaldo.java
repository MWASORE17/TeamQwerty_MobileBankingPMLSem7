package qwerty.mobilebanking.Fragment;

import android.content.Context;
import android.graphics.Typeface;
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
    private Typeface _typeFace;


    public fragment_CekSaldo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View _view = inflater.inflate(R.layout.fragment_cek_saldo,container,false);


        _typeFace = Typeface.createFromAsset(getActivity().getAssets(), "fonts/robotolight.ttf");
        // Inflate the layout for this fragment
        tv_saldo = (TextView)_view.findViewById(R.id.fragment_transfer_textView_saldo);
        tv_saldo.setText("Rp. " + User.loggedInUser.getSaldo());
        tv_saldo.setTypeface(_typeFace);
        return _view;
    }





}
