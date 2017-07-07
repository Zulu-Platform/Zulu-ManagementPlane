/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.libDataBusZulu.messages;

import libMessage.client.messages.SystemsSettings;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Носов А.В.
 */
public class SystemsTest {
    
    public SystemsTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of getSystemsInfo method, of class Systems.
     */
    @Test
    public void testGetSystemsSettings() {
        System.out.println("Start testGetSystemsSettings");
        Systems instance = new Systems();
//        SystemsSettings expResult = null;
        SystemsSettings result = instance.getSystemsSettings();
        assertEquals("ZU-010814-A", result.getProductID());
        assertEquals("1122334455", result.getSerialnumber());
        assertEquals("0.0.0.1", result.getSoftware());
        assertEquals("", result.getHostname());
        assertEquals("", result.getContact());
        assertEquals("", result.getLocation());
        System.out.println("Stop testGetSystemsSettings");
    }
    
}
