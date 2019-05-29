package com.laurent.goga.bogatu.pumpnsmash;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private Boolean notStart =false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPref = getSharedPreferences
                ("com.laurent.goga.bogatu.pumpnsmash", MODE_PRIVATE);
        if (sharedPref.contains("com.laurent.goga.bogatu.pumpnsmash.menu")) {
            Intent gotoMenuPrincipal = new Intent(getApplicationContext(), MenuPrincipal.class);
            startActivity(gotoMenuPrincipal);
            finish();
        } else {
            Intent gotoFormulaire1 = new Intent(getApplicationContext(), Formulaire1.class);
            startActivity(gotoFormulaire1);
            finish();
        }
    }
}
