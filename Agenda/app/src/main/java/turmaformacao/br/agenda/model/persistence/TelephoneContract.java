package turmaformacao.br.agenda.model.persistence;

import android.content.ContentValues;

/**
 * Created by Administrador on 01/10/2015.
 */
public final class TelephoneContract {

    public static final String TABLE_CONTACT= "contact";
    public static final String TABLE_TELEPHONE= "telephone";
    public static final String ID = "id";
    public static final String ID_CONTACT = "idContact";
    public static final String TELEPHONE = "telephone";

    public static final String[] columnsTelephones = {ID,TELEPHONE};


    public static String createTableTelephones(){
        final StringBuilder telephone = new StringBuilder();

        telephone.append(" create table "+ TABLE_TELEPHONE);
        telephone.append(" ( ");
        telephone.append(ID + " integer primary key, ");
        telephone.append(ID_CONTACT + " integer foreign key "+TABLE_CONTACT+"("+ID+"), ");
        telephone.append(TELEPHONE + " text not null ");
        telephone.append(" ); ");

        return telephone.toString();
    }

    public static ContentValues getContentValuesTelephone(Long idContact, String telephone) {
        ContentValues valuesTelephone = new ContentValues();
        valuesTelephone.put(TelephoneContract.ID_CONTACT, idContact);
        valuesTelephone.put(TelephoneContract.TELEPHONE, telephone);
        return valuesTelephone;
    }
}
