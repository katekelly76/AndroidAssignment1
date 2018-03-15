package animalmanagementsystem.katekelly.com.animalmanagement.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import animalmanagementsystem.katekelly.com.animalmanagement.ClickInterface;
import animalmanagementsystem.katekelly.com.animalmanagement.login.crud.MainActivity;
import animalmanagementsystem.katekelly.com.animalmanagement.R;
import animalmanagementsystem.katekelly.com.animalmanagement.user.UserDatabaseHandler;

public class LoginActivity extends AppCompatActivity {

    private static EditText username, password;
    private static Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        // adapted from https://developer.android.com/reference/android/content/SharedPreferences.html

        boolean loggedin = sharedPref.getBoolean("login", false);

        Toast.makeText(this, loggedin+"", Toast.LENGTH_SHORT).show();

        if(loggedin){
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }


        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        loginButton = (Button)findViewById(R.id.button_login);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                loginButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if(username.getText().toString().trim().length() == 0 ||
                                password.getText().toString().trim().length() == 0){

                            Toast.makeText(LoginActivity.this, "Fill in all the fields", Toast.LENGTH_SHORT).show();

                        } else{


                            UserDatabaseHandler db = new UserDatabaseHandler(LoginActivity.this);

                            if(db.signin(new ClickInterface.User(username.getText().toString().trim(), password.getText().toString().trim()))){

                                Toast.makeText(LoginActivity.this, "Loging you in",
                                        Toast.LENGTH_SHORT).show();



                                SharedPreferences sharedPref = LoginActivity.this.getPreferences(Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPref.edit();
                                editor.putBoolean("login", true);
                                editor.commit();
                                // adapted from https://developer.android.com/reference/android/content/SharedPreferences.html

                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                LoginActivity.this.finish();

                            }else{
                                Toast.makeText(LoginActivity.this, "Wrong credentials or the user doesnt exist. Please sign up!",
                                        Toast.LENGTH_SHORT).show();

                            }



                        }




                    }
                });



            }
        });



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);// adapted from https://guides.codepath.com/android/floating-action-buttons
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
                LoginActivity.this.finish();
            }
        });
    }

}
