package co.com.s4n.semillero.ejercicio.servicios;

import co.com.s4n.semillero.ejercicio.dominio.entidades.Dron;
import co.com.s4n.semillero.ejercicio.dominio.servicios.ServicioDron;
import co.com.s4n.semillero.ejercicio.dominio.servicios.ServicioPedido;
import io.vavr.control.Try;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class TestServicioPedido {

    @Test
    public void testEntregaPedido() {
        Try<Dron> dron = ServicioDron.crearDron();
        //Try<Dron> dron = ServicioDron.crearDron("src/test/resources/in_3.txt");
        Try<Boolean> servicio = Try.of(() -> ServicioPedido.entregarPedido(dron.get()));

    }


    //Si hay valores que no corresponden no crea el archivo de salida
    @Test
    public void testEntregaPedidoMal() {
        Try<Dron> dron = ServicioDron.crearDron("src/test/resources/in_3.txt");
        assertTrue(dron.isSuccess());
        Try<Boolean> servicio = Try.of(() -> ServicioPedido.entregarPedido(dron.get()));
    }


    @Test
    public void testEntregaPedido2() {
        Try<Dron> drons = ServicioDron.crearDron("src/test/resources/in_2.txt");
        Try<Boolean> servicio = Try.of(() -> ServicioPedido.entregarPedido(drons.get()));
    }
}
