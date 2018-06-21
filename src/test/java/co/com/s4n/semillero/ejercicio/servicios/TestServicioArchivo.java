package co.com.s4n.semillero.ejercicio.servicios;

import co.com.s4n.semillero.ejercicio.dominio.entidades.Pedido;
import co.com.s4n.semillero.ejercicio.dominio.servicios.ServicioArchivo;
import co.com.s4n.semillero.ejercicio.dominio.vo.Movimiento;
import io.vavr.collection.List;
import io.vavr.control.Try;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestServicioArchivo {

    @Test
    public void testLecturaArchivo() {
        Try<List<Pedido>> pedido = Try.of(() -> ServicioArchivo.leerArchivo());
        List<Pedido> pedidos = pedido.get();
        assertEquals(4, pedidos.size());
        assertEquals(
                List.of(
                        Movimiento.Adelante,
                        Movimiento.Adelante,
                        Movimiento.Adelante,
                        Movimiento.Adelante,
                        Movimiento.GIzquierda,
                        Movimiento.Adelante,
                        Movimiento.Adelante,
                        Movimiento.GDerecha),
                pedidos.get(0).getMovimientoList());
        assertEquals(
                List.of(
                        Movimiento.GDerecha,
                        Movimiento.GDerecha,
                        Movimiento.Adelante,
                        Movimiento.GIzquierda,
                        Movimiento.Adelante,
                        Movimiento.GDerecha
                ),
                pedidos.get(1).getMovimientoList()
        );
    }
}
