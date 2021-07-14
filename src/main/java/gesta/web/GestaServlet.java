package gesta.web;

import java.io.IOException;

import java.sql.SQLException;
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

import gesta.models.authentification;
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
    
    public void init() {
    	demandeDAO = new DemandeDAO();
    	
    	authentificationDAO = new authentificationDAO();
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
//                case "/evenements":
//                    listEvenement(request, response);
//                    break;
                    
                    
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
    	
    	
    	String login = request.getParameter("login");
        String mdp = request.getParameter("mdp");
        authentification authentification = new authentification();
        authentification.setlogin(login);
        authentification.setmdp(mdp);

        try {
            if (authentificationDAO.validate(authentification)) {
                //HttpSession session = request.getSession();
                // session.setAttribute("login",login);
                response.sendRedirect("demandes.jsp");
            } else {
                HttpSession session = request.getSession();
                //session.setAttribute("login", login);
                request.setAttribute("error", "echec de connexion");
                RequestDispatcher dispatcher = request.getRequestDispatcher("authentification.jsp");
            	dispatcher.forward(request, response);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    private void listDemand(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException {
        ///Demande < Demande > listDemand = DemandeDAO.selectAllDemande();
        //request.setAttribute("listDemande", listDemand);
        //RequestDispatcher dispatcher = request.getRequestDispatcher("demandes.jsp");
        //dispatcher.forward(request, response);
    }
    
    private void error(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    	RequestDispatcher dispatcher = request.getRequestDispatcher("Error.jsp");
    	dispatcher.forward(request, response);
    }
    
    /**
     * la suite des fonctions méthodes controlleur ici
     */
}
