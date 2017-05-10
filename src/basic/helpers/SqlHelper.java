/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basic.helpers;

import basic.classes.Knapsack;
import basic.classes.KnapsackElement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * SQL Helper Class which handels all the Transfer between the SQLite DB and the Code
 * @author adlerlu
 */
public class SqlHelper {

    //Database Connection
    private Connection connection;
    //String to the Database
    private static final String DB_PATH = "testdb.db";
    //Needed for the Singelton Pattern
    private static SqlHelper instance;

    /**
     * Constructor to Create the SQL Helper Class Sets the DB Connection and if
     * the Tables not exist Creates all needed DB Tables
     */
    private SqlHelper() {
        try {
            Class.forName("org.sqlite.JDBC");
            if (connectToDB()) {
                if (createDbTables());
            }
        } catch (ClassNotFoundException ex) {
            System.err.println(ex.toString());
        }
    }

    /**
     * Singelton Pattern for this Class / Only one Object could be created
     *
     * @return the Object of the Class
     */
    public static SqlHelper getInstance() {
        if (SqlHelper.instance == null) {
            SqlHelper.instance = new SqlHelper();
        }
        return SqlHelper.instance;
    }

    /**
     * Sets the Connection to the Database
     *
     * @return true -> Connection successfull
     * @return false -> Connection failed
     */
    private boolean connectToDB() {
        try {
            if (connection == null) {
                connection = DriverManager.getConnection("jdbc:sqlite:" + DB_PATH);
                if (!connection.isClosed()) {
                    return true;
                }
            }
            return false;

        } catch (SQLException ex) {
            System.err.println(ex.toString());
            return false;
        }
    }

    /**
     * Creates all needed Tables if they don't exist
     *
     * @return true -> Tables were created
     * @return false -> Tables weren't created
     */
    private boolean createDbTables() {
        updateDB("CREATE TABLE IF NOT EXISTS knapsackelement (elem_id integer PRIMARY KEY AUTOINCREMENT,weight,worth,name);");
        updateDB("CREATE TABLE IF NOT EXISTS knapsack (knap_id integer PRIMARY KEY AUTOINCREMENT,name,maxMass);");
        return true;
    }

    /**
     * Closes the Connection to the Database
     */
    public void closeConnection() {
        try {
            if (!connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(SqlHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Inserts into the existing table Knapsack a Knapsack E
     *
     * 
     * @param knapsack
     * @return true -> Insert was successfull / false -> Insert wasn't
     * successfull
     */
    public boolean insertKnapsack(Knapsack knapsack) {
        if (connection != null) {
            try {
                PreparedStatement pStmt = connection.prepareStatement("INSERT INTO knapsack (name,maxMass) VALUES(?,?);");
                pStmt.setString(2, Integer.toString(knapsack.getMaximumMass()));
                pStmt.setString(1, knapsack.getName());
                return pStmt.execute();
            } catch (SQLException ex) {
                System.err.println(ex.toString());
                return false;
            }
        }
        return false;
    }
    
    /**
     * Inserts into the existing table Knapsack a Knapsack E
     *
     * 
     * @param element
     * @return true -> Insert was successfull / false -> Insert wasn't
     * successfull
     */
    public boolean insertKnapsackElement(KnapsackElement element) {
        if (connection != null) {
            try {
                PreparedStatement pStmt = connection.prepareStatement("INSERT INTO knapsackelement (weight,worth,name) VALUES(?,?,?);");
                pStmt.setString(1, Integer.toString(element.getMassValue()));
                pStmt.setString(2, Integer.toString(element.getWorthValue()));
                pStmt.setString(3, element.getElementName());
                return pStmt.execute();
            } catch (SQLException ex) {
                System.err.println(ex.toString());
                return false;
            }
        }
        return false;
    }

    /**
     * Update Statement for the Database
     *
     * @param sqlStatement -> Sql Statement which should be update the Database
     * @return true -> Database is updated / false -> Database isn't updated
     */
    public boolean updateDB(String sqlStatement) {
        if (connection != null) {
            try {
                Statement stmt = connection.createStatement();
                stmt.executeUpdate(sqlStatement);
                return true;
            } catch (SQLException ex) {
                System.out.println(ex.toString());
                return false;
            }
        }
        return false;
    }
    

    /**
     * Delete row in Knapsack Elements
     *
     * @param id - ID Primary Key
     */
    public void deleteInKnapsackElements(String id) {
        this.updateDB("DELETE FROM knapsackelement WHERE elem_id='" + id + "';");
    }

    /**
     * Delete row in Knapsack
     *
     * @param id - ID Primary Key
     */
    public void deleteInKnapsack(String id) {
        this.updateDB("DELETE FROM knapsack WHERE knap_id='" + id + "';");
    }

    /**
     * Reads all Knapsack Element Objects from the SQLite Database
     * @return 
     */
    public ArrayList<KnapsackElement> getKnapsackElements() {
        if (this.connection != null) {
            ArrayList<KnapsackElement> knapElements = new ArrayList<>();
            try {
                Statement stmt = this.connection.createStatement();
                ResultSet resultSet = stmt.executeQuery("SELECT * FROM knapsackelement;");
                while (resultSet.next()) {
                    KnapsackElement e;
                    e = new KnapsackElement(resultSet.getString(4), Integer.parseInt(resultSet.getString(2)), Integer.parseInt(resultSet.getString(3)));
                    e.setId(Integer.parseInt(resultSet.getString(1)));
                    knapElements.add(e);
                }
                return knapElements;
            } catch (SQLException ex) {
                Logger.getLogger(SqlHelper.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        }
        return null;
    }

    /**
     * Reads all Knapsack Objects from the SQLite Database
     * @return 
     */
    public ArrayList<Knapsack> getKnapsack() {
        if (this.connection != null) {
            ArrayList<Knapsack> knapsack = new ArrayList<>();
            try {
                Statement stmt = this.connection.createStatement();
                ResultSet resultSet = stmt.executeQuery("SELECT * FROM knapsack;");
                while (resultSet.next()) {
                    Knapsack e = new Knapsack(Integer.parseInt(resultSet.getString(3)), resultSet.getString(2));
                    e.setId(Integer.parseInt(resultSet.getString(1)));
                    knapsack.add(e);
                }
                return knapsack;
            } catch (SQLException ex) {
                Logger.getLogger(SqlHelper.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        }
        return null;
    }

}
