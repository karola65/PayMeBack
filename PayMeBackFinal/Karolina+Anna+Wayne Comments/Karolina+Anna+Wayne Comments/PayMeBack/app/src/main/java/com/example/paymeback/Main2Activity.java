package com.example.paymeback;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.widget.EditText;

public class Main2Activity extends AppCompatActivity {

    /**
     * The Main2Activity intent
     *
     *
     * This intent is a copy of MainActivity intent.
     * It is used when we need to pass the data in the form of mem from some other intent to the login page.
     *
     *
     * @autor Karolina
     */

    private  Memory mem;
    private EditText Username;
    private EditText Password;
    private Button Login;
    private Button ForgetPassword;
    private Button AddAccount;
    private int numberoftries = 5;
    private AlertDialog.Builder builder;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent intent = getIntent();
        mem = (Memory) intent.getSerializableExtra("mem");



        Username = (EditText)findViewById(R.id.username2);
        Password = (EditText)findViewById(R.id.password2);
        Login = (Button)findViewById(R.id.btnlogin2);
        ForgetPassword = (Button)findViewById(R.id.btnforgetpassword2);
        AddAccount = (Button)findViewById(R.id.btnaddAccount2);
        builder = new AlertDialog.Builder(this);
        //builder.setMessage(R.string.dialog_message) .setTitle(R.string.dialog_title);


        /**
         * An onclick function which let the user login to the application.
         */

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(Username.getText().toString(), Password.getText().toString());


            }
        });


        /**
         * An onclick function which let the user process to Account intent.
         */



        AddAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2Activity.this, Account.class);
                intent.putExtra("mem",mem);
                startActivity(intent);
            }
        });


        /**
         * An onclick function which let the user process to ForgetPassword intent.
         */
        ForgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2Activity.this, ForgetPassword.class);
                intent.putExtra("mem",mem);
                startActivity(intent);
            }
        });
    }


    /**
     *
     * A validate method.
     *
     * This method validates if username and password are correct and returns the action accordingly.
     *
     * If it is correct the method takes user to AllGroupList intent.
     *
     * If the username is correct but the password is not it return the error message to the alert, and counts the lost chance.
     * User has 5 chances to input wrong password, after that the Login button gets disabled.
     *
     * If both username and password are incorrect the program returns the error message to alert, suggesting the creation of new account.
     *
     * @param  username
     * @param  password
     * @return void
     *
     */

    private void validate(String username, String password)
    {
        int i = 0;
        boolean userexist = true;
        while(userexist)
        {
            if (username.equals(mem.getUsers().get(i).userName)) {
                if (password.equals(mem.getUsers().get(i).password)) {
                    Intent intent = new Intent(Main2Activity.this, AllGroupList.class);
                    mem.setMainuser(mem.getUsers().get(i));
                    intent.putExtra("mem",mem);
                    startActivity(intent);
                    userexist = false;
                }
                else
                {
                    numberoftries --;

                    String popupmesssgae = "Sorry wrong password you have " +numberoftries+ " tries left" ;
                    builder.setMessage(popupmesssgae);

                    AlertDialog alert = builder.create();
                    alert.setTitle("Ups!");
                    alert.show();
                    userexist = false;
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

        if(numberoftries == 0)
        {

            Login.setEnabled(false);
        }

    }






}