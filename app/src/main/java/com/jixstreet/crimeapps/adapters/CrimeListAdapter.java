package com.jixstreet.crimeapps.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jixstreet.crimeapps.R;
import com.jixstreet.crimeapps.models.Crime;
import com.jixstreet.crimeapps.utils.Utility;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by M Agung Satrio - agung.satrio@inmagine.com on 12/4/2015.
 */
public class CrimeListAdapter extends BaseAdapter {
    private Context context;
    private List<Crime> crimeList = new ArrayList<>();

    public CrimeListAdapter(Context context, List<Crime> crimeList) {
        this.context = context;
        this.crimeList = crimeList;
    }

    @Override
    public int getCount() {
        return crimeList.size();
    }

    @Override
    public Object getItem(int position) {
        return crimeList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewHolder holder;

        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.crime_lv_item_layout, parent, false);
            holder = new ViewHolder();

            holder.crimeIV = (ImageView) convertView.findViewById(R.id.crime_iv);
            holder.crimeTitleTV = (TextView) convertView.findViewById(R.id.crime_title_tv);
            holder.crimeDescriptionTV = (TextView) convertView.findViewById(R.id.crime_description_tv);
            holder.crimeLocationTV = (TextView) convertView.findViewById(R.id.crime_location_tv);

            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();

        holder.crimeIV.setImageResource(Utility.getImageCrimeType(crimeList.get(position).getType()));
        holder.crimeTitleTV.setText(crimeList.get(position).getTitle());
        holder.crimeDescriptionTV.setText(crimeList.get(position).getDescription());
        holder.crimeLocationTV.setText(crimeList.get(position).getLocation());

        return convertView;
    }

    public void updateContent(List<Crime> crimeList) {
        this.crimeList = crimeList;
        notifyDataSetChanged();
    }

    class ViewHolder {
        TextView crimeTitleTV, crimeDescriptionTV, crimeLocationTV;
        ImageView crimeIV;
    }
}
