package com.example.amarc.androidsquare;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.net.Uri;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class Mainaccueil extends AppCompatActivity {
    Button boutton_concept;
    Button boutton_commencer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accueil);


    }

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        boutton_concept = (Button) findViewById(R.id.concept);
        boutton_commencer = (Button) findViewById(R.id.commencer);
        boutton_concept.setOnClickListener(new concepts());
        boutton_commencer.setOnClickListener(new commencer());
        // l'orientation
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {

            Toast.makeText(this, "Paysage", Toast.LENGTH_SHORT).show();
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {

            Toast.makeText(this, "Portrait", Toast.LENGTH_SHORT).show();
        }


    }
    class concepts implements View.OnClickListener {
        @Override
        public void onClick(View v) {


            String url = "https://en.wikipedia.org/wiki/Magic_square";
            Intent NaviAbout = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(NaviAbout);
        }
    }
    class commencer implements View.OnClickListener {
        @Override
        public void onClick(View v) {


            Intent mag = new Intent(Mainaccueil.this, MainActivity.class);
            startActivity(mag);
        }


    }}


