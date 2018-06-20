package co.com.s4n.semillero.ejercicio.servicios;

import co.com.s4n.semillero.ejercicio.dominio.entidades.Dron;
import co.com.s4n.semillero.ejercicio.dominio.entidades.Pedido;
import co.com.s4n.semillero.ejercicio.dominio.entidades.Posicion;
import co.com.s4n.semillero.ejercicio.dominio.servicios.ServicioDron;
import co.com.s4n.semillero.ejercicio.dominio.servicios.ServicioPosicion;
import co.com.s4n.semillero.ejercicio.dominio.vo.Movimiento;
import co.com.s4n.semillero.ejercicio.dominio.vo.Orientacion;
import io.vavr.collection.List;
import io.vavr.control.Try;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class TestServicioPosicion {

    @Test
    public void testAvanzarNorte() {
        Dron dron = ServicioDron.crearDron().get();
        Try<Dron> avanzar = ServicioPosicion.avanzar(dron);
        assertEquals(1, avanzar.get().getPosicion().getY());
    }

    @Test
    public void testAumentarY() {
        Dron dron = ServicioDron.crearDron().get();
        Try<Dron> avanzar = ServicioPosicion.aumentarPosicionY(dron);
        assertEquals(1, avanzar.get().getPosicion().getY());
        assertEquals(0, avanzar.get().getPosicion().getX());
        assertEquals(Orientacion.Norte, avanzar.get().getPosicion().getOrientacion());
    }

    @Test
    public void testAvanzarSur() {
        Dron drons = ServicioDron.crearDron().get();
        Dron dron = new Dron(new Posicion(drons.getPosicion().getX(), drons.getPosicion().getY(), Orientacion.Sur), drons.getPedidos());
        Try<Dron> avanzar = ServicioPosicion.avanzar(dron);
        assertEquals(-1, avanzar.get().getPosicion().getY());
        assertEquals(0, avanzar.get().getPosicion().getX());
        assertEquals(Orientacion.Sur, avanzar.get().getPosicion().getOrientacion());
    }

    @Test
    public void testDisminuirY() {
        Dron drons = ServicioDron.crearDron().get();
        Dron dron = new Dron(new Posicion(drons.getPosicion().getX(), drons.getPosicion().getY(), Orientacion.Sur), drons.getPedidos());
        Try<Dron> avanzar = ServicioPosicion.disminuirPosicionY(dron);
        assertEquals(-1, avanzar.get().getPosicion().getY());
        assertEquals(0, avanzar.get().getPosicion().getX());
        assertEquals(Orientacion.Sur, avanzar.get().getPosicion().getOrientacion());
    }

    @Test
    public void testAvanzarOccidente() {
        Dron drons = ServicioDron.crearDron().get();
        Dron dron = new Dron(new Posicion(drons.getPosicion().getX(), drons.getPosicion().getY(), Orientacion.Occidente), drons.getPedidos());
        Try<Dron> avanzar = ServicioPosicion.avanzar(dron);
        assertEquals(1, avanzar.get().getPosicion().getX());
        assertEquals(0, avanzar.get().getPosicion().getY());
        assertEquals(Orientacion.Occidente, avanzar.get().getPosicion().getOrientacion());
    }

    @Test
    public void testAumentarX() {
        Dron dron = ServicioDron.crearDron().get();
        Try<Dron> avanzar = ServicioPosicion.aumentarPosicionX(dron);
        assertEquals(0, avanzar.get().getPosicion().getY());
        assertEquals(1, avanzar.get().getPosicion().getX());
        assertEquals(Orientacion.Norte, avanzar.get().getPosicion().getOrientacion());
    }

    @Test
    public void testAvanzarOriente() {
        Dron drons = ServicioDron.crearDron().get();
        Dron dron = new Dron(new Posicion(drons.getPosicion().getX(), drons.getPosicion().getY(), Orientacion.Oriente), drons.getPedidos());
        Try<Dron> avanzar = ServicioPosicion.avanzar(dron);
        assertEquals(0, avanzar.get().getPosicion().getY());
        assertEquals(-1, avanzar.get().getPosicion().getX());
        assertEquals(Orientacion.Oriente, avanzar.get().getPosicion().getOrientacion());
    }

    @Test
    public void testDisminuirX() {
        Dron drons = ServicioDron.crearDron().get();
        Dron dron = new Dron(new Posicion(drons.getPosicion().getX(), drons.getPosicion().getY(), Orientacion.Oriente), drons.getPedidos());
        Try<Dron> avanzar = ServicioPosicion.disminuirPosicionX(dron);
        assertEquals(0, avanzar.get().getPosicion().getY());
        assertEquals(-1, avanzar.get().getPosicion().getX());
        assertEquals(Orientacion.Oriente, avanzar.get().getPosicion().getOrientacion());
    }

    @Test
    public void testCambiarOrientacionNorteOccidente() {
        Dron dron = ServicioDron.crearDron().get();
        Try<Dron> drons = ServicioPosicion.cambiarOrientacion(dron, Movimiento.GIzquierda);
        assertEquals(Orientacion.Occidente, drons.get().getPosicion().getOrientacion());
        assertEquals(0, drons.get().getPosicion().getX());
        assertEquals(0, drons.get().getPosicion().getY());
    }

    @Test
    public void testCambiarOrientacionNorteOriente() {
        Dron dron = ServicioDron.crearDron().get();
        Try<Dron> drons = ServicioPosicion.cambiarOrientacion(dron, Movimiento.GDerecha);
        assertEquals(Orientacion.Oriente, drons.get().getPosicion().getOrientacion());
        assertEquals(0, drons.get().getPosicion().getX());
        assertEquals(0, drons.get().getPosicion().getY());
    }

    @Test
    public void testCambiarOrientacionSurOccidente() {
        Dron dron1 = ServicioDron.crearDron().get();
        Dron dron = new Dron(new Posicion(dron1.getPosicion().getX(), dron1.getPosicion().getY(), Orientacion.Sur), dron1.getPedidos());
        Try<Dron> drons = ServicioPosicion.cambiarOrientacion(dron, Movimiento.GIzquierda);
        assertEquals(Orientacion.Oriente, drons.get().getPosicion().getOrientacion());
        assertEquals(0, drons.get().getPosicion().getX());
        assertEquals(0, drons.get().getPosicion().getY());
    }

    @Test
    public void testCambiarOrientacionSurOriente() {
        Dron dron1 = ServicioDron.crearDron().get();
        Dron dron = new Dron(new Posicion(dron1.getPosicion().getX(), dron1.getPosicion().getY(), Orientacion.Sur), dron1.getPedidos());
        Try<Dron> drons = ServicioPosicion.cambiarOrientacion(dron, Movimiento.GDerecha);
        assertEquals(Orientacion.Occidente, drons.get().getPosicion().getOrientacion());
        assertEquals(0, drons.get().getPosicion().getX());
        assertEquals(0, drons.get().getPosicion().getY());
    }

    @Test
    public void testCambiarOrientacionOccidenteSur() {
        Dron dron1 = ServicioDron.crearDron().get();
        Dron dron = new Dron(new Posicion(dron1.getPosicion().getX(), dron1.getPosicion().getY(), Orientacion.Occidente), dron1.getPedidos());
        Try<Dron> drons = ServicioPosicion.cambiarOrientacion(dron, Movimiento.GIzquierda);
        assertEquals(Orientacion.Sur, drons.get().getPosicion().getOrientacion());
        assertEquals(0, drons.get().getPosicion().getX());
        assertEquals(0, drons.get().getPosicion().getY());
    }

    @Test
    public void testCambiarOrientacionOccidenteNorte() {
        Dron dron1 = ServicioDron.crearDron().get();
        Dron dron = new Dron(new Posicion(dron1.getPosicion().getX(), dron1.getPosicion().getY(), Orientacion.Occidente), dron1.getPedidos());
        Try<Dron> drons = ServicioPosicion.cambiarOrientacion(dron, Movimiento.GDerecha);
        assertEquals(Orientacion.Norte, drons.get().getPosicion().getOrientacion());
        assertEquals(0, drons.get().getPosicion().getX());
        assertEquals(0, drons.get().getPosicion().getY());
    }

    @Test
    public void testCambiarOrientacionOrienteNorte() {
        Dron dron1 = ServicioDron.crearDron().get();
        Dron dron = new Dron(new Posicion(dron1.getPosicion().getX(), dron1.getPosicion().getY(), Orientacion.Oriente), dron1.getPedidos());
        Try<Dron> drons = ServicioPosicion.cambiarOrientacion(dron, Movimiento.GIzquierda);
        assertEquals(Orientacion.Norte, drons.get().getPosicion().getOrientacion());
        assertEquals(0, drons.get().getPosicion().getX());
        assertEquals(0, drons.get().getPosicion().getY());
    }

    @Test
    public void testCambiarOrientacionOrienteSur() {
        Dron dron1 = ServicioDron.crearDron().get();
        Dron dron = new Dron(new Posicion(dron1.getPosicion().getX(), dron1.getPosicion().getY(), Orientacion.Oriente), dron1.getPedidos());
        Try<Dron> drons = ServicioPosicion.cambiarOrientacion(dron, Movimiento.GDerecha);
        assertEquals(Orientacion.Sur, drons.get().getPosicion().getOrientacion());
        assertEquals(0, drons.get().getPosicion().getX());
        assertEquals(0, drons.get().getPosicion().getY());
    }
}
