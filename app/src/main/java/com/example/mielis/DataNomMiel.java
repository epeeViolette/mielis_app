package com.example.mielis;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

    public class DataNomMiel {

        private String[] nomMiels ;
        private int nbreMiels = 0;
        private static DataNomMiel holder = null;

        public static DataNomMiel getInstance() {
            if(holder==null){
                holder = new DataNomMiel();
            }
            return holder;
        }





    public void setDataNomMiel(String jsonString) {
        try {
            Log.i("Dylan MSG", "debut recup nom miel"+jsonString);

            // Création d'un objet JSON pour stocker les données du tableau
            JSONArray resultat = new JSONArray(jsonString);
            Log.i("Dylan MSG", "longueur de resultat ="+resultat.length());
            nomMiels =  new String[resultat.length()];
            nbreMiels=resultat.length();
            for(int i=0;i<resultat.length();i++){
                JSONObject aa = resultat.getJSONObject(i);
                Log.i("Dylan MSG", " un aa = "+aa);
                //String b = aa.getString("nom_miel");
                String b = aa.getString("nom_miel").toString();
                nomMiels[i]=b;
                Log.i("Dylan MSG", "fin recup nom miel"+b);


            }

            //holder.nomMiels = nomMiel.getString("nom_miel");
            //Log.i("Dylan MSG", "fin recup nom miel"+resultat);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public String getNomMiel(int i){
            return nomMiels[i];
        }

        public int getNbreMiel(){
            return nbreMiels;
        }


}
