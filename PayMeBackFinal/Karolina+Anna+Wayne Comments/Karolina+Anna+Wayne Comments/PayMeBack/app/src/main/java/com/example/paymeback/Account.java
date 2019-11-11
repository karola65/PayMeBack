package com.example.paymeback;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * This is a class Account.
 *
 * Class account is an intent connected to create account activity, which lets user create the new account.
 *
 * @author Karolina
 *
 *
 */

public class Account extends AppCompatActivity {

    private EditText Username;
    private EditText Password;
    private EditText FirstName;
    private EditText LastName;
    private EditText Email;
    private Button AddAccount;
    private AlertDialog.Builder builder;

    private Memory mem;

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        Intent intent = getIntent();
        mem = (Memory) intent.getSerializableExtra("mem");

        Username = (EditText)findViewById(R.id.username);
        FirstName = (EditText)findViewById(R.id.firstName);
        LastName = (EditText)findViewById(R.id.lastName);
        Email = (EditText)findViewById(R.id.email);
        Password = (EditText)findViewById(R.id.password);
        builder = new AlertDialog.Builder(this);

        AddAccount = (Button)findViewById(R.id.btnaddAccount);

        /**
         * OnclickLister function which is executed once the button addAccount is clicked.
         *
         * The function checks if all of the textboxes have been filed in , and if username has length between 2 and 15 characters.
         * If so creates a new user, adds it to memory and process to the the InCase intent.
         *
         * If not diplays an error message to the pop out box.
         *
         *
         */

        AddAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                User user = new User();
                if (Username.getText().toString().equals(""))
                {
                    String popupmesssgae ="Sorry you need to fill in all of the boxes to create the account! " ;
                    builder.setMessage(popupmesssgae);

                    AlertDialog alert = builder.create();
                    alert.setTitle("Ups!");
                    alert.show();
                }
                else if (Password.getText().toString().equals(""))
                {
                    String popupmesssgae ="Sorry you need to fill in all of the boxes to create the account! "  ;
                    builder.setMessage(popupmesssgae);

                    AlertDialog alert = builder.create();
                    alert.setTitle("Ups!");
                    alert.show();
                }
                else if (FirstName.getText().toString().equals(""))
                {
                    String popupmesssgae = "Sorry you need to fill in all of the boxes to create the account! " ;
                    builder.setMessage(popupmesssgae);

                    AlertDialog alert = builder.create();
                    alert.setTitle("Ups!");
                    alert.show();
                }
                else if (LastName.getText().toString().equals(""))
                {
                    String popupmesssgae = "Sorry you need to fill in all of the boxes to create the account! " ;
                    builder.setMessage(popupmesssgae);

                    AlertDialog alert = builder.create();
                    alert.setTitle("Ups!");
                    alert.show();
                }
                else if (Email.getText().toString().equals(""))
                {
                    String popupmesssgae = "Sorry you need to fill in all of the boxes to create the account! " ;
                    builder.setMessage(popupmesssgae);

                    AlertDialog alert = builder.create();
                    alert.setTitle("Ups!");
                    alert.show();
                }
                else if (Username.getText().toString().length()>15 || Username.getText().toString().length()<2 )
                {
                    String popupmesssgae = "Sorry the username has to be longer than 2 and shorter than 15 charctaers! " ;
                    builder.setMessage(popupmesssgae);

                    AlertDialog alert = builder.create();
                    alert.setTitle("Ups!");
                    alert.show();
                }
                else {
                    user.firstName = FirstName.getText().toString();
                    user.lastName = LastName.getText().toString();
                    user.userName = Username.getText().toString();
                    user.password = Password.getText().toString();
                    user.email = Email.getText().toString();
                    user.answer = "";
                    user.id = mem.getUsers().size() + 1;

                    mem.addUser(user);

                    Intent intent = new Intent(Account.this, InCase.class);
                    String currentXML = MainActivity.writeXml(mem);
                    MainActivity.SaveXML(currentXML);
                    intent.putExtra("mem",mem);
                    startActivity(intent);}

            }
        });
    }

}