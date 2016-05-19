package com.gurmandeep.demo.demo2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class UserActivity extends AppCompatActivity {

    private TextView textView2;
    private TextView textView3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        String email = getIntent().getExtras().getString("id");
        String prefrences = getIntent().getExtras().getString("prefrence");

        textView2 = (TextView)findViewById(R.id.textview_2);
        textView2.setText(textView2.getText().toString()+"\n"+email);

        textView3 = (TextView)findViewById(R.id.textview_3);
        textView3.setText(textView3.getText().toString()+"\n"+prefrences);



    }
}
