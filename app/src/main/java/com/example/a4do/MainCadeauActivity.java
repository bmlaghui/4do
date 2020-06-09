package com.example.a4do;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainCadeauActivity extends AppCompatActivity {
    private ListView mListView;
    Button btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_cadeau);


        mListView = (ListView) findViewById(R.id.cadeau_list_view);  // lien en java et XML via le R.java

       final ArrayList<Cadeau> cadeauList = Cadeau.getCadeauFromFile(this);  // recupere un arraylist d'objet de classe recipe
       /* String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
                "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
                "Linux", "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux",
                "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux", "OS/2",
                "Android", "iPhone", "WindowsMobile" };

        final ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < values.length; ++i) {
            list.add(values[i]);
        }
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, list);
*/

       CadeauAdapter adapter = new CadeauAdapter(this, cadeauList);
         mListView.setAdapter(adapter);

        final Context context = this;


        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 1
                Cadeau selectedCadeau = cadeauList.get(position);

                // 2
                Intent detailIntent = new Intent(context, CadeauDetailActivity.class);

                // 3
                detailIntent.putExtra("nomCadeau", selectedCadeau.nomCadeau);
                detailIntent.putExtra("prixCadeau", selectedCadeau.prixCadeau);
                detailIntent.putExtra("imageCadeau", selectedCadeau.imageCadeau);
                detailIntent.putExtra("descriptionCadeau", selectedCadeau.descriptionCadeau);



                // 4
                startActivity(detailIntent);
            }

        });


       /* btnBack= findViewById(R.id.btnRetour);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainCadeauActivity.this, MainActivity.class);
                startActivity(i);
            }
        });*/


    }


}


