package com.example.ivann.contacts;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListViewActivity extends AppCompatActivity implements Constants {

    public static ListView lv;
    private EditText etNumber;
    private Button bBack;
    private Button bDelete;
    private Button bShow;
    private Button bClose;
    private Button bLogOut;
    public static ProfileAdapter ad;
    private SharedPreferences sPref;
    private ArrayList<Profile> p;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        Toast.makeText(getBaseContext(), "Inside OnCreateListViewActivity,", Toast.LENGTH_LONG).show();
        lv = (ListView) findViewById(R.id.lvMain);
        ad = new ProfileAdapter(this, R.layout.layout_item_custom);
        lv.setAdapter(ad);
        etNumber = (EditText) findViewById(R.id.etShowDelete);
        bDelete = (Button) findViewById(R.id.bDelete);
        bDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ListViewActivity.this);
                builder.setTitle("Delete profile #" + etNumber.getText().toString());
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        p.remove(Integer.parseInt(etNumber.getText().toString()) - 1);
                        ad.updateList(p);
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.setNeutralButton("may be", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.create().show();
            }
        });
        bShow = (Button) findViewById(R.id.bShow);
        bShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
                ProfileFragment.newInstance(p.get(Integer.parseInt(etNumber.getText().toString()) - 1)).show(ft,"some tag"));

//                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//                TestDialogFragment testDialogFragment = new TestDialogFragment();
//                testDialogFragment.setArguments(null);
//                TestDialogFragment.newInstance(null).show(ft, "Some_tag");

            }
        });
        bBack = (Button) findViewById(R.id.bBack);
        bBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle b = new Bundle();
                b.putParcelableArrayList(PROFILE_LIST, p);
                Intent intent = new Intent();
                intent.putExtra(PROFILE_EXTRA, b);
                setResult(RESULT_CANCELED, intent);
                finish();
            }
        });
        bClose = (Button) findViewById(R.id.bClose);
        bClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_OK);
                finish();
            }
        });
        bLogOut = (Button) findViewById(R.id.bLogOut);
        bLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sPref = getSharedPreferences(SAVE_LOGIN_FILE_NAME, MODE_PRIVATE);
                SharedPreferences.Editor editor = sPref.edit();
                editor.putString(LOGIN_SAVED, "");
                editor.commit();
                Intent logInActivity = new Intent(ListViewActivity.this, LoginActivity.class);
                startActivity(logInActivity);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        p = new ArrayList<>();
        Intent in = getIntent();
        Toast.makeText(ListViewActivity.this, "OnResumeSize " + p.size(),Toast.LENGTH_LONG).show();
        if (in.hasExtra(PROFILE_EXTRA)) {
            p.clear();
            Bundle b = in.getBundleExtra(PROFILE_EXTRA);
            ArrayList<Profile> aluc = b.getParcelableArrayList(PROFILE_LIST);
            p.addAll(aluc);
            Toast.makeText(ListViewActivity.this, "OnResumeGotSize " + p.size(),Toast.LENGTH_LONG).show();
            ad.updateList(p);
        }
    }
}
