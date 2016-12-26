package com.example.ivann.contacts;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Ivann on 08.12.2016.
 */

public class Profile implements Parcelable {
    String image;

    String name, surname, number, email, country, city, notes;

    public Profile(String image, String name, String surname, String email, String number, String country, String city, String notes) {
        this.image = image;
        this.name = name;
        this.surname = surname;
        this.number = number;
        this.email = email;
        this.country = country;
        this.city = city;
        this.notes = notes;
    }



    protected Profile(Parcel in) {
        image = in.readString();
        name = in.readString();
        surname = in.readString();
        number = in.readString();
        email = in.readString();
        country = in.readString();
        city = in.readString();
        notes = in.readString();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(image);
        parcel.writeString(name);
        parcel.writeString(surname);
        parcel.writeString(number);
        parcel.writeString(email);
        parcel.writeString(country);
        parcel.writeString(city);
        parcel.writeString(notes);
    }


    public static final Creator<Profile> CREATOR = new Creator<Profile>() {
        @Override
        public Profile createFromParcel(Parcel in) {
            return new Profile(in);
        }

        @Override
        public Profile[] newArray(int size) {
            return new Profile[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }


}
