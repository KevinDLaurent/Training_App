package com.laurent.goga.bogatu.pumpnsmash;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

public class CardioCalories extends AppCompatActivity {

    private String unit;
    private String metric;
    private double cal;
    private double dist;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardio_calories);

        final Context context = this;

        // Selection du bon mesure
        SharedPreferences sharepref = getSharedPreferences
                ("com.laurent.goga.bogatu.pumpnsmash", MODE_PRIVATE);
        String systMesure = sharepref.getString("com.laurent.goga.bogatu.pumpnsmash.systMesure", null);
        int poids = sharepref.getInt("com.laurent.goga.bogatu.pumpnsmash.poids", 0);

        if (systMesure != null && systMesure.equals("metrique")) {
            unit = "metrique";
        } else if (systMesure != null && systMesure.equals("imperial")) {
            unit = "imperial";
        }

        Button main = (Button)findViewById(R.id.main);
        TextView text = (TextView) findViewById(R.id.text);
        CardioMeter cMeter = new CardioMeter();
        float distance = cMeter.getDistanceCovered();
        cMeter.resetDistanceCovered();
        DecimalFormat df = new DecimalFormat("#");
        DecimalFormat df2 = new DecimalFormat("#.##");

        // Formule ref: http://www.shapesense.com/fitness-exercise/calculators/running-calorie-burn-calculator.shtml
        // cals burned = (((0.05) + 0.95) x weight(kg)) x distance(km) --> weight * distance
        if (unit == "metrique") {
            dist = distance * 0.001;
            cal = (double)poids * dist;
            metric = "km";
        } else if (unit == "imperial") {
            dist = distance * 0.000621371;
            // convertir lb vers kg pour calcul
            cal = (double)poids * 0.453592 * dist;
            metric = "mi";
        }

        // Afficher le nombre de calories brulee et la distance parcourue
        String calBurned = df.format(cal);
        String distMetric = df2.format(dist) + " " + metric;

        text.setText(getString(R.string.calsBurned, distMetric, calBurned));

        // Envoyer dist (la distance) et calo (les calories brulees) vers les statistiques
        int dist = (int) this.dist;
        int calo = (int)cal;

        Statistiques stats = new Statistiques();
        stats.ajouteExercice(false, 0, dist, calo, context);

        // Retourner au menu principale
        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //envoyer notification
                SharedPreferences sharepref = getSharedPreferences
                        ("com.laurent.goga.bogatu.pumpnsmash", MODE_PRIVATE);
                String notifs = sharepref.getString("com.laurent.goga.bogatu.pumpnsmash.notifs","error");
                if(notifs.equals("on")) {
                    sendNotification();
                }
                Intent gotoMenuPrincipal = new Intent (getApplicationContext(), MenuPrincipal.class);
                startActivity(gotoMenuPrincipal);
            }
        });
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