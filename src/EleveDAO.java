import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EleveDAO {
    private Connection con;


    public List<Eleve> getEleves() {
        List<Eleve> eleves = new ArrayList<>();
        try {
          Class.forName("com.mysql.cj.jdbc.Driver");
          Connection con = DriverManager.getConnection("jdbc:mysql:// devbdd.iutmetz.univ-lorraine.fr:3306/aloui7u_connectJava",
            "aloui7u_appli", "31825951");
          Statement st = con.createStatement();
          ResultSet rs = st.executeQuery("select * from televe");
          while(rs.next()){
              eleves.add(new Eleve(rs.getString(1), rs.getString(2)));
            }
          con.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        return eleves;
    }

    public void ajouterEleve(Eleve eleve) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql:// devbdd.iutmetz.univ-lorraine.fr:3306/aloui7u_connectJava",
                    "aloui7u_appli", "31825951");
            PreparedStatement statement = con.prepareStatement("INSERT INTO televe(nom,prenom) VALUES(?,?);");
            statement.setString(1, eleve.getNom());
            statement.setString(2, eleve.getPrenom());

            statement.executeUpdate();

            con.close();
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

    public void modifierEleve(Eleve eleve) {
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
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    }
