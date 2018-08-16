package com.sau.rentalclothsapp.Owner;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.sau.rentalclothsapp.Owner.adepter.ImageAdapterMyRenit;
import com.sau.rentalclothsapp.R;

public class MyRenit_Fragment_Owner extends Fragment {

    View view;
    GridView gridView;
    RadioButton shortterm, longterm,busy,available;
    RadioGroup availability,status;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        view = inflater.inflate(R.layout.myrenit_fragment_owner, container, false);
        getActivity().setTitle("MyRenit");

        gridView = view.findViewById(R.id.gridView);
        availability = view.findViewById(R.id.availability);
        shortterm = availability.findViewById(R.id.shortterm);
        longterm = availability.findViewById(R.id.longterm);

        status = view.findViewById(R.id.status);
        busy = status.findViewById(R.id.busy);
        available = status.findViewById(R.id.available);


        ItemImages();
        Availability();
        Status();
        return view;

    }

    private void Status() {
        busy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 Toast.makeText(getActivity(), "busy", Toast.LENGTH_SHORT).show();
            }
        });
        available.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 Toast.makeText(getActivity(), "available", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void Availability() {

        shortterm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Toast.makeText(getActivity(), "shortterm", Toast.LENGTH_SHORT).show();
            }
        });
        longterm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Toast.makeText(getActivity(), "longterm", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void ItemImages() {
        gridView.setAdapter(new ImageAdapterMyRenit(getActivity()));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                // Send intent to SingleViewActivity
                Intent i = new Intent(getContext(), FullImageViewActivity.class);

                // Pass image index
                i.putExtra("id", position);
                startActivity(i);
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.profile_fragment_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

}
