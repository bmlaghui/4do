package com.example.a4do;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class MainActivity extends AppCompatActivity {
    TextView questionLabel, questionCountLabel, txtBtn1,txtBtn2,txtBtn3,txtBtn4,txtTrack ;
    ImageButton btnRep1, btnRep2, btnRep3, btnRep4;
    ProgressBar progressBar;
    Button btnTrack;
    ArrayList<QuestionModel> questionModelArraylist;
    String[] answers = new String[4];
    int currentPosition = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        questionCountLabel = findViewById(R.id.noQuestion);
        questionLabel = findViewById(R.id.question);
        btnRep1 = findViewById(R.id.btnRep1);
        btnRep2 = findViewById(R.id.btnRep2);
        btnRep3 = findViewById(R.id.btnRep3);
        btnRep4 = findViewById(R.id.btnRep4);
        progressBar = findViewById(R.id.progress);
        txtBtn1 = findViewById(R.id.txtRep1);
        txtBtn2 = findViewById(R.id.txtRep2);
        txtBtn3 = findViewById(R.id.txtRep3);
        txtBtn4 = findViewById(R.id.txtRep4);
        txtTrack = findViewById(R.id.txtTrack);
        btnTrack = findViewById(R.id.btnTrack);
        questionModelArraylist = new ArrayList<>();
        setUpQuestion();

        setData();
        btnRep1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(btnRep1);
            }
        });
        btnRep2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {  checkAnswer(btnRep2);
            }
        });
        btnRep3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkAnswer(btnRep3);
            }
        });
        btnRep4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkAnswer(btnRep4);
            }
        });

        btnRep3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkAnswer(btnRep3);
            }
        });
        btnTrack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, Track.class);
                i.putExtra("nroLivraison",txtTrack.getText().toString());
                startActivity(i);
            }
        });
    }
    public void checkAnswer(ImageButton btn){
        int id = btn.getId();

                String resourceName = getResources().getResourceEntryName(id);
                answers[currentPosition]=resourceName;

        int x = ((currentPosition+1) * 100) / questionModelArraylist.size();

        progressBar.setProgress(x);
        currentPosition ++;

        setData();

    }
    public void setUpQuestion(){
        questionModelArraylist.add(new QuestionModel("Le cadeau c'est pour ? ","Homme", "Femme", "Enfant", "Couple"));
        questionModelArraylist.add(new QuestionModel("Pour quel Age?  ","Bébé", "Enfant", "Adulte", "Vieux(lle)"));
        questionModelArraylist.add(new QuestionModel("C'est plutôt pour ? ", "Ami(e)", "Collègue", "Conjoint(e)", "Famille"));
        questionModelArraylist.add(new QuestionModel("Quel est votre Budget ? ","<30 €", "30-50 €", "50-100 €",">100 €"));
    }

    public void setData(){
        if(questionModelArraylist.size()>currentPosition) {
            switch (currentPosition) {
                case 0:
                    btnRep1.setImageResource(R.drawable.homme);
                    btnRep2.setImageResource(R.drawable.femme);
                    btnRep3.setImageResource(R.drawable.baby);
                    btnRep4.setImageResource(R.drawable.couple);
                    break;
                case 1:
                    btnRep1.setImageResource(R.drawable.baby);
                    btnRep2.setImageResource(R.drawable.child);
                    btnRep3.setImageResource(R.drawable.adult);
                    btnRep4.setImageResource(R.drawable.old);
                    break;
                case 2:
                    btnRep1.setImageResource(R.drawable.friend);
                    btnRep2.setImageResource(R.drawable.collegue);
                    btnRep3.setImageResource(R.drawable.love);
                    btnRep4.setImageResource(R.drawable.famille);
                    break;

                case 3:
                    btnRep1.setImageResource(R.drawable.money1);
                    btnRep2.setImageResource(R.drawable.money2);
                    btnRep3.setImageResource(R.drawable.money3);
                    btnRep4.setImageResource(R.drawable.money4);
                    break;
            }
            questionLabel.setText(questionModelArraylist.get(currentPosition).getQuestion());
            txtBtn1.setText(questionModelArraylist.get(currentPosition).getReponse1());
            txtBtn2.setText(questionModelArraylist.get(currentPosition).getReponse2());
            txtBtn3.setText(questionModelArraylist.get(currentPosition).getReponse3());
            txtBtn4.setText(questionModelArraylist.get(currentPosition).getReponse4());



            questionCountLabel.setText("Question N° : " + (currentPosition + 1));
        }else{
            new SweetAlertDialog(MainActivity.this, SweetAlertDialog.SUCCESS_TYPE)
                    .setTitleText("Bravo! Questionnaire terminé")
                    .setContentTextSize(25)
                    .setConfirmText("Catalogue")
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            sDialog.dismissWithAnimation();
                            //envoyer données par json
                            Intent in = new Intent(MainActivity.this, MainCadeauActivity.class);
                            in.putExtra("reponses",answers);
                            startActivity(in);

                        }
                    })
                    .show();
        }

    }

}
