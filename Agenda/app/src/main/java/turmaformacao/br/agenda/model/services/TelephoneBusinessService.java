package turmaformacao.br.agenda.model.services;

import java.util.List;

import turmaformacao.br.agenda.model.entities.Telephone;
import turmaformacao.br.agenda.model.persistence.TelephoneRepository;

/**
 * Created by dhb_s on 02/10/2015.
 */
public final class TelephoneBusinessService {

    public static void save(Telephone telephone) {
        TelephoneRepository.save(telephone);
    }


    public static List<Telephone> findAll(){
        return TelephoneRepository.getAll();
    }

    public static List<Telephone> findTelephoneByContactId(Long idContact){
        return TelephoneRepository.getTelephoneByContactId(idContact);
    }


    public static void delete(Long id){
        TelephoneRepository.delete(id);
    }
    
}
