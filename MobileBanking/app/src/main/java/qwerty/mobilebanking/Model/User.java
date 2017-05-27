package qwerty.mobilebanking.Model;

import java.util.ArrayList;

/**
 * Created by 10 on 5/25/2017.
 */

public class User {
    private String noRek,kodeAkses;
    public static ArrayList<User> users = new ArrayList<>();
    public User(){ }
    public User(String noRek, String kodeAkses){
        setNoRek(noRek);
        setKodeAkses(kodeAkses);
    }
    //==================================SET==================================//
    public void setNoRek(String noRek){
        this.noRek = noRek;
    }
    public void setKodeAkses(String kodeAkses){
        this.kodeAkses = kodeAkses;
    }
    //==================================GET==================================//
    public String getNoRek(){
        return noRek;
    }
    public String getKodeAkses(){
        return kodeAkses;
    }
}
