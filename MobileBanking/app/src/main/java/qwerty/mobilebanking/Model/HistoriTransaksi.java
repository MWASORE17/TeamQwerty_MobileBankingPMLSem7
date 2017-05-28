package qwerty.mobilebanking.Model;

import java.util.ArrayList;

/**
 * Created by 10 on 5/28/2017.
 */

public class HistoriTransaksi {

    public HistoriTransaksi() {

    } //Konstruktor Kosong

    private String pengirim;
    private String penerima;
    private String jenisTransaksi;
    private int jumlahTransaksi;
    private String tanggalTransaksi;

    public HistoriTransaksi(String pengirim, String penerima, String jenisTransaksi, int jumlahTransaksi, String tanggalTransaksi  ){
        setPengirim(pengirim);
        setPenerima(penerima);
        setJenisTransaksi(jenisTransaksi);
        setJumlahTransaksi(jumlahTransaksi);
        setTanggalTransaksi(tanggalTransaksi);
    }

    //==================================SET==================================//
    public void setPengirim(String pengirim) {
        this.pengirim = pengirim;
    }
    public void setPenerima(String penerima) {
        this.penerima = penerima;
    }
    public void setJenisTransaksi(String jenisTransaksi) {
        this.jenisTransaksi = jenisTransaksi;
    }
    public void setJumlahTransaksi(int jumlahTransaksi) {
        this.jumlahTransaksi = jumlahTransaksi;
    }
    public void setTanggalTransaksi(String tanggalTransaksi) {
        this.tanggalTransaksi = tanggalTransaksi;
    }
    //==================================GET==================================//
    public String getPengirim() {
        return pengirim;
    }
    public String getPenerima() {
        return penerima;
    }
    public String getJenisTransaksi() {
        return jenisTransaksi;
    }
    public int getJumlahTransaksi() {
        return jumlahTransaksi;
    }
    public String getTanggalTransaksi() {
        return tanggalTransaksi;
    }
}