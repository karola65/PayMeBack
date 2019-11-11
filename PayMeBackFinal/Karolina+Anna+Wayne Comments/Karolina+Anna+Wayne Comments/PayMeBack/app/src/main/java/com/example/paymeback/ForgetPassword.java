package com.example.paymeback;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * This is a ForgetPassword Class.
 *
 * It is connected to Forget Password intent.
 * The intent cope with the situation when user forget their password.
 *
 * @author Karolina
 *
 *
 */

public class ForgetPassword extends AppCompatActivity {

    public TextView textView;
    public TextView textView2;
    public TextView textView3;
    public EditText Email;
    public EditText Answer;
    public Button AddF;

    public Button Back;

    private AlertDialog.Builder builder;
    private Memory mem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        Intent intent = getIntent();
        mem = (Memory) intent.getSerializableExtra("mem");

        Email = (EditText) findViewById(R.id.email);
        Answer = (EditText)findViewById(R.id.answer);
        AddF = (Button)findViewById(R.id.buttonAdd);
        textView = (TextView)findViewById(R.id.textView);
        textView2 = (TextView)findViewById(R.id.textView2);
        textView3 = (TextView)findViewById(R.id.textView3);
        Back =(Button)findViewById(R.id.toLogin);
        builder = new AlertDialog.Builder(this);

        /**
         * On Click listener function called on button back
         *
         * This function let user go back to login, by processing to the Main2 Activity.
         *
         */

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ForgetPassword.this, Main2Activity.class);
                intent.putExtra("mem",mem);
                String currentXML = MainActivity.writeXml(mem);
                MainActivity.SaveXML(currentXML);
                startActivity(intent);

            }
        });

        /**
         * OnClick listener function to the button AddF
         *
         */
        AddF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                validate1(Email.getText().toString(), Answer.getText().toString());
            }

            /**
             *
             * This function checks if the username with given email and answer exists.
             *
             * If exists, then changes the password of the user to "1234".
             *
             * If not displays an error message to the pop out AlertDialog box.
             *
             * @param email
             * @param answer
             */

            private void validate1(String email, String answer) {
                int i = 0;
                boolean userexist = true;
                while(userexist)
                {
                    if (email.equals(mem.getUsers().get(i).email)) {
                        if (answer.equals(mem.getUsers().get(i).answer)) {
                            mem.getUsers().get(i).setPassword("1234");

                            String popupmesssgae = "Your password has been changed!";
                            builder.setMessage(popupmesssgae);

                            AlertDialog alert = builder.create();
                            alert.setTitle("Success!");
                            alert.show();
                            userexist = false;
                        }
                        else
                        {
                            String popupmesssgae = "Sorry wrong answer!";
                            builder.setMessage(popupmesssgae);

                            AlertDialog alert = builder.create();
                            alert.setTitle("Ups!");
                            alert.show();

                        }


                    }
                    i = i+1;
                    if(i == mem.getUsers().size() + 1)
                    {
                        String popupmesssgae = "Sorry this username does not exists. Add account! " ;
                        builder.setMessage(popupmesssgae);

                        AlertDialog alert = builder.create();
                        alert.setTitle("Ups!");
                        alert.show();
                        userexist = false;
                    }
                }
            }
        });


    }
}
