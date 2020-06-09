package com.example.a4do;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class CadeauDetailActivity extends AppCompatActivity {
TextView nomCadeau, prixCadeau, descriptionCadeau;
ImageView imageCadeau;
Button btnRetour,btnOrder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadeau_detail);
        nomCadeau= findViewById(R.id.nomCadeau);
        prixCadeau= findViewById(R.id.prixCadeau);
        descriptionCadeau= findViewById(R.id.descriptionCadeau);
        imageCadeau= findViewById(R.id.imageCadeau);


        Intent intent = getIntent();
        String nom = intent.getStringExtra("nomCadeau");
        String prix = intent.getStringExtra("prixCadeau");
        String description = intent.getStringExtra("descriptionCadeau");
        String image = intent.getStringExtra("imageCadeau");

        nomCadeau.setText(nom);
        prixCadeau.setText(prix+" €");
        descriptionCadeau.setText(description);
        Picasso.with(this).load(image).placeholder(R.mipmap.ic_launcher).into(imageCadeau);


        btnRetour= findViewById(R.id.btnRetour);
        btnOrder= findViewById(R.id.btnOrder);


        btnRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(CadeauDetailActivity.this, MainCadeauActivity.class);
                startActivity(i);
            }
        });

        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new SweetAlertDialog(CadeauDetailActivity.this, SweetAlertDialog.SUCCESS_TYPE)
                        .setTitleText("Félicitations! Votre commande a été bien prise en charge :)")
                        .setContentTextSize(25)
                        .setConfirmText("Retour")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                sDialog.dismissWithAnimation();
                                finish();
                                System.exit(0);
                            }
                        })

                        .show();
            }
        });




    }
}
