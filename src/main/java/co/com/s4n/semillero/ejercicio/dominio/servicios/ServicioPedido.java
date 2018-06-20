package co.com.s4n.semillero.ejercicio.dominio.servicios;

import co.com.s4n.semillero.ejercicio.dominio.entidades.Dron;
import co.com.s4n.semillero.ejercicio.dominio.entidades.Pedido;
import co.com.s4n.semillero.ejercicio.dominio.entidades.Posicion;
import co.com.s4n.semillero.ejercicio.dominio.vo.Movimiento;
import co.com.s4n.semillero.ejercicio.dominio.vo.Orientacion;
import io.vavr.collection.List;
import io.vavr.control.Try;

import java.io.IOException;

public class ServicioPedido {

    public static void entregarPedido (Dron dron) {
        Dron dron1 = dron;
        int x = 0;
        List<List<Try<Dron>>> drons = List.empty();
        while (!dron1.getPedidos().isEmpty()) {
            System.out.println(++x);
            drons = drons.append(entregarPedido1(dron1));
            List<Pedido> dropPedido = dron1.getPedidos().drop(3);
            dron1 = new Dron(new Posicion(0, 0, Orientacion.Norte), dropPedido);
            //System.out.println(dropPedido.get(0).getMovimientoList());
        }

        try {
            ServicioArchivo.escribirArchivo(drons);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Try<Dron>> entregarPedido1(Dron dron) {
        Dron[] drons = {dron};
        List<Pedido> pedidos = dron.getPedidos();
        List<Try<Dron>> entregas = pedidos
                .take(3)
                .map(pedido -> {
                    drons[0] = realizarMovimiento(drons[0], pedido.getMovimientoList()).get();
                    return Try.of(() -> drons[0]);
                });
        /*
        try {
            ServicioArchivo.escribirArchivo(entregas);
        } catch (IOException e) {
            e.printStackTrace();
        }
        */
        return entregas;
    }

    private static Try<Dron> realizarMovimiento(Dron dron, List<Movimiento> movimientoList) {
        Dron[] drons = {dron};
        movimientoList
                .forEach(movimiento -> {
            if (movimiento.equals(Movimiento.GDerecha) || movimiento.equals(Movimiento.GIzquierda)) {
                drons[0] = ServicioPosicion.cambiarOrientacion(drons[0], movimiento).get();
            } else {
                drons[0] = ServicioPosicion.avanzar(drons[0]).get();
            }
        });
        return Try.of(() -> drons[0]);
    }
}
