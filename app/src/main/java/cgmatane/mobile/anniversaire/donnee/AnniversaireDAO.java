package cgmatane.mobile.anniversaire.donnee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cgmatane.mobile.anniversaire.modele.Anniversaire;

public class AnniversaireDAO {
    private static AnniversaireDAO instance = null;
    private List<Anniversaire> listeAnniversaire;

    private AnniversaireDAO() {
        listeAnniversaire = new ArrayList<Anniversaire>();
        preparerListeAnniversaire();
    }

    private void preparerListeAnniversaire() {
        listeAnniversaire.add(new Anniversaire("Elon Musk", "1971-06-28", 0));
        listeAnniversaire.add(new Anniversaire("Leonardo DiCaprio", "1974-11-11", 1));
        listeAnniversaire.add(new Anniversaire("Anthony Hopkins", "1937-12-31", 2));
    }

    public static AnniversaireDAO getInstance() {
        if(null == instance) {
            instance = new AnniversaireDAO();
        }
        return instance;
    }

    public List<Anniversaire> listerAnniversaire() {
        return listeAnniversaire;
    }

    public void ajouterAnniversaire(HashMap<String, String> a) {
        //listeAnniversaire.add(a);
    }
}
