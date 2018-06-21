package co.com.s4n.semillero.ejercicio.dominio.servicios;

import co.com.s4n.semillero.ejercicio.dominio.entidades.Dron;
import co.com.s4n.semillero.ejercicio.dominio.entidades.Pedido;
import co.com.s4n.semillero.ejercicio.dominio.entidades.Posicion;
import co.com.s4n.semillero.ejercicio.dominio.vo.Movimiento;
import co.com.s4n.semillero.ejercicio.dominio.vo.Orientacion;
import io.vavr.collection.List;
import io.vavr.control.Try;

import java.util.ArrayList;

public class ServicioPedido {

    public static Boolean entregarPedido (Dron dron) throws Exception{
        Dron dron1 = dron;
        java.util.List<Try<Dron>> list = new ArrayList();

        while (!dron1.getPedidos().isEmpty()) {
            realizarPedido(dron1).forEach(list::add);
            List<Pedido> dropPedido = dron1.getPedidos().drop(3);
            dron1 = new Dron(new Posicion(0, 0, Orientacion.Norte), dropPedido);
        }

        ServicioArchivo.escribirArchivo(list);

        return true;
        /*
        List<Pedido> pedidos = dron.getPedidos();
        Dron dron2 = dron;
        List<Try<Dron>> drons = entregarPedido1(dron2, pedidos)
                .map(drons1 -> {
                    pedidos.drop(3);
                    return drons1;
                });
        for (int i = 0; i < drons.size(); i++) {
            System.out.println(drons.get(i).get().getPosicion().getX() + " - " + drons.get(i).get().getPosicion().getY());
        }
        ServicioArchivo.escribirArchivo(drons);
        */

    }

    public static Boolean entregarPedido (Dron dron, String src) throws Exception{
        Dron dron1 = dron;
        java.util.List<Try<Dron>> list = new ArrayList();

        while (!dron1.getPedidos().isEmpty()) {
            realizarPedido(dron1).forEach(list::add);
            List<Pedido> dropPedido = dron1.getPedidos().drop(3);
            dron1 = new Dron(new Posicion(0, 0, Orientacion.Norte), dropPedido);
        }

        ServicioArchivo.escribirArchivo(list, src);

        return true;
    }

    public static List<Try<Dron>> realizarPedido(Dron dron) {
        Dron[] drons = {dron};
        List<Try<Dron>> entregas = dron.getPedidos()
                .take(3)
                .map(pedido -> {
                    drons[0] = realizarMovimiento(drons[0], pedido.getMovimientoList()).get();
                    return Try.of(() -> drons[0]);
                });
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
