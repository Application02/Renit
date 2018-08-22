package com.sau.rentalclothsapp.Renter;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.sau.rentalclothsapp.R;

import java.io.FileNotFoundException;
import java.io.InputStream;

import static android.app.Activity.RESULT_OK;

public class Profile_Fragment extends Fragment implements View.OnClickListener {

    public static final int PICK_IMAGE = 0;
    private static final int TAKE_PICTURE = 1;
    View view;
    ImageView profile;
    AlertDialog alert;
    EditText edtfullname, edtmailid, edtmobileno;
    TextView txtfullname,txtmailid,txtmobileno;
    Button btnfullname, btnmailid, btnmobileno,btnok;
    private Uri imageUri;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        view = inflater.inflate(R.layout.profile_fragment, container, false);
        profile = view.findViewById(R.id.imageViewrenterprofile);
        profile.setOnClickListener(this);
        getActivity().setTitle(" Profile");

        edtfullname = view.findViewById(R.id.edtfullname);
        edtmailid = view.findViewById(R.id.edtmailid);
        edtmobileno = view.findViewById(R.id.edtmobileno);

        btnfullname = view.findViewById(R.id.btnfullname);
        btnmailid = view.findViewById(R.id.btnmailid);
        btnmobileno = view.findViewById(R.id.btnmobileno);

        txtfullname = view.findViewById(R.id.txtfullname);
        txtmailid = view.findViewById(R.id.txtmailid);
        txtmobileno = view.findViewById(R.id.txtmobileno);

        btnfullname.setOnClickListener(this);
        btnmailid.setOnClickListener(this);
        btnmobileno.setOnClickListener(this);

        btnok=view.findViewById(R.id.btnok);

        btnok.setOnClickListener(this);



        return view;

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.profile_fragment_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @SuppressLint("ResourceType")
    @Override
    public void onClick(View view) {
        if (view == profile) {
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

        if (view == btnfullname) {

            btnok.setVisibility(View.VISIBLE);

            edtfullname.setVisibility(View.VISIBLE);
            txtfullname.setVisibility(View.GONE);

            edtfullname.setSelection(edtfullname.getText().length());

            edtmailid.setVisibility(View.GONE);
            txtmailid.setVisibility(View.VISIBLE);

            edtmobileno.setVisibility(View.GONE);
            txtmobileno.setVisibility(View.VISIBLE);

            edtfullname.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {

                    txtfullname.setText(edtfullname.getText().toString());
                }
            });

        }
        if (view == btnmailid) {

            btnok.setVisibility(View.VISIBLE);

            edtmailid.setVisibility(View.VISIBLE);
            txtmailid.setVisibility(View.GONE);

            edtmailid.setSelection(edtmailid.getText().length());

            edtfullname.setVisibility(View.GONE);
            txtfullname.setVisibility(View.VISIBLE);

            edtmobileno.setVisibility(View.GONE);
            txtmobileno.setVisibility(View.VISIBLE);

            edtmailid.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {

                        txtmailid.setText(edtmailid.getText().toString());
                }
            });
        }
        if (view == btnmobileno) {
            btnok.setVisibility(View.VISIBLE);

            edtmobileno.setVisibility(View.VISIBLE);
            txtmobileno.setVisibility(View.GONE);

            edtmobileno.setSelection(edtmobileno.getText().length());

            edtfullname.setVisibility(View.GONE);
            txtfullname.setVisibility(View.VISIBLE);

            edtmailid.setVisibility(View.GONE);
            txtmailid.setVisibility(View.VISIBLE);

            edtmobileno.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {

                    txtmobileno.setText(edtmobileno.getText().toString());
                }
            });


        }
        if (view == btnok)
        {
            edtfullname.setVisibility(View.GONE);
            txtfullname.setVisibility(View.VISIBLE);
            edtmailid.setVisibility(View.GONE);
            txtmailid.setVisibility(View.VISIBLE);
            edtmobileno.setVisibility(View.GONE);
            txtmobileno.setVisibility(View.VISIBLE);

            btnok.setVisibility(View.GONE);

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
