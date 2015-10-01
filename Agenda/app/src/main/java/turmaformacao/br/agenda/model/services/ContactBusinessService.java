package turmaformacao.br.agenda.model.services;

import turmaformacao.br.agenda.model.entities.Contact;
import turmaformacao.br.agenda.model.persistence.ContactRepository;

/**
 * Created by Administrador on 01/10/2015.
 */
public final class ContactBusinessService {


    public static void save(Contact contact){
        ContactRepository.save();
    }
}
