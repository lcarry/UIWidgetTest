package com.example.lcarry.webTest;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.lcarry.broadcastTest.UIBroadcastTestActivity;
import com.example.lcarry.multimedia.MultimediaActivity;
import com.example.lcarry.uiwidgettest.R;

import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;

import javax.xml.parsers.SAXParserFactory;

public class WebTestActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_test);

        Button buttonWebView = (Button)findViewById(R.id.button_webView);
        buttonWebView.setOnClickListener(this);

        Button buttonHttpUrlConnection = (Button)findViewById(R.id.button_http_url_connection);
        buttonHttpUrlConnection.setOnClickListener(this);

        //JSONObject
        //XmlPullParser
        //SAXParserFactory
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_webView: {
                showWebViewTestActivity("showWebViewTestActivity");
            }
            break;
            case R.id.button_http_url_connection: {
                //展示httpUrlConnection请求功能
                showHttpUrlConnectionTestActivity("showHttpUrlConnectionTestActivity");
            }
            break;



            default:
                break;
        }
    }

    public void showWebViewTestActivity(String strName) {
        Intent intent = new Intent(WebTestActivity.this, WebViewTestActivity.class);
        startActivity(intent);
        Toast.makeText(this, strName, Toast.LENGTH_SHORT).show();
    }

    public void showHttpUrlConnectionTestActivity(String strName) {
        Intent intent = new Intent(WebTestActivity.this, HttpUrlConnectionTestActivity.class);
        startActivity(intent);
        Toast.makeText(this, strName, Toast.LENGTH_SHORT).show();
    }
}
