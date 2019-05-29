package com.laurent.goga.bogatu.pumpnsmash;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Chronometer;
import android.content.SharedPreferences;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.Date;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static java.lang.Math.floor;


public class CardioMeter extends AppCompatActivity {

    private String unit;
    private Chronometer chrono;
    private TextView vitesse;
    private TextView status;
    private ProgressBar speedometer;
    private ProgressBar distanceTracker;
    private double speed;
    private Location initialLocation;
    private Location coordinate1 = null;
    private Location coordinate2 = null;
    private static float distanceCovered = 0;
    private Button stop;
    private boolean mRequestingLocationUpdates = true;
    private LocationRequest mLocationRequest;
    private FusedLocationProviderClient mFusedLocationClient;
    private LocationCallback mLocationCallback;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardio_meter);

        // Selection du bon mesure
        SharedPreferences sharepref = getSharedPreferences
                ("com.laurent.goga.bogatu.pumpnsmash", MODE_PRIVATE);
        String systMesure = sharepref.getString("com.laurent.goga.bogatu.pumpnsmash.systMesure", null);
        if (systMesure != null && systMesure.equals("metrique")) {
            unit = "metrique";
        } else if (systMesure != null && systMesure.equals("imperial")) {
            unit = "imperial";
        }

        vitesse = (TextView) findViewById(R.id.vitesse1);
        status = (TextView) findViewById(R.id.status);
        chrono = (Chronometer) findViewById(R.id.chronometer);
        speedometer = (ProgressBar) findViewById(R.id.speedometer);
        distanceTracker = (ProgressBar) findViewById(R.id.distanceProg);
        stop = (Button) findViewById(R.id.stop);
        chrono.start();

        // ref vers le progress bar
        distanceTracker.setProgress(0);

        // API google locations
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mFusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            // Logic to handle location object
                            initialLocation = location;
                            coordinate1 = location;
                            // Si on peut etablir la connection GPS et avoir un premier point...
                            status.setText(getString(R.string.capture));
                        }
                    }
                });

        // Creer la requete de location avec les options desire
        createLocationRequest();

        // Recevoir une nouvelle location pour note widget chaque 2 secondes
        mLocationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) {
                    return;
                }
                // Pour chaque location, on calcule la distance entre les deux points
                // Methode de triangulation
                for (Location location : locationResult.getLocations()) {
                    // Update UI with location data
                    // ...
                    coordinate2 = location;
                    long delta_time = (coordinate2.getTime() - coordinate1.getTime()) / 1000;
                    float distance = coordinate1.distanceTo(coordinate2);
                    if (unit == "metrique") {
                        speed = (distance / delta_time) * 3.6; // m/s to km/h
                        if (!getString(R.string.vit_km, speed).equals("NaN") && speed > 0) {
                            vitesse.setText(getString(R.string.vit_km, speed));}
                    } else if (unit == "imperial") {
                        speed = (distance / delta_time) * 2.23694; // m/s to mi/h
                        if (!getString(R.string.vit_mi, speed).equals("NaN") && speed > 0) {
                            vitesse.setText(getString(R.string.vit_mi, speed));}
                    }
                    // update distance tracker
                    distanceCovered = distanceCovered + distance;
                    float desiredDistance = (float)EntrainementCardio.getSelectDistance() * 1000;
                    float totalDistance = distanceCovered/desiredDistance * 100;
                    int update = (int)floor(totalDistance);
                    status.setText(Float.toString(totalDistance));
                    if (update <= 100) {
                        distanceTracker.setProgress(update);
                    }
                    // Faire un update du progress bar circulaire "speedometer" avec la vitesse
                    // calcule entre deux points
                    updateSpeedometer(speed);

                    coordinate1 = coordinate2;
                }
            }

        };

        // Pour finir l'activite
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRequestingLocationUpdates = false;
                Intent gotoCardioCalories = new Intent(getApplicationContext(), CardioCalories.class);
                startActivity(gotoCardioCalories);
            }
        });


    }

    // Quand on reviens a l'activite, recommencer les mise a jour du GPS
    @Override
    protected void onResume() {
        super.onResume();
        if (mRequestingLocationUpdates && mLocationCallback != null) {
            startLocationUpdates();
        }
    }

    // Quand on quite l'activite, mettre les mise a jour sur pause pour concerver la batterie
    @Override
    protected void onPause() {
        super.onPause();
        if (mLocationCallback != null) {
            stopLocationUpdates();
        }
    }

    private void stopLocationUpdates() {
        mFusedLocationClient.removeLocationUpdates(mLocationCallback);
    }



    private void startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mFusedLocationClient.requestLocationUpdates(mLocationRequest,
                mLocationCallback,
                null /* Looper */);
    }

    // Specification des mise a jour du GPS (interval, priorite)
    @SuppressLint("RestrictedApi")
    protected void createLocationRequest() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(2000);
        mLocationRequest.setFastestInterval(2000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }

    // mise a jour du widget "speedometer" chaque fois qu'on calcule la vitesse entre deux locations du GPS
    public void updateSpeedometer(double speed){
        int desiredSpeed = (int)EntrainementCardio.getSpeed();
        int currentSpeed = (int)speed;
        int lower = 50;
        int upper = 100;
        if (desiredSpeed - currentSpeed > 3) {
            status.setText(getString(R.string.accel));
        }
        else if (desiredSpeed - currentSpeed < -3) {
            status.setText(getString(R.string.ralentir));
        }
        else {
            status.setText(getString(R.string.maintenir));
        }

        double increment = 25.00/desiredSpeed;
        double update = (double)currentSpeed * increment + 50;
        int prog = (int)update;
        if (prog <= 100 && prog >= 50) {
            speedometer.setProgress(prog);
        } else if (prog > 100) {
            speedometer.setProgress(100);
        }
    }

    public float getDistanceCovered() {
        return distanceCovered;
    }
    public void resetDistanceCovered() {distanceCovered = 0; }

}