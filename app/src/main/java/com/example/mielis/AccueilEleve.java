package com.example.mielis;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.vishnusivadas.advanced_httpurlconnection.PutData;


public class AccueilEleve extends AppCompatActivity {

    private EditText nomClient;
    private TextView a;

    private EditText Qte;

    private Button BtnFin;
    private EditText q;

    //Adresse complète du fichier php situé sur le serveur qui va executé le script de Login
    //Sur Wampserver :
    // - Ajouter l'écoute du port 8000 : Clic doit -> outils -> ajouter un Listen port à Apache
    // - Ajouter un 2ème VirtualHost pour le site en utilisant le port 8000 (clic gauche -> Vos VirtualHosts -> Gestion VirtualHost
    //Exemple du contenu de virtual host:
    //1er virtual host, pour un accès à partir de l'ordinateur : ServerName : restiloc-ac - Directory : z:/sio2/projets/restiloc/siterestiloc_ac
    //2ème virtual host, pour un accès à partir du smartphone (virtuel ou physique) : ServerName : restiloc-ac:8000 - Directory : z:/sio2/projets/restiloc/siterestiloc_ac
    private static String URL = "http://10.58.7.223:8181/appli/accueil_eleve.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil_eleve);

        LinearLayout lin = (LinearLayout) findViewById(R.id.listeNomMiel);


        DataNomMiel dataNomMiel = DataNomMiel.getInstance();
        dataNomMiel.getInstance();

        int nbreMiels = dataNomMiel.getNbreMiel();
        Log.i("Dylan MSG","nbreMiels:"+nbreMiels);
        for (int i=0;i<nbreMiels;i++){
            a = new TextView(this);
            String b = dataNomMiel.getNomMiel(i);
            a.setText(b);
            lin.addView(a);
            Log.i("Dylan MSG","b:"+b);
            Qte = new EditText(this);
            Qte.setHint("0 miel");
            Qte.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
            Qte.setInputType(InputType.TYPE_CLASS_NUMBER);
            Qte.setId(i+1);
            lin.addView(Qte);
            Log.i("Dylan MSG","i:"+i);


        }




        //Associer les propriétés de la classe aux éléments visuels de l'interface
        nomClient = findViewById(R.id.PTNomClient);
        BtnFin = findViewById(R.id.BtnFin);



        //Définir l'évenement onClickListner sur le bouton
        BtnFin.setOnClickListener(new View.OnClickListener() {
            //Surcharger la fonction onClick
            @Override
            public void onClick(View view) {

                //Récupérer les contenus des EditText de l'interface visuelle
                String NomClient = nomClient.getText().toString().trim();


                //nouvel instance dans DataNomMiel
                DataNomMiel dnm = DataNomMiel.getInstance();

                //je recupere le nombre de miels
                int nbreMiels = dnm.getNbreMiel();
                //je recupere les id de miel dans un tableau de chaine
                String[] idMiels = dnm.getIdMiel();

                String[] keys = new String[nbreMiels+1];
                keys[0] = "NomClient";
                for(int i=1;i<=nbreMiels;i++){
                    keys[i] = idMiels[i-1];
                }


                String[] values = new String[nbreMiels+1];

                values[0] = nomClient.getText().toString().trim();
                for(int i=1;i<=nbreMiels;i++){
                    q = findViewById(i);
                    values[i] = q.getText().toString().trim();
                    Log.i("Dylan MSG:", keys[i]+"  "+values[i]);

                }






                //Creer l'objet qui va communiquer avec le serveur en lui indiquant l'URL du fichier php à appeler et les données à envoyer ainsi que la methode à utiliser
                PutData putData = new PutData(URL, "POST", keys, values);

                if (putData.startPut()) {
                    Log.i("Dylan MSG", "debut");
                    //Traiter la réponse du serveur
                    if (putData.onComplete()) {
                        String result = putData.getResult();
                        Log.i("Dylan MSG", "completer  "+result);

                    }else{Log.i("Dylan MSG", "pas completdylan");}
                }
            }

        });



       }






}