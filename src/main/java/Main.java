import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadolibre.restclient.Response;
import com.mercadolibre.restclient.RestClient;
import com.mercadolibre.restclient.exception.ParseException;
import com.mercadolibre.restclient.exception.RestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static spark.Spark.*;


public class Main {

    Logger logger = LoggerFactory.getLogger(getClass());

    public static void main(String [] args) throws ParseException {
        get("/site/:site", (request, response) -> {
            return new Script().init(request.params(":site"));
        });
        get("/ping", (req, body) -> "pong");
    }
}
