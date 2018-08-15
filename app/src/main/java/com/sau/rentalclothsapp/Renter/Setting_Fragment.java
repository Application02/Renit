package com.sau.rentalclothsapp.Renter;



import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.sau.rentalclothsapp.R;

public class Setting_Fragment extends Fragment {

    View view;
    RadioButton bytextmsg,bymail;
    RadioGroup radio_grp;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        view = inflater.inflate(R.layout.setting_fragment, container, false);

        radio_grp= view.findViewById(R.id.radio_grp);
        bytextmsg =  radio_grp.findViewById(R.id.bytextmsg);
        bymail =  radio_grp.findViewById(R.id.bymail);

        getActivity().setTitle("Setting");

        Notificationsettings();
        return view;

    }

    private void Notificationsettings() {
        bytextmsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getActivity(), "bytextmsg", Toast.LENGTH_SHORT).show();
            }
        });
        bymail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getActivity(), "bymail", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.setting_fragment_menu, menu);
        super.onCreateOptionsMenu(menu,inflater);
    }

}
