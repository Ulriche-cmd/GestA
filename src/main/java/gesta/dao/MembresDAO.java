<<<<<<< HEAD
package gesta.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import gesta.models.Demande;
import gesta.models.Membre;

/**
 * 
 * @author christian
 *
 */
public class MembresDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/gesta?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private String jdbcUsername = "root";
    private String jdbcPassword = "";

    private static final String INSERT_MEMBRE_SQL = "insert into membre"+"(nom,prenom,date_naissance,adresse,telephone,email,cni) VALUES"+"(?,?,?,?,?,?,?);";
    private static final String SELECT_ALL_MEMBRE = "select * from membre;";

    public MembresDAO() {}

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
    
    public int insertMembre(Membre membre) throws SQLException {
    	System.out.println(INSERT_MEMBRE_SQL);
        // try-with-resource statement will auto close the connection.
        try (
        		Connection connection = getConnexion(); 
        		PreparedStatement preparedStatement = connection.prepareStatement(INSERT_MEMBRE_SQL)) {
	            preparedStatement.setString(1, membre.getNom());
	            preparedStatement.setString(2, membre.getPrenom());
	            preparedStatement.setDate(3, membre.getDate_naissance());
	            preparedStatement.setString(4, membre.getAdresse());
	            preparedStatement.setLong(5, membre.getTelephone());
	            preparedStatement.setString(6, membre.getEmail());
	            preparedStatement.setString(7, membre.getCni());
	            System.out.println(preparedStatement);
	            preparedStatement.executeUpdate();
	            return 1;
        }catch (SQLException e) {
        	printSQLException(e);
        }
		return 0;
    }
    
    public List < Membre > selectAllMembres() {

        // using try-with-resources to avoid closing resources (boiler plate code)
        List < Membre > membres = new ArrayList < > ();
        // Step 1: Establishing a Connection
        try (Connection connection = getConnexion();

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_MEMBRE)) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int id = rs.getInt("id_membre");
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                Date date_naissance = rs.getDate("date_naissance");
                String adresse = rs.getString("adresse");
                Long telephone = rs.getLong("telephone");
                String email = rs.getString("email");
                String cni = rs.getString("cni");
                String role = rs.getString("role");
                membres.add(new Membre(id, nom, prenom, date_naissance,adresse,telephone,email,cni,role));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return membres;
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
=======
package gesta.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import gesta.models.Demande;
import gesta.models.Membre;

/**
 * 
 * @author christian
 *
 */
public class MembresDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/gesta?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private String jdbcUsername = "root";
    private String jdbcPassword = "";

    private static final String INSERT_MEMBRE_SQL = "";
    private static final String SELECT_ALL_MEMBRE = "select * from membre";

    public MembresDAO() {}

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
>>>>>>> 2d630e63dfd91910c06392feb26940714a6ae86b
