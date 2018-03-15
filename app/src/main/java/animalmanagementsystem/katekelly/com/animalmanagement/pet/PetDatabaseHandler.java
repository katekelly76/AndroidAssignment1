package animalmanagementsystem.katekelly.com.animalmanagement.pet;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import animalmanagementsystem.katekelly.com.animalmanagement.pet.Pet;

/**
 * Created by katekelly on 05/02/2018.
 */

//Reference: https://www.androidhive.info/2011/11/android-sqlite-database-tutorial/
public class PetDatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "animalSystem";

    // Users table name
    private static final String TABLE_PETS = "pets";

    // Users Table Columns names

    private static final String KEY_ID = "id";
    private static final String KEY_OWNER_NAME = "ownername";
    private static final String KEY_OWNER_PHONE = "ownerphone";
    private static final String KEY_PET_NAME = "petname";
    private static final String KEY_PET_AGE = "petage";
    private static final String KEY_PET_GENDER = "petgender";


    // Adding new user
    public void addPet(Pet pet) {

        onCreate(this.getWritableDatabase());

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_OWNER_NAME, pet.getPetOwnerName());
        values.put(KEY_OWNER_PHONE, pet.getPetOwnerPhone());
        values.put(KEY_PET_NAME, pet.getPetName());
        values.put(KEY_PET_AGE, pet.getPetAge());
        values.put(KEY_PET_GENDER, pet.getPetGender());

        // Inserting Row
        db.insert(TABLE_PETS, null, values);
        db.close();

    }

    public List<Pet> getAll(){

        List<Pet> petList = new ArrayList<Pet>();

        String allQuery = "SELECT  * FROM " + TABLE_PETS+ ";";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(allQuery, null);



        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Pet pet = new Pet();

                pet.setId(cursor.getInt(0));
                pet.setPetOwnerName(cursor.getString(1));
                pet.setPetOwnerPhone(cursor.getString(2));
                pet.setPetName(cursor.getString(4));
                pet.setPetGender(cursor.getString(5));
                pet.setPetAge(cursor.getString(3));


                // Adding pet to list
                petList.add(pet);
            } while (cursor.moveToNext());
        }

        // return pet list
        return petList;


    }

    public void delete(int id){

        String deleteQuery = "DELETE FROM " + TABLE_PETS+" WHERE ID="+id;
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PETS, KEY_ID + " = ?",
                new String[] { String.valueOf(id) });
        db.close();


    }

    public int update(Pet pet){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_PET_NAME, pet.getPetName());
        values.put(KEY_PET_AGE, pet.getPetAge());
        values.put(KEY_PET_GENDER, pet.getPetGender());
        values.put(KEY_OWNER_PHONE, pet.getPetOwnerPhone());
        values.put(KEY_OWNER_NAME, pet.getPetOwnerName());

        // updating row
        return db.update(TABLE_PETS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(pet.getId()) });

    }


    public Pet getPet(int ID){


        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_PETS, new String[] { KEY_ID,
                        KEY_OWNER_NAME, KEY_OWNER_PHONE, KEY_PET_NAME, KEY_PET_GENDER, KEY_PET_AGE }, KEY_ID + "=?",
                new String[] { String.valueOf(ID) }, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();


        return new Pet(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4),
                cursor.getString(5)
        );


    }


    public int getPetsCount(){

        try{
            String countQuery = "SELECT  * FROM " + TABLE_PETS;
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(countQuery, null);
            int count = cursor.getCount();
            cursor.close();


            // return count
            return count;


        }catch (Exception e){
            return 0;
        }


    }

    public PetDatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public PetDatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public PetDatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        Log.e("PETHANDLER", "Creating Database");

        String CREATE_PETS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_PETS + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_OWNER_NAME + " TEXT,"
                + KEY_OWNER_PHONE + " TEXT,"
                + KEY_PET_AGE + " TEXT,"
                + KEY_PET_NAME + " TEXT," + KEY_PET_GENDER + " TEXT" + ");";

         db.execSQL(CREATE_PETS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PETS);

        onCreate(db);
    }


}
