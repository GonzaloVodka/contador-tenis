import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Jugador {

    private String nombre;
    private List<String> resultados;
    private Integer partidosJugados;

    Jugador(String nombre, Integer partidosJugados) {
        this.nombre = nombre;
        this.partidosJugados = partidosJugados;
        resultados = new ArrayList<>();
    }

    public void incrementarPartidos() {
        this.partidosJugados++;
    }

    public void agregarResultado(String resultado) {
        resultados.add(resultado);
    }

    public String getNombre() {
        return nombre;
    }

    public List<String> getResultados() {
        return resultados;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Jugador jugador = (Jugador) o;
        return Objects.equals(nombre, jugador.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre);
    }
}
