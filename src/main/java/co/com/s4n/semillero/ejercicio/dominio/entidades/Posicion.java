package co.com.s4n.semillero.ejercicio.dominio.entidades;

import co.com.s4n.semillero.ejercicio.dominio.vo.Orientacion;
import lombok.Getter;

public class Posicion {
    //@Getter
    private int x;

    //@Getter
    private int y;

    //@Getter
    private Orientacion orientacion;

    public Posicion(int x, int y, Orientacion orientacion) {
        this.x = x;
        this.y = y;
        this.orientacion = orientacion;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Orientacion getOrientacion() {
        return orientacion;
    }
}
