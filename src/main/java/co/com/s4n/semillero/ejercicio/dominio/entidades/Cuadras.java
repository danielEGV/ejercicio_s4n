package co.com.s4n.semillero.ejercicio.dominio.entidades;

import lombok.Getter;

public class Cuadras {

    //@Getter
    private int cantidadDeCuadras;

    public Cuadras() {
        this.cantidadDeCuadras = 10;
    }

    public Cuadras(int cantidadDeCuadras) {
        this.cantidadDeCuadras = cantidadDeCuadras;
    }

    public int getCantidadDeCuadras() {
        return cantidadDeCuadras;
    }
}
