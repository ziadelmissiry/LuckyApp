package com.example.luckyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class LuckyNumberActivity extends AppCompatActivity {

    TextView welcomeText, luckyNumberTxt;
    Button shareBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lucky_number);

        shareBtn = findViewById(R.id.sharebtn);
        welcomeText = findViewById(R.id.titletxt);
        luckyNumberTxt = findViewById(R.id.luckyNubertxt);

        //username
        Intent i = getIntent();
        String userName = i.getStringExtra("name");
        Toast.makeText(this, "Username: "+userName, Toast.LENGTH_SHORT).show();

        //random number
        int random_num = generateRandomNumber();

        luckyNumberTxt.setText(""+ random_num);


        shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareData(userName, random_num);
            }
        });






    }

    public int generateRandomNumber(){
        Random random = new Random();
        int upper_limt = 1000;

        int randomNumberGenerated = random.nextInt(upper_limt);
        return randomNumberGenerated;
    }

    public void shareData(String username, int randonNumber){
        //implicit Intent
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("text/plain");

        //convert the int to String
        String number = String.valueOf(randonNumber);



        i.putExtra(Intent.EXTRA_SUBJECT, username + " got lucky today!");//title of email
        i.putExtra(Intent.EXTRA_TEXT, "they lucky number is "+ number);

        startActivity(Intent.createChooser(i,"Choose a platform"));


    }
}