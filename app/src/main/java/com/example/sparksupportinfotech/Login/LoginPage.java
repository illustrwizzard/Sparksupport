package com.example.sparksupportinfotech.Login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sparksupportinfotech.DashBoard.DashBoardActivity;
import com.example.sparksupportinfotech.R;
import com.example.sparksupportinfotech.Register.RegisterPage;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPage extends AppCompatActivity {
    LoginUserViewModel loginUserViewModel;
    //String token,liveEmail,liveFname;
    SharedPreferences.Editor editor;

    SharedPreferences sharedPreferences;

    @Override
    public void onBackPressed() {

        finishAffinity();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

         sharedPreferences = getSharedPreferences("myPreef", Context.MODE_PRIVATE);
         editor = sharedPreferences.edit();

        EditText usernameEditText = findViewById(R.id.usernameEditText);
        EditText passwordEditText = findViewById(R.id.passwordEditText);
        Button loginButton = findViewById(R.id.loginButton);

        TextView registerTextView = findViewById(R.id.registerTextView);
        loginUserViewModel=new ViewModelProvider(this).get(LoginUserViewModel.class);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();


                if (!username.isEmpty() & !password.isEmpty()) {

                    loginUserViewModel.login(username,password);


                } else {
                    // Failed login
                    Toast.makeText(LoginPage.this, "Field Cannot be empty", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Observe LiveData from the ViewModel to update the UI
        loginUserViewModel.getSuccessMessage().observe(this, message -> {

            editor.putInt("check",1);



            LiveData<String> tokenLiveData = loginUserViewModel.getTokenLiveData();
            tokenLiveData.observe(this,token->{
               editor.putString("token", token);
               editor.apply();
            });
            LiveData<String> LiveEmail= loginUserViewModel.getLiveEmail();
            LiveEmail.observe(this,liveEmail->{

                editor.putString("Email",liveEmail );
                editor.apply();

            });

            LiveData<String> LiveFirstName= loginUserViewModel.getLiveFirstName();
            LiveFirstName.observe(this,liveFname->{

                editor.putString("Fname",liveFname );
                editor.apply();


            });

            // SharedPreferences sharedPreferences=getSharedPreferences("myPreef", Context.MODE_PRIVATE);
//                SharedPreferences.Editor editor=sharedPreferences.edit();
//                editor.putInt("check",1);
//                editor.apply();

//            Intent intent=new Intent(LoginPage.this, DashBoardActivity.class);
//            startActivity(intent);


            editor.apply();

            Intent intent=new Intent(LoginPage.this, DashBoardActivity.class);
            startActivity(intent);

        });

        loginUserViewModel.getErrorMessage().observe(this, message -> {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();

        });

        loginUserViewModel.isLoading().observe(this, isLoading -> {

        });









        registerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(LoginPage.this, RegisterPage.class);
                startActivity(i);
            }
        });
    }


}