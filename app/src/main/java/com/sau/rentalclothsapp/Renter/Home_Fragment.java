package com.sau.rentalclothsapp.Renter;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sau.rentalclothsapp.R;

public class Home_Fragment extends Fragment {

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.home_fragment, container, false);
        getActivity().setTitle("Home");
        // Set title bar
       /* ((MainFragmentActivity) getActivity())
                .setActionBarTitle("Your title");
*/
        try {
           // ((RenterActivity) getActivity()).getSupportActionBar().setTitle("Hello World!");
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        return view;

    }

}
