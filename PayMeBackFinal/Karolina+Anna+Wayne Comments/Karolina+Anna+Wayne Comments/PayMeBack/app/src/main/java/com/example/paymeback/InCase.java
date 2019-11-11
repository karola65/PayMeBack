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
 * This is InCase class.
 *
 * InCase class is connected to incase intent.
 * It is a second part of creating the account, which lets user create the answer for security question incase they forget their password.
 *
 * @autor Karolina
 */

public class InCase extends AppCompatActivity {

    /**
     *
     */
    public TextView textView;
    public TextView textView2;
    public TextView textView3;
    public EditText Email;
    public EditText Answer;
    public Button AddF;
    private AlertDialog.Builder builder;

    private Memory mem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_case);
        Intent intent = getIntent();
        mem = (Memory) intent.getSerializableExtra("mem");


        Answer = (EditText)findViewById(R.id.answer);
        AddF = (Button)findViewById(R.id.buttonAdd);
        textView = (TextView)findViewById(R.id.textView);
        textView3 = (TextView)findViewById(R.id.textView3);
        builder = new AlertDialog.Builder(this);

        /**
         * OnClick listener for add button.
         * Enables user create the security answer.
         * The function checks if the input to the textbox is empty, if so returns the error message to the alter.
         *
         * Otherwise it process to the Main2Activity intent.
         */

        AddF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Answer.getText().toString().equals(""))
                {
                    String popupmesssgae = "Sorry you need to fill in all of the boxes to create the account! " ;
                    builder.setMessage(popupmesssgae);

                    AlertDialog alert = builder.create();
                    alert.setTitle("Ups!");
                    alert.show();
                }
                mem.getUsers().get(mem.getUsers().size()-1).answer = Answer.getText().toString();
                Intent intent = new Intent(InCase.this, Main2Activity.class);
                intent.putExtra("mem",mem);
                String currentXML = MainActivity.writeXml(mem);
                MainActivity.SaveXML(currentXML);
                startActivity(intent);
            }
        });

    }
}