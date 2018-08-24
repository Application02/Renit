package com.sau.rentalclothsapp.Owner;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.sau.rentalclothsapp.Owner.adepter.SlidingImage_AdapterSales;
import com.sau.rentalclothsapp.R;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class FullImageViewSalesActivity extends AppCompatActivity {


    private static final Integer[] IMAGES = {R.drawable.dress1, R.drawable.dress2, R.drawable.dress3};
    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private ArrayList<Integer> ImagesArray = new ArrayList<Integer>();

    ActionBar actionBar;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_image_view_sales);


        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setTitle("Images"); // for set actionbar title
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // for add back arrow in action bar

        //icon set in title bar
        // Read your drawable from somewhere
        Drawable dr = getResources().getDrawable(R.drawable.ic_appicon_round);
        Bitmap bitmap = ((BitmapDrawable) dr).getBitmap();
        // Scale it to 50 x 50
        Drawable d = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, 90, 90, true));
        // Set your new, scaled drawable "d"

        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(d);


        // Get intent data
        Intent intent = getIntent();

        // Selected image id
        int position = intent.getExtras().getInt("id");
       /* ImageAdapterSales imageAdapterSales = new ImageAdapterSales(this);

        ImageView imageView = (ImageView) findViewById(R.id.SingleView1);
        imageView.setImageResource(imageAdapterSales.mThumbIds[position]);*/

        initialization();


    }

    //this method use for Title bar back arrow to back
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }



    private void initialization() {
            for (int i = 0; i < IMAGES.length; i++)
                ImagesArray.add(IMAGES[i]);
            mPager = (ViewPager) findViewById(R.id.pager);

            mPager.setAdapter(new SlidingImage_AdapterSales(FullImageViewSalesActivity.this, ImagesArray));

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
