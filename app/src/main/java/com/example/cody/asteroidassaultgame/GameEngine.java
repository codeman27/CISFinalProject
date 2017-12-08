package com.example.cody.asteroidassaultgame;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.graphics.RectF;


public class GameEngine extends SurfaceView implements Runnable {

    Context context;

    private Thread gameThread = null;
    private SurfaceHolder ourHolder;
    // Volatile is what allows us to change it while the activity is running
    private volatile boolean playing;
    private boolean paused = true;
    private Canvas canvas;
    private Paint paint;
    private int screenX;
    private int screenY;
    private int score;
    private long fps;
    private long timeThisFrame;

    private PlayerShip playerShip;
    private Asteroid[] asteroids = new Asteroid[15];


    public GameEngine(Context context, int x, int y){
        super(context);
        this.context = context;

        ourHolder = getHolder();
        paint = new Paint();
        // Set up screen size
        screenX = x;
        screenY = y;


        prepareLevel();

    }

    public void pause(){
        playing = false;
        paused = true;

        try {
            gameThread.join();
        } catch(InterruptedException e) {
            Log.e("Error:", "joining thread");
        }
    }

    public void resume(){
        playing = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    void prepareLevel() {

        score = 0;

        playerShip = new PlayerShip(context, screenX, screenY);

        for(int i = 0; i < asteroids.length; i++) {
            asteroids[i] = new Asteroid(context, screenX, screenY, score);
        }
    }

    @Override
    public void run(){
        while(playing) {
            long startFrameTime = System.currentTimeMillis();

            if(!paused) {
                update();
            }

            draw();

            timeThisFrame = System.currentTimeMillis() - startFrameTime;
            if(timeThisFrame >= 1) {
                fps = 1000 / timeThisFrame;
            }
        }

    }

    public void update(){
        // Move ship
        playerShip.update(fps, screenX);

        // Move asteroids
        for(int i = 0; i < asteroids.length; i++) {
            // Check for asteroids colliding with ship
            if(RectF.intersects(asteroids[i].getRect(), playerShip.getRect())){
                score = 0;
                // Lose game
                // Update DB
                // Restart
            }

            if(asteroids[i].getY() < screenY) {
                asteroids[i].update(fps);
            } else {
                asteroids[i] = new Asteroid(context, screenX, screenY, score);
                score++;
            }
        }

    }

    private void draw() {
        if(ourHolder.getSurface().isValid()) {
            canvas = ourHolder.lockCanvas();
            // Draw background
            canvas.drawColor(Color.argb(255, 0, 0, 0));

            // Draw everything else to the screen
            canvas.drawBitmap(playerShip.getBitmap(), playerShip.getX(), playerShip.getY(), paint);
            for(int i = 0; i < asteroids.length; i++){
                canvas.drawBitmap(asteroids[i].getBitmap(), asteroids[i].getX(), asteroids[i].getY(), paint);
                // Uncomment to see collision boxes for asteroids
                canvas.drawRect(asteroids[i].getRect(), paint);
            }
            // Uncomment to see collision box for the player ship
            canvas.drawRect(playerShip.getRect(), paint);

            Typeface typeface = getResources().getFont(R.font.press_start_2p);
            paint.setTypeface(typeface);
            paint.setColor(Color.argb(255, 255, 255 , 255));
            paint.setTextSize(80);
            canvas.drawText("Score: " + score, 20, 90, paint);

            if(paused) {
                canvas.drawText("PAUSED", (screenX/3), (screenY/2), paint);
            }

            // Show everything that was drawn
            ourHolder.unlockCanvasAndPost(canvas);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch(motionEvent.getAction() & MotionEvent.ACTION_MASK) {
            // Person touched the screen
            case MotionEvent.ACTION_DOWN:
                paused = false;

                if(motionEvent.getX() > screenX / 2){
                    playerShip.setMovementState(playerShip.RIGHT);
                } else {
                    playerShip.setMovementState(playerShip.LEFT);
                }
                break;
            // Person removed finger
            case MotionEvent.ACTION_UP:
                playerShip.setMovementState(playerShip.STOPPED);
                break;

        }
        return true;
    }
}
