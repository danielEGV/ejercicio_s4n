package co.com.s4n.semillero.ejercicio.dominio.servicios;


import co.com.s4n.semillero.ejercicio.dominio.entidades.Dron;
import co.com.s4n.semillero.ejercicio.dominio.entidades.Pedido;
import co.com.s4n.semillero.ejercicio.dominio.vo.Movimiento;
import io.vavr.collection.List;
import io.vavr.control.Option;
import io.vavr.control.Try;

import java.io.*;

public class ServicioArchivo {

    public static List<Pedido> leerArchivo() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("/home/s4n/Documents/semillero_s4n/daniel/s4n/ejercicio/src/main/resources/in.txt"));
        String line;
        List<Pedido> pedidos = List.empty();
        while ((line = bufferedReader.readLine()) != null) {
            pedidos = pedidos.append(new Pedido(determinarMovimiento(line)));
        }
        return pedidos;
    }



    public static List<Movimiento> determinarMovimiento(String line) {
        List<String> split = separarValores(line);
        List<Movimiento> movimientos = split.map(s -> {
            switch (s) {
                case "A":
                    return Movimiento.Adelante;
                case "I":
                    return Movimiento.GIzquierda;
                case "D":
                    return Movimiento.GDerecha;
                    default:
                        return null;
            }
        });
        return movimientos;

    }

    public static List<String> separarValores(String line) {
        List<String> split = List.empty();
        for (int i = 0; i < line.length(); i++)
        {
            split = split.append(line.substring(i, i + 1));
        }
        return split;
    }

    public static void escribirArchivo(List<List<Try<Dron>>> drones1) throws IOException {
        FileWriter fichero = new FileWriter("/home/s4n/Documents/semillero_s4n/daniel/s4n/ejercicio/src/main/resources/out.txt");
        PrintWriter pw = new PrintWriter(fichero);



        pw.println("== Reporte de entregas ==");
        drones1.forEach(drones -> drones.forEach(dron -> pw.println(
                "(" + dron.get().getPosicion().getX() + ", " +
                dron.get().getPosicion().getY() + ") " +
                dron.get().getPosicion().getOrientacion())));


                if (null != fichero) {
                    fichero.close();
                }


        /*
        System.out.println(
                "(" + dron.get().getPosicion().getX() + ", " +
                        dron.get().getPosicion().getY() + ") " +
                        dron.get().getPosicion().getOrientacion()
        );
        */
    }
}
