package gesta.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CotisationDAO {

	 private String jdbcURL = "jdbc:mysql://localhost:3306/gesta?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	    private String jdbcUsername = "root";
	    private String jdbcPassword = "";

	    private static final String INSERT_COTISATION_SQL = "";
	    private static final String SELECT_ALL_COTISATION = "select * from cotisation";

	    public CotisationDAO() {}

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
