package turmaformacao.br.agenda.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import turmaformacao.br.agenda.model.entities.SocialNetwork;
import turmaformacao.br.agenda.model.entities.Telephone;

/**
 * Created by Administrador on 01/10/2015.
 */
public final class TelephoneRepository {

    public static void save(Telephone telephone) {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues values =  TelephoneContract.getContentValuesTelephone(telephone);
        if(telephone.getId()== null){
            db.insert(TelephoneContract.TABLE_TELEPHONE, null,values);
        }
        else {
            String where = TelephoneContract.ID +" = ? ";
            String[] params = {telephone.getId().toString()};
            db.update(TelephoneContract.TABLE_TELEPHONE, values, where, params);
        }

        db.close();
        databaseHelper.close();

    }


    public static List<Telephone> getAll(){
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        List<Telephone> values = new ArrayList<>();
        Cursor cursor = db.query(TelephoneContract.TABLE_TELEPHONE, TelephoneContract.columnsTelephones, null, null, null, null, TelephoneContract.ID);
        values.addAll(TelephoneContract.geTelephones(cursor));

        db.close();
        databaseHelper.close();
        return values;
    }


    public static void delete (long id){
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        String where = TelephoneContract.ID +" = ? ";
        String[] params = {String.valueOf(id)};
        db.delete(TelephoneContract.TABLE_TELEPHONE, where, params);

        db.close();
        databaseHelper.close();
    }


    public static List<Telephone> getTelephoneByContactId(Long idContact) {

        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        List<Telephone> values = new ArrayList<>();
        String where = TelephoneContract.ID_CONTACT +" = ? ";
        String[] params = {String.valueOf(idContact)};
        Cursor cursor = db.query(TelephoneContract.TABLE_TELEPHONE, TelephoneContract.columnsTelephones, where, params, null, null, TelephoneContract.ID);
        values.addAll(TelephoneContract.geTelephones(cursor));

        db.close();
        databaseHelper.close();
        return values;
    }
}
