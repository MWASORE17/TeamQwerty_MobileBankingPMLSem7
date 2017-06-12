package qwerty.mobilebanking.Model;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by 10 on 5/25/2017.
 */

public class User {

    public User(){ } //Konstruktor Kosong



    private int idUser;
    private String noRek;
    private String kodeAkses;





    private String pin;
    private int saldo;
    private String nama;
    private ArrayList<HistoriTransaksi> listTransaksi = new ArrayList<>();
    public static User loggedInUser;
    public static ArrayList<User> users = new ArrayList<>();

    public User(String noRek, String kodeAkses,int saldo, String pin, String nama){
        setNoRek(noRek);
        setKodeAkses(kodeAkses);
        setSaldo(saldo);
        setNama(nama);
        setPin(pin);
    }
    public User(String noRek, String kodeAkses,int saldo, String pin){
        setNoRek(noRek);
        setKodeAkses(kodeAkses);
        setSaldo(saldo);
        setPin(pin);
    }

    //==================================SET==================================//
    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
    public void setNama(String nama) {
        this.nama = nama;
    }
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
    public void setPin(String pin) {
        this.pin = pin;
    }

    //==================================GET==================================//
    public int getIdUser() {
        return idUser;
    }
    public String getNama() {
        return nama;
    }
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
    public String getPin() {
        return pin;
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
