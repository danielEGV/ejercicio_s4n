package co.com.s4n.semillero.ejercicio.dominio.servicios;

import co.com.s4n.semillero.ejercicio.dominio.entidades.Dron;
import co.com.s4n.semillero.ejercicio.dominio.entidades.Pedido;
import co.com.s4n.semillero.ejercicio.dominio.entidades.Posicion;
import co.com.s4n.semillero.ejercicio.dominio.vo.Orientacion;
import io.vavr.collection.List;
import io.vavr.concurrent.Future;
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

    public static Try<Dron> crearDron(String srcArchivo) {
        Try<List<Pedido>> pedido = Try.of(() -> ServicioArchivo.leerArchivo(srcArchivo));

        if (pedido.isFailure()) {
            return Try.failure(new Exception());
        }
        return Try.of(() ->
                new Dron(new Posicion(0, 0, Orientacion.Norte), pedido.getOrElse(List.of(new Pedido()))));
    }

    public static Try<Dron> crearDron(List<Pedido> pedidos) {
        return Try.of(() ->
                new Dron(new Posicion(0, 0, Orientacion.Norte), pedidos));
    }

    public static Try<Dron> crearDronF(String srcArchivo) {
        Try<List<Pedido>> pedido = Try.of(() -> ServicioArchivo.leerArchivo(srcArchivo));

        if (pedido.isFailure()) {
            return Try.failure(new Exception());
        }
        return Try.of(() ->
                new Dron(new Posicion(0, 0, Orientacion.Norte), pedido.getOrElse(List.of(new Pedido()))));
    }

    public static List<Try<Dron>> crearListaDron() {
        String src = "src/main/resources/in";
        return List.of(
                crearDronF(src + "01.txt"),
                crearDronF(src + "02.txt"),
                crearDronF(src + "03.txt")
        );
    }

}
