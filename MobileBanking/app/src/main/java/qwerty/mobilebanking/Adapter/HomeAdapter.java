package qwerty.mobilebanking.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import qwerty.mobilebanking.Model.ItemObjek;
import qwerty.mobilebanking.R;

/**
 * Created by ricoa on 12/05/2017.
 */

public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<ItemObjek> _itemObjek;
    public List<ItemObjek> getItemObjek(){ return _itemObjek; }
    public void setItemObjek(){this._itemObjek = _itemObjek;}



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View _view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview_home,parent);


        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final HomeAdapter.itemUserViewHolder _holder =(HomeAdapter.itemUserViewHolder) holder;
        final ItemObjek _itemObject = this._itemObjek.get(position);
        _holder.name.setText(_itemObject.getName());
        _holder.image.setBackgroundResource(_itemObject.getPhoto());
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
