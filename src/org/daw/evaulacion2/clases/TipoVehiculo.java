/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.daw.evaulacion2.clases;

/**
 *
 * @author alumno
 */
public enum TipoVehiculo {
    Turismo , Ranchera , Monovolumen , SUV , Furgoneta , Otro;
    
    public static TipoVehiculo of(int i){
        if (i<0 || i>TipoVehiculo.values().length) {
            throw new IllegalArgumentException();
        }
        return TipoVehiculo.values()[i-1];
    }
    
}
