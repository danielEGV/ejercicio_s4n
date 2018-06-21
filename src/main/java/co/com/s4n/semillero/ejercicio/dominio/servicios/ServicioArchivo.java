package co.com.s4n.semillero.ejercicio.dominio.servicios;

import co.com.s4n.semillero.ejercicio.dominio.entidades.Dron;
import co.com.s4n.semillero.ejercicio.dominio.entidades.Pedido;
import co.com.s4n.semillero.ejercicio.dominio.vo.Movimiento;
import io.vavr.collection.List;
import io.vavr.control.Option;
import io.vavr.control.Try;

import java.io.*;
import java.util.Properties;

public class ServicioArchivo {

    public static Properties leerProperties() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileReader("src/main/resources/archivo.properties"));
        return properties;
    }

    public static List<Pedido> leerArchivo() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(leerProperties().getProperty("rutaArchivoIn")));
        String line;
        List<Pedido> pedidos = List.empty();
        while ((line = bufferedReader.readLine()) != null) {
            pedidos = pedidos.append(new Pedido(determinarMovimiento(line)));
        }
        return pedidos;
    }

    public static List<Pedido> leerArchivo(String src) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(src));
        String line;
        List<Pedido> pedidos = List.empty();
        while ((line = bufferedReader.readLine()) != null) {
            pedidos = pedidos.append(new Pedido(determinarMovimiento(line)));
        }
        return pedidos;
    }

    public static List<Movimiento> determinarMovimiento(String line) throws IOException{
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
        for (int i = 0; i < line.length(); i++) {
            split = split.append(line.substring(i, i + 1));
        }
        return split;
    }

    public static void escribirArchivo(java.util.List<Try<Dron>> drones1) throws IOException {
        FileWriter fichero = new FileWriter(leerProperties().getProperty("rutaArchivoOut"));
        PrintWriter pw = new PrintWriter(fichero);

        pw.println("== Reporte de entregas ==");
        drones1.stream().forEach(drons -> pw.println(
                "(" + drons.getOrElse(new Dron()).getPosicion().getX()+ ", " +
                        drons.getOrElse(new Dron()).getPosicion().getY() + ") " +
                        drons.getOrElse(new Dron()).getPosicion().getOrientacion())
        );

        if (null != fichero) {
            fichero.close();
        }
    }

    public static void escribirArchivo(java.util.List<Try<Dron>> drones1, String src) throws IOException {
        FileWriter fichero = new FileWriter(src);
        PrintWriter pw = new PrintWriter(fichero);

        pw.println("== Reporte de entregas ==");
        drones1.stream().forEach(drons -> pw.println(
                "(" + drons.getOrElse(new Dron()).getPosicion().getX()+ ", " +
                        drons.getOrElse(new Dron()).getPosicion().getY() + ") " +
                        drons.getOrElse(new Dron()).getPosicion().getOrientacion())
        );

        if (null != fichero) {
            fichero.close();
        }
    }

    /*
    public static void escribirArchivo(List<List<Try<Dron>>> drones1, String src) throws IOException {
        FileWriter fichero = new FileWriter(src);
        PrintWriter pw = new PrintWriter(fichero);

        pw.println("== Reporte de entregas ==");
        drones1.forEach(drones -> drones.forEach(dron -> pw.println(
                "(" + dron.getOrElse(new Dron()).getPosicion().getX() + ", " +
                        dron.getOrElse(new Dron()).getPosicion().getY() + ") " +
                        dron.getOrElse(new Dron()).getPosicion().getOrientacion())));

        if (null != fichero) {
            fichero.close();
        }
    }


    public static void escribirArchivo(List<List<Try<Dron>>> drones1) throws IOException {
        FileWriter fichero = new FileWriter(leerProperties().getProperty("rutaArchivoOut"));
        PrintWriter pw = new PrintWriter(fichero);

        pw.println("== Reporte de entregas ==");
        drones1.forEach(drones -> drones.forEach(dron -> pw.println(
                "(" + dron.getOrElse(new Dron()).getPosicion().getX() + ", " +
                dron.getOrElse(new Dron()).getPosicion().getY() + ") " +
                dron.getOrElse(new Dron()).getPosicion().getOrientacion())));

        if (null != fichero) {
            fichero.close();
        }
    }
*/
}
