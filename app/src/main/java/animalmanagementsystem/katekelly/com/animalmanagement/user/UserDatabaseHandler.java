package animalmanagementsystem.katekelly.com.animalmanagement.user;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import animalmanagementsystem.katekelly.com.animalmanagement.ClickInterface;

/**
 * Created by katekelly on 27/02/2018.
 */


//Reference: https://www.androidhive.info/2011/11/android-sqlite-database-tutorial/
public class UserDatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "animalSystem";

    // Users table name
    private static final String TABLE_USERS = "users";

    // Users Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";




    // Adding new user
    public void addUser(ClickInterface.User user) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_USERNAME, user.getUsername());
        values.put(KEY_PASSWORD, user.getPassword());

        // Inserting Row
        db.insert(TABLE_USERS, null, values);
        db.close();

    }




    // Getting single contact
    public boolean signin(ClickInterface.User user) {

        SQLiteDatabase db = this.getReadableDatabase();

        String countQuery = "SELECT  * FROM " + TABLE_USERS +
                " WHERE "+ KEY_USERNAME +" = '"+ user.getUsername() +
                "' AND "+ KEY_PASSWORD + " = '" + user.getPassword() + "';";

        Cursor cursor = db.rawQuery(countQuery, null);


        if(cursor.getCount()>=1){
            cursor.close();
            return true;
        } else{
            cursor.close();
            return  false;
        }

    }





    public UserDatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    public UserDatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public UserDatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USERS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_USERNAME + " TEXT,"
                + KEY_PASSWORD + " TEXT" + ")";
        db.execSQL(CREATE_USER_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);

        onCreate(db);

    }
}
