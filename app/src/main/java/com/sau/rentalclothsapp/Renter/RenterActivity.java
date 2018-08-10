package com.sau.rentalclothsapp.Renter;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.text.Html;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.sau.rentalclothsapp.LoginActivity;
import com.sau.rentalclothsapp.R;

import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class RenterActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    JSONObject response, profile_pic_data, profile_pic_url;
    SharedPreferences pref;
    String displayname,displaysurname;
    private static final String TAG = "RenterActivity";

    TextView txtUname,txtheadername,txt_img_home,txt_img_inbox,txt_img_pro,txt_img_setting;
    ImageView imageView;
    public static final int PICK_IMAGE = 0;
    private Uri imageUri;

    Fragment fragment = null;
    Toolbar toolbar;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_renter);

        toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);

        imageView = headerView.findViewById(R.id.imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent1.setType("image/*");
                startActivityForResult(intent1, PICK_IMAGE);

            }
        });
        txtUname = findViewById(R.id.txtUname);
        txtheadername = headerView.findViewById(R.id.txtheadername);
        txt_img_home = findViewById(R.id.txt_img_home);
        txt_img_inbox = findViewById(R.id.txt_img_inbox);
        txt_img_pro = findViewById(R.id.txt_img_pro);
        txt_img_setting = findViewById(R.id.txt_img_setting);

        txt_img_home.setOnClickListener(this);
        txt_img_inbox.setOnClickListener(this);
        txt_img_pro.setOnClickListener(this);
        txt_img_setting.setOnClickListener(this);

        pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        SharedPreferences.Editor editor = pref.edit();
        try {
            displayname = pref.getString("firstname", null);
            displaysurname = pref.getString("surname",null);
            txtUname.setText(" Welcome "+displayname);

            Log.d(TAG, "displayname: " + displayname + " " +displaysurname);
            txtheadername.setText(displayname+" "+displaysurname);
            editor.commit();


        } catch (Exception e) {
            e.printStackTrace();
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        fragment = new Home_Fragment();
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.framlayout, fragment);
        transaction.commit();
        txt_img_home.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.homedark), null, null);
        txt_img_inbox.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.inboxlight), null, null);
        txt_img_pro.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.profilelight), null, null);
        txt_img_setting.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.settinglight), null, null);



        // NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }


    public void setActionBarTitle(String title) {
        /*.setTitle(title);*/
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {

            case PICK_IMAGE: {
                if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null) {

                    try {
                        imageUri = data.getData();
                        final InputStream inputStream = getContentResolver().openInputStream(imageUri);
                        final Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                        imageView.setImageBitmap(bitmap);

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();

                    }
                }
            }
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitByBackKey();

            //moveTaskToBack(false);

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    protected void exitByBackKey() {

        AlertDialog alertbox = new AlertDialog.Builder(this)
                .setMessage("Do you want to exit application?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                    // do something when the button is clicked
                    public void onClick(DialogInterface arg0, int arg1) {


                        finish();


                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {

                    // do something when the button is clicked
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                })
                .show();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.renter, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
            builder.setTitle(Html.fromHtml("</b>"+"Confirm?" +"</b>"));
            builder.setMessage("Do You Really Want To Exit?")
                    .setPositiveButton(Html.fromHtml("Yes"), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }
                    })
                    .setNegativeButton(Html.fromHtml("No"), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });

            android.app.AlertDialog alertDialog = builder.create();
            alertDialog.show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home1) {
            fragment = new Home_Fragment();

            txt_img_home.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.homedark), null, null);
            txt_img_inbox.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.inboxlight), null, null);
            txt_img_pro.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.profilelight), null, null);
            txt_img_setting.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.settinglight), null, null);

            // Handle the camera action
        } else if (id == R.id.nav_profile1) {
            fragment = new Profile_Fragment();


            txt_img_home.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.homelight), null, null);
            txt_img_inbox.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.inboxlight), null, null);
            txt_img_pro.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.profiledark), null, null);
            txt_img_setting.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.settinglight), null, null);


        } else if (id == R.id.nav_inbox1) {
            fragment = new Inbox_Fragment();


            txt_img_home.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.homelight), null, null);
            txt_img_inbox.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.inboxdark), null, null);
            txt_img_pro.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.profilelight), null, null);
            txt_img_setting.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.settinglight), null, null);


        } else if (id == R.id.nav_setting1) {
            fragment = new Setting_Fragment();

            txt_img_home.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.homelight), null, null);
            txt_img_inbox.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.inboxlight), null, null);
            txt_img_pro.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.profilelight), null, null);
            txt_img_setting.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.settingdark), null, null);


        } else if (id == R.id.nav_share1) {

            try {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_SUBJECT, "My application name");
                String sAux = "\nAmazing Application For space,equipment and dresses\n\n Ranter App ";
                sAux = sAux + "https://play.google.com/store/apps/details?\n\n";
                i.putExtra(Intent.EXTRA_TEXT, sAux);
                startActivity(Intent.createChooser(i, "choose one"));
            } catch (Exception e) {
                //e.toString();
            }

        } else if (id == R.id.nav_SignOut1) {

            android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
            builder.setTitle(Html.fromHtml("</b>"+"Confirm?" +"</b>"));
            builder.setMessage("You Want To SignOut?")
                    .setPositiveButton(Html.fromHtml("Yes"), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    })
                    .setNegativeButton(Html.fromHtml("No"), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });

            android.app.AlertDialog alertDialog = builder.create();
            alertDialog.show();

        }

        if (fragment != null) {
            FragmentManager manager = getFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.framlayout, fragment);
            transaction.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    //txt bottom menu onClick
    @Override
    public void onClick(View view) {

        if (view==txt_img_home)
        {
            txt_img_home.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.homedark), null, null);
            txt_img_inbox.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.inboxlight), null, null);
            txt_img_pro.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.profilelight), null, null);
            txt_img_setting.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.settinglight), null, null);

            fragment = new Home_Fragment();
            FragmentManager manager = getFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.framlayout, fragment);
            transaction.commit();
        }
        else if(view==txt_img_inbox)
        {
            txt_img_home.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.homelight), null, null);
            txt_img_inbox.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.inboxdark), null, null);
            txt_img_pro.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.profilelight), null, null);
            txt_img_setting.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.settinglight), null, null);

            fragment = new Inbox_Fragment();
            FragmentManager manager = getFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.framlayout, fragment);
            transaction.commit();

        }
        else if(view==txt_img_pro)
        {
            txt_img_home.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.homelight), null, null);
            txt_img_inbox.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.inboxlight), null, null);
            txt_img_pro.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.profiledark), null, null);
            txt_img_setting.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.settinglight), null, null);


            fragment = new Profile_Fragment();
            FragmentManager manager = getFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.framlayout, fragment);
            transaction.commit();

        }
        else if(view==txt_img_setting)
        {
            txt_img_home.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.homelight), null, null);
            txt_img_inbox.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.inboxlight), null, null);
            txt_img_pro.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.profilelight), null, null);
            txt_img_setting.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.settingdark), null, null);

            fragment = new Setting_Fragment();
            FragmentManager manager = getFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.framlayout, fragment);
            transaction.commit();
        }

    }
}
