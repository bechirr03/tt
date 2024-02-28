package services;



import models.Categorie;

        import utils.MyDatabase;
        import java.sql.Connection;
        import java.sql.PreparedStatement;
        import java.sql.ResultSet;
        import java.sql.SQLException;
        import java.sql.Statement;
        import java.util.ArrayList;
        import java.util.List;


public class ServiceCategorie implements ICategorie<Categorie> {

    Connection con=MyDatabase.getInstance().getConnection();
    public  ServiceCategorie(){

    }
    @Override
    public void ajouterCategorie(Categorie t) {
        try {
            String qry = "INSERT INTO `categorie`(`nom`) VALUES (?);";

            PreparedStatement ps = con.prepareStatement(qry);
            ps.setString(1, t.getNom());
            ps.executeUpdate();
            System.out.println(" jawek behi fi zyeda ");
        } catch (SQLException ex) {
            System.out.println(" sehel howa ");

            System.out.println(ex.getMessage());
        }

    }

    @Override
    public List<Categorie> afficherCategorie() {
        List<Categorie> categories = new ArrayList<>();

        try {
            String req ="SELECT * FROM categorie ";

            Statement st = con.createStatement();
            ResultSet rs= st.executeQuery(req);
            while (rs.next()){
                Categorie c = new Categorie();
                c.setId(rs.getInt(1));
                c.setNom(rs.getString("nom"));
                categories.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return categories;
    }

    @Override
    public void modifierCategorie(Categorie t) {
        try {
            String req = " UPDATE categorie SET id = '" +t.getId()+ "', Nom = '" +t.getNom() + "' WHERE categorie.`id` = " + t.getId();
            Statement st = con.createStatement();
            st.executeUpdate(req);
            System.out.println("Categorie updated!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }




    @Override
    public void supprimerCategorie(int id) {
        try {
            String req = "DELETE FROM categorie WHERE id = " + id;
            Statement st = con.createStatement();
            st.executeUpdate(req);
            System.out.println("Categorie deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public List<String> getallnamesCategorie() {
        List<String> categories = new ArrayList<>();

        try {
            String req ="SELECT nom FROM categorie ";

            Statement st = con.createStatement();
            ResultSet rs= st.executeQuery(req);
            while (rs.next()){
                String n=(rs.getString("nom"));
                categories.add(n);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return categories;
    }
    public Categorie getbyidCategorie(Categorie t){
        Categorie c = new Categorie();
        try {
            String id = null;
            String req ="SELECT * FROM categorie where id = '"+id+"%' ";

            Statement st = con.createStatement();
            ResultSet rs= st.executeQuery(req);
            while (rs.next()){
                c.setId(rs.getInt(1));
                c.setNom(rs.getString(2));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return c;
    }

    @Override
    public Categorie getbyid(int id){
        Categorie c = new Categorie();
        try {
            String req ="SELECT * FROM categorie where id = '"+id+"%' ";

            Statement st = con.createStatement();
            ResultSet rs= st.executeQuery(req);
            while (rs.next()){
                c.setId(rs.getInt(1));
                c.setNom(rs.getString(2));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return c;
    }
}