package com.example.ivann.myapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //обьявляем контролы
    EditText Name, Company, Email;
    Button Save;
    CheckBox Auto, CreditCard, Freelance, FullTime, Remote;
    RadioButton Cpp, C, Java;
    String Skills;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //инициализируем контролы
        Name = (EditText) findViewById(R.id.Name);
        Company = (EditText) findViewById(R.id.Company);
        Email = (EditText) findViewById(R.id.Email);
        Save = (Button) findViewById(R.id.ButtonSave);
        Auto = (CheckBox) findViewById(R.id.Auto);
        CreditCard = (CheckBox) findViewById(R.id.CreditCard);
        Freelance = (CheckBox) findViewById(R.id.Freelance);
        FullTime = (CheckBox) findViewById(R.id.Fulltime);
        Remote = (CheckBox) findViewById(R.id.Remote);

        Cpp = (RadioButton) findViewById(R.id.Cpp);
        Cpp.setOnClickListener(radioButtonClickListener);

        C = (RadioButton) findViewById(R.id.C);
        C.setOnClickListener(radioButtonClickListener);

        Java = (RadioButton) findViewById(R.id.Java);
        Java.setOnClickListener(radioButtonClickListener);

//обработчик кнопки SAVE
        Save.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        // тут мы проверяем какой обязательной информации не хватает и просим пользователя ввести ее
                                        String enter = "Enter ";
                                        if (Name.getText().toString().equals(""))
                                            enter += "Name ";
                                        if (Company.getText().toString().equals(""))
                                            enter += "Company ";
                                        if (Email.getText().toString().equals(""))
                                            enter += "Email ";
                                        if (Skills == null)
                                            enter += "Skill ";
                                        if (enter.length() != 6) {
                                            Toast msg = Toast.makeText(getBaseContext(), enter, Toast.LENGTH_LONG);
                                            msg.show();
                                        }




                                        // если вся обязательная инфа введена то собираем дополнительную инфу из чекбоксов и выводим сообщение
                                        if (enter.equals("Enter ")) {
                                            String str = Name.getText().toString() + " " + Company.getText().toString() + " " + Email.getText().toString() + " ";
                                            if (Auto.isChecked()) str += "Auto ";
                                            if (CreditCard.isChecked()) str += "Credit Card ";
                                            if (Freelance.isChecked()) str += "Freelance ";
                                            if (FullTime.isChecked()) str += "Fulltime ";
                                            if (Remote.isChecked()) str += "Remote ";
                                            str += Skills;
                                            Toast msg = Toast.makeText(getBaseContext(), str, Toast.LENGTH_LONG);
                                            msg.show();
                                        }
                                    }
                                }
        );
    }
    // извлекаем инфу из радиогруппы
    View.OnClickListener radioButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            RadioButton rb = (RadioButton) v;
            switch (rb.getId()) {
                case R.id.C:
                    Skills = "C";
                    break;

                case R.id.Cpp:
                    Skills = "C++";
                    break;
                case R.id.Java:
                    Skills = "Java";
                    break;

                default:
                    break;
            }
        }
    };
    }

