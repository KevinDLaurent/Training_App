package com.laurent.goga.bogatu.pumpnsmash;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Button;

import java.security.KeyStore;
import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class EntrainementCardio extends AppCompatActivity{

    private static double speed;
    private static int selectDistance = 0;
    private static int selectTemps = 0;
    private String unit;
    Spinner distanceSpinner;
    Spinner tempsSpinner;
    TextView vitesse;
    Button calc;
    Button commencer;
    TextView dist;
    TextView metric;

    ArrayAdapter<Integer> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrainement_cardio);

        // Donner permission pour acceder au GPS
        requestPermission();

        distanceSpinner = (Spinner)findViewById(R.id.distanceSpinner);
        tempsSpinner = (Spinner)findViewById(R.id.tempsSpinner);
        vitesse = (TextView)findViewById(R.id.textVitesse);
        calc = (Button)findViewById(R.id.calc);
        commencer = (Button)findViewById(R.id.Commencer);
        dist = (TextView)findViewById(R.id.distText);

        metric = (TextView)findViewById(R.id.metric);

        // Choisir le bon mesure
        SharedPreferences sharepref = getSharedPreferences
                ("com.laurent.goga.bogatu.pumpnsmash", MODE_PRIVATE);
        String systMesure = sharepref.getString("com.laurent.goga.bogatu.pumpnsmash.systMesure", null);
        if (systMesure != null && systMesure.equals("metrique")) {
            unit = "metrique";
            dist.setText(getString(R.string.dist_metrique));
            metric.setText(getString(R.string.kmh));
        } else if (systMesure != null && systMesure.equals("imperial")) {
            unit = "imperial";
            dist.setText(getString(R.string.dist_imperial));
            metric.setText(getString(R.string.mih));
        }

        // Selection de la distance
        List distance = new ArrayList<Integer>();
        for (int i=0; i<50; i++)
            distance.add(i);

        ArrayAdapter<Integer> spinnerArrayAdapter = new ArrayAdapter<Integer>(
                this, android.R.layout.simple_spinner_item, distance);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        distanceSpinner = (Spinner) findViewById(R.id.distanceSpinner);
        distanceSpinner.setAdapter(spinnerArrayAdapter);

        distanceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectDistance = (int) distanceSpinner.getItemAtPosition(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // Selection du temps
        List temps = new ArrayList<Integer>();
        for (int i=0; i<240; i++)
            temps.add(i);

        ArrayAdapter<Integer> spinnerArrayAdapter2 = new ArrayAdapter<Integer>(
                this, android.R.layout.simple_spinner_item, temps);
        spinnerArrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        tempsSpinner = (Spinner) findViewById(R.id.tempsSpinner);
        tempsSpinner.setAdapter(spinnerArrayAdapter2);

        tempsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectTemps = (int) tempsSpinner.getItemAtPosition(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // on click - calculer la vitesse desire
        calc.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (selectDistance != 0 && selectTemps != 0) {
                    speed = ((double)selectDistance / (double)selectTemps) * 60;
                    vitesse.setTextSize(36);
                    vitesse.setText(getString(R.string.vitesse, speed));
                }
            }
        });

        // on click - proceder vers l'activite cardio meter
        commencer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String v = vitesse.getText().toString();
                if (v != "") {
                    Intent gotoCardioMeter = new Intent (getApplicationContext(), CardioMeter.class);
                    startActivity(gotoCardioMeter);
                }
            }
        });

    }

    private void requestPermission(){
        ActivityCompat.requestPermissions(this, new String[]{ACCESS_FINE_LOCATION}, 1);
    }

    public static int getSelectTemps() {
        return selectTemps;
    }
    public static int getSelectDistance() {
        return selectDistance;
    }
    public static double getSpeed() {
        return speed;
    }
}