package com.laurent.goga.bogatu.pumpnsmash;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuPrincipal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

      // Button menuPrincipalButton = findViewById(R.id.menuPrincipalButton);
       // menuPrincipalButton.setVisibility(View.VISIBLE);

        //Entrainement Musculation
        Button entrainementMusculationButton = findViewById(R.id.entrainementMusculationButton);
        entrainementMusculationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotoEntrainementMusculation = new Intent (getApplicationContext(), EntrainementMusculation.class);
                startActivity(gotoEntrainementMusculation);
            }
        });

        //Options
        Button optionsButton = findViewById(R.id.optionsButton);
        optionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotoOptions = new Intent(getApplicationContext(),Options.class);
                startActivity(gotoOptions);
            }
        });

        //Statisitiques
        Button statistiquesButton = findViewById(R.id.statistiquesButton);
        statistiquesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotoStatistiques = new Intent(getApplicationContext(),Statistiques.class);
                startActivity(gotoStatistiques);
            }
        });

        //Entrainement Cardio
        Button entrainementCardioButton = (Button) findViewById(R.id.entrainementCardioButton);
        entrainementCardioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotoEntrainementCardio = new Intent (getApplicationContext(), EntrainementCardio.class);
                startActivity(gotoEntrainementCardio);
            }
        });


    }
}
