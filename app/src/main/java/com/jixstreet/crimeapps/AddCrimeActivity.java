package com.jixstreet.crimeapps;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.gms.maps.model.LatLng;
import com.jixstreet.crimeapps.models.Crime;
import com.jixstreet.crimeapps.utils.CommonConstants;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by M Agung Satrio - agung.satrio@inmagine.com on 12/4/2015.
 */
public class AddCrimeActivity extends Activity {
    private EditText titleET, descriptionET, locationET, addressET;
    private Button saveBtn;
    private Spinner crimeTypeSP;
    private LatLng crimeLatLng;
    private String city;
    private List<String> crimeList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUI();
        setCallBack();
    }

    private void initUI() {
        setContentView(R.layout.input_crime_layout);

        titleET = (EditText) findViewById(R.id.title_et);
        descriptionET = (EditText) findViewById(R.id.description_et);
        locationET = (EditText) findViewById(R.id.location_et);
        addressET = (EditText) findViewById(R.id.address_et);
        saveBtn = (Button) findViewById(R.id.save_btn);
        crimeTypeSP = (Spinner) findViewById(R.id.type_sp);

        crimeList = Seeder.getCrimeType();

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, Seeder.getCrimeType());
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        crimeTypeSP.setAdapter(dataAdapter);
    }

    private void setCallBack() {
        crimeTypeSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        locationET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(AddCrimeActivity.this, GetLocationActivity.class), CommonConstants.REQUEST_LOCATION_CODE);
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateForm()) {
                    saveCrime();
                    Intent intent = new Intent();
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });
    }

    private void saveCrime() {
        Crime crime = new Crime();
        crime.setTitle(titleET.getText().toString());
        crime.setAddress(addressET.getText().toString());
        crime.setLocation(locationET.getText().toString());
        crime.setDescription(descriptionET.getText().toString());
        crime.setType(crimeList.get(crimeTypeSP.getSelectedItemPosition()));
        crime.setLatitude(crimeLatLng.latitude);
        crime.setLongitude(crimeLatLng.longitude);

        try {
            crime.create();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean validateForm() {
        int totalValidated = 0;

        if (titleET.getText().toString().isEmpty()) {
            titleET.setError(getString(R.string.error_field_text));
        } else {
            totalValidated++;
        }

        if (addressET.getText().toString().isEmpty()) {
            addressET.setError(getString(R.string.error_field_text));
        } else {
            totalValidated++;
        }

        if (locationET.getText().toString().isEmpty()) {
            locationET.setError(getString(R.string.error_field_text));
        } else {
            totalValidated++;
        }

        if (descriptionET.getText().toString().isEmpty()) {
            descriptionET.setError(getString(R.string.error_field_text));
        } else {
            totalValidated++;
        }

        return totalValidated == 4;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == CommonConstants.REQUEST_LOCATION_CODE) {
            crimeLatLng = data.getParcelableExtra(CommonConstants.LATLNG);
            city = data.getStringExtra(CommonConstants.CITY);
            String address = data.getStringExtra(CommonConstants.ADDRESS);

            locationET.setText(address);
        }
    }
}
