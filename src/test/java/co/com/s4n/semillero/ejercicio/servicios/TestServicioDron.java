package co.com.s4n.semillero.ejercicio.servicios;

import co.com.s4n.semillero.ejercicio.dominio.entidades.Dron;
import co.com.s4n.semillero.ejercicio.dominio.entidades.Pedido;
import co.com.s4n.semillero.ejercicio.dominio.entidades.Posicion;
import co.com.s4n.semillero.ejercicio.dominio.servicios.ServicioArchivo;
import co.com.s4n.semillero.ejercicio.dominio.servicios.ServicioDron;
import co.com.s4n.semillero.ejercicio.dominio.vo.Movimiento;
import co.com.s4n.semillero.ejercicio.dominio.vo.Orientacion;
import io.vavr.collection.List;
import io.vavr.concurrent.Future;
import io.vavr.control.Try;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;


//@RunWith(PowerMockRunner.class)
//@PrepareForTest(fullyQualifiedNames = "co.com.s4n.semillero.ejercicio.dominio.servicios.ServicioDron")
public class TestServicioDron {

    /*
    @Test
    public void testMock() {
        //mockStatic(ServicioDron.class);
        List<Movimiento> entrega1 = List.of(Movimiento.Adelante, Movimiento.GDerecha, Movimiento.Adelante);
        List<Movimiento> entrega2 = List.of(Movimiento.GDerecha, Movimiento.GDerecha, Movimiento.Adelante);
        List<Movimiento> entrega3 = List.of(Movimiento.GIzquierda, Movimiento.Adelante, Movimiento.Adelante);
        List<Movimiento> entrega4 = List.of(Movimiento.Adelante, Movimiento.Adelante, Movimiento.Adelante);
        Pedido pedido1 = new Pedido(entrega1);
        Pedido pedido2 = new Pedido(entrega2);
        Pedido pedido3 = new Pedido(entrega3);
        Pedido pedido4 = new Pedido(entrega4);

        Posicion posicion = new Posicion(0, 0, Orientacion.Norte);

        //when(ServicioDron.crearDron()).thenReturn(Try.of(() -> new Dron(posicion, List.of(pedido1, pedido2, pedido3))));


        Try<Dron> drons = ServicioDron.crearDron();
        assertEquals(3, drons.get().getPedidos().length());
    }*/

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

    @Test
    public void testCrearListaDron() {
        List<Future<Dron>> drones = null;
        try {
            drones = ServicioDron.crearListaDron();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(drones.get(0).get().getPedidos().get(0).getMovimientoList().get(0));
        assertEquals(3, drones.length());
    }
}
