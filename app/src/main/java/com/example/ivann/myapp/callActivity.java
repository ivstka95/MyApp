package com.example.ivann.myapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class callActivity extends AppCompatActivity implements View.OnClickListener {
    Button bCall;
    EditText etNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);
        bCall = (Button) findViewById(R.id.bCall);
        bCall.setOnClickListener(this);
        etNumber = (EditText) findViewById(R.id.etNumber);
    }

    @Override
    public void onClick(View view) {

        Intent intent = new Intent();

        intent.setAction(Intent.ACTION_CALL);

        intent.setData(Uri.parse("tel:" + etNumber.getText().toString()));

        if (etNumber.getText().toString().charAt(0) == '+') {

            if(etNumber.getText().length() != 13)
                Toast.makeText(getBaseContext(),"enter 12 numbers",Toast.LENGTH_LONG).show();

            if (etNumber.getText().length() == 13)
                startActivity(intent);

        }

        if (etNumber.getText().toString().charAt(0) == '0') {

            if(etNumber.getText().length() != 10)
                Toast.makeText(getBaseContext(),"enter 10 numbers",Toast.LENGTH_LONG).show();

            if (etNumber.getText().length() == 10)
                startActivity(intent);

        }
    }


}
