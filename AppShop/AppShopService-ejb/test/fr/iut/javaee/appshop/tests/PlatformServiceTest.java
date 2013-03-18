/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.iut.javaee.appshop.tests;

import fr.iut.javaee.appshop.commons.Platform;
import fr.iut.javaee.appshop.service.local.PlatformServiceLocal;
import fr.iut.javaee.appshop.service.local.impl.PlateformService;
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
public class PlatformServiceTest 
{
    Server hsqlServer = null;
    Connection connection = null;
    PlatformServiceLocal service = new PlateformService();
    
    public PlatformServiceTest() 
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
                "CREATE TABLE APPSHOP.PLATFORM ( " +
                "PLATFORM_ID INTEGER NOT NULL, " +
                "PLATFORM_NAME VARCHAR(255) NOT NULL, " +
                "PLATFORM_VERSION VARCHAR(255) NOT NULL, " +
                "PRIMARY KEY (PLATFORM_ID));"
            ).execute();
            
            connection.prepareStatement(
                "INSERT INTO APPSHOP.PLATFORM VALUES (1, 'Windows', 'XP');"
            ).execute();
            
            connection.prepareStatement(
                "INSERT INTO APPSHOP.PLATFORM VALUES (3, 'Windows', 'Vista');"
            ).execute();
                        
           EntityManagerFactory emf = 
                   Persistence.createEntityManagerFactory("manager");
           EntityManager em = emf.createEntityManager();  
           service.setEM(em); 
        } 
        catch (SQLException ex) {
            Logger.getLogger(PlatformServiceTest.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch (ClassNotFoundException ex) {
            Logger.getLogger(PlatformServiceTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @After
    public void tearDown() {
        try {
            connection.close();
            hsqlServer.shutdown();
        } catch (SQLException ex) {
            Logger.getLogger(PlatformServiceTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void findAllTest() 
    {  
        List<Platform> platforms = service.findAll();
        assertEquals(2, platforms.size());
    }
    
    @Test
    public void findOneByIdTest() 
    {  
        Platform platform = service.findOneById(3);
        assertEquals("Windows", platform.getPlatformName());
        assertEquals("Vista", platform.getPlatformVersion());
    }
}
