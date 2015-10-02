package turmaformacao.br.agenda.model.services;

import android.app.Activity;

import java.util.List;

import turmaformacao.br.agenda.controller.asynctasks.AsyncDeleteDB;
import turmaformacao.br.agenda.controller.asynctasks.AsyncFindAllDB;
import turmaformacao.br.agenda.controller.asynctasks.AsyncGetAddress;
import turmaformacao.br.agenda.controller.asynctasks.AsyncInterfaceAddress;
import turmaformacao.br.agenda.controller.asynctasks.AsyncInterfaceFindAll;
import turmaformacao.br.agenda.controller.asynctasks.AsyncSaveDB;
import turmaformacao.br.agenda.model.entities.Contact;
import turmaformacao.br.agenda.model.persistence.ContactRepository;

/**
 * Created by Administrador on 01/10/2015.
 */
public final class ContactBusinessService {


    public static void save(Contact contact,Activity context) {
        AsyncSaveDB saveDB = new AsyncSaveDB(context);
        saveDB.execute(contact);
    }


    public static void findAll(Activity context, AsyncInterfaceFindAll activity){
        AsyncFindAllDB findall = new AsyncFindAllDB(context, activity);
        findall.execute();
    }


    public static void getAddressByZipCode(Activity context,AsyncInterfaceAddress formActivity, String zipCode) {
        AsyncGetAddress asyncGetAddress = new AsyncGetAddress(context,formActivity);
        asyncGetAddress.execute(zipCode);
    }


    public static void delete(Activity context,Long id){
        AsyncDeleteDB asyncDeleteDB = new AsyncDeleteDB(context);
        asyncDeleteDB.execute(id);
    }

    public static Long findIdFromLastPosition(){
        return ContactRepository.getIdFromLastPosition();
    }
}
