/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.uv.tcswpractica02;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ssova
 */
public class Saludo implements Mensaje{

    @Override
    public void msg() {
           Logger.getLogger(Saludo.class.getName()).log(Level.INFO, "Hola mundo");

    }

    @Override
    public void imprimir() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
