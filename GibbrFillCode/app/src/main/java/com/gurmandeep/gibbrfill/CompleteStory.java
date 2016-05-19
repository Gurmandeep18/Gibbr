package com.gurmandeep.gibbrfill;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class CompleteStory extends Activity {
    private TextView textView3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_story);

        char[] story = getIntent().getExtras().getCharArray("story");

        textView3 = (TextView)findViewById(R.id.textview_3);
        textView3.setText(story,0,story.length);
    }
}
