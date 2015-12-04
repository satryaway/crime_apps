package com.jixstreet.crimeapps;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.jixstreet.crimeapps.adapters.CrimeListAdapter;
import com.jixstreet.crimeapps.models.Crime;
import com.jixstreet.crimeapps.utils.CommonConstants;
import com.jixstreet.crimeapps.utils.Utility;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity {

    private GoogleMap googleMap;
    private ImageView addCrimeIV;
    private ListView crimeLV;
    private CrimeListAdapter mAdapter;
    private List<Crime> crimeList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUI();
        setCallBack();
    }

    private void initUI() {
        setContentView(R.layout.activity_maps);

        crimeList = getCrimeList();

        initMap();
        addCrimeIV = (ImageView) findViewById(R.id.add_crime_iv);

        crimeLV = (ListView) findViewById(R.id.crime_lv);
        mAdapter = new CrimeListAdapter(this, crimeList);
        crimeLV.setAdapter(mAdapter);

        setMarkers();
    }

    private void initMap() {
        try {
            if (null == googleMap) {
                googleMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();

                /**
                 * If the map is still null after attempted initialisation,
                 * show an error to the user
                 */
                if (null == googleMap)
                    Toast.makeText(getApplicationContext(), "Error creating map", Toast.LENGTH_SHORT).show();
            }
        } catch (NullPointerException exception) {
            Log.e("mapApp", exception.toString());
        }
    }

    private void setCallBack() {
        addCrimeIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MapsActivity.this, AddCrimeActivity.class);
                startActivityForResult(intent, CommonConstants.ADD_CRIME_CODE);
            }
        });

        crimeLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LatLng latLng = new LatLng(crimeList.get(position).getLatitude(), crimeList.get(position).getLongitude());
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 13));
            }
        });
    }

    private void addMarker(Crime crime) {
        if (null != googleMap) {
            LatLng latLng = new LatLng(crime.getLatitude(), crime.getLongitude());

            googleMap.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title(crime.getTitle())
                            .draggable(true)
                            .icon(BitmapDescriptorFactory.fromResource(Utility.getImageCrimeType(crime.getType())))
            );
        }
    }

    private void setMarkers() {
        for (int i = 0; i < crimeList.size(); i++) {
            addMarker(crimeList.get(i));
            LatLng latLng = new LatLng(crimeList.get(i).getLatitude(), crimeList.get(i).getLongitude());
            if (i == 0)
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 13));
        }
    }

    private List<Crime> getCrimeList() {
        List<Crime> crimeList = new ArrayList<>();

        DatabaseHelper helper = CrimeApplication.getInstance().getDBHelperInstance();
        if (helper.getAllCrimes().size() > 0) {
            crimeList = helper.getAllCrimes();
        }
        return crimeList;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == CommonConstants.ADD_CRIME_CODE) {
            crimeList = getCrimeList();
            mAdapter.updateContent(crimeList);
        }
    }
}
