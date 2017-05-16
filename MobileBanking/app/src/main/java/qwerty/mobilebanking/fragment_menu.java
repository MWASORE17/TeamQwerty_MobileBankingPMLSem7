package qwerty.mobilebanking;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.view.menu.MenuAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;

import qwerty.mobilebanking.Model.ItemObjek;
import qwerty.mobilebanking.adapter.MenuAwalAdapter;

/**
 * Created by 10 on 5/16/2017.
 */

public class fragment_menu extends Fragment {
    private RecyclerView rView;
    private MenuAwalAdapter adapter;
    public fragment_menu(){}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cardview_home,container,false);
        adapter = new MenuAwalAdapter();
        rView = (RecyclerView)view.findViewById(R.id.recycler_menu);
        rView.setHasFixedSize(true);
        rView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter.setItemMenu(ItemObjek.itemMenu);
        rView.setAdapter(adapter);
        return view;
    }
}
