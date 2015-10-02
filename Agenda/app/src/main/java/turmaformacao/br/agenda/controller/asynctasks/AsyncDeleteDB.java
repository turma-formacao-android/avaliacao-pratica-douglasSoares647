package turmaformacao.br.agenda.controller.asynctasks;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;

import turmaformacao.br.agenda.R;
import turmaformacao.br.agenda.model.entities.Telephone;
import turmaformacao.br.agenda.model.persistence.ContactRepository;
import turmaformacao.br.agenda.model.persistence.EmailRepository;
import turmaformacao.br.agenda.model.persistence.TelephoneRepository;
import turmaformacao.br.agenda.model.services.SocialNetworkBusinessService;

/**
 * Created by dhb_s on 02/10/2015.
 */
public class AsyncDeleteDB extends AsyncTask<Long,Void,Void> {

    Activity context;
    ProgressDialog pd;

    public AsyncDeleteDB(Activity context){
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        pd = new ProgressDialog(context);
        pd.setMessage(context.getString(R.string.msg_delete_database));
        pd.show();
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Long... longs) {
        ContactRepository.delete(longs[0]);
        EmailRepository.delete(longs[0]);
        TelephoneRepository.delete(longs[0]);
        SocialNetworkBusinessService.delete(longs[0]);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        pd.dismiss();
        super.onPostExecute(aVoid);
    }
}
