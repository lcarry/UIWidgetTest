package com.example.lcarry.webTest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.lcarry.uiwidgettest.R;

public class WebViewTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_test);
        WebView webView = (WebView) findViewById(R.id.web_view);
        webView.getSettings().setJavaScriptEnabled(true);//表示支持webview内执行js
        webView.setWebViewClient(new WebViewClient());//表示所有网页操作都在webview内，而不是跳转到浏览器
        webView.loadUrl("http://www.baidu.com");

    }
}
