package com.collegerosemont.o_raymond_guichet_travail;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Login_ extends AppCompatActivity {

    int failedLogin = 0;
    boolean loginSuccess;
    String messageEnvoye = "Message provenant de l'activite LOGIN";

    private static final int RESULT_LOGIN_FAILED = 1;
    ActivityResultLauncher<Intent> activityResultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if(result.getResultCode() == RESULT_LOGIN_FAILED){
                            failedLogin++;
                            String value = "number of fails = " + failedLogin;
                            Toast.makeText(Login_.this, value, Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
    public void onClickValiderInformations(View view) {

        EditText editTextUsername = findViewById(R.id.editTextTextPersonName);
        EditText editTextPassword = findViewById(R.id.editTextNumberPassword2);
        String reponseUsername = editTextUsername.getText().toString();
        String reponseNip = editTextPassword.getText().toString();
        if(failedLogin >= 3){
            Toast.makeText(Login_.this, "Veuillez réessayer plus tard", Toast.LENGTH_LONG).show();
        }else{
            if (reponseNip.equals("") || reponseUsername.equals("")){
                Toast.makeText(Login_.this, "Veuillez remplir tous les champs obligatoires", Toast.LENGTH_LONG).show();
            } else {
                //start main activity
                Intent intent = new Intent(this, ATM.class);
                intent.putExtra("username",reponseUsername);
                intent.putExtra("nip", reponseNip);
                activityResultLauncher.launch(intent);
            }
            //Fixme

        }


    }



}