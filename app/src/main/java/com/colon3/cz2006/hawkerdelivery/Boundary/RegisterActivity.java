package com.colon3.cz2006.hawkerdelivery.Boundary;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.colon3.cz2006.hawkerdelivery.DAO.AccountDAO;
import com.colon3.cz2006.hawkerdelivery.DAO.AccountDAOImpl;
import com.colon3.cz2006.hawkerdelivery.DAO.CustomerDAOImpl;
import com.colon3.cz2006.hawkerdelivery.DAO.DeliveryDAO;
import com.colon3.cz2006.hawkerdelivery.DAO.DeliveryDAOImpl;
import com.colon3.cz2006.hawkerdelivery.Entity.Account;
import com.colon3.cz2006.hawkerdelivery.Entity.CustomerAccount;
import com.colon3.cz2006.hawkerdelivery.Entity.DeliveryAccount;
import com.colon3.cz2006.hawkerdelivery.R;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button customer = (Button) findViewById(R.id.customer_btn);
        customer.setOnClickListener(this);
        Button vendor = (Button) findViewById(R.id.vendor_btn);
        vendor.setOnClickListener(this);
        Button delivery = (Button) findViewById(R.id.delivery_btn);
        delivery.setOnClickListener(this);
    }

    public void onClick(View v) {
        LayoutInflater li = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final AccountDAO accountDAO = new AccountDAOImpl(this);
        switch (v.getId()) {
            case R.id.customer_btn:
                v = li.inflate(R.layout.customer_layout,null);
                Button customer_submit_btn = (Button) v.findViewById(R.id.customer_submit_btn);
                customer_submit_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(view.getContext());
                        CustomerDAOImpl customerDAO = new CustomerDAOImpl(view.getContext());
                        View v = view.getRootView();
                        String username = ((EditText) v.findViewById(R.id.customer_username)).getText().toString();
                        String password = ((EditText) v.findViewById(R.id.customer_password)).getText().toString();
                        String name = ((EditText) v.findViewById(R.id.customer_name)).getText().toString();
                        String confirm_password = ((EditText) v.findViewById(R.id.customer_confirm_password)).getText().toString();
                        String address = ((EditText) v.findViewById(R.id.customer_address)).getText().toString();
                        if (confirm_password.equals(password)) {
                            CustomerAccount customerAccount = new CustomerAccount();
                            customerAccount.setId(customerDAO.getLastId() + 1);
                            customerAccount.setUsername(username);
                            customerAccount.setPassword(password);
                            customerAccount.setName(name);
                            customerAccount.setAddress(address);
                            customerDAO.addCustomerAccount(customerAccount);

                            Account account = new Account();
                            account.setId(accountDAO.getLastId() + 1);
                            account.setDomain("Customer");
                            account.setUsername(username);
                            account.setPassword(password);
                            accountDAO.addAccount(account);

                            Toast.makeText(v.getContext(), "Successfully registered", Toast.LENGTH_LONG).show();
                        }
                        else {
                            builder.setTitle("Invalid Password");
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
                });
                break;

            case R.id.vendor_btn:
                v = li.inflate(R.layout.vendor_layout, null);
                break;

            case R.id.delivery_btn:
                v = li.inflate(R.layout.delivery_layout, null);
                final Button delivery_submit_btn = (Button) v.findViewById(R.id.delivery_submit_btn);
                delivery_submit_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(view.getContext());
                        DeliveryDAO deliveryDAO = new DeliveryDAOImpl(view.getContext());
                        View v = view.getRootView();
                        String username = ((EditText) v.findViewById(R.id.delivery_username)).getText().toString();
                        String password = ((EditText) v.findViewById(R.id.delivery_password)).getText().toString();
                        String name = ((EditText) v.findViewById(R.id.delivery_name)).getText().toString();
                        String confirm_password = ((EditText) v.findViewById(R.id.delivery_confirm_password)).getText().toString();
                        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.sex_radio_group);
                        String sex = ((RadioButton)findViewById(radioGroup.getCheckedRadioButtonId())).getText().toString();
                        if (confirm_password.equals(password)) {
                            DeliveryAccount deliveryAccount = new DeliveryAccount();
                            deliveryAccount.setId(deliveryDAO.getLastId() + 1);
                            deliveryAccount.setUsername(username);
                            deliveryAccount.setPassword(password);
                            deliveryAccount.setName(name);
                            deliveryAccount.setSex(sex);

                            Account account = new Account();
                            account.setId(accountDAO.getLastId() + 1);
                            account.setDomain("Delivery Team");
                            account.setUsername(username);
                            account.setPassword(password);
                            accountDAO.addAccount(account);

                            Toast.makeText(v.getContext(), "Successfully registered", Toast.LENGTH_LONG).show();
                        }
                        else {
                            builder.setTitle("Invalid Password");
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
                });
                break;
        }
        setContentView(v);
    }

    /*public void submit(final View view) {
        Button customer = (Button)findViewById(R.id.customer_submit_btn);
        customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick() {
                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(view.getContext());
                switch (view.getId()) {
                    case R.id.customer_submit_btn:
                        CustomerDAOImpl customerAccountDAO = new CustomerDAOImpl(view.getContext());
                        String username = ((EditText) findViewById(R.id.customer_username)).getText().toString();
                        String password = ((EditText) findViewById(R.id.customer_password)).getText().toString();
                        String name = ((EditText) findViewById(R.id.customer_name)).getText().toString();
                        String confirm_password = ((EditText) findViewById(R.id.customer_confirm_password)).getText().toString();
                        String address = ((EditText) findViewById(R.id.customer_address)).getText().toString();
                        if (confirm_password.equals(password)) {
                            CustomerAccount customerAccount = new CustomerAccount();
                            customerAccount.setUsername(username);
                            customerAccount.setPassword(password);
                            customerAccount.setName(name);
                            customerAccount.setAddress(address);
                            customerAccountDAO.addCustomerAccount(customerAccount);

                            Toast.makeText(view.getContext(), "Successfully registered", Toast.LENGTH_LONG).show();
                        }
                        else {
                            builder.setTitle("Invalid Password");
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
                        break;
                    case R.id.vendor_submit_btn:
                        break;
                    case R.id.delivery_submit_btn:
                        break;
                }
            }
        });
    }*/
}
