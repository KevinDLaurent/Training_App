package com.laurent.goga.bogatu.pumpnsmash;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Formulaire1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulaire1);

        Button suivantF1Button = (Button) findViewById(R.id.suivantF1Button);
        Button quitterF1Button = (Button) findViewById(R.id.quitterF1Button);

        suivantF1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotoFormulaire2 = new Intent(getApplicationContext(),Formulaire2.class);
                startActivity(gotoFormulaire2);
            }
        });

        //pour fermer une app en android on le fait depuis l os et non a l interieur de l app
        //ce code envoie l app en background
        // pas certain si c est utile d avoir un bouton quitter dans ce cas???????????????????
        //https://stackoverflow.com/questions/3226495/how-to-exit-from-the-application-and-show-the-home-screen
        quitterF1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homeIntent = new Intent(Intent.ACTION_MAIN);
                homeIntent.addCategory( Intent.CATEGORY_HOME );
                homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(homeIntent);
            }
        });
    }
}
