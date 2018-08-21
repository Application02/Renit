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

import com.sau.rentalclothsapp.Owner.adepter.ImageAdapterMyRenit;
import com.sau.rentalclothsapp.R;

public class Sales_Fragment_Owner extends Fragment {

    View view;
    GridView gridView;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        view = inflater.inflate(R.layout.sales_fragment_owner, container, false);
        getActivity().setTitle(" Sales");

        gridView = view.findViewById(R.id.gridView1);
        ItemImages();
        return view;

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.profile_fragment_menu, menu);
        super.onCreateOptionsMenu(menu,inflater);
    }



    private void ItemImages() {
        gridView.setAdapter(new ImageAdapterMyRenit(getActivity()));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                // Send intent to SingleViewActivity
                Intent i = new Intent(getContext(), FullImageViewSalesActivity.class);

                // Pass image index
                i.putExtra("id", position);
                startActivity(i);
            }
        });
    }

}
