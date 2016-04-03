package com.colon3.cz2006.hawkerdelivery.Boundary;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.colon3.cz2006.hawkerdelivery.Controller.AccountController;
import com.colon3.cz2006.hawkerdelivery.R;

public class LoginActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    private EditText usernameEText;
    private EditText passwordEText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usernameEText = (EditText)findViewById(R.id.username);
        passwordEText = (EditText)findViewById(R.id.password);
        usernameEText.setTypeface(Typeface.DEFAULT);
        passwordEText.setTypeface(Typeface.DEFAULT);
        String[] domain = new String[] {"Customer","Vendor","Delivery Team"};

        Spinner spinner = (Spinner)findViewById(R.id.domain_spinner);

        spinner.setOnItemSelectedListener(this);

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this,R.layout.spinner_item_light,domain);

        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(spinnerAdapter);
        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }

        });*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();

        Toast.makeText(parent.getContext(),item,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void login (View view){
        AccountController accController = new AccountController();
        Spinner spinner = (Spinner)findViewById(R.id.domain_spinner);
        String s = spinner.getSelectedItem().toString();

        String username = usernameEText.getText().toString();
        String password = passwordEText.getText().toString();


        if(accController.isAuthenticated(username,password,s)) {
            if (s.equals("Customer")) {
                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(i);
            }
            else if (s.equals("Vendor")) {

            }
            else if (s.equals("Delivery Team")) {
                Intent i = new Intent(LoginActivity.this, DeliveryActivity.class);
                startActivity(i);
            }
        }
        else {
            android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
            builder.setTitle("ERROR");
            builder.setMessage("You've enter an invalid password!");
            builder.setCancelable(false);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.cancel();
                }
            });
            AlertDialog alert = builder.create();
            alert.show();
        }

    }
}
