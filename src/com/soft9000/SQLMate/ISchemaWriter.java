/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soft9000.SQLMate;

/**
 *
 * @author profnagy
 */
public interface ISchemaWriter {
        void write(StringBuilder sb) throws ExInvalid;
}
