package qwerty.mobilebanking.Adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import qwerty.mobilebanking.Activity.SubMenu;
import qwerty.mobilebanking.Model.ItemObjek;
import qwerty.mobilebanking.R;

/**
 * Created by 10 on 5/14/2017.
 */

public class MenuAwalAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ItemObjek> itemMenu;
    public List<ItemObjek>getitem(){return itemMenu;}
    public void setItemMenu(List<ItemObjek> itemMenu){this.itemMenu = itemMenu;}
    public MenuAwalAdapter(){this.itemMenu = new ArrayList<>();}

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View _view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview_home,parent,false);
        return new ItemMenuViewHolder(_view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final int posisi = position;
        final ItemMenuViewHolder _holder = (ItemMenuViewHolder) holder;
        final ItemObjek _itemMenu = this.itemMenu.get(position);
        _holder.namaMenu.setText(_itemMenu.getName());
        _holder.layoutMenu.setBackgroundResource(_itemMenu.getPhoto());
        _holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SubMenu.class);
                intent.putExtra("posisi",posisi);
                v.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return itemMenu.size();
    }
    private class ItemMenuViewHolder extends RecyclerView.ViewHolder{
        private TextView namaMenu;
        private RelativeLayout layoutMenu;
        public ItemMenuViewHolder(View itemView) {
            super(itemView);
            namaMenu = (TextView)itemView.findViewById(R.id.name_home_cardview);
            layoutMenu = (RelativeLayout)itemView.findViewById(R.id.layout_home_cardview);
        }
    }
}
