package com.envyandroid.listdialogexample;

import android.app.ListActivity;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

public class CountrycodeActivity extends ListActivity {

    public static String RESULT_CONTRYCODE = "countrycode";
    public String[] countrynames, countrycodes;
    private TypedArray imgs;
    private List<Country> countryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        populateCountryList();
        ArrayAdapter<Country> adapter = new CountryListArrayAdapter(this, countryList);
        setListAdapter(adapter);
        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Country c = countryList.get(position);
                Intent returnIntent = new Intent();
                returnIntent.putExtra(RESULT_CONTRYCODE, c.getCode());
                setResult(RESULT_OK, returnIntent);
                imgs.recycle(); //recycle images
                finish();
            }
        });
    }

    private void populateCountryList() {
        countryList = new ArrayList<Country>();
        countrynames = getResources().getStringArray(R.array.country_names);
        countrycodes = getResources().getStringArray(R.array.country_codes);
        imgs = getResources().obtainTypedArray(R.array.country_flags);
        for(int i = 0; i < countrycodes.length; i++){
            countryList.add(new Country(countrynames[i], countrycodes[i], imgs.getDrawable(i)));
        }
    }

    public class Country {
        private String name;
        private String code;
        private Drawable flag;
        public Country(String name, String code, Drawable flag){
            this.name = name;
            this.code = code;
            this.flag = flag;
        }
        public String getName() {
            return name;
        }
        public Drawable getFlag() {
            return flag;
        }
        public String getCode() {
            return code;
        }
    }
}