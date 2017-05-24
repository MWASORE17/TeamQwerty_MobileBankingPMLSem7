package qwerty.mobilebanking.Model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by ricoa on 12/05/2017.
 */
//untuk gambar dan text pada halaman Home Setelah login
public class ItemObjek implements Serializable {

    public ItemObjek(){}

    private String name;
    private int photo;

    public ItemObjek (String name, int foto)
    {
        this.name = name;
        this.photo = foto;
    }

    public String getName() {return name;}
    public int getPhoto() {return photo;}

    public void setName(String name) {
        this.name = name;
    }
    public void setPhoto(int photo) {
        this.photo = photo;
    }
    public static ArrayList<ItemObjek> itemMenu = new ArrayList<>();
}
