package funcionamiento;
import ed.ito.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class MyApp {
    public static void menu(){
        lecturaArchivos lector = new lecturaArchivos();


        ArbolBinario<Corrida> arbol = lector.leerTxt("catalogo.txt");
        Scanner sc = new Scanner(System.in);
        boolean salir = false;
        int opcion = 0;
        while(!salir) {
            System.out.println("\nCatalogo de transporte: corridas de autobuses");
            System.out.println("Opciones disponibles:\n" +
                    "1. Iniciar busqueda por filtro \n" +
                    "2. Listar todas las corridas \n" +
                    "3. Salir");

            opcion = sc.nextInt();

            switch (opcion){
                case 1: buscarCorrida(sc, arbol);
                break;
                case 2: mostrarCorridas(arbol);
                break;
                case 3: salir = true;
                break;
                default: System.out.println("Opción no válida");
            }


        }
    }

    private static void buscarCorrida(Scanner sc, ArbolBinario<Corrida> arbol) {
        try {
            int tipoBusqueda = 0;
            sc.nextLine();
            System.out.print("Fecha (AAAA-MM-DD): ");
            LocalDate fecha = LocalDate.parse(sc.nextLine());
            System.out.print("Origen: ");
            String origen = sc.nextLine();
            System.out.print("Destino: ");
            String destino = sc.nextLine();
            System.out.println("Tipo de busqueda:\n" +
                    "(1.General)\t(2.Especifica)");
            tipoBusqueda = sc.nextInt();
            sc.nextLine();

            Corrida criterio = new Corrida("", fecha, LocalTime.MIN, origen, destino, 0, 0.0);
            ComparadorCorrida comparator = new ComparadorCorrida();
            ComparadorCorridaEspecifica comparatorE = new ComparadorCorridaEspecifica();

            ListaDinamica<Corrida> resultados = arbol.search(criterio, (tipoBusqueda == 1)? comparator : comparatorE);

            if (resultados.isEmpty()) {
                System.out.println("No se encontraron resultados.");
            } else {
                System.out.println("Resultados de búsqueda:");
                for (int i = 0; i < resultados.size(); i++) {
                    System.out.println(resultados.getItem(i));
                }
            }
        } catch (Exception e) {
            System.out.println("Error al buscar corrida: " + e.getMessage());
        }
    }

    private static void mostrarCorridas(ArbolBinario<Corrida> arbol) {
        ListaDinamica<Corrida> listaDeItems = arbol.listarCorridas();
        try {
            for (int i = 0; i < listaDeItems.size(); i++) {
                System.out.println(listaDeItems.getItem(i));
            }
        }catch (ExcepcionDeElementoNoEncontrado | ExcepcionDeListaVacia e){
            System.out.println("Error con el catalogo");
        }
    }






}
