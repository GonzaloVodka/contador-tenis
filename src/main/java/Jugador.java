import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Jugador {

    @Id
    @Column
    private String nombre;

    @Column
    private Integer partidosJugados;

    private List<String> resultados = new ArrayList<>();

    public Integer getPartidosJugados() {
        return partidosJugados;
    }

    public void agregarResultado(String resultado){
        resultados.add(resultado);
    }

    public List<String> getResultados(){
        return resultados;
    }

    public void setPartidosJugados(Integer partidosJugados) {
        this.partidosJugados = partidosJugados;
    }

    Jugador(String nombre, Integer partidosJugados) {
        this.nombre = nombre;
        this.partidosJugados = partidosJugados;
    }

    public void incrementarPartidos() {
        this.partidosJugados++;
    }

    public String getNombre() {
        return nombre;
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
