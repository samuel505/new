/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.daw.evaulacion2.clases;

import java.time.LocalDate;
import java.util.Objects;
import static java.util.Objects.hash;
import java.util.regex.Pattern;


/**
 *
 * @author alumno
 */
public final class Vehiculo implements Comparable<Vehiculo>{

    private final String marca;
    private final String modelo;
    private final String bastidor;
    private final TipoVehiculo tipoVehiculo;
    private final double precioCompra;
    private int margenDeBeneficio;
    private double precioVenta;
    private final LocalDate fechaCompra;
    private LocalDate fechaVenta;
    public final static Pattern PATRON_BASTIDOR = Pattern.compile("[A-Z]{6}[0-9][A-Z]{4}[0-9]{6}");
    public final static Pattern PATRON_MARCAMODELO = Pattern.compile("[a-zA-Z0-9 ]+");
    
    public Vehiculo(String marca, String modelo, String bastidor, TipoVehiculo tipoVehiculo, double precioCompra, int margenDeBeneficio, LocalDate fechaCompra) {
        checkMarca(marca);
        checkmodelo(modelo);
        checkBastidor(bastidor);
        checkTipoVehiculo(tipoVehiculo);
        checkPrecioCompra(precioCompra);
        checkMargenBeneficio(margenDeBeneficio);                
        checkFechaCompra(fechaCompra);        
             
        
        this.marca = marca;
        this.modelo = modelo;
        this.bastidor = bastidor;
        this.tipoVehiculo = tipoVehiculo;
        this.precioCompra = precioCompra;
        this.margenDeBeneficio = margenDeBeneficio;
        this.precioVenta = getPrecioventa(precioCompra,margenDeBeneficio);
        this.fechaCompra = fechaCompra;
        this.fechaVenta = null;
    }

    
    protected void setFechaVenta(){
        this.fechaVenta=LocalDate.now();
    }
    
    private static void checkMarca(String marca) {
        if (marca == null || !PATRON_MARCAMODELO.matcher(marca).matches()) {
            throw new IllegalArgumentException();
        }

    }

    private static void checkmodelo(String modelo) {
        if (modelo == null || !PATRON_MARCAMODELO.matcher(modelo).matches()) {
            throw new IllegalArgumentException();
        }

    }

    private static void checkBastidor(String numBastidor) {
        if (numBastidor == null || !PATRON_BASTIDOR.matcher(numBastidor).matches()) {
            throw new IllegalArgumentException();
        }

    }

    private static void checkTipoVehiculo(TipoVehiculo tipoVehiculo) {
        if (tipoVehiculo == null) {
            throw new NullPointerException();

        }

    }

    private static void checkPrecioCompra(double precioCompra) {
        if (precioCompra < 1) {
            throw new IllegalArgumentException();
        }
    }

    private static void checkMargenBeneficio(int margen) {
        if (margen < 1) {
            throw new IllegalArgumentException();
        }

    }

    private static void checkPrecioVenta(double precioVenta) {
        if (precioVenta < 1) {
            throw new IllegalArgumentException();
        }
    }
    

    private static void checkFechaCompra(LocalDate fechaCompra) {
        if (fechaCompra== null || fechaCompra.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException();

        }

    }

//    private static void checkFechaVenta(LocalDate fechaVenta) {
//        if (fechaVenta != null || fechaVenta.isBefore(LocalDate.now())) {
//            throw new IllegalArgumentException();
//        }
//
//    }

     public double getPrecioventa(double precioCompra,int margen){
          
      double precioVenta = precioCompra+((precioCompra*margen)/100);
      return precioVenta;
      
         
    }
    
    
    public String getBastidor() {
        return bastidor;
    }

    public LocalDate getFechaCompra() {
        return fechaCompra;
    }

    public LocalDate getFechaVenta() {
        return fechaVenta;
    }

    @Override
    public int hashCode() {
       return hash(this.bastidor);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Vehiculo other = (Vehiculo) obj;
        if (!Objects.equals(this.bastidor, other.bastidor)) {
            return false;
        }
        return true;
    }

    public void setMargenDeVeneficio(int margenBeneficio) {
        checkMargenBeneficio(margenBeneficio);
        this.precioVenta = getPrecioventa(precioCompra,margenBeneficio);
        this.margenDeBeneficio = margenBeneficio;
    }

    
    
    @Override
    public String toString() {

        return "Marca: "+this.marca+"\n"+"Modelo: "+this.modelo+"\n"+"Bastidor: "+this.bastidor+"\n"+"Precio Compra: "+this.precioCompra+"\n"+"Margen: "+this.margenDeBeneficio+"%"+"\n"+"PVP: "+this.precioVenta+"\n"+"Fecha compra: "+this.fechaCompra+"\n"+"Fecha venta: "+this.fechaVenta+"\n"+"\n";
    }

    public String getModelo() {
        return modelo;
    }

    public double getPrecioCompra() {
        return precioCompra;
    }

    public int getMargenDeVeneficio() {
        return margenDeBeneficio;
    }

    public double getPrecioVenta() {
        System.out.println(precioVenta);
        return precioVenta;
    }

    @Override
    public int compareTo(Vehiculo t) {
       return this.bastidor.compareTo(t.bastidor);
        
    }
    
    
    

}
