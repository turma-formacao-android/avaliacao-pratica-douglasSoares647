package turmaformacao.br.agenda.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import turmaformacao.br.agenda.model.entities.Contact;
import turmaformacao.br.agenda.model.entities.Email;
import turmaformacao.br.agenda.model.entities.SocialNetwork;

/**
 * Created by Administrador on 01/10/2015.
 */
public final class SocialNetworksRepository {

    public static void save(SocialNetwork socialNetwork) {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues values =  SocialNetworksContract.getContentValuesSocialNetwork(socialNetwork);
        if(socialNetwork.getId()== null){
            db.insert(SocialNetworksContract.TABLE_SOCIALNETWORKS, null,values);
        }
        else {
            String where = SocialNetworksContract.ID +" = ? ";
            String[] params = {socialNetwork.getId().toString()};
            db.update(SocialNetworksContract.TABLE_SOCIALNETWORKS, values, where, params);
        }

        db.close();
        databaseHelper.close();

    }


    public static List<SocialNetwork> getAll(){
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        List<SocialNetwork> values = new ArrayList<>();
        Cursor cursor = db.query(SocialNetworksContract.TABLE_SOCIALNETWORKS, SocialNetworksContract.columnsSocialNetworks, null, null, null, null, SocialNetworksContract.ID);
        values.addAll(SocialNetworksContract.getSocialNetworks(cursor));

        db.close();
        databaseHelper.close();
        return values;
    }


    public static void delete (long id){
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        String where = SocialNetworksContract.ID +" = ? ";
        String[] params = {String.valueOf(id)};
        db.delete(SocialNetworksContract.TABLE_CONTACT, where, params);

        db.close();
        databaseHelper.close();
    }

    public static List<SocialNetwork> getSocialNetworkByContactId(Long idContact) {

        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        List<SocialNetwork> values = new ArrayList<>();
        String where = SocialNetworksContract.ID_CONTACT +" = ? ";
        String[] params = {String.valueOf(idContact)};
        Cursor cursor = db.query(SocialNetworksContract.TABLE_SOCIALNETWORKS, SocialNetworksContract.columnsSocialNetworks, where, params, null, null, SocialNetworksContract.ID);
        values.addAll(SocialNetworksContract.getSocialNetworks(cursor));

        db.close();
        databaseHelper.close();
        return values;
    }
}
