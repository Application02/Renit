package com.sau.rentalclothsapp.Owner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.sau.rentalclothsapp.Owner.adepter.ImageAdapterMyRenit;
import com.sau.rentalclothsapp.Owner.adepter.ImageAdapterSales;
import com.sau.rentalclothsapp.R;

public class FullImageViewSalesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_image_view_sales);


        // Get intent data
        Intent i = getIntent();

        // Selected image id
        int position = i.getExtras().getInt("id");
        ImageAdapterSales imageAdapterSales = new ImageAdapterSales(this);

        ImageView imageView = (ImageView) findViewById(R.id.SingleView1);
        imageView.setImageResource(imageAdapterSales.mThumbIds[position]);
    }
}
