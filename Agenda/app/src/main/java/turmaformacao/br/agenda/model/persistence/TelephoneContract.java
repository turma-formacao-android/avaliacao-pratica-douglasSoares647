package turmaformacao.br.agenda.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import turmaformacao.br.agenda.model.entities.Telephone;

/**
 * Created by Administrador on 01/10/2015.
 */
public final class TelephoneContract {

    public static final String TABLE_CONTACT= "contact";
    public static final String TABLE_TELEPHONE= "telephone";
    public static final String ID = "id";
    public static final String ID_CONTACT = "idContact";
    public static final String TELEPHONE = "telephone";

    public static final String[] columnsTelephones = {ID,ID_CONTACT,TELEPHONE};


    public static String createTableTelephones(){
        final StringBuilder telephone = new StringBuilder();

        telephone.append(" create table "+ TABLE_TELEPHONE);
        telephone.append(" ( ");
        telephone.append(ID + " integer primary key, ");
        telephone.append(ID_CONTACT + " integer, ");
        telephone.append(TELEPHONE + " text not null, ");
        telephone.append("foreign key("+ID_CONTACT+") references " + TABLE_CONTACT + "(" + ID + ")");
        telephone.append(" ); ");

        return telephone.toString();
    }

    public static ContentValues getContentValuesTelephone(Telephone telephone) {
        ContentValues valuesTelephone = new ContentValues();
        valuesTelephone.put(TelephoneContract.ID_CONTACT, telephone.getId_contact());
        valuesTelephone.put(TelephoneContract.TELEPHONE, telephone.getTelephone());
        valuesTelephone.put(TelephoneContract.ID, telephone.getId());
        return valuesTelephone;
    }


    public static List<Telephone> geTelephones(Cursor cursor){
        List<Telephone> telephones = new ArrayList<>();
        while(cursor.moveToNext()){
            telephones.add(getTelehone(cursor));
        }
        return telephones;
    }

    private static Telephone getTelehone(Cursor cursor) {
        Telephone telephone = new Telephone();
        if (!cursor.isBeforeFirst() || cursor.moveToNext()) {
            telephone.setTelephone(cursor.getString(cursor.getColumnIndex(TELEPHONE)));
            telephone.setId_contact(cursor.getLong(cursor.getColumnIndex(ID_CONTACT)));
            telephone.setId(cursor.getLong(cursor.getColumnIndex(ID)));
        }
        return telephone;
    }
}
