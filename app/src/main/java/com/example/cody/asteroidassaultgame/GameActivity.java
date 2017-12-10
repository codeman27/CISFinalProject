package com.example.cody.asteroidassaultgame;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Point;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.InputFilter;
import android.text.InputType;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.EditText;

public class GameActivity extends Activity {

    GameEngine gameEngine;
    private String m_Text = "";
    DatabaseHelper databaseHelper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // Get a Display object to access screen details
        Display display = getWindowManager().getDefaultDisplay();
        //Load the resolution into a Point object
        Point size = new Point();
        display.getSize(size);

        //Initialize gameView and set it as the view
        gameEngine = new GameEngine(this, size.x, size.y, GameActivity.this);
        setContentView(gameEngine);

    }

    //This method executes when the player starts the game
    @Override
    protected void onResume(){
        super.onResume();

        //Tell the gameView resume method to execute
        gameEngine.resume();
    }

    // This method executes when the player quits the game
    @Override
    protected void onPause(){
        super.onPause();
        // Tell the gameView pause method to execute
        gameEngine.pause();
    }

    public void gameOverDialog(final int score){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Score: " + score);
        builder.setCancelable(false);

        // Set up the input
        final EditText input = new EditText(this);
        // Specify the type of input expected
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        input.setFilters( new InputFilter[] {new InputFilter.LengthFilter(3), new InputFilter.AllCaps()});
        builder.setView(input);

        // Set up button
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                if(input.length() > 0) {
//                    m_Text = input.getText().toString();
//                    finish();
//                    AddData(m_Text);
//                }
//                Boolean wantToCloseDialog = false;
//                if(wantToCloseDialog) {
//                    dialog.dismiss();
//                }
            }
        });

        GameActivity.this.runOnUiThread(new Runnable(){
            public void run() {
                onPause();
                final AlertDialog dialog = builder.create();
                dialog.show();
                dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Boolean wantToCloseDialog = false;
                        if(input.length() > 0) {
                            m_Text = input.getText().toString();
                            finish();
                            AddData(m_Text);
                            wantToCloseDialog = true;
                        }
                        if(wantToCloseDialog)
                            dialog.dismiss();
                    }
                });
            }
        });
    }

    public void AddData(String newEntry) {
        boolean insertData = databaseHelper.addData(newEntry);
        if(insertData) {
            Log.d("Success", "Data added to database");
        } else {
            Log.d("Error", "There was an error with inserting data");
        }
    }
}
