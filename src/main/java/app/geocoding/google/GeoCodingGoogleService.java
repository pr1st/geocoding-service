package app.geocoding.google;

import app.geocoding.GeoAddressNotFoundException;
import app.geocoding.GeoCodingService;
import app.model.GeoAddress;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class GeoCodingGoogleService implements GeoCodingService {
    private static final Map<GeoAddress.Address, GeoAddress.Coordinates> map =
            Map.of(
                    new GeoAddress.Address("single"), new GeoAddress.Coordinates(1.0, 0.0),
                    new GeoAddress.Address("b2"), new GeoAddress.Coordinates(1.2, 3123.3123),
                    new GeoAddress.Address("base"), new GeoAddress.Coordinates(1.1, 1.1)
            );

    @Override
    public GeoAddress getAddressFromCoordinates(GeoAddress.Coordinates coordinates) {
        var first = map.entrySet().stream().filter(i -> i.getValue().equals(coordinates)).findFirst();
        if (first.isEmpty()) throw new GeoAddressNotFoundException(coordinates.toString());

        return GeoAddress.of(first.get().getKey(), first.get().getValue());
    }

    @Override
    public GeoAddress getCoordinatesFromAddress(GeoAddress.Address address) {
        var coordinates = map.get(address);
        if (coordinates == null) throw new GeoAddressNotFoundException(address.toString());

        return GeoAddress.of(address, coordinates);
    }
}
