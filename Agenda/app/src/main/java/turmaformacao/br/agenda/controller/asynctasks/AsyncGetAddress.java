package turmaformacao.br.agenda.controller.asynctasks;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.Toast;

import turmaformacao.br.agenda.R;
import turmaformacao.br.agenda.model.entities.Address;
import turmaformacao.br.agenda.model.http.AddressService;

/**
 * Created by dhb_s on 01/10/2015.
 */
public class AsyncGetAddress extends AsyncTask<String,Void,Address>{


    Activity context;
    ProgressDialog pd;
    AsyncInterfaceAddress activity;

    public AsyncGetAddress(Activity context,AsyncInterfaceAddress asyncInterfaceAddress){
        this.context = context;
        this.activity = asyncInterfaceAddress;
    }


    @Override
    protected void onPreExecute() {
        pd = new ProgressDialog(context);
        pd.setMessage("Getting address by zipcode");
        pd.show();
        super.onPreExecute();
    }

    @Override
    protected Address doInBackground(String... zipCode) {
        return AddressService.getAddressByZipCode(zipCode[0]);

    }

    @Override
    protected void onPostExecute(Address address) {
        pd.dismiss();
        if(address==null)
            Toast.makeText(context, context.getString(R.string.msg_zipcode_not_found),Toast.LENGTH_SHORT).show();
        else
            activity.fillAddress(address);
        super.onPostExecute(address);
    }
}
