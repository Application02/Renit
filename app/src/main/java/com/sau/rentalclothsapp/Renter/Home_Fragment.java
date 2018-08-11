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

public class Home_Fragment extends Fragment {

    View view;

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.home_fragment, container, false);
        getActivity().setTitle("Home");


        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) view.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);





        return view;

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getFragmentManager());
        adapter.addFragment(new Dresses_Fragment(), "Dresses");
        adapter.addFragment(new Space_Fragment(), "Space");
        adapter.addFragment(new Equipment_Fragment(), "Equipment");
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();

    }
}
