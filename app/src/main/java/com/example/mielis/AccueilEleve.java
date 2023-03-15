package com.example.mielis;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;


public class AccueilEleve extends AppCompatActivity {

    private TextView a;

    private EditText Qte;

    private Button BtnFin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil_eleve);

        LinearLayout lin = (LinearLayout) findViewById(R.id.listeNomMiel);


        DataNomMiel dataNomMiel = DataNomMiel.getInstance();
        dataNomMiel.getInstance();

        for (int i=0;i< dataNomMiel.getNbreMiel();i++){
            a = new TextView(this);
            String b = dataNomMiel.getNomMiel(i);
            a.setText(b);
            lin.addView(a);

            Qte = new EditText(this);
            Qte.setHint("0");
            Qte.setInputType(InputType.TYPE_CLASS_NUMBER);
            lin.addView(Qte);


        }


        //Qte = findViewById();

        BtnFin = findViewById(R.id.BtnFin);





       // }
    }


}