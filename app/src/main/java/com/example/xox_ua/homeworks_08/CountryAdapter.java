package com.example.xox_ua.homeworks_08;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.Random;

public class CountryAdapter extends ArrayAdapter<Country> {
    Context context;
    Country countries[];

    public CountryAdapter(@NonNull Context context, int resource, Country[] countries) {
        super(context, resource, countries);
        this.context = context;
        this.countries = countries;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View rootView = null;
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rootView = inflater.inflate(R.layout.list_item, parent, false);
        } else {
            rootView = convertView;
        }

        ImageView ivFlg = (ImageView) rootView.findViewById(R.id.ivFlg);
        ivFlg.setImageResource(countries[position].flagId);

        TextView tvCountry = (TextView) rootView.findViewById(R.id.tvCountry);
        tvCountry.setText(String.format("%s %s", context.getString(R.string.tv_country), countries[position].countryName));

        TextView tvCapital = (TextView) rootView.findViewById(R.id.tvCapital);
        tvCapital.setText(String.format("%s %s", context.getString(R.string.tv_city), countries[position].capitalName));

        RatingBar ratingBar = (RatingBar) rootView.findViewById(R.id.ratingBar);
        ratingBar.setRating(countries[position].ratingBar);

        return rootView;
    }
}
