/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.iut.javaee.appshop.tests;

import fr.iut.javaee.appshop.commons.Editor;
import fr.iut.javaee.appshop.commons.Platform;
import fr.iut.javaee.appshop.commons.Users;
import fr.iut.javaee.appshop.service.local.EditorServiceLocal;
import fr.iut.javaee.appshop.service.local.PlatformServiceLocal;
import fr.iut.javaee.appshop.service.local.UserServiceLocal;
import fr.iut.javaee.appshop.service.local.impl.EditorService;
import fr.iut.javaee.appshop.service.local.impl.PlateformService;
import fr.iut.javaee.appshop.service.local.impl.UserService;
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
public class UserServiceTest 
{
    Server hsqlServer = null;
    Connection connection = null;
    UserServiceLocal service = new UserService();
    
    public UserServiceTest() 
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
                "INSERT INTO APPSHOP.USERS VALUES (1, 'SOUQUIERE', 'admin', 'alexis.souquiere@gmail.com', '1992-08-12', 'Alexis', 'admin', 'Admin');"
            ).execute();
            
            connection.prepareStatement(
                "INSERT INTO APPSHOP.USERS VALUES (2, 'FOUGEROUSE', 'member', 'audrey.fougerouse@gmail.com', '1990-07-12', 'Audrey', 'member', 'Member');"
            ).execute();
                        
           EntityManagerFactory emf = 
                   Persistence.createEntityManagerFactory("manager");
           EntityManager em = emf.createEntityManager();  
           service.setEM(em); 
        } 
        catch (SQLException ex) {
            Logger.getLogger(UserServiceTest.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch (ClassNotFoundException ex) {
            Logger.getLogger(UserServiceTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @After
    public void tearDown() {
        try {
            connection.close();
            hsqlServer.shutdown();
        } catch (SQLException ex) {
            Logger.getLogger(UserServiceTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void findAllTest() 
    {  
        List<Users> users = service.findAll();
        assertEquals(2, users.size());
    }
    
    @Test
    public void findOneByIdTest() 
    {  
        Users user = service.findOneById(1);
        assertEquals(1, user.getUserId().intValue());
        assertEquals("admin", user.getUserUsername());
    }
}
