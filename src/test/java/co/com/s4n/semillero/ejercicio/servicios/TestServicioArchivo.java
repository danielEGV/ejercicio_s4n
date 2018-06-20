package co.com.s4n.semillero.ejercicio.servicios;

import co.com.s4n.semillero.ejercicio.dominio.entidades.Pedido;
import co.com.s4n.semillero.ejercicio.dominio.servicios.ServicioArchivo;
import io.vavr.collection.List;
import io.vavr.control.Try;
import org.junit.Test;

public class TestServicioArchivo {

    @Test
    public void testLecturaArchivo() {
        Try<List<Pedido>> pedido = Try.of(() -> ServicioArchivo.leerArchivo());
        /*pedido.forEach(pedidos -> pedidos.forEach(pedido ->
                pedido.getMovimientoList().forEach(System.out::println)));*/
        List<Pedido> pedidos = pedido.get();

    }
}
