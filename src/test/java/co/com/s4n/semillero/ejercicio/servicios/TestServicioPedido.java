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
        Try<Boolean> servicio = Try.of(() -> ServicioPedido.entregarPedido(dron.getOrElse(new Dron())));
        assertTrue(servicio.get());
    }


    //Si hay valores que no corresponden no crea el archivo de salida
    @Test
    public void testEntregaPedidoMal() {
        Try<Dron> dron = ServicioDron.crearDron("src/test/resources/in_3.txt");
        assertTrue(dron.isSuccess());
        Try<Boolean> servicio = Try.of(() -> ServicioPedido.entregarPedido(dron.get()));
        assertTrue(servicio.isEmpty());
    }


    @Test
    public void testEntregaPedido2() {
        Try<Dron> drons = ServicioDron.crearDron("src/test/resources/in_2.txt");
        Try<Boolean> servicio = Try.of(() -> ServicioPedido.entregarPedido(drons.get()));
        assertTrue(servicio.get());
    }


    // Si se pasa de la cantidad de cuadras el servicio falla Norte
    @Test
    public void testEntregaPedidoFueraDelBarrioNo() {
        Try<Dron> dron = ServicioDron.crearDron("src/test/resources/in_4.txt");
        Try<Boolean> servicio = Try.of(() -> ServicioPedido.entregarPedido(dron.get(), "src/test/resources/out_4.txt"));
        System.out.println("Barrio" + servicio);
        assertTrue(servicio.isFailure());
    }

    // Si se pasa de la cantidad de cuadras el servicio falla occidente
    @Test
    public void testEntregaPedidoFueraDelBarrioOc() {
        Try<Dron> dron = ServicioDron.crearDron("src/test/resources/in_5.txt");
        Try<Boolean> servicio = Try.of(() -> ServicioPedido.entregarPedido(dron.get(), "src/test/resources/out_4.txt"));
        System.out.println("Barrio" + servicio);
        assertTrue(servicio.isFailure());
    }

    // Si se pasa de la cantidad de cuadras el servicio falla Sur
    @Test
    public void testEntregaPedidoFueraDelBarrioSu() {
        Try<Dron> dron = ServicioDron.crearDron("src/test/resources/in_6.txt");
        Try<Boolean> servicio = Try.of(() -> ServicioPedido.entregarPedido(dron.get(), "src/test/resources/out_5.txt"));
        System.out.println("Barrio" + servicio);
        assertTrue(servicio.isFailure());
    }

    // Si se pasa de la cantidad de cuadras el servicio falla Oriente
    @Test
    public void testEntregaPedidoFueraDelBarrioOr() {
        Try<Dron> dron = ServicioDron.crearDron("src/test/resources/in_7.txt");
        Try<Boolean> servicio = Try.of(() -> ServicioPedido.entregarPedido(dron.get(), "src/test/resources/out_6.txt"));
        System.out.println("Barrio" + servicio);
        assertTrue(servicio.isFailure());
    }
}
