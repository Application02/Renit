package com.sau.rentalclothsapp.Owner;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.sau.rentalclothsapp.R;

import es.dmoral.toasty.Toasty;

public class SendMessageOwnerActivity extends AppCompatActivity {

    ActionBar actionBar;
    Toolbar toolbar;
    EditText edt_to;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_message_owner);


        toolbar = findViewById(R.id.toolbar_send_message);
        setSupportActionBar(toolbar);
        actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        edt_to = findViewById(R.id.edt_to);



        getSupportActionBar().setTitle("Send Message"); // for set actionbar title
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
    }
/*

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
*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.send_message_owner_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case android.R.id.home:
                onBackPressed();
                return true;

            case R.id.item1:
                if (!edt_to.getText().toString().isEmpty() )
                {
                    Toasty.success(getApplicationContext(), "Message Send", Toast.LENGTH_SHORT, true).show();
                   // Toast.makeText(getApplicationContext(),"Message Send",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toasty.error(getApplicationContext(), "Email Id Required", Toast.LENGTH_SHORT, true).show();
                    //Toast.makeText(getApplicationContext(),"Email Id Required",Toast.LENGTH_LONG).show();
                }

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
