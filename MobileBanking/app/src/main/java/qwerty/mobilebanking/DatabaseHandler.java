/*package qwerty.mobilebanking;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.design.widget.TabLayout;

import java.util.ArrayList;

import qwerty.mobilebanking.Model.User;

/**
 * Created by 10 on 6/7/2017.
 */

/*public class DatabaseHandler extends SQLiteOpenHelper {
    private static final String TABEL_USER = "users";
    private static final String ID_USER = "id";
    private static final String NOREK_USER = "no_rek";
    private static final String NAMA_USER = "nama";
    private static final String SALDO_USER = "saldo";
    private static final String KODE_AKSES_USER = "kode_akses";

    public DatabaseHandler(Context context) {
        super(context, "dbUser", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABEL_USER = "CREATE TABLE " + TABEL_USER + "(" + ID_USER + " INTEGER PRIMARY KEY AUTOINCREMENT," + NOREK_USER + " TEXT," + NAMA_USER +" TEXT," + SALDO_USER + " INTEGER," + KODE_AKSES_USER + " TEXT" + ")";
        sqLiteDatabase.execSQL(CREATE_TABEL_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABEL IF EXISTS" + TABEL_USER);
        onCreate(sqLiteDatabase);
    }

    public void addUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(NOREK_USER,user.getNoRek());
        value.put(NAMA_USER,user.getNama());
        value.put(SALDO_USER,user.getSaldo());
        value.put(KODE_AKSES_USER,user.getKodeAkses());
        db.insert(TABEL_USER,null,value);
        db.close();
    }

    public User getUser(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABEL_USER, new String[]{ID_USER,NOREK_USER,NAMA_USER,SALDO_USER,KODE_AKSES_USER},ID_USER+"=?",new String[]{String.valueOf(id)},null,null,null,null);
        if(cursor != null){
            cursor.moveToFirst();
        }
        User user = new User(Integer.parseInt(cursor.getString(0)) , cursor.getString(1),cursor.getString(4),Integer.parseInt(cursor.getString(3)));
        return user;
    }

    public ArrayList<User> getAllUser(){
        ArrayList<User> listUser = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABEL_USER,null);
        if(cursor.moveToFirst()){
            do{
                User user = new User();
                user.setIdUser(Integer.parseInt(cursor.getString(0)));
                user.setNoRek(cursor.getString(1));
                user.setNama(cursor.getString(2));
                user.setSaldo(Integer.parseInt(cursor.getString(3)));
                user.setKodeAkses(cursor.getString(4));
            }while (cursor.moveToNext());
        }
        return listUser;
    }

    public int getUserCount(){
        String count = "SELECT * FROM"+TABEL_USER;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(count,null);
        return cursor.getCount();
    }

    public int updateUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(NAMA_USER,user.getNama());
        value.put(NOREK_USER,user.getNoRek());
        value.put(KODE_AKSES_USER,user.getKodeAkses());
        value.put(SALDO_USER,user.getSaldo());
        return db.update(TABEL_USER,value,ID_USER+ "=?",
                new String[]{String.valueOf(user.getIdUser())});
    }
}*/

