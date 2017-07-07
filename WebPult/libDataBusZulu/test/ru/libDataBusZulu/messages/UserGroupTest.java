/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.libDataBusZulu.messages;

import java.util.ArrayList;
import java.util.List;
import libMessage.client.messages.Group;
import libMessage.client.messages.User;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Носов А.В.
 */
public class UserGroupTest {
    
    public UserGroupTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of getUserList method, of class UserGroup.
     */
    @Test
    public void testGetUserList() {
        System.out.println("Start testGetUserList");
        UserGroup instance = new UserGroup();
        User expResult = null;
        User result = new User();
        result.setUid(1005);
        result.setGid(511);
        result.setName("admin");
        result.setGecos("Administrator");
        List<Group> groups = new ArrayList<Group>();
        groups.add(new Group(511, "monitoring"));
        groups.add(new Group(512, "systems"));
        groups.add(new Group(513, "interface"));
        groups.add(new Group(514, "mirroring"));
        groups.add(new Group(515, "firewall"));
        groups.add(new Group(516, "nat"));
        groups.add(new Group(517, "security"));
        groups.add(new Group(518, "usergroup"));
        groups.add(new Group(519, "tools"));
        result.setGroups(groups);
        
        List<User> users = instance.getUserList();
        System.out.println("Full users:");
        for (User user : users) {
            if (user.getName().equals("admin"))
                expResult = user;
            System.out.println("   "+user.getName());
        }
        
        assertNotNull(expResult);
        assertEquals(expResult.getUid(), result.getUid());
        assertEquals(expResult.getGid(), result.getGid());
        assertEquals(expResult.getGecos(), result.getGecos());
        assertEquals(expResult.getName(), result.getName());
        assertEquals(expResult.getPassword(), result.getPassword());
//        for (int i=0; i<result.getGroups().size(); i++) {
////            assertEquals(expResult.getGroups().get(i).getGid(), 
////                       result.getGroups().get(i).getGid());
////            assertEquals(expResult.getGroups().get(i).getName(), 
////                       result.getGroups().get(i).getName());
//            System.out.println(i+ " " + expResult.getGroups().get(i).getGid() + " " + expResult.getGroups().get(i).getName());
//            System.out.println(i+ " " + result.getGroups().get(i).getGid() + " " + result.getGroups().get(i).getName());
//        }
        //assertArrayEquals(expResult.getGroups().toArray(), result.getGroups().toArray());
        System.out.println("Stop testGetUserList");
    }
    
    @Test
    public void testGetGroupList() {
        System.out.println("Start testGetGroupList");
        
        System.out.println("Stop testGetGroupList");
    }
    
    @Test
    public void testGetGroupByName() {
        System.out.println("Start testGetGroupByName");
        UserGroup instance = new UserGroup();
        //User expResult = null;
        User result = instance.getUserByName("user03");
        for (Group group : result.getGroups())
            System.out.println("   " + group.getGid() + " " + group.getName());
        
        System.out.println("Stop testGetGroupByName");
    }
}
