package co.com.s4n.semillero.ejercicio.dominio.entidades;

import io.vavr.collection.List;
import lombok.Getter;

public class Dron {

    @Getter
    private Posicion posicion;

    @Getter
    private List<Pedido> pedidos;

    public Dron(Posicion posicion, List<Pedido> pedidos) {
        this.posicion = posicion;
        this.pedidos = pedidos;
    }
}
