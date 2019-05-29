package com.laurent.goga.bogatu.pumpnsmash;

import android.app.Dialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class Options extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        //Affiche le boutton precedent
        getSupportActionBar().setHomeButtonEnabled(true);

        //Check le systeme de mesure utilise
        SharedPreferences sharepref = getSharedPreferences
                ("com.laurent.goga.bogatu.pumpnsmash", MODE_PRIVATE);
        String systMesure = sharepref.getString("com.laurent.goga.bogatu.pumpnsmash.systMesure", null);
        if (systMesure != null && systMesure.equals("metrique")) {
            RadioButton metriqueOptionsButton = (RadioButton) findViewById(R.id.metriqueOptionsRadioButton);
            metriqueOptionsButton.setChecked(true);
        } else if (systMesure != null && systMesure.equals("imperial")) {
            RadioButton imperialOptionsButton = (RadioButton) findViewById(R.id.imperialOptionsRadioButton);
            imperialOptionsButton.setChecked(true);
        }

        //Check l'etat des notifications
        String notifs = sharepref.getString("com.laurent.goga.bogatu.pumpnsmash.notifs", null);
        if (notifs != null && notifs.equals("on")) {
            RadioButton notifsOn = (RadioButton) findViewById(R.id.notifsOn);
            notifsOn.setChecked(true);
        } else if (notifs != null && notifs.equals("off")) {
            RadioButton notifsOff = (RadioButton) findViewById(R.id.notifsOff);
            notifsOff.setChecked(true);
        }

        //EventListener du Mesure Systeme
        RadioGroup systMesureOptionsRadioGroup = (RadioGroup)
                findViewById(R.id.systMesureOptionsRadioGroup);
        systMesureOptionsRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                SharedPreferences sharepref = getSharedPreferences
                        ("com.laurent.goga.bogatu.pumpnsmash", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharepref.edit();
                switch (i) {
                    case R.id.metriqueOptionsRadioButton:
                        editor.putString("com.laurent.goga.bogatu.pumpnsmash.systMesure", "metrique");
                        editor.apply();
                        break;
                    case R.id.imperialOptionsRadioButton:
                        editor.putString("com.laurent.goga.bogatu.pumpnsmash.systMesure", "imperial");
                        editor.apply();
                        break;
                }
            }
        });

        //EventListener des Notifications
        RadioGroup notifsOptionsRadioGroup = (RadioGroup)
                findViewById(R.id.notifsOptionsRadioGroup);
        notifsOptionsRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                SharedPreferences sharepref = getSharedPreferences
                        ("com.laurent.goga.bogatu.pumpnsmash", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharepref.edit();
                switch (i) {
                    case R.id.notifsOn:
                        editor.putString("com.laurent.goga.bogatu.pumpnsmash.notifs", "on");
                        editor.apply();
                        break;
                    case R.id.notifsOff:
                        editor.putString("com.laurent.goga.bogatu.pumpnsmash.notifs", "off");
                        editor.apply();
                        break;
                }
            }
        });

        //Reset donnees
        Button resetOptionsButton = (Button) findViewById(R.id.resetOptionsButton);
        resetOptionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Options.this);
                    builder.setCancelable(true)
                        .setTitle(R.string.resetApp)
                        .setMessage(R.string.resetOptionsString)
                        .setPositiveButton(R.string.oui, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                SharedPreferences sharepref = getSharedPreferences
                                        ("com.laurent.goga.bogatu.pumpnsmash", MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharepref.edit();
                                editor.clear();
                                editor.apply();
                                Intent gotoFormulaire2 = new Intent(getApplicationContext(),Formulaire2.class);
                                startActivity(gotoFormulaire2);
                            }
                        })
                        .setNegativeButton(R.string.annuler, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        });
                AlertDialog resetDialog = builder.create();
                resetDialog.show();
            }
        });
    }

}
