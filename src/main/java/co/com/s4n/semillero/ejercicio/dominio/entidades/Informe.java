package co.com.s4n.semillero.ejercicio.dominio.entidades;

import io.vavr.collection.List;

public class Informe {
    List<Dron> drones;

    public Informe(List<Dron> drones) {
        this.drones = drones;
    }

    public List<Dron> getDrones() {
        return drones;
    }
}
