/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.daw.evaulacion2.clases;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import static java.util.Objects.hash;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.regex.Pattern;

/**
 *
 * @author alumno
 */
public class Concesionario implements Comparable<Concesionario> {

    private Map<String, Set<Vehiculo>> coche = new TreeMap<>();
    private Set<Vehiculo> vendido = new TreeSet<>();

    public final static Pattern PATRON_CIF = Pattern.compile("[A-Z][0-9]{8}");
    public final static Pattern PATRON_NOMBRE = Pattern.compile("[a-zA-Z0-9\\.]+");
    private final String cif;
    private final String nombre;

    public Concesionario(String cif, String nombre) {
        checkNombre(nombre);
        checkCif(cif);

        this.cif = cif;
        this.nombre = nombre;
    }

    private static void checkNombre(String nombre) {
        if (nombre == null || !PATRON_NOMBRE.matcher(nombre).matches()) {
            throw new IllegalArgumentException();

        }

    }

    private static void checkCif(String cif) {
        if (cif == null || !PATRON_CIF.matcher(cif).matches()) {
            throw new IllegalArgumentException();

        }

    }

    private static int comprados = 0;

    public boolean addVehiculo(Vehiculo c) {

        if (!coche.containsKey(c.getBastidor())) {
            coche.put(c.getBastidor(), new TreeSet<Vehiculo>());

            coche.get(c.getBastidor()).add(c);
            comprados++;
            return true;
        }
        return false;

    }

    public boolean registrarVenta(String bastidor) {
        if (coche.containsKey(bastidor)) {
            Iterator<Vehiculo> it = coche.get(bastidor).iterator();
            Vehiculo cocheVendido = it.next();
            vendido.add(cocheVendido);
            cocheVendido.setFechaVenta();
            coche.remove(bastidor);
            comprados--;
            return true;
        }
        return false;

    }

    public String mostrarVehiculosVendidos() {
        String vehiculos = "";
        for (Vehiculo vehiculo : vendido) {
            vehiculos += vehiculo.toString() + "\n";
        }
        return vehiculos;
    }

    public String mostrarVehiculosNoVendidos() {
        String vehiculos = "";
        for (Set<Vehiculo> value : coche.values()) {
            vehiculos += value.iterator().next().toString() + "\n";
        }
        return vehiculos;
    }

    @Override
    public String toString() {
        return "Concesionario{" + "cif=" + cif + "\n" + "nombre=" + nombre + "\n" + "coche=" + coche + "\n" + "vendido=" + vendido + "\n" + "comprados=" + comprados + "\n" + '}';
    }

    @Override
    public int hashCode() {
        return hash(this.cif);
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
        final Concesionario other = (Concesionario) obj;
        if (!Objects.equals(this.cif, other.cif)) {
            return false;
        }
        return true;
    }

    public int getVendidos() {
        int vendidos = vendido.size();
        return vendidos;
    }

    public int getSinvender() {
        int sinVender = coche.values().size();
        return sinVender;

    }

    public double getPVPTotalNoVendidos() {
        double pvp = 0;
        for (Set<Vehiculo> value : coche.values()) {
            Iterator<Vehiculo> it = value.iterator();
            Vehiculo next = it.next();
            pvp += next.getPrecioVenta();
        }
        return pvp;
    }

    public double getPVPTotalVendidos() {
        double pvp = 0;
        for (Vehiculo vehiculo : vendido) {
            pvp += vehiculo.getPrecioVenta();
        }
        return pvp;
    }

    public String getCif() {
        return cif;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public int compareTo(Concesionario t) {
        return this.cif.compareTo(t.cif);
    }

}
