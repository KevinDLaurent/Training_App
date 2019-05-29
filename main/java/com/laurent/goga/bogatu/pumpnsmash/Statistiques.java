package com.laurent.goga.bogatu.pumpnsmash;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Statistiques extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistiques);

       final Context context = this;

        //affiche le button precedent seulement si un parent activity est declare dans AndroidManifest.xml
        getSupportActionBar().setHomeButtonEnabled(true);


        Button semaineButton = findViewById(R.id.semaineButton);
        semaineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                metAJourStatistiques(context);
                Intent gotoStatistiquesAffichage = new Intent(getApplicationContext(),StatistiquesAffichage.class);
                gotoStatistiquesAffichage.putExtra("com.laurent.goga.bogatu.pumpnsmash.typeStatsAffichage","semaine");
                startActivity(gotoStatistiquesAffichage);
            }
        });

        Button moisButton = findViewById(R.id.moisButton);
        moisButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotoStatistiquesAffichage = new Intent(getApplicationContext(),StatistiquesAffichage.class);
                gotoStatistiquesAffichage.putExtra("com.laurent.goga.bogatu.pumpnsmash.typeStatsAffichage","mois");
                startActivity(gotoStatistiquesAffichage);
            }
        });

        Button anneeButton = findViewById(R.id.anneeButton);
        anneeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotoStatistiquesAffichage = new Intent(getApplicationContext(),StatistiquesAffichage.class);
                gotoStatistiquesAffichage.putExtra("com.laurent.goga.bogatu.pumpnsmash.typeStatsAffichage","annee");
                startActivity(gotoStatistiquesAffichage);
            }
        });

        Button totalButton = findViewById(R.id.totalButton);
        totalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotoStatistiquesAffichage = new Intent(getApplicationContext(),StatistiquesAffichage.class);
                gotoStatistiquesAffichage.putExtra("com.laurent.goga.bogatu.pumpnsmash.typeStatsAffichage","total");
                startActivity(gotoStatistiquesAffichage);
            }
        });
    }
    //reset les stats a la fin de la semaine, du mois et de l annee
    protected void metAJourStatistiques(Context context){
        SharedPreferences sharepref = context.getSharedPreferences
                ("com.laurent.goga.bogatu.pumpnsmash", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharepref.edit();
        int lastWeek = sharepref.getInt("com.laurent.goga.bogatu.pumpnsmash.derniereSemaine",99);
        int lastMonth = sharepref.getInt("com.laurent.goga.bogatu.pumpnsmash.dernierMois", 99);
        int lastYear = sharepref.getInt("com.laurent.goga.bogatu.pumpnsmash.derniereAnnee",99);
        Calendar calendar = new GregorianCalendar();
        int currentWeek = calendar.get(Calendar.WEEK_OF_YEAR);
        int currentMonth= calendar.get(Calendar.MONTH);
        int currentYear = calendar.get(Calendar.YEAR);
        //reset semaine
        if(currentWeek != lastWeek){
            editor.putInt("com.laurent.goga.bogatu.pumpnsmash.derniereSemaine",currentWeek);
            editor.putInt("com.laurent.goga.bogatu.pumpnsmash.exercicesMusculationSemaineBras",0);
            editor.putInt("com.laurent.goga.bogatu.pumpnsmash.exercicesMusculationSemaineCorps",0);
            editor.putInt("com.laurent.goga.bogatu.pumpnsmash.exercicesMusculationSemaineJambes",0);
            editor.putInt("com.laurent.goga.bogatu.pumpnsmash.exercicesCardioSemaine",0);
            editor.putInt("com.laurent.goga.bogatu.pumpnsmash.caloriesSemaine",0);
            editor.putInt("com.laurent.goga.bogatu.pumpnsmash.distanceSemaine",0);
            editor.apply();
        }
        //reset mois
        if(currentMonth != lastMonth){
            editor.putInt("com.laurent.goga.bogatu.pumpnsmash.dernierMois", currentMonth);
            editor.putInt("com.laurent.goga.bogatu.pumpnsmash.exercicesMusculationMoisBras",0);
            editor.putInt("com.laurent.goga.bogatu.pumpnsmash.exercicesMusculationMoisCorps",0);
            editor.putInt("com.laurent.goga.bogatu.pumpnsmash.exercicesMusculationMoisJambes",0);
            editor.putInt("com.laurent.goga.bogatu.pumpnsmash.exercicesCardioMois",0);
            editor.putInt("com.laurent.goga.bogatu.pumpnsmash.caloriesMois",0);
            editor.putInt("com.laurent.goga.bogatu.pumpnsmash.distanceMois",0);
            editor.apply();
        }
        //reset year
        if(currentYear != lastYear){
            editor.putInt("com.laurent.goga.bogatu.pumpnsmash.derniereAnnee", currentYear);
            editor.putInt("com.laurent.goga.bogatu.pumpnsmash.exercicesMusculationAnneeBras",0);
            editor.putInt("com.laurent.goga.bogatu.pumpnsmash.exercicesMusculationAnneeCorps",0);
            editor.putInt("com.laurent.goga.bogatu.pumpnsmash.exercicesMusculationAnneeJambes",0);
            editor.putInt("com.laurent.goga.bogatu.pumpnsmash.exercicesCardioAnnee",0);
            editor.putInt("com.laurent.goga.bogatu.pumpnsmash.caloriesAnnee",0);
            editor.putInt("com.laurent.goga.bogatu.pumpnsmash.distanceAnnee",0);
            editor.apply();
        }



    }

    //ajoute les differents stats
    //la distance est prise en km
    protected  void ajouteExercice(Boolean exerciceMusculation, int typeExerciceMusculation, int distance, int calories, Context context){
        metAJourStatistiques(context);
        SharedPreferences sharepref = context.getSharedPreferences
                ("com.laurent.goga.bogatu.pumpnsmash", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharepref.edit();
        //set partie musuclation
        if(exerciceMusculation) {
            //set exercices bras
            if(typeExerciceMusculation == 1){
                int exercicesMusculationSemaine = sharepref.getInt("com.laurent.goga.bogatu.pumpnsmash.exercicesMusculationSemaineBras", 0);
                int exercicesMusculationMois = sharepref.getInt("com.laurent.goga.bogatu.pumpnsmash.exercicesMusculationMoisBras", 0);
                int exercicesMusculationAnnee = sharepref.getInt("com.laurent.goga.bogatu.pumpnsmash.exercicesMusculationAnneeBras", 0);
                int exercicesMusculationTotal = sharepref.getInt("com.laurent.goga.bogatu.pumpnsmash.exercicesMusculationTotalBras", 0);
                editor.putInt("com.laurent.goga.bogatu.pumpnsmash.exercicesMusculationSemaineBras", ++exercicesMusculationSemaine);
                editor.putInt("com.laurent.goga.bogatu.pumpnsmash.exercicesMusculationMoisBras", ++exercicesMusculationMois);
                editor.putInt("com.laurent.goga.bogatu.pumpnsmash.exercicesMusculationAnneeBras", ++exercicesMusculationAnnee);
                editor.putInt("com.laurent.goga.bogatu.pumpnsmash.exercicesMusculationTotalBras", ++exercicesMusculationTotal);
                editor.apply();
            }
            //set exercies corps
            else if(typeExerciceMusculation == 2){
                int exercicesMusculationSemaine = sharepref.getInt("com.laurent.goga.bogatu.pumpnsmash.exercicesMusculationSemaineCorps", 0);
                int exercicesMusculationMois = sharepref.getInt("com.laurent.goga.bogatu.pumpnsmash.exercicesMusculationMoisCorps", 0);
                int exercicesMusculationAnnee = sharepref.getInt("com.laurent.goga.bogatu.pumpnsmash.exercicesMusculationAnneeCorps", 0);
                int exercicesMusculationTotal = sharepref.getInt("com.laurent.goga.bogatu.pumpnsmash.exercicesMusculationTotalCorps", 0);
                editor.putInt("com.laurent.goga.bogatu.pumpnsmash.exercicesMusculationSemaineCorps", ++exercicesMusculationSemaine);
                editor.putInt("com.laurent.goga.bogatu.pumpnsmash.exercicesMusculationMoisCorps", ++exercicesMusculationMois);
                editor.putInt("com.laurent.goga.bogatu.pumpnsmash.exercicesMusculationAnneeCorps", ++exercicesMusculationAnnee);
                editor.putInt("com.laurent.goga.bogatu.pumpnsmash.exercicesMusculationTotalCorps", ++exercicesMusculationTotal);
                editor.apply();
            }
            //set exercices jambes
            else{
                int exercicesMusculationSemaine = sharepref.getInt("com.laurent.goga.bogatu.pumpnsmash.exercicesMusculationSemaineJambes", 0);
                int exercicesMusculationMois = sharepref.getInt("com.laurent.goga.bogatu.pumpnsmash.exercicesMusculationMoisJambes", 0);
                int exercicesMusculationAnnee = sharepref.getInt("com.laurent.goga.bogatu.pumpnsmash.exercicesMusculationAnneeJambes", 0);
                int exercicesMusculationTotal = sharepref.getInt("com.laurent.goga.bogatu.pumpnsmash.exercicesMusculationTotalJambes", 0);
                editor.putInt("com.laurent.goga.bogatu.pumpnsmash.exercicesMusculationSemaineJambes", ++exercicesMusculationSemaine);
                editor.putInt("com.laurent.goga.bogatu.pumpnsmash.exercicesMusculationMoisJambes", ++exercicesMusculationMois);
                editor.putInt("com.laurent.goga.bogatu.pumpnsmash.exercicesMusculationAnneeJambes", ++exercicesMusculationAnnee);
                editor.putInt("com.laurent.goga.bogatu.pumpnsmash.exercicesMusculationTotalJambes", ++exercicesMusculationTotal);
                editor.apply();
            }


        }
        //set partie cardio
        else{
            //get calories
            int caloriesSemaine = sharepref.getInt("com.laurent.goga.bogatu.pumpnsmash.caloriesSemaine", 0);
            int caloriesMois = sharepref.getInt("com.laurent.goga.bogatu.pumpnsmash.caloriesMois",0);
            int caloriesAnnee = sharepref.getInt("com.laurent.goga.bogatu.pumpnsmash.caloriesAnnee",0);
            int caloriesTotal = sharepref.getInt("com.laurent.goga.bogatu.pumpnsmash.caloriesTotal",0);
            //get distance
            int distanceSemaine = sharepref.getInt("com.laurent.goga.bogatu.pumpnsmash.distanceSemaine", 0);
            int distanceMois = sharepref.getInt("com.laurent.goga.bogatu.pumpnsmash.distanceMois",0);
            int distanceAnnee = sharepref.getInt("com.laurent.goga.bogatu.pumpnsmash.distanceAnnee",0);
            int distanceTotal = sharepref.getInt("com.laurent.goga.bogatu.pumpnsmash.distanceTotal",0);
            //get nb exercies
            int exercicesCardioSemaine = sharepref.getInt("com.laurent.goga.bogatu.pumpnsmash.exercicesCardioSemaine", 0);
            int exercicesCardioMois = sharepref.getInt("com.laurent.goga.bogatu.pumpnsmash.exercicesCardioMois", 0);
            int exercicesCardioAnnee = sharepref.getInt("com.laurent.goga.bogatu.pumpnsmash.exercicesCardioAnnee", 0);
            int exercicesCardioTotal = sharepref.getInt("com.laurent.goga.bogatu.pumpnsmash.exercicesCardioTotal", 0);
            //set nb exercices
            editor.putInt("com.laurent.goga.bogatu.pumpnsmash.exercicesCardioSemaine", ++exercicesCardioSemaine);
            editor.putInt("com.laurent.goga.bogatu.pumpnsmash.exercicesCardioMois", ++exercicesCardioMois);
            editor.putInt("com.laurent.goga.bogatu.pumpnsmash.exercicesCardioAnnee", ++exercicesCardioAnnee);
            editor.putInt("com.laurent.goga.bogatu.pumpnsmash.exercicesCardioTotal", ++exercicesCardioTotal);
            //set calories
            editor.putInt("com.laurent.goga.bogatu.pumpnsmash.caloriesSemaine", caloriesSemaine+calories);
            editor.putInt("com.laurent.goga.bogatu.pumpnsmash.caloriesMois", caloriesMois+calories);
            editor.putInt("com.laurent.goga.bogatu.pumpnsmash.caloriesAnnee", caloriesAnnee+calories);
            editor.putInt("com.laurent.goga.bogatu.pumpnsmash.caloriesTotal", caloriesTotal+calories);
            //set distance
            editor.putInt("com.laurent.goga.bogatu.pumpnsmash.distanceSemaine", distanceSemaine + distance);
            editor.putInt("com.laurent.goga.bogatu.pumpnsmash.distanceMois", distanceMois + distance);
            editor.putInt("com.laurent.goga.bogatu.pumpnsmash.distanceAnnee", distanceAnnee + distance);
            editor.putInt("com.laurent.goga.bogatu.pumpnsmash.distanceTotal", distanceTotal + distance);
            editor.apply();
        }
    }
}
