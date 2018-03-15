package animalmanagementsystem.katekelly.com.animalmanagement.login.crud;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import animalmanagementsystem.katekelly.com.animalmanagement.R;
import animalmanagementsystem.katekelly.com.animalmanagement.pet.Pet;
import animalmanagementsystem.katekelly.com.animalmanagement.pet.PetDatabaseHandler;
import me.angrybyte.numberpicker.listener.OnValueChangeListener;
import me.angrybyte.numberpicker.view.ActualNumberPicker;

public class EditActivity extends AppCompatActivity implements OnValueChangeListener { // https://stackoverflow.com/questions/15983120/implementing-onvaluechange-to-a-numberpicker-in-android

    private EditText ownername;
    private EditText phonenumber;
    private EditText petName;


    private RadioButton maleButton;
    private RadioButton femaleButton;


    private Button updatePet;

    private boolean checked = true;
    private String gender = "";

    private PetDatabaseHandler db;

    //    https://github.com/milosmns/actual-number-picker
    private ActualNumberPicker mPicker;
    private int age = 7;

    int _id;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);


         _id = getIntent().getExtras().getInt("id");//pass between activities
        // adapted from: https://stackoverflow.com/questions/2137514/getintextra-always-returns-the-default-value //










        db = new PetDatabaseHandler(EditActivity.this);




        ownername = (EditText) findViewById(R.id.ownername);
        phonenumber =(EditText) findViewById(R.id.ownerphone);
        petName =(EditText) findViewById(R.id.petname);
        maleButton = (RadioButton) findViewById(R.id.radio_male);
        femaleButton = (RadioButton) findViewById(R.id.radio_female);

        updatePet = (Button) findViewById(R.id.button_add);


//        https://github.com/milosmns/actual-number-picker
        mPicker = (ActualNumberPicker) findViewById(R.id.actual_picker);
        mPicker.setListener(this);


        //Setting up the fields first

        Pet _pet = db.getPet(_id);

        ownername.setText(_pet.getPetOwnerName());
        phonenumber.setText(_pet.getPetOwnerPhone());
        petName.setText(_pet.getPetName());

        mPicker.setValue(Integer.parseInt(_pet.getPetAge()));

        if(_pet.getPetGender().contains("Female")){
            gender="Female";
            femaleButton.setChecked(true);

        }else{
            gender = "Male";
            maleButton.setChecked(true);
        }





        updatePet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(checkAllFields()){

                    Toast.makeText(EditActivity.this, "Details Updated", Toast.LENGTH_SHORT).show();



                    db.update(new Pet(_id, ownername.getText().toString().trim(), //https://developer.android.com/reference/java/lang/String.html
                            phonenumber.getText().toString().trim(),
                            petName.getText().toString().trim(),
                            gender, age+""));

                    Log.e("MainActivity", db.getPetsCount()+"");

                    EditActivity.this.finish(); // https://www.youtube.com/watch?v=F3kaYKqnUJg



                }


            }
        });


    }

    private boolean checkAllFields() { // https://developer.android.com/reference/java/lang/String.html

        if(ownername.getText().toString().trim().length()==0 ||
                petName.getText().toString().trim().length()==0 ||
                phonenumber.getText().toString().trim().length()==0 ){


            Toast.makeText(EditActivity.this, "Enter all the fields", Toast.LENGTH_SHORT).show();
            return false;
        }else if(!checked){
            Toast.makeText(EditActivity.this, "Select the gender", Toast.LENGTH_SHORT).show();
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
    public void onValueChanged(int oldValue, int newValue) { // https://stackoverflow.com/questions/15983120/implementing-onvaluechange-to-a-numberpicker-in-android

        age = newValue;

    }
}
