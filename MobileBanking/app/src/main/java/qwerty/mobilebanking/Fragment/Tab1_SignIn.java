package qwerty.mobilebanking.Fragment;

/**
 * Created by Rico Wu on 19/03/2017.
 */
import android.app.ProgressDialog;
import android.graphics.Typeface;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import qwerty.mobilebanking.API.ApiModel;
import qwerty.mobilebanking.Model.User;
import qwerty.mobilebanking.R;
import qwerty.mobilebanking.Model.SessionManager;
import qwerty.mobilebanking.API.UtilsApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Tab1_SignIn  extends Fragment{
    private Button loginButton;
    private TextInputLayout til_noRek, til_kodeAkses;
    private EditText etNoRekening;
    private EditText etKodeAkses;
    private SessionManager session;
    private Typeface _typeFaceRL;
    private ArrayList<User> listUser;
    private User userLogin;
    ProgressDialog loading;
    ApiModel mApiService;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_home_tab1_signin, container, false);
        session = new SessionManager(getActivity());
        mApiService = UtilsApi.getAPIService();

        loginButton = (Button)rootView.findViewById(R.id.buttonLogin);
        _typeFaceRL = Typeface.createFromAsset(getActivity().getAssets(), "fonts/robotolight.ttf");

        loginButton.setTypeface(_typeFaceRL);
        loginButton.setText("AUTHENTICATE");

        etNoRekening = (EditText)rootView.findViewById(R.id.editText);
        etKodeAkses = (EditText)rootView.findViewById(R.id.editText2);
        til_noRek = (TextInputLayout)rootView.findViewById(R.id.fragment_signin_textInputLayout_noRek);
        til_kodeAkses = (TextInputLayout)rootView.findViewById(R.id.fragment_signin_textInputLayout_kodeAkses);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*if(Objects.equals(etNoRekening.getText().toString(), "123456789")&& Objects.equals(etKodeAkses.getText().toString(), "admin")){
                    session.loginUser(etNoRekening.getText().toString(),etKodeAkses.getText().toString());
                }*/
                boolean _isvalid = true;
                til_noRek.setErrorEnabled(false);
                til_kodeAkses.setErrorEnabled(false);
                if(TextUtils.isEmpty(etNoRekening.getText().toString())){
                    _isvalid=false;
                    til_noRek.setErrorEnabled(true);
                    til_noRek.setError("Nomor Rekening Tidak Boleh Kosong");
                }
                else if(etNoRekening.getText().toString().length()!=11){
                    _isvalid=false;
                    til_noRek.setErrorEnabled(true);
                    til_noRek.setError("Nomor Rekening Terdiri Dari 11 Digit Angka");
                }
                else if(TextUtils.isEmpty(etKodeAkses.getText().toString())){
                    _isvalid=false;
                    til_kodeAkses.setErrorEnabled(true);
                    til_kodeAkses.setError("Kode Akses Tidak Boleh Kosong");
                }
                else if (etKodeAkses.getText().toString().length()<8){
                    _isvalid=false;
                    til_kodeAkses.setErrorEnabled(true);
                    til_kodeAkses.setError("Kode Akses Terlalu Pendek");
                }
                if(_isvalid){
                    loading = ProgressDialog.show(getActivity(), null, "Harap Tunggu...", true, false);
                    mApiService.loginRequest(etNoRekening.getText().toString(),etKodeAkses.getText().toString())
                            .enqueue(new Callback<ResponseBody>() {
                                @Override
                                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                    if (response.isSuccessful()){
                                        loading.dismiss();
                                        try{
                                            JSONObject result = new JSONObject(response.body().string());
                                            if(result.getString("status").equals("true")){
                                                userLogin = new Gson().fromJson(result.getString("data"),User.class);
                                                session.loginUser(userLogin);
                                                loading.dismiss();
                                            }
                                            else{
                                                loading.dismiss();
                                                Toast.makeText(getActivity(),result.getString("message"),Toast.LENGTH_LONG).show();
                                            }
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    else{
                                        loading.dismiss();
                                    }
                                }
                                @Override
                                public void onFailure(Call<ResponseBody> call, Throwable t) {
                                    loading.dismiss();
                                }
                            });
                    /*boolean _isregistered = false,_ismatch = false;
                    for(User user : User.users){
                        if(Objects.equals(user.getNoRek(), etNoRekening.getText().toString())){
                            if(Objects.equals(user.getKodeAkses(), etKodeAkses.getText().toString())){
                                _ismatch=true;
                                userLogin = user;
                            }
                            _isregistered = true;
                            break;
                        }
                    }
                    if(!_isregistered){
                        til_noRek.setErrorEnabled(true);
                        til_noRek.setError("Nomor Rekening Belum Terdaftar");
                    }
                    else if(!_ismatch){
                        til_kodeAkses.setErrorEnabled(true);
                        til_kodeAkses.setError("Kode Akses Salah");
                    }
                    if(_isregistered && _ismatch){
                        session.loginUser(userLogin);
                    }*/
                }
            }
        });

        return rootView;
    }

}

