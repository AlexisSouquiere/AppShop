/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.iut.javaee.appshop.tests;

import fr.iut.javaee.appshop.commons.Application;
import fr.iut.javaee.appshop.service.local.ApplicationServiceLocal;
import fr.iut.javaee.appshop.service.local.impl.ApplicationService;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
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
public class ApplicationServiceTest 
{
    Server hsqlServer = null;
    Connection connection = null;
    ApplicationServiceLocal service = new ApplicationService();
    
    public ApplicationServiceTest() 
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
                "INSERT INTO APPSHOP.EDITOR(EDITOR_ID, EDITOR_NAME, EDITOR_DESCRIPTION) VALUES (1, 'Microsoft', 'Microsoft');"
            ).execute();
            connection.prepareStatement(
                "INSERT INTO APPSHOP.PLATFORM(PLATFORM_ID, PLATFORM_NAME, PLATFORM_VERSION) VALUES (1, 'Windows', 'XP');"
            ).execute();
            
            connection.prepareStatement(
                "INSERT INTO APPSHOP.APPLICATION(APPLICATION_ID, APPLICATION_RELEASE_DATE, APPLICATION_WEBSITE, APPLICATION_NAME, APPLICATION_VERSION, APPLICATION_PLATFORM_ID, APPLICATION_PRICE, APPLICATION_EDITOR_ID) VALUES (2, '2011-07-02', null, 'Winamp', '1.0', 1, 19.95, 1);"
            ).execute();
            connection.prepareStatement(
                "INSERT INTO APPSHOP.APPLICATION(APPLICATION_ID, APPLICATION_RELEASE_DATE, APPLICATION_WEBSITE, APPLICATION_NAME, APPLICATION_VERSION, APPLICATION_PLATFORM_ID, APPLICATION_PRICE, APPLICATION_EDITOR_ID) VALUES (1, '2010-09-03', null, 'Microsoft Office', '2010', 1, 120, 1);"
            ).execute();
            
           EntityManagerFactory emf = 
                   Persistence.createEntityManagerFactory("manager");
           EntityManager em = emf.createEntityManager();  
           service.setEM(em);
        } 
        catch (SQLException ex) {
            Logger.getLogger(ApplicationServiceTest.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch (ClassNotFoundException ex) {
            Logger.getLogger(ApplicationServiceTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @After
    public void tearDown() {
        try {
            connection.close();
            hsqlServer.shutdown();
        } catch (SQLException ex) {
            Logger.getLogger(ApplicationServiceTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void findAllTest() 
    {   
        //Find all the applications ordered by id desc
        List<Application> applications = service.findAll();
        assertEquals(2, applications.size());      
        assertEquals("Winamp", applications.get(0).getApplicationName());
    }
    
    @Test
    public void findApplicationListTest() 
    {   
        //Find all the applications ordered by name
        List<Application> applications = service.findApplicationList();
        assertEquals(2, applications.size());  
        assertEquals("Microsoft Office", applications.get(0).getApplicationName());
    }
    
    @Test
    public void findApplicationByIdTest() 
    {   
        //Find an application by its id
        Application application = service.findOneById(2);
        assertEquals("Winamp", application.getApplicationName());
    }
    
    @Test
    public void findApplicationsByPlatformIdTest() 
    {   
        //Find all the applications having the same platform
        List<Application> applications = service.findApplicationsByPlatformId(1);
        assertEquals(2, applications.size());  
    }
    
    @Test
    public void findApplicationsByEditorIdTest() 
    {   
        //Find all the applications having the same editor
        List<Application> applications = service.findApplicationsByEditorId(1);
        assertEquals(2, applications.size());  
    }
    
    @Test
    public void findLastFiveApplicationsAddedTest() 
    {   
        //Find the last five applications added
        List<Application> applications = service.findLastFiveApplicationsAdded();
        assertEquals(2, applications.size());  
    }
}
