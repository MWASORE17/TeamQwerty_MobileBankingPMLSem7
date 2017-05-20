package qwerty.mobilebanking.Adapter;

import android.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import qwerty.mobilebanking.Model.ItemObjek;
import qwerty.mobilebanking.Model.itemObjekGrid;
import qwerty.mobilebanking.R;

/**
 * Created by ricoa on 19/05/2017.
 */

public class SubMenuAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private List<itemObjekGrid> IOGrid;
    public List<itemObjekGrid> getIOGrid(){return IOGrid;}
    public void setIOGrid(List<itemObjekGrid> IOGrid){this.IOGrid = IOGrid;}
    public SubMenuAdapter(){this.IOGrid = new ArrayList<>();}

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View _view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sub_menu_grid,parent,false);
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //final MenuAwalAdapter.ItemMenuViewHolder _holder = (MenuAwalAdapter.ItemMenuViewHolder) holder;
        //final ItemObjek _itemMenu = this.itemMenu.get(position);
        //_holder.namaMenu.setText(_itemMenu.getName());
        //_holder.layoutMenu.setBackgroundResource(_itemMenu.getPhoto());


    }

    @Override
    public int getItemCount() {
        return 0;
    }
    private class IOGridViewHolder extends RecyclerView.ViewHolder{
        private TextView namaSubMenu;
        private RelativeLayout layoutSubMenu;
        public IOGridViewHolder(View itemView) {
            super(itemView);
            namaSubMenu = (TextView)itemView.findViewById(R.id.item_title_grid);
            layoutSubMenu = (RelativeLayout)itemView.findViewById(R.id.);
        }
    }
}
