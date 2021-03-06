package turmaformacao.br.agenda.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import turmaformacao.br.agenda.model.entities.Address;
import turmaformacao.br.agenda.model.entities.Contact;

/**
 * Created by Administrador on 01/10/2015.
 */
public final class ContactContract {

    public static final String TABLE_CONTACT = "contact";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String ZIPCODE = "zipcode";
    public static final String TYPE = "type";
    public static final String STREET = "street";
    public static final String NEIGHBORHOOD = "neighborhood";
    public static final String CITY = "city";
    public static final String STATE = "state";


    public static final String[] columnsContact = {ID, NAME, ZIPCODE, TYPE, STREET, NEIGHBORHOOD, CITY, STATE};


    public ContactContract() {
        super();
    }

    public static String createTableContact() {
        final StringBuilder contact = new StringBuilder();
        contact.append(" create table " + TABLE_CONTACT);
        contact.append(" ( ");
        contact.append(ID + " integer primary key, ");
        contact.append(NAME + " text not null, ");
        contact.append(ZIPCODE + " text, ");
        contact.append(TYPE + " text, ");
        contact.append(STREET + " text, ");
        contact.append(NEIGHBORHOOD + " text, ");
        contact.append(CITY + " text, ");
        contact.append(STATE + " text ");
        contact.append(" ); ");

        return contact.toString();
    }


    public static ContentValues getContentValuesContact(Contact contact) {
        ContentValues values = new ContentValues();
        values.put(ContactContract.ID, contact.getId());
        values.put(ContactContract.NAME, contact.getName());
        values.put(ContactContract.ZIPCODE, contact.getAdress().getZipCode());
        values.put(ContactContract.TYPE, contact.getAdress().getType());
        values.put(ContactContract.STREET, contact.getAdress().getStreet());
        values.put(ContactContract.NEIGHBORHOOD, contact.getAdress().getNeighborhood());
        values.put(ContactContract.CITY, contact.getAdress().getCity());
        values.put(ContactContract.STATE, contact.getAdress().getState());
        return values;
    }


    public static Contact getContact(Cursor cursor) {

        if (!cursor.isBeforeFirst() || cursor.moveToNext()) {
            Contact contact = new Contact();
            contact.setId(cursor.getLong(cursor.getColumnIndex(ID)));
            contact.setName(cursor.getString(cursor.getColumnIndex(NAME)));

            Address address = new Address();
            address.setCity(cursor.getString(cursor.getColumnIndex(CITY)));
            address.setNeighborhood(cursor.getString(cursor.getColumnIndex(NEIGHBORHOOD)));
            address.setState(cursor.getString(cursor.getColumnIndex(STATE)));
            address.setStreet(cursor.getString(cursor.getColumnIndex(STREET)));
            address.setType(cursor.getString(cursor.getColumnIndex(TYPE)));
            address.setZipCode(cursor.getString(cursor.getColumnIndex(ZIPCODE)));

            contact.setAdress(address);

            return contact;

        }

        return null;
    }


    public static List<Contact> getContacts(Cursor cursor){
        List<Contact> contacts = new ArrayList<>();
        while(cursor.moveToNext()){
            contacts.add(getContact(cursor));
        }
        return contacts;
    }

    public static Long getIdFromLastPosition(Cursor cursor){
        if(cursor.moveToLast())
        return cursor.getLong(cursor.getColumnIndex(ID));

        else return null;
    }
}
