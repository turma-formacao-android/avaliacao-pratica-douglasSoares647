package turmaformacao.br.agenda;

import android.app.Application;

import turmaformacao.br.agenda.util.ApplicationUtil;

/**
 * Created by Administrador on 01/10/2015.
 */
public class ContactManagerApplication extends Application {

    public void onCreate() {
        super.onCreate();
        ApplicationUtil.setContext(getApplicationContext());

    }

}
