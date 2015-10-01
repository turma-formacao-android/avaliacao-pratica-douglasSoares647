package turmaformacao.br.agenda.util;

import android.content.Context;

/**
 * Created by Administrador on 16/09/2015.
 */
public final class ApplicationUtil {


    private static Context applicationContext;
    private ApplicationUtil(){
        super();
    }


    public static void setContext(Context context){
        applicationContext = context;
    }
    public static Context getContext(){
        return applicationContext;
    }
}
