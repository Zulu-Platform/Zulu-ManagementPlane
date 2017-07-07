/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libMessage.client.messages;

import java.io.Serializable;
import libMessage.client.Message;
import libMessage.client.types.TypeDuplex;
import libMessage.client.types.TypeSpeed;

/**
 * Ethernet интерфейс.
 * @author Носов А.В.
 */
public class Ethernet extends Message implements Serializable {
    
    // Variables declaration
    private static final long serialVersionUID = 1L;
    /** Идентификатор интерфейса. */
    private Short id;
    /** Номер порта. */
    private Short number;
    /** Имя. */
    private String name;
    /** Статус. */
    private boolean status;
    /** Скорость. */
    private String speed;
    /** Дуплексность. */
    private String duplex;
    /** Защищенный порт. */
    private boolean security;
    /** Flow control. */
    private boolean flowControl;
    /** Комментарий. */
    private String comment;
    // End of variables declaration
    
    public Ethernet() {
    }

    /**
     * Возвращает идентификатор порта.
     * @return идентификатор порта
     */
    public Short getId() {
        return id;
    }

    /**
     * Устанавливает идентификатор порта.
     * @param id идентификатор
     */
    public void setId(Short id) {
        this.id = id;
    }

    /**
     * Возвращает номер порта.
     * @return номер порта
     */
    public Short getNumber() {
        return number;
    }

    /**
     * Устанавливает номер порта.
     * @param number номер порта
     */
    public void setNumber(Short number) {
        this.number = number;
    }

    /**
     * Возвращает имя порта.
     * @return имя
     */
    public String getName() {
        return name;
    }

    /**
     * Устанавливает имя порта.
     * @param name имя
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Возвращает статус порта.
     * @return статус
     */
    public boolean getStatus() {
        return status;
    }

    /**
     * Устнавливает статус порта.
     * @param status статус
     */
    public void setStatus(boolean status) {
        this.status = status;
    }

    /**
     * Возвращает значение скорости порта.
     * @return скорость
     */
    public String getSpeed() {
        return speed;
    }

    /**
     * Устанавливает значение скорости порта.
     * @param speed скорость
     */
    public void setSpeed(String speed) {
        this.speed = speed;
    }
    
    /**
     * Возвращает описание скорости порта.
     * @return описание скорости
     */
    public String getSpeedDescription() {
        return TypeSpeed.valueOf(speed).getDescription();
    }

    /**
     * Устанавливает описание скорости порта.
     * @param speed описание скорости
     */
    public void setSpeedDescription(String speed) {
        this.speed = TypeSpeed.parseString(speed).name();
    }

    /**
     * Возвращает значение дуплекса порта.
     * @return дуплекс
     */
    public String getDuplex() {
        return duplex;
    }

    /**
     * Устанавливает значение дуплекса порта.
     * @param duplex дуплекс
     */
    public void setDuplex(String duplex) {
        this.duplex = duplex;
    }
    
    /**
     * Возвращает описание дуплексности порта.
     * @return описание дуплекса
     */
    public String getDuplexDescription() {
        return TypeDuplex.valueOf(duplex).getDescription();
    }

    /**
     * Устанавливает описание дуплексности порта.
     * @param duplex описание дуплекса
     */
    public void setDuplexDescription(String duplex) {
        this.duplex = TypeDuplex.parseString(duplex).name();
    }

    /**
     * Возвращает защищенность порта.
     * @return 
     */
    public boolean getSecurity() {
        return security;
    }

    /**
     * Устанавливает порт как защищенный.
     * @param security <b>true</b> - порт защищенный.
     */
    public void setSecurity(boolean security) {
        this.security = security;
    }

    /**
     * Возвращает значение flow control.
     * @return flow control
     */
    public boolean getFlowControl() {
        return flowControl;
    }

    /**
     * Устанавливает значение flow control.
     * @param flowControl flow control
     */
    public void setFlowControl(boolean flowControl) {
        this.flowControl = flowControl;
    }

    /**
     * Возвращает комментарий.
     * @return коментарий
     */
    public String getComment() {
        return comment;
    }

    /**
     * Устанавливает комментарий.
     * @param comment комментарий
     */
    public void setComment(String comment) {
        this.comment = comment;
    }
}
