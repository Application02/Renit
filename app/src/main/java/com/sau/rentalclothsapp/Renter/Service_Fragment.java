package com.sau.rentalclothsapp.Renter;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sau.rentalclothsapp.R;
import com.sau.rentalclothsapp.Renter.adepter.ViewPagerAdapter;

public class Service_Fragment extends Fragment {

    View view;

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        view = inflater.inflate(R.layout.service_fragment, container, false);
        getActivity().setTitle(" Service");


        viewPager = view.findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = view.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);


        return view;

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new Service_Problem_Fragment(), "Problem");
        adapter.addFragment(new Service_End_Fragment(), "Service End");
        adapter.addFragment(new Service_Extend_Fragment(), "Service Extend");
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();


    }
}
