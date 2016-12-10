package com.example.ivann.my20points;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

import static com.example.ivann.my20points.ListViewActivity.profilesData;

/**
 * Created by Ivann on 08.12.2016.
 */

public class ProfileAdapter extends ArrayAdapter<Profile> {
    Context context;
    //Profile profiles[];

    public ProfileAdapter(Context context, int resource ) {
        super(context, resource);
        this.context = context;
        //this.profiles = profiles;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View rootView = null;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rootView = inflater.inflate(R.layout.item_layout_custom, parent, false);
        } else {
            rootView = convertView;
        }

        ImageView ivSmallLogo = (ImageView) rootView.findViewById(R.id.ivSmallLogo);
        ivSmallLogo.setImageURI(profilesData[position].image);

        TextView tvName = (TextView) rootView.findViewById(R.id.tvName);
        tvName.setText(profilesData[position].name);

        TextView tvSurName = (TextView) rootView.findViewById(R.id.tvSurName);
        tvSurName.setText(profilesData[position].surname);

        TextView tvNumber = (TextView) rootView.findViewById(R.id.tvNumber);
        tvName.setText(profilesData[position].number);

        TextView tvEmail = (TextView) rootView.findViewById(R.id.tvEmail);
        tvEmail.setText(profilesData[position].email);

        TextView tvCountry = (TextView) rootView.findViewById(R.id.tvCountry);
        tvCountry.setText(profilesData[position].country);

        TextView tvCity = (TextView) rootView.findViewById(R.id.tvCity);
        tvCity.setText(profilesData[position].city);

        Button bCall = (Button) rootView.findViewById(R.id.bCall);
        bCall.setTag(position);
        bCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Intent.ACTION_DIAL);
                i.setData(Uri.parse("tel:" + profilesData[position].number));
                context.startActivity(i);
            }
        });
        return rootView;

    }
}
