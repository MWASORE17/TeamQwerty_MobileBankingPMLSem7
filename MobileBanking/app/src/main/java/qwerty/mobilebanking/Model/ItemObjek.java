package qwerty.mobilebanking.Model;

/**
 * Created by ricoa on 12/05/2017.
 */
//untuk gambar dan text pada halaman Home Setelah login
public class ItemObjek {
    private String name;
    private int photo;

    public ItemObjek (String name, int Photo)
    {
        this.name = name;
        this.photo = photo;
    }

    public String getName()
    {
        return name;
    }
    public int getPhoto()
    {
        return photo;

    }

    public void setName(String name) {
        this.name = name;
    }
    public void setPhoto(int photo) {
        this.photo = photo;
    }

}
