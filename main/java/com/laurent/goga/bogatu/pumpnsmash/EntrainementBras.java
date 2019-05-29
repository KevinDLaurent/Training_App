package com.laurent.goga.bogatu.pumpnsmash;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

public class EntrainementBras extends AppCompatActivity {

    private Boolean curlsBarreFini = false;
    private Boolean curlsHalteresFini = false;
    private Boolean extensionsFini = false;
    private Boolean kickbackFini = false;
    private Boolean tirageMentonFini = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrainement_bras);

        final Context context = this;

        //affiche le button precedent seulement si un parent activity est declare dans AndroidManifest.xml
        getSupportActionBar().setHomeButtonEnabled(true);

        setPoidsExercices();

        //ajoute le poids souleve lors de l exercice
        final CheckBox curlsBarreCheckBox = findViewById(R.id.benchPressCheckBox);
        curlsBarreCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                curlsBarreFini = curlsBarreCheckBox.isChecked();
            }
        });

        //ajoute le poids souleve lors de l exercice
        final CheckBox curlsHalteresCheckBox = findViewById(R.id.chestFlyesCheckBox);
        curlsHalteresCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                curlsHalteresFini = curlsHalteresCheckBox.isChecked();
            }
        });

        //ajoute le poids souleve lors de l exercice
        final CheckBox extensionsVerticalesCheckBox = findViewById(R.id.pulldownCheckBox);
        extensionsVerticalesCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                extensionsFini = extensionsVerticalesCheckBox.isChecked();
            }
        });

        //ajoute le poids souleve lors de l exercice
        final CheckBox kickbackCheckBox = findViewById(R.id.rowingBarreCheckBox);
        kickbackCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                kickbackFini = kickbackCheckBox.isChecked();
            }
        });

        //ajoute le poids souleve lors de l exercice
        final CheckBox tirageMentonCheckBox = findViewById(R.id.crunchBenchCheckBox);
        tirageMentonCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                tirageMentonFini = tirageMentonCheckBox.isChecked();
            }
        });



        //Termine l exercice
        final Button finEntrainementJambesButton = findViewById(R.id.finEntrainementBrasButton);
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
                if(curlsBarreFini || curlsHalteresFini || extensionsFini || kickbackFini || tirageMentonFini) {
                    Intent gotoMenuPrincipal = new Intent(getApplicationContext(),MenuPrincipal.class);
                    startActivity(gotoMenuPrincipal);
                    Statistiques stats = new Statistiques();
                    stats.ajouteExercice(true, 1,0,0, context);
                }
                else{
                    Context context = getApplicationContext();
                    CharSequence text = getString(R.string.aucunExerciceEffectue);
                    int duration = Toast.LENGTH_LONG;
                    Toast.makeText(context, text, duration).show();
                }
            }
        });

        //affiche description curls a la barre
        Button curlsBarreButton = findViewById(R.id.curlsBarreButton);
        curlsBarreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotoDescriptionExercice = new Intent(getApplicationContext(),DescriptionExercice.class);
                gotoDescriptionExercice.putExtra("com.laurent.goga.bogatu.pumpnsmash.descriptionName", "curlsBarre");
                startActivity(gotoDescriptionExercice);
            }
        });

        //affiche description curls avec halteres
        Button curlsHalteresButton = findViewById(R.id.curlsHalteresButton);
        curlsHalteresButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotoDescriptionExercice = new Intent(getApplicationContext(),DescriptionExercice.class);
                gotoDescriptionExercice.putExtra("com.laurent.goga.bogatu.pumpnsmash.descriptionName", "curlsHalteres");
                startActivity(gotoDescriptionExercice);
            }
        });

        //affiche description extensions verticales
        Button extensionsVerticalesButton = findViewById(R.id.extensionsVerticalesButton);
        extensionsVerticalesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotoDescriptionExercice = new Intent(getApplicationContext(),DescriptionExercice.class);
                gotoDescriptionExercice.putExtra("com.laurent.goga.bogatu.pumpnsmash.descriptionName", "extensionsVerticales");
                startActivity(gotoDescriptionExercice);
            }
        });

        //affiche description kickback
        Button kickbackButton = findViewById(R.id.kickbackButton);
        kickbackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotoDescriptionExercice = new Intent(getApplicationContext(),DescriptionExercice.class);
                gotoDescriptionExercice.putExtra("com.laurent.goga.bogatu.pumpnsmash.descriptionName", "kickback");
                startActivity(gotoDescriptionExercice);
            }
        });

        //affiche description tirage menton
        Button tirageMentonButton = findViewById(R.id.tirageMentonButton);
        tirageMentonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotoDescriptionExercice = new Intent(getApplicationContext(),DescriptionExercice.class);
                gotoDescriptionExercice.putExtra("com.laurent.goga.bogatu.pumpnsmash.descriptionName", "tirageMenton");
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
        setPoidsCurlsBarre(sexe, ageGroup, systMesure);
        setPoidsCurlsHalteres(sexe, ageGroup, systMesure);
        setPoidsExtensionsVerticales(sexe, ageGroup, systMesure);
        setPoidsTirageMenton(sexe,ageGroup,systMesure);
        setPoidsKickback(sexe, ageGroup, systMesure);

    }

    //affiche le poids de Curls a la barre
    private void setPoidsCurlsBarre(String sexe, int ageGroup, String systMesure){
        int poids;
        String textAffiche;
        String mesure = "kg";
        if(sexe.equals("F")){
            poids = (int)Math.round(10 - 2.5 * ageGroup);
        }
        else{
            poids = 22 - 5 * ageGroup;
        }
        if(systMesure.equals("imperial")){
            poids = (int)(5*Math.round(poids * 2.2 / 5));
            mesure = "lb";
        }
        TextView poidsSquatTextView = findViewById(R.id.poidsBenchPressTextView);
        textAffiche = "4 x 10 x " + poids + mesure;
        poidsSquatTextView.setText(textAffiche);

    }

    //affiche le pods de curls avec halteres
    private void setPoidsCurlsHalteres(String sexe, int ageGroup, String systMesure){
        int poids;
        String textAffiche;
        String mesure = "kg";
        if(sexe.equals("F")){
            poids = 15 - 5 * ageGroup;
        }
        else{
            poids = 25 - 10 * ageGroup;
        }
        if(systMesure.equals("imperial")){
            poids = (int)(5*Math.round(poids * 2.2 / 5));
            mesure = "lb";
        }
        TextView poidsHamstringTextView = findViewById(R.id.poidsChestFlyesTextView);
        textAffiche = "4 x 8 x " + poids + mesure;
        poidsHamstringTextView.setText(textAffiche);
    }

    //affiche le poids des extensions verticales
    private void setPoidsExtensionsVerticales(String sexe, int ageGroup, String systMesure){
        int poids;
        String textAffiche;
        String mesure = "kg";
        if(sexe.equals("F")){
            poids = 12 - 5 * ageGroup;
        }
        else{
            poids = 20- 5 * ageGroup;
        }
        if(systMesure.equals("imperial")){
            poids = (int)(5*Math.round((poids * 2.2 / 5)));
            mesure = "lb";
        }
        TextView poidsLegTextView = findViewById(R.id.poidsPulldownTextView);
        textAffiche = "3 x 8 x " + poids + mesure;
        poidsLegTextView.setText(textAffiche);
    }

    //affiche le poids de tirage menton
    private void setPoidsTirageMenton(String sexe, int ageGroup, String systMesure){
        int poids;
        String textAffiche;
        String mesure = "kg";
        if(sexe.equals("F")){
            poids = 25 - 5 * ageGroup;
        }
        else{
            poids = 35 - 5 * ageGroup;
        }
        if(systMesure.equals("imperial")){
            poids = (int)(5*Math.round(poids * 2.2 / 5));
            mesure = "lb";
        }
        TextView poidsCalfTextView = findViewById(R.id.poidsCrunchBenchTextView);
        textAffiche = "3 x 10 x " + poids + mesure;
        poidsCalfTextView.setText(textAffiche);
    }

    //affiche le poids de kickback
    private void setPoidsKickback(String sexe, int ageGroup, String systMesure){
        int poids;
        String textAffiche;
        String mesure = "kg";
        if(sexe.equals("F")){
            poids = 15 - 5 * ageGroup;
        }
        else{
            poids = 20 - 5 * ageGroup;
        }
        if(systMesure.equals("imperial")){
            poids = (int)(5*Math.round(poids * 2.2 / 5));
            mesure = "lb";
        }
        TextView poidsCalfTextView = findViewById(R.id.poidsRowingBarreTextView);
        textAffiche = "4 x 8 x " + poids + mesure;
        poidsCalfTextView.setText(textAffiche);
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