package com.example.cinequiz.controller;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cinequiz.R;
import com.example.cinequiz.model.Question;
import com.example.cinequiz.model.QuestionBank;

import java.util.Arrays;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mQuestionTextView;
    private Button mAnswerButton1;
    private Button mAnswerButton2;
    private Button mAnswerButton3;
    private Button mAnswerButton4;

    private QuestionBank mQuestionBank;
    private Question mCurrentQuestion;

    private int mScore;
    private int mNumberOfQuestions;

    private boolean mEnableTouchEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        System.out.println("GameActivity::OnCreate()");

        mQuestionBank = this.generateQuestions();

        mScore = 0;
        mNumberOfQuestions = 4;
        mEnableTouchEvents = true;


        //Wire widget
        mQuestionTextView = findViewById(R.id.activity_game_questions_txt);
        mAnswerButton1 = findViewById(R.id.activity_game_answer1_btn);
        mAnswerButton2 = findViewById(R.id.activity_game_answer2_btn);
        mAnswerButton3 = findViewById(R.id.activity_game_answer3_btn);
        mAnswerButton4 = findViewById(R.id.activity_game_answer4_btn);

        //Use the tag property to 'name' the buttons
        mAnswerButton1.setTag(0);
        mAnswerButton2.setTag(1);
        mAnswerButton3.setTag(2);
        mAnswerButton4.setTag(3);

        mAnswerButton1.setOnClickListener(this);
        mAnswerButton2.setOnClickListener(this);
        mAnswerButton3.setOnClickListener(this);
        mAnswerButton4.setOnClickListener(this);

        mCurrentQuestion = mQuestionBank.getQuestion();
        this.displayQuestion(mCurrentQuestion);

    }

    @Override
    public void onClick(View view) {
        int responseIndex = (int) view.getTag();

        if (responseIndex == mCurrentQuestion.getAnswerIndex()) {
            //Good Answer
            Toast.makeText(this, "Exact !", Toast.LENGTH_SHORT).show();
            mScore++;
            //Wrong Answer
        } else {
            Toast.makeText(this, "C'est faux !", Toast.LENGTH_SHORT).show();
        }

        mEnableTouchEvents = false;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mEnableTouchEvents = true;
                //if this is the last question, end the game.
                //Else, display the next question.
            }
        },2000); //LENGTH SHORT 2 secs long

        if (--mNumberOfQuestions == 0) {
            //End the game
            endGame();
        } else {
            mCurrentQuestion = mQuestionBank.getQuestion();
            displayQuestion(mCurrentQuestion);
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return mEnableTouchEvents && super.dispatchTouchEvent(ev);
    }

    private void endGame() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        if (mScore == 0) {
            builder.setTitle("Aie Aie Aie !");
            builder.setMessage("Ton score est de " + mScore + "\nCultive toi un peu et reviens tenter ta chance !");
        } else if (mScore == 1) {
            builder.setTitle("Oupsi !");
            builder.setMessage("Ton score est de " + mScore + "\nTu feras mieux la prochaine fois !");
        } else if (mScore >= 2) {
            builder.setTitle("Bien joué");
            builder.setMessage("Ton score est de " + mScore + "\nBravo !");
        }

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // End of activity
                finish();
            }
        })
         .create()
         .show();

    }

    private void displayQuestion(final Question question) {
        mQuestionTextView.setText(question.getQuestion());
        mAnswerButton1.setText(question.getChoiceList().get(0));
        mAnswerButton2.setText(question.getChoiceList().get(1));
        mAnswerButton3.setText(question.getChoiceList().get(2));
        mAnswerButton4.setText(question.getChoiceList().get(3));

    }


    private QuestionBank generateQuestions() {
        Question question1 = new Question("Quel est le seul acteur français à avoir eu l'Oscar du 'meilleur acteur' ?",
                Arrays.asList("Gerard Depardieu", "Christophe Lambert", "Jean Dujardin", "Christian Clavier"),
                2);

        Question question2 = new Question("Quel est le premier film réalisé par Quentin Tarantino ?",
                Arrays.asList("Pulp Fiction", "Reservoir Dogs", "Kill Bill", "Boulevard de la mort"),
                1);

        Question question3 = new Question("Qui s'est fait giflé pour une mauvaise blague lors des Oscars 2022 ?",
                Arrays.asList("The Rock", "Denzel Washington", "Will Smith", "Chris Rock"),
                3);

        Question question4 = new Question("Qui a gagné l'Oscar du 'meilleur acteur dans un second role' pour son role dans Inglorious Bastards ?",
                Arrays.asList("Brad Pitt", "Christoph Waltz", "Michael Fassbender", "Daniel Brühl"),
                1);



        return new QuestionBank(Arrays.asList(question1,
                                              question2,
                                              question3,
                                              question4));

    }

    @Override
    protected void onStart() {
        super.onStart();

        System.out.println("GameActivity::OnStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();

        System.out.println("GameActivity::OnResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();

        System.out.println("GameActivity::OnPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();

        System.out.println("GameActivity::OnStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        System.out.println("GameActivity::OnDestroy()");
    }
}
