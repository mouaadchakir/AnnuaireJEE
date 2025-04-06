package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import dao.ContactDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Contact;

@WebServlet("/ContactServlet")
public class ContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ContactDAO contactDAO;

    public void init() {
        try {
            contactDAO = new ContactDAO();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
                         throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        
        try {
            switch (action) { 
                case "ajouter":
                    ajouterContact(request, response);
                    break;
                case "modifier":
                    modifierContact(request, response);
                    break;
                case "supprimer":
                    supprimerContact(request, response);
                    break;
                case "lister":
                    List<Contact> contacts = contactDAO.listerContacts();
                    request.setAttribute("contact", contacts);
                    listerContacts(request, response);
                    break;
                case "rechercher":
                	List<Contact> contactsR=contactDAO.rechercherContactParNom(request.getParameter("nom"));
                	request.setAttribute("contact", contactsR);
                	rechercherContact(request, response);
                    break;
                default:
                    response.sendRedirect("index.jsp");
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void ajouterContact(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
    	int id = Integer.parseInt(request.getParameter("id"));
    	String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String telephone = request.getParameter("telephone");
        Contact contact = new Contact(id, nom, prenom, telephone);
        contactDAO.ajouterContact(contact);
        response.sendRedirect("ContactServlet?action=lister");
    }

    private void modifierContact(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String telephone = request.getParameter("telephone");
        Contact contact = new Contact(id, nom, prenom, telephone);
        contactDAO.modifierContact(contact);
        response.sendRedirect("ContactServlet?action=lister");
    }

    private void supprimerContact(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        contactDAO.supprimerContact(id);
        response.sendRedirect("ContactServlet?action=lister");
    }

    private void listerContacts(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        List<Contact> contacts = contactDAO.listerContacts();
        request.setAttribute("contacts", contacts);
        request.getRequestDispatcher("listeContacts.jsp").forward(request, response);
    }

    private void rechercherContact(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        String nom = request.getParameter("nom"); 
        List<Contact> contact = contactDAO.rechercherContactParNom(nom);
        request.setAttribute("contact", contact);
        request.getRequestDispatcher("listeContacts.jsp").forward(request, response);
    }
}
