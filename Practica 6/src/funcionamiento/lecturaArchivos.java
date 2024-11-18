package funcionamiento;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;

import ed.ito.*;
public class lecturaArchivos {

    public ArbolBinario<Corrida> leerTxt(String direccion){

        ArbolBinario<Corrida> listaDinamicaCorridas = new ArbolBinario<>();

        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader(direccion));
            String bfRead;

            while ((bfRead = bufferedReader.readLine()) != null){
                String[] datos = bfRead.split("/");
                if (datos.length == 7) {

                    Corrida corridaNueva = new Corrida(
                            datos[0],                    //linea
                            LocalDate.parse(datos[1]),   //fecha
                            LocalTime.parse(datos[2]),   //hora
                            datos[3],                    //origen
                            datos[4],                    //destino
                            Integer.parseInt(datos[5]),  //duracion
                            Double.parseDouble(datos[6]) //precio
                    );

                    listaDinamicaCorridas.add(corridaNueva);
                }
                else {
                    System.out.println("Linea ignorada por formato invalido: [" + bfRead + "]");
                }

            }
        }catch(IOException | ExcepcionNodoRepetido e){
            System.out.println("Error en la lectura de archivos.");
        }

        return listaDinamicaCorridas;
    }

}
