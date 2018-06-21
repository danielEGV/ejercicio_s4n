package co.com.s4n.semillero.ejercicio.dominio.servicios;

import co.com.s4n.semillero.ejercicio.dominio.entidades.Cuadras;
import io.vavr.control.Try;

public class ServicioCuadras {

    public static Try<Integer> fueraDeRango(int valor) {
        Cuadras cuadras = new Cuadras();
        int valorAbs = Math.abs(valor);
        return (valorAbs < cuadras.getCantidadDeCuadras() - 1) ? Try.of(() -> valor) : Try.failure(new Exception());
    }

    public static Try<Integer> fueraDeRango(int valor, Cuadras cuadras) {
        int valorAbs = Math.abs(valor);
        return (valor < cuadras.getCantidadDeCuadras() - 1) ? Try.of(() -> valorAbs) : Try.failure(new Exception());
    }
}
