package com.example.nhamlani101.materialtest;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;

import android.support.v7.app.ActionBarActivity;
import android.util.JsonReader;
import android.util.JsonToken;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;



public class MainActivity extends Activity{

    TextView fajrTV;
    TextView zuhrTV;
    TextView asrTV;
    TextView maghribTV;
    TextView ishaTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new GetMethodEx().execute("http://muslimsalat.com/11717/daily/4.json?key=ea4417ebf152c478082e1ff6a1a759d9");
    }

    public class GetMethodEx  extends AsyncTask<String, Void, InputStream> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            fajrTV = (TextView) findViewById(R.id.fajr);
            zuhrTV = (TextView) findViewById(R.id.zuhr);
            asrTV = (TextView) findViewById(R.id.asr);
            maghribTV = (TextView) findViewById(R.id.maghrib);
            ishaTV = (TextView) findViewById(R.id.isha);
        }

        @Override
        protected InputStream doInBackground(String... params) {
            try {
                HttpClient client = new DefaultHttpClient();
                HttpPost post = new HttpPost(params[0]);
                HttpResponse response = client.execute(post);
                int status = response.getStatusLine().getStatusCode();
                if (status == 200) {
                    HttpEntity entity = response.getEntity();
                    InputStream in = entity.getContent();
                    return in;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(InputStream in) {
            super.onPostExecute(in);
            try {
                JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));

                String fajr = null;
                String zuhr = null;
                String asr = null;
                String maghrib = null;
                String isha = null;

                reader.beginObject();
                while (reader.hasNext()) {
                    String name = reader.nextName();
                    if (name.equals("items")) {
                        reader.beginArray();
                        while (reader.hasNext()) {
                            reader.beginObject();
                            while (reader.hasNext()) {
                                String innerName = reader.nextName();
                                final boolean isInnerNull = reader.peek() == JsonToken.NULL;
                                if (innerName.equals("fajr") && !isInnerNull) {
                                    fajr = reader.nextString();
                                } else if (innerName.equals("dhuhr") && !isInnerNull) {
                                    zuhr = reader.nextString();
                                } else if (innerName.equals("asr") && !isInnerNull) {
                                    asr = reader.nextString();
                                } else if (innerName.equals("maghrib") && !isInnerNull) {
                                    maghrib = reader.nextString();
                                } else if (innerName.equals("isha") && !isInnerNull) {
                                    isha = reader.nextString();
                                } else {
                                    reader.skipValue();
                                }
                            }
                            reader.endObject();
                        }
                        reader.endArray();
                    } else {
                        reader.skipValue();
                    }
                }
                reader.endObject();
                reader.close();

                fajrTV.setText(fajr);
                zuhrTV.setText(zuhr);
                asrTV.setText(asr);
                maghribTV.setText(maghrib);
                ishaTV.setText(isha);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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
