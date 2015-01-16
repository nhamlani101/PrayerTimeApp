package com.example.nhamlani101.materialtest;


import android.util.JsonReader;
import android.util.JsonToken;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nhamlani101 on 1/4/2015.
 */


public class readJson {
    public List readJsonStream(InputStream in) throws Exception {
        JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));

        try {
            return readMessage(reader);
        }
        finally {
            reader.close();
        }
    }


    public List readMessage(JsonReader reader) throws IOException {
        String fajr = null;
        String zuhr = null;
        String asr = null;
        String maghrib = null;
        String isha = null;

        reader.beginObject();
        while (reader.hasNext()) {
           String name = reader.nextName();
            if(name.equals( "items" )) {
                reader.beginArray();
                while (reader.hasNext()) {
                    reader.beginObject();
                    while (reader.hasNext()) {
                        String innerName = reader.nextName();
                        final boolean isInnerNull = reader.peek() == JsonToken.NULL;
                        if (innerName.equals("fajr") && !isInnerNull) {
                            fajr = reader.nextString();
                        }
                        else if (innerName.equals("dhuhr") && !isInnerNull) {
                            zuhr = reader.nextString();
                        }
                        else if (innerName.equals("asr") && !isInnerNull) {
                            asr = reader.nextString();
                        }
                        else if (innerName.equals("maghrib") && !isInnerNull) {
                            maghrib = reader.nextString();
                        }
                        else if (innerName.equals("isha") && !isInnerNull) {
                            isha = reader.nextString();
                        }
                        else {
                            reader.skipValue();
                        }
                    }
                    reader.endObject();
                }
                reader.endArray();
            }
            else {
                reader.skipValue();
            }
        }
        reader.endObject();

        List myList = new ArrayList();
        myList.add(fajr);
        myList.add(zuhr);
        myList.add(asr);
        myList.add(maghrib);
        myList.add(isha);

        return myList;
    }
}
