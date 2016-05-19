package com.gurmandeep.demo.demo2;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Registration extends AppCompatActivity {

    private Button button1;
    private EditText editText1;
    private EditText editText2;
    private EditText editText3;
    private EditText editText4;
    private RadioGroup radioGroup1;
    private RadioButton maleButton, femaleButton;
    private CheckBox checkBox1, checkBox2, checkBox3, checkBox4, checkBox5;


    private static final String CONSTANT_KEY = "constant_key";
    private static final String NAME_KEY = "name_key";
    private static final String CONTACT_KEY = "contact_key";
    private static final String EMAIL_KEY = "email_key";
    private static final String PASSWORD_KEY = "password_key";
    private static final String GENDER_KEY = "gender_key";
    private static final String PREFRENCE1_KEY = "prefrence1_key";
    private static final String PREFRENCE2_KEY = "prefrence2_key";
    private static final String PREFRENCE3_KEY = "prefrence3_key";
    private static final String PREFRENCE4_KEY = "prefrence4_key";
    private static final String PREFRENCE5_KEY = "prefrence5_key";

    private String name_key_value;
    private String contact_key_value;
    private String email_key_value;
    private String password_key_value;
    private String gender_key_value;
    private boolean prefrence1_key_value;
    private boolean prefrence2_key_value;
    private boolean prefrence3_key_value;
    private boolean prefrence4_key_value;
    private boolean prefrence5_key_value;

    private int checkedCounter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        editText1 = (EditText)findViewById(R.id.edittext_1);
        editText2 = (EditText)findViewById(R.id.edittext_2);
        editText3 = (EditText)findViewById(R.id.edittext_3);
        editText4 = (EditText)findViewById(R.id.edittext_4);


        button1 = (Button)findViewById(R.id.button_1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    //already registered
                    if(validate()) {

                        name_key_value = editText1.getText().toString();
                        contact_key_value = editText2.getText().toString();
                        email_key_value = editText3.getText().toString();
                        password_key_value = editText4.getText().toString();

                        SharedPreferences mSharedPreferences = getSharedPreferences("user counter", Context.MODE_PRIVATE);
                        SharedPreferences.Editor mEditor = mSharedPreferences.edit();
                        if(mSharedPreferences.contains(CONSTANT_KEY)) {
                            int counter = mSharedPreferences.getInt(CONSTANT_KEY, 0);
                            counter = counter + 1;
                            mEditor.putInt(CONSTANT_KEY, counter);
                            mEditor.apply();
                            Toast.makeText(getApplicationContext(), counter + "th User", Toast.LENGTH_LONG).show();
                            store_parameters(counter);
                        }else {
                            mEditor.putInt(CONSTANT_KEY,1);
                            mEditor.apply();
                            Toast.makeText(getApplicationContext(),"First User",Toast.LENGTH_LONG).show();
                            store_parameters(1);
                    }
                } else {
                    return;
                }
            }
        });

        radioGroup1 = (RadioGroup)findViewById(R.id.gender);
        radioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.radio_male) {
                    gender_key_value = "Male";
                } else if(checkedId == R.id.radio_female) {
                    gender_key_value = "Female";
                }
            }
        });

        checkBox1 = (CheckBox)findViewById(R.id.prefrences_1);
        checkBox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    prefrence1_key_value = true;
                    checkedCounter = checkedCounter + 1;
                } else {
                    prefrence1_key_value = false;
                    checkedCounter = checkedCounter - 1;
                }
            }
        });

        checkBox2 = (CheckBox)findViewById(R.id.prefrences_2);
        checkBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    prefrence2_key_value = true;
                    checkedCounter = checkedCounter + 1;
                } else {
                    prefrence2_key_value = false;
                    checkedCounter = checkedCounter - 1;
                }
            }
        });

        checkBox3 = (CheckBox)findViewById(R.id.prefrences_3);
        checkBox3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    prefrence3_key_value = true;
                    checkedCounter = checkedCounter + 1;
                } else {
                    prefrence3_key_value = false;
                    checkedCounter = checkedCounter - 1;
                }
            }
        });

        checkBox4 = (CheckBox)findViewById(R.id.prefrences_4);
        checkBox4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    prefrence4_key_value = true;
                    checkedCounter = checkedCounter + 1;
                } else {
                    prefrence4_key_value = false;
                    checkedCounter = checkedCounter - 1;
                }
            }
        });

        checkBox5 = (CheckBox)findViewById(R.id.prefrences_5);
        checkBox5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    prefrence5_key_value = true;
                    checkedCounter = checkedCounter + 1;
                } else {
                    prefrence5_key_value = false;
                    checkedCounter = checkedCounter - 1;
                }
            }
        });


    }

    public boolean validate() {
        if(editText1.getText().length() >= 3) {
            if (isValidMobile(editText2.getText().toString())) {
               if(MainActivity.isValidEmail(editText3.getText().toString())) {
                   if(editText4.getText().length() >= 8) {
                       if(checkedCounter > 0) {
                           return true;
                       } else {
                           Toast.makeText(getApplicationContext(),"please select atleast one prefrence",Toast.LENGTH_LONG).show();
                       }
                   } else {
                       Toast.makeText(getApplicationContext(),"Password must contain 8 characters",Toast.LENGTH_LONG).show();
                   }

               } else {
                   Toast.makeText(getApplicationContext(),"Please enter a valid email id",Toast.LENGTH_LONG).show();
               }
            } else {
                Toast.makeText(getApplicationContext(),"Please enter valid mobile number",Toast.LENGTH_LONG).show();
            }

        }else {
            Toast.makeText(getApplicationContext(),"Name length should be greater than three characters",Toast.LENGTH_LONG).show();
        }
        return false;
    }

    private boolean isValidMobile(String phone)
    {
        return android.util.Patterns.PHONE.matcher(phone).matches();
    }


    public void store_parameters(int counter) {
        SharedPreferences mSharedPreferences = getSharedPreferences("user data",Context.MODE_PRIVATE);
        SharedPreferences.Editor mEditor = mSharedPreferences.edit();

        mEditor.putString(NAME_KEY + counter,name_key_value);
        mEditor.putString(CONTACT_KEY + counter,contact_key_value);
        mEditor.putString(EMAIL_KEY + counter,email_key_value);
        mEditor.putString(PASSWORD_KEY + counter,password_key_value);
        mEditor.putString(GENDER_KEY + counter,gender_key_value);
        mEditor.putBoolean(PREFRENCE1_KEY + counter,prefrence1_key_value);
        mEditor.putBoolean(PREFRENCE2_KEY + counter,prefrence2_key_value);
        mEditor.putBoolean(PREFRENCE3_KEY + counter,prefrence3_key_value);
        mEditor.putBoolean(PREFRENCE4_KEY + counter,prefrence4_key_value);
        mEditor.putBoolean(PREFRENCE5_KEY + counter,prefrence5_key_value);
        mEditor.apply();

        Toast.makeText(getApplicationContext(),"User Registered",Toast.LENGTH_LONG).show();
    }
}
