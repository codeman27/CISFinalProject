package com.example.cody.asteroidassaultgame;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class TopScoresActivity extends Activity {

    DatabaseHelper mDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_scores);

        mDatabaseHelper = new DatabaseHelper(this);

        populateListView();
    }

    private void populateListView() {

        Cursor data = mDatabaseHelper.getData();
        ArrayList<String> nameData = new ArrayList<>();
        ArrayList<String> scoreData = new ArrayList<>();

        // WARNING -- HORRIBLE CODE BELOW -- CONTINUE AT YOUR OWN PERIL
        // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!


        TextView nameText1 = findViewById(R.id.nameText);
        TextView scoreText1 = findViewById(R.id.scoreText);
        TextView nameText2 = findViewById(R.id.nameText1);
        TextView scoreText2 = findViewById(R.id.scoreText1);
        TextView nameText3 = findViewById(R.id.nameText2);
        TextView scoreText3 = findViewById(R.id.scoreText2);
        TextView nameText4 = findViewById(R.id.nameText3);
        TextView scoreText4 = findViewById(R.id.scoreText3);
        TextView nameText5 = findViewById(R.id.nameText4);
        TextView scoreText5 = findViewById(R.id.scoreText4);
        TextView nameText6 = findViewById(R.id.nameText5);
        TextView scoreText6 = findViewById(R.id.scoreText5);
        TextView nameText7 = findViewById(R.id.nameText6);
        TextView scoreText7 = findViewById(R.id.scoreText6);
        TextView nameText8 = findViewById(R.id.nameText7);
        TextView scoreText8 = findViewById(R.id.scoreText7);
        TextView nameText9 = findViewById(R.id.nameText8);
        TextView scoreText9 = findViewById(R.id.scoreText8);
        TextView nameText10 = findViewById(R.id.nameText9);
        TextView scoreText10 = findViewById(R.id.scoreText9);
        TextView nameText11 = findViewById(R.id.nameText10);
        TextView scoreText11 = findViewById(R.id.scoreText10);
        TextView nameText12 = findViewById(R.id.nameText11);
        TextView scoreText12 = findViewById(R.id.scoreText11);
        TextView nameText13 = findViewById(R.id.nameText12);
        TextView scoreText13 = findViewById(R.id.scoreText12);
        TextView nameText14 = findViewById(R.id.nameText13);
        TextView scoreText14 = findViewById(R.id.scoreText13);
        TextView nameText15 = findViewById(R.id.nameText14);
        TextView scoreText15 = findViewById(R.id.scoreText14);

        while(data.moveToNext()){
            nameData.add(data.getString(1));
            scoreData.add(data.getString(2));
        }
        switch (nameData.size()) {
            case 0:
                nameText1.setText("No Scores Yet, time to play!");
                break;
            case 1:
                nameText1.setText(nameData.get(0));
                scoreText1.setText(scoreData.get(0));
                break;
            case 2:
                nameText2.setText(nameData.get(1));
                scoreText2.setText(scoreData.get(1));
                break;
            case 3:
                nameText3.setText(nameData.get(2));
                scoreText3.setText(scoreData.get(2));
                break;
            case 4:
                nameText4.setText(nameData.get(3));
                scoreText4.setText(scoreData.get(3));
                break;
            case 5:
                nameText5.setText(nameData.get(4));
                scoreText5.setText(scoreData.get(4));
                break;
            case 6:
                nameText6.setText(nameData.get(5));
                scoreText6.setText(scoreData.get(5));
                break;
            case 7:
                nameText7.setText(nameData.get(6));
                scoreText7.setText(scoreData.get(6));
                break;
            case 8:
                nameText8.setText(nameData.get(7));
                scoreText8.setText(scoreData.get(7));
                break;
            case 9:
                nameText9.setText(nameData.get(8));
                scoreText9.setText(scoreData.get(8));
                break;
            case 10:
                nameText10.setText(nameData.get(9));
                scoreText10.setText(scoreData.get(9));
                break;
            case 11:
                nameText11.setText(nameData.get(10));
                scoreText11.setText(scoreData.get(10));
                break;
            case 12:
                nameText12.setText(nameData.get(11));
                scoreText12.setText(scoreData.get(11));
                break;
            case 13:
                nameText13.setText(nameData.get(12));
                scoreText13.setText(scoreData.get(12));
                break;
            case 14:
                nameText14.setText(nameData.get(13));
                scoreText14.setText(scoreData.get(13));
                break;
            case 15:
                nameText15.setText(nameData.get(14));
                scoreText15.setText(scoreData.get(14));
                break;
        }
    }
}
