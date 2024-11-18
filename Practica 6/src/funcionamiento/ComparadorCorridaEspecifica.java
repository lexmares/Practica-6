package funcionamiento;

import java.util.Comparator;

public class ComparadorCorridaEspecifica implements Comparator<Corrida> {
    @Override
    public int compare(Corrida a, Corrida b) {

        int comparacion = a.getFecha().compareTo(b.getFecha());

        if (comparacion == 0) comparacion = a.getOrigen().compareTo(b.getOrigen());

        if (comparacion == 0) comparacion = a.getDestino().compareTo(b.getDestino());

        return comparacion;
    }
}
