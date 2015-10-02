package turmaformacao.br.agenda.controller.asynctasks;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;

import java.util.List;

import turmaformacao.br.agenda.R;
import turmaformacao.br.agenda.model.entities.Contact;
import turmaformacao.br.agenda.model.entities.Email;
import turmaformacao.br.agenda.model.entities.SocialNetwork;
import turmaformacao.br.agenda.model.entities.Telephone;
import turmaformacao.br.agenda.model.persistence.ContactRepository;
import turmaformacao.br.agenda.model.persistence.EmailRepository;
import turmaformacao.br.agenda.model.persistence.SocialNetworksRepository;
import turmaformacao.br.agenda.model.persistence.TelephoneRepository;
import turmaformacao.br.agenda.model.services.EmailBusinessService;
import turmaformacao.br.agenda.model.services.SocialNetworkBusinessService;
import turmaformacao.br.agenda.model.services.TelephoneBusinessService;

/**
 * Created by dhb_s on 02/10/2015.
 */
public class AsyncFindAllDB extends AsyncTask<Void, Void, List<Contact>> {

    Activity context;
    ProgressDialog pd;
    AsyncInterfaceFindAll activity;

    public AsyncFindAllDB(Activity context, AsyncInterfaceFindAll activity) {
        this.activity = activity;
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        pd = new ProgressDialog(context);
        pd.setMessage(context.getString(R.string.msg_loading_database));
        pd.show();
        super.onPreExecute();
    }

    @Override
    protected List<Contact> doInBackground(Void... voids) {
        List<Contact> contacts = null;
        contacts = ContactRepository.getAll();

        for (Contact contact : contacts) {
            List<Telephone> telephones = TelephoneBusinessService.findTelephoneByContactId(contact.getId());
            List<SocialNetwork> socialNetworks = SocialNetworkBusinessService.findSocialNetworkByContactId(contact.getId());
            List<Email> emails = EmailBusinessService.findEmailByContactId(contact.getId());

            contact.setEmails(emails);
            contact.setSocialNetworks(socialNetworks);
            contact.setTelephones(telephones);
        }

        return contacts;

    }

    @Override
    protected void onPostExecute(List<Contact> contacts) {
        pd.dismiss();
        activity.listReceivedFromDatabase(contacts);
        super.onPostExecute(contacts);
    }
}
