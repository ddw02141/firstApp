package com.example.q.firstapp;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Pair;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TabHost tabHost;
    TabHost.TabSpec ts1;
    TabHost.TabSpec ts2;
    TabHost.TabSpec ts3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv1 = (TextView)findViewById(R.id.tv1);
        LinearLayout l1 = (LinearLayout)findViewById(R.id.phoneNumbers);

        AssetManager assetManager = getResources().getAssets();
        try {
            AssetManager.AssetInputStream ais = (AssetManager.AssetInputStream) assetManager.open("sample.json");
            BufferedReader br = new BufferedReader(new InputStreamReader(ais));
            StringBuilder sb = new StringBuilder();
            int bufferSize = 1024 * 1024;
            char readBuf[] = new char[bufferSize];
            int resultSize = 0;
            while ((resultSize = br.read(readBuf)) != -1) {
                if (resultSize == bufferSize) {
                    sb.append(readBuf);
                } else {
                    for (int i = 0; i < resultSize; i++) {
                        //StringBuilder 에 append
                        sb.append(readBuf[i]);
                    }
                }
            }
            String jString = sb.toString();

            //JSONObject 얻어 오기
            JSONArray jsonArray = new JSONArray(jString);

            //json value 값 얻기
            for(int i =0;i<jsonArray.length();i++){
                String name = ((JSONObject)(jsonArray.get(i))).get("name").toString();
                String phone =  ((JSONObject)(jsonArray.get(i))).get("phone").toString();
//                TextView nameView = new TextView(this);
//                TextView phoneView = new TextView(this);
//                LinearLayout l2 = new LinearLayout(this);
//                l2.setOrientation(LinearLayout.HORIZONTAL);
//                l2.addView(nameView);
//                l2.addView(phoneView);
                TextView tv = new TextView(this);
                tv.setText(name+"\t\t"+phone);
                l1.addView(tv);

            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        tabHost = (TabHost) findViewById(R.id.tabHost);
        tabHost.setup();

        ts1 = tabHost.newTabSpec("Tab Spec 1");
        ts1.setContent(R.id.content1);
        ts1.setIndicator("TAB 1");
        tabHost.addTab(ts1);

        ts2 = tabHost.newTabSpec("Tab Spec 2");
        ts2.setContent(R.id.content2);
        ts2.setIndicator("TAB 2");
        tabHost.addTab(ts2);

        ts3 = tabHost.newTabSpec("Tab Spec 3");
        ts3.setContent(R.id.content3);
        ts3.setIndicator("TAB 3");
        tabHost.addTab(ts3);


    }

}
