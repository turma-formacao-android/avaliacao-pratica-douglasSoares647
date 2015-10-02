package turmaformacao.br.agenda.model.services;

import android.app.Activity;

import java.util.List;

import turmaformacao.br.agenda.model.entities.SocialNetwork;
import turmaformacao.br.agenda.model.persistence.SocialNetworksRepository;

/**
 * Created by dhb_s on 02/10/2015.
 */
public final class SocialNetworkBusinessService {

    public static void save(SocialNetwork socialNetwork) {
        SocialNetworksRepository.save(socialNetwork);
    }


    public static List<SocialNetwork> findAll(){
        return SocialNetworksRepository.getAll();
    }

    public static List<SocialNetwork> findSocialNetworkByContactId(Long idContact){
        return SocialNetworksRepository.getSocialNetworkByContactId(idContact);
    }


    public static void delete(Long id){
        SocialNetworksRepository.delete(id);
    }
}
