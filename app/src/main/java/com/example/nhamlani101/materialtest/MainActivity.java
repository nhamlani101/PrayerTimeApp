package com.example.nhamlani101.materialtest;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView fajrTV = (TextView) findViewById(R.id.fajr);
        final TextView zuhrTV = (TextView) findViewById(R.id.zuhr);
        final TextView asrTV = (TextView) findViewById(R.id.asr);
        final TextView maghribTV = (TextView) findViewById(R.id.maghrib);
        final TextView ishaTV = (TextView) findViewById(R.id.isha);

        //String source = "This is the source of my input stream";
        //InputStream in = IOUtils.toInputStream(source, "UTF-8");
        GetMethodEx test = new GetMethodEx();
        InputStream ins;
        List prayList = new ArrayList();
        readJson myJson = new readJson();
        ins = test.getInternetData();

        try {
            prayList = myJson.readJsonStream(ins);
        } catch (Exception e) {
            e.printStackTrace();
        }


        if (prayList.isEmpty() == true) {
            fajrTV.setText("The array is empty brah");
        }

        //fajrTV.setText((String) prayList.get(0));
        //zuhrTV.setText((String) prayList.get(1));
        //asrTV.setText((String) prayList.get(2));
        //maghribTV.setText((String) prayList.get(3));
        //ishaTV.setText((String) prayList.get(4));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
