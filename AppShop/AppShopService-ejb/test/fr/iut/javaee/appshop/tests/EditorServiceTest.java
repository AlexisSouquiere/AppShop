/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.iut.javaee.appshop.tests;

import fr.iut.javaee.appshop.commons.Editor;
import fr.iut.javaee.appshop.service.local.EditorServiceLocal;
import fr.iut.javaee.appshop.service.local.impl.EditorService;
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
public class EditorServiceTest 
{
    Server hsqlServer = null;
    Connection connection = null;
    EditorServiceLocal service = new EditorService();
    
    public EditorServiceTest() 
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
                "CREATE TABLE APPSHOP.EDITOR ( " +
                "EDITOR_ID INTEGER NOT NULL, " +
                "EDITOR_NAME VARCHAR(50) NOT NULL, " +
                "EDITOR_DESCRIPTION VARCHAR(500), " +
                "PRIMARY KEY (EDITOR_ID));"
            ).execute();
            
            connection.prepareStatement(
                "INSERT INTO APPSHOP.EDITOR VALUES (1, 'Microsoft', 'Microsoft');"
            ).execute();
            
            connection.prepareStatement(
                "INSERT INTO APPSHOP.EDITOR VALUES (2, 'NullSoft', 'NullSoft');"
            ).execute();
                        
           EntityManagerFactory emf = 
                   Persistence.createEntityManagerFactory("manager");
           EntityManager em = emf.createEntityManager();  
           service.setEM(em); 
        } 
        catch (SQLException ex) {
            Logger.getLogger(EditorServiceTest.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch (ClassNotFoundException ex) {
            Logger.getLogger(EditorServiceTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @After
    public void tearDown() {
        try {
            connection.close();
            hsqlServer.shutdown();
        } catch (SQLException ex) {
            Logger.getLogger(EditorServiceTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void findAllTest() 
    {  
        List<Editor> editors = service.findAll();
        assertEquals(2, editors.size());
    }
    
    @Test
    public void findOneByIdTest() 
    {  
        Editor editor = service.findOneById(2);
        assertEquals("NullSoft", editor.getEditorName());
    }
}
