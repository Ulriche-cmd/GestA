package gesta.web;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import gesta.dao.DemandeDAO;
import gesta.models.Demande;
import gesta.dao.MembresDAO;
import gesta.models.Membre;
import gesta.dao.CotisationDAO;
import gesta.models.Cotisation;
import gesta.dao.EvenementDAO;
import gesta.models.Evenement;
import gesta.dao.authentificationDAO;


/**
 * 
 * @author christian
 *
 */

@WebServlet("/")
public class GestaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DemandeDAO demandeDAO;
	private authentificationDAO authentificationDAO;
    private EvenementDAO evenementDAO;
    private MembresDAO membreDAO;
    private CotisationDAO cotisationDAO;
   
    public void init() {
    	demandeDAO = new DemandeDAO();
    	evenementDAO = new EvenementDAO();
    	membreDAO = new MembresDAO();
		authentificationDAO = new authentificationDAO();
		cotisationDAO = new CotisationDAO();
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
            	case "/authentification":
	                showConnexion(request, response);
	                break;
            	case "/deconnexion":
	                showDeconnexion(request, response);
	                break;
            	case "/New_Demand":
            		newDemand(request, response);
            		break;
            	case "/insert_Demand":
            		insertDemand(request, response);
            		break;
                case "/connexion":
                    logIn(request, response);
                    break;
                case "/list_demand":
                    listDemand(request, response);
                    break;
                case "/rejet_demand":
                	rejetDemand(request, response);
                    break;
                case "/accept_demand":
                	acceptDemand(request, response);
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
                case "/list-member":
                    listMember(request, response);
                    break;
                case "/add-member":
                    showMemberForm(request, response);
                    break;
                case "/insert_Member":
                    addMember(request, response);
                    break;
                case "/list_cotisations":
                   listCotisation(request, response);
                   break;
                case "/new_cotisation":
                	showNewForm(request, response);
                   break;
                case "/insert_cotisation":
                    insertCotisation(request, response);
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
    
    private void showConnexion(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
	   		 RequestDispatcher dispatcher = request.getRequestDispatcher("authentification.jsp");
	   		 dispatcher.forward(request, response);
    }
    
    private void showDeconnexion(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    		HttpSession session = request.getSession();
    		session.invalidate();
	   		RequestDispatcher dispatcher = request.getRequestDispatcher("authentification.jsp");
	   		dispatcher.forward(request, response);
    }
    
  //insertion d'une demande
    private void insertDemand(HttpServletRequest request, HttpServletResponse response)
	    throws SQLException, IOException, ServletException {
	   		 String nom = request.getParameter("nom");
	        String prenom = request.getParameter("prenom");
	        String date_naiss = request.getParameter("date_naissance");
	        Date date_naissance = Date.valueOf(date_naiss);
	        String adresse = request.getParameter("adresse");
	        Long telephone= Long.parseLong(request.getParameter("telephone"));
	        String email = request.getParameter("email");
	        String cni = request.getParameter("cni");
	        String description = request.getParameter("description");
	        Demande newDemande = new Demande(nom, prenom,date_naissance,adresse,telephone,email,cni,description);
	        int res = demandeDAO.insertDemande(newDemande);
	        if(res==1)
	        {
	        	request.setAttribute("success", "Votre demande a ete bien pris en compte");
	        	RequestDispatcher dispatcher = request.getRequestDispatcher("demande-form.jsp");
	        	dispatcher.forward(request, response);
	        }
	        else {
	        	request.setAttribute("error", "Echec d'envoi");
	        	RequestDispatcher dispatcher = request.getRequestDispatcher("demande-form.jsp");
	        	dispatcher.forward(request, response);
	        }
		        
 }
    
    private void logIn(HttpServletRequest request, HttpServletResponse response)
    	    throws ServletException, IOException {
    	    	String login = request.getParameter("login");
    	        String mdp = request.getParameter("mdp");
    	        Membre authentification = new  Membre(login, mdp);
    	        try {
    	            if (authentificationDAO.validate(authentification)) {
    	            	HttpSession session = request.getSession();
    	            	session.setAttribute( "login", login );
    	                response.sendRedirect("list_demand");
    	            } else {
    	                //HttpSession session = request.getSession();
    	                //session.setAttribute("login", login);
    	                request.setAttribute("erreur", "erreur de connexion");
    		        	RequestDispatcher dispatcher = request.getRequestDispatcher("authentification");
    		        	dispatcher.forward(request, response);
    	            }
    	        } catch (ClassNotFoundException e) {
    	            e.printStackTrace();
    	        }
    	    }


    private void listDemand(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException {
    	HttpSession session = request.getSession();
	   	 if(session.getAttribute("login") == null)
	   	 {
	   		 response.sendRedirect("authentification");
	   	 }
	   	 else {
	   		 List < Demande > listDemand = demandeDAO.selectAllDemands();
	        request.setAttribute("demandes", listDemand);
	        RequestDispatcher dispatcher = request.getRequestDispatcher("demand-list.jsp");
	        dispatcher.forward(request, response);
	   	 }
        
    }
    
    private void rejetDemand(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
    	HttpSession session = request.getSession();
	   	if(session.getAttribute("login") == null)
	   	{
	   		response.sendRedirect("authentification");
	   	}
	   	else {
	   		int id = Integer.parseInt(request.getParameter("id"));
	        boolean res = demandeDAO.rejetDemand(id);
	        response.sendRedirect("list_demand");
	   	}
        
    }
    
    private void acceptDemand(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
    	HttpSession session = request.getSession();
	   	if(session.getAttribute("login") == null)
	   	{
	   		response.sendRedirect("authentification");
	   	}
	   	else {
	   		int id = Integer.parseInt(request.getParameter("id"));
	        Demande demande = null;
	        demande = demandeDAO.selectOneDemands(id);
	        Membre membre = null;
	        membre = new Membre(demande.getNom(),demande.getPrenom(),demande.getDate_naissance(),demande.getAdresse(),demande.getTelephone(),demande.getEmail(),demande.getCni());
	        int res = membreDAO.insertMembre(membre);
	        demandeDAO.rejetDemand(id);
	        response.sendRedirect("list_demand");
	   	}
        
    }
    
    private void error(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    	HttpSession session = request.getSession();
	   	if(session.getAttribute("login") == null)
	   	{
	   		response.sendRedirect("authentification");
	   	}
	   	else {
	   		RequestDispatcher dispatcher = request.getRequestDispatcher("Error.jsp");
	   		dispatcher.forward(request, response);
	   	}
    	
    }
    
    /**
     * la suite des fonctions méthodes controlleur ici
     */
    
    private void listEvent(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException {
    	HttpSession session = request.getSession();
	   	if(session.getAttribute("login") == null)
	   	{
	   		response.sendRedirect("authentification");
	   	}
	   	else {
	   		List < Evenement > listEvent = evenementDAO.selectAllEvents();
	        System.out.println(listEvent);
	        request.setAttribute("listEvent", listEvent);
	        RequestDispatcher dispatcher = request.getRequestDispatcher("event-list.jsp");
	        dispatcher.forward(request, response);
	   	}
        
    }
    
    private void listMember(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException {
    	HttpSession session = request.getSession();
    	if(session.getAttribute("login") == null)
	   	{
	   		response.sendRedirect("authentification");
	   	}
	   	else {
	   		List < Membre > listMember = membreDAO.selectAllMembres();
	        request.setAttribute("listMember", listMember);
	        RequestDispatcher dispatcher = request.getRequestDispatcher("member-list.jsp");
	        dispatcher.forward(request, response);
	   	}
        
    }
    
    private void showMemberForm(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    	HttpSession session = request.getSession();
    	if(session.getAttribute("login") == null)
	   	{
	   		response.sendRedirect("authentification");
	   	}
	   	else {
	   		RequestDispatcher dispatcher = request.getRequestDispatcher("member-form.jsp");
	   		dispatcher.forward(request, response);
	   	}
    	
    }
    
  //insertion d'une demande
    private void addMember(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
    	HttpSession session = request.getSession();
    	if(session.getAttribute("login") == null)
	   	{
	   		response.sendRedirect("authentification");
	   	}
	   	else {
	   		String nom = request.getParameter("nom");
	        String prenom = request.getParameter("prenom");
	        String date_naiss = request.getParameter("date_naissance");
	        Date date_naissance = Date.valueOf(date_naiss);
	        String adresse = request.getParameter("adresse");
	        Long telephone= Long.parseLong(request.getParameter("telephone"));
	        String email = request.getParameter("email");
	        String cni = request.getParameter("cni");
	        Membre newMembre = new Membre(nom,prenom,date_naissance,adresse,telephone,email,cni);
	        membreDAO.insertMembre(newMembre);
	        response.sendRedirect("list-member");
	   	}
        
	}

    private void showNewEventForm(HttpServletRequest request, HttpServletResponse response)
    	    throws ServletException, IOException {
	    	HttpSession session = request.getSession();
	    	if(session.getAttribute("login") == null)
		   	{
		   		response.sendRedirect("authentification");
		   	}
		   	else {
		   		RequestDispatcher dispatcher = request.getRequestDispatcher("event-form.jsp");
    	        dispatcher.forward(request, response);
		   	}
    	        
    }

    private void showEditEventForm(HttpServletRequest request, HttpServletResponse response)
    	    throws SQLException, ServletException, IOException {
	    	HttpSession session = request.getSession();
	    	if(session.getAttribute("login") == null)
		   	{
		   		response.sendRedirect("authentification");
		   	}
		   	else {
		   		int id = Integer.parseInt(request.getParameter("id"));
    	        System.out.println(id);
    	        Evenement existingEvent = evenementDAO.selectEvent(id);
    	        request.setAttribute("event", existingEvent);
    	        RequestDispatcher dispatcher = request.getRequestDispatcher("event-form.jsp");
    	        dispatcher.forward(request, response);
		   	}
    	        
    	        
    	        
    }

    private void insertEvent(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {HttpSession session = request.getSession();
		if(session.getAttribute("login") == null)
	   	{
	   		response.sendRedirect("authentification");
	   	}
	   	else {
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
        
    }

    private void updateEvent(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
    	HttpSession session = request.getSession();
    	if(session.getAttribute("login") == null)
	   	{
	   		response.sendRedirect("authentification");
	   	}
	   	else {
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
        
    }

    private void deleteEvent(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("login") == null)
	   	{
	   		response.sendRedirect("authentification");
	   	}
	   	else {
	   		int id = Integer.parseInt(request.getParameter("id"));
	        evenementDAO.deleteEvent(id);
	        response.sendRedirect("list_event");
	   	}
        

    }

    /*FONCTIONS DE LA COTISATIONS*/
    
    
    private void listCotisation(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException {
    	HttpSession session = request.getSession();
    	if(session.getAttribute("login") == null)
	   	{
	   		response.sendRedirect("authentification");
	   	}
	   	else {
	   		List < Cotisation > listCotisation = cotisationDAO.selectAllCotisations();
	        List < Evenement > listEvent = evenementDAO.selectAllEvents();
	        request.setAttribute("listEvent", listEvent);
	        request.setAttribute("listCotisation", listCotisation);
	        RequestDispatcher dispatcher = request.getRequestDispatcher("cotisation-list.jsp");
	        dispatcher.forward(request, response);
	   	}
        
    }

		    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
		    throws ServletException, IOException {
		    	HttpSession session = request.getSession();
		    	if(session.getAttribute("login") == null)
			   	{
			   		response.sendRedirect("authentification");
			   	}
			   	else {
			   		List < Membre > listMember = membreDAO.selectAllMembres();
			    	List < Evenement > listEvent = evenementDAO.selectAllEvents();
			        request.setAttribute("listEvent", listEvent);
			        request.setAttribute("listMember", listMember);
			        RequestDispatcher dispatcher = request.getRequestDispatcher("cotisation-form.jsp");
			        dispatcher.forward(request, response);
			   	}
		    	
		    }

//    	    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
//    	    throws SQLException, ServletException, IOException {
//    	        int id = Integer.parseInt(request.getParameter("id"));
//    	        Cotisation existingCotisation = cotisationDAO.selectCotisation(id);
//    	        RequestDispatcher dispatcher = request.getRequestDispatcher("cotisation-form.jsp");
//    	        request.setAttribute("cotisation", existingCotisation);
//    	        dispatcher.forward(request, response);
//
//    	    }

    	    private void insertCotisation(HttpServletRequest request, HttpServletResponse response)
    	    throws SQLException, IOException {
    	    	HttpSession session = request.getSession();
    	    	if(session.getAttribute("login") == null)
    		   	{
    		   		response.sendRedirect("authentification");
    		   	}
    		   	else {
    		   		Float montant =Float.parseFloat( request.getParameter("montant"));
	    	        String format = request.getParameter("date");
	    	        Date date_cotisation  = Date.valueOf(format);
	    	        int id_evenement = Integer.parseInt(request.getParameter("evenement"));
	    	        int id_membre = Integer.parseInt( request.getParameter("membre"));
	    	        Cotisation newCotisation = new Cotisation(montant,date_cotisation,id_evenement, id_membre);
	    	        cotisationDAO.insertCotisation(newCotisation);
	    	        response.sendRedirect("list_cotisations");
    		   	}
    	        
    	    }

//    	    private void updateCotisation(HttpServletRequest request, HttpServletResponse response)
//    	    throws SQLException, IOException {
//    	        int id = Integer.parseInt(request.getParameter("id"));
//    	        Float montant =Float.parseFloat( request.getParameter("montant"));
//    	        Date date_cotisation  = Date.valueOf(request.getParameter("date_cotisation"));
//    	        int id_evenement = Integer.parseInt(request.getParameter("id_evenement"));
//    	        int id_membre = Integer.parseInt( request.getParameter("id_membre"));
//
//    	        Cotisation book = new Cotisation(id,montant,date_cotisation,id_evenement, id_membre);
//    	        cotisationDAO.updateCotisation(book);
//    	        response.sendRedirect("list");
//    	    }
//
//    	    private void deleteCotisation(HttpServletRequest request, HttpServletResponse response)
//    	    throws SQLException, IOException {
//    	        int id = Integer.parseInt(request.getParameter("id"));
//    	        cotisationDAO.deleteCotisation(id);
//    	        response.sendRedirect("list");
//
//    	    }
    
}
