package com.example.cinequiz.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cinequiz.R;
import com.example.cinequiz.model.User;

public class MainActivity extends AppCompatActivity {

   private TextView mGreetingText;
   private TextView mVersion;
   private EditText mNameInput;
   private Button mPlayButton;
   private User mUser;
   private ImageView mImage;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        System.out.println("MainActivity::OnCreate()");

        mUser= new User();
        mGreetingText = findViewById(R.id.activity_main_greeting_txt);
        mNameInput = findViewById(R.id.activity_main_name_input);
        mPlayButton =findViewById(R.id.activity_main_play_button);
        mImage = findViewById(R.id.activity_main_img);
        mVersion=findViewById(R.id.activity_main_version);

        mPlayButton.setEnabled(false);

        mNameInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
              mPlayButton.setEnabled(charSequence.toString().length() !=0);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        mPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstname =mNameInput.getText().toString();
                mUser.setFirstName(firstname);
                //User clicked the button
                Intent gameActivityIntent = new Intent(MainActivity.this, GameActivity.class);
                startActivity(gameActivityIntent);



            }
        });
    }



    @Override
    protected void onStart() {
        super.onStart();

        System.out.println("MainActivity::OnStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();

        System.out.println("MainActivity::OnResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();

        System.out.println("MainActivity::OnPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();

        System.out.println("MainActivity::OnStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        System.out.println("MainActivity::OnDestroy()");
    }
}