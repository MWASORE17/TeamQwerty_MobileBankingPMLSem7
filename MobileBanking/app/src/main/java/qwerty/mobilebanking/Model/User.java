package qwerty.mobilebanking.Model;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by 10 on 5/25/2017.
 */

public class User {

    public User(){ } //Konstruktor Kosong
    private String noRek;
    private String kodeAkses;
    private int saldo;
    private ArrayList<HistoriTransaksi> listTransaksi = new ArrayList<>();

    public static User loggedInUser;
    public static ArrayList<User> users = new ArrayList<>();
    public User(String noRek, String kodeAkses,int saldo){
        setNoRek(noRek);
        setKodeAkses(kodeAkses);
        setSaldo(saldo);
    }

    //==================================SET==================================//
    public void setSaldo(int saldo){ this.saldo = saldo; }
    public void setNoRek(String noRek){
        this.noRek = noRek;
    }
    public void setKodeAkses(String kodeAkses){
        this.kodeAkses = kodeAkses;
    }
    public void setListTransaksi(ArrayList<HistoriTransaksi> listTransaksi) {
        this.listTransaksi = listTransaksi;
    }

    //==================================GET==================================//
    public int getSaldo(){
        return saldo;
    }
    public String getNoRek(){
        return noRek;
    }
    public String getKodeAkses(){
        return kodeAkses;
    }
    public ArrayList<HistoriTransaksi> getListTransaksi() {
        return listTransaksi;
    }

    //===============================Transaksi===============================//
    public void kurangSaldo(int jumlah){
        this.saldo = this.saldo - jumlah;
    }
    public void tambahSaldo(int jumlah){
        this.saldo = this.saldo + jumlah;
    }
    public void tambahListTransaksi(HistoriTransaksi histori  ){
        this.listTransaksi.add(histori);
    }

    //==============================FindUser=================================//
    public static int findUserIndex(String noRek){
        for(User user : users){
            if(Objects.equals(user.getNoRek(), noRek)){
                return users.indexOf(user);
            }
        }
        return 0;
    }

    public static void updateUser(User user){
        users.set(findUserIndex(user.getNoRek()),user);
    }
}
