package qwerty.mobilebanking.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import qwerty.mobilebanking.Model.ItemObjek;
import qwerty.mobilebanking.R;

/**
 * Created by ricoa on 19/05/2017.
 */
//untuk sub menu
public class Fragment_Sub_Menu extends AppCompatActivity{
    private ItemObjek _itemObjek;
    private TextView _title;
    private RelativeLayout _layoutImage;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_sub_menu);

        _itemObjek = (ItemObjek)getIntent().getExtras().get("itemObjek");
        _title = (TextView)findViewById(R.id._title_sub_menu_cardview);
        _layoutImage = (RelativeLayout) findViewById(R.id.image_sub_menu_intent);

        _title.setText(_itemObjek.getName().toString());
        _layoutImage.setBackgroundResource(_itemObjek.getPhoto());

    }
}
