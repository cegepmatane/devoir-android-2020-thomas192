package cgmatane.mobile.anniversaire.donnee;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BaseDeDonnees extends SQLiteOpenHelper {

    private static BaseDeDonnees instance = null;

    private static synchronized BaseDeDonnees getInstance(Context context) {
        instance = new BaseDeDonnees(context);
        return instance;
    }

    public static BaseDeDonnees getInstance() {
        return instance;
    }

    public BaseDeDonnees(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public BaseDeDonnees(Context contexte) {
        super(contexte, "anniversaire", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "create table anniversaire(id INTEGER PRIMARY KEY, prenomEtNom TEXT, dateDeNaissance TEXT)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        // Créer l'échafaud lors de la première exécution, commenter cette méthode pour les prochaines exécutions

        String DELETE = "delete from anniversaire where 1 = 1";
        db.execSQL(DELETE);

        String INSERT_1 = "insert into anniversaire(prenomEtNom, dateDeNaissance) VALUES('Elon Musk', '1971-06-28')";
        String INSERT_2 = "insert into anniversaire(prenomEtNom, dateDeNaissance) VALUES('Leonardo DiCaprio', '1974-11-11')";
        String INSERT_3 = "insert into anniversaire(prenomEtNom, dateDeNaissance) VALUES('Anthony Hopkins', '1937-12-31')";

        db.execSQL(INSERT_1);
        db.execSQL(INSERT_2);
        db.execSQL(INSERT_3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
        //String DETRUIRE_TABLE = "drop table anniversaire";
        //db.execSQL(DETRUIRE_TABLE);
        String CREER_TABLE = "create table anniversaire(id INTEGER PRIMARY KEY, prenomEtNom TEXT, dateDeNaissance TEXT)";
        db.execSQL(CREER_TABLE);
    }
}
