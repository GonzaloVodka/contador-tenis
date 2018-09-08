import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mercadolibre.restclient.Response;
import com.mercadolibre.restclient.RestClient;
import com.mercadolibre.restclient.exception.ParseException;
import com.mercadolibre.restclient.exception.RestException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Script {

    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public Object init(String site) {
        List<Domain> domains = new ArrayList<>();
        try {
            domains = getDomains(site).stream().sorted(Comparator.comparing(Domain::getName)).collect(Collectors.toList());
        } catch (RestException | ParseException e) {
            e.printStackTrace();
        }

        String beforeDomain = "";
        String actualDomain;
        String proxDomain;
        Integer lastElement = domains.size() - 1;
        for (int i = lastElement; i > 0; i--) {
            if (i != lastElement) {
                beforeDomain = domains.get(i + 1).getName();
            }
            actualDomain = domains.get(i).getName();
            proxDomain = domains.get(i - 1).getName();
            if (!cutDomain(actualDomain).equals(cutDomain(proxDomain)) && !cutDomain(actualDomain).equals(cutDomain(beforeDomain))) {
                domains.remove(domains.get(i));
            }
        }

        return gson.toJson(domains);
    }

    private static String cutDomain(String domain) {
        Integer beginIndex = domain.indexOf(" ");
        if (beginIndex != -1) {
            return domain.substring(0, beginIndex);
        }
        return domain;
    }

    private static List<Domain> getDomains(String site) throws RestException, ParseException {
        Response response = RestClient.getDefault().get("https://api.mercadolibre.com/sites/" + site + "/domains");
        return new ObjectMapper().convertValue(response.getData(), new TypeReference<List<Domain>>() {
    });
}

}
