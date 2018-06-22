package co.com.s4n.semillero.ejercicio.dominio.servicios;

import co.com.s4n.semillero.ejercicio.dominio.entidades.Dron;
import co.com.s4n.semillero.ejercicio.dominio.entidades.Pedido;
import co.com.s4n.semillero.ejercicio.dominio.entidades.Posicion;
import co.com.s4n.semillero.ejercicio.dominio.vo.Orientacion;
import io.vavr.collection.List;
import io.vavr.concurrent.Future;
import io.vavr.control.Try;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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

    public static Dron crearDronF(String srcArchivo) {
        Try<List<Pedido>> pedido = Try.of(() -> ServicioArchivo.leerArchivo(srcArchivo));

        if (pedido.isFailure()) {
            return new Dron();
        }

        System.out.println(">> Pedido: " + pedido);
        return new Dron(new Posicion(0, 0, Orientacion.Norte), pedido.getOrElse(List.of(new Pedido())));
    }

    public static List<Future<Dron>> crearListaDron() throws IOException {
        String src = "src/main/resources/in";
        ExecutorService es = Executors.newFixedThreadPool(3);
        List<Future<Dron>> futureDron = List.empty();
        System.out.println(ServicioArchivo.leerPropertiesLecturaF().size());
        for (int i = 1; i <= ServicioArchivo.leerPropertiesLecturaF().size(); i++) {
            final int x = i;
            System.out.println(src + "0" + i + ".txt");
            futureDron.append(Future.of(es,() -> crearDronF(src + "0" + x +".txt")));
        }

        return futureDron;
    }

}
