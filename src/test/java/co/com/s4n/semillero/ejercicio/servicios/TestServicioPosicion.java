package co.com.s4n.semillero.ejercicio.servicios;

import org.junit.Test;

import static org.junit.Assert.assertTrue;


public class TestServicioPosicion {

    @Test
    public void testMock() {
        assertTrue(true);
    }

    /*
    @Test
    public void testAvanzarNorte() {
        List<Movimiento> movimientos = List.of(Movimiento.Adelante, Movimiento.GIzquierda);
        Pedido pedido = new Pedido(movimientos);
        List<Pedido> pedidos = List.of(pedido);
        Dron dron = new Dron(new Posicion(0, 0, Orientacion.Norte), pedidos);
        Try<Dron> avanzar = ServicioPosicion.avanzar(dron);
        assertEquals(1, avanzar.get().getPosicion().getX());
    }
    */

}
