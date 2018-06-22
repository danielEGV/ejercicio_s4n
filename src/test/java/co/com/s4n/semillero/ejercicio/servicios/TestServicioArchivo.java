package co.com.s4n.semillero.ejercicio.servicios;

import co.com.s4n.semillero.ejercicio.dominio.entidades.Dron;
import co.com.s4n.semillero.ejercicio.dominio.entidades.Pedido;
import co.com.s4n.semillero.ejercicio.dominio.entidades.Posicion;
import co.com.s4n.semillero.ejercicio.dominio.servicios.ServicioArchivo;
import co.com.s4n.semillero.ejercicio.dominio.servicios.ServicioDron;
import co.com.s4n.semillero.ejercicio.dominio.vo.Movimiento;
import co.com.s4n.semillero.ejercicio.dominio.vo.Orientacion;
import io.vavr.collection.List;
import io.vavr.control.Try;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.IOException;
import java.util.Properties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;


@RunWith(PowerMockRunner.class)
@PrepareForTest(fullyQualifiedNames = "co.com.s4n.semillero.ejercicio.dominio.servicios.ServicioArchivo")
public class TestServicioArchivo {

     @Test
    public void test() {
        List<Movimiento> entrega1 = List.of(Movimiento.Adelante, Movimiento.GDerecha, Movimiento.Adelante);
        List<Movimiento> entrega2 = List.of(Movimiento.GDerecha, Movimiento.GDerecha, Movimiento.Adelante);
        List<Movimiento> entrega3 = List.of(Movimiento.GIzquierda, Movimiento.Adelante, Movimiento.Adelante);
        List<Movimiento> entrega4 = List.of(Movimiento.Adelante, Movimiento.Adelante, Movimiento.Adelante);
        Pedido pedido1 = new Pedido(entrega1);
        Pedido pedido2 = new Pedido(entrega2);
        Pedido pedido3 = new Pedido(entrega3);
        Pedido pedido4 = new Pedido(entrega4);

        mockStatic(ServicioArchivo.class);

         try {
            //when(ServicioArchivo.leerArchivo()).thenReturn(List.of(pedido1, pedido2, pedido3));
             List<Pedido> pedidos = ServicioArchivo.leerArchivo();
             //assertEquals(3, pedidos.length());
             //assertEquals(entrega1, pedido1.getMovimientoList());
        } catch (IOException e) {
             assertTrue(false);
            e.printStackTrace();
        }




    }



    @Test
    public void testLecturaArchivo() {
        Try<List<Pedido>> pedido = Try.of(() -> ServicioArchivo.leerArchivo());
        List<Pedido> pedidos = pedido.get();
        assertEquals(4, pedidos.size());
        assertEquals(
                List.of(
                        Movimiento.Adelante,
                        Movimiento.Adelante,
                        Movimiento.Adelante,
                        Movimiento.Adelante,
                        Movimiento.GIzquierda,
                        Movimiento.Adelante,
                        Movimiento.Adelante,
                        Movimiento.GDerecha),
                pedidos.get(0).getMovimientoList());
        assertEquals(
                List.of(
                        Movimiento.GDerecha,
                        Movimiento.GDerecha,
                        Movimiento.Adelante,
                        Movimiento.GIzquierda,
                        Movimiento.Adelante,
                        Movimiento.GDerecha
                ),
                pedidos.get(1).getMovimientoList()
        );
    }

    @Test
    public void testLecturaArchivo_2() {
        Try<List<Pedido>> pedido = Try.of(() -> ServicioArchivo.leerArchivo("src/test/resources/in_2.txt"));
        List<Pedido> pedidos = pedido.get();
        assertEquals(1, pedidos.size());
        assertEquals(
                List.of(
                        Movimiento.Adelante,
                        Movimiento.Adelante,
                        Movimiento.Adelante,
                        Movimiento.Adelante,
                        Movimiento.GIzquierda,
                        Movimiento.Adelante,
                        Movimiento.Adelante,
                        Movimiento.GDerecha),
                pedidos.get(0).getMovimientoList());
    }


    @Test
    public void testPropiedades() {
         Properties properties = null;
        try {
            properties = ServicioArchivo.leerProperties();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals(2, properties.size());


    }
}
