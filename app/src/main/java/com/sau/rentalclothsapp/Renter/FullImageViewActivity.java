package com.sau.rentalclothsapp.Renter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.sau.rentalclothsapp.Renter.adepter.ImageAdapterRenit;
import com.sau.rentalclothsapp.R;
import com.sau.rentalclothsapp.Renter.adepter.ImageAdapterRenit;

public class FullImageViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_image_view2);

        // Get intent data
        Intent i = getIntent();

        // Selected image id
        int position = i.getExtras().getInt("id");
        ImageAdapterRenit imageAdapterMyRenit = new ImageAdapterRenit(this);

        ImageView imageView = (ImageView) findViewById(R.id.SingleView);
        imageView.setImageResource(imageAdapterMyRenit.mThumbIds[position]);
    }
}
