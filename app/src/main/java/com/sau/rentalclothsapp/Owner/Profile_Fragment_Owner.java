package com.sau.rentalclothsapp.Owner;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sau.rentalclothsapp.R;

public class Profile_Fragment_Owner extends Fragment {

    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.profile_fragment_owner, container, false);

        getActivity().setTitle("Profile");
        return view;

    }

}
