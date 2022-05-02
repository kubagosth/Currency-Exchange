package com.example.currencyexchange;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class RatesAdapter extends ArrayAdapter<Rate> {
    public RatesAdapter(Context context, ArrayList<Rate> rates) {
        super(context, 0,rates);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Rate rate = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_element, parent, false);
        }

        // Lookup view
        ImageView imageFlag = (ImageView) convertView.findViewById(R.id.imageViewFlag);
        TextView flag = (TextView) convertView.findViewById(R.id.textViewCurrency);
        TextView amount = (TextView) convertView.findViewById(R.id.textViewAmount);

        // Populate the data into the template view using the data object
        //SET FLAG UNFINISHED JUST A PLACE HOLDER
        imageFlag.setImageResource(R.drawable.eur);
        flag.setText(rate.getName());
        DecimalFormat df = new DecimalFormat("#.##");
        amount.setText(df.format(rate.getSpotRate()));

        return convertView;
    }

}
