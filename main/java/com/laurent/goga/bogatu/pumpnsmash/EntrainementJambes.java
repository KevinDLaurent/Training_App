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

public class EntrainementJambes extends AppCompatActivity {

    private Boolean squatFini = false;
    private Boolean hamstringFini = false;
    private Boolean legFini = false;
    private Boolean fentesFini= false;
    private Boolean calfFini = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrainement_jambes);

        final Context context = this;

        //affiche le button precedent seulement si un parent activity est declare dans AndroidManifest.xml
        getSupportActionBar().setHomeButtonEnabled(true);

        setPoidsExercices();

        //ajoute le poids souleve lors de l exercice
        final CheckBox squatCheckBox = findViewById(R.id.benchPressCheckBox);
        squatCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                squatFini = squatCheckBox.isChecked();
            }
        });

        //ajoute le poids souleve lors de l exercice
        final CheckBox hamstringCheckBox = findViewById(R.id.chestFlyesCheckBox);
        hamstringCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                hamstringFini = hamstringCheckBox.isChecked();
            }
        });

        //ajoute le poids souleve lors de l exercice
        final CheckBox legCheckBox = findViewById(R.id.pulldownCheckBox);
        legCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                legFini = legCheckBox.isChecked();
            }
        });

        //ajoute le poids souleve lors de l exercice
        final CheckBox fentesCheckBox = findViewById(R.id.rowingBarreCheckBox);
        fentesCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                fentesFini = fentesCheckBox.isChecked();
            }
        });

        //ajoute le poids souleve lors de l exercice
        final CheckBox calfCheckBox = findViewById(R.id.crunchBenchCheckBox);
        calfCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                calfFini = calfCheckBox.isChecked();
            }
        });



        //Termine l exercice
        final Button finEntrainementJambesButton = findViewById(R.id.finEntrainementJambesButton);
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
                if(squatFini || hamstringFini || legFini || fentesFini || calfFini) {
                   Intent gotoMenuPrincipal = new Intent(getApplicationContext(),MenuPrincipal.class);
                   startActivity(gotoMenuPrincipal);
                   Statistiques stats = new Statistiques();
                   stats.ajouteExercice(true, 3,0,0, context);
                }
                else{
                    Context context = getApplicationContext();
                    CharSequence text = getString(R.string.aucunExerciceEffectue);
                    int duration = Toast.LENGTH_LONG;
                    Toast.makeText(context, text, duration).show();
                }
            }
        });

        //affiche description squat
        Button squatButton = findViewById(R.id.squatButton);
        squatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotoDescriptionExercice = new Intent(getApplicationContext(),DescriptionExercice.class);
                gotoDescriptionExercice.putExtra("com.laurent.goga.bogatu.pumpnsmash.descriptionName", "squat");
                startActivity(gotoDescriptionExercice);
            }
        });

        //affiche description hamstring
        Button hamstringCurlsButton = findViewById(R.id.hamstringCurlsButton);
        hamstringCurlsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotoDescriptionExercice = new Intent(getApplicationContext(),DescriptionExercice.class);
                gotoDescriptionExercice.putExtra("com.laurent.goga.bogatu.pumpnsmash.descriptionName", "hamstringCurls");
                startActivity(gotoDescriptionExercice);
            }
        });

        //affiche description legs
        Button seatedLegExtensionButton = findViewById(R.id.seatedLegExtensionButton);
        seatedLegExtensionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotoDescriptionExercice = new Intent(getApplicationContext(),DescriptionExercice.class);
                gotoDescriptionExercice.putExtra("com.laurent.goga.bogatu.pumpnsmash.descriptionName", "seatedLegExtensions");
                startActivity(gotoDescriptionExercice);
            }
        });

        //affiche description fentes
        Button fentesStatiquesButton = findViewById(R.id.fentesStatiquesButton);
        fentesStatiquesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotoDescriptionExercice = new Intent(getApplicationContext(),DescriptionExercice.class);
                gotoDescriptionExercice.putExtra("com.laurent.goga.bogatu.pumpnsmash.descriptionName", "fentesStatiques");
                startActivity(gotoDescriptionExercice);
            }
        });

        //affiche description calves
        Button calfRaiseButton = findViewById(R.id.calfRaiseButton);
        calfRaiseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotoDescriptionExercice = new Intent(getApplicationContext(),DescriptionExercice.class);
                gotoDescriptionExercice.putExtra("com.laurent.goga.bogatu.pumpnsmash.descriptionName", "calfRaise");
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
        setPoidsSquat(sexe, ageGroup, systMesure);
        setPoidsHamstrings(sexe, ageGroup, systMesure);
        setPoidsLeg(sexe, ageGroup, systMesure);
        setPoidsCalf(sexe,ageGroup,systMesure);
        setNombreRepsFentes(sexe, ageGroup);

    }

    //affiche le poids de Squat
    private void setPoidsSquat(String sexe, int ageGroup, String systMesure){
        int poids;
        String textAffiche;
        String mesure = "kg";
        if(sexe.equals("F")){
            poids = 30 - 15 * ageGroup;
        }
        else{
            poids = 60 - 20 * ageGroup;
        }
        if(systMesure.equals("imperial")){
            poids = (int)(5*Math.round(poids * 2.2 / 5));
            mesure = "lb";
        }
        TextView poidsSquatTextView = findViewById(R.id.poidsBenchPressTextView);
        textAffiche = "3 x 12 x " + poids + mesure;
        poidsSquatTextView.setText(textAffiche);

    }

    //affiche le pods de Hamstring
    private void setPoidsHamstrings(String sexe, int ageGroup, String systMesure){
        int poids;
        String textAffiche;
        String mesure = "kg";
        if(sexe.equals("F")){
            poids = 20 - 5 * ageGroup;
        }
        else{
            poids = 35 - 10 * ageGroup;
        }
        if(systMesure.equals("imperial")){
            poids = (int)(5*Math.round(poids * 2.2 / 5));
            mesure = "lb";
        }
        TextView poidsHamstringTextView = findViewById(R.id.poidsChestFlyesTextView);
        textAffiche = "3 x 12 x " + poids + mesure;
        poidsHamstringTextView.setText(textAffiche);
    }

    //affiche le poids de Legs
    private void setPoidsLeg(String sexe, int ageGroup, String systMesure){
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

    //affiche le poids de Calf
    private void setPoidsCalf(String sexe, int ageGroup, String systMesure){
        int poids;
        String textAffiche;
        String mesure = "kg";
        if(sexe.equals("F")){
            poids = 30 - 10 * ageGroup;
        }
        else{
            poids = 60 - 20 * ageGroup;
        }
        if(systMesure.equals("imperial")){
            poids = (int)(5*Math.round(poids * 2.2 / 5));
            mesure = "lb";
        }
        TextView poidsCalfTextView = findViewById(R.id.poidsCrunchBenchTextView);
        textAffiche = "3 x 12 x " + poids + mesure;
        poidsCalfTextView.setText(textAffiche);
    }

    //affiche le poids de Fentes
    private void setNombreRepsFentes(String sexe, int ageGroup){
        int nbReps;
        String textAffiche;
        if(sexe.equals("F")){
            nbReps = 15 - 5 * ageGroup;
        }
        else{
            nbReps = 25 - 10 * ageGroup;
        }
        textAffiche = "3 x "+nbReps;
        TextView poidsFentesTextView = findViewById(R.id.poidsRowingBarreTextView);
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
