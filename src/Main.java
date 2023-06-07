import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://devbdd.iutmetz.univ-lorraine.fr:3306/aloui7u_connectJava",
                    "aloui7u_appli", "31825951");

            ElevesDAO eleveDAO = new ElevesDAO(con);

            // Ajouter un élève
            Eleves eleve = new Eleves("John", "Doe");
            eleveDAO.ajouterEleve(eleve);

            // Lui ajouter une note
            Note note = new Note(eleve.getId(), Note.Matiere.Math, 18.5);
            eleve.ajouterNote(note);
            eleveDAO.modifierEleve(eleve);

            // Afficher la liste des notes d'un élève
            List<Note> notesEleve = eleveDAO.getNotesEleve(eleve.getId());
            System.out.println("Notes de l'élève " + eleve.getNom() + " " + eleve.getPrenom() + ":");
            for (Note n : notesEleve) {
                System.out.println(n.getMatiere() + ": " + n.getValeur());
            }

            // Afficher la liste des élèves ainsi que leurs notes
            List<Eleves> eleves = eleveDAO.getEleves();
            System.out.println("\nListe des élèves et leurs notes :");
            for (Eleves e : eleves) {
                System.out.println("Nom: " + e.getNom() + ", Prénom: " + e.getPrenom());
                List<Note> notes = eleveDAO.getNotesEleve(e.getId());
                for (Note n : notes) {
                    System.out.println("- " + n.getMatiere() + ": " + n.getValeur());
                }
                System.out.println();
            }

            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}