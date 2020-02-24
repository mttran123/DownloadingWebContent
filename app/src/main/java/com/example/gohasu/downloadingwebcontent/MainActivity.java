package com.example.gohasu.downloadingwebcontent;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    public class DownloadTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {  // String... strings : a way to create an array named strings

            String result = "";
            URL url; // create an URL object
            HttpURLConnection urlConnection;

            // turn actual url (from array strings) to URL object
            try {
                url = new URL(urls[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);

                int data = reader.read();

                while (data != -1) {
                    char current = (char) data;

                    result += current;

                    data = reader.read();
                }
                return result;

            } catch (Exception e) {
                e.printStackTrace();
                return "Failed";
            }
        }
    }

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DownloadTask task = new DownloadTask();
        String result = null;

        try {
            result = task.execute("https://zappycode.com/").get();
            //this url will then come through method doInBackground. We can add as many strings as we like
            // get() will get whatever return value is ("Done" in this case)

        } catch (Exception e) {
            e.printStackTrace();
        }

        Log.i("Result", result);
    }
}
