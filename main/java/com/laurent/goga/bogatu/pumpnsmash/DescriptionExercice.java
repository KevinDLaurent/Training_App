package com.laurent.goga.bogatu.pumpnsmash;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DescriptionExercice extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description_exercice);

        //affiche le button precedent seulement si un parent activity est declare dans AndroidManifest.xml
        getSupportActionBar().setHomeButtonEnabled(true);

        TextView descriptionTextView = findViewById(R.id.descriptionTextView);
        ImageView descriptionImageView = findViewById(R.id.descriptionImageView);

        if(getIntent().hasExtra("com.laurent.goga.bogatu.pumpnsmash.descriptionName")){
            switch (getIntent().getExtras().getString("com.laurent.goga.bogatu.pumpnsmash.descriptionName")){
                case "squat":
                    descriptionTextView.setText(getString(R.string.squat_description));
                    descriptionImageView.setImageResource(R.drawable.squat);
                    break;

                case "hamstringCurls":
                    descriptionTextView.setText(getText(R.string.hamstring_curls_description));
                    descriptionImageView.setImageResource(R.drawable.hamstring_curls);
                    break;

                case  "seatedLegExtensions":
                    descriptionTextView.setText(R.string.leg_extension_description);
                    descriptionImageView.setImageResource(R.drawable.leg_extension);
                    break;

                case "fentesStatiques":
                    descriptionTextView.setText(R.string.fentes_statiques_description);
                    descriptionImageView.setImageResource(R.drawable.fentes_statiques);
                    break;

                case "calfRaise":
                    descriptionTextView.setText(R.string.calf_raise_description);
                    descriptionImageView.setImageResource(R.drawable.calf_raise);
                    break;

                case "curlsBarre":
                    descriptionTextView.setText(R.string.curls_a_la_barre_description);
                    descriptionImageView.setImageResource(R.drawable.curls_a_la_barre);
                    break;

                case "curlsHalteres":
                    descriptionTextView.setText(R.string.curls_halteres_description);
                    descriptionImageView.setImageResource(R.drawable.curls_haltere);
                    break;

                case "extensionsVerticales":
                    descriptionTextView.setText(R.string.extensions_verticales_description);
                    descriptionImageView.setImageResource(R.drawable.extension_verticale);
                    break;

                case "kickback":
                    descriptionTextView.setText(R.string.kickback_description);
                    descriptionImageView.setImageResource(R.drawable.kickback);
                    break;

                case "tirageMenton":
                    descriptionTextView.setText(R.string.tirage_menton_description);
                    descriptionImageView.setImageResource(R.drawable.tirage_menton);
                    break;

                case "benchPress":
                    descriptionTextView.setText(R.string.developpe_couche_description);
                    descriptionImageView.setImageResource(R.drawable.developpe_couche);
                    break;

                case "chestFlyes":
                    descriptionTextView.setText(R.string.chest_flyes_description);
                    descriptionImageView.setImageResource(R.drawable.chest_flyes);
                    break;

                case "pulldown":
                    descriptionTextView.setText(R.string.pulldown_description);
                    descriptionImageView.setImageResource(R.drawable.pulldown);
                    break;

                case "rowingBarre":
                    descriptionTextView.setText(R.string.rowing_barre_description);
                    descriptionImageView.setImageResource(R.drawable.rowing_barre);
                    break;

                case "crunchBench":
                    descriptionTextView.setText(R.string.crunch_description);
                    descriptionImageView.setImageResource(R.drawable.bench_crunch);
                    break;
            }
        }
    }

    @Override
    public Intent getSupportParentActivityIntent(){
        return getParentActivityIntent();
    }

    @Override
    public Intent getParentActivityIntent(){
        Intent i = null;
        String exercice = getIntent().getExtras().getString
                ("com.laurent.goga.bogatu.pumpnsmash.descriptionName");
        if(exercice.equals("squat") || exercice.equals("hamstringCurls") || exercice.equals("seatedLegExtensions")
                || exercice.equals("fentesStatiques") || exercice.equals("calfRaise")){
            i = new Intent(this, EntrainementJambes.class);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        }

        if(exercice.equals("curlsBarre") || exercice.equals("curlsHalteres") || exercice.equals("extensionsVerticales")
                || exercice.equals("kickback") || exercice.equals("tirageMenton")){
            i = new Intent(this, EntrainementBras.class);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        }

        if(exercice.equals("benchPress") || exercice.equals("chestFlyes") || exercice.equals("pulldown")
                || exercice.equals("rowingBarre") || exercice.equals("crunchBench")){
            i = new Intent(this, EntrainementBras.class);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        }
        return i;
    }
}
