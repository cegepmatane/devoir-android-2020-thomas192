package cgmatane.mobile.anniversaire.donnee;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cgmatane.mobile.anniversaire.modele.Anniversaire;

public class AnniversaireDAO {
    private static AnniversaireDAO instance = null;
    private List<Anniversaire> listeAnniversaire;
    private BaseDeDonnees baseDeDonnees;

    public AnniversaireDAO() {
        this.baseDeDonnees =BaseDeDonnees.getInstance();
        listeAnniversaire = new ArrayList<Anniversaire>();
        //preparerListeAnniversaire();
    }

    private void preparerListeAnniversaire() {
        /*
        listeAnniversaire.add(new Anniversaire("Elon Musk", "1971-06-28", 0));
        listeAnniversaire.add(new Anniversaire("Leonardo DiCaprio", "1974-11-11", 1));
        listeAnniversaire.add(new Anniversaire("Anthony Hopkins", "1937-12-31", 2));
         */
    }

    public static AnniversaireDAO getInstance() {
        if(null == instance) {
            instance = new AnniversaireDAO();
        }
        return instance;
    }

    /*
    public List<Anniversaire> listerAnniversaire() {
        return listeAnniversaire;
    }
     */

    public List<Anniversaire> listerAnniversaire() {
        String LISTER_ANNIVERSAIRE = "SELECT * FROM anniversaire";
        Cursor curseur = baseDeDonnees.getReadableDatabase().rawQuery(LISTER_ANNIVERSAIRE, null);
        this.listeAnniversaire.clear();
        Anniversaire a;

        int indexId = curseur.getColumnIndex("id");
        int indexPrenomEtNom = curseur.getColumnIndex("prenomEtNom");
        int indexDateDeNaissance = curseur.getColumnIndex("dateDeNaissance");

        for (curseur.moveToFirst();!curseur.isAfterLast();curseur.moveToNext()) {
            int id = curseur.getInt((indexId));
            String prenomEtNom = curseur.getString(indexPrenomEtNom);
            String dateDeNaissance = curseur.getString(indexDateDeNaissance);
            a = new Anniversaire(prenomEtNom, dateDeNaissance, id);
            this.listeAnniversaire.add(a);
        }

        return listeAnniversaire;
    }

    /*
    public void ajouterAnniversaire(HashMap<String, String> a) {
        //listeAnniversaire.add(a);
    }
     */

    public void ajouterAnniversaire(Anniversaire anniversaire) {
        SQLiteDatabase baseDeDonneesEcriture = baseDeDonnees.getWritableDatabase();
        baseDeDonneesEcriture.beginTransaction();
        try {
            ContentValues anniversaireEnCleValeur = new ContentValues();
            anniversaireEnCleValeur.put("prenomEtNom", anniversaire.getPrenomEtNom());
            anniversaireEnCleValeur.put("dateDeNaissance", anniversaire.getDateDeNaissance());

            baseDeDonneesEcriture.insertOrThrow("anniversaire", null, anniversaireEnCleValeur);
            baseDeDonneesEcriture.setTransactionSuccessful();
        } catch (Exception e) {
            Log.d("AnniversaireDAO", "Erreur lors de l'ajout d'un anniversaire dans la base de données");
        } finally {
            baseDeDonneesEcriture.endTransaction();
        }
    }

    public void modifierAnniversaire(Anniversaire anniversaire) {
        SQLiteDatabase baseDeDonneesEcriture = baseDeDonnees.getWritableDatabase();
        baseDeDonneesEcriture.beginTransaction();
        try {
            ContentValues livreEnCleValeur = new ContentValues();
            livreEnCleValeur.put("prenomEtNom", anniversaire.getPrenomEtNom());
            livreEnCleValeur.put("dateDeNaissance", anniversaire.getDateDeNaissance());

            baseDeDonneesEcriture.update("anniversaire", livreEnCleValeur,
                    "id = ?", new String[]{String.valueOf(anniversaire.getId())});
            baseDeDonneesEcriture.setTransactionSuccessful();
        } catch (Exception e) {
            Log.d("AnniversaireDAO", "Erreur lors de la modification d'un anniversaire dans la base de données");
        } finally {
            baseDeDonneesEcriture.endTransaction();
        }
    }

        public Anniversaire chercherAnniversaireParId(int id) {
        listerAnniversaire();
        for (Anniversaire a : this.listeAnniversaire) {
            if (a.getId() == id)
                return a;
        }
        return null;
    }
}
