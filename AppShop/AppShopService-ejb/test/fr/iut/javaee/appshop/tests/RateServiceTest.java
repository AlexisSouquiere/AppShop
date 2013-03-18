/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.iut.javaee.appshop.tests;

import fr.iut.javaee.appshop.service.local.RateServiceLocal;
import fr.iut.javaee.appshop.service.local.impl.RateService;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import org.hsqldb.Server;
/**
 *
 * @author Alexis
 */
public class RateServiceTest 
{
    Server hsqlServer = null;
    Connection connection = null;
    RateServiceLocal service = new RateService();
    
    public RateServiceTest() 
    {
        hsqlServer = new Server();

        hsqlServer.setLogWriter(null);
        hsqlServer.setSilent(true);

        hsqlServer.setDatabaseName(0, "xdb");
        hsqlServer.setDatabasePath(0, "file:testdb");

        hsqlServer.start();
    }
    
    @Before
    public void setUp() 
    {
        try {
            Class.forName("org.hsqldb.jdbcDriver");
            
            connection = DriverManager.getConnection(
                "jdbc:hsqldb:hsql://localhost/xdb", "sa", "");            
            
            connection.prepareStatement("DROP SCHEMA IF EXISTS APPSHOP CASCADE")
                    .execute();               
            connection.prepareStatement("CREATE SCHEMA APPSHOP AUTHORIZATION sa")
                    .execute();
            
            connection.prepareStatement(
                "CREATE TABLE APPSHOP.APPLICATION ( " +
                    "APPLICATION_ID INTEGER NOT NULL, " +
                    "APPLICATION_RELEASE_DATE DATE, " +
                    "APPLICATION_WEBSITE VARCHAR(255), " + 
                    "APPLICATION_NAME VARCHAR(255) NOT NULL, " +
                    "APPLICATION_VERSION VARCHAR(255) NOT NULL, " +
                    "APPLICATION_PLATFORM_ID INTEGER NOT NULL, " +
                    "APPLICATION_PRICE DOUBLE, " +
                    "APPLICATION_EDITOR_ID INTEGER NOT NULL, " + 
                    "PRIMARY KEY (APPLICATION_ID));"
            ).execute();
            
            connection.prepareStatement(
                "CREATE TABLE APPSHOP.EDITOR ( " +
                    "EDITOR_ID INTEGER NOT NULL, " +
                    "EDITOR_NAME VARCHAR(50) NOT NULL, " +
                    "EDITOR_DESCRIPTION VARCHAR(500), " +
                    "PRIMARY KEY (EDITOR_ID));"
            ).execute();
            
            connection.prepareStatement(
                "CREATE TABLE APPSHOP.PLATFORM ( " + 
                    "PLATFORM_ID INTEGER NOT NULL, " +
                    "PLATFORM_NAME VARCHAR(255) NOT NULL, " +
                    "PLATFORM_VERSION VARCHAR(255) NOT NULL, " +
                    "PRIMARY KEY (PLATFORM_ID));"
            ).execute();
            
            connection.prepareStatement(
                "CREATE TABLE APPSHOP.RATE ( " +
                "RATE_ID INTEGER NOT NULL, " +
                "RATE INTEGER NOT NULL, " +
                "RATE_APPLICATION_ID INTEGER NOT NULL, " +
                "RATE_USER_ID INTEGER, " +
                "PRIMARY KEY (RATE_ID));"
            ).execute();
            
            connection.prepareStatement(
                "CREATE TABLE APPSHOP.USERS ( " +
                "USER_ID INTEGER NOT NULL, " +
                "USER_LASTNAME VARCHAR(255), " +
                "USER_USERNAME VARCHAR(255) NOT NULL, " +
                "USER_EMAIL VARCHAR(255) NOT NULL, " +
                "USER_BIRTHDATE DATE, " + 
                "USER_FIRSTNAME VARCHAR(255) NOT NULL, " +
                "USER_PASSWORD VARCHAR(255) NOT NULL, " + 
                "USER_GROUP_NAME VARCHAR(50) NOT NULL, " +
                "PRIMARY KEY (USER_ID));"
            ).execute();
            
            connection.prepareStatement(
                "ALTER TABLE APPSHOP.APPLICATION " +
                    "ADD FOREIGN KEY (APPLICATION_PLATFORM_ID) " +
                    "REFERENCES APPSHOP.PLATFORM (PLATFORM_ID); "
            ).execute();    
            
            connection.prepareStatement(
                "ALTER TABLE APPSHOP.APPLICATION " +
                    "ADD FOREIGN KEY (APPLICATION_EDITOR_ID) " +
                    "REFERENCES APPSHOP.EDITOR (EDITOR_ID);"
            ).execute();   
            
            connection.prepareStatement(
                "ALTER TABLE APPSHOP.RATE " +
                "ADD FOREIGN KEY (RATE_APPLICATION_ID) " +
                "REFERENCES APPSHOP.APPLICATION (APPLICATION_ID);"
            ).execute();  
            
            connection.prepareStatement(
                "ALTER TABLE APPSHOP.RATE " +
                "ADD FOREIGN KEY (RATE_USER_ID) " +
                "REFERENCES APPSHOP.USERS (USER_ID);"
            ).execute();  
            
            connection.prepareStatement(
                "INSERT INTO APPSHOP.EDITOR VALUES (1, 'Microsoft', 'Microsoft');"
            ).execute();
            connection.prepareStatement(
                "INSERT INTO APPSHOP.PLATFORM VALUES (1, 'Windows', 'XP');"
            ).execute();
            
            connection.prepareStatement(
                "INSERT INTO APPSHOP.APPLICATION VALUES (2, '2011-07-02', null, 'Winamp', '1.0', 1, 19.95, 1);"
            ).execute();
            
            connection.prepareStatement(
                "INSERT INTO APPSHOP.RATE (RATE_ID, RATE, RATE_APPLICATION_ID) VALUES (1, 2, 2);"
            ).execute();
            
            connection.prepareStatement(
                "INSERT INTO APPSHOP.RATE (RATE_ID, RATE, RATE_APPLICATION_ID) VALUES (2, 4, 2);"
            ).execute();
            
            connection.prepareStatement(
                "INSERT INTO APPSHOP.RATE (RATE_ID, RATE, RATE_APPLICATION_ID) VALUES (3, 5, 2);"
            ).execute();
            
           EntityManagerFactory emf = 
                   Persistence.createEntityManagerFactory("manager");
           EntityManager em = emf.createEntityManager();  
           service.setEM(em);
        } 
        catch (SQLException ex) {
            Logger.getLogger(RateServiceTest.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch (ClassNotFoundException ex) {
            Logger.getLogger(RateServiceTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @After
    public void tearDown() {
        try {
            connection.close();
            hsqlServer.shutdown();
        } catch (SQLException ex) {
            Logger.getLogger(RateServiceTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void findApplicationRateAverageTest() 
    {   
        //3 rates : 3, 2, 1 /5 => Average : 3.6
        Double average = service.findApplicationRateAverage(2);
        assertEquals(3.6, average, 0.1);      
    }
}
