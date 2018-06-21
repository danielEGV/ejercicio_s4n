package co.com.s4n.semillero.ejercicio.servicios;

import co.com.s4n.semillero.ejercicio.dominio.entidades.Dron;
import co.com.s4n.semillero.ejercicio.dominio.entidades.Pedido;
import co.com.s4n.semillero.ejercicio.dominio.servicios.ServicioDron;
import co.com.s4n.semillero.ejercicio.dominio.vo.Movimiento;
import io.vavr.collection.List;
import io.vavr.control.Try;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestServicioDron {

     @Test
    public void testCrearDron() {
        Try<Dron> drons = ServicioDron.crearDron();
        assertEquals(List.of(
                Movimiento.Adelante,
                Movimiento.Adelante,
                Movimiento.Adelante,
                Movimiento.Adelante,
                Movimiento.GIzquierda,
                Movimiento.Adelante,
                Movimiento.Adelante,
                Movimiento.GDerecha),
                drons.get().getPedidos().get(0).getMovimientoList());

        assertEquals(0, drons.get().getPosicion().getX());
        assertEquals(0, drons.get().getPosicion().getY());
    }

    @Test
    public void testCrearDronConArchivo() {
        Try<Dron> drons = ServicioDron.crearDron("src/test/resources/in.txt");
        assertEquals(List.of(
                Movimiento.Adelante,
                Movimiento.Adelante,
                Movimiento.Adelante,
                Movimiento.Adelante,
                Movimiento.GIzquierda,
                Movimiento.Adelante,
                Movimiento.Adelante,
                Movimiento.GDerecha),
                drons.get().getPedidos().get(0).getMovimientoList());

        assertEquals(0, drons.get().getPosicion().getX());
        assertEquals(0, drons.get().getPosicion().getY());
    }


    @Test
    public void testCrearDronConPedidoManual() {
         List<Movimiento> movimientos = List.of(Movimiento.Adelante, Movimiento.Adelante, Movimiento.GDerecha);
         Pedido pedido = new Pedido(movimientos);
         List<Pedido> pedidos = List.of(pedido);
        Try<Dron> drons = ServicioDron.crearDron(pedidos);
        assertEquals(List.of(
                Movimiento.Adelante,
                Movimiento.Adelante,
                Movimiento.GDerecha),
                drons.get().getPedidos().get(0).getMovimientoList());

        assertEquals(0, drons.get().getPosicion().getX());
        assertEquals(0, drons.get().getPosicion().getY());
    }
}
