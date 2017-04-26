package com.example.lcarry.webTest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.lcarry.fragmentbestpractice.News;
import com.example.lcarry.uiwidgettest.R;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpUrlConnectionTestActivity extends AppCompatActivity implements View.OnClickListener  {

    private final static String BAIDU_URL = "http://www.baidu.com";
    TextView responseTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http_url_connection_test);

        Button buttonClear = (Button)findViewById(R.id.button_clear);
        buttonClear.setOnClickListener(this);

        Button buttonSend = (Button)findViewById(R.id.button_send);
        buttonSend.setOnClickListener(this);

        Button buttonSendOkHttp = (Button)findViewById(R.id.button_send_okhttp);
        buttonSendOkHttp.setOnClickListener(this);


        responseTextView = (TextView)findViewById(R.id.response_text);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_clear: {
                //清除结果
                responseTextView.setText("");
            }
            break;
            case R.id.button_send: {
                //发送请求
                sendRequestWithHttpURLConnection();
            }
            break;

            case R.id.button_send_okhttp: {
                //发送请求
                sendRequestWithOkHttp();
            }
            break;
            default:
                break;
        }
    }

    private void sendRequestWithOkHttp() {
        //开启线程进行网络请求
        new Thread(new Runnable() {
            @Override
            public void run() {


                try {

                    //下面是网络请求
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url(BAIDU_URL)
                            .build();

                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    //下面是显示结果
                    showResponse(responseData);




                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

    private void sendRequestWithHttpURLConnection() {
        //开启线程进行网络请求
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                BufferedReader reader = null;

                try {

                    //下面是网络请求
                    URL url = new URL(BAIDU_URL);
                    connection = (HttpURLConnection)url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(10000);
                    InputStream in = connection.getInputStream();

                    //下面是对获取的输入流进行读取
                    reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null){
                        response.append(line);
                    }
                    showResponse(response.toString());




                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    if (connection != null)
                        connection.disconnect();
                }

            }
        }).start();
    }

    //android不允许在子线程操作UI
    private void showResponse(final String response){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //在这里进行ui操作，显示请求返回结果
                responseTextView.setText(response);
            }
        });
    }
}
