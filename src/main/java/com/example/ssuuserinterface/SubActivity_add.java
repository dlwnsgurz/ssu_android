package com.example.ssuuserinterface;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class SubActivity_add extends AppCompatActivity {

    private TextView crawledData;
    private String url;
    private String str;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
        Intent intent = getIntent();
        url = intent.getStringExtra("webUrl");
        crawledData = (TextView) findViewById(R.id.crawledData);
        Log.d("qweqweqwe : ",url);
        final Bundle bundle = new Bundle();

        new Thread(){
            @Override
            public void run(){
                Document doc = null;
                try{
                    doc = Jsoup.connect(url).get();
                    Elements contents = doc.select("title");
                    str += contents.text();
                    bundle.putString("Strings", str);
                    Message msg = handler.obtainMessage();
                    msg.setData(bundle);
                    handler.sendMessage(msg);

                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }.start();
    }
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            Bundle bundle = msg.getData();
            crawledData.setText(bundle.getString("Strings"));
        }
    };
}