package turmaformacao.br.agenda.controller.asynctasks;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;

import java.util.List;

import turmaformacao.br.agenda.R;
import turmaformacao.br.agenda.model.entities.Contact;
import turmaformacao.br.agenda.model.persistence.ContactRepository;

/**
 * Created by dhb_s on 02/10/2015.
 */
public class AsyncSaveDB extends AsyncTask<Contact,Void,Void> {

    Activity context;
    ProgressDialog pd;

    public AsyncSaveDB(Activity context){
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
    protected Void doInBackground(Contact... contacts) {
        ContactRepository.save(contacts[0]);
        return null;
    }

    @Override
    protected void onPostExecute(Void voids) {
        pd.dismiss();
        super.onPostExecute(voids);
    }
}
