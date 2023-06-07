
import java.util.ArrayList;
import java.util.List;

public class Eleves {
    private int id;
    private String nom;
    private String prenom;
    private List<Note> notes;

    public Eleves(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
        this.notes = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    public void ajouterNote(Note note) {
        notes.add(note);
    }

    public void supprimerNote(Note note) {
        notes.remove(note);
    }


    public void add(Eleves eleve) {
    }
}