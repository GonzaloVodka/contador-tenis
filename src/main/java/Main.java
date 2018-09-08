import com.mercadolibre.restclient.exception.ParseException;

import static spark.Spark.*;


public class Main {

    public static void main(String [] args) throws ParseException {
        get("/", (req, body) -> {
            System.out.println("Entró a root");
            return "Welcome";
        });
        get("/nuevo/jugadores/:jugador1/:jugador2/resultado/:puntaje1/:puntaje2", (request, response) -> {
            return new Script().init(request.params(":jugador1"), request.params(":jugador2"), request.params(":puntaje1"), request.params(":puntaje2"));
        });
        get("/ping", (req, body) -> "pong");
    }
}
