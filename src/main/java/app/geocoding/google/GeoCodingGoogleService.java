package app.geocoding.google;

import app.geocoding.GeoCodingService;
import app.model.GeoAddress;

public class GeoCodingGoogleService implements GeoCodingService {
    @Override
    public GeoAddress.Address getAddressFromCoordinates(GeoAddress.Coordinates coordinates) {
        return null;
    }

    @Override
    public GeoAddress.Coordinates getCoordinatesFromAddress(GeoAddress.Address coordinates) {
        return null;
    }
}
