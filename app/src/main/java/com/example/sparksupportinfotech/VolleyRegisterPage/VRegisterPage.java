package com.example.sparksupportinfotech.VolleyRegisterPage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sparksupportinfotech.Login.LoginPage;
import com.example.sparksupportinfotech.R;
import com.example.sparksupportinfotech.Register.RegisterPage;

public class VRegisterPage extends AppCompatActivity {
    EditText emailEditText;
    EditText firstNameEditText;
    EditText lastNameEditText;
    EditText usernameEditText;
    EditText passwordEditText;
    EditText confirmPasswordEditText;
    Button registerButton;
    private RegistrationViewModel viewModel;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vregister_page);
        viewModel = ViewModelProviders.of(this).get(RegistrationViewModel.class);

        emailEditText     = findViewById(R.id.emailEditText2);
        firstNameEditText  = findViewById(R.id.firstNameEditText2);
        lastNameEditText  = findViewById(R.id.lastNameEditText2);
        usernameEditText  = findViewById(R.id.usernameEditText2);
        passwordEditText  = findViewById(R.id.passwordEditText2);
         confirmPasswordEditText = findViewById(R.id.confirmPasswordEditText2);
        registerButton = findViewById(R.id.registerButton2);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String email=emailEditText.getText().toString();
                String first_name=firstNameEditText.getText().toString();
                String last_name=lastNameEditText.getText().toString();
                String username=usernameEditText.getText().toString();
                String password=passwordEditText.getText().toString();
                String password2=confirmPasswordEditText.getText().toString();


                if (!email.isEmpty()& !first_name.isEmpty() & !last_name.isEmpty() & !username.isEmpty() & !password.isEmpty() & !password2.isEmpty()){
                    if (password.equals(password2)){

                        viewModel.getRegistrationResponse().observe(VRegisterPage.this, new Observer<String>() {
                            @Override
                            public void onChanged(String response) {
                                // Update UI with the registration response
                                // You can display a message or perform other UI updates here
                                Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(VRegisterPage.this, LoginPage.class);
                                startActivity(intent);
                            }
                        });

                        // Create a User object with the registration data
                        User user = new User();
                        user.setUsername(username);
                        user.setPassword(password);
                        user.setPassword2(password2);
                        user.setEmail(email);
                        user.setFirst_name(first_name);
                        user.setLast_name(last_name);

                        // Initiate the registration process
                        viewModel.registerUser(user);


                    }else {
                        Toast.makeText(VRegisterPage.this, "The Password is not matching", Toast.LENGTH_SHORT).show();
                    }
                }else{

                    Toast.makeText(VRegisterPage.this, "Fields Cannot be empty", Toast.LENGTH_SHORT).show();

                }


            }
        });
    }
}