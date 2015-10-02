package turmaformacao.br.agenda.controller.asynctasks;

import java.util.List;

import turmaformacao.br.agenda.model.entities.Contact;

/**
 * Created by dhb_s on 02/10/2015.
 */
public interface AsyncInterfaceFindAll {
    public void listReceivedFromDatabase(List<Contact> contacts);
}
