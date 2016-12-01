package com.example.ivann.myapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    //обьявляем контролы
    private EditText name, company, email;
    private Button save;
    private CheckBox auto, creditCard, freelance, fullTime, remote;
    private RadioButton cpp, c, java;
    private String skills;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //инициализируем контролы
        name = (EditText) findViewById(R.id.name);
        company = (EditText) findViewById(R.id.company);
        email = (EditText) findViewById(R.id.email);
        save = (Button) findViewById(R.id.buttonSave);
        auto = (CheckBox) findViewById(R.id.auto);
        creditCard = (CheckBox) findViewById(R.id.creditCard);
        freelance = (CheckBox) findViewById(R.id.freelance);
        fullTime = (CheckBox) findViewById(R.id.fulltime);
        remote = (CheckBox) findViewById(R.id.remote);

        cpp = (RadioButton) findViewById(R.id.cpp);
        cpp.setOnClickListener(this);

        c = (RadioButton) findViewById(R.id.c);
        c.setOnClickListener(this);

        java = (RadioButton) findViewById(R.id.java);
        java.setOnClickListener(this);

//обработчик кнопки SAVE
        save.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        // тут мы проверяем какой обязательной информации не хватает и просим пользователя ввести ее
                                        String enter = "Enter ";
                                        if (name.getText().toString().equals(""))
                                            enter += "Name ";
                                        if (company.getText().toString().equals(""))
                                            enter += "Company ";
                                        if (email.getText().toString().equals(""))
                                            enter += "Email ";
                                        if (skills == null)
                                            enter += "Skill ";
                                        if (enter.length() != 6)
                                            Toast.makeText(getBaseContext(), enter, Toast.LENGTH_LONG).show();





                                        // если вся обязательная инфа введена то собираем дополнительную инфу из чекбоксов и выводим сообщение
                                        if (enter.equals("Enter ")) {
                                            String str = name.getText().toString() + " " + company.getText().toString() + " " + email.getText().toString() + " ";
                                            if (auto.isChecked()) str += "Auto ";
                                            if (creditCard.isChecked()) str += "Credit Card ";
                                            if (freelance.isChecked()) str += "Freelance ";
                                            if (fullTime.isChecked()) str += "Fulltime ";
                                            if (remote.isChecked()) str += "Remote ";
                                            str += skills;
                                            Toast.makeText(getBaseContext(), str, Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }
        );
    }
    // извлекаем инфу из радиогруппы

    @Override
    public void onClick(View v) {
        RadioButton rb = (RadioButton) v;
        switch (rb.getId()) {
            case R.id.c:
                skills = "C";
                break;

            case R.id.cpp:
                skills = "C++";
                break;
            case R.id.java:
                skills = "Java";
                break;

            default:
                break;
        }

    }
}

