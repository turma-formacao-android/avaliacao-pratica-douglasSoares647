package turmaformacao.br.agenda.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import turmaformacao.br.agenda.model.entities.Contact;

/**
 * Created by Administrador on 01/10/2015.
 */
public final class ContactRepository {
    public static void save(Contact contact) {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues values =  ContactContract.getContentValuesContact(contact);
        if(contact.getId()== null){
            db.insert(ContactContract.TABLE_CONTACT, null,values);
        }
        else {
            String where = ContactContract.ID +" = ? ";
            String[] params = {contact.getId().toString()};
            db.update(ContactContract.TABLE_CONTACT, values, where, params);
        }


        db.close();
        databaseHelper.close();

    }


    public static List<Contact> getAll(){
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        List<Contact> values = new ArrayList<>();
        Cursor cursor = db.query(ContactContract.TABLE_CONTACT, ContactContract.columnsContact, null, null, null, null, ContactContract.ID);
        values.addAll(ContactContract.getContacts(cursor));

        db.close();
        databaseHelper.close();
        return values;
    }


    public static void delete (long id){
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        String where = ContactContract.ID +" = ? ";
        String[] params = {String.valueOf(id)};
        db.delete(ContactContract.TABLE_CONTACT, where, params);

        db.close();
        databaseHelper.close();
    }

    public static Long getIdFromLastPosition(){
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        Long id;
        Cursor cursor = db.query(ContactContract.TABLE_CONTACT, ContactContract.columnsContact, null, null, null, null, ContactContract.ID);
        id = ContactContract.getIdFromLastPosition(cursor);

        db.close();
        databaseHelper.close();
        return id;
    }
}
