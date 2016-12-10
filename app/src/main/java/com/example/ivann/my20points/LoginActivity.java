package com.example.ivann.my20points;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    static final String LOGIN = "LOGIN";
    private EditText etLogin;
    private EditText etPassword;
    private Button bLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etLogin = (EditText) findViewById(R.id.etLogin);
        etPassword = (EditText) findViewById(R.id.etPassword);
        bLogin = (Button) findViewById(R.id.bLogin);
        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (InputChecker.checkLogin(etLogin.getText().toString()) && etPassword.getText().length() != 0) {
                    Intent newProfile = new Intent(getApplicationContext(),NewProfileActivity.class);
                    newProfile.putExtra(LOGIN,etLogin.getText().toString());
                    startActivity(newProfile);
                   // Toast.makeText(getBaseContext(),"1111111111", Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });

    }
}
