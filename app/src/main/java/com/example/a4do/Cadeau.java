package com.example.a4do;
import android.content.Context;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;

public class Cadeau {

    public String nomCadeau;
    public String prixCadeau;
    public String imageCadeau;
    public String descriptionCadeau;

    public static ArrayList<Cadeau> getCadeauFromFile(Context context){
        final ArrayList<Cadeau> cadeauList = new ArrayList<>();

        try {
            // Load data json from asset directory
            String jsonString = loadJsonFromAsset("cadeau.json", context);
            JSONObject json = new JSONObject(jsonString); //transform jsonstrin in json object

            JSONArray gifts = json.getJSONArray("gifts");// recupere en tableau

            // Get Recipe objects from data
            for(int i = 0; i < gifts.length(); i++){
                Cadeau cadeau = new Cadeau();

                cadeau.nomCadeau = gifts.getJSONObject(i).getString("nomCadeau");
                cadeau.prixCadeau = gifts.getJSONObject(i).getString("prixCadeau");
                cadeau.imageCadeau = gifts.getJSONObject(i).getString("imageCadeau");
                cadeau.descriptionCadeau = gifts.getJSONObject(i).getString("descriptionCadeau");

                cadeauList.add(cadeau);// instancie des objets de classe Recipe en une liste
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return cadeauList;//list d'objet de classe recipe
    }

    private static String loadJsonFromAsset(String filename, Context context) {
        String json = null;

        try {
            InputStream is = context.getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        }
        catch (java.io.IOException ex) {
            ex.printStackTrace();
            return null;
        }

        return json;
    }


}
