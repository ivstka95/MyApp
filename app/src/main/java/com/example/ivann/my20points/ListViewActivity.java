package com.example.ivann.my20points;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListViewActivity extends AppCompatActivity {

    private static final String PROFILE_OBJECT = "PROFILE_OBJECT";
    public static ListView lv;
    private Button bBack, bClose;
    public static ProfileAdapter ad;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        Intent i = getIntent();
        Bundle extras = i.getExtras();
        Profile p = extras.getParcelable(PROFILE_OBJECT);


        lv = (ListView) findViewById(R.id.lvMain);
        ad = new ProfileAdapter(this, R.layout.item_layout_custom);
        lv.setAdapter(ad);
        final List<Profile> li = new ArrayList<Profile>();

        li.add(p);
        li.add(p);
        li.add(p);

        ad.updateList(li);

        bBack = (Button) findViewById(R.id.bBack);
        bBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_CANCELED);
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
        lv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Toast.makeText(ListViewActivity.this,"touch",Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(ListViewActivity.this, "delete"+i, Toast.LENGTH_LONG).show();
                AlertDialog.Builder builder = new AlertDialog.Builder(ListViewActivity.this);
                builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(ListViewActivity.this, "delete", Toast.LENGTH_LONG).show();
                        li.remove(i);
                        ad.updateList(li);

                    }
                });
                builder.setNegativeButton("no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(ListViewActivity.this, "keep", Toast.LENGTH_LONG).show();

                    }
                });
                builder.setNeutralButton("may be", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(ListViewActivity.this, "keep", Toast.LENGTH_LONG).show();

                    }
                });
                builder.setTitle("Delete?");
                builder.setIcon(R.drawable.asd2);
                builder.create().show();
                return false;
            }
        });

//        lv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(ListViewActivity.this, "sssss", Toast.LENGTH_SHORT).show();
//            }
//        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(ListViewActivity.this, "on item click", Toast.LENGTH_LONG).show();

            }
        });
    }
}
