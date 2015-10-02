package turmaformacao.br.agenda.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import turmaformacao.br.agenda.model.entities.Email;
import turmaformacao.br.agenda.model.entities.Telephone;

/**
 * Created by Administrador on 01/10/2015.
 */
public final class EmailRepository {

    public static void save(Email email) {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues values =  EmailContract.getContentValuesEmail(email);
        if(email.getId()== null){
            db.insert(EmailContract.TABLE_EMAIL, null,values);
        }
        else {
            String where = EmailContract.ID +" = ? ";
            String[] params = {email.getId().toString()};
            db.update(EmailContract.TABLE_EMAIL, values, where, params);
        }

        db.close();
        databaseHelper.close();

    }


    public static List<Email> getAll(){
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        List<Email> values = new ArrayList<>();
        Cursor cursor = db.query(EmailContract.TABLE_EMAIL, EmailContract.columnsEmail, null, null, null, null, ContactContract.ID);
        values.addAll(EmailContract.getEmails(cursor));

        db.close();
        databaseHelper.close();
        return values;
    }


    public static void delete (long id){
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        String where = EmailContract.ID +" = ? ";
        String[] params = {String.valueOf(id)};
        db.delete(EmailContract.TABLE_EMAIL, where, params);

        db.close();
        databaseHelper.close();
    }


    public static List<Email> getEmailByContactId(Long idContact) {

        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        List<Email> values = new ArrayList<>();
        String where = EmailContract.ID_CONTACT +" = ? ";
        String[] params = {String.valueOf(idContact)};
        Cursor cursor = db.query(EmailContract.TABLE_EMAIL, EmailContract.columnsEmail, where, params, null, null, EmailContract.ID);
        values.addAll(EmailContract.getEmails(cursor));

        db.close();
        databaseHelper.close();
        return values;
    }
}
