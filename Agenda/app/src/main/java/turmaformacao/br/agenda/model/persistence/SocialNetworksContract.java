package turmaformacao.br.agenda.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import turmaformacao.br.agenda.model.entities.SocialNetwork;

/**
 * Created by Administrador on 01/10/2015.
 */
public final class SocialNetworksContract {

    public static final String TABLE_CONTACT= "contact";
    public static final String TABLE_SOCIALNETWORKS= "socialnetwork";
    public static final String ID = "id";
    public static final String ID_CONTACT = "idContact";
    public static final String SOCIALNETWORK = "socialnetwork";

    public static final String[] columnsSocialNetworks = {ID,ID_CONTACT,SOCIALNETWORK};


    public static String createTableSocialNetworks(){
        final StringBuilder telephone = new StringBuilder();

        telephone.append(" create table "+ TABLE_SOCIALNETWORKS);
        telephone.append(" ( ");
        telephone.append(ID + " integer primary key, ");
        telephone.append(ID_CONTACT + " integer, ");
        telephone.append(SOCIALNETWORK + " text not null, ");
        telephone.append(" foreign key("+ID_CONTACT+") references " + TABLE_CONTACT + "(" + ID + ")");
        telephone.append(" ); ");

        return telephone.toString();
    }


    public static ContentValues getContentValuesSocialNetwork(SocialNetwork socialNetwork) {
        ContentValues valuesTelephone = new ContentValues();
        valuesTelephone.put(SocialNetworksContract.ID_CONTACT, socialNetwork.getId_contact());
        valuesTelephone.put(SocialNetworksContract.SOCIALNETWORK, socialNetwork.getSocialNetwork());
        valuesTelephone.put(SocialNetworksContract.ID,socialNetwork.getId());
        return valuesTelephone;
    }


    public static List<SocialNetwork> getSocialNetworks(Cursor cursor){
        List<SocialNetwork> socialNetworks = new ArrayList<>();
        while(cursor.moveToNext()){
            socialNetworks.add(getSocialNetwork(cursor));
        }
        return socialNetworks;
    }

    private static SocialNetwork getSocialNetwork(Cursor cursor) {
        SocialNetwork socialNetwork = new SocialNetwork();
        if (!cursor.isBeforeFirst() || cursor.moveToNext()) {
            socialNetwork.setSocialNetwork(cursor.getString(cursor.getColumnIndex(SOCIALNETWORK)));
            socialNetwork.setId(cursor.getLong(cursor.getColumnIndex(ID)));
            socialNetwork.setId_contact(cursor.getLong(cursor.getColumnIndex(ID_CONTACT)));
        }
        return socialNetwork;
    }

}
