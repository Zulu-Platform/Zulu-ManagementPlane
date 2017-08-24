/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.libDataBusZulu.utils;

/**
 *
 * @author Носов А.В.
 */
public class Shell {
    
    // Variables declaration
    private String output;
    private String message;
    private String error;
    private int exitVal;
    // End of variables declaration

    public Shell() {
        this(null, null, null, -1);
    }
    
    public Shell(String output, String message, String error, int exitVal) {
        this.output = output;
        this.message = message;
        this.error = error;
        this.exitVal = exitVal;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public int getExitVal() {
        return exitVal;
    }

    public void setExitVal(int exitVal) {
        this.exitVal = exitVal;
    }
    
}
