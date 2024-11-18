package funcionamiento;

import java.time.LocalDate;
import java.time.LocalTime;

public class Corrida implements Comparable<Corrida> {
    //protected String lienaDeAutobus, fecha, hora, origen, destino, tiempo, costos;

    protected String linea;
    protected LocalDate fecha;
    protected LocalTime hora;
    protected String origen;
    protected String destino;
    protected int tiempoMinutos;
    protected double costos;

    public Corrida(String linea, LocalDate fecha, LocalTime hora, String origen, String destino, int tiempoMinutos, double costos) {
        this.linea = linea;
        this.fecha = fecha;
        this.hora = hora;
        this.origen = origen;
        this.destino = destino;
        this.tiempoMinutos = tiempoMinutos;
        this.costos = costos;
    }

    public String getLinea() {
        return linea;
    }

    public void setLinea(String linea) {
        this.linea = linea;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public int getTiempoMinutos() {
        return tiempoMinutos;
    }

    public void setTiempoMinutos(int tiempoMinutos) {
        this.tiempoMinutos = tiempoMinutos;
    }

    public double getCostos() {
        return costos;
    }

    public void setCostos(double costos) {
        this.costos = costos;
    }

    @Override
    public String toString() {
        return "| Línea: " + linea + " | Fecha: " + fecha + " | Hora: " + hora +
                " | Origen: " + origen + " | Destino: " + destino +
                " | Duración: " + tiempoMinutos + " min | Costo: $" + costos + " |";
    }

    @Override
    public int compareTo(Corrida c) {
        // Compara por línea de autobús
        int comparacionLinea = this.linea.compareTo(c.linea);
        if (comparacionLinea != 0) return comparacionLinea;

        int comparacionFecha = this.fecha.compareTo(c.fecha);
        if (comparacionFecha != 0) return comparacionFecha;

        int comparacionHora = this.hora.compareTo(c.hora);
        if (comparacionHora != 0) return comparacionHora;

        int comparacionOrigen = this.origen.compareTo(c.origen);
        if (comparacionOrigen != 0) return comparacionOrigen;

        int comparacionDestino = this.destino.compareTo(c.destino);
        if (comparacionDestino != 0) return comparacionDestino;

        return Double.compare(this.costos, c.costos);
    }
}
