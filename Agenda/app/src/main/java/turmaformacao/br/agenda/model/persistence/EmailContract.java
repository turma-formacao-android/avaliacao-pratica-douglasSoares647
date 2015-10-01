package turmaformacao.br.agenda.model.persistence;

import android.content.ContentValues;

/**
 * Created by Administrador on 01/10/2015.
 */
public final class EmailContract {

    public static final String TABLE_CONTACT = "contact";
    public static final String TABLE_EMAIL = "email";
    public static final String ID = "id";
    public static final String ID_CONTACT = "idContact";
    public static final String EMAIL = "email";


    public static final String[] columnsSocialNetworks = {ID_CONTACT, EMAIL};

    public static String createTableEmail() {
        final StringBuilder telephone = new StringBuilder();
        telephone.append(" create table " + TABLE_EMAIL);
        telephone.append(" ( ");
        telephone.append(ID + " integer primary key, ");
        telephone.append(ID_CONTACT + " integer foreign key " + TABLE_CONTACT + "(" + ID + "), ");
        telephone.append(EMAIL + " text not null ");
        telephone.append(" ); ");

        return telephone.toString();
    }


    public static ContentValues getContentValuesEmail(Long idContact, String email) {
        ContentValues valuesTelephone = new ContentValues();
        valuesTelephone.put(EmailContract.ID_CONTACT, idContact);
        valuesTelephone.put(EmailContract.EMAIL, email);
        return valuesTelephone;
    }

}
