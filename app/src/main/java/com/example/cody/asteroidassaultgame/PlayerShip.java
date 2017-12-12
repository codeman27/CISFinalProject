package com.example.cody.asteroidassaultgame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.RectF;

public class PlayerShip {

    private RectF rect;
    private Bitmap bitmap;
    private float width;
    private float height;

    private float x;
    private float y;

    private float shipSpeed;

    public final int STOPPED = 0;
    public final int LEFT = 1;
    public final int RIGHT = 2;

    private int shipMoving = STOPPED;

    public PlayerShip(Context context, int screenX, int screenY) {
        width = screenX/8;
        height = screenY/12;
        rect = new RectF();

        x = (screenX / 2) - (width / 2);
        y = screenY - (height * 2);

        //Init bitmap
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.player_ship);

        // stretch bitmap to an appropriate size
        bitmap = Bitmap.createScaledBitmap(bitmap, (int) width, (int) height, false);

        shipSpeed = 400;
    }

    public Bitmap getBitmap() { return bitmap; }

    public RectF getRect(){return rect;}

    public float getX() {return x; }

    public float getY() {return y;}

    public void setMovementState(int state) { shipMoving = state; }

    public void update (long fps, int screenX) {
        if(shipMoving == LEFT) {
            if(x >= width){
                x = x - shipSpeed/fps;
            }
        }
        if(shipMoving == RIGHT) {
            if(x <= screenX - width) {
                x = x + shipSpeed/fps;
            }
        }

        rect.left = x + (width/8);
        rect.right = x + width - (width/8);
        rect.top = y + (height/4);
        rect.bottom = y + height - (height/4);
    }


}
