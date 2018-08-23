package com.sau.rentalclothsapp.Owner;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;


import com.sau.rentalclothsapp.R;

public class Setting_Fragment_Owner extends Fragment {

    View view;
    RadioButton bytextmsg, bymail;
    RadioGroup radio_grp;
    Button save;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        view = inflater.inflate(R.layout.setting_fragment_owner, container, false);
        getActivity().setTitle(" Setting");


        radio_grp = view.findViewById(R.id.radio_grp1);
        bytextmsg = radio_grp.findViewById(R.id.bytextmsg1);
        bymail = radio_grp.findViewById(R.id.bymail1);
        save = view.findViewById(R.id.btnsubmit1);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        Notificationsettings();
        return view;

    }

    private void Notificationsettings() {
        bytextmsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Toast.makeText(getActivity(), "bytextmsg", Toast.LENGTH_SHORT).show();
            }
        });
        bymail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  Toast.makeText(getActivity(), "bymail", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.setting_fragment_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

}
