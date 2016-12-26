package com.example.ivann.contacts;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;


public class LoginActivity extends AppCompatActivity implements Constants {

    private EditText etLogin;
    private EditText etPassword;
    private Button bLogin;
    private CheckBox cbSaveMe;
    private SharedPreferences sPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etLogin = (EditText) findViewById(R.id.etLogin);
        LoadLogin();
        etPassword = (EditText) findViewById(R.id.etPassword);
        bLogin = (Button) findViewById(R.id.bLogin);
        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (InputCheker.checkLogin(etLogin.getText().toString()) && etPassword.getText().length() != 0) {
                    Intent newProfile = new Intent(getApplicationContext(), NewProfileActivity.class);
                    newProfile.putExtra(LOGIN, etLogin.getText().toString());
                    startActivity(newProfile);
                    finish();
                }
            }
        });

        cbSaveMe = (CheckBox) findViewById(R.id.cbSaveMe);
    }

    protected void SaveLogin() {
        if (cbSaveMe.isChecked()) {
            sPref = getSharedPreferences(SAVE_LOGIN_FILE_NAME, MODE_PRIVATE);
            SharedPreferences.Editor editor = sPref.edit();
            editor.putString(LOGIN_SAVED, etLogin.getText().toString());
            editor.commit();
        }
    }

    protected void LoadLogin() {
        sPref = getSharedPreferences(SAVE_LOGIN_FILE_NAME, MODE_PRIVATE);
        String savedLogin = sPref.getString(LOGIN_SAVED, "");
        etLogin.setText(savedLogin);

    }

    @Override
    protected void onDestroy() {
        SaveLogin();
        super.onDestroy();
    }

}
