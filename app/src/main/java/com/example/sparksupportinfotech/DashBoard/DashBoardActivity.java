package com.example.sparksupportinfotech.DashBoard;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sparksupportinfotech.Login.LoginPage;
import com.example.sparksupportinfotech.R;

import java.util.List;

public class DashBoardActivity extends AppCompatActivity {
    ImageView logoutimg;
    TextView showvalue;
    private OnBackPressedCallback onBackPressedCallback;

    private RecyclerView recyclerView;
    private ImageAdapter imageAdapter;
    private ImageViewModel imageViewModel;
    private String authToken;




    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        logoutimg=findViewById(R.id.logoutimg);
        showvalue=findViewById(R.id.showvalue);
        onBackPressedCallback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {

                AlertDialog.Builder builder1 = new AlertDialog.Builder(DashBoardActivity.this);
                builder1.setMessage("Do you want to Exit?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                finishAffinity();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                dialog.dismiss();
                            }
                        });

                builder1.create().show();

                // Your business logic to handle the back pressed event
                //Log.d(TAG, "onBackPressedCallback: handleOnBackPressed");
            }
        };

        getOnBackPressedDispatcher().addCallback(this, onBackPressedCallback);

        recyclerView = findViewById(R.id.recyclerView); // Replace with your RecyclerView's ID


        SharedPreferences sharedPreferences=getSharedPreferences("myPreef", Context.MODE_PRIVATE);
        String savedvalue=sharedPreferences.getString("Email","");
        String savedname=sharedPreferences.getString("Fname","");
        showvalue.setText("Name : "+savedname+ "\n" +"Email : "+savedvalue);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        imageAdapter = new ImageAdapter(this);
        imageViewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication()).create(ImageViewModel.class);

        //SharedPreferences sharedPreferences1 = PreferenceManager.getDefaultSharedPreferences(this);
        authToken = sharedPreferences.getString("token", "");
        imageViewModel.getImageList(authToken).observe(this, new Observer<List<DashboardResponse>>() {
            @Override
            public void onChanged(List<DashboardResponse> imageItems) {
                imageAdapter.setData(imageItems);
            }
        });

        recyclerView.setAdapter(imageAdapter);









        logoutimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(DashBoardActivity.this);
                builder.setMessage("Do you want to Logout?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                SharedPreferences sharedPreferences = getSharedPreferences("myPreef", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                //editor.remove("key_name");
                                editor.clear();
                                editor.apply();

                                Intent intent=new Intent(DashBoardActivity.this, LoginPage.class);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                dialog.dismiss();
                            }
                        });

                builder.create().show();

            }
        });
    }


}