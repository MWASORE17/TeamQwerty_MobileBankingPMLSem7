package qwerty.mobilebanking.API;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by 10 on 1/9/2018.
 */

public interface ApiModel {
    @FormUrlEncoded
    @POST("login")
    Call<ResponseBody>loginRequest(@Field("no_rekening")String noRekening,
                                   @Field("kode_akses")String kodeAkses);

    @FormUrlEncoded
    @POST("getkodeakses")
    Call<ResponseBody>getKodeAkses(@Field("no_rekening")String noRekening);

    @FormUrlEncoded
    @POST("transfer")
    Call<ResponseBody>transfer(@Field("no_rekening")String noRekening,
                               @Field("nominal")int nominal,
                               @Field("rek_tujuan")String rek_tujuan,
                               @Field("catatan")String catatan);

    @FormUrlEncoded
    @POST("historitransaksi")
    Call<ResponseBody>historitransaksi(@Field("no_rekening")String noRekening);

    @FormUrlEncoded
    @POST("updatekodeakses")
    Call<ResponseBody>updateKodeAkses(@Field("no_rekening")String noRekening,
                               @Field("kode_akses_lama")String kodeAksesLama,
                               @Field("kode_akses_baru")String kodeAksesBaru);

    @FormUrlEncoded
    @POST("getmpin")
    Call<ResponseBody>getMPin(@Field("no_rekening")String noRekening);

    @FormUrlEncoded
    @POST("updatempin")
    Call<ResponseBody>updateMPin(@Field("no_rekening")String noRekening,
                                      @Field("mPin_lama")String mPinLama,
                                      @Field("mPin_baru")String mPinBaru);
}
