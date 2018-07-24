package com.pubgo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class Splash extends Activity {

    FusedLocationProviderClient mFusedLocationClient;
    Geocoder geocoder;
    List<Address> addresses;
    TextView text_view_locale_profile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_splash);

        text_view_locale_profile = (TextView) findViewById(R.id.text_view_locale_profile);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                localeProfile();
            }
        }, 1500);
    }

    @SuppressLint("MissingPermission")
    private void localeProfile() {
        mFusedLocationClient.getLastLocation()
        .addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(final Location location) {
                if (location != null) {
                    geocoder = new Geocoder(Splash.this, Locale.getDefault());
                    try {
                        addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                        String address = addresses.get(0).getAddressLine(0);
                        String logradouro = addresses.get(0).getThoroughfare();
                        String estado = addresses.get(0).getAdminArea();
                        String number = addresses.get(0).getFeatureName();
                        String city = addresses.get(0).getLocality();
                        String state = addresses.get(0).getAdminArea();
                        String country = addresses.get(0).getCountryName();
                        String postalCode = addresses.get(0).getPostalCode();
                        String knownName = addresses.get(0).getFeatureName();

                        text_view_locale_profile.setText(logradouro+", "+number);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else{
                    System.out.println("asdasdasdasdasd");
                }
            }
        });
    }


}
