package gesta.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import gesta.models.Evenement;

public class EvenementDAO {

	private String jdbcURL = "jdbc:mysql://localhost:3306/gesta?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private String jdbcUsername = "root";
    private String jdbcPassword = "";

    private static final String INSERT_EVENT_SQL = "INSERT INTO evenement" + "  (intitule, description, date_debut, date_fin) VALUES " +
            " (?, ?, ?, ?);";
    private static final String SELECT_EVENT_BY_ID = "select * from evenement where id_evenement =?";
    private static final String SELECT_ALL_EVENTS= "select * from evenement";
    private static final String DELETE_EVENT_SQL = "delete from evenement where id_evenement = ?";
    private static final String UPDATE_EVENT_SQL = "update evenement set intitule = ?,description= ?, date_debut =?, date_fin =? where id_evenement = ?";

    public EvenementDAO() {}

    protected Connection getConnexion() {
        Connection connection = null;
        try {
        	System.out.println( "Chargement du driver..." );
            Class.forName( "com.mysql.jdbc.Driver" );
            System.out.println( "Driver charge !" );
        } catch (ClassNotFoundException e) {
        	System.out.println( "Erreur lors du chargement : le driver n'a pas ete trouve dans le classpath ! <br/>"+e.getMessage());
        }
        
        try {
        	System.out.println( "Connexion a� la base de donnees..." );
        	connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        	System.out.println( "Connexion reussie !" );
        }catch ( SQLException e ) {
        	System.out.println( "Erreur lors de la connexion : <br/>"+ e.getMessage() );
        }
        return connection;
    }
    
    
    /** la suite des fonctions metier seront ecrites ici **/
    
    public void insertEvent(Evenement evenement) throws SQLException {
        System.out.println(INSERT_EVENT_SQL);
        // try-with-resource statement will auto close the connection.
        try (Connection connection = getConnexion(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_EVENT_SQL)) {
            preparedStatement.setString(1, evenement.getIntitule());
            preparedStatement.setString(2, evenement.getDescription());
            preparedStatement.setDate(3, evenement.getDate_debut());
            preparedStatement.setDate(4, evenement.getDate_fin());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public Evenement selectEvent(int id) {
        Evenement evenement = null;
        // Step 1: Establishing a Connection
        try (Connection connection = getConnexion();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_EVENT_BY_ID)) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String intitule = rs.getString("intitule");
                String description = rs.getString("description");
                Date date_debut = rs.getDate("date_debut");
                Date date_fin = rs.getDate("date_fin");
                evenement = new Evenement(id, intitule, description, date_debut, date_fin);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return evenement;
    }

    public List < Evenement > selectAllEvents() {

        // using try-with-resources to avoid closing resources (boiler plate code)
        List < Evenement > evenements = new ArrayList < > ();
        // Step 1: Establishing a Connection
        try (Connection connection = getConnexion();

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_EVENTS)) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int id = rs.getInt("id_evenement");
                String intitule = rs.getString("intitule");
                String description = rs.getString("description");
                Date date_debut = rs.getDate("date_debut");
                Date date_fin = rs.getDate("date_fin");
                evenements.add(new Evenement(id, intitule, description, date_debut, date_fin));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return evenements;
    }

    public boolean deleteEvent(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnexion(); PreparedStatement statement = connection.prepareStatement(DELETE_EVENT_SQL)) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updateEvent(Evenement evenement) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnexion(); PreparedStatement statement = connection.prepareStatement(UPDATE_EVENT_SQL)) {
            statement.setString(1, evenement.getIntitule());
            statement.setString(2, evenement.getDescription());
            statement.setDate(3, evenement.getDate_debut());
            statement.setDate(4, evenement.getDate_fin());
            statement.setInt(5, evenement.getId());

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
