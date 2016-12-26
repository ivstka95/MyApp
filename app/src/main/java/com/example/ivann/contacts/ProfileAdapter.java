package com.example.ivann.contacts;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Ivann on 08.12.2016.
 */

public class ProfileAdapter extends ArrayAdapter<Profile> {
    private List<Profile> list;
    private Context context;
    private LayoutInflater inflater;

    public ProfileAdapter(Context context, int resource) {
        super(context, resource);
        list = new ArrayList<>();
        this.context = context;
        inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void updateList(List<Profile> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
        Toast.makeText(getContext(), "updateList", Toast.LENGTH_SHORT).show();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    public List<Profile> getList() {
        return list;
    }

    @Nullable
    @Override
    public Profile getItem(int position) {
        return list.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = inflater.inflate(R.layout.layout_item_custom, parent, false);
        }
        final int pos = position;

        ImageView ivSmallLogo = (ImageView) view.findViewById(R.id.ivSmallLogo);
        ivSmallLogo.setImageURI(Uri.parse(list.get(position).image));

        TextView tvName = (TextView) view.findViewById(R.id.tvName);
        tvName.setText(list.get(position).name);

        TextView tvSurName = (TextView) view.findViewById(R.id.tvSurName);
        tvSurName.setText(list.get(position).surname);

        TextView tvNumber = (TextView) view.findViewById(R.id.tvNumber);
        tvName.setText(list.get(position).number);

        TextView tvEmail = (TextView) view.findViewById(R.id.tvEmail);
        tvEmail.setText(list.get(position).email);

        TextView tvCountry = (TextView) view.findViewById(R.id.tvCountry);
        tvCountry.setText(list.get(position).country);

        TextView tvCity = (TextView) view.findViewById(R.id.tvCity);
        tvCity.setText(list.get(position).city);

        Button bCall = (Button) view.findViewById(R.id.bCall);
       /* bCall.setTag(position);
        bCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Intent.ACTION_DIAL);
                i.setData(Uri.parse("tel:" + list.get(pos).number));
                context.startActivity(i);
            }
        });*/


        return view;
    }

    ;
}