package animalmanagementsystem.katekelly.com.animalmanagement.login.crud;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import animalmanagementsystem.katekelly.com.animalmanagement.R;
import animalmanagementsystem.katekelly.com.animalmanagement.login.LoginActivity;
import animalmanagementsystem.katekelly.com.animalmanagement.pet.Pet;
import animalmanagementsystem.katekelly.com.animalmanagement.pet.PetDatabaseHandler;
import animalmanagementsystem.katekelly.com.animalmanagement.pet.PetsListActivity;
import me.angrybyte.numberpicker.listener.OnValueChangeListener;
import me.angrybyte.numberpicker.view.ActualNumberPicker;

public class MainActivity extends AppCompatActivity implements OnValueChangeListener {


    private EditText ownername;
    private EditText phonenumber;
    private EditText petName;


    private Button addPet;

    private boolean checked;
    private String gender = "";

    private PetDatabaseHandler db;

//    https://github.com/milosmns/actual-number-picker
    private ActualNumberPicker mPicker;
    private int age = 7;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         db = new PetDatabaseHandler(MainActivity.this);

        ownername = (EditText) findViewById(R.id.ownername);
        phonenumber =(EditText) findViewById(R.id.ownerphone);
        petName =(EditText) findViewById(R.id.petname);

        addPet = (Button) findViewById(R.id.button_add);


//        https://github.com/milosmns/actual-number-picker
        mPicker = (ActualNumberPicker) findViewById(R.id.actual_picker);
        mPicker.setListener(this);



        addPet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(checkAllFields()){

                    Toast.makeText(MainActivity.this, "Added "+ ownername.getText().toString()+"'s pet "+
                            petName.getText().toString()+" to the list", Toast.LENGTH_SHORT).show();



                    db.addPet(new Pet(ownername.getText().toString().trim(),
                            phonenumber.getText().toString().trim(),
                            petName.getText().toString().trim(),
                                 gender, age+""));

                    Log.e("MainActivity", db.getPetsCount()+""); //https://stackoverflow.com/questions/7959263/android-log-v-log-d-log-i-log-w-log-e-when-to-use-each-one



                }


            }
        });


    }

    private boolean checkAllFields() {

        if(ownername.getText().toString().trim().length()==0 ||
                petName.getText().toString().trim().length()==0 ||
                phonenumber.getText().toString().trim().length()==0 ){


            Toast.makeText(MainActivity.this, "Enter all the fields", Toast.LENGTH_SHORT).show();
            return false;
        }else if(!checked){
            Toast.makeText(MainActivity.this, "Select the gender", Toast.LENGTH_SHORT).show();
            return false;
        }


            return true;

    }


    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
         checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_male:
                if (checked)
                    gender = "Male";
                    break;
            case R.id.radio_female:
                if (checked)
                    gender = "Female";
                    break;
        }
    }


    @Override
    public void onValueChanged(int oldValue, int newValue) {

        age = newValue;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.pet_list:

                if(db.getPetsCount()<=0){

                    Toast.makeText(MainActivity.this, "No pets registered yet.", Toast.LENGTH_SHORT).show();

                }else{

                    Intent intent = new Intent(MainActivity.this, PetsListActivity.class);
                    startActivity(intent);
                }

                break;

            case R.id.log_out:

                SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putBoolean("login", false);
                editor.commit();


                        startActivity(new Intent(MainActivity.this, LoginActivity.class));
                        MainActivity.this.finish();




                break;
            default:
                break;
        }
        return true;
    }



}
