package com.example.amarc.androidsquare;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class MainActivity extends AppCompatActivity {
    //la somme des lignes et des colonne de notre matrice
    int sommeligne1;    int sommeligne2;    int sommeligne3;
    int sommecolonne1;    int sommecolonne2;    int sommecolonne3;

   //vecteur des numéros autorisés au joueur && notre matrice de jeu
    int[] nombre_a_utiliser = {1,2,3,4,5,6,7,8,9};    int[] jeu = new int[9];
    int nombre_aides = 9;

 //genérer les nombres aléatoirement pour jouer et pour help
    Random rondom1 = new Random();
    Random rondom2 = new Random();

    //Liste des nombres choisis par le joueur && Liste des nombres en appuyant sur help de type array

    List Nombres_choisis = new ArrayList();
    List Nombres_apres_un_help = new ArrayList();
//indice matrice de jeu et  help
    int ind_jeu;
    int ind_help;

    MediaPlayer mymedia1;
//nombre d'aides
    int comptHelp = 0;
//les bouttons du jeu
    Button boutton_newgame;
    Button boutton_submit;
    Button boutton_exit;
    Button boutton_help;
// Les EditTEXT que le joueur va remplir pour jouer , il doit choisir pour chacun un chiffre entre 1 et 9 sans redendance
    EditText edit1;
    EditText edit2;
    EditText edit3;
    EditText edit4;
    EditText edit5;
    EditText edit6;
    EditText edit7;
    EditText edit8;
    EditText edit9;

 //les résultats des lignes et des colonnes de type TextView
    TextView Ligneresultat1;
    TextView Ligneresultat2;
    TextView Ligneresultat3;

    TextView Columneresultat1;
    TextView Columneresultat2;
    TextView Columneresultat3;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
  //Réccuperer tous les id de notre xml
        boutton_submit = (Button) findViewById(R.id.sub);
        boutton_newgame = (Button) findViewById(R.id.NEW);
        boutton_exit = (Button) findViewById(R.id.exit);
        boutton_help = (Button) findViewById(R.id.aide);

        edit1 = (EditText) findViewById(R.id.X00);
        edit2 = (EditText) findViewById(R.id.X02);
        edit3 = (EditText) findViewById(R.id.X04);
        edit4 = (EditText) findViewById(R.id.X20);
        edit5 = (EditText) findViewById(R.id.X22);
        edit6 = (EditText) findViewById(R.id.X24);
        edit7 = (EditText) findViewById(R.id.X40);
        edit8 = (EditText) findViewById(R.id.X42);
        edit9 = (EditText) findViewById(R.id.X44);

        Ligneresultat1 = (TextView) findViewById(R.id.X06);
        Ligneresultat2 = (TextView) findViewById(R.id.X26);
        Ligneresultat3 = (TextView) findViewById(R.id.X46);
        Columneresultat1 = (TextView) findViewById(R.id.X60);
        Columneresultat2 = (TextView) findViewById(R.id.X62);
        Columneresultat3 = (TextView) findViewById(R.id.X64);
//Allouer pour chaque case de notre matrice de jeu les valeurs du joueur
        for(int i=0;i<9;i++){
            int Ind = verifier_ajout();
            int val = nombre_a_utiliser[Ind];
            jeu [i] = val;
        }
   //sommer des lignes et les colomnes
        sommeligne1 = jeu [0]+jeu [1]+jeu [2];
        sommeligne1 = jeu [3]+jeu [4]+jeu [5];
        sommeligne1 = jeu [6]+jeu [7]+jeu [8];

        sommecolonne1 = jeu [0]+jeu [3]+jeu [6];
        sommecolonne2 = jeu [1]+jeu [4]+jeu [7];
        sommecolonne3 = jeu [2]+jeu [5]+jeu [8];

        Ligneresultat1.setText(""+sommeligne1);
        Ligneresultat2.setText(""+sommeligne2);
        Ligneresultat3.setText(""+sommeligne3);

        Columneresultat1.setText(""+sommecolonne1);
        Columneresultat2.setText(""+sommecolonne2);
        Columneresultat3.setText(""+sommecolonne3);

        boutton_newgame.setEnabled(false);

        boutton_submit.setOnClickListener(new Submit());
        boutton_newgame.setOnClickListener(new Newgame());
        boutton_exit.setOnClickListener(new Exit());
        boutton_help.setOnClickListener(new Help());

    }


    class Submit implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            // VERIFICATION DES CASES
            if(!edit1.getText().toString().equals("") && !edit2.getText().toString().equals("") &&
                    !edit3.getText().toString().equals("") && !edit4.getText().toString().equals("") &&
                    !edit5.getText().toString().equals("") && !edit6.getText().toString().equals("") &&
                    !edit7.getText().toString().equals("") && !edit8.getText().toString().equals("") &&
                    !edit9.getText().toString().equals("")){
                // si 0 et dans la matrice on affiche un message d'erreur

                if(Les_nombres() == 0){
                    message("Le zéro est intérdit dans le jeu ");


                }
                //verifier la redendances des valeurs dans la matrice
                else if(Les_nombres() == 1){
                    message("Il faut soumettre des valeur une fois");
                }
                else {
                    // réussi
                    if(Reussi()){
                        boutton_newgame.setEnabled(true);
                        message("Réussi Bravo");
                        boutton_help.setEnabled(false);

                    }
                    // sinon echoué
                    else{
                        message("Echoué,Retenter votre chance");
                        boutton_newgame.setEnabled(true);
                    }
                }
            }else{
                message("Completer toutes les cases du jeu");
            }
        }
    }

    class Exit implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            //finish();
            System.exit(0);
        }
    }

    class Newgame implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(i);
            finish();
        }
    }


    class Help implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            comptHelp++;
            int index = getindice();
            switch (index){
                case 0 :
                    edit1.setText(""+jeu [0]);
                    edit1.setEnabled(false);

                    break;
                case 1 :
                    edit2.setText(""+jeu [1]);
                    edit2.setEnabled(false);

                    break;
                case 2 :
                    edit3.setText(""+jeu [2]);
                    edit3.setEnabled(false);

                    break;
                case 3 :
                    edit4.setText(""+jeu [3]);
                    edit4.setEnabled(false);

                    break;
                case 4 :
                    edit5.setText(""+jeu [4]);
                    edit5.setEnabled(false);

                    break;
                case 5 :
                    edit6.setText(""+jeu [5]);
                    edit6.setEnabled(false);

                    break;
                case 6 :
                    edit7.setText(""+jeu [6]);
                    edit7.setEnabled(false);

                    break;
                case 7 :
                    edit8.setText(""+jeu [7]);
                    edit8.setEnabled(false);

                    break;
                case 8 :
                    edit9.setText(""+jeu [8]);
                    edit9.setEnabled(false);
                    break;
                default:
                    Log.i("Erreur","Valeur non correspondante");
                    break;
            }
            nombre_aides--;
            boutton_help.setText("HELP : "+nombre_aides);
            if(nombre_aides == 0){
                boutton_help.setEnabled(false);
            }
        }
    }

    //Quand le joueur choisit un nombre on l'ajoute a une  liste de type array sans la redendance
    public int verifier_ajout(){
        int x = rondom1.nextInt(9);

        if(Nombres_choisis.contains(x)){
            verifier_ajout();
        }else{
            ind_jeu = x;
            Nombres_choisis.add(x);
        }
        return ind_jeu;
    }
//Quand le joueur a besoin d'un help, on ajoute le nombre donné par le help à une liste, en verifiant la redendance

    public int getindice(){

        int x= rondom2.nextInt(9);
        if(Nombres_apres_un_help .contains(x)){
            getindice();
        }else{
            ind_help = x;

            Nombres_apres_un_help .add(x);
        }
        return ind_help;
    }

    public int Les_nombres(){
        int a1 = Integer.parseInt(edit1.getText().toString());
        int a2 = Integer.parseInt(edit2.getText().toString());
        int a3 = Integer.parseInt(edit3.getText().toString());
        int a4 = Integer.parseInt(edit4.getText().toString());
        int a5 = Integer.parseInt(edit5.getText().toString());
        int a6 = Integer.parseInt(edit6.getText().toString());
        int a7 = Integer.parseInt(edit7.getText().toString());
        int a8 = Integer.parseInt(edit8.getText().toString());
        int a9 = Integer.parseInt(edit9.getText().toString());
        int[] numbers = {a1,a2,a3,a4,a5,a6,a7,a8,a9};

        for(int i=0;i<9;i++){
            if(numbers[i] == 0){
                return 0;
            }
        }
        for(int i=0;i<(9-1);i++){
            for(int j=i+1;j<9;j++){
                if(numbers[i] == numbers[j]){
                    return 1;
                }
            }
        }
        return 2;
    }


//méthode qui retourne un type booleen , true si les sommes des lignes et des colonnes egalent au résultats des lignes et des colonnes sinon elle retourne false
    public boolean Reussi(){
        int a1 = Integer.parseInt(edit1.getText().toString());
        int a2 = Integer.parseInt(edit2.getText().toString());
        int a3 = Integer.parseInt(edit3.getText().toString());
        int a4 = Integer.parseInt(edit4.getText().toString());
        int a5 = Integer.parseInt(edit5.getText().toString());
        int a6 = Integer.parseInt(edit6.getText().toString());
        int a7 = Integer.parseInt(edit7.getText().toString());
        int a8 = Integer.parseInt(edit8.getText().toString());
        int a9 = Integer.parseInt(edit9.getText().toString());

        if(a1 == jeu [0] && a2 == jeu [1] && a3 == jeu [2]
                && a4 == jeu [3] && a5 == jeu [4] && a6 == jeu [5]
                && a7 == jeu [6] && a8 == jeu [7] && a9 == jeu [8]){
            return true;
        }else{
            return false;
        }
    }

    public void message(String alert){
        AlertDialog msg = new AlertDialog.Builder(MainActivity.this).create();
        msg.setMessage(alert);
        msg.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        msg.show();
    }

}




















