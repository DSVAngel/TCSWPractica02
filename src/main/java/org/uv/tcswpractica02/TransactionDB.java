/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.uv.tcswpractica02;

import java.sql.Connection;

/**
 *
 * @author ssova
 */
public abstract class TransactionDB<T> {

    protected T pojoDB;

    protected TransactionDB(T pojoDB) {
        this.pojoDB = pojoDB;
    }
    public abstract boolean execute(Connection con);
}
