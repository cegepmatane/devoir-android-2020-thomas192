package cgmatane.mobile.anniversaire.modele;

import java.util.HashMap;

public class Anniversaire {
    protected String prenomEtNom;
    protected String dateDeNaissance;
    protected int id;

    public Anniversaire(String prenomEtNom, String dateDeNaissance, int id) {
        this.prenomEtNom = prenomEtNom;
        this.dateDeNaissance = dateDeNaissance;
        this.id = id;
    }

    public HashMap<String, String> obtenirAnniversairePourAfficher() {
        HashMap<String, String> anniversairePourAfficher = new HashMap<String, String>();
        anniversairePourAfficher.put("prenomEtNom", this.prenomEtNom);
        anniversairePourAfficher.put("dateDeNaissance", this.dateDeNaissance);
        anniversairePourAfficher.put("id", "" + this.id);
        return anniversairePourAfficher;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrenomEtNom() {
        return prenomEtNom;
    }

    public void setPrenomEtNom(String prenomEtNom) {
        this.prenomEtNom = prenomEtNom;
    }

    public String getDateDeNaissance() {
        return dateDeNaissance;
    }

    public void setDateDeNaissance(String dateDeNaissance) {
        this.dateDeNaissance = dateDeNaissance;
    }
}
