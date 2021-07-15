package gesta.dao;


import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import gesta.models.Membre;

public class authentificationDAO {
	private String jdbcURL = "jdbc:mysql://localhost:3306/gesta?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private String jdbcUsername = "root";
    private String jdbcPassword = "";
    
    private static final String Authentification = "select * from membre where login = ? and mdp = ? and role = ?;";
    
    public authentificationDAO() {}

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

    public boolean validate(Membre Membre) throws ClassNotFoundException {
        boolean status = false;

        try (Connection connection = getConnexion(); 

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(Authentification)) {
            preparedStatement.setString(1, Membre.getLogin());
            preparedStatement.setString(2, Membre.getMdp());
            preparedStatement.setString(3, "gestionnaire");

            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            status = rs.next();
            System.out.println(status);

        } catch (SQLException e) {
            // process sql exception
            printSQLException(e);
        }
        return status;
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