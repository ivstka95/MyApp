package com.example.ivann.contacts;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import static com.example.ivann.contacts.ListViewActivity.ad;

public class NewProfileActivity extends AppCompatActivity implements Constants {


    private EditText etName, etSurName, etPhoneNumber, etEmail, etCountry, etCity, etNotes;
    private ImageView iProfilePicture;
    private Button bCancel, bSkip, bOk;
    public static Uri uriImage;
    private ArrayList<Profile> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_profile);
        Toast.makeText(NewProfileActivity.this, "OnCreate", Toast.LENGTH_LONG).show();
        list = new ArrayList<>();
        Intent i = getIntent();
        final Bundle extras = i.getExtras();
        String Login = extras.getString(LoginActivity.LOGIN);
        getSupportActionBar().setTitle(Login);
        etName = (EditText) findViewById(R.id.etName);
        etSurName = (EditText) findViewById(R.id.etSurName);
        etPhoneNumber = (EditText) findViewById(R.id.etPhoneNumber);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etCountry = (EditText) findViewById(R.id.etCountry);
        etCity = (EditText) findViewById(R.id.etCity);
        etNotes = (EditText) findViewById(R.id.etNotes);
        iProfilePicture = (ImageView) findViewById(R.id.iProfilePicture);
        iProfilePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, GALLERY_REQUEST);

            }
        });
        bCancel = (Button) findViewById(R.id.bCancel);
        bCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

            }
        });
        bSkip = (Button) findViewById(R.id.bSkip);
        bSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etName.getText().length() == 0 && etSurName.getText().length() == 0 && etPhoneNumber.getText().length() == 0 && etEmail.getText().length() == 0 && etCountry.getText().length() == 0 && etCity.getText().length() == 0) {
                    Toast.makeText(getBaseContext(), "Inside SKIP,", Toast.LENGTH_LONG).show();
                    Bundle b = new Bundle();
                    b.putParcelableArrayList(PROFILE_LIST, list);
                    Intent listViewActivityIntent = new Intent(NewProfileActivity.this, ListViewActivity.class);
                    listViewActivityIntent.putExtra(PROFILE_EXTRA, b);
                    startActivityForResult(listViewActivityIntent, LIST_VIEW_REQUEST);
                }
            }
        });
        bOk = (Button) findViewById(R.id.bOk);
        bOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {// тут мне надо дописать проверку в ифе о отправить обьект
                if (InputCheker.checkEmail(etEmail.getText().toString())
                        && InputCheker.checkNumber(etPhoneNumber.getText().toString())) {
                    Toast.makeText(getBaseContext(), "Inside OK,", Toast.LENGTH_LONG).show();
                    Profile p = new Profile(uriImage.toString(), etName.getText().toString(),
                            etSurName.getText().toString(), etPhoneNumber.getText().toString(),
                            etEmail.getText().toString(), etCountry.getText().toString(),
                            etCity.getText().toString(), etNotes.getText().toString());
                    list.add(p);
                    Bundle b = new Bundle();
                    b.putParcelableArrayList(PROFILE_LIST, list);
                    Intent listViewActivityIntent = new Intent(NewProfileActivity.this, ListViewActivity.class);
                    listViewActivityIntent.putExtra(PROFILE_EXTRA, b);
                    startActivityForResult(listViewActivityIntent, LIST_VIEW_REQUEST);

                }
            }


        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        Bitmap bitmap = null;
        if (requestCode == GALLERY_REQUEST)
            if (resultCode == RESULT_OK) {
                Uri selectedImage = imageReturnedIntent.getData();
                uriImage = imageReturnedIntent.getData();
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImage);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                iProfilePicture.setImageBitmap(bitmap);
            }
        if (requestCode == LIST_VIEW_REQUEST)
            if (resultCode == RESULT_OK)
                finish();
        if (requestCode == LIST_VIEW_REQUEST)
            if (resultCode == RESULT_CANCELED) {
                Intent i = getIntent();
                Toast.makeText(NewProfileActivity.this, "sizeBefore " + list.size(), Toast.LENGTH_LONG).show();
                if (i.hasExtra(PROFILE_EXTRA)) {
                    list.clear();
                    Bundle b = i.getBundleExtra(PROFILE_EXTRA);
                    ArrayList<Profile> aluc = b.getParcelableArrayList(PROFILE_LIST);
                    list.addAll(aluc);
                    Toast.makeText(NewProfileActivity.this, "sizeAfter " + list.size(), Toast.LENGTH_LONG).show();
                    ad.updateList(list);
                }
            }
    }


}