package app.geocoding.yandex;

import app.geocoding.GeoAddressNotFoundException;
import app.geocoding.GeoCodingService;
import app.model.GeoAddress;
import org.springframework.stereotype.Service;

@Service
public class GeoCodingYandexService implements GeoCodingService {

    private final GeoCodingYandexClient client;

    public GeoCodingYandexService(GeoCodingYandexClient client) {
        this.client = client;
    }

    @Override
    public GeoAddress getAddressFromCoordinates(GeoAddress.Coordinates coordinates) {
        var optional = client.requestGeoAddress(coordinates);
        if (optional.isEmpty()) throw new GeoAddressNotFoundException(coordinates.toString());
        return optional.get();
    }

    @Override
    public GeoAddress getCoordinatesFromAddress(GeoAddress.Address address) {
        var optional = client.requestGeoAddress(address);
        if (optional.isEmpty()) throw new GeoAddressNotFoundException(address.toString());
        return optional.get();
    }
}
