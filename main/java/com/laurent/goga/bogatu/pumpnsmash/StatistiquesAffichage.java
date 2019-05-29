package com.laurent.goga.bogatu.pumpnsmash;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class StatistiquesAffichage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistiques_affichage);

        //affiche le button precedent seulement si un parent activity est declare dans AndroidManifest.xml
        getSupportActionBar().setHomeButtonEnabled(true);


        SharedPreferences sharepref = getSharedPreferences
                ("com.laurent.goga.bogatu.pumpnsmash", MODE_PRIVATE);

        //les boutons
        String systMesure = sharepref.getString("com.laurent.goga.bogatu.pumpnsmash.systMesure","errpr");
        String typeStatsAffichage = getIntent().getExtras().getString("com.laurent.goga.bogatu.pumpnsmash.typeStatsAffichage");
        TextView brasStatistiquesTextView = findViewById(R.id.brasStatistiquesTextView);
        TextView corpsStatistiquesTextView = findViewById(R.id.corpsStatistiquesTextView);
        TextView jambesStatistiquesTextView = findViewById(R.id.jambesStatistiquesTextView);
        TextView nbExercicesCardioTextView = findViewById(R.id.nbExercicesCardioTextView);
        TextView distanceTextView = findViewById(R.id.distanceTextView);
        TextView caloriesTextView = findViewById(R.id.caloriesTextView);
        TextView periodeStatistiques = findViewById(R.id.periodeStatistiquesTextView);

        //affiche stats semaine
        if(typeStatsAffichage.equals("semaine")){
            //get valeurs
            int bras = sharepref.getInt("com.laurent.goga.bogatu.pumpnsmash.exercicesMusculationSemaineBras",0);
            int corps = sharepref.getInt("com.laurent.goga.bogatu.pumpnsmash.exercicesMusculationSemaineCorps",0);
            int jambes = sharepref.getInt("com.laurent.goga.bogatu.pumpnsmash.exercicesMusculationSemaineJambes",0);
            int exerciesCarido = sharepref.getInt("com.laurent.goga.bogatu.pumpnsmash.exercicesCardioSemaine",0);
            int calories = sharepref.getInt("com.laurent.goga.bogatu.pumpnsmash.caloriesSemaine", 0);
            int distance = sharepref.getInt("com.laurent.goga.bogatu.pumpnsmash.distanceSemaine", 0);
            String mesure = getString(R.string.km);
            //affiche valeur
            brasStatistiquesTextView.setText(getString(R.string.bras)+ bras);
            corpsStatistiquesTextView.setText(getString(R.string.corps)+ corps);
            jambesStatistiquesTextView.setText(getString(R.string.jambes)+ jambes);
            nbExercicesCardioTextView.setText(getString(R.string.nbExerciesCardio)+ exerciesCarido);
            caloriesTextView.setText(getString(R.string.calories)+ calories);
            if(systMesure.equals("imperial")){
                distance = (int)Math.round(distance / 1.60933);
                mesure = getString(R.string.mi);
            }
            distanceTextView.setText(getString(R.string.distance) + distance + " " + mesure);
            periodeStatistiques.setText(getString(R.string.periodeSemaine));
        }
        //affiche stats mois
        else if(typeStatsAffichage.equals("mois")){
            int bras = sharepref.getInt("com.laurent.goga.bogatu.pumpnsmash.exercicesMusculationMoisBras",0);
            int corps = sharepref.getInt("com.laurent.goga.bogatu.pumpnsmash.exercicesMusculationMoisCorps",0);
            int jambes = sharepref.getInt("com.laurent.goga.bogatu.pumpnsmash.exercicesMusculationMoisJambes",0);
            int exerciesCarido = sharepref.getInt("com.laurent.goga.bogatu.pumpnsmash.exercicesCardioMois",0);
            int calories = sharepref.getInt("com.laurent.goga.bogatu.pumpnsmash.caloriesMois", 0);
            int distance = sharepref.getInt("com.laurent.goga.bogatu.pumpnsmash.distanceMois", 0);
            String mesure = getString(R.string.km);
            //affiche valeur
            brasStatistiquesTextView.setText(getString(R.string.bras)+ bras);
            corpsStatistiquesTextView.setText(getString(R.string.corps)+ corps);
            jambesStatistiquesTextView.setText(getString(R.string.jambes)+ jambes);
            nbExercicesCardioTextView.setText(getString(R.string.nbExerciesCardio)+ exerciesCarido);
            caloriesTextView.setText(getString(R.string.calories)+ calories);
            if(systMesure.equals("imperial")){
                distance = (int)Math.round(distance / 1.60933);
                mesure = getString(R.string.mi);
            }
            distanceTextView.setText(getString(R.string.distance) + distance + " " + mesure);
            periodeStatistiques.setText(getString(R.string.periodeMois));
        }
        //affiche stats annee
        else if(typeStatsAffichage.equals("annee")){
            int bras = sharepref.getInt("com.laurent.goga.bogatu.pumpnsmash.exercicesMusculationAnneeBras",0);
            int corps = sharepref.getInt("com.laurent.goga.bogatu.pumpnsmash.exercicesMusculationAnneeCorps",0);
            int jambes = sharepref.getInt("com.laurent.goga.bogatu.pumpnsmash.exercicesMusculationAnneeJambes",0);
            int exerciesCarido = sharepref.getInt("com.laurent.goga.bogatu.pumpnsmash.exercicesCardioAnnee",0);
            int calories = sharepref.getInt("com.laurent.goga.bogatu.pumpnsmash.caloriesAnnee", 0);
            int distance = sharepref.getInt("com.laurent.goga.bogatu.pumpnsmash.distanceAnnee", 0);
            String mesure = getString(R.string.km);
            //affiche valeur
            brasStatistiquesTextView.setText(getString(R.string.bras)+ bras);
            corpsStatistiquesTextView.setText(getString(R.string.corps)+ corps);
            jambesStatistiquesTextView.setText(getString(R.string.jambes)+ jambes);
            nbExercicesCardioTextView.setText(getString(R.string.nbExerciesCardio)+ exerciesCarido);
            caloriesTextView.setText(getString(R.string.calories)+ calories);
            if(systMesure.equals("imperial")){
                distance = (int)Math.round(distance / 1.60933);
                mesure = getString(R.string.mi);
            }
            distanceTextView.setText(getString(R.string.distance) + distance + " " + mesure);
            periodeStatistiques.setText(getString(R.string.periodeAnnee));
        }
        //affiche stats total
        else{
            int bras = sharepref.getInt("com.laurent.goga.bogatu.pumpnsmash.exercicesMusculationTotalBras",0);
            int corps = sharepref.getInt("com.laurent.goga.bogatu.pumpnsmash.exercicesMusculationTotalCorps",0);
            int jambes = sharepref.getInt("com.laurent.goga.bogatu.pumpnsmash.exercicesMusculationTotalJambes",0);
            int exerciesCarido = sharepref.getInt("com.laurent.goga.bogatu.pumpnsmash.exercicesCardioTotal",0);
            int calories = sharepref.getInt("com.laurent.goga.bogatu.pumpnsmash.caloriesTotal", 0);
            int distance = sharepref.getInt("com.laurent.goga.bogatu.pumpnsmash.distanceTotal", 0);
            String mesure = getString(R.string.km);
            //affiche valeur
            brasStatistiquesTextView.setText(getString(R.string.bras)+ bras);
            corpsStatistiquesTextView.setText(getString(R.string.corps)+ corps);
            jambesStatistiquesTextView.setText(getString(R.string.jambes)+ jambes);
            nbExercicesCardioTextView.setText(getString(R.string.nbExerciesCardio)+ exerciesCarido);
            caloriesTextView.setText(getString(R.string.calories)+ calories);
            if(systMesure.equals("imperial")){
                distance = (int)Math.round(distance / 1.60933);
                mesure = getString(R.string.mi);
            }
            distanceTextView.setText(getString(R.string.distance) + distance + " " + mesure);
            periodeStatistiques.setText(getString(R.string.periodeTotal));
        }
    }
}
