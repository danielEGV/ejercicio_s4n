package co.com.s4n.semillero.ejercicio.dominio.entidades;

import io.vavr.collection.List;
import lombok.Getter;

public class Dron {

    private int id;

    //@Getter
    private Posicion posicion;

    //@Getter
    private List<Pedido> pedidos;

    public Dron(int id, Posicion posicion, List<Pedido> pedidos) {
        this.id = id;
        this.posicion = posicion;
        this.pedidos = pedidos;
    }

    public Dron(Posicion posicion, List<Pedido> pedidos) {
        this.id = id;
        this.posicion = posicion;
        this.pedidos = pedidos;
    }

    public Dron() {

    }

    public Posicion getPosicion() {
        return posicion;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public int getId() {
        return id;
    }
}
