package com.laurent.goga.bogatu.pumpnsmash;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

public class EntrainementCorps extends AppCompatActivity {

    private Boolean benchPressFini = false;
    private Boolean chestFlyesFini = false;
    private Boolean pulldownFini = false;
    private Boolean rowingBarreFini = false;
    private Boolean crunchBenchFini = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrainement_corps);

        final Context context = this;

        //affiche le button precedent seulement si un parent activity est declare dans AndroidManifest.xml
        getSupportActionBar().setHomeButtonEnabled(true);

        setPoidsExercices();

        //ajoute le poids souleve lors de l exercice
        final CheckBox benchPressCheckBox = findViewById(R.id.benchPressCheckBox);
        benchPressCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                benchPressFini = benchPressCheckBox.isChecked();
            }
        });

        //ajoute le poids souleve lors de l exercice
        final CheckBox chestFlyesCheckBox = findViewById(R.id.chestFlyesCheckBox);
        chestFlyesCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                chestFlyesFini = chestFlyesCheckBox.isChecked();
            }
        });

        //ajoute le poids souleve lors de l exercice
        final CheckBox pulldownCheckBox = findViewById(R.id.pulldownCheckBox);
        pulldownCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                pulldownFini = pulldownCheckBox.isChecked();
            }
        });

        //ajoute le poids souleve lors de l exercice
        final CheckBox rowingBarreCheckBox = findViewById(R.id.rowingBarreCheckBox);
        rowingBarreCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                rowingBarreFini = rowingBarreCheckBox.isChecked();
            }
        });

        //ajoute le poids souleve lors de l exercice
        final CheckBox crunchBenchCheckBox = findViewById(R.id.crunchBenchCheckBox);
        crunchBenchCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                crunchBenchFini = crunchBenchCheckBox.isChecked();
            }
        });



        //Termine l exercice
        final Button finEntrainementJambesButton = findViewById(R.id.finEntrainementCorpsButton);
        finEntrainementJambesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //envoyer notification
                SharedPreferences sharepref = getSharedPreferences
                        ("com.laurent.goga.bogatu.pumpnsmash", MODE_PRIVATE);
                String notifs = sharepref.getString("com.laurent.goga.bogatu.pumpnsmash.notifs","error");
                if(notifs.equals("on")) {
                    sendNotification();
                }
                if(benchPressFini || chestFlyesFini || pulldownFini || rowingBarreFini || crunchBenchFini) {
                    Intent gotoMenuPrincipal = new Intent(getApplicationContext(),MenuPrincipal.class);
                    startActivity(gotoMenuPrincipal);
                    Statistiques stats = new Statistiques();
                    stats.ajouteExercice(true, 2,0,0, context);
                }
                else{
                    Context context = getApplicationContext();
                    CharSequence text = getString(R.string.aucunExerciceEffectue);
                    int duration = Toast.LENGTH_LONG;
                    Toast.makeText(context, text, duration).show();
                }
            }
        });

        //affiche description bench press
        Button benchPressButton = findViewById(R.id.benchPressButton);
        benchPressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotoDescriptionExercice = new Intent(getApplicationContext(),DescriptionExercice.class);
                gotoDescriptionExercice.putExtra("com.laurent.goga.bogatu.pumpnsmash.descriptionName", "benchPress");
                startActivity(gotoDescriptionExercice);
            }
        });

        //affiche description chest flyes
        Button chestFlyesButton = findViewById(R.id.chestFlyesButton);
        chestFlyesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotoDescriptionExercice = new Intent(getApplicationContext(),DescriptionExercice.class);
                gotoDescriptionExercice.putExtra("com.laurent.goga.bogatu.pumpnsmash.descriptionName", "chestFlyes");
                startActivity(gotoDescriptionExercice);
            }
        });

        //affiche description pulldown
        Button pulldownButton = findViewById(R.id.pulldownButton);
        pulldownButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotoDescriptionExercice = new Intent(getApplicationContext(),DescriptionExercice.class);
                gotoDescriptionExercice.putExtra("com.laurent.goga.bogatu.pumpnsmash.descriptionName", "pulldown");
                startActivity(gotoDescriptionExercice);
            }
        });

        //affiche description rowning barre
        Button rowingBarreButton = findViewById(R.id.rowingBarreButton);
        rowingBarreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotoDescriptionExercice = new Intent(getApplicationContext(),DescriptionExercice.class);
                gotoDescriptionExercice.putExtra("com.laurent.goga.bogatu.pumpnsmash.descriptionName", "rowingBarre");
                startActivity(gotoDescriptionExercice);
            }
        });

        //affiche description crunch bench
        Button crunchBenchButton = findViewById(R.id.crunchBenchButton);
        crunchBenchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotoDescriptionExercice = new Intent(getApplicationContext(),DescriptionExercice.class);
                gotoDescriptionExercice.putExtra("com.laurent.goga.bogatu.pumpnsmash.descriptionName", "crunchBench");
                startActivity(gotoDescriptionExercice);
            }
        });
    }

    //Appelles les differentes fonctions pour afficher le poids de tous les exercies
    private void setPoidsExercices(){
        SharedPreferences sharepref = getSharedPreferences
                ("com.laurent.goga.bogatu.pumpnsmash", MODE_PRIVATE);
        String sexe = sharepref.getString("com.laurent.goga.bogatu.pumpnsmash.sexe","error");
        String systMesure = sharepref.getString("com.laurent.goga.bogatu.pumpnsmash.systMesure","error");
        int ageGroup = sharepref.getInt("com.laurent.goga.bogatu.pumpnsmash.ageGroup", 0);
        setPoidsBenchPress(sexe, ageGroup, systMesure);
        setPoidsChestFlyes(sexe, ageGroup, systMesure);
        setPoidsPulldown(sexe, ageGroup, systMesure);
        setPoidsRowingBarre(sexe,ageGroup,systMesure);
        setPoidsCrunchBench(sexe, ageGroup);

    }

    //affiche le poids de bench press
    private void setPoidsBenchPress(String sexe, int ageGroup, String systMesure){
        int poids;
        String textAffiche;
        String mesure = "kg";
        if(sexe.equals("F")){
            poids = 15 - 5 * ageGroup;
        }
        else{
            poids = 30 - 5 * ageGroup;
        }
        if(systMesure.equals("imperial")){
            poids = (int)(5*Math.round(poids * 2.2 / 5));
            mesure = "lb";
        }
        TextView poidsSquatTextView = findViewById(R.id.poidsBenchPressTextView);
        textAffiche = "3 x 10 x " + poids + mesure;
        poidsSquatTextView.setText(textAffiche);

    }

    //affiche le pods de chest flyes
    private void setPoidsChestFlyes(String sexe, int ageGroup, String systMesure){
        int poids;
        String textAffiche;
        String mesure = "kg";
        if(sexe.equals("F")){
            poids = 12 - 5 * ageGroup;
        }
        else{
            poids = 15 - 5 * ageGroup;
        }
        if(systMesure.equals("imperial")){
            poids = (int)(5*Math.round(poids * 2.2 / 5));
            mesure = "lb";
        }
        TextView poidsHamstringTextView = findViewById(R.id.poidsChestFlyesTextView);
        textAffiche = "3 x 10 x " + poids + mesure;
        poidsHamstringTextView.setText(textAffiche);
    }

    //affiche le poids de pulldown
    private void setPoidsPulldown(String sexe, int ageGroup, String systMesure){
        int poids;
        String textAffiche;
        String mesure = "kg";
        if(sexe.equals("F")){
            poids = 30 - 10 * ageGroup;
        }
        else{
            poids = 45- 15 * ageGroup;
        }
        if(systMesure.equals("imperial")){
            poids = (int)(5*Math.round((poids * 2.2 / 5)));
            mesure = "lb";
        }
        TextView poidsLegTextView = findViewById(R.id.poidsPulldownTextView);
        textAffiche = "3 x 12 x " + poids + mesure;
        poidsLegTextView.setText(textAffiche);
    }

    //affiche le poids de crunch bench
    private void setPoidsRowingBarre(String sexe, int ageGroup, String systMesure){
        int poids;
        String textAffiche;
        String mesure = "kg";
        if(sexe.equals("F")){
            poids = 20 - 10 * ageGroup;
        }
        else{
            poids = 30 - 20 * ageGroup;
        }
        if(systMesure.equals("imperial")){
            poids = (int)(5*Math.round(poids * 2.2 / 5));
            mesure = "lb";
        }
        TextView poidsCalfTextView = findViewById(R.id.poidsRowingBarreTextView);
        textAffiche = "4 x 8 x " + poids + mesure;
        poidsCalfTextView.setText(textAffiche);
    }

    //affiche le poids de Fentes
    private void setPoidsCrunchBench(String sexe, int ageGroup){
        int nbReps;
        String textAffiche;
        if(sexe.equals("F")){
            nbReps = 12 - 5 * ageGroup;
        }
        else{
            nbReps = 20 - 10 * ageGroup;
        }
        textAffiche = "3 x "+nbReps;
        TextView poidsFentesTextView = findViewById(R.id.poidsCrunchBenchTextView);
        poidsFentesTextView.setText(textAffiche);
    }

    public void sendNotification() {

        //Creer instance de NotificationManager

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setLargeIcon(BitmapFactory.decodeResource(this.getResources(),
                                R.mipmap.ic_launcher))
                        .setContentTitle("Entraînement complet")
                        .setContentText("Voir la progression dans \"statistiques\"");

        Intent resultIntent = new Intent(this, Statistiques.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(Statistiques.class);

        // Placer le nouveau intent premier dans le stack
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(resultPendingIntent);

        //instance de NotificationManager service

        NotificationManager mNotificationManager =

                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        mNotificationManager.notify(001, mBuilder.build());
    }
}
