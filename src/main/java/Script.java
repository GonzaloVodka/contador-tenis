import com.google.gson.Gson;

import java.io.*;
import java.util.*;

public class Script {

    public static final String PUNTAJES_TXT = "src/puntajes.txt";

    public Object init(String nombreUno, String nombreDos, String resultadoUno, String resultadoDos) throws IOException {
        File archivo = new File(PUNTAJES_TXT);
        Scanner scanner = new Scanner(archivo);
        int cantidadDeJugadores = scanner.nextInt();
        List<Jugador> jugadores = new ArrayList<>();

        for (int i = 0; i < cantidadDeJugadores; i++) {
            int partidosJugados = scanner.nextInt();
            Jugador jugador = new Jugador(scanner.next(), partidosJugados);
            for (int j = 0; j < partidosJugados; j++) {
                jugador.agregarResultado(scanner.next());
            }
            jugadores.add(jugador);
        }

        Jugador jugadorUno = actualizarJugador(nombreUno, resultadoUno, jugadores);
        Jugador jugadorDos = actualizarJugador(nombreDos, resultadoDos, jugadores);

        jugadores.remove(jugadorUno);
        jugadores.remove(jugadorDos);

        jugadores.add(jugadorUno);
        jugadores.add(jugadorDos);

        scanner.close();
        PrintWriter printWriter = new PrintWriter(new FileWriter(archivo));
        printWriter.println(jugadores.size());
        jugadores.forEach(jugador -> {
            printWriter.println(jugador.getResultados().size());
            printWriter.println(jugador.getNombre());
            jugador.getResultados().forEach(printWriter::println);
        });

        printWriter.close();

        return new Gson().toJsonTree(jugadores);
    }

    private Jugador actualizarJugador(String nombreDelJugador, String resultadoUno, List<Jugador> jugadores) {
        Jugador jugador = jugadores.stream()
                .filter(j -> j.getNombre().equals(nombreDelJugador))
                .findFirst()
                .orElse(new Jugador(nombreDelJugador,0));

        jugador.agregarResultado(resultadoUno);
        jugador.incrementarPartidos();
        return jugador;
    }

}
