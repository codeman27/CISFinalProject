package com.example.cody.asteroidassaultgame;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;

public class GameActivity extends Activity {

    GameEngine gameEngine;

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
        gameEngine = new GameEngine(this, size.x, size.y);
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
}
