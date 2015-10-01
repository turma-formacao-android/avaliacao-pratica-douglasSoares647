package turmaformacao.br.agenda.model.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import turmaformacao.br.agenda.util.ApplicationUtil;

/**
 * Created by Administrador on 01/10/2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "contactManagerdb";
    private static final int DATABASE_VERSION = 1;

    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null,DATABASE_VERSION);
    }

    public static DatabaseHelper getInstance(){
        return new DatabaseHelper(ApplicationUtil.getContext());
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(ContactContract.createTableContact());
        db.execSQL(ContactContract.createTableEmail());
        db.execSQL(ContactContract.createTableSocialNetworks());
        db.execSQL(ContactContract.createTableTelephones());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
