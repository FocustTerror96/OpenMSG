/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oserver;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author sgmud
 */
public class SServerTest {
    
    public SServerTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of GetPort method, of class SServer.
     */
    @Test
    public void testGetPort() {
        System.out.println("GetPort");
        SServer instance = new SServer();
        int expResult = 0;
        int result = instance.GetPort();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of start method, of class SServer.
     */
    @Test
    public void testStart() {
        System.out.println("start");
        int port = 0;
        SServer instance = new SServer();
        instance.start(port);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of stop method, of class SServer.
     */
    @Test
    public void testStop() {
        System.out.println("stop");
        SServer instance = new SServer();
        instance.stop();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class SServer.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        SServer.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
