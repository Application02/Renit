package com.sau.rentalclothsapp.Renter;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.sau.rentalclothsapp.Owner.adepter.SlidingImage_AdapterMyRenit;
import com.sau.rentalclothsapp.Renter.adepter.ImageAdapterRenit;
import com.sau.rentalclothsapp.R;
import com.sau.rentalclothsapp.Renter.adepter.ImageAdapterRenit;
import com.sau.rentalclothsapp.Renter.adepter.SlidingImage_AdapterRenit;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class FullImageViewActivity extends AppCompatActivity {

    private static final Integer[] IMAGES = {R.drawable.homedark, R.drawable.profiledark, R.drawable.inboxdark};
    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private ArrayList<Integer> ImagesArray = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_image_view2);

        // Get intent data
        Intent intent = getIntent();

       /* // Selected image id
        int position = intent.getExtras().getInt("id");
        ImageAdapterRenit imageAdapterMyRenit = new ImageAdapterRenit(this);

        ImageView imageView = (ImageView) findViewById(R.id.SingleView);
        imageView.setImageResource(imageAdapterMyRenit.mThumbIds[position]);*/

        initialization ();

    }
        private void initialization () {
            for (int i = 0; i < IMAGES.length; i++)
                ImagesArray.add(IMAGES[i]);
            mPager = (ViewPager) findViewById(R.id.pager);

            mPager.setAdapter(new SlidingImage_AdapterRenit(FullImageViewActivity.this, ImagesArray));

            CirclePageIndicator indicator = (CirclePageIndicator) findViewById(R.id.indicator);

            indicator.setViewPager(mPager);

            final float density = getResources().getDisplayMetrics().density;

            indicator.setRadius(5 * density);

            NUM_PAGES = IMAGES.length;

            final Handler handler = new Handler();
            final Runnable Update = new Runnable() {
                public void run() {
                    if (currentPage == NUM_PAGES) {
                        currentPage = 0;
                    }
                    mPager.setCurrentItem(currentPage++, true);
                }
            };
            Timer swipeTimer = new Timer();
            swipeTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.post(Update);
                }
            }, 3000, 3000);

            indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

                @Override
                public void onPageSelected(int position) {
                    currentPage = position;

                }

                @Override
                public void onPageScrolled(int pos, float arg1, int arg2) {

                }

                @Override
                public void onPageScrollStateChanged(int pos) {

                }
            });

        }


}