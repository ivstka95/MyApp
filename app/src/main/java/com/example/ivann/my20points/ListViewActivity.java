package com.example.ivann.my20points;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class ListViewActivity extends AppCompatActivity {

    private static final String PROFILE_OBJECT = "PROFILE_OBJECT";
    public static ListView lv;
    private Button bBack, bClose;
    public static ArrayAdapter ad;
    public static Profile profilesData[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);



        lv = (ListView) findViewById(R.id.lvMain);
        ad = new ProfileAdapter(this, R.layout.item_layout_custom);
        //Intent i = getIntent();
        //Bundle extras = i.getExtras();
        //Profile p = extras.getParcelable(PROFILE_OBJECT);
        //ad.add(p);
        ad.notifyDataSetChanged();

        lv.setAdapter(ad);


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

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
    }
}
