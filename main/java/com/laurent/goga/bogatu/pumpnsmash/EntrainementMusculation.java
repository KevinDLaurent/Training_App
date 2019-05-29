package com.laurent.goga.bogatu.pumpnsmash;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.security.KeyStore;

public class EntrainementMusculation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrainement_musculation);

        //affiche le button precedent seulement si un parent activity est declare dans AndroidManifest.xml
        getSupportActionBar().setHomeButtonEnabled(true);

        Button jambesButton = (Button) findViewById(R.id.jambesButton);
        jambesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotoEntrainementJambes = new Intent(getApplicationContext(),EntrainementJambes.class);
                startActivity(gotoEntrainementJambes);
            }
        });

        Button brasButton = findViewById(R.id.brasButton);
        brasButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotoEntrainementBras = new Intent(getApplicationContext(),EntrainementBras.class);
                startActivity(gotoEntrainementBras);
            }
        });

        Button corpsButton =findViewById(R.id.corpsButton);
        corpsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotoEntrainementCorps = new Intent(getApplicationContext(), EntrainementCorps.class);
                startActivity(gotoEntrainementCorps);
            }
        });
    }
}
