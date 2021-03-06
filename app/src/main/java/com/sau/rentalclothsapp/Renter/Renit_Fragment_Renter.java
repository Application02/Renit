package com.sau.rentalclothsapp.Renter;


import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;


import com.sau.rentalclothsapp.R;
import com.sau.rentalclothsapp.Renter.adepter.ImageAdapterRenit;

public class Renit_Fragment_Renter extends Fragment {

    View view;
    GridView gridView;
    Fragment fragment = null;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        view = inflater.inflate(R.layout.renit_fragment_renter, container, false);
        getActivity().setTitle(" Renit");

        gridView = view.findViewById(R.id.gridView);

        ItemImages();
        return view;

    }

    private void ItemImages() {
        gridView.setAdapter(new ImageAdapterRenit(getActivity()));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                // Send intent to SingleViewActivity
                Intent i = new Intent(getContext(), FullImageViewActivity.class);

                // Pass image index
                i.putExtra("id", position);
                startActivity(i);
              /*  fragment = new FullImageViewFragment();
                android.support.v4.app.FragmentManager manager = getFragmentManager();
                android.support.v4.app.FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.framlayout, new FullImageViewFragment());
                transaction.commit();

*/


            }
        });


    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.profile_fragment_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

}
