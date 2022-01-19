package app.geocoding;

import app.model.GeoAddress;


public interface GeoCodingService {
    GeoAddress getAddressFromCoordinates(GeoAddress.Coordinates coordinates);
    GeoAddress getCoordinatesFromAddress(GeoAddress.Address coordinates);
}
