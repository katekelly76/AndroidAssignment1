package animalmanagementsystem.katekelly.com.animalmanagement.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import animalmanagementsystem.katekelly.com.animalmanagement.ClickInterface;
import animalmanagementsystem.katekelly.com.animalmanagement.login.crud.MainActivity;
import animalmanagementsystem.katekelly.com.animalmanagement.R;
import animalmanagementsystem.katekelly.com.animalmanagement.user.UserDatabaseHandler;

public class SignupActivity extends AppCompatActivity {

    private static EditText username, password, confirmpassword;
    private static Button signupButton;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        confirmpassword = (EditText) findViewById(R.id.confirm_password);
        signupButton = (Button)findViewById(R.id.button_signup);


        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(username.getText().toString().trim().length() == 0 ||
                        password.getText().toString().trim().length() == 0 ||
                        confirmpassword.getText().toString().trim().length() == 0){

                    Toast.makeText(SignupActivity.this, "Fill in all the fields", Toast.LENGTH_SHORT).show();

                }else if(password.getText().toString().trim().equals(confirmpassword.getText().toString().trim())){

                    Toast.makeText(SignupActivity.this, "Signing you up",
                            Toast.LENGTH_SHORT).show();

                    UserDatabaseHandler db = new UserDatabaseHandler(SignupActivity.this);

                    db.addUser(new ClickInterface.User(username.getText().toString().trim(), password.getText().toString().trim() ));

                    Toast.makeText(SignupActivity.this, "Signed in :" + db.signin(new ClickInterface.User(username.getText().toString().trim(), password.getText().toString().trim() )) ,
                            Toast.LENGTH_SHORT).show();

                    SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(SignupActivity.this);// https://developer.android.com/reference/android/support/v7/preference/PreferenceManager.html
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putBoolean("login", true);
                    editor.commit();

                    startActivity(new Intent(SignupActivity.this, MainActivity.class));
                    SignupActivity.this.finish();



                } else{

                    Toast.makeText(SignupActivity.this, "Password and confirm password fields don't match",
                            Toast.LENGTH_SHORT).show();

                }


            }
        });
    }
}

