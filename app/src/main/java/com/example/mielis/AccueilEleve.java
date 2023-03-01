package com.example.mielis;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;


public class AccueilEleve extends AppCompatActivity {

    private TextView a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil_eleve);

        LinearLayout lin = (LinearLayout) findViewById(R.id.listeNomMiel);

        DataNomMiel dataNomMiel = DataNomMiel.getInstance();
        dataNomMiel.getInstance();

        for (int i=0;i< dataNomMiel.getNbreMiel();i++){
            a = new TextView(this);
            String b = dataNomMiel.getNomMiel(i)+"\n";
            a.setText(b);
            lin.addView(a);

        }


        //private void createTextView() {




       // }
    }


}