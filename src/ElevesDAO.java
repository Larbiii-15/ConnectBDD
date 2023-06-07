
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ElevesDAO {
    private Connection con;

    public ElevesDAO(Connection con) {
        this.con = con;
    }

    public List<Eleves> listerEleves() {
        List<Eleves> eleve = new ArrayList<>();
        try {
            String query = "SELECT e.id, e.nom, e.prenom, n.id AS note_id, n.matiere, n.valeur " +
                    "FROM eleve e LEFT JOIN note n ON e.id = n.id_eleve";
            try (Statement st = con.createStatement();
                 ResultSet rs = st.executeQuery(query)) {
                while (rs.next()) {
                    int eleveId = rs.getInt("id");
                    String nom = rs.getString("nom");
                    String prenom = rs.getString("prenom");

                    Eleves eleve1 = new Eleves(nom, prenom);
                    eleve1.setId(eleveId);

                    int noteId = rs.getInt("note_id");
                    if (noteId != 0) {
                        Note.Matiere matiere = Note.Matiere.valueOf(rs.getString("matiere"));
                        double valeur = rs.getDouble("valeur");
                        Note note = new Note(eleve1.getId(), matiere, valeur);
                        note.setId(noteId);
                        eleve1.ajouterNote(note);
                    }

                    eleve.add(eleve1);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return eleve;
    }

    public void ajouterEleve(Eleves eleve) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql:// devbdd.iutmetz.univ-lorraine.fr:3306/aloui7u_connectJava",
                    "aloui7u_appli", "31825951");
            PreparedStatement statement = con.prepareStatement("INSERT INTO televe(nom,prenom) VALUES(?,?);");
            statement.setString(1, eleve.getNom());
            statement.setString(2, eleve.getPrenom());

            statement.executeUpdate();

            con.close();

            for (Note note : eleve.getNotes()) {
                ajouterNoteEleve(eleve.getId(), note);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void supprimerEleve(int id) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://devbdd.iutmetz.univ-lorraine.fr:3306/aloui7u_connectJava",
                    "aloui7u_appli", "31825951");
            PreparedStatement statement = con.prepareStatement("DELETE FROM televe WHERE id = ?");
            statement.setInt(1, id);
            statement.executeUpdate();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void modifierEleve(Eleves eleve) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://devbdd.iutmetz.univ-lorraine.fr:3306/aloui7u_connectJava",
                    "aloui7u_appli", "31825951");
            PreparedStatement statement = con.prepareStatement("UPDATE televe SET nom = ?, prenom = ? WHERE id = ?");
            statement.setString(1, eleve.getNom());
            statement.setString(2, eleve.getPrenom());
            statement.setInt(3, eleve.getId());
            statement.executeUpdate();
            con.close();

            for (Note note : eleve.getNotes()) {
                ajouterNoteEleve(eleve.getId(), note);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void ajouterNoteEleve(int eleveId, Note note) {
        try {
            String query = "INSERT INTO tnot(id, matiere, valeur, eleveid) VALUES (?, ?, ?)";
            try (PreparedStatement statement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                statement.setInt(1, eleveId);
                statement.setString(2, note.getMatiere().toString());
                statement.setDouble(3, note.getValeur());
                statement.executeUpdate();

                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    note.setId(generatedKeys.getInt(1));
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public List<Eleves> getEleves() {
        List<Eleves> eleves = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql:// devbdd.iutmetz.univ-lorraine.fr:3306/aloui7u_connectJava",
                    "aloui7u_appli", "31825951");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from televe");
            while (rs.next()) {
                eleves.add(new Eleves(rs.getString(1), rs.getString(2)));
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        return eleves;
    }

    public List<Note> getNotesEleve(int eleveId) {
        List<Note> notes = new ArrayList<>();
        try {
            String query = "SELECT * FROM tnot WHERE eleveid = ?";
            try (PreparedStatement statement = con.prepareStatement(query)) {
                statement.setInt(1, eleveId);
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    int noteId = rs.getInt("id");
                    Note.Matiere matiere = Note.Matiere.valueOf(rs.getString("matiere"));
                    double valeur = rs.getDouble("valeur");
                    notes.add(new Note(eleveId, matiere, valeur));
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return notes;
    }



}

