package com.sau.rentalclothsapp.Owner;


import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sau.rentalclothsapp.R;

import java.io.FileNotFoundException;
import java.io.InputStream;

import static android.app.Activity.RESULT_OK;

public class Profile_Fragment_Owner extends Fragment implements View.OnClickListener {

    View view;
    ImageView profile;
    public static final int PICK_IMAGE = 0;
    private static final int TAKE_PICTURE = 1;
    AlertDialog alert;
    private Uri imageUri;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        view = inflater.inflate(R.layout.profile_fragment_owner, container, false);
        profile = view.findViewById(R.id.imageViewownerprofile);
        profile.setOnClickListener(this);
        getActivity().setTitle(" Profile");
        return view;

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.profile_fragment_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onClick(View view) {
        if (view == profile)
        {
            // get prompts.xml view
            LayoutInflater li = LayoutInflater.from(getContext());
            View promptsView = li.inflate(R.layout.profile_image_dailog, null);
            final AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
            alertDialog.setView(promptsView);
            // alertDialog.setTitle("Update Profile");
            alertDialog.setMessage("Update Profile Image");

            TextView txtcamera = (TextView) promptsView.findViewById(R.id.txtcamera);
            TextView txtgallery = promptsView.findViewById(R.id.txtgallery);
            TextView txtcancel = promptsView.findViewById(R.id.txtcancel);
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
               /* alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        return;
                    } });*/

            // alertDialog.show();

            alert = alertDialog.create();
            alert.show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {

            case PICK_IMAGE: {
                if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null) {


                    try {
                        imageUri = data.getData();
                        final InputStream inputStream = getActivity().getContentResolver().openInputStream(imageUri);
                        final Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                        profile.setImageBitmap(bitmap);
                        alert.dismiss();

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();

                    }
                }

            }
            case TAKE_PICTURE: {
                if (requestCode == TAKE_PICTURE && resultCode == RESULT_OK && data != null) {

                    Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                    profile.setImageBitmap(bitmap);
                    alert.dismiss();

                }
            }
        }
    }
}
