package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Contact;

public class ContactDAO {
	private Connection connexion;

    public ContactDAO() throws SQLException {
        connexion = DBConnection.getConnection();
    }

    public void ajouterContact(Contact contact) throws SQLException {
     String sql = "INSERT INTO contacts (id, nom, prenom, telephone) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = connexion.prepareStatement(sql);
        ps.setLong(1, contact.getId());
        ps.setString(2, contact.getNom());
        ps.setString(3, contact.getPrenom());
        ps.setString(4, contact.getTelephone());
        ps.executeUpdate();
    }
    public void modifierContact(Contact contact) throws SQLException {
      String sql = "UPDATE contacts SET nom = ?, prenom = ?, telephone = ? WHERE id = ?";
        PreparedStatement ps = connexion.prepareStatement(sql);
        ps.setString(1, contact.getNom());
        ps.setString(2, contact.getPrenom());
        ps.setString(3, contact.getTelephone());
        ps.setInt(4, contact.getId());
        ps.executeUpdate();
    }

    public void supprimerContact(int id) throws SQLException {
        String sql = "DELETE FROM contacts WHERE id = ?";
        PreparedStatement ps = connexion.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();
    }

    public List<Contact> listerContacts() throws SQLException {
        List<Contact> contacts = new ArrayList<>();
        String sql = "SELECT * FROM contacts";
        Statement statement = connexion.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            contacts.add(new Contact(rs.getInt("id"), rs.getString("nom"), 
                          rs.getString("prenom"), rs.getString("telephone")));
        }
        return contacts;
    }

    public List<Contact> rechercherContactParNom(String nom) throws SQLException {
    	List<Contact> contacts = new ArrayList<>();
        String sql = "SELECT * FROM contacts WHERE nom = ?";
        PreparedStatement ps = connexion.prepareStatement(sql);
        ps.setString(1, nom);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            contacts.add(new Contact(rs.getInt("id"), rs.getString("nom"), 
                          rs.getString("prenom"), rs.getString("telephone")));
        }
        return contacts;
    }

}
