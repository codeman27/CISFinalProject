package com.example.cody.asteroidassaultgame;

import android.content.Context;
import android.database.Cursor;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    Context appContext;
    private PlayerShip playerShip;
    private DatabaseHelper databaseHelper = new DatabaseHelper(appContext);

    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.example.cody.asteroidassaultgame", appContext.getPackageName());
    }

    @Test
    public void test_ship_position() throws Exception {

        //playerShip = new PlayerShip(appContext, 1440, 2712);
        //assertEquals(playerShip.getX(), 1440);
    }

    @Test
    public void test_DBWrite() {
        //assertEquals(databaseHelper.addData("TEST", 500), true);
    }

    public void test_DBRead() {
        //Cursor data = databaseHelper.getData();
        //ArrayList<String> nameData = new ArrayList<>();

        //while(data.moveToNext()){
        //    nameData.add(data.getString(1));
        //}

        //assertEquals(nameData.get(0), "TEST");
    }
}
