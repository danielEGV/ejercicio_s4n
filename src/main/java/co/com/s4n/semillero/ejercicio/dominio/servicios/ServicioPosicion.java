package co.com.s4n.semillero.ejercicio.dominio.servicios;

import co.com.s4n.semillero.ejercicio.dominio.entidades.Dron;
import co.com.s4n.semillero.ejercicio.dominio.entidades.Posicion;
import co.com.s4n.semillero.ejercicio.dominio.vo.Movimiento;
import co.com.s4n.semillero.ejercicio.dominio.vo.Orientacion;
import io.vavr.control.Try;

public class ServicioPosicion {

    public static Try<Integer> fueraDeRango(int valor) {
        return (valor >= -10 && valor <= 10) ? Try.of(() -> valor) : Try.failure(new Exception());
    }

    public static Try<Dron> disminuirPosicionX(Dron dron) {
        return fueraDeRango(dron.getPosicion().getX())
                .flatMap(posicionX -> Try.of(() -> {
                    Integer posicion = posicionX;
                    return new Posicion(--posicion, dron.getPosicion().getY(), dron.getPosicion().getOrientacion());
                }).flatMap(posicion -> Try.of(() -> new Dron(posicion, dron.getPedidos()))));
    }

    public static Try<Dron> disminuirPosicionY(Dron dron) {
        return fueraDeRango(dron.getPosicion().getY())
                .flatMap(posicionY -> Try.of(() -> {
                    Integer posicion = posicionY;
                    return new Posicion(dron.getPosicion().getX(), --posicion, dron.getPosicion().getOrientacion());
                }).flatMap(posicion -> Try.of(() -> new Dron(posicion, dron.getPedidos()))));
    }

    public static Try<Dron> aumentarPosicionX(Dron dron) {
        return fueraDeRango(dron.getPosicion().getX())
                .flatMap(posicionX -> Try.of(() -> {
                    Integer posicion = posicionX;
                    return new Posicion(++posicion, dron.getPosicion().getY(), dron.getPosicion().getOrientacion());
                }).flatMap(posicion -> Try.of(() -> new Dron(posicion, dron.getPedidos()))));
    }

    public static Try<Dron> aumentarPosicionY(Dron dron) {
        return fueraDeRango(dron.getPosicion().getY())
                .flatMap(posicionY -> Try.of(() -> {
                    Integer posicion = posicionY;
                    return new Posicion(dron.getPosicion().getX(), ++posicion, dron.getPosicion().getOrientacion());
                }).flatMap(posicion -> Try.of(() -> new Dron(posicion, dron.getPedidos()))));
    }

    public static Try<Dron> cambiarOrientacion(Dron dron, Movimiento movimiento) {
        Orientacion orientacion = DeterminarNuevaOrientacion(dron.getPosicion().getOrientacion(), movimiento);

        return Try.of(() ->
                new Dron(
                        new Posicion(
                                dron.getPosicion().getX(),
                                dron.getPosicion().getY(),
                                orientacion),
                        dron.getPedidos()));
    }

    public static Try<Dron> avanzar(Dron dron) {
        return Try.of(() -> dron.getPosicion().getOrientacion())
                .flatMap(orientacion1 -> {
                    switch (orientacion1) {
                        case Norte:
                            return aumentarPosicionY(dron);
                        case Sur:
                            return disminuirPosicionY(dron);
                        case Occidente:
                            return aumentarPosicionX(dron);
                        case Oriente:
                            return disminuirPosicionX(dron);
                        default:
                            return Try.of(() -> dron);
                    }
                });
        /*
        switch (orientacion) {
            case Norte:
                return aumentarPosicionY(dron);
            case Sur:
                return disminuirPosicionY(dron);
            case Occidente:
                return aumentarPosicionX(dron);
            case Oriente:
                return disminuirPosicionX(dron);
                default:
                    return Try.of(() -> dron);
        }
        */
    }

    private static Orientacion DeterminarNuevaOrientacion(Orientacion orientacion, Movimiento movimiento) {
        switch (orientacion) {
            case Norte:
                return fijarOrientacionDesdeNorte(movimiento);
            case Sur:
                return fijarOrientacionDesdeSur(movimiento);
            case Occidente:
                return fijarOrientacionDesdeOccidente(movimiento);
            case Oriente:
                return fijarOrientacionDesdeOriente(movimiento);
                default:
                    return orientacion;
        }
    }

    private static Orientacion fijarOrientacionDesdeOriente(Movimiento movimiento) {
        switch (movimiento) {
            case GIzquierda:
                return Orientacion.Norte;
            case GDerecha:
                return Orientacion.Sur;
                default:
                    return Orientacion.Oriente;
        }
    }

    private static Orientacion fijarOrientacionDesdeOccidente(Movimiento movimiento) {
        switch (movimiento) {
            case GIzquierda:
                return Orientacion.Sur;
            case GDerecha:
                return Orientacion.Norte;
                default:
                    return Orientacion.Occidente;
        }
    }

    private static Orientacion fijarOrientacionDesdeSur(Movimiento movimiento) {
        switch (movimiento) {
            case GIzquierda:
                return Orientacion.Oriente;
            case GDerecha:
                return Orientacion.Occidente;
            default:
                return Orientacion.Sur;
        }

    }

    private static Orientacion fijarOrientacionDesdeNorte(Movimiento movimiento) {
        switch (movimiento) {
            case GIzquierda:
                return Orientacion.Occidente;
            case GDerecha:
                return Orientacion.Oriente;
                default:
                    return Orientacion.Norte;
        }
    }
}
