package com.example.mielis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class SplashScreen extends AppCompatActivity{

    //Adresse complète du fichier php situé sur le serveur qui va executé le script de Login
    //Sur Wampserver :
    // - Ajouter l'écoute du port 8000 : Clic doit -> outils -> ajouter un Listen port à Apache
    // - Ajouter un 2ème VirtualHost pour le site en utilisant le port 8000 (clic gauche -> Vos VirtualHosts -> Gestion VirtualHost
    //Exemple du contenu de virtual host:
    //1er virtual host, pour un accès à partir de l'ordinateur : ServerName : restiloc-ac - Directory : z:/sio2/projets/restiloc/siterestiloc_ac
    //2ème virtual host, pour un accès à partir du smartphone (virtuel ou physique) : ServerName : restiloc-ac:8000 - Directory : z:/sio2/projets/restiloc/siterestiloc_ac
    private static String URL_nom_miels = "http://10.58.7.223:8181/appli/nom_miels.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);


        //Rediriger vers la page principale "MainActivity" après 3s
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                //Démarrer une page en créant un intent et en le redirigeant ver la classe MainActivity
                Intent intent = new Intent(getApplicationContext(), Login.class);
                //Lancer l'intent précédement créé
                startActivity(intent);
                //fonction traitement pour taiter la récupération des données
                traitement();
                //Terminer l'activité en cours (Le Splash)
                finish();
            }
        };
        //Handler
        new Handler().postDelayed(runnable, 3000);
    }

    private void traitement() {



            //Implémenter l'interface Runnable qui va spécifier et encapsuler le code destiné à être exécuté dans un thread
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new Runnable() {
                //Définition de la méthode run() qui contient Le code à exécuter
                @Override
                public void run() {

                    //Créer les clés des paramètres qui seront envoyés au serveur
                    String[] keys = new String[1];
                    keys[0] = "";

                    //Créer les valeurs des paramètres créés précedement
                    String[] values = new String[1];
                    values[0] = "";


                    //Créer les clés des paramètres qui seront envoyés au serveur
                    Log.i("Dylan MSG", "debut run traitement");
                    //Creer l'objet qui va communiquer avec le serveur en lui indiquant l'URL du fichier php à appeler et les données à envoyer ainsi que la methode à utiliser
                    PutData putData = new PutData(URL_nom_miels, "POST",keys,values);
                    //Exécuter le script de communication : startPut() renvoi true si la communication a pu commencer
                    if (putData.startPut()) {
                        Log.i("Dylan MSG", "debut traitement serveur");
                        //Traiter la réponse du serveur
                        if (putData.onComplete()) {
                            Log.i("Dylan MSG", "fin de traitement par le serveur");
                            //Récupérer le résultat renvoyer par le serveur (correspond à la chaine affichée par l'écho réalisé dans la page php appelée
                            String result = putData.getResult();
                            Log.i("Dylan MSG", "Réponse du serveur miel = "+result);
                            //Splitter la chaine reçue en utilisant le séparateur défini dans le fichier php (ici : %)
                            String[] msg = result.split("%");
                            //Si la 1ère partie de la chaine splittée contient "SUCCESS" c'est que l'action a réussie (voir le fichier php)
                            if(msg[0].equals("SUCCESS")){
                                //Afficher un court message sur l'écran : recup réussi
                                Toast.makeText(getApplicationContext(), "recup nom miel reussi", Toast.LENGTH_SHORT).show();
                                //Récupérer la 2ème partie de la chaine splittée : elle contient les données renvoyées par le serveur
                                DataNomMiel dataNomMiel = DataNomMiel.getInstance();
                                dataNomMiel.setDataNomMiel(msg[1]);

                            }else{
                                //Si la 1ère partie de la chaine splitée ne contient pas "SUCCESS" c'est que l'action a échoué (voir le fichier php)
                                //Afficher un court message à l'écran avec l'erreur rencontrée
                                Toast.makeText(getApplicationContext(), msg[1], Toast.LENGTH_SHORT).show();
                            }
                            //Si la réponse du serveur est incomplète afficher l'erreur rencontrée dans la console de LOG pour le débogage
                        }else{Log.i("Dylan MSG", "traitement incomplet");}
                    }else{
                        Log.i("Dylan MSG", "serveur non atteint");
                    }
                }
            });

    }
}
