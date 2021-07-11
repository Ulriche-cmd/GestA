package gesta.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import gesta.models.Demande;

/**
 * 
 * @author christian
 *
 */
public class DemandeDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/gesta?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private String jdbcUsername = "root";
    private String jdbcPassword = "";

    private static final String INSERT_DEMANDE_SQL = "";
    private static final String SELECT_DEMANDE_BY_ID = "";
    private static final String SELECT_ALL_DEMANDE = "select * from demande";

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
    
    public void insertDemande(Demande demande) throws SQLException {
    	System.out.println(INSERT_DEMANDE_SQL);
        // try-with-resource statement will auto close the connection.
        try (
        		Connection connection = getConnexion(); 
        		PreparedStatement preparedStatement = connection.prepareStatement(INSERT_DEMANDE_SQL)) {
	            preparedStatement.setString(2, demande.getNom());
	            preparedStatement.setString(3, demande.getPrenom());
	            preparedStatement.setDate(4, demande.getDate_naissance());
	            preparedStatement.setString(5, demande.getAdresse());
	            preparedStatement.setLong(6, demande.getTelephone());
	            preparedStatement.setString(7, demande.getEmail());
	            preparedStatement.setString(8, demande.getCni());
	            preparedStatement.setString(9, demande.getDescription());
	            preparedStatement.setBoolean(10, demande.getEtat());
	            preparedStatement.setDate(11, demande.getDate_demande());
	            System.out.println(preparedStatement);
	            preparedStatement.executeUpdate();
        }catch (SQLException e) {
        	printSQLException(e);
        }
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

