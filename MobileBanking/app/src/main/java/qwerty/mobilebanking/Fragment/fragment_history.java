package qwerty.mobilebanking.Fragment;

import android.graphics.Typeface;
import android.icu.text.DecimalFormat;
import android.icu.text.NumberFormat;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.app.Fragment;
import android.widget.TextView;

import java.util.ArrayList;

import qwerty.mobilebanking.Adapter.HistoriTransaksiAdapter;
import qwerty.mobilebanking.Model.HistoriTransaksi;
import qwerty.mobilebanking.Model.User;
import qwerty.mobilebanking.R;


public class fragment_history extends Fragment {
    private RecyclerView rView;
    private HistoriTransaksiAdapter adapter;
    private ArrayList<HistoriTransaksi> historiTransaksiUserAktif = new ArrayList<>();

    public fragment_history() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View _view = inflater.inflate(R.layout.fragment_history,container,false);

        historiTransaksiUserAktif = User.loggedInUser.getListTransaksi();
        adapter = new HistoriTransaksiAdapter();
        rView = (RecyclerView) _view.findViewById(R.id.fragment_transfer_recyclerView_historiTransaksi);
        rView.setHasFixedSize(true);
        rView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter.sethistoriTransaksi(historiTransaksiUserAktif);
        rView.setAdapter(adapter);
        return _view;
    }


}
