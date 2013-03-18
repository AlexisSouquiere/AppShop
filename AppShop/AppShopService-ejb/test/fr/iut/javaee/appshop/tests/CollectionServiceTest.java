/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.iut.javaee.appshop.tests;

import fr.iut.javaee.appshop.commons.Collection;
import fr.iut.javaee.appshop.service.local.CollectionServiceLocal;
import fr.iut.javaee.appshop.service.local.impl.CollectionService;
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
public class CollectionServiceTest 
{
    Server hsqlServer = null;
    Connection connection = null;
    CollectionServiceLocal service = new CollectionService();
    
    public CollectionServiceTest() 
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
                "CREATE TABLE APPSHOP.COLLECTION ( " +
        	"COLLECTION_ID INTEGER NOT NULL, " +
                "COLLECTION_NAME VARCHAR(25) NOT NULL, " +
                "COLLECTION_USERS_ID INTEGER NOT NULL, " +
                "PRIMARY KEY (COLLECTION_ID));"
            ).execute();
            
            connection.prepareStatement(
                    "ALTER TABLE APPSHOP.COLLECTION " +
                    "ADD FOREIGN KEY (COLLECTION_USERS_ID) " +
                    "REFERENCES APPSHOP.USERS (USER_ID);"
            ).execute();        
            
            connection.prepareStatement(
                "INSERT INTO APPSHOP.USERS VALUES (1, 'FOUGEROUSE', 'member', 'audrey.fougerouse@gmail.com', '1990-07-12', 'Audrey', 'member', 'Member');"
            ).execute();
            
            connection.prepareStatement(
                "INSERT INTO APPSHOP.COLLECTION VALUES (1, 'Collection', 1);"
            ).execute();
            
            connection.prepareStatement(
                "INSERT INTO APPSHOP.COLLECTION VALUES (2, 'Collection2', 1);"
            ).execute();
            
           EntityManagerFactory emf = 
                   Persistence.createEntityManagerFactory("manager");
           EntityManager em = emf.createEntityManager();  
           service.setEM(em); 
        } 
        catch (SQLException ex) {
            Logger.getLogger(CollectionServiceTest.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch (ClassNotFoundException ex) {
            Logger.getLogger(CollectionServiceTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @After
    public void tearDown() {
        try {
            connection.close();
            hsqlServer.shutdown();
        } catch (SQLException ex) {
            Logger.getLogger(CollectionServiceTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void findOneByIdTest() 
    {  
        Collection collection = service.findOneById(2);
        assertEquals("Collection2", collection.getCollectionName());
        assertEquals("member", collection.getCollectionUsers().getUserUsername());
    }
    
    @Test
    public void findCollectionsByMemberIdTest() 
    {  
        List<Collection> collections = service.findCollectionsByMemberId(1);
        assertEquals(2, collections.size());
    }
}
