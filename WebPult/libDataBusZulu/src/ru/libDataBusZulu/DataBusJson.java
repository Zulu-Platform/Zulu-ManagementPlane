/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.libDataBusZulu;

import com.google.gson.Gson;
import libMessage.client.Message;
import libMessage.client.messages.MessageGroups;
import libMessage.client.messages.MessageUsers;
import libMessage.client.messages.SystemsSettings;
import libMessage.client.messages.User;
import libMessage.client.systems.MessageSystem;
import libMessage.client.types.TypeMessage;
import libMessage.exceptions.ErrorsMessage;
import libMessage.exceptions.ExceptionsMessage;
import libMessage.parser.MessageParser;
import libMessage.utils.SystemMsg;
import org.apache.log4j.Logger;

/**
 *
 * @author Носов А.В.
 */
public class DataBusJson {
    
    // Variables declaration
    private static final Logger log = Logger.getLogger(DataBusJson.class);
    /** Пасер JSON. */
    private Gson gson = new Gson();
    // End of variables declaration
    
    public DataBusJson() {
    }
    
    /**
     * Возвращает данные используя запрос в формате JSON.
     * Ответ возвращает так же в формете JSON.
     * @param json запрос
     * @return ответ
     */
    public String getDataByJSON(String json) {
        if (json == null) return SystemMsg.getMsgNull();
        libDataBusZulu zulu = new libDataBusZulu();
        try {
            TypeMessage tm = MessageParser.parseJSON(json).getTypeMessage();
            switch (tm) {
                case RqUsers:
                    MessageUsers users = new MessageUsers();
                    users.setUsers(zulu.getUsersList());
                    json = gson.toJson(users, users.getClass());
                    break;
                case UserAdd:
                    Message msgUA = MessageParser.parseJSON(json);
                    User ua = (User) msgUA;
                    MessageSystem msua = new MessageSystem();
                    msua.setTypeMessage(TypeMessage.Info);
                    msua.setDescription(zulu.addUser(ua));
                    json = gson.toJson(msua, msua.getClass());
                    break;
                case UserEdit:
                    Message msgUE = MessageParser.parseJSON(json);
                    User ue = (User) msgUE;
                    MessageSystem msue = new MessageSystem();
                    msue.setTypeMessage(TypeMessage.Info);
                    msue.setDescription(zulu.addUser(ue));
                    json = gson.toJson(msue, msue.getClass());
                    break;
                case UserRemove:
                    Message msgUR = MessageParser.parseJSON(json);
                    User ur = (User) msgUR;
                    MessageSystem msur = new MessageSystem();
                    msur.setTypeMessage(TypeMessage.Info);
                    msur.setDescription(zulu.removeUser(ur.getName()));
                    json = gson.toJson(msur, msur.getClass());
                    break;
                case RqUserByName:
                    Message msgByName = MessageParser.parseJSON(json);
                    User un = (User) msgByName;
                    if ( (un==null) || (un.getName() == null) )
                        un = new User();
                    else
                        un = zulu.getUserByName(un.getName());
                    json = gson.toJson(un, un.getClass());
                    break;
                case RqGroups:
                    MessageGroups groups = new MessageGroups();
                    groups.setGroups(zulu.getGroupList());
                    json = gson.toJson(groups, groups.getClass());
                    break;
                case RqSystemsSettings:
                    SystemsSettings ss = zulu.getSystemsSettings();
                    json = gson.toJson(ss, ss.getClass());
                    break;
                case SystemsSettingsEdit:
                    Message msgSSE = MessageParser.parseJSON(json);
                    SystemsSettings sse = (SystemsSettings) msgSSE;
                    MessageSystem mssse = new MessageSystem();
                    mssse.setTypeMessage(TypeMessage.Info);
                    mssse.setDescription(zulu.editSystemSettings(sse));
                    json = gson.toJson(mssse, mssse.getClass());
                    break;
                default:
                    MessageSystem msg = SystemMsg.getMsgError(
                                        ErrorsMessage.WARNING_UNKNOWN_TYPE.getCode(), 
                                        ErrorsMessage.WARNING_UNKNOWN_TYPE.getDescription(), 
                                        ErrorsMessage.WARNING_UNKNOWN_TYPE.getDescription());
                    json = gson.toJson(msg, msg.getClass());
            }
        } catch (ExceptionsMessage ex) {
            MessageSystem msg = SystemMsg.getMsgError(
                    ex.getError().getCode(), 
                    ex.getError().name(), 
                    ex.getError().getDescription());
            json = gson.toJson(msg, msg.getClass());
        }
        
        return json;
    }
}
