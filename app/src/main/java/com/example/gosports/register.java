package com.example.gosports;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class register extends AppCompatActivity {

    EditText et_username, et_email, et_phone, et_password, et_confirm;
    CheckBox cb_terms;
    Button btn_submit;

    private DatabaseHandler db;
    private user user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = new DatabaseHandler(this);
        user = new user();

        et_username = findViewById(R.id.et_username);
        et_email = findViewById(R.id.et_email);
        et_phone = findViewById(R.id.et_phone);
        et_password = findViewById(R.id.et_password);
        et_confirm = findViewById(R.id.et_confirm_password);

        cb_terms = findViewById(R.id.cb_terms);

        btn_submit = findViewById(R.id.btn_submit);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = et_username.getText().toString();
                String email = et_email.getText().toString();
                String phone = et_phone.getText().toString();
                String password = et_password.getText().toString();
                String confirm = et_confirm.getText().toString();

                if (username.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty() || confirm.isEmpty()){
                    Toast.makeText(register.this, "Fields can't be empty!", Toast.LENGTH_SHORT).show();
                }
                else if (!cb_terms.isChecked()){
                    Toast.makeText(register.this, "You must agree to the Terms and Conditions", Toast.LENGTH_SHORT).show();
                }
                else if (!password.equals(confirm)){
                    Toast.makeText(register.this, "Password does not match!", Toast.LENGTH_SHORT).show();
                }
                else {
                    user.setUsername(username);
                    user.setEmail(email);
                    user.setPhone(phone);
                    user.setPassword(password);
                    db.addUser(user);
                    db.close();
                    Toast.makeText(register.this, "Successfully registered!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}