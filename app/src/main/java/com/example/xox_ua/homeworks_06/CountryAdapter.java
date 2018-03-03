package com.example.xox_ua.homeworks_06;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

        ImageView ivFlag = (ImageView) rootView.findViewById(R.id.ivFlag);
        ivFlag.setImageResource(countries[position].flagId);

        TextView tvCountryName = (TextView) rootView.findViewById(R.id.tvCountry);
        tvCountryName.setText(String.format("%s %s", context.getString(R.string.txtCountry), countries[position].countryName));

        TextView tvCapitalName = (TextView) rootView.findViewById(R.id.tvCapital);
        tvCapitalName.setText(String.format("%s %s", context.getString(R.string.txtCapital), countries[position].capitalName));

        Button btnWiki = (Button) rootView.findViewById(R.id.bWiki);

        btnWiki.setTag(position);

        btnWiki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int positionOfItem = (int) v.getTag();
                Intent intent = new Intent(Intent.ACTION_VIEW);
                Uri uri = Uri.parse("https://ru.wikipedia.org/wiki/" + countries[positionOfItem].countryName);
                intent.setData(uri);
                context.startActivity(intent);
            }
        });

        return rootView;
    }
}
