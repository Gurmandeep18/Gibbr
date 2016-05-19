package com.gurmandeep.gibbrfill;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FillInActivity extends Activity {

    private String fileContent = "";
    private TextView textView3;
    private TextView textView4;
    private EditText editText1;
    private Button button1;
    private int count;
    private int numOfTag;
    private String[] results;
    private char[] story;
    private String storyString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_in);
        getFileContent();
        Log.d("Gibbr",fileContent);

        numOfTag = 0;
        for(int i = 0; i < fileContent.length();i++) {
            if(fileContent.charAt(i) == '<') {
                numOfTag++;
            }
        }

        textView3 = (TextView)findViewById(R.id.textview_3);
        textView3.setText(numOfTag + " word(s) left");
        textView4 = (TextView)findViewById(R.id.textview_4);

        results = new String[numOfTag + 1];
        int s = 0;
        int l = 0;

        for(int j = 1; j <= numOfTag; j++) {
            s = fileContent.indexOf("<",s+1);
            l = fileContent.indexOf(">",l+1);
            results[j] = fileContent.substring(s+1,l);
            Log.d("Gibbr",fileContent);
        }

        editText1 = (EditText)findViewById(R.id.edittext_1);
        button1 = (Button)findViewById(R.id.button_1);

        count = 1;
        if(results[count].equals("adjective")){
            textView4.setText("Fill an adjective");
        } else if(results[count].equals("plural-noun")) {
            textView4.setText("Fill a plural-noun");
        } else if(results[count].equals("noun")) {
            textView4.setText("Fill a noun");
        }


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editText1.getText().length() >= 2) {
                    results[count] = editText1.getText().toString();
                    textView3.setText((numOfTag - count) + " word(s) left.");
                    editText1.setText("");
                    count++;



                    if(numOfTag-count+1 == 0) {
                        changeContent();
                        Intent mIntent = new Intent(getApplicationContext(),CompleteStory.class);
                        mIntent.putExtra("story",story);
                        startActivity(mIntent);
                        Log.d("Gliffr", storyString);
                    } else {
                        if(results[count].equals("adjective")){
                            textView4.setText("Fill an adjective");
                        } else if(results[count].equals("plural-noun")) {
                            textView4.setText("Fill a plural-noun");
                        } else if(results[count].equals("noun")) {
                            textView4.setText("Fill a noun");
                        }
                    }

                } else {
                    Toast.makeText(getApplicationContext(),"Fill Text",Toast.LENGTH_LONG).show();
                }
            }
        });


    }


    public void getFileContent(){
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(
                    new InputStreamReader(getAssets().open("gibbr_fill.txt")));

            // do reading, usually loop until end of file reading
            String mLine;
            while ((mLine = reader.readLine()) != null) {
                fileContent = fileContent + mLine;
            }
        } catch (IOException e) {
            //log the exception
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    //log the exception
                }
            }
        }
    }

    private void changeContent() {
        char[] fileContentArray = fileContent.toCharArray();
        story = new char[10000];
        int count = numOfTag;
        int k = 0;
        int k1 = 0;
        for(int i = 1; i <= count ; ) {
            if(fileContentArray[k1] == '<') {
               
                for(int j = 0; j < results[i].length(); j++) {
                    story[k] = results[i].charAt(j);
                    k++;
                }

                for(; fileContentArray[k1]!='>';k1++ ) {

                }
                k1++;
                i++;
            } else {
                story[k] = fileContentArray[k1];
                k1++;
                k++;
            }
        }
        storyString = story.toString();
    }
}
