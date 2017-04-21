package com.example.lcarry.datapersistance.filepersistance;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lcarry.uiwidgettest.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class UIFilePersistenceActivity extends AppCompatActivity {

    private EditText editTextInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uifile_persistence);

        editTextInput = (EditText) findViewById(R.id.edit_text_input);


        String strInput = load();
        if (!TextUtils.isEmpty(strInput)){
            editTextInput.setText(strInput);
            Toast.makeText(this,"Restoring succeeded",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //窗口销毁时进行内容存储
        String inputContent = editTextInput.getText().toString();
        save(inputContent);
    }


    public void save(String input){
        FileOutputStream output  = null;
        BufferedWriter writer = null;
        try {
            output = openFileOutput("data", Context.MODE_PRIVATE);
            writer = new BufferedWriter(new OutputStreamWriter(output));
            writer.write(input);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally {
            try{
                if (writer != null){
                    writer.close();
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    public String load(){
        FileInputStream in = null;
        BufferedReader reader = null;
        StringBuilder content = new StringBuilder();


        try {

            in = openFileInput("data");
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while (null != ((line = reader.readLine()))){
                content.append(line);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null){
                try {
                    reader.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return content.toString();
    }

}
