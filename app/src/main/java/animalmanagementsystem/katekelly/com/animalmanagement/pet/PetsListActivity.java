package animalmanagementsystem.katekelly.com.animalmanagement.pet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import animalmanagementsystem.katekelly.com.animalmanagement.ClickInterface;
import animalmanagementsystem.katekelly.com.animalmanagement.login.crud.EditActivity;
import animalmanagementsystem.katekelly.com.animalmanagement.R;


//Reference : https://www.raywenderlich.com/124438/android-listview-tutorial
//Reference : https://stackoverflow.com/questions/994840/how-to-create-our-own-listener-interface-in-android
public class PetsListActivity extends AppCompatActivity {


    private RecyclerView petsRecyclerView;
    private RecyclerView.LayoutManager layoutManager;
    List<Pet> petList = new ArrayList<Pet>();
    PetDatabaseHandler db;

    private PetsRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pets_list);

         db = new PetDatabaseHandler(PetsListActivity.this);


        adapter = new PetsRecyclerViewAdapter(this, db.getAll(), db, new ClickInterface() {
            @Override
            public void clickDelete(int id) {

                Toast.makeText(PetsListActivity.this, "Deleted"+ id, Toast.LENGTH_SHORT).show();
                db.delete(id);


                adapter = new PetsRecyclerViewAdapter(db.getAll());

                petsRecyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();


                finish();
                startActivity(getIntent());


            }

            @Override
            public void clickEdit(int id) {


                Intent intent =  new Intent(PetsListActivity.this, EditActivity.class);
                intent.putExtra("id", id);

                startActivity(intent);



            }
        });

        petsRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        petsRecyclerView.setLayoutManager(layoutManager);
        petsRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        Log.e("COUNT", db.getPetsCount()+"");






    }

    @Override
    protected void onResume() {
        super.onResume(); // https://developer.android.com/reference/android/app/Activity.html#onResume()

    }

    @Override
    protected void onRestart() { // https://developer.android.com/reference/android/app/Activity.html#onRestart()
        super.onRestart();

        finish(); // https://www.youtube.com/watch?v=F3kaYKqnUJg
        startActivity(getIntent());
    }
}
