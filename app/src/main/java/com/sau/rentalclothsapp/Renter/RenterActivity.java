package com.sau.rentalclothsapp.Renter;


import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sau.rentalclothsapp.LoginActivity;
import com.sau.rentalclothsapp.Owner.OwnerActivity;
import com.sau.rentalclothsapp.R;

import java.io.FileNotFoundException;
import java.io.InputStream;

import es.dmoral.toasty.Toasty;

public class RenterActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    public static final int PICK_IMAGE = 0;
    private static final int TAKE_PICTURE = 1;
    private static final String TAG = "RenterActivity";
    SharedPreferences pref;
    String displayname, displaysurname;
    TextView txtUname, txtheadername, txt_img_home, txt_img_inbox, txt_img_pro, txt_img_setting;
    ImageView imageView;
    Fragment fragment = null;
    Toolbar toolbar;
    private Uri imageUri;
    android.app.AlertDialog alert;
    SharedPreferences preferences;
    SharedPreferences.Editor editor1;
    TextView txtcamera,txtgallery,txtcancel;
    android.app.AlertDialog.Builder alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_renter);

        toolbar = findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);

        //icon set in title bar

        // Read your drawable from somewhere
        Drawable dr = getResources().getDrawable(R.drawable.ic_appicon_round);
        Bitmap bitmap = ((BitmapDrawable) dr).getBitmap();
// Scale it to 50 x 50
        Drawable d = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, 90, 90, true));
// Set your new, scaled drawable "d"

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(d);


      /* // toolbar.setLogo(R.mipmap.ic_toolbaricon);


        //icon set in title bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.mipmap.ic_appicon_round);
*/

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);

        //shared pref for Check user already login or first time
        preferences = getApplicationContext().getSharedPreferences("logindata", 0);
        editor1 = preferences.edit();
        editor1.putString("value", "2");
        editor1.apply();// Storing string

        imageView = headerView.findViewById(R.id.imageView);
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

        imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    // get prompts.xml view
                    LayoutInflater li = LayoutInflater.from(getApplicationContext());
                    View promptsView = li.inflate(R.layout.profile_image_dailog, null);
                    alertDialog = new android.app.AlertDialog.Builder(RenterActivity.this);
                    alertDialog.setView(promptsView);
                    // alertDialog.setTitle("Update Profile");
                    alertDialog.setMessage("Update Profile Picture");

                     txtcamera = (TextView) promptsView.findViewById(R.id.txtcamera);
                     txtgallery = promptsView.findViewById(R.id.txtgallery);
                     txtcancel = promptsView.findViewById(R.id.txtcancel);

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                            txtcamera.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                    startActivityForResult(intent, TAKE_PICTURE);

                                }
                            });
                            txtgallery.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Intent intent1 = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                    intent1.setType("image/*");
                                    startActivityForResult(intent1, PICK_IMAGE);
                                }
                            });
                            txtcancel.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    alert.dismiss();
                                }
                            });

                            alert = alertDialog.create();
                            alert.show();

                        } else {
                            requestPermissions(new String[]{Manifest.permission.CAMERA}, 666);  // Comment 26
                        }
                    }

                }
            });


            pref = getApplicationContext().getSharedPreferences("MyPref", 0);
            SharedPreferences.Editor editor = pref.edit();
            try {
                displayname = pref.getString("firstname", null);
                displaysurname = pref.getString("surname", null);
                txtUname.setText(" Welcome " + displayname);

                Log.d(TAG, "displayname: " + displayname + " " + displaysurname);
                txtheadername.setText(displayname + " " + displaysurname);
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
        android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.framlayout, new Home_Fragment());
        transaction.commit();
        txt_img_home.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.homedark), null, null);
        txt_img_inbox.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.inboxlight), null, null);
        txt_img_pro.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.profilelight), null, null);
        txt_img_setting.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.settinglight), null, null);


        // NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public void refresh() {
/*        finish();
        overridePendingTransition( 0, 0);
        startActivity(getIntent());
        overridePendingTransition( 0, 0);*/
        Intent intent = getIntent();
        overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);
    }

    //this method use for run time permission
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {

            case 666: // User selected Allowed  Permission Granted
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    txtcamera.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(intent, TAKE_PICTURE);

                        }
                    });
                    txtgallery.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent1 = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            intent1.setType("image/*");
                            startActivityForResult(intent1, PICK_IMAGE);
                        }
                    });
                    txtcancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            alert.dismiss();
                        }
                    });

                    alert = alertDialog.create();
                    alert.show();


                    // User selected the Never Ask Again Option Change settings in app settings manually
                } else if (Build.VERSION.SDK_INT >= 23 && !shouldShowRequestPermissionRationale(permissions[0])) {

                    android.support.v7.app.AlertDialog.Builder alertDialogBuilder = new android.support.v7.app.AlertDialog.Builder(this, R.style.AppBaseTheme);
                    alertDialogBuilder.setTitle("Change Permissions in Settings");
                    alertDialogBuilder
                            .setMessage("Click SETTINGS to Manually Set\n\n" + "Permissions to use Change Profile Picture")
                            .setCancelable(false)
                            .setPositiveButton("SETTINGS", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                    Uri uri = Uri.fromParts("package", getPackageName(), null);
                                    intent.setData(uri);
                                    startActivityForResult(intent, 1000);
                                }
                            });

                    android.support.v7.app.AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();

                } else {
                    // User selected Deny Dialog to EXIT App ==> OR <== RETRY to have a second chance to Allow Permissions
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {



                        android.support.v7.app.AlertDialog.Builder alertDialogBuilder = new android.support.v7.app.AlertDialog.Builder(this);
                        // android.support.v7.app.AlertDialog.Builder alertDialogBuilder = new android.support.v7.app.AlertDialog.Builder(this, R.style.AppTheme);
                        alertDialogBuilder.setTitle("Please Give Permission");
                        alertDialogBuilder
                                .setMessage("Click RETRY to Set Permissions to Allow Camera \n\n" + "Click EXIT to the Close App")
                                .setCancelable(false)
                                .setPositiveButton("RETRY", new DialogInterface.OnClickListener() {

                                    public void onClick(DialogInterface dialog, int id) {
                                       /* requestPermissions(new String[]{Manifest.permission.CAMERA},99);*/
          /*                              finish();
                                        startActivity(getIntent());*/
                                      refresh();
        /*                                Intent i = new Intent(RenterActivity.this, RenterActivity.class);
                                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(i);*/
                                    }
                                })
                                .setNegativeButton("EXIT", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        finish();
                                        dialog.cancel();
                                    }
                                });
                        android.support.v7.app.AlertDialog alertDialog = alertDialogBuilder.create();
                        alertDialog.show();
                    }
                }
                break;
        }};





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
                        alert.dismiss();

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();

                    }
                }
            }

            case TAKE_PICTURE: {
                if (requestCode == TAKE_PICTURE && resultCode == RESULT_OK && data != null) {

                    Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                    imageView.setImageBitmap(bitmap);
                    alert.dismiss();

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


    //option menu for exit
   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.renter, menu);
        return true;
    }*/

  /*  @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
            builder.setTitle(Html.fromHtml("</b>" + "Confirm?" + "</b>"));
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
    }*/

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
            builder.setTitle(Html.fromHtml("</b>" + "Confirm?" + "</b>"));
            builder.setMessage("You Want To SignOut?")
                    .setPositiveButton(Html.fromHtml("Yes"), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            editor1.clear();
                            editor1.commit();
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

        }else if (id == R.id.nav_myrenit) {

            fragment = new Renit_Fragment_Renter();

            txt_img_home.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.homelight), null, null);
            txt_img_inbox.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.inboxlight), null, null);
            txt_img_pro.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.profilelight), null, null);
            txt_img_setting.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.settinglight), null, null);


        }
        else if (id == R.id.nav_service) {
            fragment = new Service_Fragment();


            txt_img_home.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.homelight), null, null);
            txt_img_inbox.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.inboxlight), null, null);
            txt_img_pro.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.profilelight), null, null);
            txt_img_setting.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.settinglight), null, null);


        }

        if (fragment != null) {
            FragmentManager manager = getSupportFragmentManager();
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

        if (view == txt_img_home) {
            txt_img_home.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.homedark), null, null);
            txt_img_inbox.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.inboxlight), null, null);
            txt_img_pro.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.profilelight), null, null);
            txt_img_setting.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.settinglight), null, null);

            fragment = new Home_Fragment();
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.framlayout, fragment);
            transaction.commit();
        } else if (view == txt_img_inbox) {
            txt_img_home.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.homelight), null, null);
            txt_img_inbox.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.inboxdark), null, null);
            txt_img_pro.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.profilelight), null, null);
            txt_img_setting.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.settinglight), null, null);

            fragment = new Inbox_Fragment();
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.framlayout, fragment);
            transaction.commit();

        } else if (view == txt_img_pro) {
            txt_img_home.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.homelight), null, null);
            txt_img_inbox.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.inboxlight), null, null);
            txt_img_pro.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.profiledark), null, null);
            txt_img_setting.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.settinglight), null, null);


            fragment = new Profile_Fragment();
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.framlayout, fragment);
            transaction.commit();

        } else if (view == txt_img_setting) {
            txt_img_home.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.homelight), null, null);
            txt_img_inbox.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.inboxlight), null, null);
            txt_img_pro.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.profilelight), null, null);
            txt_img_setting.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.settingdark), null, null);

            fragment = new Setting_Fragment();
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.framlayout, fragment);
            transaction.commit();
        }

    }
}
