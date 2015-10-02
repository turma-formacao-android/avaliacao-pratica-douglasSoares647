package turmaformacao.br.agenda.model.services;

import java.util.List;

import turmaformacao.br.agenda.model.entities.Email;
import turmaformacao.br.agenda.model.persistence.EmailRepository;


/**
 * Created by dhb_s on 02/10/2015.
 */
public final class EmailBusinessService {

    public static void save(Email email) {
        EmailRepository.save(email);
    }


    public static List<Email> findAll(){
        return EmailRepository.getAll();
    }

    public static List<Email> findEmailByContactId(Long idContact){
        return EmailRepository.getEmailByContactId(idContact);
    }


    public static void delete(Long id){
        EmailRepository.delete(id);
    }
}
