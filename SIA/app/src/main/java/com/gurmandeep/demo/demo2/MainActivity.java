package com.gurmandeep.demo.demo2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Map;

public class MainActivity extends Activity {

    private Button button1;
    private EditText editText1;
    private EditText editText2;
    private TextView textView2;
    private String prefrences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * Accessing edittext from layout
         */
        editText1 = (EditText) findViewById(R.id.edittext_1);
        editText2 = (EditText) findViewById(R.id.edittext_2);

        /**
         * Accessing button from layout
         */
        button1 = (Button) findViewById(R.id.button_1);

        /**
         * On Click listener on Button
         */
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean mBoolean = isValidEmail(editText1.getText());

                if (mBoolean) {
                    if (editText2.getText().length() >= 8) {
                        if (userExist(editText1.getText().toString(), editText2.getText().toString())) {
                            Intent mIntent = new Intent(getApplicationContext(), UserActivity.class);
                            mIntent.putExtra("id", editText1.getText().toString());
                            mIntent.putExtra("prefrence", prefrences);
                            startActivity(mIntent);
                        } else {
                            Toast.makeText(getApplicationContext(), "User does not exist", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Password must contain 8 characters", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Please enter a valid emailId", Toast.LENGTH_LONG).show();
                }

            }
        });
        textView2 = (TextView) findViewById(R.id.textview_2);
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(getApplicationContext(), Registration.class);
                startActivity(mIntent);
            }
        });

    }

    /**
     * it validates the string for email address
     *
     * @param target
     * @return
     */
    public final static boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    public boolean userExist(String username, String password) {

        SharedPreferences mSharedPreferences = getSharedPreferences("user counter", Context.MODE_PRIVATE);
        int numberOfUsers = mSharedPreferences.getInt("constant_key", 0);
        SharedPreferences m2SharedPreferences = getSharedPreferences("user data", Context.MODE_PRIVATE);
        Log.d("SIA Tutorial", username);
        prefrences = "";

        for (int i = 1; i <= numberOfUsers; i++) {
            if (m2SharedPreferences.getString("email_key" + i, "").equals(username)) {
                if (m2SharedPreferences.getString("password_key" + i, "").equals(password)) {
                    Log.d("SIA Tutorial", username);
                    if (m2SharedPreferences.getBoolean("prefrence1_key" + i, false)) {
                        prefrences = prefrences + ",Music";
                    }

                    if (m2SharedPreferences.getBoolean("prefrence2_key" + i, false)) {
                        prefrences = prefrences + ",Art";
                    }

                    if (m2SharedPreferences.getBoolean("prefrence3_key" + i, false)) {
                        prefrences = prefrences + ",Science";
                    }

                    if (m2SharedPreferences.getBoolean("prefrence4_key" + i, false)) {
                        prefrences = prefrences + ",Maths";
                    }

                    if (m2SharedPreferences.getBoolean("prefrence5_key" + i, false)) {
                        prefrences = prefrences + ",Painting";
                    }
                    return true;
                }
            }
        }

            Log.d("SIA Tutorial", username);
            return false;
        }
    }

