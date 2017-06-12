package qwerty.mobilebanking.Adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.icu.text.DecimalFormat;
import android.icu.text.DisplayContext;
import android.icu.text.NumberFormat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import qwerty.mobilebanking.Model.HistoriTransaksi;
import qwerty.mobilebanking.R;

/**
 * Created by 10 on 5/28/2017.
 */

public class HistoriTransaksiAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private ArrayList<HistoriTransaksi> historiTransaksi;

    public ArrayList<HistoriTransaksi> gethistoriTransaksi() {
        return historiTransaksi;
    }
    public void sethistoriTransaksi(ArrayList<HistoriTransaksi> historiTransaksi) {
        this.historiTransaksi = historiTransaksi;
    }
    public HistoriTransaksiAdapter(){
        this.historiTransaksi = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview_historitransaksi,parent,false);
        return new itemHistoriTransaksiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final itemHistoriTransaksiViewHolder _holder = (itemHistoriTransaksiViewHolder) holder;
        final HistoriTransaksi _historiTransaksi = this.historiTransaksi.get(position);
        _holder.tanggal.setText(_historiTransaksi.getTanggalTransaksi());
        NumberFormat nf= NumberFormat.getInstance();
        DecimalFormat df=(DecimalFormat)nf;
        df.applyPattern("#,###.00");
        _holder.deskripsi.setText(_historiTransaksi.getJenisTransaksi());
        _holder.nominal.setText("IDR. "+ df.format(_historiTransaksi.getJumlahTransaksi()));
    }

    @Override
    public int getItemCount() {
        return historiTransaksi.size();
    }

    private class itemHistoriTransaksiViewHolder extends RecyclerView.ViewHolder {
        private TextView tanggal,deskripsi,nominal;
        public itemHistoriTransaksiViewHolder(View itemView) {
            super(itemView);
            tanggal = (TextView)itemView.findViewById(R.id.item_historitransaksi_textView_tanggal);
            deskripsi = (TextView)itemView.findViewById(R.id.item_historitransaksi_textView_deskripsi);
            nominal = (TextView)itemView.findViewById(R.id.item_historitransaksi_textView_nominal);
        }
    }
    public void swap(ArrayList list){
        historiTransaksi = list;
    }
}
