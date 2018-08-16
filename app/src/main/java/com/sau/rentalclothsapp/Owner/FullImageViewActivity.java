package com.sau.rentalclothsapp.Owner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.sau.rentalclothsapp.Owner.adepter.ImageAdapterMyRenit;
import com.sau.rentalclothsapp.R;

public class FullImageViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_image_view);

        // Get intent data
        Intent i = getIntent();

        // Selected image id
        int position = i.getExtras().getInt("id");
        ImageAdapterMyRenit imageAdapterMyRenit = new ImageAdapterMyRenit(this);

        ImageView imageView = (ImageView) findViewById(R.id.SingleView);
        imageView.setImageResource(imageAdapterMyRenit.mThumbIds[position]);
    }
}
