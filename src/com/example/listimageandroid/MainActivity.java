package com.example.listimageandroid;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.GridView;
import android.widget.ListView;

public class MainActivity extends Activity {

	private List<Country> countryList = new ArrayList<Country>();
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gridview);
        
        // Parse raw/countries.xml
        CountryParser cp = new CountryParser();
        InputStream inputStream = getResources().openRawResource(R.raw.countries);
        cp.parse(inputStream);
        
        countryList = cp.getList();
        
        // Create a customized ArrayAdapter
        CountryArrayAdapter adapter = new CountryArrayAdapter(getApplicationContext(), 
        		R.layout.country_listitem, countryList);
        
        // Reference to listView holder
        //ListView lv = (ListView) this.findViewById(R.id.countryLV);
        
        //lv.setAdapter(adapter);
        
        GridView gv = (GridView) findViewById(R.id.countryGV);
        gv.setAdapter(adapter);
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
