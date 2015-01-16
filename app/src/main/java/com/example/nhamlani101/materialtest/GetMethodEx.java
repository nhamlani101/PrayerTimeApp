package com.example.nhamlani101.materialtest;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

/**
 * Created by nhamlani101 on 1/12/2015.
 */
public class GetMethodEx {
    //HELLO
    //HELLO

    static InputStream in = null;

    public InputStream getInternetData(){
        try {
            DefaultHttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost("http://muslimsalat.com/11717/daily/4.json?key=ea4417ebf152c478082e1ff6a1a759d9");
            HttpResponse response = client.execute(post);
            HttpEntity entity = response.getEntity();
            in = entity.getContent();
            return in;
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        catch (ClientProtocolException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return in;
    }
}
