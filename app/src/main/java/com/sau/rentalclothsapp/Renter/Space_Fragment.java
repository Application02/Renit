package com.sau.rentalclothsapp.Renter;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


import com.sau.rentalclothsapp.R;

public class Space_Fragment extends Fragment {

    View view;
    String[] activities  = { "Wedding", "Birthday Parties", "Meetings", "Dinner Parties", "Networking Events","Film Shoots","Cooperate Parties","Launch Event","Brand Activation","Production"};
    Spinner spin;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        view = inflater.inflate(R.layout.space_fragment, container, false);

         spin = view.findViewById(R.id.spinneractivities);


        SelectActivities();
        return view;

    }

    private void SelectActivities() {
        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,activities);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);

        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
             //   Toast.makeText(getContext(),activities [position] , Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.space_fragment_menu, menu);
        super.onCreateOptionsMenu(menu,inflater);
    }

}