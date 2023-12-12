/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.soar.ssn.v4.client.exceptions;

/**
 *
 * @author dimitriroulin
 */
public class UnauthorizedActionException extends Exception {
    private String message;

    public UnauthorizedActionException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
