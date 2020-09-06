package cgmatane.mobile.anniversaire.donnee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AnniversaireDAO {
    private static AnniversaireDAO instance = null;
    private List<HashMap<String, String>> listeAnniversaire;

    private AnniversaireDAO() {
        listeAnniversaire = new ArrayList<HashMap<String, String>>();
        preparerListeAnniversaire();
    }

    private void preparerListeAnniversaire() {

        HashMap<String, String> a;

        a = new HashMap<String, String>();
        a.put("prenomEtNom", "Elon Musk");
        a.put("dateDeNaissance", "1971-06-28");
        listeAnniversaire.add(a);

        a = new HashMap<String, String>();
        a.put("prenomEtNom", "Leonardo DiCaprio");
        a.put("dateDeNaissance", "1974-11-11");
        listeAnniversaire.add(a);

        a = new HashMap<String, String>();
        a.put("prenomEtNom", "Anthony Hopkins");
        a.put("dateDeNaissance", "1937-12-31");
        listeAnniversaire.add(a);
    }

    public static AnniversaireDAO getInstance() {
        if(null == instance) {
            instance = new AnniversaireDAO();
        }
        return instance;
    }

    public List<HashMap<String, String>> listerAnniversaire() {
        return listeAnniversaire;
    }

    public void ajouterAnniversaire(HashMap<String, String> a) {
        listeAnniversaire.add(a);
    }
}
