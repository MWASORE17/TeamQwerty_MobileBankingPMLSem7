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

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import qwerty.mobilebanking.API.ApiModel;
import qwerty.mobilebanking.API.UtilsApi;
import qwerty.mobilebanking.Adapter.HistoriTransaksiAdapter;
import qwerty.mobilebanking.Model.HistoriTransaksi;
import qwerty.mobilebanking.Model.SessionManager;
import qwerty.mobilebanking.Model.User;
import qwerty.mobilebanking.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class fragment_history extends Fragment {
    private RecyclerView rView;
    private HistoriTransaksiAdapter adapter;
    private ArrayList<HistoriTransaksi> historiTransaksiUserAktif = new ArrayList<>();
    ApiModel mApiService;
    public fragment_history() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View _view = inflater.inflate(R.layout.fragment_history,container,false);
        mApiService = UtilsApi.getAPIService();
        historiTransaksiUserAktif = User.loggedInUser.getListTransaksi();
        adapter = new HistoriTransaksiAdapter();
        rView = (RecyclerView) _view.findViewById(R.id.fragment_transfer_recyclerView_historiTransaksi);
        rView.setHasFixedSize(true);
        rView.setLayoutManager(new LinearLayoutManager(getContext()));
        refreshHistory();
        adapter.sethistoriTransaksi(historiTransaksiUserAktif);
        rView.setAdapter(adapter);
        return _view;
    }
    private void refreshHistory(){
        mApiService.historitransaksi(User.loggedInUser.getNoRek())
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            try{
                                JSONObject result = new JSONObject(response.body().string());
                                if(result.getString("status").equals("true")){
                                    User.loggedInUser = new Gson().fromJson(result.getString("dataRekening"),User.class);
                                    SessionManager.with(getActivity()).updateUser(User.loggedInUser);
                                    historiTransaksiUserAktif = new Gson().fromJson(result.getString("dataHistori"), new TypeToken<List<HistoriTransaksi>>(){}.getType());
                                    User.loggedInUser.setListTransaksi(historiTransaksiUserAktif);
                                    adapter.swap(historiTransaksiUserAktif);
                                    adapter.notifyDataSetChanged();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                    }
                });
    }

}
