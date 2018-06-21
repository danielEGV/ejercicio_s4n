package co.com.s4n.semillero.ejercicio.dominio.servicios;

import co.com.s4n.semillero.ejercicio.dominio.entidades.Dron;
import co.com.s4n.semillero.ejercicio.dominio.entidades.Pedido;
import co.com.s4n.semillero.ejercicio.dominio.entidades.Posicion;
import co.com.s4n.semillero.ejercicio.dominio.vo.Orientacion;
import io.vavr.collection.List;
import io.vavr.control.Try;

public class ServicioDron {

    public static Try<Dron> crearDron() {
        Try<List<Pedido>> pedido = Try.of(() -> ServicioArchivo.leerArchivo());

        if (pedido.isFailure()) {
            return Try.failure(new Exception());
        }
        return Try.of(() ->
                new Dron(new Posicion(0, 0, Orientacion.Norte), ServicioArchivo.leerArchivo()));
    }
}
