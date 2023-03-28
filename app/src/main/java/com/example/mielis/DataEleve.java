package com.example.mielis;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;


public class DataEleve {
    private String idEleve , nomEleve, prenomEleve;
    private static DataEleve holder = null;

    //Constructeur privé pour empécher la création de plusieurs instances
    private DataEleve(){
        idEleve = "";
        nomEleve = "";
        prenomEleve = "" ;
    }

    //Cette fonction doit être appelé pour créer l'objet DataEleve
    //Si une instance exite déjà elle la retourne sinon elle crée une nouvelle instance
    public static DataEleve getInstance() {
        if(holder==null){
            holder = new DataEleve();
        }
        return holder;
    }
    public String getIdEleve() {
        return idEleve;
    }
    public String getNomEleve() {
        return nomEleve;
    }

    public String getPrenomEleve() {
        return prenomEleve;
    }

    public void setDataEleve(String jsonString) {
        try {
            Log.i("Dylan MSG", "debut recup 7777"+jsonString);
            JSONObject eleve = new JSONObject(jsonString);
            holder.idEleve = eleve.getString("id_eleve");
            holder.nomEleve = eleve.getString("nom_eleve");
            holder.prenomEleve = eleve.getString("prenom_eleve");
            Log.i("Dylan MSG", "fin recup");

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

