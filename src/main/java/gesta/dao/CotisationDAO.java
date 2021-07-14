package gesta.dao;

import java.rmi.AccessException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import gesta.models.*;

public class CotisationDAO {

	 private String jdbcURL = "jdbc:mysql://localhost:3306/gesta?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	    private String jdbcUsername = "root";
	    private String jdbcPassword = "";
	    
	    private static final String INSERT_COTISATION_SQL = "INSERT INTO cotisation" + "  (montant, date_cotisation, id_evenement, id_membre) VALUES " +
	            " (?, ?, ?, ?);";
	    private static final String COTISATION_BY_ID = "select id,montant,date_cotisation,id_evenement,id_membre from cotisation where id =?";

	    private static final String SELECT_ALL_COTISATIONS = "select * from cotisation";
	    
	    private static final String DELETE_COTISATIONS_SQL = "delete from cotisation where id = ?;";
	    private static final String UPDATE_COTISATIONS_SQL = "update cotisations set montant = ?,date_cotisation= ?, id_evenement =?, id_membre =? where id = ?;";

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
	    
	    /** FONCTIONS METIERS**/
	    
	    public void insertCotisation (Cotisation cotisation) throws AccessException {
	    	 System.out.println(INSERT_COTISATION_SQL);
	         // try-with-resource statement will auto close the connection.
	         try (Connection connection = getConnexion(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_COTISATION_SQL)) {
	             preparedStatement.setFloat(1,cotisation.getMontant());
	             preparedStatement.setDate(2, cotisation.getDate_cotisation());
	             preparedStatement.setInt(3, cotisation.getId_evenement());
	             preparedStatement.setInt(4, cotisation.getId_membre());
	             System.out.println(preparedStatement);
	             preparedStatement.executeUpdate();
	         } catch (SQLException e) {
	             printSQLException(e);
	         }
	     }
	    
	    public Cotisation selectCotisation(int id) {
	        Cotisation cotisation = null;
	        // Step 1: Establishing a Connection
	        try (Connection connection = getConnexion();
	            // Step 2:Create a statement using connection object
	            PreparedStatement preparedStatement = connection.prepareStatement(COTISATION_BY_ID);) {
	            preparedStatement.setInt(1, id);
	            System.out.println(preparedStatement);
	            // Step 3: Execute the query or update query
	            ResultSet rs = preparedStatement.executeQuery();

	            // Step 4: Process the ResultSet object.
	            while (rs.next()) {
	                Float montant = rs.getFloat("montant");
	                Date date_cotisation = rs.getDate("date_cotisation");
	                int id_evenement= rs.getInt("id_evenement");
	                int id_membre =rs.getInt("id_membre");
	                
	                
	                cotisation = new Cotisation(id, montant, date_cotisation, id_evenement,id_membre);
	            }
	        } catch (SQLException e) {
	            printSQLException(e);
	        }
	        return cotisation;
	    }
	    
	    public List < Cotisation > selectAllCotisations() {

	        // using try-with-resources to avoid closing resources (boiler plate code)
	        List < Cotisation > cotisation = new ArrayList < > ();
	        // Step 1: Establishing a Connection
	        try (Connection connection = getConnexion();

	            // Step 2:Create a statement using connection object
	            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_COTISATIONS);) {
	            System.out.println(preparedStatement);
	            // Step 3: Execute the query or update query
	            ResultSet rs = preparedStatement.executeQuery();

	            // Step 4: Process the ResultSet object.
	            while (rs.next()) {
	                int id = rs.getInt("id_cotisation");
	                Float montant = rs.getFloat("montant");
	                Date date_cotisation = rs.getDate("date_cotisation");
	                int id_evenement= rs.getInt("id_evenement");
	                int id_membre =rs.getInt("id_membre");
	                
	         
	                cotisation.add( new Cotisation(id, montant, date_cotisation, id_evenement,id_membre));
	            }
	        } catch (SQLException e) {
	            printSQLException(e);
	        }
	        return cotisation;
	    }

	    public boolean deleteCotisation(int id) throws SQLException {
	        boolean rowDeleted;
	        try (Connection connection = getConnexion(); PreparedStatement statement = connection.prepareStatement(DELETE_COTISATIONS_SQL);) {
	            statement.setInt(1, id);
	            rowDeleted = statement.executeUpdate() > 0;
	        }
	        return rowDeleted;
	    }

	    public boolean updateCotisation(Cotisation cotisation) throws SQLException {
	        boolean rowUpdated;
	        try (Connection connection = getConnexion(); PreparedStatement statement = connection.prepareStatement(UPDATE_COTISATIONS_SQL);) {
	            statement.setFloat(1, cotisation.getMontant());
	            statement.setDate(2, cotisation.getDate_cotisation());
	            statement.setInt(2, cotisation.getId_evenement());
	            statement.setInt(3, cotisation.getId_membre());
	            statement.setInt(4, cotisation.getId());

	            rowUpdated = statement.executeUpdate() > 0;
	        }
	        return rowUpdated;
	    }
	    	
	    
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
