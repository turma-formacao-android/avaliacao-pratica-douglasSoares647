package turmaformacao.br.agenda.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import turmaformacao.br.agenda.model.entities.Email;

/**
 * Created by Administrador on 01/10/2015.
 */
public final class EmailContract {

    public static final String TABLE_CONTACT = "contact";
    public static final String TABLE_EMAIL = "email";
    public static final String ID = "id";
    public static final String ID_CONTACT = "idContact";
    public static final String EMAIL = "email";


    public static final String[] columnsEmail = {ID,ID_CONTACT, EMAIL};

    public static String createTableEmail() {
        final StringBuilder telephone = new StringBuilder();
        telephone.append(" create table " + TABLE_EMAIL);
        telephone.append(" ( ");
        telephone.append(ID + " integer primary key, ");
        telephone.append(ID_CONTACT + " integer, ");
        telephone.append(EMAIL + " text not null, ");
        telephone.append(" foreign key("+ID_CONTACT+") references " + TABLE_CONTACT + "(" + ID + ")");
        telephone.append(" ); ");

        return telephone.toString();
    }


    public static ContentValues getContentValuesEmail(Email email) {
        ContentValues valuesTelephone = new ContentValues();
        valuesTelephone.put(EmailContract.ID, email.getId());
        valuesTelephone.put(EmailContract.ID_CONTACT, email.getId_contact());
        valuesTelephone.put(EmailContract.EMAIL, email.getEmail());
        return valuesTelephone;
    }





    public static List<Email> getEmails(Cursor cursor){
        List<Email> emails = new ArrayList<>();
        while(cursor.moveToNext()){
            emails.add(getEmail(cursor));
        }
        return emails;
    }

    private static Email getEmail(Cursor cursor) {
        Email email = new Email();
        if (!cursor.isBeforeFirst() || cursor.moveToNext()) {

           email.setEmail(cursor.getString(cursor.getColumnIndex(EMAIL)));
            email.setId(cursor.getLong(cursor.getColumnIndex(ID)));
            email.setId_contact(cursor.getLong(cursor.getColumnIndex(ID_CONTACT)));
        }
        return email;
    }

}
