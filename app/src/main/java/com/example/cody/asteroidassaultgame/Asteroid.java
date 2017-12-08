package com.example.cody.asteroidassaultgame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.RectF;

import java.util.Random;

public class Asteroid {

    private Bitmap bitmap;
    private float width;
    private float height;

    private float y;
    private float x;
    Random randNum = new Random();
    private float speed;
    private RectF rect;

    public Asteroid(Context context, int screenX, int screenY, int gameSpeed) {
        width = screenX/8;
        height = screenY/12;
        speed = 350 + gameSpeed + randNum.nextInt(150);
        rect = new RectF();
        float ranYNum = -((randNum.nextInt(3000) + height));

        y = ranYNum;

        float ranXNum = randNum.nextInt(screenX);
        if(ranXNum < width){
            x = ranXNum + width;
        } else if (ranXNum > (screenX - width)) {
            x = ranXNum - width;
        } else {
            x = ranXNum;
        }

        int randBitMap = randNum.nextInt(3);
        //Init bitmap
        switch(randBitMap){
            case 0:
                bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.asteroid_alpha);
                break;
            case 1:
                bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.asteroid_b);
                break;
            case 2:
                bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.asteroid_c);
                break;
        }

        // stretch bitmap to an appropriate size
        bitmap = Bitmap.createScaledBitmap(bitmap, (int) width, (int) height, false);

    }

    public Bitmap getBitmap() { return bitmap; }

    public float getY() { return y; }

    public float getX() {return x; }

    public void update(long fps) {
        y = y + speed/fps;

        // Update rect for collision detection
        rect.left = x + (width/7);
        rect.right = x + width - (width/7);
        rect.top = y + (height/7);
        rect.bottom = y + height - (height/6);
    }

    public RectF getRect() {
        return rect;
    }
}
