package turmaformacao.br.agenda.model.persistence;

import android.content.ContentValues;

/**
 * Created by Administrador on 01/10/2015.
 */
public final class SocialNetworksContract {

    public static final String TABLE_CONTACT= "contact";
    public static final String TABLE_SOCIALNETWORKS= "socialnetwork";
    public static final String ID = "id";
    public static final String ID_CONTACT = "idContact";
    public static final String SOCIALNETWORK = "socialnetwork";

    public static final String[] columnsSocialNetworks = {ID,SOCIALNETWORK};


    public static String createTableSocialNetworks(){
        final StringBuilder telephone = new StringBuilder();

        telephone.append(" create table "+ TABLE_SOCIALNETWORKS);
        telephone.append(" ( ");
        telephone.append(ID + " integer primary key, ");
        telephone.append(ID_CONTACT + " integer foreign key "+TABLE_CONTACT+"("+ID+"), ");
        telephone.append(SOCIALNETWORK + " text not null ");
        telephone.append(" ); ");

        return telephone.toString();
    }


    public static ContentValues getContentValuesSocialNetwork(Long idContact, String socialNetworks) {
        ContentValues valuesTelephone = new ContentValues();
        valuesTelephone.put(SocialNetworksContract.ID_CONTACT, idContact);
        valuesTelephone.put(SocialNetworksContract.SOCIALNETWORK, socialNetworks);
        return valuesTelephone;
    }
}
