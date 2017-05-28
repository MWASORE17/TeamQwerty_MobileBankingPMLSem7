package qwerty.mobilebanking.Adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import qwerty.mobilebanking.Fragment.Fragment_Sub_Menu;
import qwerty.mobilebanking.Model.ItemObjek;
import qwerty.mobilebanking.R;

/**
 * Created by ricoa on 12/05/2017.
 */

public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<ItemObjek> _itemObjek;
    public List<ItemObjek> getItemObjek(){ return _itemObjek; }
    public void setItemObjek(List<ItemObjek> _itemObjek){this._itemObjek = _itemObjek;}
    public HomeAdapter() {this._itemObjek = new ArrayList<>();}

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View _view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview_home,parent, false);
        return new HomeAdapter.itemUserViewHolder(_view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final HomeAdapter.itemUserViewHolder _holder =(HomeAdapter.itemUserViewHolder) holder;
        final ItemObjek itemObject = this._itemObjek.get(position);
        _holder.name.setText(itemObject.getName());
        _holder.image.setBackgroundResource(itemObject.getPhoto());

        _holder.itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v){
                Intent _intent = new Intent(v.getContext(), Fragment_Sub_Menu.class);
                _intent.putExtra("itemObjek", (Serializable) itemObject);
                v.getContext().startActivity(_intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return _itemObjek.size();
    }

    private class itemUserViewHolder extends RecyclerView.ViewHolder{
        private RelativeLayout image;
        private TextView name;
        public itemUserViewHolder(View itemView) {
            super(itemView);
            image = (RelativeLayout) itemView.findViewById(R.id.layout_home_cardview);
            name = (TextView) itemView.findViewById(R.id.name_home_cardview);
        }
    }
}
