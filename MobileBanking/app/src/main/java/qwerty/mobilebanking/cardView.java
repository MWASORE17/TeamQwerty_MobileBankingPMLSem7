package qwerty.mobilebanking;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 10 on 3/26/2017.
 */

public class cardView extends RecyclerView.Adapter<cardView.cardViewHolder> {
    private List<isiCardview> isicardview;
    public cardView(List<isiCardview> isicardview){
        this.isicardview=isicardview;
    }

    public int jumlahMenu(){
        return isicardview.size();
    }
    @Override
    public void onBindViewHolder(cardViewHolder holder, int position) {
        isiCardview i = isicardview.get(position);
        holder.vNamaMenu.setText(i.namaMenu);
        holder.vDeskripsiMenu.setText(i.deskripsiMenu);
    }

    @Override
    public cardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.content_card_view,parent,false);
        return new cardViewHolder(itemView);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class cardViewHolder extends RecyclerView.ViewHolder{
        protected TextView vNamaMenu;
        protected TextView vDeskripsiMenu;
        public cardViewHolder(View itemView) {
            super(itemView);
            vNamaMenu = (TextView)itemView.findViewById(R.id.textMenu);
            vDeskripsiMenu = (TextView)itemView.findViewById(R.id.deskripsiMenu);
        }
    }
}

