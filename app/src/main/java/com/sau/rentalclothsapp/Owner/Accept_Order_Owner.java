package com.sau.rentalclothsapp.Owner;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.sau.rentalclothsapp.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Calendar;

public class Accept_Order_Owner extends AppCompatActivity implements View.OnClickListener {


    public static final int PICK_IMAGE = 0;
    private static final int TAKE_PICTURE = 1;
    ImageView btncamera, btngallery;
    TextView txtDate, txtStartTime;
    private int mYear, mMonth, mDay, mHour, mMinute;
    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accept__order__owner);

        btncamera = findViewById(R.id.btncamera);
        btngallery = findViewById(R.id.btngallery);
        txtDate = findViewById(R.id.return_date);
        txtStartTime = findViewById(R.id.in_starttime1);

        btncamera.setOnClickListener(this);
        btngallery.setOnClickListener(this);
        txtDate.setOnClickListener(this);
        txtStartTime.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == btncamera) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, TAKE_PICTURE);

        }
        if (view == btngallery) {
            Intent intent1 = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            intent1.setType("image/*");
            intent1.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
            startActivityForResult(intent1, PICK_IMAGE);

        }
        if (view == txtDate) {
            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();

        }
        if (view == txtStartTime) {
            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            txtStartTime.setText(hourOfDay + ":" + minute);
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case TAKE_PICTURE: {

                if (requestCode == TAKE_PICTURE && resultCode == RESULT_OK && data != null) {

                    Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                    btncamera.setImageBitmap(bitmap);

                    createDirectoryAndSaveFile(bitmap, "image1");

                    //   MediaStore.Images.Media.insertImage(getContentResolver(), yourBitmap, yourTitle , yourDescription);


/*                    //send second activity
                    btncamera.buildDrawingCache();
                    Bitmap bitmap1 = btncamera.getDrawingCache();

                    Intent intent = new Intent(this, NewActivity.class);
                    intent.putExtra("BitmapImage", bitmap1);*/

   /*                 and second activity get image by
                    Bitmap bitmap = (Bitmap) intent.getParcelableExtra("BitmapImage");*/

                }
                break;
            }

            case PICK_IMAGE: {
                if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null) {

                    try {
                        imageUri = data.getData();
                        final InputStream inputStream = getContentResolver().openInputStream(imageUri);
                        final Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                        btngallery.setImageBitmap(bitmap);

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();

                    }
                }
            }
        }
    }

    private void createDirectoryAndSaveFile(Bitmap imageToSave, String fileName) {

        File direct = new File(Environment.getExternalStorageDirectory() + "/Renit");

        if (!direct.exists()) {
            File wallpaperDirectory = new File("/sdcard/Renit/");
            wallpaperDirectory.mkdirs();
        }

        File file = new File(new File("/sdcard/Renit/"), fileName);
        if (file.exists()) {
            file.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(file);
            imageToSave.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
