package com.example.listimageandroid;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CountryArrayAdapter extends ArrayAdapter<Country> {
	private static final String tag = "CountryArrayAdapter";
	private static final String ASSETS_DIR = "images/";
	private Context context;
	
	private ImageView countryIcon;
	private TextView countryName;
	private TextView countryAbbreviation;
	private List<Country> countries = new ArrayList<Country>();
	
	public CountryArrayAdapter(Context context, int textViewResourceId, List<Country> objects) {
		super(context, textViewResourceId, objects);
		this.context = context;
		countries = objects;
	}
	
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		
		if(row == null) {
			LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			row = inflater.inflate(R.layout.country_listitem, parent, false);
		}
		
		Country country = getItem(position);
		
		countryIcon = (ImageView) row.findViewById(R.id.country_icon);
		countryName = (TextView) row.findViewById(R.id.country_name);
		countryAbbreviation = (TextView) row.findViewById(R.id.country_abbrev);
		
		countryName.setText(country.name);
		
		String imgFilePath = ASSETS_DIR + country.resourceId;
		try {
			Bitmap bitmap = BitmapFactory.decodeStream(context.getResources().getAssets().open(imgFilePath));
			countryIcon.setImageBitmap(bitmap);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		countryAbbreviation.setText(country.abbreviation);
		return row;
	}
	
	@Override
	public int getCount() { return countries.size(); }
	
	@Override
	public Country getItem(int index) { return countries.get(index); }
}
