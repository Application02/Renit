package com.sau.rentalclothsapp.Owner;


import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sau.rentalclothsapp.Owner.adepter.RecyclerInboxAdapter;
import com.sau.rentalclothsapp.R;

public class Inbox_Fragment_Owner extends Fragment {

    View view;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        view = inflater.inflate(R.layout.inbox_fregment_owner, container, false);
        getActivity().setTitle(" InBox");

        init();
        return view;

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.inbox_fragment_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void init() {
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab_owner);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // get the reference of RecyclerView
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_owner);
        // set a LinearLayoutManager with default vertical orientation
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        //  call the constructor of RecyclerInboxAdapter to send the reference and data to Adapter
        RecyclerInboxAdapter recyclerInboxAdapter = new RecyclerInboxAdapter(getActivity()/*, personNames*/);
        recyclerView.setAdapter(recyclerInboxAdapter); // set the Adapter to RecyclerView


    }


}
