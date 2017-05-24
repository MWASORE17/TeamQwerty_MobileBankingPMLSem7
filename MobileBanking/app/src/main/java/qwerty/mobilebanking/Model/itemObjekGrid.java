package qwerty.mobilebanking.Model;

import java.util.ArrayList;

/**
 * Created by ricoa on 19/05/2017.
 */

public class itemObjekGrid
{
    public itemObjekGrid(){}

    private String _titleGrid;
    private int _photoGrid;

    public itemObjekGrid(String _titleGrid, int _photoGrid)
    {
        this._photoGrid = _photoGrid;
        this._titleGrid = _titleGrid;

    }
    public int getPhotoGrid()
    {
        return _photoGrid;
    }
    public String getTitleGrid()
    {
        return _titleGrid;
    }
    public void setTitleGrid(String _titleGrid){
        this._titleGrid=_titleGrid;
    }
    public void setPhotoGrid(int _photoGrid){
        this._photoGrid =_photoGrid;
    }
    public static ArrayList<itemObjekGrid> IOGrid = new ArrayList<>();
}
