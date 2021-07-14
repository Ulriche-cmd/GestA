package gesta.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import gesta.models.Demande;
import gesta.models.Evenement;

/**
 * 
 * @author christian
 *
 */
public class DemandeDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/gesta?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private String jdbcUsername = "root";
    private String jdbcPassword = "";
    

	 private static final String INSERT_DEMANDE_SQL = "INSERT INTO demande" + "  (nom, prenom, date_naissance, adresse, telephone, email, cni, description, date_demande) VALUES " +
     " (?, ?, ?, ?, ?, ?, ?, ?, ?);";;
    private static final String UPDATE_REJET_DEMANDE = "update demande set etat = 1 where id_demande = ?;";
    private static final String SELECT_ALL_DEMANDE = "select * from demande";
    private static final String SELECT_ONE_DEMANDE = "select * from demande where id_demande = ?";

    public DemandeDAO() {}

    protected Connection getConnexion() {
        Connection connection = null;
        try {
        	System.out.println( "Chargement du driver..." );
            Class.forName( "com.mysql.jdbc.Driver" );
            System.out.println( "Driver chargé !" );
        } catch (ClassNotFoundException e) {
        	System.out.println( "Erreur lors du chargement : le driver n'a pas été trouvé dans le classpath ! <br/>"+e.getMessage());
        }
        
        try {
        	System.out.println( "Connexion à la base de données..." );
        	connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        	System.out.println( "Connexion réussie !" );
        }catch ( SQLException e ) {
        	System.out.println( "Erreur lors de la connexion : <br/>"+ e.getMessage() );
        }
        return connection;
    }
    
  //insertion d'une demande
    public int insertDemande(Demande demande) throws SQLException {
    	System.out.println(INSERT_DEMANDE_SQL);
        // try-with-resource statement will auto close the connection.
        try (
        		Connection connection = getConnexion(); 
        		PreparedStatement preparedStatement = connection.prepareStatement(INSERT_DEMANDE_SQL)) {
	            preparedStatement.setString(1, demande.getNom());
	            preparedStatement.setString(2, demande.getPrenom());
	            preparedStatement.setDate(3, demande.getDate_naissance());
	            preparedStatement.setString(4, demande.getAdresse());
	            preparedStatement.setLong(5, demande.getTelephone());
	            preparedStatement.setString(6, demande.getEmail());
	            preparedStatement.setString(7, demande.getCni());
	            preparedStatement.setString(8, demande.getDescription());
	            
	            long millis=System.currentTimeMillis();  
	            Date today =new Date(millis); 
	            
	            preparedStatement.setDate(9, today);
	            System.out.println(preparedStatement);
	            preparedStatement.executeUpdate();
	            return 1;
        }catch (SQLException e) {
        	printSQLException(e);
        }
		return 0;
    }
    
    public List < Demande > selectAllDemands() {

        // using try-with-resources to avoid closing resources (boiler plate code)
        List < Demande > demandes = new ArrayList < > ();
        // Step 1: Establishing a Connection
        try (Connection connection = getConnexion();

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_DEMANDE)) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int id = rs.getInt("id_demande");
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                Date date_naissance = rs.getDate("date_naissance");
                String adresse = rs.getString("adresse");
                Long telephone = rs.getLong("telephone");
                String email = rs.getString("email");
                String cni = rs.getString("cni");
                String description = rs.getString("description");
                int etat = rs.getInt("etat");
                String date_demande = rs.getString("date_demande");
                demandes.add(new Demande(id, nom, prenom, date_naissance,adresse,telephone,email,cni,description,etat,date_demande));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return demandes;
    }
    
    public Demande selectOneDemands(int id_demande) {

        // using try-with-resources to avoid closing resources (boiler plate code)
        Demande demandes = null ;
        // Step 1: Establishing a Connection
        try (Connection connection = getConnexion();

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ONE_DEMANDE)) {
        	preparedStatement.setInt(1, id_demande);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int id = rs.getInt("id_demande");
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                Date date_naissance = rs.getDate("date_naissance");
                String adresse = rs.getString("adresse");
                Long telephone = rs.getLong("telephone");
                String email = rs.getString("email");
                String cni = rs.getString("cni");
                String description = rs.getString("description");
                int etat = rs.getInt("etat");
                String date_demande = rs.getString("date_demande");
                demandes = new Demande(id, nom, prenom, date_naissance,adresse,telephone,email,cni,description,etat,date_demande);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return demandes;
    }
    
    public boolean rejetDemand(int id) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnexion(); PreparedStatement statement = connection.prepareStatement(UPDATE_REJET_DEMANDE)) {
            statement.setInt(1, id);
            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }
    
    public boolean acceptDemand(int id) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnexion(); PreparedStatement statement = connection.prepareStatement(UPDATE_REJET_DEMANDE)) {
            statement.setInt(1, id);
            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }
    
    /** la suite des fonctions métier seront écrites ici **/
    
    private void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }

}

