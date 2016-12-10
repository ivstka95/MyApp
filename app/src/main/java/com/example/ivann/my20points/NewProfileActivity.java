package com.example.ivann.my20points;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;




public class NewProfileActivity extends AppCompatActivity {

    private static final int GALLERY_REQUEST = 2;
    private static final int LIST_VIEW_REQUEST = 1;
    private static final String PROFILE_OBJECT = "PROFILE_OBJECT";
    private TextView etLogin;
    private EditText etName, etSurName, etPhoneNumber, etEmail, etCountry, etCity, etNotes;
    private ImageView iProfilePicture;
    private Button bCancel, bSkip, bOk;
    public static Uri uriImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_profile);
        //Toast.makeText(getBaseContext(),"onCiiiiiiiick,", Toast.LENGTH_LONG).show();
        Intent i = getIntent();
        final Bundle extras = i.getExtras();
        String Login = extras.getString(LoginActivity.LOGIN);
        etLogin = (TextView) findViewById(R.id.etextLogin);
        etLogin.setText(Login);
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
                Toast.makeText(getBaseContext(),"222222222222,", Toast.LENGTH_LONG).show();
            }
        });
        bSkip = (Button) findViewById(R.id.bSkip);
        bSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etName.getText().length() == 0 && etSurName.getText().length() == 0 && etPhoneNumber.getText().length() == 0 && etEmail.getText().length() == 0 && etCountry.getText().length() == 0 && etCity.getText().length() == 0) {
                    Intent listViewActivity = new Intent(NewProfileActivity.this, ListViewActivity.class);
                    startActivity(listViewActivity);
                    //startActivityForResult(listViewActivity, LIST_VIEW_REQUEST);
                }
            }
        });
        bOk = (Button) findViewById(R.id.bOk);
        bOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getBaseContext(),"onClick,", Toast.LENGTH_LONG).show();
                if (InputChecker.checkEmail(etEmail.getText().toString())
                        && InputChecker.checkNumber(etPhoneNumber.getText().toString())
                        && etName.getText().length() != 0 && etSurName.getText().length() != 0
                        && etPhoneNumber.getText().length() != 0 && etEmail.getText().length() != 0
                        && etCountry.getText().length() != 0 && etCity.getText().length() != 0
                        && uriImage != null) {

                   // Toast.makeText(getBaseContext(),"Inside if,", Toast.LENGTH_LONG).show();
                    Intent listViewActivity = new Intent(NewProfileActivity.this, ListViewActivity.class);
                    listViewActivity.putExtra(PROFILE_OBJECT, new Profile(uriImage, etName.getText().toString(),
                            etSurName.getText().toString(),
                            etEmail.getText().toString(), etPhoneNumber.getText().toString(),
                            etCountry.getText().toString(), etCity.getText().toString(),
                            etNotes.getText().toString()));
                    ///sddddd




                    startActivityForResult(listViewActivity, LIST_VIEW_REQUEST);
                }


            }
        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        Bitmap bitmap = null;

        switch (requestCode) {
            case GALLERY_REQUEST:
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
            case LIST_VIEW_REQUEST:
                if (resultCode == RESULT_OK)
                    finish();

        }
    }
}
