package qwerty.mobilebanking.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import qwerty.mobilebanking.Model.ItemObjek;
import qwerty.mobilebanking.R;

/**
 * Created by 10 on 5/14/2017.
 */

public class MenuAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private class ItemMenuViewHolder extends RecyclerView.ViewHolder{
        private TextView namaMenu;
        private RelativeLayout layoutMenu;
        public ItemMenuViewHolder(View itemView) {
            super(itemView);
            namaMenu = (TextView)itemView.findViewById(R.id.name_home_cardview);
            layoutMenu = (RelativeLayout) itemView.findViewById(R.id.layout_home_cardview);
        }
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View _view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview_home,parent,false);
        return new ItemMenuViewHolder(_view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final ItemMenuViewHolder _holder = (ItemMenuViewHolder) holder;
//<-------------------------------------------------------------------------------------------------------------------->

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
