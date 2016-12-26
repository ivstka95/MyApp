package com.example.ivann.contacts;


import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {
    private Profile profile;

    public ProfileFragment() {
        // Required empty public constructor
    }


    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        profile = bundle.getParcelable("KEY");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        ImageView iProfilePicture =  (ImageView) view.findViewById(R.id.iProfilePicture);
        iProfilePicture.setImageURI(Uri.parse(profile.image));
        TextView tvName = (TextView) view.findViewById(R.id.tvName);
        tvName.setText(profile.name);
        TextView tvSurName = (TextView) view.findViewById(R.id.tvSurName);
        tvSurName.setText(profile.surname);
        TextView tvNumber = (TextView) view.findViewById(R.id.tvPhoneNumber);
        tvNumber.setText(profile.number);
        TextView tvEmail = (TextView) view.findViewById(R.id.tvEmail);
        tvEmail.setText(profile.email);
        TextView tvCountry = (TextView) view.findViewById(R.id.tvCountry);
        tvCountry.setText(profile.country);
        TextView tvCity = (TextView) view.findViewById(R.id.tvCity);
        tvCity.setText(profile.city);
        TextView tvNotes = (TextView) view.findViewById(R.id.tvNotes);
        tvNotes.setText(profile.notes);
        Button button = (Button) view.findViewById(R.id.bCall);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "btnClick", Toast.LENGTH_SHORT).show();

            }
        });
        return view;
    }

    public static ProfileFragment newInstance(Profile profile) {
        ProfileFragment profileFragment = new ProfileFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("KEY", profile);
        profileFragment.setArguments(bundle);
        return profileFragment;
    }

}
