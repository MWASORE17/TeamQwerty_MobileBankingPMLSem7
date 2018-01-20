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

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import okhttp3.ResponseBody;
import qwerty.mobilebanking.API.ApiModel;
import qwerty.mobilebanking.Model.HistoriTransaksi;
import qwerty.mobilebanking.Model.User;
import qwerty.mobilebanking.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by 10 on 5/28/2017.
 */

public class HistoriTransaksiAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private ArrayList<HistoriTransaksi> historiTransaksi;
    private ApiModel mApiService;
    private ArrayList<HistoriTransaksi> histori;
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
        _holder.deskripsi.setText(deskripsi(_historiTransaksi));
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
    public String deskripsi(HistoriTransaksi hTransaksi){
        if(Objects.equals(hTransaksi.getPengirim(), User.loggedInUser.getNoRek())){
            return "Transfer ke - "+hTransaksi.getPenerima();
        }
        else {
            return "Terima dari - "+hTransaksi.getPengirim();
        }
    }
}
