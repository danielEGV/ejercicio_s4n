package co.com.s4n.semillero.ejercicio.servicios;

import co.com.s4n.semillero.ejercicio.dominio.entidades.Dron;
import co.com.s4n.semillero.ejercicio.dominio.servicios.ServicioDron;
import co.com.s4n.semillero.ejercicio.dominio.servicios.ServicioPedido;
import io.vavr.control.Try;
import org.junit.Test;

public class TestServicioPedido {

    @Test
    public void testEntregaPedido() {
        Try<Dron> dron = ServicioDron.crearDron();
        ServicioPedido.entregarPedido(dron.get());
    }
}
