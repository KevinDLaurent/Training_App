package com.laurent.goga.bogatu.pumpnsmash;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Formulaire2 extends AppCompatActivity {

    //variables utilises pour verifier que l utilisateur a tout rempli
    private static int selectAge = 0;
    private static int selectSexe = 0;
    private static int selectTaille = 0;
    private static int selectPoids = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulaire2);

        //par default on utilise le systeme metrique
        SharedPreferences sharepref = getSharedPreferences
                ("com.laurent.goga.bogatu.pumpnsmash",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharepref.edit();
        editor.putString("com.laurent.goga.bogatu.pumpnsmash.systMesure","metrique");
        editor.apply();
        //par defaut notifications on
        editor.putString("com.laurent.goga.bogatu.pumpnsmash.notifs", "on");
        editor.apply();

        //remplie le spinner age avec les valeurs de 16 a 80
        initialiseAgeSpinner();

        //choisi l age
        final Spinner ageSpinner = findViewById(R.id.ageSpinner);
        ageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int age = Integer.parseInt(adapterView.getItemAtPosition(i).toString());
                SharedPreferences sharepref = getSharedPreferences
                        ("com.laurent.goga.bogatu.pumpnsmash",MODE_PRIVATE);
                SharedPreferences.Editor editor = sharepref.edit();
                editor.putInt("com.laurent.goga.bogatu.pumpnsmash.age",age);
                editor.apply();

                //l utilisateur a indique son age
                Formulaire2.selectAge = age;

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        //choisi le sexe
        RadioGroup sexeRadioGroup = findViewById(R.id.sexeRadioGroup);
        sexeRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                SharedPreferences sharepref = getSharedPreferences
                        ("com.laurent.goga.bogatu.pumpnsmash",MODE_PRIVATE);
                SharedPreferences.Editor editor = sharepref.edit();
                switch (i){
                    case R.id.maleRadioButton:
                        editor.putString("com.laurent.goga.bogatu.pumpnsmash.sexe","M");
                        editor.apply();
                        break;
                    case R.id.femaleRadioButton:
                        editor.putString("com.laurent.goga.bogatu.pumpnsmash.sexe","F");
                        editor.apply();
                        break;
                }

                //l utilisateur a indique son sexe
                Formulaire2.selectSexe = 1 ;
            }
        });

        //change entre metrique et imperial
        RadioGroup systMesureRadioGroup = findViewById(R.id.systMesureRadioGroup);
        systMesureRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                SharedPreferences sharepref = getSharedPreferences
                        ("com.laurent.goga.bogatu.pumpnsmash",MODE_PRIVATE);
                SharedPreferences.Editor editor = sharepref.edit();
                TextView tailleTypeMesureTextView = (TextView)
                        findViewById(R.id.tailleTypeMesureTextView);
                TextView poidsTypeMesureTextView = (TextView)
                        findViewById(R.id.poidsTypeMesureTextView);
                switch (i){
                    case R.id.metriqueRadioButton:
                        editor.putString("com.laurent.goga.bogatu.pumpnsmash.systMesure","metrique");
                        editor.apply();
                        //change le taille en cm
                        String typeMesureTailleCM = getResources().getString(R.string.cm);
                        tailleTypeMesureTextView.setText(typeMesureTailleCM);
                        //change le poids en kg
                        String typeMesurePoidsKG = getResources().getString(R.string.kg);
                        poidsTypeMesureTextView.setText(typeMesurePoidsKG);
                        changeTailleSelonSystMesure(true);
                        changePoidsSelonSystMesure(true);
                        break;
                    case R.id.imperialRadioButton:
                        editor.putString("com.laurent.goga.bogatu.pumpnsmash.systMesure","imperial");
                        editor.apply();
                        //change le taille en in
                        String typeMesureTailleIN = getResources().getString(R.string.in);
                        tailleTypeMesureTextView.setText(typeMesureTailleIN);
                        //change le poids en lb
                        String typeMesurePoidsLB = getResources().getString(R.string.lb);
                        poidsTypeMesureTextView.setText(typeMesurePoidsLB);
                        changeTailleSelonSystMesure(false);
                        changePoidsSelonSystMesure(false);
                        break;
                }
            }
        });

        //remplie le spinner taille avec les mesure du systeme metrique
        changeTailleSelonSystMesure(true);

        //choisi la taille
        Spinner tailleSpinner = findViewById(R.id.tailleSpinner);
        tailleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int taille = Integer.parseInt(adapterView.getItemAtPosition(i).toString());
                SharedPreferences sharepref = getSharedPreferences
                        ("com.laurent.goga.bogatu.pumpnsmash",MODE_PRIVATE);
                SharedPreferences.Editor editor = sharepref.edit();
                //on store les donnes selon le systeme metrique
                String systMesure = sharepref.getString("com.laurent.goga.bogatu.pumpnsmash.systMesure","error");
                if(systMesure.equals("imperial")){
                    taille = (int) Math.round(taille * 2.54);
                }
                editor.putInt("com.laurent.goga.bogatu.pumpnsmash.taille",taille);
                editor.apply();

                //on indique que l utilisateur a selectionne sa taille
                Formulaire2.selectTaille = taille;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //change le spinner poids avec les mesure du systeme metrique
        changePoidsSelonSystMesure(true);

        //choisi le poids
        Spinner poidsSpinner = findViewById(R.id.poidsSpinner);
        poidsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int poids = Integer.parseInt(adapterView.getItemAtPosition(i).toString());
                SharedPreferences sharepref = getSharedPreferences
                        ("com.laurent.goga.bogatu.pumpnsmash",MODE_PRIVATE);
                //on store les donnes selon le systeme metrique
                String systMesure = sharepref.getString("com.laurent.goga.bogatu.pumpnsmash.systMesure","error");
                if(systMesure.equals("imperial")){
                    poids = (int)Math.round(poids / 2.2);
                }
                SharedPreferences.Editor editor = sharepref.edit();
                editor.putInt("com.laurent.goga.bogatu.pumpnsmash.poids",poids);
                editor.apply();

                //on indique que l utilisateur a selectionne son poids
                Formulaire2.selectPoids = poids;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //termine le formulaire et va au menu principal
        Button finFormulaireButton = findViewById(R.id.finFormulaireButton);
        finFormulaireButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Formulaire2.selectAge != 0 && Formulaire2.selectSexe != 0
                        && Formulaire2.selectTaille !=0 && Formulaire2.selectPoids !=0) {
                    SharedPreferences sharepref = getSharedPreferences
                            ("com.laurent.goga.bogatu.pumpnsmash", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharepref.edit();
                    editor.putBoolean("com.laurent.goga.bogatu.pumpnsmash.menu", true);
                    editor.apply();
                    Intent gotoMenuPrincipal = new Intent(getApplicationContext(), MenuPrincipal.class);
                    startActivity(gotoMenuPrincipal);

                }
                else{
                    //affiche message d erreur si l utilisateur n a pas choisi tous les parametres
                    Context context = getApplicationContext();
                    String message = getString(R.string.formulaireIncomplet);
                    if(Formulaire2.selectAge == 0){
                        message += " Age";
                    }
                    if(Formulaire2.selectSexe == 0){
                        message += " Sexe";
                    }
                    if(Formulaire2.selectTaille == 0){
                        message += " Taille";
                    }
                    if(Formulaire2.selectPoids == 0){
                        message += " Poids";
                    }
                    CharSequence text = message;
                    int duration = Toast.LENGTH_LONG;
                    Toast.makeText(context, text, duration).show();
                }
            }
        });
    }
    //change le valeurs du spinner taille en fonction du systeme de mesure selectionne
    private void changeTailleSelonSystMesure(Boolean metrique) {
        List taille = new ArrayList<Integer>();
        if (metrique) {
            for (int i = 119; i <= 220; i++) {
                if (i == 119) {
                    taille.add(Integer.toString(0));
                } else {
                    taille.add(Integer.toString(i));
                }
            }
        }
        else {
            for (int i = 46; i <= 87; i++) {
                if (i == 46) {
                    taille.add(Integer.toString(0));
                } else {
                    taille.add(Integer.toString(i));
                }
            }
        }
            ArrayAdapter<Integer> spinnerTailleArrayAdapter = new ArrayAdapter<Integer>(
                    this, android.R.layout.simple_spinner_item, taille);
            spinnerTailleArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            Spinner tailleSpinner = findViewById(R.id.tailleSpinner);
            tailleSpinner.setAdapter(spinnerTailleArrayAdapter);

    }

    //change le valeurs du spinner poids en fonction du systeme de mesure selectionne
    private  void changePoidsSelonSystMesure(Boolean metrique){
        List poids = new ArrayList<Integer>();
        if(metrique) {
            for (int i = 34; i <= 200; i++) {
                if (i == 34) {
                    poids.add(Integer.toString(0));
                } else {
                    poids.add(Integer.toString(i));
                }
            }
        }
        else{
            for (int i = 74; i <= 440; i++) {
                if (i == 74) {
                    poids.add(Integer.toString(0));
                } else {
                    poids.add(Integer.toString(i));
                }
            }
        }
        ArrayAdapter<Integer> spinnerPoidsArrayAdapter = new ArrayAdapter<Integer>(
                this, android.R.layout.simple_spinner_item, poids);
        spinnerPoidsArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner poidsSpinner = findViewById(R.id.poidsSpinner);
        poidsSpinner.setAdapter(spinnerPoidsArrayAdapter);
    }

    //cree le valeur du spinner age
    private void initialiseAgeSpinner(){
        List age = new ArrayList<Integer>();
        for (int i = 15; i <= 80; i++) {
            if(i==15){
                age.add(Integer.toString(0));
            }
            else {
                age.add(Integer.toString(i));
            }
        }
        ArrayAdapter<Integer> spinnerArrayAdapter = new ArrayAdapter<Integer>(
                this, android.R.layout.simple_spinner_item, age);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner ageSpinner = (Spinner) findViewById(R.id.ageSpinner);
        ageSpinner.setAdapter(spinnerArrayAdapter);
    }

    private void creeProfilEntrainement(){
        SharedPreferences sharepref = getSharedPreferences
                ("com.laurent.goga.bogatu.pumpnsmash",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharepref.edit();
        int age = sharepref.getInt("com.laurent.goga.bogatu.pumpnsmash.age", 0);
        int taille = sharepref.getInt("com.laurent.goga.bogatu.pumpnsmash.taille",0);
        int poids = sharepref.getInt("com.laurent.goga.bogatu.pumpnsmash.poids", 0);
        String systMesure = sharepref.getString("com.laurent.goga.bogatu.pumpnsmash.systMesure","error");
        int ageGroup = getAgeGroup(age);
        int imc = getIMC(taille, poids, systMesure);
        String interpretationIMC = interpretationIMC(imc);
        editor.putInt("com.laurent.goga.bogatu.pumpnsmash.ageGroup", ageGroup);
        editor.putInt("com.laurent.goga.bogatu.pumpnsmash.imc", imc);
        editor.putString("com.laurent.goga.bogatu.pumpnsmash.interpretationIMC", interpretationIMC);
    }

    //determine le groupe d age
    private  int getAgeGroup(int age){
        int ageGroup;
        if(age >= 15 && age < 40){
            ageGroup = 0;
        }
        else if(age >= 40 && age < 60){
            ageGroup = 1;
        }
        else {
            ageGroup = 2;
        }
        return ageGroup;
    }

    //determine l indice de masse corporelle
    private int getIMC(int taille, int poids, String systMesure){
        double tailleCM = taille;
        double poidsKG = poids;
        if(systMesure.equals("imperial")){
            tailleCM = Math.round(tailleCM * 2.54);
            poidsKG = Math.round(poidsKG / 2.2);
        }
        return (int)Math.round(poidsKG / Math.pow(tailleCM,2.0));
    }

    //interprete l indice de masse corporelle
    private String interpretationIMC(int imc){
        if(imc < 12){
            return "Etremement maigre";
        }
        else if (imc >= 12 && imc < 19){
            return "Maigre";
        }
        else if(imc >=19 && imc < 25){
            return "Poids Sante";
        }
        else if(imc >= 25 && imc < 30){
            return "Surpoids";
        }
        else if (imc >= 30 && imc < 39){
            return "Obese";
        }
        else {
            return "Morbidement Obese";
        }
    }
}
