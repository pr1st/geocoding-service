package app.geocoding.yandex;

import app.model.GeoAddress;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Optional;

@Component
public class GeoCodingYandexClient {
    private static final Log log = LogFactory.getLog(GeoCodingYandexClient.class);
    private static final String resourceUrl = "https://geocode-maps.yandex.ru/1.x?apikey={apikey}&geocode={geocode}&format=json";

    private final RestTemplate restTemplate;
    private final String apiKey;

    public GeoCodingYandexClient(RestTemplate restTemplate, @Value("${yandex.api.key}") String apiKey) {
        this.restTemplate = restTemplate;
        this.apiKey = apiKey;
    }

    public Optional<GeoAddress> requestGeoAddress(GeoAddress.Coordinates coordinates) {
        return makeRequest(coordinates.x() + "," + coordinates.y());
    }

    public Optional<GeoAddress> requestGeoAddress(GeoAddress.Address address) {
        return makeRequest(address.fullName());
    }

    private Optional<GeoAddress> makeRequest(String geoCodeParam) {
        var request = RequestEntity.get(resourceUrl, apiKey, geoCodeParam)
                .accept(MediaType.APPLICATION_JSON)
                .build();

        var response = restTemplate.exchange(request, String.class);
        if (!response.getStatusCode().is2xxSuccessful()) {
            if (log.isErrorEnabled())
                log.error("Could not retrieve data: [" + response.getStatusCode() + "] body: " + response.getBody());
            return Optional.empty();
        }

        final JsonNode tree;
        try {
            tree = new ObjectMapper().readTree(response.getBody());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        var address = tree.get("response")
                .get("GeoObjectCollection")
                .get("featureMember")
                .get(0)
                .get("GeoObject")
                .get("metaDataProperty")
                .get("GeocoderMetaData")
                .get("text")
                .asText();
        var point = tree.get("response")
                .get("GeoObjectCollection")
                .get("featureMember")
                .get(0)
                .get("GeoObject")
                .get("Point")
                .get("pos")
                .asText();
        var s = point.split(" ");
        var x = Double.parseDouble(s[0]);
        var y = Double.parseDouble(s[1]);

        var geoAddress = GeoAddress.of(new GeoAddress.Address(address), new GeoAddress.Coordinates(x, y));
        if (log.isInfoEnabled()) log.info("Received new GeoAddress entity: " + geoAddress);

        return Optional.of(geoAddress);
    }
}
