/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.zulu.server.services.db;

/**
 *
 * @author nosov
 */
public class ZuluFactorys {

    // Variables declaration
    private static ZuluFactorys instance = null;
    // End of variables declaration

    public static synchronized ZuluFactorys getInstance() {
        if (instance == null)  instance = new ZuluFactorys();
        return instance;
    }

    /**
     * 
     * @return 
     */
//    public EthernetDAO getEthernetDAO() {
//        if (ethernetDAO == null) ethernetDAO = new EthernetDAOImpl();
//        return ethernetDAO;
//    }
}
