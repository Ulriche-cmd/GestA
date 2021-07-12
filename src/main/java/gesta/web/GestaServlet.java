package gesta.web;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gesta.dao.DemandeDAO;
import gesta.models.Demande;

import gesta.dao.MembresDAO;
import gesta.models.Membre;
import gesta.dao.CotisationDAO;
import gesta.models.Cotisation;

import gesta.dao.EvenementDAO;
import gesta.models.Evenement;

/**
 * 
 * @author christian
 *
 */

@WebServlet("/")
public class GestaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DemandeDAO demandeDAO;
    private EvenementDAO evenementDAO;
   
    public void init() {
    	demandeDAO = new DemandeDAO();
    	evenementDAO = new EvenementDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
            	case "/":
	                showConnexion(request, response);
	                break;
            	case "/New_Demand":
            		newDemand(request, response);
            		break;
            	case "/insert_Demand":
            		insertDemand(request, response);
            		break;
                case "/connexion":
                    showConnexion(request, response);
                    break;
                case "/demandes":
                    listDemand(request, response);
                    break;
//                case "/membres":
//                    listMember(request, response);
//                    break;
//                case "/addMembre":
//                    addMember(request, response);
//                    break;
//                case "/cotisations":
//                    listCotisation(request, response);
//                    break;
//                case "/addCotisation":
//                    addCotisation(request, response);
//                    break;
                case "/list_event":
                   listEvent(request, response);
                   break;
                case "/new_event":
                    showNewEventForm(request, response);
                    break;
                case "/insert_event":
                    insertEvent(request, response);
                    break;
                case "/delete_event":
                    deleteEvent(request, response);
                    break;
                case "/edit_event":
                    showEditEventForm(request, response);
                    break;
                case "/update_event":
                    updateEvent(request, response);
                    break;
                    
                    
             /**case "/nom_de_la_route":
                    listCotisation(request, response);
                    break;
             */
                default:
                    error(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
    
    private void newDemand(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    	RequestDispatcher dispatcher = request.getRequestDispatcher("demande-form.jsp");
    	dispatcher.forward(request, response);
    }
    
    private void insertDemand(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        //String name = request.getParameter("nom");
        //Demande demande = new Demande();
        //demandeDAO.insertDemande(newUser);
        //response.sendRedirect("New_Demand");
    }
    
    private void showConnexion(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    	RequestDispatcher dispatcher = request.getRequestDispatcher("authentification.jsp");
    	dispatcher.forward(request, response);
    }


    private void listDemand(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException {
        ///Demande < Demande > listDemand = DemandeDAO.selectAllDemande();
        //request.setAttribute("listDemande", listDemand);
        RequestDispatcher dispatcher = request.getRequestDispatcher("demandes.jsp");
        dispatcher.forward(request, response);
    }
    
    private void error(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    	RequestDispatcher dispatcher = request.getRequestDispatcher("Error.jsp");
    	dispatcher.forward(request, response);
    }
    
    /**
     * la suite des fonctions méthodes controlleur ici
     */
    
    private void listEvent(HttpServletRequest request, HttpServletResponse response)
    	    throws SQLException, IOException, ServletException {
    	        List < Evenement > listEvent = evenementDAO.selectAllEvents();
    	        System.out.println(listEvent);
    	        request.setAttribute("listEvent", listEvent);
    	        RequestDispatcher dispatcher = request.getRequestDispatcher("event-list.jsp");
    	        dispatcher.forward(request, response);
    	    }

    private void showNewEventForm(HttpServletRequest request, HttpServletResponse response)
    	    throws ServletException, IOException {
    	        RequestDispatcher dispatcher = request.getRequestDispatcher("event-form.jsp");
    	        dispatcher.forward(request, response);
    	    }

    private void showEditEventForm(HttpServletRequest request, HttpServletResponse response)
    	    throws SQLException, ServletException, IOException {
    	        int id = Integer.parseInt(request.getParameter("id"));
    	        System.out.println(id);
    	        Evenement existingEvent = evenementDAO.selectEvent(id);
    	        request.setAttribute("event", existingEvent);
    	        RequestDispatcher dispatcher = request.getRequestDispatcher("event-form.jsp");
    	        
    	        dispatcher.forward(request, response);
    	    }

    private void insertEvent(HttpServletRequest request, HttpServletResponse response)
    	    throws SQLException, IOException {
    	        String intitule = request.getParameter("intitule");
    	        String description = request.getParameter("description");
    	        String date_debutStr = request.getParameter("date_debut");
    	        String date_finStr = request.getParameter("date_fin");

    	        //surround below line with try catch block as below code throws checked exception
    	        Date date_debut = Date.valueOf(date_debutStr);
    	        java.sql.Date date_fin = Date.valueOf(date_finStr);
        	    //do further processing with Date object
    	        Evenement newEvent = new Evenement(intitule ,description, date_debut, date_fin);
    	        evenementDAO.insertEvent(newEvent);
    	        response.sendRedirect("list_event");
    	    }

    private void updateEvent(HttpServletRequest request, HttpServletResponse response)
    	    throws SQLException, IOException {
    	        int id = Integer.parseInt(request.getParameter("id"));
    	        String intitule = request.getParameter("intitule");
    	        String description = request.getParameter("description");
    	        String date_debutStr = request.getParameter("date_debut");
    	        String date_finStr = request.getParameter("date_fin");

    	        //surround below line with try catch block as below code throws checked exception
    	        Date date_debut = Date.valueOf(date_debutStr);
    	        java.sql.Date date_fin = Date.valueOf(date_finStr);
        	    //do further processing with Date object
    	        
    	        Evenement book = new Evenement(id, intitule ,description, date_debut, date_fin);
    	        evenementDAO.updateEvent(book);
    	        response.sendRedirect("list_event");
    	    }

    private void deleteEvent(HttpServletRequest request, HttpServletResponse response)
    	    throws SQLException, IOException {
    	        int id = Integer.parseInt(request.getParameter("id"));
    	        evenementDAO.deleteEvent(id);
    	        response.sendRedirect("list_event");

    	    }
    
}
