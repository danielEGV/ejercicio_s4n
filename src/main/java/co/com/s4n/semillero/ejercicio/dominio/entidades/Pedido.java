package co.com.s4n.semillero.ejercicio.dominio.entidades;

import co.com.s4n.semillero.ejercicio.dominio.vo.Movimiento;
import io.vavr.collection.List;
import lombok.Getter;

public class Pedido {
    //@Getter
    List<Movimiento> movimientoList;

    public Pedido(List<Movimiento> movimientoList) {
        this.movimientoList = movimientoList;
    }

    public List<Movimiento> getMovimientoList() {
        return movimientoList;
    }
}
