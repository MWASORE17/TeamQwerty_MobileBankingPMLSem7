package qwerty.mobilebanking;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Rico Wu on 19/03/2017.
 */

public class Tab2__SignUp  extends Fragment {
    private Button _button;
    private Typeface _typeFaceRL;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab2_signup, container, false);
        _button = (Button) rootView.findViewById(R.id.button);
        _typeFaceRL = Typeface.createFromAsset(getActivity().getAssets(),"fonts/robotolight.ttf");
        _button.setTypeface(_typeFaceRL);
        _button.setText("REGISTER");


        return rootView;

    }
}
