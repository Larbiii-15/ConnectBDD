
public class Note {
    private int id;
    private int eleveId ;
    private Matiere matiere;
    private double valeur;

    public Note(int eleveId, Matiere matiere, double valeur) {
        this.eleveId = eleveId;
        this.matiere = matiere;
        this.valeur = valeur;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEleve() {
        return eleveId;
    }

    public void setEleve(int eleveId) {
        this.eleveId = eleveId;
    }

    public Matiere getMatiere() {
        return matiere;
    }

    public void setMatiere(Matiere matiere) {
        this.matiere = matiere;
    }

    public double getValeur() {
        return valeur;
    }

    public void setValeur(double valeur) {
        this.valeur = valeur;
    }

    public enum Matiere {
        MATH,
        ANGLAIS,
        PHYSIQUE,
        INFORMATIQUE;
        public static Matiere Math;
    }
}